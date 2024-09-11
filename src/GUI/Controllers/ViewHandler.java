package GUI.Controllers;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import GUI.Components.BaseScreen;
import GUI.Views.AccountsScreen;
import GUI.Views.AgencyScreen;
import GUI.Views.CajerosSinSaldo;
import GUI.Views.ClientScreen;
import GUI.Views.ClienteConSaldoSuperior;
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
	    BaseScreen accountScreen = loadAccountScreen();
	    BaseScreen agencyScreen = loadAgencyScreen();
	    BaseScreen reportScreen = loadReportScreen();
	    BaseScreen userAccountScreen = loadUserAccountScreen();
	    BaseScreen cuentasFondosAsociadas = loadCuentasFormacionFondos();
	    BaseScreen clientSaldoSuperior = loadclientSaldoSuperior();
	    BaseScreen entidadMasContratos = loadentidadMasContratos();
	    BaseScreen cajerosSinSaldo = loadCjerosSinSaldo();
	    
	    mainScreen.setName("MainScreen");
	    clientScreen.setName("ClientScreen");
	    accountScreen.setName("AccountScreen");
	    agencyScreen.setName("AgencyScreen");
	    reportScreen.setName("ReportScreen");
	    userAccountScreen.setName("Cuentas del Cliente");
	    cuentasFondosAsociadas.setName("CuentasFFondosAsociadas");
	    clientSaldoSuperior.setName("ClienteConSaldoSuperior");
	    entidadMasContratos.setName("EntidadMasContratos");
	    cajerosSinSaldo.setName("CajerosSinSaldo");

	    contentPane.add(mainScreen, "MainScreen");
	    contentPane.add(clientScreen, "ClientScreen");
	    contentPane.add(accountScreen, "AccountScreen");
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
	    } else if ("Cuentas".equals(command)) {
	        cardLayout.show(contentPane, "AccountScreen");
	    } else if ("Agencias".equals(command)) {
	        cardLayout.show(contentPane, "AgencyScreen");
	    } else if ("Reportes".equals(command)) {
	        cardLayout.show(contentPane, "ReportScreen");
	    }
	    else if ("Cuentas del Cliente".equals(command)) {
	        cardLayout.show(contentPane, "Cuentas del Cliente");

	        for (Component component : contentPane.getComponents()) {
	            if (component.getName().equals("Cuentas del Cliente")) {
	                UserAccountScreen cuentasDelClienteScreen = (UserAccountScreen) component;
	                cuentasDelClienteScreen.refreshContent();
	                break;
	            }
	        }
	    } else if ("CuentasFFondosAsociadas".equals(command)) {
	        // Aquí cargamos la pantalla de últimas operaciones
	        cardLayout.show(contentPane, "CuentasFFondosAsociadas");
	    }
	    else if ("ClienteConSaldoSuperior".equals(command)){
	    	cardLayout.show(contentPane, "ClienteConSaldoSuperior");
	    }
	    else if ("EntidadMasContratos".equals(command)){
	    	cardLayout.show(contentPane, "EntidadMasContratos");
	    }
	    else if ("CajerosSinSaldo".equals(command)){
	    	cardLayout.show(contentPane, "CajerosSinSaldo");
	    }
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
    
    private BaseScreen loadAccountScreen() {
    	
    	return new AccountsScreen(new ActionListener() {
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