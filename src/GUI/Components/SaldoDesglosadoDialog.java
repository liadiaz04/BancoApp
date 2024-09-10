package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SaldoDesglosadoDialog extends JDialog {
    public SaldoDesglosadoDialog(Frame owner, String cajeroId, String saldoDesglosado) {
        super(owner, "Saldo Desglosado - " + cajeroId, true);
        
        // Configuración de la ventana
        setSize(350, 250); // Tamaño de la ventana más pequeño
        setLocationRelativeTo(owner); // Centrar en la ventana principal
        
        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(204, 255, 204)); // Color verde pastel
        
        // Área de texto para mostrar el saldo desglosado
        JTextArea textArea = new JTextArea(saldoDesglosado);
        textArea.setFont(new Font("Arial", Font.BOLD, 16)); // Ajustar la fuente
        textArea.setEditable(false); 
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.WHITE); 
        textArea.setForeground(Color.BLACK); 
        
        // Scroll para el área de texto
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Botón de cerrar
        JButton closeButton = new JButton("Cerrar");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16)); 
        closeButton.setBackground(new Color(153, 204, 153)); 
        closeButton.setForeground(Color.WHITE); 
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        
        panel.add(closeButton, BorderLayout.SOUTH);
        
        add(panel);
        setVisible(true);
    }
}
