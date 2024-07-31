package Clases;

import java.time.LocalDate;

public class Operacion {
	private String tipo;
	private LocalDate fecha;

	public Operacion(String tipo, LocalDate fecha) {
		setTipo(tipo);
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

}
