package Logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Inicializadora {
   
	private static final Random random = new Random();
	
	public Inicializadora(){
	}

	public static void inicializar(ArrayList<CuentaBancaria> cuentasBanco,ArrayList<Cliente> clientes,ArrayList<User> usuarios,ArrayList<Contrato> contratos,ArrayList<Agencia> agencias, ArrayList<Plazo_Deposito> plazos){

		loadUsers(usuarios);
    	loadAgencias(agencias);
    	ArrayList<Contrato> con = loadContratos(contratos);
    	ArrayList<Plazo_Deposito> pd= loadPlazos(plazos);	
    	ArrayList<Cliente> c = loadClientes(clientes);
    	loadCuentas(cuentasBanco,c,con,pd);
    }   
	
    //FUNCIONES DE PRUEBA DE DATOS
    
	public static ArrayList<Cliente> loadClientes(ArrayList<Cliente> clientes ) {
		ArrayList<Cliente> retorno= new ArrayList<>();
		
		int cont = 0;
        String[] nombres = {"Juan Pérez", "María García", "Carlos López", "Ana Rodríguez",
                            "Pedro Martínez", "Sofía Hernández", "Luis Fernández", "Eva Gómez",
                            "Tomás Díaz", "Isabel Sánchez", "Antonio Moreno", "Cristina Santos",
                            "David González", "Lucia Álvarez", "Javier Torres", "Laura Jiménez",
                            "Miguel Fernández", "Natalia Gómez", "Alejandro Moreno", "Patricia Hernández"};

        String[] direcciones = {"Calle 123, 45678 Ciudad", "Avenida Principal, 78900 Población",
                                "Paseo Marítimo, 10111 Costa", "Plaza Central, 23456 Capital",
                                "Carretera Nacional, 56789 Provincia", "Avda. de la Constitución, 89012 Municipio",
                                "Ctra. de la Libertad, 34567 Comunidad", "Pza. del Ayuntamiento, 67890 Localidad",
                                "Calle de las Flores, 24680 Distrito", "Ave. de los Hombres Ilustres, 13579 Barrio",
                                "Paseo del Río, 54321 Sector", "Calle de la República, 98765 Zona"};

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
       
        String[] carnets ={"44050202340", "08030377632", "75071813361", "93072021983", "96030623213", "04022164897", "65081033529",
        		"67060931981", "31121557974", "80102140698", "67121145159", "44101738182", "06050265281", "52060654120",
        		"81040740818", "92030536779", "62071215367", "06110887030", "76032354972", "29082250105"};
   
        for (int i = 0; i < 20; i++) {

            Cliente aux = new Cliente(carnets[i], nombres[i], direcciones[i % direcciones.length],telefonos[i % telefonos.length], emails[i]);
        	clientes.add(aux);
        }
        retorno = clientes;
        return retorno;
	}

	public static void loadUsers(ArrayList<User> usuarios){
		usuarios.add(new User ("Banco","12345"));
		usuarios.add(new User ("Clari","040221"));
		usuarios.add(new User ("Lia","040401"));
	}


	private static ArrayList <Contrato>  loadContratos(ArrayList<Contrato> contratos) {
		ArrayList <Contrato> contratos2= new ArrayList<>();
		String[] ids = {"CON-01", "CON-02", "CON-03", "CON-04", "CON-05",
				"CON-06", "CON-07", "CON-08", "CON-09", "CON-10",
				"CON-11", "CON-12", "CON-13", "CON-14", "CON-15",
				"CON-16", "CON-17", "CON-18", "CON-19", "CON-20"};

		String[] entidades = {"Empresa XYZ", "S.A. de C.V.", "Institución Educativa",
				"Universidad Nacional", "Gobierno Local", "Hospital General",
				"Escuela Técnica", "Colegio Universitario", "Centro Comercial",
				"Universidad Privada", "Instituto Tecnológico", "Colegio Secundario",
				"Hospital Especializado", "Fundación de Investigación",
				"Escuela de Negocio", "Instituto Cultural", "Colegio Internacional",
				"Universidad Estatal", "Centro Médico", "Instituto de Ciencia",
				"Escuela Superior", "Colegio Técnico"};

		int[] periodosTiempo = {60, 36, 84, 120, 72, 90, 48, 108, 132, 180,
				96, 126, 144, 168, 192, 216, 252, 288, 324, 360};

		double[] salarios = {50000.0, 60000.0, 70000.0, 80000.0,
				90000.0, 100000.0, 110000.0, 120000.0,
				130000.0, 140000.0, 150000.0, 160000.0,
				170000.0, 180000.0, 190000.0, 200000.0,
				210000.0, 220000.0, 230000.0, 240000.0,
				250000.0};

		for (int i = 0; i < 20; i++) {
			String idContrato = ids[i];
			String entidad = entidades[i % entidades.length];
			int periodoTiempo = periodosTiempo[i % periodosTiempo.length];
			double salario = salarios[i % salarios.length];

			contratos.add(new Contrato(idContrato, entidad, periodoTiempo, salario));
		}
		contratos2 = contratos;
		return contratos2;
	}

	private static void loadAgencias(ArrayList<Agencia> agencias) {
		String[] ids = {"AGI-01", "AGI-02", "AGI-03", "AGI-04", "AGI-05",
				"AGI-06", "AGI-07", "AGI-08", "AGI-09", "AGI-10",
				"AGI-11", "AGI-12", "AGI-13", "AGI-14", "AGI-15",
				"AGI-16", "AGI-17", "AGI-18", "AGI-19", "AGI-20",
				"AGI-21", "AGI-22", "AGI-23", "AGI-24", "AGI-25",
				"AGI-26", "AGI-27", "AGI-28", "AGI-29", "AGI-30"};

		String[] gerentes = {"Juan Pérez", "María García", "Carlos López", "Ana Rodríguez",
				"Pedro Martínez", "Sofía Hernández", "Luis Fernández", "Eva Gómez",
				"Tomás Díaz", "Isabel Sánchez", "Antonio Moreno", "Cristina Santos",
				"David González", "Lucia Álvarez", "Javier Torres", "Laura Jiménez",
				"Miguel Fernández", "Natalia Gómez", "Alejandro Moreno", "Patricia Hernández",
				"Rosa García", "Francisco López", "Beatriz Hernández", "Manuel Gómez",
				"Ana Moreno", "Carlos Hernández", "Sofía López", "Luis Gómez", "María Fernández"};
		
		String[] direcciones = {"Calle 123, 45678 Ciudad", "Avenida Principal, 78900 Población",
				"Paseo Marítimo, 10111 Costa", "Plaza Central, 23456 Capital",
				"Carretera Nacional, 56789 Provincia", "Avda. de la Constitución, 89012 Municipio",
				"Ctra. de la Libertad, 34567 Comunidad", "Pza. del Ayuntamiento, 67890 Localidad",
				"Calle de las Flores, 24680 Distrito", "Ave. de los Hombres Ilustres, 13579 Barrio",
				"Paseo del Río, 54321 Sector", "Calle de la República, 98765 Zona",
				"Avenida Independencia, 43210 Ciudad", "Calle de la Paz, 19876 Municipio",
				"Paseo de la Unión, 75342 Provincia", "Avenida de la Libertad, 32145 Comunidad",
				"Calle de la Justicia, 45678 Ciudad", "Plaza de la Constitución, 23456 Capital",
				"Carretera Nacional, 56789 Provincia", "Avda. de la República, 89012 Municipio",
				"Pza. del Ayuntamiento, 67890 Localidad", "Calle de las Flores, 24680 Distrito"};

		for (int i = 0; i < 30; i++) {
			
			ArrayList<Cajero> cajeros = inicializarCajeros();
			String idAgencia = ids[i];
			String gerente = gerentes[i % gerentes.length];
			String direccion = direcciones[i % direcciones.length];
			Agencia agencia= new Agencia(idAgencia, gerente, direccion);
			agencia.setCajeros(cajeros);
			agencias.add(agencia);
		}
	}

	private static ArrayList<Plazo_Deposito> loadPlazos(ArrayList<Plazo_Deposito> plazos) {
		ArrayList<Plazo_Deposito> pd= new ArrayList<Plazo_Deposito>();
		double [] interes = {2,2.50,4.00,5.00,6.00,6.25,6.50};
		int [] meses = {3,6,12,24,36,48,60};

		for(int i =0 ; i < 7 ; i++ ){
			plazos.add(new Plazo_Deposito(meses[i], interes[i]));
		}
		pd = plazos;
		return pd;
		
	}

	public static void loadCuentas(ArrayList<CuentaBancaria> cuentasBanco ,ArrayList<Cliente> clientes,ArrayList<Contrato> contratos ,ArrayList<Plazo_Deposito> plazos) {
		int cont =0;
		String[] cuentasCorrientes = {
				"9205959800000012", "9205959800000023", "9205959800000034", "9205959800000045",
				"9205959800000056", "9205959800000067", "9205959800000078", "9205959800000089",
				"9205959800000100", "9205959800000111", "9205959800000122", "9205959800000133",
				"9205959800000144", "9205959800000155", "9205959800000166", "9205959800000177",
				"9205959800000188", "9205959800000199", "9205959800000200", "9205959800000211"
		};

		String[] cuentasFormacionFondos = {
				"9227959800000012", "9227959800000023", "9227959800000034", "9227959800000045",
				"9227959800000056", "9227959800000067", "9227959800000078", "9227959800000089",
				"9227959800000100", "9227959800000111", "9227959800000122", "9227959800000133",
				"9227959800000144", "9227959800000155", "9227959800000166", "9227959800000177",
				"9227959800000188", "9227959800000199", "9227959800000200", "9227959800000211"
		};

		String[] cuentasMLC = {
				"9235959800000012", "9235959800000023", "9235959800000034", "9235959800000045",
				"9235959800000056", "9235959800000067", "9235959800000078", "9235959800000089",
				"9235959800000100", "9235959800000111", "9235959800000122", "9235959800000133",
				"9235959800000144", "9235959800000155", "9235959800000166", "9235959800000177",
				"9235959800000188", "9235959800000199", "9235959800000200", "9235959800000211"
		};

		String[] cuentasPlazoFijo = {
				"9205959800002788", "9205959800002799", "9205959800002800", "9205959800002811",
				"9205959800002822", "9205959800002833", "9205959800002844", "9205959800002855",
				"9205959800002866", "9205959800002877", "9205959800002888", "9205959800002899",
				"9205959800002900", "9205959800002911", "9205959800002922", "9205959800002933",
				"9205959800002944", "9205959800002955", "9205959800002966", "9205959800002977",
		};

		String[] beneficiarios = {
			    "Juan Pérez", "María García", "Carlos López", "Ana Rodríguez",
			    "Pedro Martínez", "Sofía Hernández", "Luis Fernández", "Eva Gómez",
			    "Tomás Díaz", "Isabel Sánchez", "Antonio Moreno", "Cristina Santos",
			    "David González", "Lucia Álvarez", "Javier Torres", "Laura Jiménez",
			    "Miguel Fernández", "Natalia Gómez", "Alejandro Moreno", "Patricia Hernández",
			    "Rosa García", "Francisco López", "Beatriz Hernández", "Luis Miguel",
			    "Carmen Díaz", "Alberto Castro", "Verónica Silva", "Ricardo Ruiz",
			    "Gabriela Morales", "Felipe Ortega", "Mariana Romero", "Jorge Ramírez",
			    "Claudia Salazar", "Arturo Méndez", "Sara Paredes", "Nicolás Cordero",
			    "Andrea Castillo", "Mario León", "Silvia Soto", "Hugo Delgado",
			    "Paola Ríos", "Samuel Romero", "Alicia Romero", "Cecilia Vargas",
			    "Diego Serrano", "Valentina Torres", "Fernando Ruiz", "Emilia Peralta",
			    "Raúl Martínez", "Vanessa Rojas", "Alma Cortés", "Ezequiel Rivas",
			    "Luciano Valdés", "Cynthia Aguirre", "Esteban Aguirre", "Carla Mendoza",
			    "Pablo Castañeda", "Susana León", "Bruno Castro", "Mónica Peña",
			    "Ricardo Pérez", "Fabiola Sánchez", "Cristopher Santos", "Paula López",
			    "Gonzalo Díaz", "Tania Mendoza", "Omar Martín", "Leticia Ortega",
			    "Nadia Gómez", "Héctor Jiménez", "Tamara Silva", "Ignacio Morales",
			    "Patricia Ruiz", "David Romero", "Natalia Castro", "Cristina Salas",
			    "Fabio López", "Marina Torres", "Hugo Ceballos", "Julieta Pérez"
			};
		
		double[] cantidades = {
			    2500.50, 3200.75, 4500.00, 2750.20,
			    3100.40, 3900.60, 5000.00, 4000.90,
			    2800.30, 3600.10, 4800.80, 5500.00,
			    3000.25, 3300.50, 4700.90, 5200.15,
			    6000.00, 3400.60, 4100.80, 5300.45
			};
		
		for(int i = 0 ; i < clientes.size() ; i++){
			  int index = i % plazos.size();
			ArrayList<CuentaBancaria> cuentas = clientes.get(i).getCuentas();
			cuentas.add(new C_Corriente (cuentasCorrientes[i],beneficiarios[cont++],"CUP"));
			cuentas.add(new C_MLC (cuentasMLC[i],beneficiarios[cont++],"MLC"));
			cuentas.add(new C_Formacion_Fondos(cuentasFormacionFondos[i], beneficiarios[cont++], "CUP", contratos.get(i)));
			cuentas.add(new C_Plazo_Fijo(cuentasPlazoFijo[i], beneficiarios[cont++], "CUP", plazos.get(index),cantidades[index]));
			cuentasBanco.addAll(cuentas);
		}
      
	}

	public static ArrayList<Cajero> inicializarCajeros() {
		ArrayList<Cajero> cajeros = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			String idCajero = "Cajero" + (i + 1); 
			Cajero cajero = new Cajero(idCajero);


			if (i != 0) {
					cajero.agregarBillete(new Billete(TipoBillete.Diez, 10)); 
					cajero.agregarBillete(new Billete(TipoBillete.Veinte, 5)); 
					cajero.agregarBillete(new Billete(TipoBillete.Cien, 2)); 
					cajero.agregarBillete(new Billete(TipoBillete.Mil, 100)); 
				}

				cajeros.add(cajero);
			}

	        return cajeros;
	    }
 
	//-------------------------------------------------------------------------------------//

	//PARA LA EXPO//

	public static void modificarFechasApertura (ArrayList<CuentaBancaria> cuentas){
		ArrayList<LocalDate> newFechas = generarFechasAleatorias(80); 
		for(int i=0; i < cuentas.size(); i++){
			cuentas.get(i).setFechaApertura(newFechas.get(i));
		}
	}

	public static ArrayList<LocalDate> generarFechasAleatorias(int cantidad) {
		ArrayList<LocalDate> fechas = new ArrayList<>();

		for (int i = 0; i < cantidad; i++) {

			int año = random.nextInt(LocalDate.now().getYear() - 2015 + 1) + 2015;
			int mes = random.nextInt(12) + 1;
			int dia = random.nextInt(diasEnMes(mes, año)) + 1;

			LocalDate fecha = LocalDate.of(año, mes, dia);
			fechas.add(fecha);
		}

		return fechas;
	}

	private static int diasEnMes(int mes, int año) {

		int dias = 0; 
		switch (mes) {
		case 1: 
		case 3: 
		case 5: 
		case 7:
		case 8: 
		case 10:
		case 12:
			dias = 31;
			break;
		case 4: 
		case 6: 
		case 9: 
		case 11:
			dias = 30; 
			break;
		case 2: 
			dias = (LocalDate.of(año, 2, 1).isLeapYear()) ? 29 : 28;
			break;
		}
		return dias; 
	}


}
