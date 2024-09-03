package GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationButton extends JButton {
    
    private String targetView;
    private Color backgroundColor;
    private Color hoverColor;
    private Color textColor;
    private Font font;

    public NavigationButton(String text, String iconPath, Font font, Color backgroundColor, Color hoverColor, Color textColor, String targetView) {
        super(text);
        this.targetView = targetView;
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
        this.font = font;
        initialize();
        loadIcon(iconPath);
    }

    private void initialize() {
        setFont(font);
        setBackground(backgroundColor);
        setForeground(textColor);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(true);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setHorizontalAlignment(SwingConstants.LEFT);
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(backgroundColor);
            }
        });
    }
    
    private void setButtonAction(ActionListener action) {
        addActionListener(action);
    }

    
    private void loadIcon(String iconPath) {
        if (iconPath != null && !iconPath.isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(iconPath));
            setIcon(imageIcon);
        }
    }


    public void addToPanel(JPanel panel, int x, int y, int width, int height) {
    	System.out.println(panel);
        setBounds(x, y, width, height);
        panel.add(this);
    }
}
