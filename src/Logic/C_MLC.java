package Logic;

import java.time.LocalDate;
import java.util.ArrayList;

public class C_MLC extends CuentaBancaria implements Deposito {


	public C_MLC(String noCuenta, String beneficiario, String moneda) {

		super(noCuenta, beneficiario, moneda);
	}


	public void depositar(double saldo) {
		if(Validaciones.validarDinero(saldo)){
			this.saldo += saldo;
			operaciones.add(new Operacion ("Deposito",saldo,LocalDate.now()));
		}else 
			throw new IllegalArgumentException ("Debe extraer una cantidad valida");
	}

}
