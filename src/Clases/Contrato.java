package Clases;


public class Contrato {
	private String entidad;
	   private int periodoTiempo;
	   private double salario;

	   public Contrato(String entidad, int periodoTiempo, double salario) {
	        setEntidad(entidad);
	        setPeriodoTiempo(periodoTiempo);
	        setSalario(salario);
	   }

	  public String getEntidad() {
	   return entidad;
	  }

	  public void setEntidad(String entidad) {
	   if (Validaciones.validarNombre(entidad)){
	       this.entidad = entidad;
	   } else
	  throw new IllegalArgumentException (" El nombre de la Entidad debe tener minimmo 3 caracteres que sean letras.");
	  }

	  public int getPeriodoTiempo() {
	   return periodoTiempo;
	  }

	  public void setPeriodoTiempo(int periodoTiempo) {
	      if (periodoTiempo > 0) {
	          this.periodoTiempo = periodoTiempo;
	      } else
	          throw new IllegalArgumentException ("La cantidad de tiempo del incremento del salario debe ser minimo 1 mes");
	  }

	  public double getSalario() {
	   return salario;
	  }

	  public void setSalario(double salario) {
	       if(salario > 2400) {
	           this.salario = salario;
	       } else
	      throw new IllegalArgumentException  ("El salario debe ser un monto mayor a los 2400 cup");
	   }
}
