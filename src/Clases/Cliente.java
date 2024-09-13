package Clases;

import java.util.ArrayList;


public class Cliente {

	private String idCliente;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private ArrayList <CuentaBancaria> cuentas;

	public Cliente(String idCliente, String nombre, String direccion,
			String telefono, String email) {

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

	public void setIdCliente(String idCliente) {
		try{
			Validaciones.validarCI(idCliente);
			this.idCliente = idCliente;
		}catch (Exception e){
			e.printStackTrace();
		}
	}



	public String getNombre() {

		return nombre;
	}



	public void setNombre(String nombre) {
		if(Validaciones.validarNombre(nombre)){
			this.nombre = nombre;
		}else 
			throw new IllegalArgumentException ("Nombre Invalido");
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		if(Validaciones.validarNombre(direccion)){
			this.direccion = direccion;
		}else 
			throw new IllegalArgumentException ("Nombre Invalido");
	}



	public String getTelefono() {
		return this.telefono;
	}



	public void setTelefono(String telefono) {
		if(Validaciones.validarTelefono(telefono)){
			this.telefono = telefono;
		}else 
			throw new IllegalArgumentException ("Numero de telefono no valido");
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		if(Validaciones.validadorCorreo(email)){
			this.email = email;
		}else 
			throw new IllegalArgumentException ("Su correo electronico no es valido");
	}


	public void agregarCuenta(CuentaBancaria cuenta , String tipo) {
		int cant= cantCuentasDeUnTipo(cuenta);
		if((!(cuenta instanceof C_Corriente)) && cant < 1){
			cuentas.add(cuenta);
		} else 
			if(cuenta instanceof C_Corriente)
				cuentas.add(cuenta);
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
