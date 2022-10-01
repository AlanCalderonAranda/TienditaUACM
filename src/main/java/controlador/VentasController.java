package controlador;

import BasesDeDatos.DatosBD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author alanc
 */
public class VentasController implements Initializable {
    @FXML    private ImageView IMGImagenProducto;
    @FXML    private Label LBNombre;
    @FXML    private Label LBPrecio;
    @FXML    private Label LBPeso;
    @FXML    private Button BTNChecarPRecio;
    @FXML    private Button BTNEliminarProducto;
    @FXML    private Button BTNProcesarVenta;
    @FXML    private Button BTNCancelarVenta;
    @FXML    private Button DuplicarProducto;
    @FXML    private Button BTNMultiplicarProducto;
    @FXML    private Button BTNRecargaTelefonica;
    @FXML    private Button BTNPagoDeServicios;
    @FXML    private Button BTNSalir;
    @FXML    private Button BTNAbrirCaja;
    @FXML    private TableView<Map> TWTablaCarrito;
    @FXML    private TableColumn<Producto, String> TCColumnaNombre;
    @FXML    private TableColumn<Producto, Double> TCColumnaPrecio;
    @FXML    private TableColumn<Producto, Integer> TCColumnaCantidad;
    @FXML    private TableColumn<Producto, String> TCColumnaTotal;
    @FXML    private TextField TFCodigoProducto;
    @FXML    private Button BTNAgregarProducto;
    
    private Producto productito = new Producto();
    private ArrayList<String> listProducto = new ArrayList<>();
    private DatosBD bbd = new DatosBD();
    private int guardai=-1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bbd.conexionProductos(listProducto);
        } catch (SQLException ex) {
            Logger.getLogger(VentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML    private void ConsultarPrecioProducto(ActionEvent event) {
        if(TFCodigoProducto.getText().isEmpty()){
            System.out.println("Para checar el precio tienes que ingresar el codigo del producto");
        }else{
            for(int i=0;i<listProducto.size()/6;i++){
                if(TFCodigoProducto.getText().equals(listProducto.get((i*6)))){
                    System.out.println("El producto cuesta: "+listProducto.get((i*6)+2));
                    break;
                }
            }
        }
    }

    @FXML    private void EliminarProductoCarrito(ActionEvent event) {
        
    }

    @FXML
    private void CancelarVentaTotal(ActionEvent event) {
        
    }

    @FXML
    private void DuplicarProducto(ActionEvent event) {
        
    }

    @FXML
    private void MultiplicarProducto(ActionEvent event) {
        
    }

    @FXML
    private void RealizarRecargaTelefonica(MouseEvent event){
    }
    
    @FXML
    private void PagoDeServicios(ActionEvent event){
    }

    @FXML    private void SalirVenta(ActionEvent event) {
        closeWindow();
    }

    @FXML    private void AbrirCajon(ActionEvent event) {
        
    }

    @FXML    private void AgregarProductoCarrito(ActionEvent event) {
        llenarCarrito();
    }
    
    private void llenarCarrito(){
        ObservableList<Map> lista = obtenetProductos();
        this.TCColumnaNombre.setCellValueFactory(new MapValueFactory("Nombre"));
        this.TCColumnaPrecio.setCellValueFactory(new MapValueFactory("Precio"));
        this.TCColumnaCantidad.setCellValueFactory(new MapValueFactory("Cantidad"));
        this.TWTablaCarrito.setItems(lista);
    }
    
    private ObservableList<Map> obtenetProductos(){
        if(buscaEnBDProducto(Integer.parseInt(TFCodigoProducto.getText()))==true){
            ObservableList<Map> carrito = FXCollections.observableArrayList();
            Producto Pcarrito = new Producto();
            Map<String, Object> coleccion = new HashMap<> ();
            Pcarrito.setNombre(listProducto.get(guardai+1));
            Pcarrito.setPrecio(Double.parseDouble(listProducto.get(guardai+2)));
            Pcarrito.setCantidad(Integer.parseInt(listProducto.get(guardai+3)));
            Pcarrito.setPeso(listProducto.get(guardai+4));
            coleccion.put("Nombre",Pcarrito.getNombre());
            coleccion.put("Precio",Pcarrito.getPrecio());
            coleccion.put("Cantidad",Pcarrito.getCantidad());
            coleccion.put("Total", Pcarrito.getPrecio());
            carrito.add(coleccion);
            return carrito;
        }else{
            return null;
        }
        
    }
    
    private boolean buscaEnBDProducto(int idProducto){
        boolean bandera = false;
        for(int i=0;i<listProducto.size()/6;i++){
            if(idProducto==(Integer.parseInt(listProducto.get(i*6)))){
                bandera=true;
                guardai=i*6;
                break;
            }else{
                bandera=false;
            }
        }
        return bandera;
    }
    
    public void closeWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ventanaLogin.fxml"));
            Parent root = loader.load();
            VentanaLoginController controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e->controlador.closeWindow());
            Stage myStage = (Stage) this.BTNSalir.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(VentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
