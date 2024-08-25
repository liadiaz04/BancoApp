package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class C_MLC extends CuentaBancaria implements Extraccion {
	private String titular;

	public C_MLC(String noCuenta, double saldo, String beneficiario, String moneda, String titular) {
		
		super(noCuenta, saldo, beneficiario, moneda);
		this.titular = titular;
	}

	public void extraer(double saldo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tieneCuenta(String nombreCliente) {
		boolean encontrado = false;
		
		if (nombreCliente == titular)
			encontrado = true;
		
		return encontrado;
	}

}
