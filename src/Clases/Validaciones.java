package Clases;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 public final class Validaciones {
  
	 public static boolean validarNombre(String nombre) {/*
	        String regex = ".*[^a-zA-Z].*[^a-zA-Z].*[^a-zA-Z].*";
	        boolean valido = false;
	        if (!(nombre.trim().length() < 3)) {
	            valido = nombre.matches(regex);
	        }
	        return valido;*/
		 return true; 
	    }
   
	 public static boolean validarDinero (double cant){
		 return cant > 0 ? true : false;
	 }
    
	 public static void validarCI(String no_identidad) {
	        // COMPROBAR QUE EL CI TIENE 11 DIGITOS 
	        if (no_identidad == null || no_identidad.length() != 11 || !no_identidad.matches("\\d{11}")) {
	        	throw new IllegalArgumentException("El carnet debe tener 11 dígitos");
	        }

	        // EXTRAER FECHA DE NACIMIENTO 
	        String yearPart = no_identidad.substring(0, 2);
	        String monthPart = no_identidad.substring(2, 4);
	        String dayPart = no_identidad.substring(4, 6);
	        char centuryDigit = no_identidad.charAt(6);

	        int year = 0;
	        switch (centuryDigit) {
	            case '9' : 
	            	year = 1800 + Integer.parseInt(yearPart);
	            	break;
	            case '0': case '1': case '2': case '3': case '4': case '5' : 
	            	year = 1900 + Integer.parseInt(yearPart);
	            	break;
	            case '6': case '7': case '8': 
	            	year = 2000 + Integer.parseInt(yearPart);
	            	break;
	            default : throw new IllegalArgumentException("El siglo no es valido");
	        };

	        // CREAR LA FECHA DE NACIMIENTO
	        String birthDateStr = String.format("%04d-%02d-%02d", year, Integer.parseInt(monthPart), Integer.parseInt(dayPart));
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate birthDate;

	        try {
	            birthDate = LocalDate.parse(birthDateStr, formatter);
	        } catch (DateTimeParseException e) {
	        	throw new IllegalArgumentException("La fecha de nacimiento no es válida");
	        }

	        // VALIDAR LA EDAd
	        LocalDate currentDate = LocalDate.now();
	        int age = Period.between(birthDate, currentDate).getYears();

	        if (age < 16 || age > 100) {
	        	throw new IllegalArgumentException("El usuario esta un rango de edad no permitido");
	        }

	    }
	 
	 //EVENTOS CUJAE VALIDATIONS 
	 public static boolean validarTelefono(String telefono){
			boolean valido = false;
			
			if(telefono.trim().length()!= 0)
			{
				if(telefono.matches("\\d{8}")){
					valido =true;
				}
			}
			
			return valido;
		}
		

	 public static boolean validadorCorreo(String correo) {

		 String email = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

		 Pattern pattern = Pattern.compile(email);
		 Matcher matcher = pattern.matcher(correo);

		 return matcher.matches();
	 }
 
 }
