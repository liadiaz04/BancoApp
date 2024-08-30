package Clases;

 public final class Validaciones {
  
	 public static boolean validarNombre(String nombre) {
	        String regex = ".*[^a-zA-Z].*[^a-zA-Z].*[^a-zA-Z].*";
	        boolean valido = false;
	        if (!(nombre.trim().length() < 3)) {
	            valido = nombre.matches(regex);
	        }
	        return valido;
	    }
   
	 public static boolean validarDinero (double cant){
		 return cant > 0 ? true : false;
	 }
}
