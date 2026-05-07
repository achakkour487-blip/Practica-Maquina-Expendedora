package com.example.maquinaexpendedora;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingLogicTest {

    @Test
    void testHayStock() {
        assertTrue(VendingLogic.hayStock(5));
        assertFalse(VendingLogic.hayStock(0));
    }

    @Test
    void testSaldoSuficiente() {
        assertTrue(VendingLogic.saldoSuficiente(2.0, 1.5));
        assertFalse(VendingLogic.saldoSuficiente(1.0, 2.5));
    }

    @Test
    void testCalcularCambio() {
        assertEquals(0.5, VendingLogic.calcularCambio(2.0, 1.5));
    }
}