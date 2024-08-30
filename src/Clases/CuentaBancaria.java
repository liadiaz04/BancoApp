package Clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public abstract class CuentaBancaria {
	
	protected String noCuenta;
	protected double saldo;
	protected String beneficiario;
	protected String moneda;
	protected boolean estado;
	protected LocalDate fechaApertura;
	protected ArrayList<Operacion> operaciones;
	
	public CuentaBancaria(String noCuenta, double saldo, String beneficiario, String moneda) {
		
		setNoCuenta(noCuenta);
		setSaldo(saldo);
		setBeneficiario(beneficiario);
		setMoneda(moneda);
		
		setEstado(true);
		this.fechaApertura =  LocalDate.now();
		this.operaciones = new ArrayList<Operacion>();

	}
	
	public String getNoCuenta() {
		return noCuenta;
	}
	
	public void setNoCuenta(String noCuenta) {
		this.noCuenta = noCuenta;
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
		this.beneficiario = beneficiario;
	}
	
	public String getMoneda() {
		return moneda;
	}
	
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public ArrayList<Operacion> getOperaciones() {
		return operaciones;
	}
	
	public LocalDate getFechaApertura() {
		return fechaApertura;
	}
	
	//Hecha en el IntelliJ 
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
		return cantidad;
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
		return op;
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
		return cantMeses;
   } 
}
