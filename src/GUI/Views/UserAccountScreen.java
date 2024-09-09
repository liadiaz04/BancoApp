package GUI.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Clases.Cliente;
import Clases.CuentaBancaria;
import Clases.Banco;
import GUI.Components.BaseScreenWithSideMenu;
import GUI.Controllers.SelectedUserManager;
import GUI.Tables.AccountTable;
import GUI.Tables.ClientTable;

import java.time.format.DateTimeFormatter;

public class UserAccountScreen extends BaseScreenWithSideMenu {
    
    private JTable accountTable;
    private DefaultTableModel mt;
    private JScrollPane s1;
    private ArrayList<CuentaBancaria> cuentas;
    private Cliente cliente;
    private JLabel detailLabel;
    private JPanel detailPanel;
    private JLabel nameLabel;

    public UserAccountScreen(ActionListener listener) {
        super(listener);
        this.cliente = SelectedUserManager.getInstancia().getClienteSeleccionado();
        loadContent(); // Initial load
    }

    // New method to refresh the account table
    public void refreshContent() {
        this.cliente = SelectedUserManager.getInstancia().getClienteSeleccionado();
        updateAccountTable(); // Update only the account table
    }

    @Override
    protected void loadContent() {
    	
    	nameLabel = new JLabel();
    	nameLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
    	nameLabel.setForeground(new Color(0, 128, 0));
    	nameLabel.setBounds(550, 50, 300, 30);
        add(nameLabel);

        // Initialize account table
        updateAccountTable();
        
     // Create buttons
        Font buttonFont = new Font("Tahoma", Font.BOLD, 16);

        JButton depositButton = new JButton("Depositar");
        customizeButton(depositButton, buttonFont, 500, 800);
        add(depositButton);

        JButton withdrawButton = new JButton("Extraer");
        customizeButton(withdrawButton, buttonFont, 760, 800);
        add(withdrawButton);

        JButton transferButton = new JButton("Transferir");
        customizeButton(transferButton, buttonFont, 1020, 800);
        add(transferButton);
        
        JButton detailsButton = new JButton("Detalles de la Cuenta");
        customizeButton(detailsButton, buttonFont, 1280, 800);
        add(detailsButton);
        
        
        
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarDeposito(); // Método que abre el diálogo para el depósito
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarExtraccion(); // Método que abre el diálogo para la extracción
            }
        });

        transferButton.addActionListener(listener);
     // Agrega esto en el método loadContent() después de agregar el botón detailsButton
        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAccountDetails(); // Llama al método que muestra los detalles de la cuenta
            }
        });

        
     // Crear JPanel para mostrar los detalles del cliente
        detailPanel = new JPanel();
        detailPanel.setLayout(new GridBagLayout());
        detailPanel.setBounds(1550, 200, 300, 400); // Mover hacia abajo y alargar
        detailPanel.setBackground(new Color(240, 255, 240));
        add(detailPanel);

        // Crear JLabel estático para "Detalles del Cliente:"
        detailLabel = new JLabel("Detalles de la Cuenta:");
        detailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailLabel.setForeground(new Color(0, 128, 0));
        GridBagConstraints gbcStatic = new GridBagConstraints();
        gbcStatic.insets = new Insets(5, 5, 5, 5);
        gbcStatic.gridx = 0;
        gbcStatic.gridy = 0;
        detailPanel.add(detailLabel, gbcStatic);

        // Crear JLabel para mostrar los detalles
        detailLabel = new JLabel();
        detailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailLabel.setForeground(new Color(0, 128, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1; // Cambiar la posición a 1 para mover hacia abajo
        detailPanel.add(detailLabel, gbc);
        


        revalidate(); // Refresh the panel
        repaint(); // Repaint the panel
    }

    private void updateAccountTable() {
    	
    	String name = cliente == null ? "" : cliente.getNombre();
    	nameLabel.setText("Cuentas de " + name);

        
    	
        if (cliente != null) {
            cuentas = getAccountList(cliente);
            String[] columns = new String[]{"Número de Cuenta", "Saldo", "Beneficiario"};

            if (accountTable == null) {
                AccountTable accountTable = new AccountTable(cuentas, columns);
                this.accountTable = accountTable.getTable();
                accountTable.setBackground(new Color(144, 238, 144));
                accountTable.setBounds(480, 100, 1000, 600);
                add(accountTable);
            } else {
                // Update the existing table model with new data
                DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
                model.setRowCount(0); // Clear existing rows
                for (CuentaBancaria cuenta : cuentas) {
                    model.addRow(new Object[]{cuenta.getNoCuenta(), cuenta.getSaldo(), cuenta.getBeneficiario()});
                }
            }
        }
    }
    
    private void customizeButton(JButton button, Font font, int x, int y) {
        button.setBounds(x, y, 185, 53);
        button.setBackground(new Color(119, 221, 119));
        button.setForeground(Color.WHITE);
        button.setFont(font);
    }
    
    private void realizarDeposito() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow != -1) {
            CuentaBancaria cuentaSeleccionada = cuentas.get(selectedRow);

            // Crear el panel para la ventana emergente
            JPanel panelDeposito = new JPanel(new GridLayout(2, 2));
            JLabel cuentaLabel = new JLabel("Depósito en cuenta:");
            JTextField cuentaTextField = new JTextField(cuentaSeleccionada.getNoCuenta());
            cuentaTextField.setEditable(false); // No permitir la edición del número de cuenta
            JLabel montoLabel = new JLabel("Monto a depositar:");
            JTextField montoTextField = new JTextField();

            panelDeposito.add(cuentaLabel);
            panelDeposito.add(cuentaTextField);
            panelDeposito.add(montoLabel);
            panelDeposito.add(montoTextField);

            // Mostrar la ventana emergente para ingresar el monto
            int result = JOptionPane.showConfirmDialog(null, panelDeposito, "Depositar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double monto = Double.parseDouble(montoTextField.getText());

                    if (monto > 0) {
                        // Actualizar el saldo de la cuenta
                        cuentaSeleccionada.setSaldo(cuentaSeleccionada.getSaldo() + monto);

                        // Actualizar la tabla de cuentas
                        updateAccountTable();
                        JOptionPane.showMessageDialog(null, "Depósito realizado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una cuenta para depositar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void realizarExtraccion() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow != -1) {
            CuentaBancaria cuentaSeleccionada = cuentas.get(selectedRow);

            // Crear el panel para la ventana emergente
            JPanel panelExtraccion = new JPanel(new GridLayout(2, 2));
            JLabel cuentaLabel = new JLabel("Extraer de cuenta:");
            JTextField cuentaTextField = new JTextField(cuentaSeleccionada.getNoCuenta());
            cuentaTextField.setEditable(false); // No permitir la edición del número de cuenta
            JLabel montoLabel = new JLabel("Monto a extraer:");
            JTextField montoTextField = new JTextField();

            panelExtraccion.add(cuentaLabel);
            panelExtraccion.add(cuentaTextField);
            panelExtraccion.add(montoLabel);
            panelExtraccion.add(montoTextField);

            // Mostrar la ventana emergente para ingresar el monto
            int result = JOptionPane.showConfirmDialog(null, panelExtraccion, "Extraer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double monto = Double.parseDouble(montoTextField.getText());

                    if (monto > 0) {
                        if (monto <= cuentaSeleccionada.getSaldo()) {
                            // Actualizar el saldo de la cuenta restando el monto
                            cuentaSeleccionada.setSaldo(cuentaSeleccionada.getSaldo() - monto);

                            // Actualizar la tabla de cuentas
                            updateAccountTable();
                            JOptionPane.showMessageDialog(null, "Extracción realizada con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar la extracción.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una cuenta para extraer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void showAccountDetails() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow != -1) {
            CuentaBancaria cuentaSeleccionada = cuentas.get(selectedRow);
            
            // Formatear la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            // Establecer el texto en detailLabel
            detailLabel.setText("<html><strong>Moneda:</strong> " + cuentaSeleccionada.getMoneda() + "<br/>" +
                                "<strong>Fecha de Apertura:</strong> " + cuentaSeleccionada.getFechaApertura().format(formatter) + "</html>");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una cuenta para ver los detalles.");
        }
    }

    
    private ArrayList<CuentaBancaria> getAccountList(Cliente cliente) {
        Banco banco = Banco.getInstancia();
        ArrayList<CuentaBancaria> cuentas = banco.getCuentasCliente(cliente);
        return cuentas;
    }
}
