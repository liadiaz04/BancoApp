package GUI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
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
    private JButton loadButton; // Botón para cargar los cajeros

    public CajerosSinSaldo(ActionListener listener) {
        super(listener);
        banco = Banco.getInstancia(); 
        setBackground(Color.WHITE);
        loadContent(); // Cargar contenido de la pantalla
    }

    @Override
    protected void loadContent() {
        JLabel label = new JLabel("Cajeros con saldo insuficiente");
        label.setFont(new Font("Tahoma", Font.BOLD, 24)); 
        label.setForeground(new Color(0, 128, 0)); 
        label.setBounds(550, 50, 1200, 30);
        add(label);

        // Configuración de la tabla con nueva columna "Agencia"
        String[] columnNames = { "ID Cajero", "Saldo Total", "Agencia" };  // Nueva columna
        tableModel = new DefaultTableModel(columnNames, 0);
        cajeroTable = new JTable(tableModel);
        styleTable(cajeroTable);

        JScrollPane scrollPane = new JScrollPane(cajeroTable);
        scrollPane.setBounds(550, 170, 800, 500);
        add(scrollPane);

        // Botón para cargar cajeros sin saldo
        loadButton = new JButton("Mostrar cajeros sin saldo");
        loadButton.setBounds(650, 700, 300, 50); 
        loadButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        loadButton.setBackground(new Color(144, 238, 144)); 
        loadButton.setForeground(Color.WHITE);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatosCajeros(); // Cargar los datos de cajeros al presionar el botón
            }
        });
        
        add(loadButton);
        revalidate();
        repaint();
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
            // Obtener la agencia a la que pertenece el cajero
            String agencia = banco.obtenerAgenciaPorCajero(cajero.getIdCajero());
            
            Object[] rowData = { cajero.getIdCajero(), cajero.mostrarSaldoTotal(), agencia }; // Mostrar ID, saldo y agencia
            tableModel.addRow(rowData); // Agregar cada cajero a la tabla
        }

        tableModel.fireTableDataChanged(); // Notificar que los datos han cambiado
    }

}
