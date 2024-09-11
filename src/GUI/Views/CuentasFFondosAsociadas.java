package GUI.Views;
import java.awt.event.ActionListener;


import javax.swing.JLabel;

import GUI.Components.BaseScreenWithSideMenu;

public class CuentasFFondosAsociadas extends BaseScreenWithSideMenu{

	public CuentasFFondosAsociadas(ActionListener listener) {
		super(listener);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loadContent() {
		JLabel label = new JLabel("FORMACION FONDOS");
        label.setBounds(550, 50, 200, 30);
        add(label);

		
	}

	
	
}






