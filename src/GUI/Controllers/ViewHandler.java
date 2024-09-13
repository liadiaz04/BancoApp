package GUI.Controllers;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import GUI.Components.BaseScreen;
import GUI.Views.AgencyScreen;
import GUI.Views.CajerosSinSaldo;
import GUI.Views.ClientScreen;
import GUI.Views.ClienteConSaldoSuperior;
import GUI.Views.ContratoScreen;
import GUI.Views.CuentasFFondosAsociadas;
import GUI.Views.EntidadMasContratos;
import GUI.Views.MainScreen;
import GUI.Views.ReportsScreen;
import GUI.Views.UserAccountScreen;


public class ViewHandler {
	
	private JPanel contentPane;
    private CardLayout cardLayout;
	
	
	public ViewHandler(JPanel contentPane, CardLayout cardLayout) {
		this.contentPane = contentPane;
		this.cardLayout = cardLayout;
	}
	
	public void loadViews() {
	    BaseScreen mainScreen = loadMainScreen();
	    BaseScreen clientScreen = loadClientScreen();
	    BaseScreen contratoScreen = loadContratoScreen();
	    BaseScreen agencyScreen = loadAgencyScreen();
	    BaseScreen reportScreen = loadReportScreen();
	    BaseScreen userAccountScreen = loadUserAccountScreen();
	    BaseScreen cuentasFondosAsociadas = loadCuentasFormacionFondos();
	    BaseScreen clientSaldoSuperior = loadclientSaldoSuperior();
	    BaseScreen entidadMasContratos = loadentidadMasContratos();
	    BaseScreen cajerosSinSaldo = loadCjerosSinSaldo();
	    
	    mainScreen.setName("MainScreen");
	    clientScreen.setName("ClientScreen");
	    contratoScreen.setName("ContratoScreen");
	    agencyScreen.setName("AgencyScreen");
	    reportScreen.setName("ReportScreen");
	    userAccountScreen.setName("Cuentas del Cliente");
	    cuentasFondosAsociadas.setName("CuentasFFondosAsociadas");
	    clientSaldoSuperior.setName("ClienteConSaldoSuperior");
	    entidadMasContratos.setName("EntidadMasContratos");
	    cajerosSinSaldo.setName("CajerosSinSaldo");

	    contentPane.add(mainScreen, "MainScreen");
	    contentPane.add(clientScreen, "ClientScreen");
	    contentPane.add(contratoScreen, "ContratoScreen");
	    contentPane.add(agencyScreen, "AgencyScreen");
	    contentPane.add(reportScreen, "ReportScreen");
	    contentPane.add(userAccountScreen, "Cuentas del Cliente");
	    contentPane.add(cuentasFondosAsociadas, "CuentasFFondosAsociadas");
	    contentPane.add(clientSaldoSuperior, "ClienteConSaldoSuperior");
	    contentPane.add(entidadMasContratos, "EntidadMasContratos");
	    contentPane.add(cajerosSinSaldo, "CajerosSinSaldo");

	    cardLayout.show(contentPane, "MainScreen");
	}


	
	//El router para desplazarse entre las views
	
