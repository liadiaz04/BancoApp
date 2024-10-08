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

import GUI.Components.BaseScreenWithSideMenu;
import GUI.Components.NavigationButton;
import GUI.Controllers.SelectedUserManager;
import GUI.Tables.ClientTable;
import Logic.Banco;
import Logic.C_Corriente;
import Logic.C_Formacion_Fondos;
import Logic.C_MLC;
import Logic.Cliente;
import Logic.CuentaBancaria;
import Logic.Validaciones;

public class ClientScreen extends BaseScreenWithSideMenu {
    private JTable clientTable;
    private JLabel detailLabel;
    private JLabel staticDetailLabel; 
    private ArrayList<Cliente> clientes;
    private JPanel detailPanel; 

    //CAMPOS DE TEXTO
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
        label.setFont(new Font("Tahoma", Font.BOLD, 24)); 
        label.setForeground(new Color(0, 128, 0)); 
        label.setBounds(550, 50, 200, 30);
        add(label);

        clientes = getClientList();
        String[] columns = new String[]{"Identificador", "Nombre", "Direcci�n"};

        Font buttonFont = new Font("Tahoma", Font.BOLD, 16);
        
        ClientTable clientTableComponent = new ClientTable(clientes, columns);
        this.clientTable = clientTableComponent.getTable();
        clientTableComponent.setBackground(new Color(144, 238, 144));
        clientTableComponent.setBounds(480, 100, 1000, 600);
        add(clientTableComponent);

        // BOTONES
        JButton addClient = new JButton("Agregar");
        customizeButton(addClient, buttonFont, 500, 800);
        add(addClient);

        JButton deleteClient = new JButton("Eliminar");
        customizeButton(deleteClient, buttonFont, 760, 800);
        add(deleteClient);
        
        NavigationButton clientButton = new NavigationButton(
        	    "Cuentas del Cliente", 
        	    null, 
        	    buttonFont,  
        	    new Color(119, 221, 119),  
        	    new Color(144, 238, 144),  
        	    Color.WHITE,  
        	    "Cuentas del Cliente"
        	);
        	customizeButton(clientButton, buttonFont, 1020, 800);
        	add(clientButton);

        
        JButton details = new JButton("Ver Detalles");
        customizeButton(details, buttonFont, 1280, 800);
        add(details);

        	
        	clientButton.addActionListener(listener);
        	customizeButton(clientButton, buttonFont, 1020, 800);
        	add(clientButton);

