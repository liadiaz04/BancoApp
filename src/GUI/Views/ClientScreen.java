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
        String[] columns = new String[]{"Identificador", "Nombre", "Teléfono"};

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
        	    new Font("Arial", Font.PLAIN, 16), 
        	    Color.BLACK, 
        	    Color.LIGHT_GRAY, 
        	    Color.WHITE, 
        	    "Cuentas del Cliente"
        	);
        
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

        	        JLabel addressLabel = new JLabel("Dirección:");
        	        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 2;
        	        addClientDialog.add(addressLabel, gbc);

        	        final JTextField addressField = new JTextField(20);
        	        gbc.gridx = 1;
        	        gbc.gridy = 2;
        	        addClientDialog.add(addressField, gbc);

        	        JLabel phoneLabel = new JLabel("Teléfono:");
        	        phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        	        gbc.gridx = 0;
        	        gbc.gridy = 3;
        	        addClientDialog.add(phoneLabel, gbc);

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

        	                if (idCliente.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
        	                    JOptionPane.showMessageDialog(addClientDialog, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        	                    return;
        	                }

        	                // AGREGAR CLIENTE
        	                Banco.getInstancia().addCliente(idCliente, nombre, direccion, telefono, email);

        	                ((DefaultTableModel) clientTable.getModel()).addRow(new Object[]{idCliente, nombre, telefono});

        	                addClientDialog.dispose();
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
                        int response = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar al cliente " + clientId + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
                            "<strong>Dirección:</strong> " + clienteSeleccionado.getDireccion() + "<br/>" +
                            "<strong>Email:</strong> " + clienteSeleccionado.getEmail() + "</html>");
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
