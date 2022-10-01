
package Aplication;

import controlador.VentanaLoginController;
import controlador.VentasController;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Clase para Darle al usuario para que seleccione el metodo de pago a su eleccion
 * @author Alancalderon
 * 
 */
public class CerrarVentanas {
    private ObservableList<Map> carritoCV = FXCollections.observableArrayList();
    private double TotalVenta;
    
    public void cerrarVentanaMP(String ventanaRegreso,Button BTNSalir){
        if(ventanaRegreso.equals("Ventas")){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Ventas.fxml"));
                Parent root = loader.load();
                VentasController controlador = loader.getController();
                controlador.recivoCarrito(carritoCV);
                controlador.recivoTotal(TotalVenta);
                controlador.pintarCarrito();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                stage.setScene(scene);
                stage.show();
                
                stage.setOnCloseRequest(e -> controlador.closeWindow());
                Stage myStage = (Stage) BTNSalir.getScene().getWindow();
                myStage.close();
            } catch (IOException ex) {
                Logger.getLogger(CerrarVentanas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(ventanaRegreso.equals("Recargas")){
            System.out.println("Mostraremos la ventana Recargas");
        }
        if(ventanaRegreso.equals("PagoServicios")){
            System.out.println("Mostraremos la ventana Pago de Servicios");
        }
    }
    
    public void cerrarVentanaVentas(Button BTNSalir) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ventanaLogin.fxml"));
            Parent root = loader.load();
            VentanaLoginController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> controlador.closeWindow());
            Stage myStage = (Stage) BTNSalir.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(CerrarVentanas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dameCarrito(ObservableList<Map> carritoAnterior){
        this.carritoCV = carritoAnterior;
    }
    
    public void dameTotalVenta(double TotalRecibido){
        this.TotalVenta=TotalRecibido;
    }
}