	private void Router(ActionEvent e) {
	    String command = e.getActionCommand();

	    if ("Principal".equals(command)) {
	        cardLayout.show(contentPane, "MainScreen");
	    } else if ("Clientes".equals(command)) {
	        cardLayout.show(contentPane, "ClientScreen");
	    } else if ("Contratos".equals(command)) {
	        cardLayout.show(contentPane, "ContratoScreen");
	    } else if ("Agencias".equals(command)) {
	        cardLayout.show(contentPane, "AgencyScreen");
	    } else if ("Reportes".equals(command)) {
	        // En lugar de mostrar la pantalla de reportes, puedes abrir un men� aqu�
	        mostrarMenuReportes(e); // Nueva funci�n para mostrar el men� de reportes
	    } else if ("Cuentas del Cliente".equals(command)) {
	        cardLayout.show(contentPane, "Cuentas del Cliente");

	        for (Component component : contentPane.getComponents()) {
	            if (component.getName().equals("Cuentas del Cliente")) {
	                UserAccountScreen cuentasDelClienteScreen = (UserAccountScreen) component;
	                cuentasDelClienteScreen.refreshContent();
	                break;
	            }
	        }
	    } else if ("CuentasFFondosAsociadas".equals(command)) {
	        cardLayout.show(contentPane, "CuentasFFondosAsociadas");
	    } else if ("ClienteConSaldoSuperior".equals(command)) {
	        cardLayout.show(contentPane, "ClienteConSaldoSuperior");
	    } else if ("EntidadMasContratos".equals(command)) {
	        cardLayout.show(contentPane, "EntidadMasContratos");
	    } else if ("CajerosSinSaldo".equals(command)) {
	        cardLayout.show(contentPane, "CajerosSinSaldo");
	    }
	}

	private void mostrarMenuReportes(ActionEvent e) {
	    // Crear un popup menu
	    JPopupMenu reportMenu = new JPopupMenu();

	    // Crear las opciones del men�
	    JMenuItem clienteSaldoSuperior = new JMenuItem("Clientes con saldo superior");
	    JMenuItem entidadMasContratos = new JMenuItem("Entidad con m�s contratos");
	    JMenuItem cuentasFFondosAsociadas = new JMenuItem("Cuentas FF Asociadas");
	    JMenuItem cajerosSinSaldo = new JMenuItem("Cajeros sin saldo");

	    // A�adir ActionListeners a las opciones del men�
	    clienteSaldoSuperior.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            cardLayout.show(contentPane, "ClienteConSaldoSuperior");
	        }
	    });
	    
	    entidadMasContratos.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            cardLayout.show(contentPane, "EntidadMasContratos");
	        }
	    });
	    
	    cuentasFFondosAsociadas.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            cardLayout.show(contentPane, "CuentasFFondosAsociadas");
	        }
	    });
	    
	    cajerosSinSaldo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            cardLayout.show(contentPane, "CajerosSinSaldo");
	        }
	    });

	    
	    reportMenu.add(clienteSaldoSuperior);
	    reportMenu.add(entidadMasContratos);
	    reportMenu.add(cuentasFFondosAsociadas);
	    reportMenu.add(cajerosSinSaldo);

	    // Mostrar el popup donde se hizo clic
	    Component source = (Component) e.getSource();
	    reportMenu.show(source, source.getWidth() / 2, source.getHeight());
	}

	
	
    //Crear las llamadas a las Pantallas
	
	private BaseScreen loadMainScreen() {
		
	    return new MainScreen(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Router(e);
	        }
	    });
	}
	private BaseScreen loadClientScreen() {
			
		    return new ClientScreen(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            Router(e);
		        }
		    });
		}
    
    private BaseScreen loadContratoScreen() {
    	
    	return new ContratoScreen(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Router(e);
	        }
	    });
    }
    
    private BaseScreen loadAgencyScreen() {
    	
    	return new AgencyScreen(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Router(e);
	        }
	    });
    }
    
    private BaseScreen loadReportScreen() {
    	
    	return new ReportsScreen(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Router(e);
	        }
	    });
    }
    
    private BaseScreen loadUserAccountScreen() {
    	
    	return new UserAccountScreen(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Router(e);
	        }
	    });
    }
    
    private BaseScreen loadCuentasFormacionFondos() {
	    return new CuentasFFondosAsociadas(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Router(e);
	        }
	    });
	}
	
	private BaseScreen loadclientSaldoSuperior() {
	    return new ClienteConSaldoSuperior(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Router(e);
	        }
	    });
	}
    
	private BaseScreen loadentidadMasContratos() {
	    return new EntidadMasContratos(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Router(e);
	        }
	    });
	}
	
	
	
	private BaseScreen loadCjerosSinSaldo() {
	    return new CajerosSinSaldo(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            Router(e);
	        }
	    });
	}
	
	
}