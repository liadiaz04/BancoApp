package Clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public abstract class CuentaBancaria {
	
	protected String noCuenta;
	protected double saldo;
	protected String beneficiario;
	protected String moneda;
	protected LocalDate fechaApertura;
	protected ArrayList<Operacion> operaciones;
	
	public CuentaBancaria(String noCuenta, String beneficiario, String moneda) {
		
		this.noCuenta = noCuenta;
		this.saldo =0;
		setBeneficiario(beneficiario);
		setMoneda(moneda);
		this.fechaApertura =  LocalDate.now();
		this.operaciones = new ArrayList<Operacion>();

	}
	
	public String getNoCuenta() {
		return noCuenta;
	}
	

	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String getBeneficiario() {
		return beneficiario;
	}
	
	public void setBeneficiario(String beneficiario) {
		if(Validaciones.validarNombre(beneficiario)){
			this.beneficiario = beneficiario;
		} else
			throw new IllegalArgumentException (" El nombre del Beneficiario debe tener minimmo 3 caracteres que sean letras.");
	}


	public String getMoneda() {
		return moneda;
	}
	
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	
	public ArrayList<Operacion> getOperaciones() {
		return operaciones;
	}
	
	public LocalDate getFechaApertura() {
		return fechaApertura;
	}
	
	                   //FUNCIONES ADICIONALES   
	
	public int cantOperacionesDeUnTipo (String tipoOperacion,LocalDate fecha) {

		int cantidad = 0;
		for (int i = 0; i < operaciones.size(); i++) {
			Operacion op = operaciones.get(i);
			if (fecha.getYear() == op.getFecha().getYear()) {
				if (op.getTipo().equalsIgnoreCase(tipoOperacion)) {
					cantidad++;
				}
			}

		}
		return cantidad; //CANTIDAD DE OPERACIONES DE UN TIPO EN UN ANNIO
	}
	
	public Operacion ultimaOperacionDeUnTipo (String tipo){
		Operacion op = null;
		boolean found = false;
		for (int i = this.operaciones.size() - 1; i >= 0 && !found; i--) {
			 op = this.operaciones.get(i);
			if (op.getTipo().equals(tipo)) {
				found = true;
			}
		}
		return op; //ULTIMA OPERACION DE UN TIPO 
	}
	
   public long mesesDeUltimaExtraccion(){
	   boolean found = false;
       long cantMeses = -1;
		for (int i = this.operaciones.size() - 1; i >= 0 && !found; i--) {
			Operacion op = this.operaciones.get(i);
			if (op.getTipo().equals("Extraccion")) {
				found = true;
				cantMeses = ChronoUnit.MONTHS.between(op.getFecha(), LocalDate.now());
			}
		}
		return cantMeses; // CANTIDAD DE MESES DE LA ULTIMA EXTRACCION
   }
   
   public ArrayList <Operacion> ultimasDiezOperaciones (){
	   
	   ArrayList<Operacion> ultimasOperaciones = new ArrayList<Operacion>();
	   for (int i = this.operaciones.size() - 1 ; i >= 0  && ultimasOperaciones.size() < 10; i--){
		   ultimasOperaciones.add(operaciones.get(i));
	   }
	   return ultimasOperaciones;
   }
   
}
