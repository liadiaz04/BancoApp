package GUI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import GUI.Components.BaseScreenWithSideMenu;
import Clases.Banco;
import Clases.Cliente;

public class ClienteConSaldoSuperior extends BaseScreenWithSideMenu {

    private JTextField montoField; // Campo de texto para el monto
    private JTable clientTable; // Tabla para mostrar los resultados
    private DefaultTableModel tableModel; // Modelo de la tabla
    private Banco banco; // Instancia de la clase Banco

    public ClienteConSaldoSuperior(ActionListener listener) {
        super(listener);
        banco = Banco.getInstancia(); 
        setBackground(Color.WHITE);
    }

    @Override
    protected void loadContent() {
        JLabel label = new JLabel("Clientes con al menos una cuenta con saldo superior al valor a ingresar");
        label.setFont(new Font("Tahoma", Font.BOLD, 24));
        label.setForeground(new Color(0, 128, 0));
        label.setBounds(550, 50, 1000, 30);
        add(label);

        JLabel ingresarMontoLabel = new JLabel("Ingrese el monto:");
        ingresarMontoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ingresarMontoLabel.setForeground(new Color(0, 128, 0)); 
        ingresarMontoLabel.setBounds(550, 100, 200, 30);
        add(ingresarMontoLabel);

        montoField = new JTextField();
        montoField.setBounds(550, 170, 200, 30);
        add(montoField);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(550, 220, 100, 30);
        buscarButton.setFont(new Font("Tahoma", Font.BOLD, 16)); // Tamaño de fuente aumentado
        buscarButton.setBackground(new Color(144, 238, 144)); // Color de fondo verde
        buscarButton.setForeground(Color.white); // Color de texto blanco
        add(buscarButton);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarClientesConSaldoSuperior();
            }
        });

        String[] columnNames = { "ID Cliente", "Nombre", "Dirección", "Teléfono", "Email" };
        tableModel = new DefaultTableModel(columnNames, 0);
        clientTable = new JTable(tableModel);
        styleTable(clientTable); // Aplicar estilo a la tabla
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setBounds(550, 300, 1000, 400);
        add(scrollPane);
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

    private void buscarClientesConSaldoSuperior() {
        try {
            double monto = Double.parseDouble(montoField.getText());
            ArrayList<Cliente> clientes = banco.clientesConCuentaSuperiorASaldoDado(monto);

            tableModel.setRowCount(0); // Limpiar la tabla antes de actualizarla

            if (clientes == null || clientes.isEmpty()) { // Verificar si la lista es null o está vacía
                JOptionPane.showMessageDialog(this, "No se encontraron clientes con saldo superior al monto ingresado.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return; // Salir del método si no hay clientes
            }

            for (Cliente cliente : clientes) {
                Object[] rowData = {
                    cliente.getIdCliente(), 
                    cliente.getNombre(), 
                    cliente.getDireccion(), 
                    cliente.getTelefono(), 
                    cliente.getEmail()
                };
                tableModel.addRow(rowData);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
