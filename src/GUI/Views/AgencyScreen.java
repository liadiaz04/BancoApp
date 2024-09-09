package GUI.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;

import Clases.Agencia;
import Clases.Banco;
import Clases.Billete;
import Clases.Cajero;
import Clases.Cliente;
import GUI.Components.BaseScreenWithSideMenu;
import GUI.Tables.AgenciesTable;



import GUI.Tables.CajeroTable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AgencyScreen extends BaseScreenWithSideMenu {
    private JTable agenciesTable;
    private DefaultTableModel mt;
    private JScrollPane s1;
    private ArrayList<Agencia> agencias;
    private JTable cajerosTable; // Tabla de cajeros
    private JScrollPane cajerosScrollPane; // JScrollPane para la tabla de cajeros
    private DefaultTableModel cajerosTableModel;
    
    
    public AgencyScreen(ActionListener listener) {
        super(listener);
        setBackground(Color.WHITE);
    }

    
    @Override
    protected void loadContent() {
        JLabel label = new JLabel("Agencias");
        label.setBounds(550, 50, 200, 30);
        add(label);

        agencias = getAgenciesList();
        String[] columns = new String[]{"Identificador", "Gerente", "Direccion"};

        final AgenciesTable agenciesTableWrapper = new AgenciesTable(agencias, columns);
        this.agenciesTable = agenciesTableWrapper.getTable(); // Obtener la JTable interna
        agenciesTableWrapper.setBounds(480, 100, 800, 600);
        add(agenciesTableWrapper);

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        
        // Cajeros table setup
        String[] cajeroColumns = new String[]{"ID Cajero", "Saldo Total"};
        cajerosTableModel = new DefaultTableModel(cajeroColumns, 0);
        cajerosTable = new JTable(cajerosTableModel);
        cajerosScrollPane = new JScrollPane(cajerosTable);
        cajerosScrollPane.setBounds(1350, 200, 400, 400);
        add(cajerosScrollPane);
     
        
        styleTable(agenciesTable);
        styleTable(cajerosTable);


        // Crear y personalizar los botones
        JButton addAgencyButton = new JButton("Agregar");
        customizeButton(addAgencyButton, buttonFont, 550, 800);
        add(addAgencyButton);

        JButton deleteAgencyButton = new JButton("Eliminar");
        customizeButton(deleteAgencyButton, buttonFont, 780, 800);
        add(deleteAgencyButton);
        
        JButton viewCajerosButton = new JButton("Ver cajeros");
        customizeButton(viewCajerosButton, buttonFont, 1010, 800);
        add(viewCajerosButton);
        

        JButton viewAgencyDetailsButton = new JButton("Agregar cajero");
        customizeButton(viewAgencyDetailsButton, buttonFont, 1350, 650);
        add(viewAgencyDetailsButton);

        JButton viewCajeroDetailsButton = new JButton("Ver Detalles Cajero");
        customizeButton(viewCajeroDetailsButton, buttonFont, 1350, 800);
        add(viewCajeroDetailsButton);

        

        // Asignar ActionListeners
        addAgencyButton.addActionListener(listener);
        deleteAgencyButton.addActionListener(listener);
        viewAgencyDetailsButton.addActionListener(listener);
        viewCajerosButton.addActionListener(listener);
        viewCajeroDetailsButton.addActionListener(listener);
        
        viewCajerosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = agenciesTable.getSelectedRow(); // Get the selected row from agenciesTable
                if (selectedRow != -1) {
                    String agencyId = (String) agenciesTable.getValueAt(selectedRow, 0); // Get the ID of the selected agency
                    Agencia selectedAgency = findAgencyById(agencyId); // Find the agency by ID
                    if (selectedAgency != null) {
                        ArrayList<Cajero> cajeros = selectedAgency.getCajeros(); // Retrieve the cashiers for the selected agency
                        if (cajeros.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No hay cajeros disponibles para esta agencia.", "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            updateCajerosTable(cajeros); // Update the cajerosTable with the cashiers
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una agencia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });




        
    
       

        addAgencyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JDialog addAgencyDialog = new JDialog();
                addAgencyDialog.setTitle("Agregar Agencia");
                addAgencyDialog.setSize(400, 300);
                addAgencyDialog.setLayout(new GridBagLayout());
                addAgencyDialog.getContentPane().setBackground(new Color(240, 255, 240));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                JLabel idLabel = new JLabel("ID Agencia:");
                idLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
                gbc.gridx = 0;
                gbc.gridy = 0;
                addAgencyDialog.add(idLabel, gbc);

                final JTextField idField = new JTextField(20);
                gbc.gridx = 0;
                gbc.gridy = 1;
                addAgencyDialog.add(idField, gbc);

                JLabel managerLabel = new JLabel("Gerente:");
                managerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
                gbc.gridx = 0;
                gbc.gridy = 2;
                addAgencyDialog.add(managerLabel, gbc);

                final JTextField managerField = new JTextField(20);
                gbc.gridx = 0;
                gbc.gridy = 3;
                addAgencyDialog.add(managerField, gbc);

                JLabel addressLabel = new JLabel("Dirección:");
                addressLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
                gbc.gridx = 0;
                gbc.gridy = 4;
                addAgencyDialog.add(addressLabel, gbc);

                final JTextField addressField = new JTextField(20);
                gbc.gridx = 0;
                gbc.gridy = 5;
                addAgencyDialog.add(addressField, gbc);

                JButton saveButton = new JButton("Guardar");
                saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
                saveButton.setBackground(new Color(119, 221, 119));
                saveButton.setForeground(Color.WHITE);
                saveButton.setPreferredSize(new Dimension(150, 40)); // Hacer el botón más grande
                gbc.gridx = 0;
                gbc.gridy = 6;
                addAgencyDialog.add(saveButton, gbc);

                saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String idAgencia = idField.getText();
                        String gerente = managerField.getText();
                        String direccion = addressField.getText();

                        // Crear nueva agencia y agregarla a la lista
                        Agencia nuevaAgencia = new Agencia(idAgencia, gerente, direccion);
                        Banco.getInstancia().getAgencias().add(nuevaAgencia);
                        ((DefaultTableModel) agenciesTable.getModel()).addRow(new Object[]{nuevaAgencia.getIdAgencia(), gerente, direccion});
                        addAgencyDialog.dispose();
                    }
                });

                addAgencyDialog.setVisible(true);
            }
        });
        
        

        //Eliminar agencia
        deleteAgencyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = agenciesTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Mostrar un cuadro de diálogo de confirmación
                    int response = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta agencia?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        // Eliminar la agencia de la lista y del modelo de la tabla
                        agencias.remove(selectedRow);
                        ((DefaultTableModel) agenciesTable.getModel()).removeRow(selectedRow);
                    }
                } else {
                    // Mostrar un mensaje de advertencia si no hay ninguna fila seleccionada
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
                
      
    }
    private Agencia findAgencyById(String id) {
        for (Agencia agencia : agencias) {
            if (agencia.getIdAgencia().equals(id)) {
                return agencia;
            }
        }
        return null;
    }
    
    private void updateCajerosTable(ArrayList<Cajero> cajeros) {
        cajerosTableModel.setRowCount(0); // Clear existing rows
        for (Cajero cajero : cajeros) {
            cajerosTableModel.addRow(new Object[]{cajero.getIdCajero(), cajero.mostrarSaldoTotal()});
        }
    }

    // Método para personalizar botones
    private void customizeButton(JButton button, Font font, int x, int y) {
    	button.setBounds(x, y, 185, 53);
        button.setBackground(new Color(119, 221, 119));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 16));
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

    private ArrayList<Agencia> getAgenciesList() {
        Banco banco = Banco.getInstancia();
        ArrayList<Agencia> agencias = banco.getAgencias();
        return agencias;
    }
}
