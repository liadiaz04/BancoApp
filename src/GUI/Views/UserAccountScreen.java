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

    public UserAccountScreen(ActionListener listener) {
        super(listener);
        this.cliente = SelectedUserManager.getInstancia().getClienteSeleccionado();

    }


    private ArrayList<CuentaBancaria> getCuentasDelCliente(Cliente cliente) {
        Banco banco = Banco.getInstancia();
        return banco.getCuentasCliente(cliente);
    }
    
    private void reloadUser() {
    	this.cliente = SelectedUserManager.getInstancia().getClienteSeleccionado();
    }

	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Cuentas del Cliente");
        label.setFont(new Font("Tahoma", Font.BOLD, 24));
        label.setForeground(new Color(0, 128, 0));
        label.setBounds(550, 50, 300, 30);
        add(label);
        
        System.out.println("Cliente: " + cliente);
        if (cliente != null) {
        	
        	setBackground(Color.WHITE);
            Cliente clienteSeleccionado = SelectedUserManager.getInstancia().getClienteSeleccionado();
            cuentas = getCuentasDelCliente(clienteSeleccionado);
            loadContent(); 
    		
    		JLabel label1 = new JLabel("Cuentas del Cliente");
            label1.setFont(new Font("Tahoma", Font.BOLD, 24));
            label1.setForeground(new Color(0, 128, 0));
            label1.setBounds(550, 50, 300, 30);
            add(label1);

            String[] columns = new String[]{"Número de Cuenta", "Saldo", "Beneficiario"};

            
            AccountTable accountTable = new AccountTable(cuentas, columns);
            this.accountTable = accountTable.getTable();
            accountTable.setBackground(new Color(144, 238, 144));
            accountTable.setBounds(480, 100, 1000, 600);
            add(accountTable);

        }
		
	}
	
	private ArrayList<CuentaBancaria> getAccountList() {
		Banco banco = Banco.getInstancia();
		ArrayList<CuentaBancaria> cuentas = banco.getCuentas();
		return cuentas;
	}
	
	
}
