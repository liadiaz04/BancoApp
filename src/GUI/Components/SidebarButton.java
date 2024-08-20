package GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class SidebarButton extends JButton {
	
    private Color backgroundColor;
    private Color hoverColor;
    private Color textColor;
    private Font font;

    public SidebarButton(String text, Icon icon, Font font, Color backgroundColor, Color hoverColor, Color textColor) {
        super(text);
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
        this.font = font;
        initializate();
    }
    
    
    private void initializate() {
    	setFont(font);
        //setIcon(icon);
        setBackground(backgroundColor);
        setForeground(textColor);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(true);
        setHorizontalTextPosition(SwingConstants.RIGHT); // Alinear el texto a la derecha del ícono
        setHorizontalAlignment(SwingConstants.LEFT);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                onMouseEnter();
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            	onMouseLeave();
            }
        });
    }
    
    private void onMouseEnter() {
    	setBackground(hoverColor);
    	
    }
    
    private void onMouseLeave() {
    	setBackground(backgroundColor);
    }

    public void setButtonAction(ActionListener action) {
        addActionListener(action);
    }
    
    public void addToPanel(JPanel panel, int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        panel.add(this);
    }

}
