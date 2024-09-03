package GUI.Components;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import GUI.Utils.Colors;
import GUI.Utils.Fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

public abstract class BaseScreen extends JPanel {
    protected ActionListener listener;
    protected JPanel panel;

    public BaseScreen(ActionListener listener) {
        this.listener = listener;
        // Panel principal
        this.panel = new JPanel();
        this.panel.setBounds(0, 0, 379, 1004);
        this.panel.setBackground(new Color(0, 153, 102));
        this.panel.setLayout(null);
        add(panel);
        loadContent();
    }

    protected abstract void loadContent();
    

    
}
