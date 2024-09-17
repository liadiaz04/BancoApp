package GUI.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.Views.Application;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.net.URL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SidebarButton extends JButton {
    
    private Color backgroundColor;
    private Color hoverColor;
    private Color textColor;
    private Font font;
    private JPopupMenu popupMenu;
    private ActionListener listener; 


    public SidebarButton(String text, String iconPath, Font font, Color backgroundColor, Color hoverColor, Color textColor, ActionListener listener) {
        super(text);
        this.listener = listener; 
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
        this.font = font;
        loadIcon(iconPath);
        initialize();
        setIconTextGap(15); 
        if (text.equals("Reportes")) {
            createPopupMenu();
        }
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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                onMouseEnter();
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                onMouseLeave();
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (popupMenu != null) {
                    popupMenu.show(SidebarButton.this, 0, getHeight());
                }
            }
        });
    }
    
    private void onMouseEnter() {
        setBackground(hoverColor);
    }
    
    private void onMouseLeave() {
        setBackground(backgroundColor);
    }

    private void loadIcon(String iconPath) {
        if (iconPath == null || iconPath.isEmpty()) {
            setIcon(null);
        } else {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(iconPath));
            setIcon(imageIcon);
        }
    }
    
    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        String[] reportes = {
            "<html>1. Clientes con al menos una cuenta con<br> saldo superior a un valor dado.</html>",
            "<html>2. Entidad con mayor cantidad de <br>contratos con el banco.</html>",
            "<html>3. Cuentas de Formación de Fondos <br>asociadas a una entidad.</html>",
            "<html>4. Cajeros con saldo insuficiente de cada<br> agencia.</html>"
        };
        for (final String reporte : reportes) {
            JMenuItem menuItem = new JMenuItem(reporte);
            menuItem.setBackground(backgroundColor);
            menuItem.setForeground(textColor);
            menuItem.setFont(new Font("Tahoma", Font.BOLD, 17));
            menuItem.setPreferredSize(new Dimension(377, 45));
            
            // Añadimos un ActionListener a cada JMenuItem
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleMenuItemAction(reporte);  // Llamamos al método para manejar la acción
                }
            });

            popupMenu.add(menuItem);
        }
        popupMenu.setPopupSize(new Dimension(377, 190)); // Ajusta el tamaño del popup aquí
    }

    // Método para manejar las acciones del menú
    private void handleMenuItemAction(String reporte) {
        if (listener != null) { // Aseguramos que el listener no sea null
            switch (reporte) {
                case "<html>1. Clientes con al menos una cuenta con<br> saldo superior a un valor dado.</html>":
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "ClienteConSaldoSuperior"));
                    break;
                case "<html>2. Entidad con mayor cantidad de <br>contratos con el banco.</html>":
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "EntidadMasContratos"));
                    break;
                case "<html>3. Cuentas de Formación de Fondos <br>asociadas a una entidad.</html>":
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "CuentasFFondosAsociadas"));
                    break;
                case "<html>4. Cajeros con saldo insuficiente de cada<br> agencia.</html>":
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "CajerosSinSaldo"));
                    break;
                default:
                    // Acción por defecto
                    break;
            }
        }
    }

    public void addToPanel(JPanel panel, int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        panel.add(this);
    }
}