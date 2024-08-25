package Clases;

import java.time.LocalDate;
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
	
	public abstract boolean tieneCuenta(String nombreCliente);
	
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
}
