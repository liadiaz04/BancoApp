package Clases;

import java.util.ArrayList;

public class Cajero {
	private String idCajero;
	private ArrayList<Billete> billetes;
	
	public Cajero(String idCajero) {
		this.idCajero = idCajero;
		this.billetes = new ArrayList<Billete>();
	}

	

	public String getIdCajero() {
		return idCajero;
	}
	
	public ArrayList<Billete> getBilletes() {
		return billetes;
	}

	public int mostrarSaldoTotal() {
        int saldoTotal = 0;

       
        for (Billete billete : billetes) {
            saldoTotal += billete.valorTotal();
        }

        return saldoTotal;
    }
	
	public double mostrarSaldoDesglosado() {
        double total = 0;
        for (Billete billete : billetes) {
            total += billete.getCantidad() * billete.getTipo().getValor();
        }
        return total;
    }
}


