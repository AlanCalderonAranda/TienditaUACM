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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author alanCalderon
 */
public class MetodoPagoController implements Initializable {
    @FXML    private Label LBTotal;
    @FXML    private Label LBDescripcion;
    @FXML    private Label LBTipoCompra;
    @FXML    private Button btnEfectivo;
    @FXML    private Button btnTarjeta;
    @FXML    private Button btnCancelar;
    private Double totalVenta;
    private String ventanaAnterior,compania;
    private FXMLLoader loader;
    private ObservableList<Map> carritoAnterior = FXCollections.observableArrayList();
    private int totalArticulos;
    private boolean tipoRecarga;
    
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
    
    void closeWindow() {
            CerrarVentanas cerrar = new CerrarVentanas();
            if(ventanaAnterior.equals("Ventas")){
               cerrar.dameCarrito(carritoAnterior); 
            }
            cerrar.dameTotalVenta(totalVenta);
            cerrar.cerrarVentanaMP(ventanaAnterior, btnCancelar);
    }
    
    @FXML    private void cancelarMetodoPago(MouseEvent event) {
        closeWindow();
    }
    
    private void llenarInformacion(){
        if(ventanaAnterior.equals("Ventas")){
            LBTipoCompra.setText("Venta de productos");
            LBDescripcion.setText("Compra de Articulos en tienda\nTotal de Articulos: "+totalArticulos);
            LBTotal.setText(Double.toString(totalVenta));
        }
        //Recibo compania monto y Recarga/Paquete
        if (ventanaAnterior.equals("Recargas")) {
            if (tipoRecarga == false) {
                LBTipoCompra.setText("Recarga Telefonica");
                LBDescripcion.setText("Recarga Saldo ( "+compania +" ) " + totalVenta);
                LBTotal.setText(Double.toString(totalVenta));
            } else {
                LBTipoCompra.setText("Recarga en Paquete");
                LBDescripcion.setText("Recarga Saldo ( "+compania +" ) " + totalVenta);
                LBTotal.setText(Double.toString(totalVenta));
            }

        }
    }
    
    //PARA VENTAS
    public void InfoVentas(ObservableList<Map> carrito,int totalProductos) {
        this.carritoAnterior=carrito;
        this.totalArticulos=totalProductos;
    }
    
    public void dameCarrito(ObservableList<Map> carrito){
        carrito=this.carritoAnterior;           
    }
    
    public void infoRecargas(String compania,boolean tipoRecarga){
        this.tipoRecarga=tipoRecarga;
        this.compania=compania;
    }
    
    //Recibir Total que se pagara
    public void reciboTotal(Double totalVenta){
        this.totalVenta=totalVenta;
    }
    //Recibir de que ventana venimos
    public void pasarVentanaAnterior(String ventanaAntes){
        this.ventanaAnterior = ventanaAntes;
        llenarInformacion();
    }

}
