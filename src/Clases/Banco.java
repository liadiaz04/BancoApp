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
        this.clientes = new ArrayList<Cliente>(); 
    }
    
    //CLIENTES

    public void addCliente(String idCliente, String direccion, String nombre, String telefono, String correo) {
        if (buscarClientePorId(idCliente) == null) {
            Cliente cliente = new Cliente(idCliente, direccion, nombre, telefono, correo);
            clientes.add(cliente);
        } else {
            System.out.println("El cliente con ID " + idCliente + " ya existe.");
        }
    }
    
    public Cliente buscarClientePorId(String idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(idCliente)) {
                return cliente;
            }
        }
        return null; 
    }
    
     public boolean eliminarCliente(String idCliente) {
        boolean eliminado = false; 
        
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(idCliente)) {
                clientes.remove(i);
                eliminado = true; 
            }
        }

        return eliminado; 
    }
     
     //Reporte 1
     //Obtener el saldo de todas las cuentas de un cliente
     public ArrayList<String> obtenerSaldosCliente(String idCliente) {
         ArrayList<String> saldos = new ArrayList<String>(); 
         Cliente cliente = buscarClientePorId(idCliente);
         
         if (cliente != null) {
             saldos = cliente.obtenerSaldosPorCuenta(); 
         } else {
             System.out.println("Cliente no encontrado.");
         }
         
         return saldos; 
     }
  


    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }
}
