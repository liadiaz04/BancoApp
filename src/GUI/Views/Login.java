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

import Clases.Banco;
import Clases.InfoWindow;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtIngreseSuUsuario;
	private JPasswordField passwordField;
    private JButton entrarBtn ;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(229,229,229));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelBienvenida = new JPanel();
		panelBienvenida.setBackground(new Color(0, 153, 102));
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
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/GUI/Icons/bancoMet.png")));
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
		
		entrarBtn = new JButton("Entrar");
		entrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = txtIngreseSuUsuario.getText();
        		String password = new String(passwordField.getPassword()); 

        		boolean isAuthenticated = Banco.getInstancia().authenticateUser(username, password);

        		if (isAuthenticated) {
        			Application app = new Application();
        			app.setVisible(true);
        			dispose();

        			
        		} else {
        			mostrarPanel();
        		}
			}
		});
		entrarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		entrarBtn.setFocusable(false);
		entrarBtn.setBackground(new Color(0, 153, 102));
		entrarBtn.setForeground(UIManager.getColor("Button.disabledShadow"));
		entrarBtn.setFont(new Font("Century Gothic", Font.BOLD, 15));
		entrarBtn.setBounds(512, 382, 136, 34);
		contentPane.add(entrarBtn);
	
		passwordField = new JPasswordField();
		passwordField.setBounds(441, 298, 252, 34);
		contentPane.add(passwordField);
		
		
	}
	
	public void mostrarPanel(){
		InfoWindow w = new InfoWindow (this,InfoWindow.ERROR,"Usted no esta autenticado en la plataforma");
	}
	
	public JButton getBtnEnviar (){
		return entrarBtn;
	}
	
	  public String getUsername() {
	        return txtIngreseSuUsuario.getText();
	    }

	    public char[] getPassword() {
	        return passwordField.getPassword();
	    }
}
