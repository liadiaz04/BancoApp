package Clases;

public class Main {
    public static void main(String[] args) {
        // Crear el banco
        Banco banco = new Banco("123456789", "Avenida Principal 123");

        // Inicializar 10 clientes y agregarlos al banco
        banco.agregarCliente(new Cliente("C001", "Juan Pérez", "Calle 1", "123456789", "juan@gmail.com"));
        banco.agregarCliente(new Cliente("C002", "María López", "Calle 2", "987654321", "maria@gmail.com"));
        banco.agregarCliente(new Cliente("C003", "Carlos García", "Calle 3", "456789123", "carlos@gmail.com"));
        banco.agregarCliente(new Cliente("C004", "Ana Torres", "Calle 4", "321654987", "ana@gmail.com"));
        banco.agregarCliente(new Cliente("C005", "Luis Fernández", "Calle 5", "159753486", "luis@gmail.com"));
        banco.agregarCliente(new Cliente("C006", "Sofía Martínez", "Calle 6", "753159486", "sofia@gmail.com"));
        banco.agregarCliente(new Cliente("C007", "Javier Ruiz", "Calle 7", "852741963", "javier@gmail.com"));
        banco.agregarCliente(new Cliente("C008", "Laura Jiménez", "Calle 8", "963258741", "laura@gmail.com"));
        banco.agregarCliente(new Cliente("C009", "Pedro Sánchez", "Calle 9", "147258369", "pedro@gmail.com"));
        banco.agregarCliente(new Cliente("C010", "Clara Romero", "Calle 10", "258963147", "clara@gmail.com"));

        // Imprimir clientes del banco
        for (Cliente cliente : banco.getClientes()) {
            System.out.println("ID: " + cliente.getIdCliente() + ", Nombre: " + cliente.getNombre());
        }
    }
}

