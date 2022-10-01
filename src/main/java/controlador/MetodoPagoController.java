package controlador;

import Aplication.CerrarVentanas;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author alanCalderon
 */
public class MetodoPagoController implements Initializable {

    @FXML    private Button btnEfectivo;
    @FXML    private Button btnTarjeta;
    @FXML    private Button btnCancelar;
    private Double totalVenta;
    private String ventanaAnterior;
    private FXMLLoader loader;
    private ObservableList<Map> carritoAnterior = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnPagoEnEfectivo(MouseEvent event) {
        
    }

    @FXML
    private void PagoConTarjeta(MouseEvent event) {
        
    }
    
    public void pasarTotal(Double totalVenta){
        this.totalVenta=totalVenta;
    }
    
    public void pasarVentanaAnterior(String ventanaAntes){
        this.ventanaAnterior = ventanaAntes;
    }

    void closeWindow() {
            CerrarVentanas cerrar = new CerrarVentanas();
            cerrar.dameCarrito(carritoAnterior);
            cerrar.cerrarVentanaMP(ventanaAnterior, btnCancelar);
    }
    
    @FXML
    private void cancelarMetodoPago(MouseEvent event) {
        closeWindow();
    }

    public void pasarCarrito(ObservableList<Map> carrito) {
        this.carritoAnterior=carrito;
    }
    
    public void dameCarrito(ObservableList<Map> carrito){
        carrito=this.carritoAnterior;
                
    }
    
}
