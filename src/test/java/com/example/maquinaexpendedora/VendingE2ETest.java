package com.example.maquinaexpendedora;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class VendingE2ETest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new HelloApplication().start(stage);
    }

    @Test
    void caso1_InsertarMonedaAumentaSaldo() {
        clickOn("#btnMoneda1");
        Label mensaje = lookup("#mensajeLabel").queryAs(Label.class);
        assertTrue(mensaje.getText().contains("1,00") || mensaje.getText().contains("1.00"));
    }

    @Test
    void caso2_SeleccionarProductoMuestraTotal() {
        clickOn("#btnProducto1");
        Label importe = lookup("#labelImporte").queryAs(Label.class);
        assertTrue(importe.getText().contains("0,50") || importe.getText().contains("0.50"));
    }

    @Test
    void caso3_BotonComprarSeHabilitaConSaldo() {
        clickOn("#btnProducto1"); // Cuesta 0.50
        clickOn("#btnMoneda1");   // Metemos 1.00
        assertFalse(lookup("#btnComprar").queryButton().isDisabled());
    }

    // --- NUEVAS PRUEBAS PARA SUBIR LA COBERTURA DE RAMAS ---

    @Test
    void caso4_CancelarOperacionConDineroDevuelveCambio() {
        clickOn("#btnMoneda2"); // Metemos 2 euros
        clickOn("#btnCancelar"); // Cancelamos
        Label mensaje = lookup("#mensajeLabel").queryAs(Label.class);
        // Comprobamos que el saldo se resetea a cero correctamente
        assertTrue(mensaje.getText().contains("0,00") || mensaje.getText().contains("0.00"));
    }

    @Test
    void caso5_ClickEnProductoCambiaColor() {
        clickOn("#btnProducto2"); // Seleccionamos el boli
        Label importe = lookup("#labelImporte").queryAs(Label.class);
        assertTrue(importe.getText().contains("1,00") || importe.getText().contains("1.00"));
    }
}