package Clases;

import java.util.ArrayList;

public class Agencia {
	private String idAgencia;
	private String gerente;
	private String direccion;
	private ArrayList<Cajero> cajeros;

	public Agencia(String idAgencia, String gerente, String direccion) {
		setGerente(gerente);
		setDireccion(direccion);
		this.cajeros = new ArrayList<Cajero>();
	}

	public String getIdAgencia() {
		return idAgencia;
	}
	

	public String getGerente() {
		return gerente;
	}
	

	public void setGerente(String gerente) {
		if(Validaciones.validarNombre(gerente)){
			this.gerente = gerente;
		}else 
			throw new IllegalArgumentException ("El nombre del gerente no es correcto");
	}


	public String getDireccion() {
		return direccion;
	}
	

	public void setDireccion(String direccion) {
		if(Validaciones.validarNombre(direccion)){
			this.direccion = direccion;
		}else 
			throw new IllegalArgumentException ("Direccion no valida");
	}
	

	public ArrayList<Cajero> getCajeros() {
		return cajeros;
	}

	
	public void agregarCajero (){
		int cantCajeros = this.cajeros.size();
		if(cantCajeros < 5 ){
			String idCajero = String.format("Caj%02d", cantCajeros +1);
			this.cajeros.add(new Cajero (idCajero));
		}
	}

}

	
