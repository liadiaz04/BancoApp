package Clases;

import java.time.LocalDate;

public class Operacion {
	
    private String tipo; 
    private double monto;
    private LocalDate fecha;

    public Operacion(String tipo, double monto, LocalDate fecha) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Monto: " + monto + ", Fecha: " + fecha;
    }
}
