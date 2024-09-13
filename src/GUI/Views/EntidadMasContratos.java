package GUI.Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.Components.BaseScreenWithSideMenu;
import Logic.Banco;

public class EntidadMasContratos extends BaseScreenWithSideMenu {

    public EntidadMasContratos(ActionListener listener) {
        super(listener);
        setBackground(new Color(143, 188, 143)); // Verde grisáceo suave
    }

    @Override
    protected void loadContent() {
        try {

            Color primaryGreen = new Color(34, 139, 34); 
            Color lightGray = new Color(245, 245, 245); 

            JLabel titleLabel = new JLabel("Entidad con mayor cantidad de contratos con el banco");
            titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setBounds(500, 20, 1200, 200); 
            add(titleLabel);

            JPanel panel = new JPanel();
            panel.setBackground(lightGray);
            panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(primaryGreen, 2, true), 
                "Entidad con más contratos", 0, 0, new Font("Tahoma", Font.BOLD, 16), primaryGreen));
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(20, 0, 20, 0); 

            JLabel iconLabel = new JLabel(new ImageIcon("path/to/icon.png")); 
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(iconLabel, gbc);

            // Etiqueta para mostrar la entidad
            JLabel entityLabel = new JLabel(" " + getEntidades());
            entityLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
            entityLabel.setForeground(primaryGreen); 
            entityLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(entityLabel, gbc);
            

            // Configuración del tamaño y la posición del panel
            panel.setBounds(850, 250, 500, 200); 
            add(panel);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    private String getEntidades() {
        Banco banco = Banco.getInstancia(); 
        String entidad = banco.entidadConMasContratos(); 
        return entidad != null ? entidad : "No hay contratos disponibles"; 
    }
}
