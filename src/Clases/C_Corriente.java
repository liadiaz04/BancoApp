package Clases;
import java.time.LocalDate;


public class C_Corriente extends CuentaBancaria implements Intereses, Deposito, Extraccion,Transferencia {
	
	
	

	public C_Corriente(String noCuenta, double saldo, String beneficiario, String moneda) {
		
		super(noCuenta, saldo, beneficiario, moneda);
		depositar(50);
		
	}
	
	public double getSaldo(){
		interes();
		return this.saldo;
	}

	public void extraer(double saldo) {
	  if(Validaciones.validarDinero(saldo) && (this.saldo - saldo) > 50){
		  this.saldo -= saldo;
	  }else 
		  throw new IllegalArgumentException ("Debe extrar una cantidad valida");
	}

	
	public void depositar(double saldo) {
		if(Validaciones.validarDinero(saldo)){
			this.saldo += saldo;
		}else 
			  throw new IllegalArgumentException ("Debe extrar una cantidad valida");
	}

	public void interes() {
		Operacion op = ultimaOperacionDeUnTipo();
		if(op != null){
		long meses = mesesDeUltimaExtraccion();
		if (meses >= 365) {
			this.saldo += saldo * 0.02;
		}
		}
	}


}
