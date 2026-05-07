package com.example.maquinaexpendedora;

public class VendingLogic {

    // Comprueba si queda stock del producto
    public static boolean hayStock(int stockActual) {
        return stockActual > 0;
    }

    // Comprueba si el saldo da para pagar
    public static boolean saldoSuficiente(double saldo, double precio) {
        return saldo >= precio;
    }

    // Calcula cuánto cambio hay que devolver
    public static double calcularCambio(double saldo, double precio) {
        return saldo - precio;
    }
}