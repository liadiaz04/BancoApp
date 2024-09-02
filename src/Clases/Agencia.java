package Clases;

import java.util.ArrayList;

public class Agencia {
	private int idAgencia;
	private String gerente;
	private String direccion;
	private ArrayList<Cajero> cajeros;
	
	public Agencia(int idAgencia, String gerente, String direccion) {
		setIdAgencia(idAgencia);
		setGerente(gerente);
		setDireccion(direccion);
		this.cajeros = new ArrayList<Cajero>();
	}

	public int getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public ArrayList<Cajero> getCajeros() {
		return cajeros;
	}
}
