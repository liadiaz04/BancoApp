package GUI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI.Components.BaseScreenWithSideMenu;

public class ClienteConSaldoSuperior extends BaseScreenWithSideMenu{

		
	private JTextField montoField; // Campo de texto para el monto

    public ClienteConSaldoSuperior(ActionListener listener) {
        super(listener);
    }

    @Override
    protected void loadContent() {
        JLabel label = new JLabel("Cliente con Saldo Superior al valor a Ingresar");
        label.setFont(new Font("Tahoma", Font.BOLD, 24)); 
        label.setForeground(new Color(0, 128, 0)); 
        label.setBounds(550, 50, 800, 30);
        add(label);

        // Nuevo JLabel para ingresar monto
        JLabel ingresarMontoLabel = new JLabel("Ingrese un monto:");
        ingresarMontoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Fuente más pequeña
        ingresarMontoLabel.setBounds(550, 100, 200, 30);
        add(ingresarMontoLabel);

        // JTextField para ingresar el monto
        montoField = new JTextField();
        montoField.setBounds(550, 140, 200, 30);
        add(montoField);
    }
	
}
