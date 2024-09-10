package Clases;

import java.util.ArrayList;

public class Agencia {
    private String idAgencia;
    private String gerente;
    private String direccion;
    private ArrayList<Cajero> cajeros;

    public Agencia(String idAgencia, String gerente, String direccion) {
        setIdAgencia(idAgencia);
        this.gerente = gerente;
        this.direccion = direccion;
        this.cajeros = new ArrayList<Cajero>();
    }

    private void setIdAgencia(String idAgencia2) {
        this.idAgencia = idAgencia2;
    }

    public String getIdAgencia() {
        return idAgencia;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public ArrayList<Cajero> getCajeros() {
        return cajeros;
    }

    public void agregarCajero() {
        int cantCajeros = this.cajeros.size();
        if (cantCajeros < 5) {
            String idCajero = String.format("Caj%02d", cantCajeros + 1);
            this.cajeros.add(new Cajero(idCajero));
        }
    }

    public boolean eliminarCajero(String idCajero) {
    	boolean delete = false;
        for (int i = 0; i < cajeros.size() && !delete; i++) {
            Cajero cajero = cajeros.get(i);
            if (cajero.getIdCajero().equals(idCajero)) {
                cajeros.remove(i);
                delete = true; 
            }
        }
        return delete; 
    }
}

	
