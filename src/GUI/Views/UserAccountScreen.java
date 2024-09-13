package GUI.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Clases.C_Corriente;
import Clases.C_Formacion_Fondos;
import Clases.C_MLC;
import Clases.C_Plazo_Fijo;
import Clases.Cliente;
import Clases.CuentaBancaria;
import Clases.Banco;
import Clases.Operacion;
import Clases.InfoWindow;
import Clases.Plazo_Deposito;
import GUI.Components.BaseScreenWithSideMenu;
import GUI.Controllers.SelectedUserManager;
import GUI.Tables.AccountTable;
import GUI.Tables.ClientTable;

import java.time.LocalDate;
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

        JButton addAccount = new JButton("Agregar cuenta");
        customizeButton(addAccount, buttonFont, 1020, 800);
        add(addAccount);
        
        JButton detailsButton = new JButton("Detalles de la Cuenta");
        customizeButton(detailsButton, buttonFont, 1280, 800);
        add(detailsButton);
        
        JButton lastOperations = new JButton("�ltimas Operaciones");
        customizeButton(lastOperations, buttonFont, 1600, 700);
        add(lastOperations);
        
        
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarDeposito(); // M�todo que abre el di�logo para el dep�sito
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarExtraccion(); // M�todo que abre el di�logo para la extracci�n
            }
        });

        addAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCuenta();
            }
        });
        add(addAccount);

     
        
        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAccountDetails(); 
            }
        });
        
   
     // �LTIMAS OPERACIONES
        lastOperations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = accountTable.getSelectedRow();
                if (selectedRow != -1) {
                    CuentaBancaria cuentaSeleccionada = cuentas.get(selectedRow);
                    ArrayList<Operacion> operaciones = cuentaSeleccionada.ultimasDiezOperaciones();
                    mostrarOperaciones(operaciones);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una cuenta para ver las operaciones.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        
     // Crear JPanel para mostrar los detalles del cliente
        detailPanel = new JPanel();
        detailPanel.setLayout(new GridBagLayout());
        detailPanel.setBounds(1550, 200, 300, 400); // Mover hacia abajo y alargar
        detailPanel.setBackground(new Color(240, 255, 240));
        add(detailPanel);

        // Crear JLabel est�tico para "Detalles del Cliente:"
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
        gbc.gridy = 1; // Cambiar la posici�n a 1 para mover hacia abajo
        detailPanel.add(detailLabel, gbc);
        
        
        



        revalidate(); // Refresh the panel
        repaint(); // Repaint the panel
    }
    
    private void mostrarOperaciones(ArrayList<Operacion> operaciones) {
        if (operaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay operaciones para mostrar.", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder operacionesText = new StringBuilder("<html><h3>�ltimas Operaciones:</h3><ul>");
        for (Operacion operacion : operaciones) {
            operacionesText.append("<li>")
                .append("Tipo: ").append(operacion.getTipo()).append(", ")
                .append("Monto: ").append(operacion.getMonto()).append(", ")
                .append("Fecha: ").append(operacion.getFecha().toString())
                .append("</li>");
        }
        operacionesText.append("</ul></html>");

        JOptionPane.showMessageDialog(null, operacionesText.toString(), "Operaciones", JOptionPane.INFORMATION_MESSAGE);
    }


    private void updateAccountTable() {
        String name = cliente == null ? "" : cliente.getNombre();
        nameLabel.setText("Cuentas de " + name);

        if (cliente != null) {
            cuentas = getAccountList(cliente);
            String[] columns = new String[]{"N�mero de Cuenta", "Saldo", "Beneficiario", "Moneda", "Fecha de Apertura", "Tipo de Cuenta"}; // Nueva columna

            if (accountTable == null) {
                AccountTable accountTable = new AccountTable(cuentas, columns);
                this.accountTable = accountTable.getTable();
                accountTable.setBackground(new Color(144, 238, 144));
                accountTable.setBounds(480, 100, 1000, 600);
                add(accountTable);
            } else {
                // Actualizar el modelo de la tabla existente con nuevos datos
                DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
                model.setRowCount(0); // Limpiar las filas existentes
                for (CuentaBancaria cuenta : cuentas) {
                    model.addRow(new Object[]{
                        cuenta.getNoCuenta(),
                        cuenta.getSaldo(),
                        cuenta.getBeneficiario(),
                        cuenta.getMoneda(),
                        cuenta.getFechaApertura(),
                        cuenta.getClass().getSimpleName() // Mostrar el tipo de cuenta
                    });
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
            JLabel cuentaLabel = new JLabel("Dep�sito en cuenta:");
            JTextField cuentaTextField = new JTextField(cuentaSeleccionada.getNoCuenta());
            cuentaTextField.setEditable(false); // No permitir la edici�n del n�mero de cuenta
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
                        // Verificar si la cuenta es de tipo C_Formacion_Fondos
                        if (cuentaSeleccionada instanceof C_Formacion_Fondos) {
                            JOptionPane.showMessageDialog(null, "No se pueden realizar dep�sitos en cuentas de Formaci�n de Fondos.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Llamar al m�todo depositar seg�n el tipo de cuenta
                            if (cuentaSeleccionada instanceof C_Corriente) {
                                ((C_Corriente) cuentaSeleccionada).depositar(monto);
                            } else if (cuentaSeleccionada instanceof C_MLC) {
                                ((C_MLC) cuentaSeleccionada).depositar(monto);
                            } else if (cuentaSeleccionada instanceof C_Plazo_Fijo) {
                                ((C_Plazo_Fijo) cuentaSeleccionada).depositar(monto);
                            }

                            // Actualizar la tabla de cuentas
                            updateAccountTable();
                            JOptionPane.showMessageDialog(null, "Dep�sito realizado con �xito.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto v�lido.", "Error", JOptionPane.ERROR_MESSAGE);
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
            cuentaTextField.setEditable(false); // No permitir la edici�n del n�mero de cuenta
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
                        // Llamar al m�todo de extracci�n espec�fico seg�n el tipo de cuenta
                        if (cuentaSeleccionada instanceof C_Corriente) {
                            ((C_Corriente) cuentaSeleccionada).extraer(monto);
                        } else if (cuentaSeleccionada instanceof C_MLC) {
                            // Asumiendo que C_MLC no permite extracci�n, puedes manejarlo aqu� si es necesario
                            JOptionPane.showMessageDialog(null, "Las cuentas MLC no permiten extracciones.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        } else if (cuentaSeleccionada instanceof C_Plazo_Fijo) {
                            ((C_Plazo_Fijo) cuentaSeleccionada).extraer(monto);
                        } else if (cuentaSeleccionada instanceof C_Formacion_Fondos) {
                            ((C_Formacion_Fondos) cuentaSeleccionada).extraer(monto);
                        }

                        // Actualizar la tabla de cuentas
                        updateAccountTable();
                        JOptionPane.showMessageDialog(null, "Extracci�n realizada con �xito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto v�lido.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    
    private void agregarCuenta() {
        String[] tiposCuentas = {"Corriente", "Formacion de Fondos", "MLC", "Plazo Fijo"};
        String tipoSeleccionado = (String) JOptionPane.showInputDialog(
            null, 
            "Seleccione el tipo de cuenta a agregar:",
            "Agregar Cuenta",
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            tiposCuentas, 
            tiposCuentas[0]
        );

        if (tipoSeleccionado != null) {
            abrirFormularioCuenta(tipoSeleccionado);
        }
    }

    private void abrirFormularioCuenta(String tipoSeleccionado){

    	JPanel panelCuenta = crearPanelCuenta(tipoSeleccionado);

    	int result = JOptionPane.showConfirmDialog(null, panelCuenta, "Agregar " + tipoSeleccionado, JOptionPane.OK_CANCEL_OPTION);

    	if (result == JOptionPane.OK_OPTION) {
    		procesarDatosIngreso(panelCuenta, tipoSeleccionado);
    	}
    	updateAccountTable();
    }	 

    private JPanel crearPanelCuenta(String tipoSeleccionado){

    	//CAMPOS GENERALES
    	JPanel panelCuenta = new JPanel(new GridLayout(0, 2));
    	JTextField beneficiarioField = new JTextField();
    	JLabel monedaLabel = new JLabel();

    	panelCuenta.add(new JLabel("Beneficiario:"));
    	panelCuenta.add(beneficiarioField);
    	panelCuenta.add(new JLabel("Moneda:"));
    	panelCuenta.add(monedaLabel);

    	//ESPECIFICACION DE MONEDA 
    	if (tipoSeleccionado.equals("Cuenta MLC")) {
    		monedaLabel.setText("MLC");
    	}else {
    		monedaLabel.setText("CUP");
    	}

    	// CAMPOS ADICIONALES SEGUN EL TIPO DE CUENTA
    	switch (tipoSeleccionado) {
    	case "Formacion de Fondos":
    		JTextField idContratoField = new JTextField();
    		panelCuenta.add(new JLabel("C�digo del Contrato:"));
    		panelCuenta.add(idContratoField);
    		break;
    	case "Plazo Fijo":
    		JTextField plazoField = new JTextField();
    		JTextField cantInicialField = new JTextField();
    		panelCuenta.add(new JLabel("Plazo:"));
    		panelCuenta.add(plazoField);
    		panelCuenta.add(new JLabel("Cantidad Inicial:"));
    		panelCuenta.add(cantInicialField);
    		break;
    	}
    	return panelCuenta;
    }

    private void procesarDatosIngreso(JPanel panelCuenta,String tipoSeleccionado ){
    	String beneficiario = ((JTextField) panelCuenta.getComponent(1)).getText();
    	
    	
    	switch (tipoSeleccionado) {
    	case "Formacion de Fondos":
    		String idContrato = ((JTextField) panelCuenta.getComponent(5)).getText();
    		try{
    			//VALIDACIONES DE LOS CAMPOS
    			Banco.getInstancia().agregarCuentaFormacionFondos(cliente, beneficiario, idContrato, tipoSeleccionado);
    		}catch(Exception e){
    			//MANEJAR ERROR 
    		}
    		break;
    	case "Plazo Fijo":
    		double cantInicial = Double.parseDouble(((JTextField)panelCuenta.getComponent(7)).getText());
    		int plazo= Integer.parseInt(((JTextField)panelCuenta.getComponent(5)).getText());
    		try{
    			//VALIDACIONES DE LOS CAMPOS
    			Banco.getInstancia().agregarCuentaPlazoFijo(cliente, beneficiario, cantInicial, plazo, tipoSeleccionado);
    		}catch(Exception e){
    			//MANEJAR ERROR 
    		}
    		break;
    	case "Corriente":
    		try{
    			//VALIDACIONES DE LOS CAMPOS
    			Banco.getInstancia().agregarCuentaCorriente(cliente, beneficiario,tipoSeleccionado);
    		}catch(Exception e){
    			//MANEJAR ERROR 
    		}
    		break;
    	case "MLC":
    		try{
    			//VALIDACIONES DE LOS CAMPOS
    			Banco.getInstancia().agregarCuentaMLC(cliente, beneficiario,tipoSeleccionado);
    		}catch(Exception e){
    			//MANEJAR ERROR 
    		}
    		break;
    	 }
     }
    
   
    
    private ArrayList<CuentaBancaria> getAccountList(Cliente cliente) {
        Banco banco = Banco.getInstancia();
        ArrayList<CuentaBancaria> cuentas = banco.getCuentasCliente(cliente);
        return cuentas;
    }
}
