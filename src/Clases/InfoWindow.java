package Clases;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InfoWindow extends JFrame {

	private JFrame parent;
	private JPanel contentPane;
	
	public static final String ERROR = "ERROR";
	public static final String SUCCESS = "SUCCESS";
	
	public InfoWindow(JFrame frame, String type, String message) {
		frame.setEnabled(false);
		parent = frame;
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setAlwaysOnTop(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		addPanel(type, message);
		
		
	}
	
	private void addPanel(String type, String message){
		if(type.equals(ERROR)){
			contentPane.add(new ErrorPanel(this, message));
		}
		else{
			contentPane.add(new SuccessPanel(this, message));
		}
	}
	public void enableParent(){
		parent.setEnabled(true);
	}

}
