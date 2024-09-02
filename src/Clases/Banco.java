package Clases;

import java.util.ArrayList;

public class Banco {
	
	private static Banco instancia; // Instancia única
    private ArrayList<Cliente> clientes;
    private ArrayList<CuentaBancaria> cuentas;
    private ArrayList<Agencia> agencias;

    // Constructor
    public Banco() {
        this.clientes = new ArrayList<Cliente>(); 
        this.cuentas = new ArrayList<CuentaBancaria>(); 
        this.agencias = new ArrayList<Agencia>();
        
        loadTestUsers(); //Carga los usuarios de prueba
        crearCuentasBancarias(); //Carga las cuentas de prueba
    }
    
   

	// Método para obtener la instancia única
    public static Banco getInstancia() {
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }
    
   
    
    
    //CLIENTES

    public void addCliente(String idCliente, String nombre, String direccion, String telefono, String correo) {
        if (buscarClientePorId(idCliente) == null) {
            Cliente cliente = new Cliente(idCliente, direccion, nombre, telefono, correo);
            clientes.add(cliente);
        } else {
            System.out.println("El cliente con ID " + idCliente + " ya existe.");
        }
    }
    
    public Cliente buscarClientePorId(String idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(idCliente)) {
                return cliente;
            }
        }
        return null; 
    }
    
     public boolean eliminarCliente(String idCliente) {
        boolean eliminado = false; 
        
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(idCliente)) {
                clientes.remove(i);
                eliminado = true; 
            }
        }

        return eliminado; 
    }
     
     //Reporte 1
     //Obtener el saldo de todas las cuentas de un cliente
     public ArrayList<String> obtenerSaldosCliente(String idCliente) {
         ArrayList<String> saldos = new ArrayList<String>(); 
         Cliente cliente = buscarClientePorId(idCliente);
         
         if (cliente != null) {
             saldos = cliente.obtenerSaldosPorCuenta(); 
         } else {
             System.out.println("Cliente no encontrado.");
         }
         
         return saldos; 
     }
  

     private void loadTestUsers() {
    	    addCliente("04040178174", "Calle A 1", "Juan", "12345678", "juan.perez@gmail.com");
    	    addCliente("03040178175", "Calle B 2", "Maria", "23456789", "maria.lopez@gmail.com");
    	    addCliente("05040178176", "Calle C 3", "Carlos", "34567890", "carlos.garcia@gmail.com");
    	    addCliente("06040178177", "Calle D 4", "Ana", "45678901", "ana.martinez@gmail.com");
    	    addCliente("07040178178", "Calle E 5", "Luis", "56789012", "luis.rodriguez@gmail.com");
    	}
    
     private void crearCuentasBancarias() {
    	    
         C_MLC cuenta1 = new C_MLC("001", 1000.0, "Beneficiario1", "MLC", "Titular1");
         C_MLC cuenta2 = new C_MLC("002", 2000.0, "Beneficiario2", "MLC", "Titular2");
         C_MLC cuenta3 = new C_MLC("003", 3000.0, "Beneficiario3", "MLC", "Titular3");
         C_MLC cuenta4 = new C_MLC("001", 1000.0, "Beneficiario1", "MLC", "Titular1");
         C_MLC cuenta5 = new C_MLC("002", 2000.0, "Beneficiario2", "MLC", "Titular2");
         C_MLC cuenta6 = new C_MLC("003", 3000.0, "Beneficiario3", "MLC", "Titular3");
         
         cuentas.add(cuenta3);
         cuentas.add(cuenta2);
         cuentas.add(cuenta1);
         cuentas.add(cuenta4);
         cuentas.add(cuenta5);
         cuentas.add(cuenta6);
	} 
     
     private void crearAgencias() {
    	    Agencia agencia1 = new Agencia(1, "Gerente A", "Calle Principal 1");
    	    Agencia agencia2 = new Agencia(2, "Gerente B", "Calle Norte 2");
    	    Agencia agencia3 = new Agencia(3, "Gerente C", "Calle Sur 3");
    	    Agencia agencia4 = new Agencia(4, "Gerente D", "Calle Este 4");
    	    Agencia agencia5 = new Agencia(5, "Gerente E", "Calle Oeste 5");

    	    agencias.add(agencia1);
    	    agencias.add(agencia2);
    	    agencias.add(agencia3);
    	    agencias.add(agencia4);
    	    agencias.add(agencia5);
    	}

     
     
     //Dado un cliente retorna todas sus cuentas
     public ArrayList<CuentaBancaria> getCuentasDadoCliente(String nombreCliente) {
    	 
    	 ArrayList<CuentaBancaria> cuentasUsuario = new ArrayList<CuentaBancaria>();
    	 CuentaBancaria cuentaAcutal;
    	 int size = this.cuentas.size();
    	 
    	 for( int i=0; i<size; i++) {
    		 cuentaAcutal = this.cuentas.get(i);
    		 
    		 if (cuentaAcutal.tieneCuenta(nombreCliente))
    			 cuentasUsuario.add(cuentaAcutal);
    	 }
    	 
    	 return cuentasUsuario;
     }


    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    public ArrayList<CuentaBancaria> getCuentas() {
        return cuentas;
    }
    
    public ArrayList<Agencia> getAgencias() {
		return agencias;
	}

}
