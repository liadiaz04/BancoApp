package Logic;

import java.util.ArrayList;
import java.util.Random;

public class Banco {
	
	private static Banco instancia; 
	private ArrayList<Contrato> contratos;
    private ArrayList<Cliente> clientes;
    private ArrayList<CuentaBancaria> cuentas;
    private ArrayList<Agencia> agencias;
    private ArrayList<User> usuarios;
    private ArrayList<Plazo_Deposito> plazos;

    
    // CONSTRUCTOR
    
    public Banco() {
        this.usuarios = new ArrayList<User>();
        this.clientes = new ArrayList<Cliente>(); 
        this.cuentas = new ArrayList<CuentaBancaria>(); 
        this.agencias = new ArrayList<Agencia>();
        this.plazos = new ArrayList <Plazo_Deposito> ();
        this.contratos = new ArrayList<>(); 
        
       Inicializadora.inicializar(cuentas,clientes, usuarios, contratos, agencias, plazos);
      
    }
     
    	
    public  void loadCuentas() {
		clientes.get(0).getCuentas().add(new C_Corriente("9205959800000012","hijo","CUP"));
		clientes.get(0).getCuentas().add(new C_Corriente("9205959845987012","hermano","CUP"));
      
	}
    	 

    //FUNCIONES DE PRUEBA DE DATOS
    
/*public  void inicializar(ArrayList<Cliente> clientes,ArrayList<User> usuarios,ArrayList<Contrato> contratos,ArrayList<Agencia> agencias, ArrayList<Plazo_Deposito> plazos,ArrayList<CuentaBancaria> cuentas ){
    	
		loadUsers(usuarios);
    	loadAgencias(agencias);
    	ArrayList<Contrato> c = loadContratos(contratos);
    	ArrayList<Plazo_Deposito> p =loadPlazos(plazos);
    	loadCuentas(cuentas,c,p);	
    	loadClientes(clientes);
    }     
    
  */  
   
	public void crearContratos(){
    	try{
    		contratos.add(new Contrato("2122","Entidad",5,6000.5));
    	}catch(Exception e){
    		//manejar error
    	}
    	
    }
    public static Banco getInstancia() {
        if (instancia == null) {
            instancia = new Banco();
        }
        return instancia;
    }
    
    public ArrayList<Plazo_Deposito> getPlazos(){
    	return plazos;
    }
    
    public Plazo_Deposito buscarPlazoDeposito (int cantMeses){
    	Plazo_Deposito aux = null;
        for (int i = 0 ; i < plazos.size() && aux == null; i++ ) {
            if (plazos.get(i).getMeses() == cantMeses) {
               aux = plazos.get(i);
            }
        }
        return aux; 
    }
    
   //USUARIOS
    
    public void usuariosValidos(){
    	usuarios.add(new User("Banco", "12345"));
    }
    
    public boolean authenticateUser(String username,String  password){
    	boolean found = false;
     for(int i = 0 ; i < usuarios.size() && !found ; i++){
    	 User user = usuarios.get(i);
    	 if(user.getUsername().equals(username) && user.getPassword().equals(password)){
    		 found = true;
    	 }
     }
     return found;
    }
  
    //CLIENTES
    
