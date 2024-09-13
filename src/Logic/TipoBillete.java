package Logic;

public enum TipoBillete {
    Uno(1),
    Tres(3),
    Diez(10),
    Veinte(20),
    Cincuenta(50),
    Cien(100),
    Dosciento(200),
    Quiniento(500),
    Mil(1000);

    private final int valor;

    TipoBillete(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
