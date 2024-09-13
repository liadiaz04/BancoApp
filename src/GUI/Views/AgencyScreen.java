package GUI.Views;

import java.awt.Color;

import GUI.Components.SaldoDesglosadoDialog;

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

import GUI.Components.BaseScreenWithSideMenu;
import GUI.Tables.AgenciesTable;



import GUI.Tables.CajeroTable;
import Logic.Agencia;
import Logic.Banco;
import Logic.Billete;
import Logic.Cajero;
import Logic.Cliente;

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
    private JTable cajerosTable; 
    private JScrollPane cajerosScrollPane;
    private DefaultTableModel cajerosTableModel;
    
    
    public AgencyScreen(ActionListener listener) {
        super(listener);
        setBackground(Color.WHITE);
    }

    
    @Override
    protected void loadContent() {
    	JLabel label = new JLabel("Agencias ");
        label.setFont(new Font("Tahoma", Font.BOLD, 24)); 
        label.setForeground(new Color(0, 128, 0)); 
        label.setBounds(550, 50, 200, 30);
        add(label);
        
        JLabel labelCajero = new JLabel("Cajeros ");
        labelCajero.setFont(new Font("Tahoma", Font.BOLD, 24)); 
        labelCajero.setForeground(new Color(0, 128, 0)); 
        labelCajero.setBounds(1400, 50, 200, 250);
        add(labelCajero);

        agencias = getAgenciesList();
        String[] columns = new String[]{"Identificador", "Gerente", "Direccion"};

        final AgenciesTable agenciesTableWrapper = new AgenciesTable(agencias, columns);
        this.agenciesTable = agenciesTableWrapper.getTable(); // Obtener la JTable interna
        agenciesTableWrapper.setBounds(480, 100, 800, 600);
        add(agenciesTableWrapper);

        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        
        // TABLA CAJEROS
        String[] cajeroColumns = new String[]{"ID Cajero", "Saldo Total"};
        cajerosTableModel = new DefaultTableModel(cajeroColumns, 0);
        cajerosTable = new JTable(cajerosTableModel);
        cajerosScrollPane = new JScrollPane(cajerosTable);
        cajerosScrollPane.setBounds(1350, 200, 400, 400);
        add(cajerosScrollPane);
     
        
        styleTable(agenciesTable);
        styleTable(cajerosTable);


        // BOTONES
        JButton addAgencyButton = new JButton("Agregar");
        customizeButton(addAgencyButton, buttonFont, 480, 800);
        add(addAgencyButton);

        JButton deleteAgencyButton = new JButton("Eliminar");
        customizeButton(deleteAgencyButton, buttonFont, 680, 800);
        add(deleteAgencyButton);
        
        JButton viewCajerosButton = new JButton("Ver cajeros");
        customizeButton(viewCajerosButton, buttonFont, 880, 800);
        add(viewCajerosButton);
        

        JButton addCajero = new JButton("Agregar cajero");
        customizeButton(addCajero, buttonFont, 1080, 800);
        add(addCajero);

        JButton viewCajeroDetailsButton = new JButton("Saldo Desglosado");
        customizeButton(viewCajeroDetailsButton, buttonFont, 1350, 650);
        add(viewCajeroDetailsButton);

        JButton deleteCajero = new JButton("Eliminar Cajero");
        customizeButton(deleteCajero, buttonFont, 1560, 650);
        add(deleteCajero);

        // ASIGNAR ActionListeners
        addAgencyButton.addActionListener(listener);
        deleteAgencyButton.addActionListener(listener);
        addCajero.addActionListener(listener);
        viewCajerosButton.addActionListener(listener);
        viewCajeroDetailsButton.addActionListener(listener);
        deleteCajero.addActionListener(listener);
        
        
       
        
        //VER CAJEROS
        viewCajerosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = agenciesTable.getSelectedRow(); 
                if (selectedRow != -1) {
                    String agencyId = (String) agenciesTable.getValueAt(selectedRow, 0); 
                    Agencia selectedAgency = findAgencyById(agencyId); 
                    if (selectedAgency != null) {
                        ArrayList<Cajero> cajeros = selectedAgency.getCajeros(); 
                        if (cajeros.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No hay cajeros disponibles para esta agencia.", "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            updateCajerosTable(cajeros); 
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una agencia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

     // AGREGAR CAJERO
        addCajero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = agenciesTable.getSelectedRow();
                if (selectedRow != -1) {
                    String agencyId = (String) agenciesTable.getValueAt(selectedRow, 0);
                    Agencia selectedAgency = findAgencyById(agencyId);
                    if (selectedAgency != null) {
                        
                        if (selectedAgency.getCajeros().size() < 5) {
                            selectedAgency.agregarCajero(); 
                            JOptionPane.showMessageDialog(null, "Cajero agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            updateCajerosTable(selectedAgency.getCajeros());
                        } else {
                            
                            JOptionPane.showMessageDialog(null, "La agencia no puede tener más de 5 cajeros.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una agencia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

     // SALDO DESGLOSADO
        viewCajeroDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = cajerosTable.getSelectedRow(); 
                if (selectedRow != -1) {
                    String cajeroId = (String) cajerosTable.getValueAt(selectedRow, 0); 
                    Agencia selectedAgency = getSelectedAgency(); 
                    if (selectedAgency != null) {
                        Cajero selectedCajero = findCajeroById(selectedAgency, cajeroId); 
                        if (selectedCajero != null) {
                            String saldoDesglosado = selectedCajero.mostrarSaldoDesglosado();                         
                            if (saldoDesglosado == null || saldoDesglosado.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "El cajero " + cajeroId + " no tiene saldo.", "Sin Saldo", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                new SaldoDesglosadoDialog(null, cajeroId, saldoDesglosado); 
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un cajero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        
        //ELIMINAR CAJERO
        deleteCajero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = cajerosTable.getSelectedRow();
                if (selectedRow != -1) {
                    String cajeroId = (String) cajerosTable.getValueAt(selectedRow, 0);
                    Agencia selectedAgency = getSelectedAgency();
                    if (selectedAgency != null) {
                        int response = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar el cajero " + cajeroId + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            boolean deleted = selectedAgency.eliminarCajero(cajeroId);
                            if (deleted) {
                                JOptionPane.showMessageDialog(null, "Cajero eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                updateCajerosTable(selectedAgency.getCajeros());
                            } else {
                                JOptionPane.showMessageDialog(null, "Cajero no encontrado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un cajero para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });



     // AGREGAR AGENCIA
        addAgencyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JDialog addAgencyDialog = new JDialog();
                addAgencyDialog.setTitle("Agregar Agencia");
                addAgencyDialog.setSize(400, 300);
                addAgencyDialog.setLayout(new GridBagLayout());
                addAgencyDialog.getContentPane().setBackground(new Color(240, 255, 240));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                JLabel managerLabel = new JLabel("Gerente:");
                managerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
                gbc.gridx = 0;
                gbc.gridy = 0;
                addAgencyDialog.add(managerLabel, gbc);

                final JTextField managerField = new JTextField(20);
                gbc.gridx = 0;
                gbc.gridy = 1;
                addAgencyDialog.add(managerField, gbc);

                JLabel addressLabel = new JLabel("Dirección:");
                addressLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
                gbc.gridx = 0;
                gbc.gridy = 2;
                addAgencyDialog.add(addressLabel, gbc);

                final JTextField addressField = new JTextField(20);
                gbc.gridx = 0;
                gbc.gridy = 3;
                addAgencyDialog.add(addressField, gbc);

                JButton saveButton = new JButton("Guardar");
                saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
                saveButton.setBackground(new Color(119, 221, 119));
                saveButton.setForeground(Color.WHITE);
                saveButton.setPreferredSize(new Dimension(150, 40));
                gbc.gridx = 0;
                gbc.gridy = 4;
                addAgencyDialog.add(saveButton, gbc);

                saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String gerente = managerField.getText();
                        String direccion = addressField.getText();

                        if (gerente.isEmpty() || direccion.isEmpty()) {
                            JOptionPane.showMessageDialog(addAgencyDialog, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        Banco.getInstancia().agregarAgencia(gerente, direccion);
                        
                        ((DefaultTableModel) agenciesTable.getModel()).addRow(new Object[]{
                            Banco.getInstancia().getAgencias().get(Banco.getInstancia().getAgencias().size() - 1).getIdAgencia(),
                            gerente,
                            direccion
                        });
                        addAgencyDialog.dispose();
                    }
                });

                addAgencyDialog.setVisible(true);
            }
        });

     // ELIMINAR AGENCIA
        deleteAgencyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = agenciesTable.getSelectedRow();
                if (selectedRow != -1) {
                    int response = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta agencia?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        String agencyId = (String) agenciesTable.getValueAt(selectedRow, 0);
                        
                        Banco.getInstancia().eliminarAgencia(agencyId);
                        
                        ((DefaultTableModel) agenciesTable.getModel()).removeRow(selectedRow);
                        
                        agencias.remove(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

 
      
    }
    
    private Agencia getSelectedAgency() {
        int selectedRow = agenciesTable.getSelectedRow();
        if (selectedRow != -1) {
            String agencyId = (String) agenciesTable.getValueAt(selectedRow, 0);
            return findAgencyById(agencyId);
        }
        return null;
    }

    private Cajero findCajeroById(Agencia agencia, String cajeroId) {
        for (Cajero cajero : agencia.getCajeros()) {
            if (cajero.getIdCajero().equals(cajeroId)) {
                return cajero;
            }
        }
        return null;
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
