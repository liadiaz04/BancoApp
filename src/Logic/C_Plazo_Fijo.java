package Logic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class C_Plazo_Fijo extends CuentaBancaria implements Intereses,Deposito,Extraccion{

	private Plazo_Deposito plazo;
	
	public Plazo_Deposito getPlazo(){
		return this.plazo;
	}

	public C_Plazo_Fijo(String noCuenta, String beneficiario, String moneda, Plazo_Deposito plazo,double cantInicial) {
		super(noCuenta, beneficiario, moneda);
		this.plazo = plazo;
		depositar(cantInicial);
	}

    public double getSaldo(){
		interes();
		return this.saldo;
	}
	
	public void interes() {
		if(ultimaOperacionDeUnTipo("Cobro de Interes")== null){
			long diferenciaMeses = ChronoUnit.MONTHS.between(this.fechaApertura,LocalDate.now());
			if(diferenciaMeses >= this.plazo.getMeses()){
				this.saldo += plazo.getTasaInteres();
				operaciones.add(new Operacion ("Cobro de Interes",plazo.getTasaInteres(),LocalDate.now()));
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
		operaciones.add(new Operacion ("Extraccion",this.saldo,LocalDate.now()));
	}

}
