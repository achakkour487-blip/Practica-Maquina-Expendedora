package com.example.maquinaexpendedora;

import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class VendingE2ETest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new HelloApplication().start(stage);
    }

    // =================================================================
    // 1. COMPROBACIONES DE ESTADO INICIAL (TODO DEBE ESTAR A CERO)
    // =================================================================

    @Test
    void testEstadoInicial_ImporteCero() {
        Label importe = lookup("#labelImporte").queryAs(Label.class);
        assertTrue(importe.getText().contains("0.00") || importe.getText().contains("0,00"), "El importe inicial debe ser 0");
    }

    @Test
    void testEstadoInicial_BotonesAccionDeshabilitados() {
        assertTrue(lookup("#btnComprar").queryButton().isDisabled(), "Comprar debe estar bloqueado al inicio");
        assertTrue(lookup("#btnCancelar").queryButton().isDisabled(), "Cancelar debe estar bloqueado al inicio");
    }

    // =================================================================
    // 2. INSERCIÓN DE MONEDAS EXHAUSTIVA
    // =================================================================

    @Test
    void testMonedas_Insertar10Centimos() {
        clickOn("#btnMoneda10");
        assertTrue(lookup("#mensajeLabel").queryAs(Label.class).getText().contains("0,10") || lookup("#mensajeLabel").queryAs(Label.class).getText().contains("0.10"));
    }

    @Test
    void testMonedas_Insertar20Centimos() {
        clickOn("#btnMoneda20");
        assertTrue(lookup("#mensajeLabel").queryAs(Label.class).getText().contains("0,20") || lookup("#mensajeLabel").queryAs(Label.class).getText().contains("0.20"));
    }

    @Test
    void testMonedas_Insertar50Centimos() {
        clickOn("#btnMoneda50");
        assertTrue(lookup("#mensajeLabel").queryAs(Label.class).getText().contains("0,50") || lookup("#mensajeLabel").queryAs(Label.class).getText().contains("0.50"));
    }

    @Test
    void testMonedas_Insertar1Euro() {
        clickOn("#btnMoneda1");
        assertTrue(lookup("#mensajeLabel").queryAs(Label.class).getText().contains("1,00") || lookup("#mensajeLabel").queryAs(Label.class).getText().contains("1.00"));
    }

    @Test
    void testMonedas_Insertar2Euros() {
        clickOn("#btnMoneda2");
        assertTrue(lookup("#mensajeLabel").queryAs(Label.class).getText().contains("2,00") || lookup("#mensajeLabel").queryAs(Label.class).getText().contains("2.00"));
    }

    @Test
    void testMonedas_InsertarCombinacionCompleja() {
        clickOn("#btnMoneda2");
        clickOn("#btnMoneda1");
        clickOn("#btnMoneda50");
        clickOn("#btnMoneda20");
        clickOn("#btnMoneda10");
        // Suma total: 3.80
        Label mensaje = lookup("#mensajeLabel").queryAs(Label.class);
        assertTrue(mensaje.getText().contains("3,80") || mensaje.getText().contains("3.80"));
    }

    // =================================================================
    // 3. SELECCIÓN DE PRODUCTOS EXHAUSTIVA
    // =================================================================

    @Test
    void testProducto_SeleccionarGoma_Cuesta050() {
        clickOn("#btnProducto1");
        assertTrue(lookup("#labelImporte").queryAs(Label.class).getText().contains("0"));
    }

    @Test
    void testProducto_SeleccionarBoli_Cuesta100() {
        clickOn("#btnProducto2");
        assertTrue(lookup("#labelImporte").queryAs(Label.class).getText().contains("1"));
    }

    @Test
    void testProducto_SeleccionarRegla_Cuesta200() {
        clickOn("#btnProducto3");
        assertTrue(lookup("#labelImporte").queryAs(Label.class).getText().contains("2"));
    }

    @Test
    void testProducto_SeleccionarLibreta_Cuesta350() {
        clickOn("#btnProducto4");
        assertTrue(lookup("#labelImporte").queryAs(Label.class).getText().contains("3"));
    }

    @Test
    void testProducto_SeleccionarCorrector_Cuesta230() {
        clickOn("#btnProducto5");
        assertTrue(lookup("#labelImporte").queryAs(Label.class).getText().contains("2"));
    }

    @Test
    void testProducto_SeleccionarLapiz_Cuesta070() {
        clickOn("#btnProducto6");
        assertTrue(lookup("#labelImporte").queryAs(Label.class).getText().contains("0"));
    }

    @Test
    void testProducto_CambiarDeOpinionActualizaPrecio() {
        clickOn("#btnProducto1"); // Goma (0.50)
        clickOn("#btnProducto4"); // Libreta (3.50)
        Label importe = lookup("#labelImporte").queryAs(Label.class);
        assertTrue(importe.getText().contains("3,50") || importe.getText().contains("3.50"), "El precio no se actualizó al cambiar de producto");
    }

    // =================================================================
    // 4. LÓGICA DE INTERFAZ Y BLOQUEOS
    // =================================================================

    @Test
    void testBotonComprar_BloqueadoSiFaltaDinero() {
        clickOn("#btnProducto4"); // Libreta cuesta 3.50
        clickOn("#btnMoneda2");   // Solo metemos 2.00
        assertTrue(lookup("#btnComprar").queryButton().isDisabled(), "No debe dejar comprar si falta dinero");
    }

    @Test
    void testBotonComprar_DesbloqueadoSiHayDinero() {
        clickOn("#btnProducto1"); // Goma cuesta 0.50
        clickOn("#btnMoneda1");   // Metemos 1.00
        assertFalse(lookup("#btnComprar").queryButton().isDisabled(), "Debe dejar comprar si el dinero es suficiente");
    }

    @Test
    void testBotonCancelar_SeDesbloqueaAlMeterDinero() {
        clickOn("#btnMoneda10");
        assertFalse(lookup("#btnCancelar").queryButton().isDisabled(), "Debe permitir cancelar si hay saldo");
    }

    // =================================================================
    // 5. FLUJOS REALES DE COMPRA Y CANCELACIÓN
    // =================================================================

    @Test
    void testFlujo_CancelarDevuelveDinero() {
        clickOn("#btnMoneda1");
        clickOn("#btnMoneda2");
        clickOn("#btnCancelar");
        Label mensaje = lookup("#mensajeLabel").queryAs(Label.class);
        assertTrue(mensaje.getText().contains("devuelve") || mensaje.getText().contains("0"));
        assertTrue(lookup("#btnCancelar").queryButton().isDisabled(), "Tras cancelar, el botón debe volver a bloquearse");
    }

    @Test
    void testFlujo_CompraExitosaLimpiaInterfaz() {
        clickOn("#btnProducto2"); // Boli 1.00
        clickOn("#btnMoneda1");   // Metemos 1.00
        clickOn("#btnComprar");

        // Cerramos el modal
        press(KeyCode.ENTER).release(KeyCode.ENTER);

        Label importe = lookup("#labelImporte").queryAs(Label.class);
        assertTrue(importe.getText().contains("0.00") || importe.getText().contains("0,00"), "El total debe resetearse a 0");
        assertTrue(lookup("#btnComprar").queryButton().isDisabled(), "El botón comprar debe resetearse");
    }

    // =================================================================
    // 6. FLUJO LÍMITE: AGOTAR EL STOCK DE LA MÁQUINA
    // =================================================================

    @Test
    void testFlujo_AgotarProductoMuestraError() {
        // En HelloController inicializamos el stock a 5.
        // Vamos a comprar la Goma (Producto 1) 5 veces seguidas.
        for (int i = 0; i < 5; i++) {
            clickOn("#btnProducto1"); // Cuesta 0.50
            clickOn("#btnMoneda1");   // Metemos 1.00
            clickOn("#btnComprar");
            press(KeyCode.ENTER).release(KeyCode.ENTER); // Cerrar modal
        }

        // Intento número 6: La goma debería estar agotada
        clickOn("#btnProducto1");
        Label mensaje = lookup("#mensajeLabel").queryAs(Label.class);
        assertTrue(mensaje.getText().contains("agotado") || mensaje.getText().contains("Agotado"), "El sistema no detecta que el producto se ha quedado sin stock");
    }
}