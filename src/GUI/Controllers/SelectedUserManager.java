package GUI.Controllers;

import Clases.Banco;
import Clases.Cliente;

public class SelectedUserManager {
    private static SelectedUserManager instancia;
    private Cliente clienteSeleccionado;

    public static SelectedUserManager getInstancia() {
        if (instancia == null) {
            instancia = new SelectedUserManager();
        }
        return instancia;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente cliente) {
        this.clienteSeleccionado = cliente;
    }
}
