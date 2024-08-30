package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class C_Plazo_Fijo extends CuentaBancaria implements Intereses {
	
	private String titular;

	public C_Plazo_Fijo(String noCuenta, double saldo, String beneficiario, String moneda, String titular) {
		super(noCuenta, saldo, beneficiario, moneda);
		
		this.titular = titular;
	}

	public double calcularInteres() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
