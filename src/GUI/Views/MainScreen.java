package GUI.Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale; // Importa Locale

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.Components.BaseScreenWithSideMenu;

import javax.swing.ImageIcon;

public class MainScreen extends BaseScreenWithSideMenu {
	
	
	public MainScreen(ActionListener listener) {
        super(listener);
    }

    @Override
    protected void loadContent() {
        
        setBackground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("Su confianza es nuestra prioridad");
        subtitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        subtitleLabel.setBounds(900, 13, 700, 50);
        subtitleLabel.setForeground(Color.DARK_GRAY); 
        add(subtitleLabel);
        
        JLabel bienvenido = new JLabel("Bienvenido");
        bienvenido.setFont(new Font("Tahoma", Font.PLAIN, 24));
        bienvenido.setBounds(421, 365, 372, 88);
        bienvenido.setForeground(Color.DARK_GRAY); 
        add(bienvenido);
        
        JLabel icon = new JLabel("");
        icon.setIcon(new ImageIcon("C:\\Users\\dir.auditoria\\Documents\\Github\\BancoApp\\src\\GUI\\Icons\\principal.png"));
        icon.setFont(new Font("Tahoma", Font.PLAIN, 24));
        icon.setBounds(1167, 393, 779, 507);
        icon.setForeground(Color.DARK_GRAY); 
        add(icon);
        
        Font commonFont = new Font("Tahoma", Font.PLAIN, 20); // Define la fuente común

        JLabel label = new JLabel("<html>Controle y administre de forma óptima y sencilla todo el flujo de operaciones relacionadas con cuentas de ahorro. " +
                        "Esta herramienta le permitirá llevar un control completo y detallado de las cuentas de los clientes, brindando acceso a funcionalidades especiales para tareas específicas, como son:</html>");
        label.setFont(commonFont); // Aplica la fuente común
        label.setBounds(421, 475, 779, 237); 
        label.setForeground(Color.DARK_GRAY);
        label.setVerticalAlignment(SwingConstants.TOP); 
        label.setHorizontalAlignment(SwingConstants.LEFT); // Alineación horizontal
        add(label);

        JLabel detalles = new JLabel(
        	    "<html>" +
        	    "<div style='text-align: justify;'>" + // Justificación del texto
        	    "<ul>" +
        	    "<li>Registro de Clientes y sus Cuentas de Ahorro: Agregue y administre información de clientes y sus respectivas cuentas.</li><br>" +
        	    "<li>Operaciones Bancarias: Realice depósitos y retiros de manera rápida y segura.</li><br>" +
        	    "<li>Creación y Eliminación de Registros: Administre la información de clientes y cuentas con facilidad.</li><br>" +
        	    "<li>Sección de Reportes: Acceda a informes detallados sobre las transacciones y el estado de las cuentas en el sistema.</li><br>" +
        	    "</ul>" +
        	    "</div>" +
        	    "</html>");
        	detalles.setFont(commonFont); // Aplica la fuente común
        	detalles.setBounds(388, 630, 700, 382); 
        	detalles.setForeground(Color.DARK_GRAY);
        	detalles.setVerticalAlignment(SwingConstants.TOP);
        	detalles.setHorizontalAlignment(SwingConstants.LEFT); // Alineación horizontal
        	add(detalles);



            
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 143, 102));
        panel.setBounds(378, 83, 1917, 244);
        panel.setLayout(null);
        add(panel);
        
        JLabel lblNewLabel = new JLabel("Administración/Control/Banco");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel.setBounds(111, 40, 398, 50);
        lblNewLabel.setForeground(Color.WHITE); 
        panel.add(lblNewLabel);
        
        // Obtener la fecha actual
        Date fechaActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd 'de' MMMM 'del' yyyy", new Locale("es", "ES")); // Incluir el día de la semana
        String fechaFormateada = "Hoy es " + sdf.format(fechaActual);
        
        JLabel fecha = new JLabel(fechaFormateada);
        fecha.setFont(new Font("Tahoma", Font.PLAIN, 30));
        fecha.setBounds(111, 123, 789, 87); 
        fecha.setForeground(Color.WHITE); 
        panel.add(fecha);
    }
}
