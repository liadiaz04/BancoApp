package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class C_Corriente extends CuentaBancaria implements Intereses, Deposito, Extraccion {
	
	private ArrayList<String> titulares;
	

	public C_Corriente(String noCuenta, double saldo, String beneficiario,
			String moneda, boolean estado, LocalDate fecha,
			ArrayList<Operacion> operaciones, ArrayList<String> titulares) {
		super(noCuenta, saldo, beneficiario, moneda, estado, fecha, operaciones);
		this.titulares = titulares;
	}

	public void extraer(double saldo) {
		// TODO Auto-generated method stub
		
	}

	public void depositar(double saldo) {
		// TODO Auto-generated method stub
		
	}

	public double calcularInteres() {
		// TODO Auto-generated method stub
		return 0;
	}
}
