package Clases;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class C_Plazo_Fijo extends CuentaBancaria implements Intereses,Deposito,Extraccion{
	
    private boolean interesCobrado;
	private Plazo_Deposito plazo;

	public C_Plazo_Fijo(String noCuenta, double saldo, String beneficiario, String moneda, Plazo_Deposito plazo,double cantInicial) {
		super(noCuenta, saldo, beneficiario, moneda);
		this.plazo = plazo;
		this.interesCobrado = false;
		depositar(cantInicial);
	}


	public void interes() {
		if(!this.interesCobrado){
			long diferenciaMeses = ChronoUnit.MONTHS.between(this.fechaApertura,LocalDate.now());
			if(diferenciaMeses >= this.plazo.getMeses()){
				this.saldo += plazo.getTasaInteres();
				this.estado = true;
			}
		}
	}

	public void depositar(double saldo) {
		if(Validaciones.validarDinero(saldo)){
			this.saldo += saldo;
			operaciones.add(new Operacion ("Deposito",saldo,LocalDate.now()));
		}else 
			throw new IllegalArgumentException ("Debe extraer una cantidad valida");
	}


	public void extraer(double saldo) {
		 this.saldo = 0;
	}

	
}
