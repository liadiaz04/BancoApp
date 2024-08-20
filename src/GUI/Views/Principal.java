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

public class Principal extends JFrame {

	private JPanel contentPane;
	private SidebarButton principalButton;
	private SidebarButton secundaryButton;

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
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 651);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(null);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 337, 604);
		panel.setBackground(new Color(0, 153, 102));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Banco Metropolitano");
		lblNewLabel.setBounds(57, 5, 223, 46);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(57, 52, 223, 22);
		panel.add(separator);
		separator.setBackground(Color.WHITE);
		separator.setForeground(Color.WHITE);
		
		//Principal Boton
		principalButton = new SidebarButton(
				"Principal",
				null,
				Fonts.BUTTONS.getFont(), 
				Colors.PRIMARY.getColor(), 
				Colors.HOVER.getColor(), 
				Colors.TEXT.getColor()
				);
		
		principalButton.addToPanel(panel, 0, 80, 337, 50);
		
		//Secundario Boton
		secundaryButton = new SidebarButton(
				"Secundario", 
				null, 
				null, 
				Colors.PRIMARY.getColor(), 
				Colors.HOVER.getColor(), 
				Colors.TEXT.getColor()
				);
		secundaryButton.addToPanel(panel, 0, 130, 337, 50);
		
		
		

		
		
		
		
	}
}
