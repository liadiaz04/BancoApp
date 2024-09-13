package Logic;

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
	
	public String mostrarSaldoDesglosado() {
	    StringBuilder desglosado = new StringBuilder();
	    for (Billete billete : billetes) {
	        int cantidad = billete.getCantidad();
	        double valor = billete.getTipo().getValor();
	        if (cantidad > 0) {
	            desglosado.append(cantidad).append(" billetes de ").append(valor).append("\n");
	        }
	    }
	    return desglosado.toString();
	}

}


