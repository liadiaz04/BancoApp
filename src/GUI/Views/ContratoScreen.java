package GUI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Banco;
import Clases.Contrato;
import GUI.Components.BaseScreenWithSideMenu;
import GUI.Tables.ContratoTable;

public class ContratoScreen extends BaseScreenWithSideMenu{

	private JTable contratoTable;
	private DefaultTableModel mt;
	private JScrollPane s1;

	public ContratoScreen(ActionListener listener) {
		super(listener);
	}
	
	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Contratos");
        label.setFont(new Font("Tahoma", Font.BOLD, 24)); 
        label.setForeground(new Color(0, 128, 0)); 
        label.setBounds(550, 50, 200, 30);
        add(label);
        
        ArrayList<Contrato> contratos = getContratosList();
        String[] columns = new String[]{"Entidad", "Periodo de Tiempo", "Salario"};
        
        
        ContratoTable contratoTable = new ContratoTable(contratos, columns);
        contratoTable.setBounds(550, 100, 1180, 600);
        add(contratoTable);
	}
	
	private ArrayList<Contrato> getContratosList() {
		Banco banco = Banco.getInstancia();
		ArrayList<Contrato> contrato = banco.getContratos();
		return contrato;
	}
}


