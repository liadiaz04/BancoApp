package GUI.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.Views.Application;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.net.URL;

public class SidebarButton extends JButton {
	
    private Color backgroundColor;
    private Color hoverColor;
    private Color textColor;
    private Font font;

    public SidebarButton(String text, String iconPath, Font font, Color backgroundColor, Color hoverColor, Color textColor) {
        super(text);
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
        this.font = font;
        loadIcon(iconPath);
        initializate();
        setIconTextGap(15); // Espacio entre el ícono y el texto
    }
    
    
    private void initializate() {
    	setFont(font);
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

    private void setButtonAction(ActionListener action) {
        addActionListener(action);
    }
    
    private void loadIcon(String iconPath) {
    	if (iconPath == null || iconPath.isEmpty()) {
    		setIcon(null);
    	}
    	else {
    		ImageIcon imageIcon = new ImageIcon(Application.class.getResource(iconPath));
    		setIcon(imageIcon);
    	}
        
        
    }
    
    public void addToPanel(JPanel panel, int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        panel.add(this);
    }

}
