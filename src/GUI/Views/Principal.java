package GUI.Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import GUI.Components.SidebarButton;
import GUI.Utils.Colors;
import GUI.Utils.Fonts;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GUI.Components.SidebarButton;
import GUI.Utils.Colors;
import GUI.Utils.Fonts;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import GUI.Components.SidebarButton;
import GUI.Utils.Colors;
import GUI.Utils.Fonts;
import javax.swing.ImageIcon;
public class Principal extends JFrame {
	
	private JPanel contentPane;
	private JPanel cardPanel;
	private JPanel CardLayout;
	
	private SidebarButton principalButton;
	private SidebarButton clientesButton;
	private SidebarButton cuentasButton;
	private SidebarButton agenciasButton;
	private SidebarButton reportesButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 651);
		
		
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(null);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Panel lateral (Sidebar)
		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBounds(0, 0, 379, 1004);
		sidebarPanel.setBackground(new Color(0, 153, 102));
		sidebarPanel.setLayout(null);
		contentPane.add(sidebarPanel);
		
		JLabel lblNewLabel = new JLabel("Banco Metropolitano");
		lblNewLabel.setBounds(57, 50, 280, 60);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(57, 98, 280, 23);
		panel.add(separator);
		separator.setBackground(Color.WHITE);
		separator.setForeground(Color.WHITE);
		
		//Principal Boton
		principalButton = new SidebarButton(
				"Principal",
				"/GUI/Icons/home.png",
				Fonts.BUTTONS.getFont(), 
				Colors.PRIMARY.getColor(), 
				Colors.HOVER.getColor(), 
				Colors.TEXT.getColor()
				);
		
		principalButton.addToPanel(panel, 0, 150, 400, 80);
		
		//Clientes Boton
		clientesButton = new SidebarButton(
				"Clientes", 
				"/GUI/Icons/clientes.png", 
				Fonts.BUTTONS.getFont(), 
				Colors.PRIMARY.getColor(), 
				Colors.HOVER.getColor(), 
				Colors.TEXT.getColor()
				);
		clientesButton.addToPanel(panel, 0, 230, 400, 80);
		
		//Cuentas Boton
		cuentasButton = new SidebarButton(
						"Cuentas", 
						"/GUI/Icons/cuentas.png", 
						Fonts.BUTTONS.getFont(), 
						Colors.PRIMARY.getColor(), 
						Colors.HOVER.getColor(), 
						Colors.TEXT.getColor()
						);
		cuentasButton.addToPanel(panel, 0, 310, 400, 80);
		
		//Agencias Boton
				agenciasButton = new SidebarButton(
								"Agencias", 
								"/GUI/Icons/agencias.png", 
								Fonts.BUTTONS.getFont(), 
								Colors.PRIMARY.getColor(), 
								Colors.HOVER.getColor(), 
								Colors.TEXT.getColor()
								);
				agenciasButton.addToPanel(panel, 0, 390, 400, 80);
				
		//Agencias Boton
				reportesButton = new SidebarButton(
								"Reportes", 
								"/GUI/Icons/reportes.png", 
								Fonts.BUTTONS.getFont(), 
								Colors.PRIMARY.getColor(), 
								Colors.HOVER.getColor(), 
								Colors.TEXT.getColor()
								);
				reportesButton.addToPanel(panel, 0, 470, 400, 80);
				
		
		

		
		
		
		
	}
}
