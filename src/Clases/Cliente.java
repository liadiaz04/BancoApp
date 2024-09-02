package Clases;

import java.util.ArrayList;


public class Cliente {
	
	private String idCliente;
	private String nombre;
	private String direccion;
	private String telefono;
	private String gmail;
	private ArrayList <CuentaBancaria> cuentas;
	
	public Cliente(String idCliente, String nombre, String direccion,
			String telefono, String gmail) {
		
		setIdCliente(idCliente);
		setNombre(nombre);
		setDireccion(direccion);
		setTelefono(telefono);
		setGmail(gmail);
		this.cuentas = new ArrayList<CuentaBancaria>();
	}

	
	public String getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getGmail() {
		return gmail;
	}



	public void setGmail(String gmail) {
		this.gmail = gmail;
	}



	public ArrayList<CuentaBancaria> getCuentas() {
		return cuentas;
	}



	public void setCuentas(ArrayList<CuentaBancaria> cuentas) {
		this.cuentas = cuentas;
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
