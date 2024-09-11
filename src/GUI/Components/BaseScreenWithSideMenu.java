package GUI.Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import GUI.Utils.Colors;
import GUI.Utils.Fonts;

public abstract class BaseScreenWithSideMenu extends BaseScreen {
	
	
	public BaseScreenWithSideMenu(ActionListener listener) {
		super(listener);
        
		loadSideMenu();
	}

	protected void loadSideMenu() {
		setLayout(null);
        System.out.println(panel);

        // Etiqueta de título
        JLabel lblNewLabel = new JLabel("Banco Metropolitano");
        lblNewLabel.setBounds(57, 50, 280, 60);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
        panel.add(lblNewLabel);

        // Separador
        JSeparator separator = new JSeparator();
        separator.setBounds(57, 98, 280, 23);
        separator.setBackground(Color.WHITE);
        separator.setForeground(Color.WHITE);
        panel.add(separator);

        // Botones del Sidebar
        addSidebarButton(panel, "Principal", "/GUI/Icons/home.png", 150);
        addSidebarButton(panel, "Clientes", "/GUI/Icons/clientes.png", 230);
        addSidebarButton(panel, "Contratos", "/GUI/Icons/cuentas.png", 310);
        addSidebarButton(panel, "Agencias", "/GUI/Icons/agencias.png", 390);
        addSidebarButton(panel, "Reportes", "/GUI/Icons/reportes.png", 470);
    }
    
    private void addSidebarButton(JPanel panel, String text, String iconPath, int yPosition) {
        SidebarButton button = new SidebarButton(
            text,
            iconPath,
            Fonts.BUTTONS.getFont(),
            Colors.PRIMARY.getColor(),
            Colors.HOVER.getColor(),
            Colors.TEXT.getColor(), listener
        );
        button.addToPanel(panel, 0, yPosition, 400, 80);
        
        // Asignar un ActionListener al botón
        button.addActionListener(listener); 
        button.addToPanel(panel, 0, yPosition, 400, 80);
    }

}
