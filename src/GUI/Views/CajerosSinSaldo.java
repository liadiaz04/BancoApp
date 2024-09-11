package GUI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Clases.Banco;
import Clases.Cajero;
import GUI.Components.BaseScreenWithSideMenu;

public class CajerosSinSaldo extends BaseScreenWithSideMenu {
    private JTable cajeroTable; // Tabla para mostrar los resultados
    private DefaultTableModel tableModel; // Modelo de la tabla
    private Banco banco; 

    public CajerosSinSaldo(ActionListener listener) {
        super(listener);
        banco = Banco.getInstancia(); 
        setBackground(Color.WHITE);
        loadContent(); 
        cargarDatosCajeros(); 
    }

    @Override
    protected void loadContent() {
        JLabel label = new JLabel("Cajeros con saldo insuficiente");
        label.setFont(new Font("Tahoma", Font.BOLD, 24)); 
        label.setForeground(new Color(0, 128, 0)); 
        label.setBounds(550, 50, 1200, 30);
        add(label);

        // Configuración de la tabla
        String[] columnNames = { "ID Cajero", "Saldo Total" };
        tableModel = new DefaultTableModel(columnNames, 0);
        cajeroTable = new JTable(tableModel);
        styleTable(cajeroTable);

        JScrollPane scrollPane = new JScrollPane(cajeroTable);
        scrollPane.setBounds(550, 170, 800, 500);
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

    // Método para cargar los cajeros con saldo insuficiente y mostrarlos en la tabla
    private void cargarDatosCajeros() {
        ArrayList<Cajero> cajerosSinSaldo = banco.cajerosConSaldoInsuficiente(); // Obtener cajeros con saldo insuficiente
        tableModel.setRowCount(0); // Limpiar la tabla

        for (Cajero cajero : cajerosSinSaldo) {
            Object[] rowData = { cajero.getIdCajero(), cajero.mostrarSaldoTotal() }; // Mostrar el ID y saldo
            tableModel.addRow(rowData); // Agregar cada cajero a la tabla
        }
    }
}
