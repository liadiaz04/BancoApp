package Clases;

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
        
        loadTestUsers(); 
        crearCuentasBancarias();
        crearAgencias();
        //inicializarBilletes(); 
        inicializarCajerosConSaldoCero();
        inicializar(); 
    }
     
    	
    	
    	
    

    //FUNCIONES DE PRUEBA DE DATOS
    
    public void inicializar(){
    	loadUsers();
    	loadClientes();
    	loadCuentas();
    	loadAgencias();
    	loadPlazos();
    	loadContratos();
    }   
    
    private void loadContratos() {
		
		
	}

	private void loadAgencias() {
		
	}

	private void loadPlazos() {
	
		
	}

	private void loadCuentas() {


	}


	public void loadClientes() {
        String[] nombres = {"Juan P�rez", "Mar�a Garc�a", "Carlos L�pez", "Ana Rodr�guez",
                            "Pedro Mart�nez", "Sof�a Hern�ndez", "Luis Fern�ndez", "Eva G�mez",
                            "Tom�s D�az", "Isabel S�nchez", "Antonio Moreno", "Cristina Santos",
                            "David Gonz�lez", "Lucia �lvarez", "Javier Torres", "Laura Jim�nez",
                            "Miguel Fern�ndez", "Natalia G�mez", "Alejandro Moreno", "Patricia Hern�ndez"};

        String[] direcciones = {"Calle 123, 45678 Ciudad", "Avenida Principal, 78900 Poblaci�n",
                                "Paseo Mar�timo, 10111 Costa", "Plaza Central, 23456 Capital",
                                "Carretera Nacional, 56789 Provincia", "Avda. de la Constituci�n, 89012 Municipio",
                                "Ctra. de la Libertad, 34567 Comunidad", "Pza. del Ayuntamiento, 67890 Localidad",
                                "Calle de las Flores, 24680 Distrito", "Ave. de los Hombres Ilustres, 13579 Barrio",
                                "Paseo del R�o, 54321 Sector", "Calle de la Rep�blica, 98765 Zona"};

        String[] telefonos = {"12345678", "98765432", "55511122", "44433300",
                              "66677788", "99900011", "22233344", "55566677",
                              "88899900", "12345678", "98765432", "55511122",
                              "44433300", "66677788", "99900011", "22233344",
                              "55566677", "88899900", "12345678", "98765432"};

        String[] emails = {"juan.perez@email.com", "maria.garcia@email.com", "carlos.lopez@email.com",
                           "ana.rodriguez@email.com", "pedro.martinez@email.com", "sofia.hernandez@email.com",
                           "luis.fernandez@email.com", "eva.gomez@email.com", "tomas.diaz@email.com",
                           "isabel.sanchez@email.com", "antonio.moreno@email.com", "cristina.santos@email.com",
                           "david.gonzalez@email.com", "lucia.alvarez@email.com", "javier.torres@email.com",
                           "laura.jimenez@email.com", "miguel.fernandez@email.com", "natalia.gomez@email.com",
                           "alejandro.moreno@email.com", "patricia.hernandez@email.com"};
       
        String[] carnets ={"44050202340", "08030377632", "75071813361", "93072021983", "96030623213", "08110560210", "65081033529",
        		"67060931981", "31121557974", "80102140698", "67121145159", "44101738182", "06050265281", "52060654120",
        		"81040740818", "92030536779", "62071215367", "06110887030", "76032354972", "29082250105"};

        for (int i = 0; i < 20; i++) {
            
            clientes.add(new Cliente(carnets[i], nombres[i], direcciones[i % direcciones.length],
                                    telefonos[i % telefonos.length], emails[i]));
        }
    }

	public void loadUsers(){
    	
    }
    
   
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
        for (int i = 0 ; i < plazos.size() && aux != null; i++ ) {
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
        
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(idCliente)) {
                clientes.remove(i);
                eliminado = true; 
            }
        }

        return eliminado; 
    }
     
     
     //CONTRATOS 
     
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
     


    
     public void agregarAgencia(String gerente , String direccion) {
    	 String numeroAgencia = generarDigitosAleatorios(2);
         String idAgencia = String.format("Ag%02d", numeroAgencia);
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
     

     /*public void agregarContrato(String entidad, int periodo, double salario) {
         Contrato nuevoContrato = new Contrato(entidad, periodo, salario);
         contratos.add(nuevoContrato);
     }*/

     public boolean eliminarContrato(String entidad) {
    	 boolean delete = false;
         for (int i = 0; i < contratos.size() && !delete; i++) {
             if (contratos.get(i).getEntidad().equals(entidad)) {
                 contratos.remove(i);
                 delete = true; 
             }
         }
         return delete; 
     }
     
     
     //FUNCIONES DE PRUEBA DE DATOS
     private void loadTestUsers() {
    	    addCliente("04040178174", "Calle A 1", "Juan", "12345678", "juan.perez@gmail.com");
    	    addCliente("03040178175", "Calle B 2", "Maria", "23456789", "maria.lopez@gmail.com");
    	    addCliente("05040178176", "Calle C 3", "Carlos", "34567890", "carlos.garcia@gmail.com");
    	    addCliente("06040178177", "Calle D 4", "Ana", "45678901", "ana.martinez@gmail.com");
    	    addCliente("07040178178", "Calle E 5", "Luis", "56789012", "luis.rodriguez@gmail.com");
    	    
    	    C_MLC cuenta = new C_MLC("011", 1000.0, "Beneficiario1", "MLC");
    	    C_MLC cuenta7 = new C_MLC("011", 1500.0, "Beneficiario1", "MLC");
    	    clientes.get(0).agregarCuenta(cuenta);
    	    clientes.get(1).agregarCuenta(cuenta);
    	    clientes.get(2).agregarCuenta(cuenta);
    	    clientes.get(0).agregarCuenta(cuenta7);
    	    
    	  
    	        addCliente("04040178174", "Calle A 1", "Juan", "12345678", "juan.perez@gmail.com");
    	        addCliente("03040178175", "Calle B 2", "Maria", "23456789", "maria.lopez@gmail.com");
    	        contratos.add(new Contrato("Etecsa", 12, 2500)); // Cambia los par�metros seg�n sea necesario
    	        contratos.add(new Contrato("Etecsa", 12, 2500)); // Cambia los par�metros seg�n sea necesario
    	        contratos.add(new Contrato("Etecsa", 12, 2500)); // Cambia los par�metros seg�n sea necesario

    	        // Crear cuentas y asignar contratos
    	        C_Formacion_Fondos cuentaFF1 = new C_Formacion_Fondos("004", 1000.0, "Beneficiario4", "CUP", "Entidad1", 12, 2500);
    	        cuentaFF1.setContrato("Entidad1", 12, 2500);
    	        clientes.get(0).agregarCuenta(cuentaFF1);
    	        
    	        C_Corriente corriente = new C_Corriente("005", 1000, "Lia", "CUP");
    	        clientes.get(1).agregarCuenta(corriente);

    	  
    	        C_Formacion_Fondos cuenta2 = new C_Formacion_Fondos("005", 1500.0, "Beneficiario2", "CUP", "Etecsa", 6, 3000);
    	        
    	        // Agregar cuentas a los clientes
    	        clientes.get(1).agregarCuenta(cuenta2);

    	    }

    
     private void crearCuentasBancarias() {
    	    
         C_MLC cuenta1 = new C_MLC("001", 1000.0, "Beneficiario1", "MLC");
         C_MLC cuenta2 = new C_MLC("002", 2000.0, "Beneficiario2", "MLC");
         C_MLC cuenta3 = new C_MLC("003", 3000.0, "Beneficiario3", "MLC");
         C_MLC cuenta4 = new C_MLC("001", 1000.0, "Beneficiario1", "MLC");
         C_MLC cuenta5 = new C_MLC("002", 2000.0, "Beneficiario2", "MLC");
         C_MLC cuenta6 = new C_MLC("003", 3000.0, "Beneficiario3", "MLC");
         C_Formacion_Fondos cuentaFF1 = new C_Formacion_Fondos("004", 1000.0, "Beneficiario4", "CUP", "Entidad1", 12, 2500);
         
         cuentas.add(cuenta3);
         cuentas.add(cuenta2);
         cuentas.add(cuenta1);
         cuentas.add(cuenta4);
         cuentas.add(cuenta5);
         cuentas.add(cuenta6);
         cuentas.add(cuentaFF1);
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
    	    
    	 // Crear cajeros y asignarlos a las agencias
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
    	            cajero.getBilletes().clear();  // Vac�a los billetes
    	            System.out.println("Cajero " + cajero.getIdCajero() + " inicializado con saldo cero."); // Debug
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

     
 	   
     
     public ArrayList<Contrato> getContratos() {
		return contratos;
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

        return entidad + " - " + cantidadContratos + " contratos";  // Retorna nombre y cantidad de contratos
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



	public void agregarContrato(Contrato nuevoContrato) {
		contratos.add(nuevoContrato);
		
	}
}