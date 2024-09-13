package GUI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import GUI.Components.BaseScreenWithSideMenu;
import Logic.Banco;
import Logic.Contrato;

public class ContratoScreen extends BaseScreenWithSideMenu {

    private JTable contratoTable;
    private DefaultTableModel tableModel;
    private JScrollPane s1;

    public ContratoScreen(ActionListener listener) {
        super(listener);
        setBackground(Color.WHITE);
    }

    @Override
    protected void loadContent() {
        JLabel label = new JLabel("Contratos");
        label.setFont(new Font("Tahoma", Font.BOLD, 24));
        label.setForeground(new Color(0, 128, 0));
        label.setBounds(550, 50, 200, 30);
        add(label);

        ArrayList<Contrato> contratos = getContratosList();
        String[] columns = new String[]{"ID Contrato", "Entidad", "Periodo de Tiempo (Meses)", "Salario"};


        tableModel = new DefaultTableModel(columns, 0);
        contratoTable = new JTable(tableModel);
        s1 = new JScrollPane(contratoTable);
        s1.setBounds(550, 100, 1180, 600);
        add(s1);

     
        styleTable(contratoTable);

      
        fillTable(contratos);

     
        Font buttonFont = new Font("Tahoma", Font.BOLD, 16);

        JButton addContractButton = new JButton("Agregar Contrato");
        customizeButton(addContractButton, buttonFont, 800, 800);
        add(addContractButton);

        JButton deleteContractButton = new JButton("Eliminar Contrato");
        customizeButton(deleteContractButton, buttonFont, 1200, 800);
        add(deleteContractButton);

      
        addContractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoAgregarContrato();
            }
        });
        
        // ELIMINAR CONTRATO
        deleteContractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = contratoTable.getSelectedRow();
                if (selectedRow != -1) {
                    String contractId = (String) contratoTable.getValueAt(selectedRow, 0); 
                    int response = JOptionPane.showConfirmDialog(null, 
                        "¿Estás seguro de que deseas eliminar el contrato con ID " + contractId + "?", 
                        "Confirmar eliminación", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
                    
                    if (response == JOptionPane.YES_OPTION) {
                        boolean deleted = Banco.getInstancia().eliminarContrato(contractId);
                        if (deleted) {
                            JOptionPane.showMessageDialog(null, "Contrato eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            ((DefaultTableModel) contratoTable.getModel()).removeRow(selectedRow);
                        } else {
                            JOptionPane.showMessageDialog(null, "Contrato no encontrado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un contrato para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }

    
    private void mostrarDialogoAgregarContrato() {
        JTextField entidadField = new JTextField(15);
        JTextField periodoField = new JTextField(5);
        JTextField salarioField = new JTextField(10);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Entidad:"));
        panel.add(entidadField);
        panel.add(new JLabel("Periodo (Meses):"));
        panel.add(periodoField);
        panel.add(new JLabel("Salario:"));
        panel.add(salarioField);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Contrato", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String entidad = entidadField.getText().trim();
                String periodoStr = periodoField.getText().trim();
                String salarioStr = salarioField.getText().trim();

                if (entidad.isEmpty() || !validarNombre(entidad)) {
                    JOptionPane.showMessageDialog(null, "La entidad solo debe contener letras y no estar vacía.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int periodo;
                try {
                    periodo = Integer.parseInt(periodoStr);
                    if (periodo <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El periodo debe ser un número entero positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double salario;
                try {
                    salario = Double.parseDouble(salarioStr);
                    if (salario <= 0) {
                        throw new NumberFormatException(); 
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El salario debe ser un número positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Banco.getInstancia().agregarContrato(entidad, periodo, salario);

                actualizarTabla();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private boolean validarNombre(String nombre) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+( [a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*$";
        return nombre.matches(regex);
    }


    private void actualizarTabla() {
        ArrayList<Contrato> contratos = getContratosList();
        fillTable(contratos);
    }


    private void fillTable(ArrayList<Contrato> contratos) {
        tableModel.setRowCount(0); 
        for (Contrato contrato : contratos) {
            Object[] rowData = new Object[]{
                contrato.getIdContrato(), 
                contrato.getEntidad(),
                contrato.getPeriodoTiempo(),
                contrato.getSalario()
            };
            tableModel.addRow(rowData);
        }
    }

    private ArrayList<Contrato> getContratosList() {
        Banco banco = Banco.getInstancia();
        return banco.getContratos();
    }

    private void customizeButton(JButton button, Font font, int x, int y) {
        button.setBounds(x, y, 185, 53);
        button.setBackground(new Color(119, 221, 119));
        button.setForeground(Color.WHITE);
        button.setFont(font);
    }


    private void styleTable(JTable table) {
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setBackground(Color.white);
        table.setForeground(Color.black);  

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(144, 238, 144)); 
        tableHeader.setForeground(Color.white); 
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 18)); 
    }
}
