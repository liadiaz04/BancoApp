package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class C_Corriente extends CuentaBancaria implements Intereses, Deposito, Extraccion {
	
	private ArrayList<String> titulares;
	

	public C_Corriente(String noCuenta, double saldo, String beneficiario, String moneda, ArrayList<String> titulares) {
		
		super(noCuenta, saldo, beneficiario, moneda);
		this.titulares = titulares;
		depositar(50);
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

	@Override
	public boolean tieneCuenta(String nombreCliente) {
		String titularActual = null;
		boolean encontrado = false;
		int size = titulares.size();
		
		for(int i=0; i<size && !encontrado; i++) {
			titularActual = titulares.get(i);
			
			if (titularActual == nombreCliente) {
				encontrado = true;
			}
		}
		
		return encontrado;
	}



}
