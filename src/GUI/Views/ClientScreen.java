package GUI.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import Clases.Banco;
import Clases.C_Corriente;
import Clases.C_Formacion_Fondos;
import Clases.C_MLC;
import Clases.Cliente;
import Clases.CuentaBancaria;
import GUI.Components.BaseScreenWithSideMenu;
import GUI.Components.NavigationButton;
import GUI.Controllers.SelectedUserManager;
import GUI.Tables.ClientTable;

public class ClientScreen extends BaseScreenWithSideMenu {
    private JTable clientTable;
    private JLabel detailLabel;
    private JLabel staticDetailLabel; // Nueva etiqueta para "Detalles del Cliente:"
    private ArrayList<Cliente> clientes;
    private JPanel detailPanel; // Panel para los detalles del cliente

    // Declarar los campos de texto como variables de instancia
    private JTextField idField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField phoneField;

    public ClientScreen(ActionListener listener) {
        super(listener);
        setBackground(Color.WHITE);
    }

    @Override
    protected void loadContent() {
    	
        JLabel label = new JLabel("Clientes");
        label.setFont(new Font("Tahoma", Font.BOLD, 24)); // Cambiar el tamaño de la fuente
        label.setForeground(new Color(0, 128, 0)); // Cambiar el color de la fuente
        label.setBounds(550, 50, 200, 30);
        add(label);

        clientes = getClientList();
        String[] columns = new String[]{"Identificador", "Nombre", "Teléfono"};

        Font buttonFont = new Font("Tahoma", Font.BOLD, 16);
        
        ClientTable clientTableComponent = new ClientTable(clientes, columns);
        this.clientTable = clientTableComponent.getTable();
        clientTableComponent.setBackground(new Color(144, 238, 144));
        clientTableComponent.setBounds(480, 100, 1000, 600);
        add(clientTableComponent);

        // Crear botones
        JButton addClient = new JButton("Agregar");
        customizeButton(addClient, buttonFont, 500, 800);
        add(addClient);

        JButton deleteClient = new JButton("Eliminar");
        customizeButton(deleteClient, buttonFont, 760, 800);
        add(deleteClient);
        
        NavigationButton clientButton = new NavigationButton(
        	    "Cuentas del Cliente", 
        	    null, 
        	    new Font("Arial", Font.PLAIN, 16), 
        	    Color.BLACK, 
        	    Color.LIGHT_GRAY, 
        	    Color.WHITE, 
        	    "Cuentas del Cliente"
        	);

        	clientButton.addActionListener(listener);
        	customizeButton(clientButton, buttonFont, 1020, 800);
        	add(clientButton);

        	// Verificar si hay un cliente seleccionado
        	clientButton.addActionListener(new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	    	int selectedRow = clientTable.getSelectedRow();
        	    	
                    if (selectedRow != -1){
                    	Cliente clienteSeleccionado = clientes.get(selectedRow);
                    	SelectedUserManager.getInstancia().setClienteSeleccionado(clienteSeleccionado);
                    	System.out.println(clienteSeleccionado.getNombre());
                    }
                    else {
        	            JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente antes de continuar.");
                    }
        	    }
        	});

        	addClient.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {
        	        final JDialog addClientDialog = new JDialog();
        	        addClientDialog.setTitle("Agregar Cliente");
        	        addClientDialog.setSize(400, 400);
        	        addClientDialog.setLayout(new GridBagLayout());
        	        addClientDialog.getContentPane().setBackground(new Color(240, 255, 240));

        	        GridBagConstraints gbc = new GridBagConstraints();
        	        gbc.insets = new Insets(5, 5, 5, 5);

        	        // ID Cliente
        	        JLabel idLabel = new JLabel("ID Cliente:");
        	        idLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 0;
        	        addClientDialog.add(idLabel, gbc);

        	        final JTextField idField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 0;
        	        addClientDialog.add(idField, gbc);

        	        // Nombre
        	        JLabel nameLabel = new JLabel("Nombre:");
        	        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 1;
        	        addClientDialog.add(nameLabel, gbc);

        	        final JTextField nameField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 1;
        	        addClientDialog.add(nameField, gbc);

        	        // Dirección
        	        JLabel addressLabel = new JLabel("Dirección:");
        	        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 2;
        	        addClientDialog.add(addressLabel, gbc);

        	        final JTextField addressField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 2;
        	        addClientDialog.add(addressField, gbc);

        	        // Teléfono
        	        JLabel phoneLabel = new JLabel("Teléfono:");
        	        phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 3;
        	        addClientDialog.add(phoneLabel, gbc);

        	        final JTextField phoneField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 3;
        	        addClientDialog.add(phoneField, gbc);

        	        // Email
        	        JLabel emailLabel = new JLabel("Email:");
        	        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 4;
        	        addClientDialog.add(emailLabel, gbc);

        	        final JTextField emailField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 4;
        	        addClientDialog.add(emailField, gbc);

        	        // Botón Guardar
        	        JButton saveButton = new JButton("Guardar");
        	        saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        	        saveButton.setBackground(new Color(119, 221, 119));
        	        saveButton.setForeground(Color.WHITE);
        	        saveButton.setPreferredSize(new Dimension(150, 40));
        	        gbc.gridx = 1;
        	        gbc.gridy = 5;
        	        addClientDialog.add(saveButton, gbc);

        	        // Acción del botón Guardar
        	        saveButton.addActionListener(new ActionListener() {
        	            public void actionPerformed(ActionEvent e) {
        	                try {
        	                    String idCliente = idField.getText();
        	                    String nombre = nameField.getText();
        	                    String direccion = addressField.getText();
        	                    String telefono = phoneField.getText();
        	                    String email = emailField.getText();

        	                    // Validar que no haya campos vacíos
        	                    if (idCliente.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
        	                        JOptionPane.showMessageDialog(addClientDialog, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        	                        return;
        	                    }

        	                    // Crear nuevo cliente y agregarlo a la lista
        	                    Cliente nuevoCliente = new Cliente(idCliente, nombre, direccion, telefono, email);
        	                    clientes.add(nuevoCliente);
        	                    Banco.getInstancia().addCliente(idCliente, nombre, direccion, telefono, email);
        	                    
        	                    
        	                    
        	                    // Actualizar la tabla
        	                    ((DefaultTableModel) clientTable.getModel()).addRow(new Object[]{idCliente, nombre, telefono});

        	                    // Cerrar el diálogo
        	                    addClientDialog.dispose();
        	                } catch (Exception ex) {
        	                    JOptionPane.showMessageDialog(addClientDialog, "Error al agregar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        	                }
        	            }
        	        });

        	        addClientDialog.setVisible(true);
        	    }
        	});

        	


        JButton details = new JButton("Ver Detalles");
        customizeButton(details, buttonFont, 1280, 800);
        add(details);

        // Crear JPanel para mostrar los detalles del cliente
        detailPanel = new JPanel();
        detailPanel.setLayout(new GridBagLayout());
        detailPanel.setBounds(1550, 200, 300, 400); // Mover hacia abajo y alargar
        detailPanel.setBackground(new Color(240, 255, 240));
        add(detailPanel);

        // Crear JLabel estático para "Detalles del Cliente:"
        staticDetailLabel = new JLabel("Detalles del Cliente:");
        staticDetailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        staticDetailLabel.setForeground(new Color(0, 128, 0));
        GridBagConstraints gbcStatic = new GridBagConstraints();
        gbcStatic.insets = new Insets(5, 5, 5, 5);
        gbcStatic.gridx = 0;
        gbcStatic.gridy = 0;
        detailPanel.add(staticDetailLabel, gbcStatic);

        // Crear JLabel para mostrar los detalles
        detailLabel = new JLabel();
        detailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailLabel.setForeground(new Color(0, 128, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1; // Cambiar la posición a 1 para mover hacia abajo
        detailPanel.add(detailLabel, gbc);

     // Acción para el botón "Ver Detalles"
        details.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientTable.getSelectedRow();
                if (selectedRow != -1) {
                    Cliente clienteSeleccionado = clientes.get(selectedRow);
                    detailLabel.setText("<html><strong>Nombre:</strong> " + clienteSeleccionado.getNombre() + "<br/>" +
                        "<strong>Dirección:</strong> " + clienteSeleccionado.getDireccion() + "<br/>" +
                        "<strong>Email:</strong> " + clienteSeleccionado.getEmail() + "</html>");
                } else {
                    // Mostrar mensaje de advertencia
                    JOptionPane.showMessageDialog(null, 
                        "Por favor, seleccione un cliente para ver los detalles."
                        );
                }
            }
        });

        

     // Acción para el botón "Eliminar"
        deleteClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, 
                        "¿Está seguro de que desea eliminar este cliente?", 
                        "Confirmar Eliminación", 
                        JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Eliminar cliente
                        clientes.remove(selectedRow);
                        ((DefaultTableModel) clientTable.getModel()).removeRow(selectedRow);
                        detailLabel.setText("<html><i>Seleccione un cliente para ver los detalles.</i></html>");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente para eliminar.");
                }
            }
        });  

        
    }
    
    private void customizeButton(JButton button, Font font, int x, int y) {
        button.setBounds(x, y, 185, 53);
        button.setBackground(new Color(119, 221, 119));
        button.setForeground(Color.WHITE);
        button.setFont(font);
    }

    private ArrayList<Cliente> getClientList() {
        Banco banco = Banco.getInstancia();
        return banco.getClientes();
    }

}
