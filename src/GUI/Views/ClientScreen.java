package GUI.Views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import GUI.Components.BaseScreenWithSideMenu;

import javax.swing.JTextField;

public class ClientScreen extends BaseScreenWithSideMenu {

	public ClientScreen(ActionListener listener) {
		super(listener);
	}
	
	@Override
	protected void loadContent() {
		
		
		JLabel label = new JLabel("Clientes");
        label.setBounds(550, 50, 200, 30);
        add(label);
        
		
	}

}
