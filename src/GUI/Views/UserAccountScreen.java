package GUI.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Clases.Cliente;
import Clases.CuentaBancaria;
import Clases.Banco;
import GUI.Components.BaseScreenWithSideMenu;
import GUI.Controllers.SelectedUserManager;
import GUI.Tables.AccountTable;
import GUI.Tables.ClientTable;

public class UserAccountScreen extends BaseScreenWithSideMenu {
    
    private JTable accountTable;
    private DefaultTableModel mt;
    private JScrollPane s1;
    private ArrayList<CuentaBancaria> cuentas;
    private Cliente cliente;
    private JLabel detailLabel;
    private JPanel detailPanel;

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

        JLabel label = new JLabel("Cuentas del Cliente");
        label.setFont(new Font("Tahoma", Font.BOLD, 24));
        label.setForeground(new Color(0, 128, 0));
        label.setBounds(550, 50, 300, 30);
        add(label);

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
        
        depositButton.addActionListener(listener);
        withdrawButton.addActionListener(listener);
        transferButton.addActionListener(listener);
        detailsButton.addActionListener(listener);
        
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
    
    private void showAccountDetails() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow != -1) {
            CuentaBancaria cuentaSeleccionada = cuentas.get(selectedRow);
            detailLabel.setText("<html><strong>Número de Cuenta:</strong> " + cuentaSeleccionada.getNoCuenta() + "<br/>" +
                                "<strong>Saldo:</strong> " + cuentaSeleccionada.getSaldo() + "<br/>" +
                                "<strong>Beneficiario:</strong> " + cuentaSeleccionada.getBeneficiario() + "</html>");
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
