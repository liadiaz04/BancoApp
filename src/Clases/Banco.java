package Clases;

import java.util.ArrayList;


import java.util.ArrayList;

public class Banco {
    private String telefono;
    private String direccion;
    private ArrayList<Cliente> clientes;

    // Constructor
    public Banco(String telefono, String direccion) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.clientes = new ArrayList<Cliente>(); // Inicializa el ArrayList
    }

    // Método para agregar un cliente
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Método para obtener la lista de clientes
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    // Métodos para obtener información adicional
    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }
}
