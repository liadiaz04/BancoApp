package GUI.Views;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;

import Clases.Agencia;
import Clases.Banco;
import GUI.Components.BaseScreenWithSideMenu;
import GUI.Tables.AgenciesTable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AgencyScreen extends BaseScreenWithSideMenu {
	private JTable AgenciesTable;
	private DefaultTableModel mt;
	private JScrollPane s1;
	
	public AgencyScreen(ActionListener listener) {
		super(listener);
	}
	
	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Agencias");
        label.setBounds(550, 50, 200, 30);
        add(label);
        
        ArrayList<Agencia> agencias = getAgenciesList();
        String[] columns = new String[]{"Identificador", "Gerente", "Direccion"};
        
        AgenciesTable agenciesTable = new AgenciesTable(agencias, columns);
        agenciesTable.setBounds(550, 100, 1180, 600);
        add(agenciesTable);
        
		
	}
	
	 private ArrayList<Agencia> getAgenciesList() {
 		Banco banco = Banco.getInstancia();
 		ArrayList<Agencia> agencias = banco.getAgencias();
 		return agencias;
 	}

}
