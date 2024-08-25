package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class C_Formacion_Fondos extends CuentaBancaria implements Intereses, Deposito, Extraccion{
	
	private String titular;

	public C_Formacion_Fondos(String noCuenta, double saldo,String beneficiario, String moneda, String titular) {
		super(noCuenta, saldo, beneficiario, moneda);
		this.titular = titular;
	}

	public void extraer(double saldo) {
		if(saldo > 30) {
			//algo
		}
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
		boolean encontrado = false;
		
		if (nombreCliente == titular)
			encontrado = true;
		
		return encontrado;
	}

	

}
