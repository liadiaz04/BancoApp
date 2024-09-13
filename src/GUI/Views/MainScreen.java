package GUI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import GUI.Components.BaseScreenWithSideMenu;
import GUI.Utils.Colors;
import GUI.Utils.Fonts;

public class MainScreen extends BaseScreenWithSideMenu {
	
    public MainScreen(ActionListener listener) {
        super(listener);
    }

    @Override
    protected void loadContent() {
        
    	JLabel label = new JLabel("BIENVENIDO");
    	label.setFont(new Font("Tahoma", Font.BOLD, 36)); 
    	label.setBounds(1050, 150, 900, 330);
    	add(label);

        
    }
    
}
