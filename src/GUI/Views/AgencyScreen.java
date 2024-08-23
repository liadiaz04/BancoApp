package GUI.Views;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import GUI.Components.BaseScreenWithSideMenu;
import javax.swing.JButton;
import javax.swing.JTable;

public class AgencyScreen extends BaseScreenWithSideMenu {
	private JTable table;
	
	public AgencyScreen(ActionListener listener) {
		super(listener);
	}
	
	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Agencia");
        label.setBounds(550, 50, 200, 30);
        add(label);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(630, 210, 97, 25);
        add(btnNewButton);
        
        table = new JTable();
        table.setBounds(537, 290, 158, 156);
        add(table);
        
		
	}
}
