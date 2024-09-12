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
import Clases.Banco;
import Clases.Contrato;
import GUI.Components.BaseScreenWithSideMenu;

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
        String[] columns = new String[]{"Entidad", "Periodo de Tiempo (Meses)", "Salario"};

        // Crear la tabla con el modelo de datos
        tableModel = new DefaultTableModel(columns, 0);
        contratoTable = new JTable(tableModel);
        s1 = new JScrollPane(contratoTable);
        s1.setBounds(550, 100, 1180, 600);
        add(s1);

        // Estilizar la tabla para que tenga un diseño similar al de AgencyScreen
        styleTable(contratoTable);

        // Llenar la tabla inicialmente
        fillTable(contratos);

        // Crear botones
        Font buttonFont = new Font("Tahoma", Font.BOLD, 16);

        JButton addContractButton = new JButton("Agregar Contrato");
        customizeButton(addContractButton, buttonFont, 800, 800);
        add(addContractButton);

        JButton deleteContractButton = new JButton("Eliminar Contrato");
        customizeButton(deleteContractButton, buttonFont, 1200, 800);
        add(deleteContractButton);

        // Añadir funcionalidad al botón de agregar contrato
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
                    String contractEntity = (String) contratoTable.getValueAt(selectedRow, 0); // Suponiendo que la entidad está en la primera columna
                    int response = JOptionPane.showConfirmDialog(null, 
                        "¿Estás seguro de que deseas eliminar el contrato de " + contractEntity + "?", 
                        "Confirmar eliminación", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
                    
                    if (response == JOptionPane.YES_OPTION) {
                        boolean deleted = Banco.getInstancia().eliminarContrato(contractEntity);
                        if (deleted) {
                            JOptionPane.showMessageDialog(null, "Contrato eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            // Actualizar la tabla, si es necesario
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

    // Método para mostrar el formulario emergente
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
                String entidad = entidadField.getText();
                int periodo = Integer.parseInt(periodoField.getText());
                double salario = Double.parseDouble(salarioField.getText());

                // Agregar el contrato a la lógica
                agregarContrato(entidad, periodo, salario);

                // Actualizar la tabla
                actualizarTabla();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor ingresa valores válidos.");
            }
        }
    }

    // Método para agregar un nuevo contrato al banco
    private void agregarContrato(String entidad, int periodo, double salario) {
        Banco banco = Banco.getInstancia();
        banco.agregarContrato(entidad, periodo, salario);
    }

    // Método para actualizar la tabla después de agregar un contrato
    private void actualizarTabla() {
        ArrayList<Contrato> contratos = getContratosList();
        fillTable(contratos);
    }

    // Llenar la tabla con la lista de contratos
    private void fillTable(ArrayList<Contrato> contratos) {
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Contrato contrato : contratos) {
            Object[] rowData = new Object[]{contrato.getEntidad(), contrato.getPeriodoTiempo(), contrato.getSalario()};
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

    // Método para estilizar la tabla, similar a la de AgencyScreen
    private void styleTable(JTable table) {
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setBackground(Color.white);
        table.setForeground(Color.black);  

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(144, 238, 144)); // Color verde claro
        tableHeader.setForeground(Color.white); // Texto blanco
        tableHeader.setFont(new Font("Tahoma", Font.BOLD, 18)); // Fuente del encabezado
    }
}
