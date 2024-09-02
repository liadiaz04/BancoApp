package Clases;

public class Plazo_Deposito {
	
	    private int meses;
	    private double tasaInteres;

	    public Plazo_Deposito(int meses, double tasaInteres) {
	        this.meses = meses;
	        this.tasaInteres = tasaInteres;
	    }

	    public int getMeses() {
	        return meses;
	    }

	    public double getTasaInteres() {
	        return tasaInteres;
	    }
	    
	    public void setMeses(int meses){
	    	if(Validaciones.validarDinero(meses)){
	    		this.meses= meses;
	    	}
	    	else 
	    		throw new IllegalArgumentException ("Debe ingresar una cantidad valida");
	    }
	   
	    public void setTasaInteres(double tasa){
	    	if(Validaciones.validarDinero(tasa)){
	    		this.tasaInteres= tasa;
	    	}
	    	else 
	    		throw new IllegalArgumentException ("Debe ingresar una cantidad valida");
	    }
	}


