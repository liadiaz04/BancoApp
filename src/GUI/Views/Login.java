package GUI.Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Rectangle;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.JPasswordField;
import java.awt.Cursor;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtIngreseSuUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelBienvenida = new JPanel();
		panelBienvenida.setBackground(new Color(124, 189, 107));
		panelBienvenida.setBounds(0, 0, 384, 466);
		contentPane.add(panelBienvenida);
		panelBienvenida.setLayout(null);
		
		JLabel bienvenida = new JLabel("    Banco \r\nMetropolitano");
		bienvenida.setBounds(33, 285, 339, 53);
		panelBienvenida.add(bienvenida);
		bienvenida.setFont(new Font("Century Gothic", Font.BOLD, 25));
		
		JLabel lblNewLabel = new JLabel("Cada decision cuenta, cada ahorro suma.");
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		lblNewLabel.setBounds(12, 410, 369, 16);
		panelBienvenida.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/GUI/Icons/bancoLogin.png")));
		lblNewLabel_3.setBounds(73, 62, 234, 192);
		panelBienvenida.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Iniciar Sesion");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel_1.setBounds(431, 71, 303, 63);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(447, 168, 72, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblContrasea.setBounds(447, 269, 108, 16);
		contentPane.add(lblContrasea);
		
		txtIngreseSuUsuario = new JTextField();
		txtIngreseSuUsuario.setForeground(Color.BLACK);
		txtIngreseSuUsuario.setBounds(439, 201, 254, 34);
		contentPane.add(txtIngreseSuUsuario);
		txtIngreseSuUsuario.setColumns(10);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(new Color(124, 189, 107));
		btnNewButton.setForeground(UIManager.getColor("Button.disabledShadow"));
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnNewButton.setBounds(512, 382, 136, 34);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(441, 298, 252, 34);
		contentPane.add(passwordField);
		
		
	}
	
	
    
}
