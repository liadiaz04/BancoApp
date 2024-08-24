

package Clases;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("123456789", "Calle Falsa 123");

        try {
            banco.addCliente("04040178174", "Calle A 1", "Juan", "12345678", "juan.perez@gmail.com");
            banco.addCliente("03040178175", "Calle B 2", "Maria", "23456789", "maria.lopez@gmail.com");
            // Añadir más clientes...
        } catch (IllegalArgumentException e) {
            System.out.println("Error al añadir cliente: " + e.getMessage());
        }

        for (Cliente cliente : banco.getClientes()) {
            System.out.println("Cliente ID: " + cliente.getIdCliente() + 
                               ", Nombre: " + cliente.getNombre() + 
                               ", Dirección: " + cliente.getDireccion() + 
                               ", Teléfono: " + cliente.getTelefono() + 
                               ", Email: " + cliente.getGmail());
        }    }
}