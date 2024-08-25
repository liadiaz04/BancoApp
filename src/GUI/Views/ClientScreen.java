package GUI.Views;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import Clases.Banco;
import Clases.Cliente;
import GUI.Components.BaseScreenWithSideMenu;
import GUI.Components.ClientTable;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.security.ntlm.Client;

public class ClientScreen extends BaseScreenWithSideMenu {
	private JTable clientTable;
	private DefaultTableModel mt;
	private JScrollPane s1;

	public ClientScreen(ActionListener listener) {
		super(listener);
	}
	
	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Clientes");
        label.setBounds(550, 50, 200, 30);
        add(label);
        
        ArrayList<Cliente> clientes = getClientList();
        String[] columns = new String[]{"Identificador", "Nombre", "Dirección", "Teléfono", "Correo"};
        
        
        ClientTable clientTable = new ClientTable(clientes, columns);
        clientTable.setBounds(550, 100, 1180, 600);
        add(clientTable);
	}
	
	
	private ArrayList<Cliente> getClientList() {
		Banco banco = Banco.getInstancia();
		ArrayList<Cliente> clientes = banco.getClientes();
		return clientes;
	}
	
	
}

