package controlador;

import Aplication.CerrarVentanas;
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
import javax.swing.JOptionPane;
import modelo.Producto;

/**
 * FXML Controller class
 * Clase para realizar las acciones correspondientes de las 
 * ventas de productos de nuestra Tienda
 * @author Alan Calderon
 */
public class VentasController implements Initializable {
    @FXML    private ImageView IMGImagenProducto;
    @FXML    private Label LBNombre;
    @FXML    private Label LBPrecio;
    @FXML    private Label LBPeso;
    @FXML    private Label LBDescripcion;
    @FXML    private Label lbTotal;
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
    @FXML    private TextField TFCodigoProducto;
    @FXML    private Button BTNAgregarProducto;
    
    private Producto productito = new Producto();
    private ArrayList<String> listProducto = new ArrayList<>();
    private DatosBD bbd = new DatosBD();
    private int guardai=-1,totalArticulos=0;
    private Double TotalVenta=0.0;
    private ObservableList<Map> lista;
    private ObservableList<Map> carrito;
    private CerrarVentanas cerrar = new CerrarVentanas();
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bbd.conexionProductos(listProducto);
            lbTotal.setText(Double.toString(TotalVenta));
            LBDescripcion.setText(null);
            LBNombre.setText(null);
            LBDescripcion.setText(null);
            LBPrecio.setText(null);
            LBPeso.setText(null);
            carrito = FXCollections.observableArrayList();
        } catch (SQLException ex) {
            Logger.getLogger(VentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ConsultarPrecioProducto(MouseEvent event) throws Exception {
        if (!(TFCodigoProducto.getText() != null && TFCodigoProducto.getText().matches("[0-9]+"))) {
            throw new Exception("Para checar el precio debe ingresar el codigo correcto");
        }
        for (int i = 0; i < listProducto.size() / 6; i++) {
            if (TFCodigoProducto.getText().equals(listProducto.get((i * 6)))) {
                System.out.println("El producto cuesta: " + listProducto.get((i * 6) + 2));
                break;
            }
        }
    }

    @FXML    private void EliminarProductoCarrito(ActionEvent event) throws Exception{
        if(carrito==null || lista==null || carrito.size()==0 || lista.size()==0){
            throw new Exception("Carrito nulo o vacio");
        }
        totalArticulos--;
        TotalVenta -= TCColumnaPrecio.getCellData(TWTablaCarrito.getSelectionModel().getSelectedIndex());
        lbTotal.setText(Double.toString(TotalVenta));
        carrito.remove(TWTablaCarrito.getSelectionModel().getSelectedIndex());
        pintarCarrito();
    }

    @FXML
    private void CancelarVentaTotal(ActionEvent event) {
        lista.clear();
        carrito.clear();
        TotalVenta = 0.0;
        lbTotal.setText(Double.toString(TotalVenta));
    }

    @FXML
    private void DuplicarProducto(ActionEvent event) throws Exception {
        if(carrito==null || lista==null || carrito.size()==0 || lista.size()==0){
            throw new Exception("Carrito nulo o vacio");
        }
        for(int i=0;i<carrito.size();i++){
            System.out.println(carrito.get(i));
            System.out.println(lista.get(i));
        }
    }

    @FXML
    private void MultiplicarProducto(ActionEvent event) {
        int valor = 0,idproduct=0;
        boolean productoCorrecto = true;
        do {
            valor = Integer.parseInt(JOptionPane.showInputDialog("Multiplicar: "));
            if (!(valor <= 0 )) {
                while(productoCorrecto){
                    idproduct = Integer.parseInt(JOptionPane.showInputDialog("idProducto: "));
                    if(buscaEnBDProducto(idproduct)==true){
                        productoCorrecto=false;//Para romper el while
                        do{
                            llenarCarrito(idproduct);
                            valor--;
                        }while(valor>0);
                        valor=1; //Le asignamos 1 para romer el primer do while
                    }
                }
            }
        }while (valor <= 0);
    }
        
    @FXML
    private void RealizarRecargaTelefonica(MouseEvent event) throws Exception{
        if(TWTablaCarrito.getItems().isEmpty()){
            cerrar.cerrarVentanaMP("Recargas", BTNRecargaTelefonica);
        }else{
            System.out.println("El carrito Tiene Elementos");
        }
        
    }
    
    @FXML
    private void PagoDeServicios(ActionEvent event) throws Exception{
        if(TWTablaCarrito.getItems().isEmpty()){
            cerrar.cerrarVentanaMP("PagoServicios", BTNRecargaTelefonica);
        }else{
            System.out.println("El carrito Tiene Elementos");
        }
    }

    @FXML    private void SalirVenta(ActionEvent event){
        closeWindow();
    }

    @FXML    private void AbrirCajon(ActionEvent event) {
        
    }

    @FXML
    private void AgregarProductoCarrito(ActionEvent event) throws Exception {
        if (!(TFCodigoProducto.getText() != null && TFCodigoProducto.getText().matches("[0-9]+"))) {
            throw new Exception("Debe ser numero");
        }
        llenarCarrito(Integer.parseInt(TFCodigoProducto.getText()));
    }
    
    @FXML
    private void RealizarVenta(MouseEvent event) throws Exception {
        if(carrito==null || lista==null || carrito.size()==0 || lista.size()==0){
            throw new Exception("Carrito nulo o vacio");
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MetodoPago.fxml"));
            Parent root = loader.load();
            MetodoPagoController controlador = loader.getController();
            controlador.reciboTotal(TotalVenta);
            controlador.InfoVentas(carrito,totalArticulos);
            controlador.pasarVentanaAnterior("Ventas");
            
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
    
    public void recivoTotal(Double totalRecibido){
        this.TotalVenta=totalRecibido;
        this.lbTotal.setText(Double.toString(totalRecibido));
    }
    
    public void recivoCarrito(ObservableList<Map> carritoRegreso){
        this.carrito=carritoRegreso;
        this.totalArticulos=carrito.size();
    }
    
    
    private void llenarCarrito(int idProducto){
        lista = obtenetProductos(idProducto);
        this.TCColumnaNombre.setCellValueFactory(new MapValueFactory("Nombre"));
        this.TCColumnaPrecio.setCellValueFactory(new MapValueFactory("Precio"));
        this.TCColumnaCantidad.setCellValueFactory(new MapValueFactory("Cantidad"));
        this.TWTablaCarrito.setItems(lista);
    }
    
    private ObservableList<Map> obtenetProductos(int idProducto){
        if(buscaEnBDProducto(idProducto)==true){
            Producto Pcarrito = new Producto();
            Map<String, Object> coleccion = new HashMap<> ();
            Pcarrito.setIdProducto(guardai);
            Pcarrito.setNombre(listProducto.get(guardai+1));
            Pcarrito.setPrecio(Double.parseDouble(listProducto.get(guardai+2)));
            Pcarrito.setCantidad(Integer.parseInt(listProducto.get(guardai+3)));
            Pcarrito.setPeso(listProducto.get(guardai+4));
            Pcarrito.setDescripcion(listProducto.get(guardai+5));
            //Actualizamos el Total que debe pagar el cliente por los productos en su carrito
            TotalVenta+=Pcarrito.getPrecio();
            lbTotal.setText(Double.toString(TotalVenta));
            totalArticulos++;
            //Mostramos en pantalla los datos del producto que desea comprar
            mostrarDatosProducto(Pcarrito);
            coleccion.put("Nombre",Pcarrito.getNombre());
            coleccion.put("Precio",Pcarrito.getPrecio());
            coleccion.put("Cantidad",1);
            coleccion.put("Total", Pcarrito.getPrecio());
            carrito.add(coleccion);
            TFCodigoProducto.setText(null);
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
    
    private void mostrarDatosProducto(Producto productoAMostrar){
        LBNombre.setText(productoAMostrar.getNombre());
        LBPrecio.setText(Double.toString(productoAMostrar.getPrecio()));
        LBPeso.setText(productoAMostrar.getPeso());
        LBDescripcion.setText(productoAMostrar.getDescripcion());
    }
    
    public void pintarCarrito(){
        lista=carrito;
        this.TCColumnaNombre.setCellValueFactory(new MapValueFactory("Nombre"));
        this.TCColumnaPrecio.setCellValueFactory(new MapValueFactory("Precio"));
        this.TCColumnaCantidad.setCellValueFactory(new MapValueFactory("Cantidad"));
        this.TWTablaCarrito.setItems(lista);
    }
    
    
    public void closeWindow(){
        CerrarVentanas cerrar1 = new CerrarVentanas();
        cerrar1.cerrarVentanaVentas(this.BTNSalir);
    }
    
}