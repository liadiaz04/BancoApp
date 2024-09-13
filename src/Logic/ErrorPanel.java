package Logic;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class ErrorPanel extends JPanel {
	
	private static final Color ERROR_RED = new Color(237, 67, 55);
	/**
	 * Create the panel.
	 */
	public ErrorPanel(final InfoWindow frame, String message) {
		setBounds(new Rectangle(0, 0, 350, 400));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JButton btnAction = new JButton("ACEPTAR");
		btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.enableParent();
				frame.dispose();
			}
		});
		btnAction.setFocusable(false);
		btnAction.setForeground(Color.WHITE);
		btnAction.setBackground(ERROR_RED);
		btnAction.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAction.setBounds(90, 344, 158, 43);
		add(btnAction);
		
		JPanel panelImg = new JPanel();
		panelImg.setBackground(ERROR_RED);
		panelImg.setBounds(0, 0, 349, 189);
		add(panelImg);
		panelImg.setLayout(null);
		
		JLabel errorIcon = new JLabel("");
		errorIcon.setIcon(new ImageIcon(ErrorPanel.class.getResource("/GUI/Icons/sad-face.png")));
		errorIcon.setBounds(110, 12, 128, 128);
		panelImg.add(errorIcon);
		
		JLabel errorLabel = new JLabel("Error!");
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBounds(153, 152, 49, 25);
		panelImg.add(errorLabel);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(Color.WHITE);
		panelInfo.setBounds(10, 231, 328, 78);
		add(panelInfo);
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		JTextArea text = new JTextArea();
		text.setBackground(Color.WHITE);
		text.setEditable(false);
		text.setFocusable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setText(message);
		panelInfo.add(text, BorderLayout.CENTER);
		SimpleAttributeSet centered = new SimpleAttributeSet();
		StyleConstants.setAlignment(centered, StyleConstants.ALIGN_JUSTIFIED);

	}
}
