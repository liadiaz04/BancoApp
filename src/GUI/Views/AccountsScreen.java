package GUI.Views;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Banco;
import Clases.CuentaBancaria;
import GUI.Components.BaseScreenWithSideMenu;
import GUI.Tables.AccountTable;

public class AccountsScreen extends BaseScreenWithSideMenu{
	private JTable accountTable;
	private DefaultTableModel mt;
	private JScrollPane s1;

	public AccountsScreen(ActionListener listener) {
		super(listener);
	}
	
	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Cuentas");
        label.setBounds(550, 50, 200, 30);
        add(label);
        
        ArrayList<CuentaBancaria> cuentas = getAccountList();
        String[] columns = new String[]{"No_Cuenta", "Saldo", "Beneficiario", "Moneda", "Estado","Fecha de Apertura", "TipoCuenta"};
        
        
        AccountTable accountTable = new AccountTable(cuentas, columns);
        accountTable.setBounds(550, 100, 1180, 600);
        add(accountTable);
	}
	
	private ArrayList<CuentaBancaria> getAccountList() {
		Banco banco = Banco.getInstancia();
		ArrayList<CuentaBancaria> cuentas = banco.getCuentas();
		return cuentas;
	}
}


