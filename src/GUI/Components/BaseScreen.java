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

    public BaseScreen(ActionListener listener) {
        this.listener = listener;
        loadContent();
    }

    protected abstract void loadContent();
    

    
}
