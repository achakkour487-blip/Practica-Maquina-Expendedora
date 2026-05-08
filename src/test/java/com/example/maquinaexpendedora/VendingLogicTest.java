package com.example.maquinaexpendedora;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class VendingLogicTest {

    // --- PRUEBAS DE STOCK ---

    @Test
    @DisplayName("Stock abundante")
    void testHayStockAbundante() {
        assertTrue(VendingLogic.hayStock(50), "Debería haber stock con 50 unidades");
    }

    @Test
    @DisplayName("Stock al límite (1 unidad)")
    void testHayStockMinimo() {
        assertTrue(VendingLogic.hayStock(1), "Debería haber stock con 1 unidad");
    }

    @Test
    @DisplayName("Stock vacío (0 unidades)")
    void testHayStockCero() {
        assertFalse(VendingLogic.hayStock(0), "No debería haber stock con 0 unidades");
    }

    @Test
    @DisplayName("Stock corrupto o negativo")
    void testHayStockNegativo() {
        assertFalse(VendingLogic.hayStock(-5), "El stock negativo debe dar falso por seguridad");
    }

    // --- PRUEBAS DE SALDO ---

    @Test
    @DisplayName("Saldo muy superior al precio")
    void testSaldoMuySuficiente() {
        assertTrue(VendingLogic.saldoSuficiente(10.0, 1.50), "10€ son más que suficientes para 1.50€");
    }

    @Test
    @DisplayName("Saldo exacto al céntimo")
    void testSaldoExacto() {
        assertTrue(VendingLogic.saldoSuficiente(2.30, 2.30), "2.30€ deben bastar para pagar 2.30€");
    }

    @Test
    @DisplayName("Saldo insuficiente por 1 céntimo")
    void testSaldoFaltaUnCentimo() {
        assertFalse(VendingLogic.saldoSuficiente(0.99, 1.00), "0.99€ no es suficiente para 1.00€");
    }

    @Test
    @DisplayName("Saldo a cero")
    void testSaldoCero() {
        assertFalse(VendingLogic.saldoSuficiente(0.0, 1.50), "No se puede pagar con 0€");
    }

    @Test
    @DisplayName("Precio a cero (producto gratis)")
    void testPrecioCero() {
        assertTrue(VendingLogic.saldoSuficiente(0.0, 0.0), "Debería permitir sacar un producto de 0€ con 0€");
    }

    // --- PRUEBAS DE CÁLCULO DE CAMBIO ---

    @Test
    @DisplayName("Devolución de cambio normal")
    void testCalcularCambioNormal() {
        assertEquals(0.50, VendingLogic.calcularCambio(2.00, 1.50), 0.001, "El cambio de 2€ - 1.50€ debe ser 0.50€");
    }

    @Test
    @DisplayName("Compra exacta (Cero cambio)")
    void testCalcularCambioCero() {
        assertEquals(0.00, VendingLogic.calcularCambio(3.50, 3.50), 0.001, "No debe haber cambio si se paga exacto");
    }

    @Test
    @DisplayName("Cambio con muchos decimales")
    void testCalcularCambioDecimales() {
        assertEquals(1.30, VendingLogic.calcularCambio(2.00, 0.70), 0.001, "El cambio de 2€ - 0.70€ debe ser 1.30€");
    }
}