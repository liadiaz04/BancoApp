package GUI.Views;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import GUI.Components.BaseScreenWithSideMenu;

public class AgencyScreen extends BaseScreenWithSideMenu {
	
	public AgencyScreen(ActionListener listener) {
		super(listener);
	}
	
	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Agencia");
        label.setBounds(550, 50, 200, 30);
        add(label);
        
		
	}
}
