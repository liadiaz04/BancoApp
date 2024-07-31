package Clases;

import java.util.ArrayList;

public class Cajero {
	private int idCajero;
	private ArrayList<Billete> billetes;
	
	public Cajero(int idCajero) {
		setIdCajero(idCajero);
		this.billetes = new ArrayList<Billete>();
	}

	public int getIdCajero() {
		return idCajero;
	}

	public void setIdCajero(int idCajero) {
		this.idCajero = idCajero;
	}

	public ArrayList<Billete> getBilletes() {
		return billetes;
	}

}
