package GUI.Components;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BasePanel extends JFrame{
	
	public BasePanel() {
        setBackground(Color.LIGHT_GRAY);
        add(new JLabel("BASE PANEL"));
    }

}
