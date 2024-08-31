package Clases;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class C_Corriente extends CuentaBancaria implements Intereses, Deposito, Extraccion {




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
			operaciones.add(new Operacion ("Extraccion",saldo,LocalDate.now()));
		}else 
			throw new IllegalArgumentException ("Debe extrar una cantidad valida");
	}


	public void depositar(double saldo) {
		if(Validaciones.validarDinero(saldo)){
			this.saldo += saldo;
			operaciones.add(new Operacion ("Deposito",saldo,LocalDate.now()));
		}else 
			throw new IllegalArgumentException ("Debe extraer una cantidad valida");
	}

	public void interes() {
		Operacion op = ultimaOperacionDeUnTipo("Cobro de Intereses");
		if(op != null  && ChronoUnit.YEARS.between(op.getFecha(), LocalDate.now())>= 1){
			long meses = mesesDeUltimaExtraccion();
			if (meses >= 12) {
				double nuevo = this.saldo * 0.02;
				this.saldo += nuevo;
				this.operaciones.add(new Operacion("Cobro de Intereses",nuevo,LocalDate.now()));
			}
		}
	}


}
