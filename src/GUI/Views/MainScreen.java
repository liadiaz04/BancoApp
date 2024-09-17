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
	
	private final JLabel label = new JLabel("New label");

	
    public MainScreen(ActionListener listener) {
        super(listener);
    }

    @Override
    protected void loadContent() {
        
    	setBackground(Color.WHITE);
    	
    	JLabel subtitleLabel = new JLabel("Su confianza es nuestra prioridad");
        subtitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        subtitleLabel.setBounds(900, 13, 700, 50);
        subtitleLabel.setForeground(Color.DARK_GRAY); 
        add(subtitleLabel);
    	  	
    	JPanel panel = new JPanel();
    	panel.setBackground(new Color(0, 143, 102));
    	panel.setBounds(378, 83, 1917, 244);
    	panel.setLayout(null);
    	add(panel);
    	
    	JLabel lblNewLabel = new JLabel("Administración/Control/Banco");
    	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
    	lblNewLabel.setBounds(50, 40, 400, 50);
    	lblNewLabel.setForeground(Color.WHITE); 
    	panel.add(lblNewLabel);
    	
    	
    }
}