    public Cliente buscarClientePorId(String idCliente) {
    	Cliente aux = null;
    	for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(idCliente)) {
                aux = clientes.get(i);
            }
        }
        return aux; 
    }
    

    public void addCliente(String idCliente, String nombre, String direccion, String telefono, String correo) {
        if (buscarClientePorId(idCliente) == null) {
            Cliente cliente = new Cliente(idCliente, direccion, nombre, telefono, correo);
            clientes.add(cliente);
        } else {
        	throw new IllegalArgumentException("El cliente con ID " + idCliente + " ya existe.");
        }
    }
    
     public boolean eliminarCliente(String idCliente) {
        boolean eliminado = false; 
        
        for (int i = 0; i < clientes.size() && !eliminado; i++) {
            if (clientes.get(i).getIdCliente().equals(idCliente)) {
                clientes.remove(i);
                eliminado = true; 
            }
        }

        return eliminado; 
    }

     
     
     //CONTRATOS 
     
     public ArrayList<Contrato> getContratos() {
 		return contratos;
 	}
     
     public Contrato buscarContratoPorId(String idContrato) {
    	 int size=contratos.size();
    	System.out.println("entra a buscar el contrato en la lista de tamanno" + size);
      	Contrato aux = null;
          for (int i = 0 ; i < contratos.size() && aux == null; i++ ) {
              if (contratos.get(i).getIdContrato().equals(idContrato)) {
                 aux = contratos.get(i);
                 System.out.println("encontro contrato con id" + aux.getIdContrato());
              }
          }
          return aux; 
      }
     
     public void agregarContrato (String entidad, int periodoTiempo , double salario ){
    	 boolean existe= contratoExistente(entidad, periodoTiempo, salario);
    	 if(!existe){
    		 String idContrato = generarDigitosAleatorios(5);
    		 contratos.add(new Contrato (idContrato,entidad,periodoTiempo,salario));
    	 }
     }
     
     
     

     public boolean contratoExistente(String entidad, int periodoTiempo , double salario ){
    	 boolean found = false;

    	 for(int i = 0; i < contratos.size() && !found ; i++){
    		 Contrato c= contratos.get(i);
    		 if((c.getEntidad().equalsIgnoreCase(entidad))&& (c.getPeriodoTiempo()== periodoTiempo) && (c.getSalario()== salario)){
    			 found = true;
    		 }
    	 }
    	 return found;
     }
     
     

     //AGENCIAS 
     
     public ArrayList<Agencia> getAgencias() {
 		return agencias;
 	}
  
     
     public Agencia buscarAgenciaPorId(String idAgencia) {
     	Agencia aux = null;
         for (int i = 0 ; i < agencias.size() && aux == null; i++ ) {
             if (agencias.get(i).getIdAgencia().equals(idAgencia)) {
                aux = agencias.get(i);
             }
         }
         return aux; 
     }
     


    
     public void agregarAgencia(String gerente, String direccion) {
    	    String numeroAgencia = generarDigitosAleatorios(2);
    	    int numeroAgenciaInt = Integer.parseInt(numeroAgencia);
    	    String idAgencia = String.format("Ag%02d", numeroAgenciaInt);
    	    agencias.add(new Agencia(idAgencia, gerente, direccion));
    	}
     
     public boolean eliminarAgencia(String idAgencia) {
         boolean eliminado = false; 
         
         for (int i = 0; i < agencias.size(); i++) {
             if (agencias.get(i).getIdAgencia().equals(idAgencia)) {
                 agencias.remove(i);
                 eliminado = true; 
             }
         }

         return eliminado; 
     }
     

    //CUENTAS
     
     public CuentaBancaria buscarCuentaBancariaPorNo(String noCuenta) {
    	 
    	 CuentaBancaria aux = null;
      
    	 for (int i = 0 ; i < cuentas.size() && aux == null; i++ ) {
             if (cuentas.get(i).getNoCuenta().equals(noCuenta)) {
                aux = cuentas.get(i);
             }
         }
         return aux; 
     }
     
     public void agregarCuentaFormacionFondos(Cliente cliente,String beneficiario, String idcontrato , String tipo){
    	 
    	 String numCuenta = generarNumeroCuenta (tipo);
    	
    	 Contrato c = buscarContratoPorId(idcontrato);
    	
    	 if(c != null){
    		 
			 C_Formacion_Fondos aux = new C_Formacion_Fondos (numCuenta,beneficiario,"CUP",c);
			
			 cuentas.add(aux);
			 cliente.agregarCuenta(aux,tipo);
		 }else 
			 throw new IllegalArgumentException("Contrato no encontrado");
     }
     
     public void agregarCuentaCorriente(Cliente cliente,String beneficiario, String tipo){
    	 String numCuenta = generarNumeroCuenta (tipo);
    	 C_Corriente aux = new C_Corriente (numCuenta,beneficiario,"CUP");
		 cuentas.add(aux);
		 cliente.agregarCuenta(aux,tipo);
     }
     
     public void agregarCuentaMLC(Cliente cliente,String beneficiario, String tipo){
    	 String numCuenta = generarNumeroCuenta (tipo);
    	 C_MLC aux= new C_MLC(numCuenta, beneficiario, "MLC" );
		 cuentas.add(aux);
		 cliente.agregarCuenta(aux,tipo);
     }
    
     public void agregarCuentaPlazoFijo(Cliente cliente,String beneficiario,double cantInicial,int plazo,String tipo){
    	 String numCuenta = generarNumeroCuenta (tipo);
    	 Plazo_Deposito p = buscarPlazoDeposito(plazo);
    	 if(p != null){
    		 C_Plazo_Fijo aux = new  C_Plazo_Fijo (numCuenta,beneficiario,"MLC",p,cantInicial);
    		 cuentas.add(aux);
    		 cliente.agregarCuenta(aux,tipo);
    	 }else
    		 throw new IllegalArgumentException("Plazo de mes no valido");
     }
    
     public ArrayList<CuentaBancaria> getCuentasCliente(Cliente cliente) {
 		ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
 		
 		cuentas.addAll(cliente.getCuentas());
 		
 		
 		return cuentas;
 	}
 	
     //CONTRATOS
     public Contrato buscarContratoporEntidad(String entidad) {
     	Contrato aux = null;
         for (int i = 0 ; i < contratos.size() && aux != null; i++ ) {
             if (contratos.get(i).getEntidad().equals(entidad)) {
                aux = contratos.get(i);
             }
         }
         return aux; 
     }
     
     
     public boolean eliminarContrato(String idContrato) {
    	    boolean delete = false;
    	    for (int i = 0; i < contratos.size() && !delete; i++) {
    	        if (contratos.get(i).getIdContrato().equals(idContrato)) {
    	            contratos.remove(i);
    	            delete = true; 
    	        }
    	    }
    	    return delete; 
    	}

     
    
    
     private void crearAgencias() {
    	    Agencia agencia1 = new Agencia("A001", "Gerente1", "Direccion1");
    	    Agencia agencia2 = new Agencia("A002", "Gerente2", "Direccion2");
    	    Agencia agencia3 = new Agencia("A003", "Gerente3", "Direccion3");
    	    Agencia agencia4 = new Agencia("A004", "Gerente4", "Direccion4");
    	    Agencia agencia5 = new Agencia("A005", "Gerente5", "Direccion5");

    	    agencias.add(agencia1);
    	    agencias.add(agencia2);
    	    agencias.add(agencia3);
    	    agencias.add(agencia4);
    	    agencias.add(agencia5);
    	    

            Cajero cajero1 = new Cajero("C001");
            Cajero cajero2 = new Cajero("C002");
            Cajero cajero3 = new Cajero("C003");
            Cajero cajero4 = new Cajero("C004");
            Cajero cajero5 = new Cajero("C005");
            Cajero cajero6 = new Cajero("C006"); 

            agencia1.getCajeros().add(cajero1);
            agencia1.getCajeros().add(cajero2);
            agencia2.getCajeros().add(cajero3);
            agencia2.getCajeros().add(cajero4);
            agencia3.getCajeros().add(cajero5);
            agencia5.getCajeros().add(cajero6);
            
            
            
    	}

     public void inicializarCajerosConSaldoCero() {
    	    for (Agencia agencia : agencias) {
    	        for (Cajero cajero : agencia.getCajeros()) {
    	            cajero.getBilletes().clear();  
    	        }
    	    }
    	}

     
    /* private void inicializarBilletes() {
    	    // Definir los billetes que se van a agregar
    	    int[] cantidades = {3, 20, 10}; // Cantidades de billetes de 500, 100 y 50
    	    TipoBillete[] tipos = {TipoBillete.Quiniento, TipoBillete.Cien, TipoBillete.Cincuenta};

    	    // Asignar billetes a cada cajero
    	    for (Agencia agencia : agencias) {
    	        for (Cajero cajero : agencia.getCajeros()) {
    	            for (int i = 0; i < tipos.length; i++) {
    	                Billete billete = new Billete(tipos[i], cantidades[i]);
    	                cajero.getBilletes().add(billete);
    	            }
    	        }
    	    }
     }*/

     
 	   
     
    

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

    	switch (tipoCuenta) {
    	case "Corriente":
    	case "Plazo Fijo":
    		primerosCuatroDigitos = "9205";
    		
    		break;
    	case "Formacion de Fondos":
    		primerosCuatroDigitos = "9227";
    		break;
    	case "MLC":
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


 
  // REPORTES 
   
    // 1.	VER ULTIMAS OPERACIONES DE UNA CUENTA 
    
    public ArrayList <Operacion> ultimasOperacionesUnCuenta (String numeroCuenta){
    	ArrayList <Operacion> operaciones = new ArrayList <Operacion> ();
    	CuentaBancaria aux = buscarCuentaBancariaPorNo (numeroCuenta);
    	if(aux != null){
    		operaciones = aux.getOperaciones();
    	}
    	return operaciones;
    }
    
   // 2. LISTA DE CLIENTES CON AL MENOS UNA CUENTA CON SALDO SUPERIOR A UN VALOR DADO 
    
    public ArrayList <Cliente> clientesConCuentaSuperiorASaldoDado (double saldoDado){

    	ArrayList <Cliente> aux = new ArrayList <Cliente> ();

    	for(Cliente c : clientes){
    		boolean found = false;
    		for (int j = 0 ; j < c.getCuentas().size() && !found ; j ++){
    			if(c.getCuentas().get(j).getSaldo() > saldoDado ){
    				aux.add(c);
    				found = true;
    			}
    		}
    	}

    	return aux;
    }

    
  // 3. TITULARES DE UNA CUENTA CORRIENTE DADA 
    
    public ArrayList <Cliente> titularesDeCuentaCorrienteDada (String numeroCuenta){

    	ArrayList <Cliente> titulares = new ArrayList <Cliente> ();

    	for(Cliente c : clientes){

    		boolean found = false;

    		for (int j = 0 ; j < c.getCuentas().size() && !found ; j ++){
    			
    			CuentaBancaria x = c.getCuentas().get(j);
    			if( x instanceof C_Corriente){
    				if(x.getNoCuenta().equals(numeroCuenta)){
    					titulares.add(c);
    					found = true;
    				}
    			}
    		}
    	}

    	return titulares ;
    }
    
  // 4. ENTIDAD CON MAYOR CANTIDAD DE CONTRATOS CON EL BANCO 
    
    public String entidadConMasContratos() {
        ArrayList<String> entidades = new ArrayList<>();
        ArrayList<Integer> conteos = new ArrayList<>();

        for (int i = 0; i < contratos.size(); i++) {
            String entidad = contratos.get(i).getEntidad();

            if (entidades.contains(entidad)) {
                int index = entidades.indexOf(entidad);
                conteos.set(index, conteos.get(index) + 1);
            } else {
                entidades.add(entidad);
                conteos.add(1);
            }
        }

        int posicionMaxima = encontrarPosicionMaxima(conteos);
        String entidad = entidades.get(posicionMaxima);
        int cantidadContratos = conteos.get(posicionMaxima);

        return entidad + " - " + cantidadContratos + " contratos";  
    }

    private int encontrarPosicionMaxima(ArrayList<Integer> conteos) {
        int maxPosicion = 0;
        int maxValor = 0;

        for (int i = 0; i < conteos.size(); i++) {
            if (conteos.get(i) > maxValor) {
                maxValor = conteos.get(i);
                maxPosicion = i;
            }
        }

        return maxPosicion;
    }

   

   // 5. TODAS LAS CUENTAS DE FORMACION DE FONDOS ASOCIADAS A UNA ENTIDAD DADA 

    public ArrayList<CuentaBancaria> cuentasFormacionFondosDeEntidadDada (String entidad){
    	ArrayList<CuentaBancaria> cuentasFF = new  ArrayList<CuentaBancaria>();

    	for(CuentaBancaria c : cuentas){
    		if(c instanceof C_Formacion_Fondos){
    			if(((C_Formacion_Fondos)c).getContrato().getEntidad().equalsIgnoreCase(entidad)){
    				cuentasFF.add(c);
    			}
    		}
    	}

    	return cuentasFF;
    }
    

    // 6.CAJEROS CON SALDO INSUFICIENTE DE CADA AGENCIA
    public ArrayList<Cajero> cajerosConSaldoInsuficiente (){

    	ArrayList<Cajero> cajerosSinSaldo = new ArrayList<>();

    	for(Agencia a : agencias){
    		for (Cajero c : a.getCajeros()){
    			if(c.mostrarSaldoTotal()==0){
    				cajerosSinSaldo.add(c);
    			}
    		}
    	}
    	return cajerosSinSaldo;
    }
    
    public String obtenerAgenciaPorCajero(String idCajero) {
    	String idAgencia = null;
    	
        for (Agencia agencia : agencias) {
            for (Cajero cajero : agencia.getCajeros()) {
                if (cajero.getIdCajero().equals(idCajero)) {
                    idAgencia = agencia.getIdAgencia(); 
                }
            }
        }
        return idAgencia; 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    


}