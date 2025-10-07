package com.example.maquinaexpendedora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {

    @FXML private ImageView imgProducto1;
    @FXML private ImageView imgProducto2;
    @FXML private ImageView imgProducto3;
    @FXML private ImageView imgProducto4;
    @FXML private ImageView imgProducto5;
    @FXML private ImageView imgProducto6;
    @FXML
    private Button btnMoneda10;
    @FXML
    private Button btnMoneda50;
    @FXML
    private Label mensajeLabel;
    @FXML
    private Button btnMoneda1;
    @FXML
    private Label lblNombre2;
    @FXML
    private Label lblNombre1;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblNombre6;
    @FXML
    private Label lblNombre5;
    @FXML
    private Label lblNombre4;
    @FXML
    private Label lblNombre3;
    @FXML
    private Button btnProducto1;
    @FXML
    private Button btnProducto3;
    @FXML
    private Button btnProducto2;
    @FXML
    private Button btnProducto5;
    @FXML
    private Button btnMoneda2;
    @FXML
    private Button btnProducto4;
    @FXML
    private Button btnProducto6;
    @FXML
    private Button btnMoneda20;
    @FXML
    private Label lblPrecio1;
    @FXML
    private Label lblPrecio3;
    @FXML
    private Label lblPrecio2;
    @FXML
    private Label labelImporte;
    @FXML
    private Button btnComprar;
    @FXML
    private Label lblPrecio5;
    @FXML
    private Label lblPrecio4;
    @FXML
    private Label lblPrecio6;

    private double importeTotal = 0.0;
    private double saldoIngresado = 0.0;
    private int productoSeleccionado = -1;

    private final double[] precios = {0.50, 1.00, 2.00, 3.50, 2.30, 0.70};

    @FXML
    public void initialize() {
        imgProducto1.setImage(new Image(getClass().getResourceAsStream("/imagenes/goma.png")));
        imgProducto2.setImage(new Image(getClass().getResourceAsStream("/imagenes/boli.png")));
        imgProducto3.setImage(new Image(getClass().getResourceAsStream("/imagenes/regla.png")));
        imgProducto4.setImage(new Image(getClass().getResourceAsStream("/imagenes/libreta.png")));
        imgProducto5.setImage(new Image(getClass().getResourceAsStream("/imagenes/tipex.png")));
        imgProducto6.setImage(new Image(getClass().getResourceAsStream("/imagenes/lapiz.png")));
    }

    @FXML
    public void seleccionarProducto1(ActionEvent actionEvent) {
        productoSeleccionado = 0;
        importeTotal = precios[productoSeleccionado];
        labelImporte.setText(String.format("Total: %.2f €", importeTotal));
        mensajeLabel.setText("Producto seleccionado: " + lblNombre1.getText());
    }

    @FXML
    public void seleccionarProducto2(ActionEvent actionEvent) {
        productoSeleccionado = 1;
        importeTotal = precios[productoSeleccionado];
        labelImporte.setText(String.format("Total: %.2f €", importeTotal));
        mensajeLabel.setText("Producto seleccionado: " + lblNombre2.getText());
    }

    @FXML
    public void seleccionarProducto3(ActionEvent actionEvent) {
        productoSeleccionado = 2;
        importeTotal = precios[productoSeleccionado];
        labelImporte.setText(String.format("Total: %.2f €", importeTotal));
        mensajeLabel.setText("Producto seleccionado: " + lblNombre3.getText());
    }

    @FXML
    public void seleccionarProducto4(ActionEvent actionEvent) {
        productoSeleccionado = 3;
        importeTotal = precios[productoSeleccionado];
        labelImporte.setText(String.format("Total: %.2f €", importeTotal));
        mensajeLabel.setText("Producto seleccionado: " + lblNombre4.getText());
    }

    @FXML
    public void seleccionarProducto5(ActionEvent actionEvent) {
        productoSeleccionado = 4;
        importeTotal = precios[productoSeleccionado];
        labelImporte.setText(String.format("Total: %.2f €", importeTotal));
        mensajeLabel.setText("Producto seleccionado: " + lblNombre5.getText());
    }

    @FXML
    public void seleccionarProducto6(ActionEvent actionEvent) {
        productoSeleccionado = 5;
        importeTotal = precios[productoSeleccionado];
        labelImporte.setText(String.format("Total: %.2f €", importeTotal));
        mensajeLabel.setText("Producto seleccionado: " + lblNombre6.getText());
    }

    @FXML
    public void insertarMoneda10(ActionEvent actionEvent) {
        saldoIngresado += 0.10;
        actualizarSaldo();
    }

    @FXML
    public void insertarMoneda20(ActionEvent actionEvent) {
        saldoIngresado += 0.20;
        actualizarSaldo();
    }

    @FXML
    public void insertarMoneda50(ActionEvent actionEvent) {
        saldoIngresado += 0.50;
        actualizarSaldo();
    }

    @FXML
    public void insertarMoneda1(ActionEvent actionEvent) {
        saldoIngresado += 1.00;
        actualizarSaldo();
    }

    @FXML
    public void insertarMoneda2(ActionEvent actionEvent) {
        saldoIngresado += 2.00;
        actualizarSaldo();
    }

    private void actualizarSaldo() {
        mensajeLabel.setText(String.format("Saldo ingresado: %.2f €", saldoIngresado));
    }

    @FXML
    public void comprarProducto(ActionEvent actionEvent) {
        if (productoSeleccionado == -1) {
            mensajeLabel.setText("Por favor, selecciona un producto.");
            return;
        }
        if (saldoIngresado < importeTotal) {
            mensajeLabel.setText(String.format("Saldo insuficiente. Falta %.2f €", importeTotal - saldoIngresado));
            return;
        }
        double cambio = saldoIngresado - importeTotal;
        mensajeLabel.setText(String.format("Compra realizada. Cambio: %.2f €", cambio));
        // Reiniciar estado
        saldoIngresado = 0.0;
        importeTotal = 0.0;
        productoSeleccionado = -1;
        labelImporte.setText("Total: 0.00 €");
        actualizarSaldo();
    }

    @FXML
    public void cancelarOperacion(ActionEvent actionEvent) {
        if (saldoIngresado > 0) {
            mensajeLabel.setText(String.format("Operación cancelada. Se devuelve %.2f €", saldoIngresado));
        } else {
            mensajeLabel.setText("Operación cancelada.");
        }
        // Reiniciar estado
        saldoIngresado = 0.0;
        importeTotal = 0.0;
        productoSeleccionado = -1;
        labelImporte.setText("Total: 0.00 €");
        actualizarSaldo();
    }
}
