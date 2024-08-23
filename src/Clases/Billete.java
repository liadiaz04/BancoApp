package Clases;

public class Billete {
    private TipoBillete tipo;
    private int cantidad;

    public Billete(TipoBillete tipo, int cantidad) {
        setTipo(tipo);
        setCantidad(cantidad);
    }

    public TipoBillete getTipo() {
        return tipo;
    }

    public void setTipo(TipoBillete tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int valorTotal() {
        return tipo.getValor() * cantidad; // Calcula el valor total del billete
    }

    @Override
    public String toString() {
        return "Billete{" +
                "tipo=" + tipo +
                ", cantidad=" + cantidad +
                ", valor total=" + valorTotal() +
                '}';
    }
}
