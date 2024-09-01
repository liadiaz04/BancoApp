package Clases;

import java.util.ArrayList;
import java.util.Random;

public class Banco {
	
	private static Banco instancia; 
    private ArrayList<Cliente> clientes;
    private ArrayList<CuentaBancaria> cuentas;
    private ArrayList<Agencia> agencias;

    // CONSTRUCTOR
    public Banco() {
        this.clientes = new ArrayList<Cliente>(); 
        this.cuentas = new ArrayList<CuentaBancaria>(); 
        this.agencias = new ArrayList<Agencia>();
        
        loadTestUsers(); 
        crearCuentasBancarias();
    }
    
    // METODO PARA OBTENER LA INTANCIA UNICA
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
     
     //AGENCIAS 
    
     public void agregarAgencia(String gerente , String direccion) {
         int numeroAgencia = this.agencias.size() + 1;
         String idAgencia = String.format("Ag%02d", numeroAgencia);
         agencias.add(new Agencia(idAgencia, gerente, direccion));
        
     }
     
     public boolean eliminarAgencia(String idAgencia) {
         boolean eliminado = false; 
         
         for (int i = 0; i < clientes.size(); i++) {
             if (clientes.get(i).getIdCliente().equals(idAgencia)) {
                 clientes.remove(i);
                 eliminado = true; 
             }
         }

         return eliminado; 
     }
     

    
     
     
     
     
     
     //REPORTE 1
     //OBTENER EL SALDO DE TODAS LAS CUENTAS DE UN CLIENTE
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
  
     //FUNCIONES DE PRUEBA DE DATOS
     private void loadTestUsers() {
    	    addCliente("04040178174", "Calle A 1", "Juan", "12345678", "juan.perez@gmail.com");
    	    addCliente("03040178175", "Calle B 2", "Maria", "23456789", "maria.lopez@gmail.com");
    	    addCliente("05040178176", "Calle C 3", "Carlos", "34567890", "carlos.garcia@gmail.com");
    	    addCliente("06040178177", "Calle D 4", "Ana", "45678901", "ana.martinez@gmail.com");
    	    addCliente("07040178178", "Calle E 5", "Luis", "56789012", "luis.rodriguez@gmail.com");
    	}
    
     private void crearCuentasBancarias() {
    	    
         C_MLC cuenta1 = new C_MLC("001", 1000.0, "Beneficiario1", "MLC");
         C_MLC cuenta2 = new C_MLC("002", 2000.0, "Beneficiario2", "MLC");
         C_MLC cuenta3 = new C_MLC("003", 3000.0, "Beneficiario3", "MLC");
         C_MLC cuenta4 = new C_MLC("001", 1000.0, "Beneficiario1", "MLC");
         C_MLC cuenta5 = new C_MLC("002", 2000.0, "Beneficiario2", "MLC");
         C_MLC cuenta6 = new C_MLC("003", 3000.0, "Beneficiario3", "MLC");
         
         cuentas.add(cuenta3);
         cuentas.add(cuenta2);
         cuentas.add(cuenta1);
         cuentas.add(cuenta4);
         cuentas.add(cuenta5);
         cuentas.add(cuenta6);
	} 
     
     
     //DADO UN CLIENTE RETORNA TODAS SUS CUENTAS 
     public ArrayList<CuentaBancaria> getCuentasDadoCliente(String id) {
    	 
    	 ArrayList<CuentaBancaria> cuentasUsuario = new ArrayList<CuentaBancaria>();
    	 Cliente aux = buscarClientePorId(id);
    	 if(aux != null){
    		 cuentasUsuario = aux.getCuentas();
    	 }
    	 return cuentasUsuario;
     }


    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    public ArrayList<CuentaBancaria> getCuentas() {
        return cuentas;
    }
    
   
    
    // FUNCION PARA LA GENERACION DE NUMEROS DE CUENTAS

    public static String generarNumeroCuenta(String tipoCuenta) {
    	String primerosCuatroDigitos = "";

    	switch (tipoCuenta.toLowerCase()) {
    	case "corriente":
    	case "plazo fijo":
    		primerosCuatroDigitos = "9205";
    		break;
    	case "formacion de fondos":
    		primerosCuatroDigitos = "9227";
    		break;
    	case "mlc":
    		primerosCuatroDigitos = "9235";
    		break;
    	default:
    		throw new IllegalArgumentException("Tipo de cuenta desconocido: " + tipoCuenta);
    	}

    	String siguientesCuatroDigitos = "9598";

    	String ultimosOchoDigitos = generarDigitosAleatorios(8);


    	return primerosCuatroDigitos + siguientesCuatroDigitos + ultimosOchoDigitos;
    }


    private static String generarDigitosAleatorios(int longitud) {
    	Random random = new Random();
    	StringBuilder sb = new StringBuilder();

    	for (int i = 0; i < longitud; i++) {
    		int digito = random.nextInt(10); 
    		sb.append(digito);
    	}

    	return sb.toString();
    }
 
 
    

}
