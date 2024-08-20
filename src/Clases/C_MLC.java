package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class C_MLC extends CuentaBancaria implements Extraccion{
	private String titular;

	public C_MLC(String noCuenta, double saldo, String beneficiario,
			String moneda, boolean estado, LocalDate fecha,
			ArrayList<Operacion> operaciones, String titular) {
		super(noCuenta, saldo, beneficiario, moneda, estado, fecha, operaciones);
		this.titular = titular;
	}

	public void extraer(double saldo) {
		// TODO Auto-generated method stub
		
	}

}
