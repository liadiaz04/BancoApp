package GUI.Views;

import javax.swing.*;

import GUI.Components.BaseScreenWithSideMenu;


import java.awt.event.ActionListener;

import javax.swing.JLabel;

import GUI.Components.BaseScreenWithSideMenu;

public class UltimasOperacionesScreen extends BaseScreenWithSideMenu {
	
	public UltimasOperacionesScreen(ActionListener listener) {
		super(listener);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void loadContent() {
		
		JLabel label = new JLabel("Reportes");
        label.setBounds(550, 50, 200, 30);
        add(label);
        
		
	}

}
