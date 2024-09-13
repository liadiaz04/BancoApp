package GUI.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import GUI.Components.BaseScreenWithSideMenu;
import GUI.Controllers.SelectedUserManager;
import GUI.Tables.AccountTable;
import GUI.Tables.ClientTable;
import Logic.Banco;
import Logic.C_Corriente;
import Logic.C_Formacion_Fondos;
import Logic.C_MLC;
import Logic.C_Plazo_Fijo;
import Logic.Cliente;
import Logic.CuentaBancaria;
import Logic.InfoWindow;
import Logic.Operacion;
import Logic.Plazo_Deposito;

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
        loadContent(); 
    }


    public void refreshContent() {
        this.cliente = SelectedUserManager.getInstancia().getClienteSeleccionado();
        updateAccountTable(); 
    }

    @Override
    protected void loadContent() {
    	
    	nameLabel = new JLabel();
    	nameLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
    	nameLabel.setForeground(new Color(0, 128, 0));
    	nameLabel.setBounds(550, 50, 400, 30);
        add(nameLabel);


        updateAccountTable();
        

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
        
        JButton lastOperations = new JButton("Últimas Operaciones");
        customizeButton(lastOperations, buttonFont, 1600, 700);
        add(lastOperations);
        
        
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarDeposito(); 
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarExtraccion(); 
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
        
   
     // ï¿½LTIMAS OPERACIONES
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

        
     // DETALLES DE LA CUENTA
        detailPanel = new JPanel();
        detailPanel.setLayout(new GridBagLayout());
        detailPanel.setBounds(1550, 200, 300, 400); 
        detailPanel.setBackground(new Color(240, 255, 240));
        add(detailPanel);

        detailLabel = new JLabel("Detalles de la Cuenta:");
        detailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailLabel.setForeground(new Color(0, 128, 0));
        GridBagConstraints gbcStatic = new GridBagConstraints();
        gbcStatic.insets = new Insets(5, 5, 5, 5);
        gbcStatic.gridx = 0;
        gbcStatic.gridy = 0;
        detailPanel.add(detailLabel, gbcStatic);


        detailLabel = new JLabel();
        detailLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        detailLabel.setForeground(new Color(0, 128, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1; 
        detailPanel.add(detailLabel, gbc);
        

        revalidate(); 
        repaint(); 
    }
    
    private void mostrarOperaciones(ArrayList<Operacion> operaciones) {
        if (operaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay operaciones para mostrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder operacionesText = new StringBuilder("<html><h3>Últimas Operaciones:</h3><ul>");
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
            String[] columns = new String[]{"Número de Cuenta", "Saldo", "Beneficiario","Tipo de Cuenta"}; 

            if (accountTable == null) {
                AccountTable accountTable = new AccountTable(cuentas, columns);
                this.accountTable = accountTable.getTable();
                accountTable.setBackground(new Color(144, 238, 144));
                accountTable.setBounds(480, 100, 1000, 600);
                add(accountTable);
            } else {
                DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
                model.setRowCount(0); 
                for (CuentaBancaria cuenta : cuentas) {
                    model.addRow(new Object[]{
                        cuenta.getNoCuenta(),
                        cuenta.getSaldo(),
                        cuenta.getBeneficiario(),
                        cuenta.getClass().getSimpleName() 
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

            JPanel panelDeposito = new JPanel(new GridLayout(2, 2));
            JLabel cuentaLabel = new JLabel("Depósito en cuenta:");
            JTextField cuentaTextField = new JTextField(cuentaSeleccionada.getNoCuenta());
            cuentaTextField.setEditable(false); 
            JLabel montoLabel = new JLabel("Monto a depositar:");
            JTextField montoTextField = new JTextField();

            panelDeposito.add(cuentaLabel);
            panelDeposito.add(cuentaTextField);
            panelDeposito.add(montoLabel);
            panelDeposito.add(montoTextField);

            int result = JOptionPane.showConfirmDialog(null, panelDeposito, "Depositar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double monto = Double.parseDouble(montoTextField.getText());

                    if (monto > 0) {
                        if (cuentaSeleccionada instanceof C_Formacion_Fondos) {
                            JOptionPane.showMessageDialog(null, "No se pueden realizar depósitos en cuentas de Formación de Fondos.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (cuentaSeleccionada instanceof C_Corriente) {
                                ((C_Corriente) cuentaSeleccionada).depositar(monto);
                            } else if (cuentaSeleccionada instanceof C_MLC) {
                                ((C_MLC) cuentaSeleccionada).depositar(monto);
                            } else if (cuentaSeleccionada instanceof C_Plazo_Fijo) {
                                ((C_Plazo_Fijo) cuentaSeleccionada).depositar(monto);
                            }

                           
                            updateAccountTable();
                            JOptionPane.showMessageDialog(null, "Depósito realizado con éxito.");
                        }
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


            JPanel panelExtraccion = new JPanel(new GridLayout(2, 2));
            JLabel cuentaLabel = new JLabel("Extraer de cuenta:");
            JTextField cuentaTextField = new JTextField(cuentaSeleccionada.getNoCuenta());
            cuentaTextField.setEditable(false); 
            JLabel montoLabel = new JLabel("Monto a extraer:");
            JTextField montoTextField = new JTextField();

            panelExtraccion.add(cuentaLabel);
            panelExtraccion.add(cuentaTextField);
            panelExtraccion.add(montoLabel);
            panelExtraccion.add(montoTextField);


            int result = JOptionPane.showConfirmDialog(null, panelExtraccion, "Extraer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double monto = Double.parseDouble(montoTextField.getText());

                    if (monto > 0) {
                        if (cuentaSeleccionada instanceof C_Corriente) {
                            ((C_Corriente) cuentaSeleccionada).extraer(monto);
                        } else if (cuentaSeleccionada instanceof C_MLC) {
                            JOptionPane.showMessageDialog(null, "Las cuentas MLC no permiten extracciones.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        } else if (cuentaSeleccionada instanceof C_Plazo_Fijo) {
                            ((C_Plazo_Fijo) cuentaSeleccionada).extraer(monto);
                        } else if (cuentaSeleccionada instanceof C_Formacion_Fondos) {
                            ((C_Formacion_Fondos) cuentaSeleccionada).extraer(monto);
                        }


                        updateAccountTable();
                        JOptionPane.showMessageDialog(null, "Extracción realizada con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
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
            
            // FORMATEAR LA FECHA
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
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
    		panelCuenta.add(new JLabel("Cï¿½digo del Contrato:"));
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