        	//CUENTAS DEL CLIENTE
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

        	    	
        	//AGREGAR CLIENTE 
        	addClient.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {
        	        final JDialog addClientDialog = new JDialog();
        	        addClientDialog.setTitle("Agregar Cliente");
        	        addClientDialog.setSize(400, 400);
        	        addClientDialog.setLayout(new GridBagLayout());
        	        addClientDialog.getContentPane().setBackground(new Color(240, 255, 240));

        	        GridBagConstraints gbc = new GridBagConstraints();
        	        gbc.insets = new Insets(5, 5, 5, 5);

        	        //ETIQUETAS
        	        JLabel idLabel = new JLabel("ID Cliente:");
        	        idLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 0;
        	        addClientDialog.add(idLabel, gbc);

        	        final JTextField idField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 0;
        	        addClientDialog.add(idField, gbc);

        	        JLabel nameLabel = new JLabel("Nombre:");
        	        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 1;
        	        addClientDialog.add(nameLabel, gbc);

        	        final JTextField nameField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 1;
        	        addClientDialog.add(nameField, gbc);


        	        JLabel phoneLabel = new JLabel("Tel�fono:");
        	        phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 3;
        	        addClientDialog.add(phoneLabel, gbc);
        	        
        	        JLabel addressLabel = new JLabel("Direcci�n:");
        	        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 2;
        	        addClientDialog.add(addressLabel, gbc);

        	        final JTextField addressField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 2;
        	        addClientDialog.add(addressField, gbc);

        	        final JTextField phoneField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 3;
        	        addClientDialog.add(phoneField, gbc);

        	        JLabel emailLabel = new JLabel("Email:");
        	        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 4;
        	        addClientDialog.add(emailLabel, gbc);

        	        final JTextField emailField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 4;
        	        addClientDialog.add(emailField, gbc);

        	        //BOTON GUARDAR
        	        JButton saveButton = new JButton("Guardar");
        	        saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        	        saveButton.setBackground(new Color(119, 221, 119));
        	        saveButton.setForeground(Color.WHITE);
        	        gbc.gridx = 1;
        	        gbc.gridy = 5;
        	        addClientDialog.add(saveButton, gbc);

        	        saveButton.addActionListener(new ActionListener() {
        	            public void actionPerformed(ActionEvent e) {
        	                String idCliente = idField.getText();
        	                String nombre = nameField.getText();
        	                String direccion = addressField.getText();
        	                String telefono = phoneField.getText();
        	                String email = emailField.getText();

        	                String validacionError = validarCampos(idCliente, nombre, direccion, telefono, email);
        	                if (validacionError != null) {
        	                    JOptionPane.showMessageDialog(addClientDialog, validacionError, "Error de Validaci�n", JOptionPane.ERROR_MESSAGE);
        	                    return;
        	                }

        	                try {
        	                    Validaciones.validarCI(idCliente);
        	                } catch (IllegalArgumentException ex) {
        	                    JOptionPane.showMessageDialog(addClientDialog, ex.getMessage(), "Error de Validaci�n", JOptionPane.ERROR_MESSAGE);
        	                    return; 
        	                }

        	                for (Cliente cliente : clientes) {
        	                    if (cliente.getIdCliente().equals(idCliente)) {
        	                        JOptionPane.showMessageDialog(addClientDialog, "Ya existe un cliente con este carnet de identidad.", "Error", JOptionPane.ERROR_MESSAGE);
        	                        return; 
        	                    }
        	                }

        	                // AGREGAR CLIENTE
        	                Banco.getInstancia().addCliente(idCliente, nombre, direccion, telefono, email);
        	                ((DefaultTableModel) clientTable.getModel()).addRow(new Object[]{idCliente, nombre, telefono});
        	                addClientDialog.dispose();
        	            }

        	            private String validarCampos(String idCliente, String nombre, String direccion, String telefono, String email) {
        	                if (nombre.isEmpty() || !validarNombre(nombre)) {
        	                    return "El nombre solo debe contener letras y no estar vac�o.";
        	                }

        	                if (idCliente.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
        	                    return "Por favor, complete todos los campos.";
        	                }

        	                if (!Validaciones.validadorCorreo(email)) {
        	                    return "El email no es v�lido.";
        	                }

        	                if (!Validaciones.validarTelefono(telefono)) {
        	                    return "El tel�fono debe contener exactamente 8 d�gitos num�ricos.";
        	                }

        	                return null; // No hay errores
        	            }
        	        });


        	        addClientDialog.setVisible(true);
        	    }
        	});
        	
        	//ELIMINAR CLIENTE
        	deleteClient.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {
        	        int selectedRow = clientTable.getSelectedRow();
        	        if (selectedRow != -1) {
        	            String clientId = (String) clientTable.getValueAt(selectedRow, 0); 
        	            System.out.println("Intentando eliminar cliente con ID: " + clientId); // Debug
        	            int response = JOptionPane.showConfirmDialog(null, "�Est�s seguro de que deseas eliminar al cliente " + clientId + "?", "Confirmar eliminaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        	            if (response == JOptionPane.YES_OPTION) {
        	                boolean deleted = Banco.getInstancia().eliminarCliente(clientId);
        	                if (deleted) {
        	                    ((DefaultTableModel) clientTable.getModel()).removeRow(selectedRow); 
        	                } else {
        	                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        	                }
        	            }
        	        } else {
        	            JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        	        }
        	    }
        	});
        	
        	
        	

         // VER DETALLES
        	details.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {
        	        int selectedRow = clientTable.getSelectedRow();
        	        if (selectedRow != -1) {
        	            Cliente clienteSeleccionado = clientes.get(selectedRow);

        	            detailLabel.setText("<html><strong>Nombre:</strong> " + clienteSeleccionado.getNombre() + "<br/>" +
        	                "<strong>Direcci�n:</strong> " + clienteSeleccionado.getDireccion() + "<br/>" +
        	                "<strong>Email:</strong> " + clienteSeleccionado.getEmail() + "<br/>" +
        	                "<strong>Tel�fono:</strong> " + clienteSeleccionado.getTelefono() + "</html>");
        	        } else {
        	            JOptionPane.showMessageDialog(null, 
        	                "Por favor, seleccione un cliente para ver los detalles."
        	            );
        	        }
        	    }
        	});


        // PANEL DETALLES
        detailPanel = new JPanel();
        detailPanel.setLayout(new GridBagLayout());
        detailPanel.setBounds(1550, 200, 300, 400); 
        detailPanel.setBackground(new Color(240, 255, 240));
        add(detailPanel);

        // DETALLES JLABEL
        staticDetailLabel = new JLabel("Detalles del Cliente:");
        staticDetailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        staticDetailLabel.setForeground(new Color(0, 128, 0));
        GridBagConstraints gbcStatic = new GridBagConstraints();
        gbcStatic.insets = new Insets(5, 5, 5, 5);
        gbcStatic.gridx = 0;
        gbcStatic.gridy = 0;
        detailPanel.add(staticDetailLabel, gbcStatic);

        //JLABEL DETALLES
        detailLabel = new JLabel();
        detailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailLabel.setForeground(new Color(0, 128, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1; 
        detailPanel.add(detailLabel, gbc);


    }
    
    private boolean validarNombre(String nombre) {
        String regex = "^[a-zA-Z������������]+( [a-zA-Z������������]+)*$";
        return nombre.matches(regex);
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
