package Clases;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class C_Formacion_Fondos extends CuentaBancaria implements Intereses, Extraccion{

	private Contrato contrato;
	private LocalDate fechaUltimaActualizacion;

	public C_Formacion_Fondos(String noCuenta, double saldo, String beneficiario, String moneda,String entidad, int tiempo , double salario) {

		super(noCuenta,saldo,beneficiario,moneda);
		this.contrato = new Contrato (entidad,tiempo,salario);
		this.fechaUltimaActualizacion = LocalDate.now();
		this.saldo= 30;
	}

	public double getSaldo() {
		actualizarSaldo();
		interes();
		return this.saldo;
	}


	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(String entidad , int tiempo , double salario ) {
		this.contrato.setEntidad(entidad);
		this.contrato.setPeriodoTiempo(tiempo);
		this.contrato.setSalario(salario);
	}


	public void extraer(double cantidad) {
		if(Validaciones.validarDinero(cantidad)){
			if((this.saldo - cantidad) > 30 && cantOperacionesDeUnTipo("Extraccion",LocalDate.now()) < 4) {
				this.saldo -= 30;
				operaciones.add(new Operacion ("Extraccion",cantidad,LocalDate.now()));
			}
			else
				throw new IllegalArgumentException("No es posible realizar la extraccion");
		}else 
			throw new IllegalArgumentException ("Debe extrar una cantidad valida");
	}


	public void actualizarSaldo () {

		long mesesDesdeUltimaActualizacion = ChronoUnit.MONTHS.between(fechaUltimaActualizacion, LocalDate.now());

		if (mesesDesdeUltimaActualizacion >= contrato.getPeriodoTiempo()) {
			int numeroDeIncrementos = (int) (mesesDesdeUltimaActualizacion / contrato.getPeriodoTiempo());
			saldo += numeroDeIncrementos * contrato.getSalario();
			fechaUltimaActualizacion = fechaUltimaActualizacion.plusMonths(numeroDeIncrementos * contrato.getPeriodoTiempo());
			this.operaciones.add(new Operacion ("Deposito",contrato.getSalario(),LocalDate.now()));
		}
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
