package GUI.Views;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import GUI.Components.BaseScreenWithSideMenu;

public class ReportsScreen extends BaseScreenWithSideMenu {
	
	public ReportsScreen(ActionListener listener) {
		super(listener);
	}
	
	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Reportes");
        label.setBounds(550, 50, 200, 30);
        add(label);
        
		
	}

}
