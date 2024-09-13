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
import GUI.Tables.AccountTable;
import Logic.Banco;
import Logic.CuentaBancaria;

public class CuentasFFondosAsociadas extends BaseScreenWithSideMenu {

    private JTextField entidadField;  // Campo para ingresar la entidad
    private JTable accountTable;  // Tabla para mostrar las cuentas
    private DefaultTableModel tableModel;  // Modelo de la tabla
    private Banco banco;  // Instancia del banco

    public CuentasFFondosAsociadas(ActionListener listener) {
        super(listener);
        banco = Banco.getInstancia();  // Obtener la instancia del banco
        setBackground(Color.WHITE);
    }

    @Override
    protected void loadContent() {
        // Título de la pantalla
        JLabel label = new JLabel("Cuentas de Formación de Fondos Asociadas a una Entidad");
        label.setFont(new Font("Tahoma", Font.BOLD, 24));
        label.setForeground(new Color(0, 128, 0));
        label.setBounds(550, 50, 1000, 30);
        add(label);

        // Etiqueta para el campo de entrada
        JLabel ingresarEntidadLabel = new JLabel("Ingrese la entidad:");
        ingresarEntidadLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ingresarEntidadLabel.setForeground(new Color(0, 128, 0));
        ingresarEntidadLabel.setBounds(550, 100, 200, 30);
        add(ingresarEntidadLabel);

        // Campo de texto para ingresar la entidad
        entidadField = new JTextField();
        entidadField.setBounds(550, 150, 200, 30);
        add(entidadField);

        // Botón de búsqueda
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(550, 200, 100, 30);
        buscarButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        buscarButton.setBackground(new Color(144, 238, 144));  // Verde claro
        buscarButton.setForeground(Color.white);  // Texto blanco
        add(buscarButton);

        // Agregar acción al botón de búsqueda
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCuentasPorEntidad();
            }
        });

        // Crear la tabla con sus columnas
        String[] columnNames = { "No. Cuenta", "Saldo", "Beneficiario", "Moneda", "Fecha Apertura", "Tipo" };
        tableModel = new DefaultTableModel(columnNames, 0);
        accountTable = new JTable(tableModel);
        styleTable(accountTable);  // Aplicar estilo a la tabla
        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setBounds(550, 300, 1000, 400);
        add(scrollPane);
    }

    // Estilo de la tabla
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

    // Buscar las cuentas de formación de fondos por entidad
    private void buscarCuentasPorEntidad() {
        String entidad = entidadField.getText();

        // Validar que el campo de la entidad no esté vacío
        if (entidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una entidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener las cuentas de formación de fondos asociadas a la entidad
        ArrayList<CuentaBancaria> cuentas = banco.cuentasFormacionFondosDeEntidadDada(entidad);

        // Limpiar la tabla antes de actualizarla
        tableModel.setRowCount(0);

        // Verificar si no hay cuentas
        if (cuentas == null || cuentas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron cuentas de formación de fondos para la entidad ingresada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Llenar la tabla con los datos de las cuentas
        for (CuentaBancaria cuenta : cuentas) {
            Object[] rowData = {
                cuenta.getNoCuenta(),
                String.valueOf(cuenta.getSaldo()),
                cuenta.getBeneficiario(),
                cuenta.getMoneda(),
                String.valueOf(cuenta.getFechaApertura()),
                cuenta.getClass().getSimpleName()
            };
            tableModel.addRow(rowData);
        }
    }
}
