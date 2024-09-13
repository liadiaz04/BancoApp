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

public class SuccessPanel extends JPanel {
	
	private static final Color SUCCESS_GREEN = new Color(92, 184, 92);
	/**
	 * Create the panel.
	 */
	public SuccessPanel(final InfoWindow frame, String message) {
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
		btnAction.setBackground(SUCCESS_GREEN);
		btnAction.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAction.setBounds(90, 344, 158, 43);
		add(btnAction);
		
		JPanel panelImg = new JPanel();
		panelImg.setBackground(SUCCESS_GREEN);
		panelImg.setBounds(0, 0, 349, 189);
		add(panelImg);
		panelImg.setLayout(null);
		
		JLabel successIcon = new JLabel("");
		successIcon.setIcon(new ImageIcon(SuccessPanel.class.getResource("/GUI/Icons/check-mark.png")));
		successIcon.setBounds(110, 12, 128, 128);
		panelImg.add(successIcon);
		
		JLabel successLabel = new JLabel("Succesfuly!");
		successLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		successLabel.setForeground(Color.WHITE);
		successLabel.setBounds(134, 151, 101, 25);
		panelImg.add(successLabel);
		
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
