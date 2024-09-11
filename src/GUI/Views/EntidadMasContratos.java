package GUI.Views;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import GUI.Components.BaseScreenWithSideMenu;

public class EntidadMasContratos extends BaseScreenWithSideMenu{
	
	public EntidadMasContratos(ActionListener listener) {
		super(listener);
	}
	
	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Entidad con mas contratooos");
        label.setBounds(550, 50, 200, 30);
        add(label);
        
		
	}

}



