package Logic;

import java.util.ArrayList;


public class Cliente {

	private String idCliente;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private ArrayList <CuentaBancaria> cuentas;

	public Cliente(String idCliente, String nombre, String direccion,String telefono, String email) {

		setIdCliente(idCliente);
		setNombre(nombre);
		setDireccion(direccion);
		setTelefono(telefono);
		setEmail(email);
		this.cuentas = new ArrayList<CuentaBancaria>();
	} 


	public String getIdCliente() {
		return idCliente;
	}

	public String getNombre() {

		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setCuentas(ArrayList<CuentaBancaria> cuentas) {
		this.cuentas = cuentas;
	}


	public void agregarCuenta(CuentaBancaria cuenta , String tipo) {
		int cant= cantCuentasDeUnTipo(cuenta);
		if((!(cuenta instanceof C_Corriente)) && cant < 1){
			cuentas.add(cuenta);
		} else 
			if(cuenta instanceof C_Corriente){
				cuentas.add(cuenta);
		}else 
			throw new IllegalArgumentException ("Ya usted posee el limite maximo de cuentas de este tipo");
				
	}

	public int cantCuentasDeUnTipo (CuentaBancaria cuenta){
	    int cant = 0;
	    for(int i = 0 ; i < cuentas.size(); i++){
	        Class<?> tipo = cuentas.get(i).getClass();
	        if(tipo.equals(cuenta.getClass())){
	            cant ++;
	        }
	    }
	    return cant;
	}

	public ArrayList<CuentaBancaria> getCuentas() {
		return cuentas;
	}

	
	public ArrayList<String> obtenerSaldosPorCuenta() {
		ArrayList<String> saldos = new ArrayList<String>();
		for (CuentaBancaria cuenta : cuentas) {
			String saldoInfo = "Cuenta ID: " + cuenta.getNoCuenta() + ", Saldo: " + cuenta.getSaldo() + " " + cuenta.getMoneda();
			saldos.add(saldoInfo);
		}
		return saldos;
	}


}
