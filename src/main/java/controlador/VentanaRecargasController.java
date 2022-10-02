package controlador;

import Aplication.CerrarVentanas;
import BasesDeDatos.DatosBD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alan_
 */
public class VentanaRecargasController implements Initializable {
    
    @FXML    private Button btnRecarga10;
    @FXML    private Button btnRecarga20;
    @FXML    private Button btnRecarga30;
    @FXML    private Button btnRecarga50;
    @FXML    private Button btnRecarga100;
    @FXML    private Button btnRecarga150;
    //BOTONES PAQUETE
    @FXML    private Button btnPaquete10;
    @FXML    private Button btnPaquete30;
    @FXML    private Button btnPaquete50;
    @FXML    private Button btnPaquete100;
    @FXML    private Button btnPaquete200;
    @FXML    private Button btnCancelar;
    @FXML    private Button btnRecargar;
    @FXML    private ComboBox<String> cbOperador;
    @FXML    private TextField tfNumeroCelular;
    @FXML    private Button btnSiguiente;
    @FXML    private TextField tfNumeroConfirmado;
    @FXML    private Accordion accRecarga;
    //Declaraciones
    private static final String[] companiasCB = {"UNEFON", "TELCEL", "AT&T", "MOVISTAR"};
    private CerrarVentanas cerrar = new CerrarVentanas();
    private DatosBD bd = new DatosBD();
    private ArrayList<String> INFORMACION;
    private int valorBoton=-1;
    private boolean tipoBoton; //Si es false es Recarga, si es True es Paquete
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            accRecarga.setVisible(false);
            btnRecargar.setVisible(false);
            cbOperador.getItems().addAll(companiasCB);
            INFORMACION = new ArrayList<>();
            llenarContenidoRecargas();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaRecargasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void closeWindow() {
        cerrar.cerrarVentanaRecargas(btnCancelar);
    }

    @FXML
    private void CancelarRecarga(MouseEvent event) {
        closeWindow();
    }

    @FXML
    private void ElegirRecarga(MouseEvent event) {
        if(!(verificarNumero(tfNumeroCelular.getText())==true && verificarNumero(tfNumeroConfirmado.getText())==true)){
            System.out.println("Debes de ingresar un numero");
        }
        if(!(tfNumeroCelular.getText().equals(tfNumeroConfirmado.getText()))){
                System.out.println("Los numeros deben ser iguales");
        }
        if(!(cbOperador.getValue()!=null)){
            System.out.println("Debes seleccionar alguna compania");
        }
        accRecarga.setVisible(true);
    }
    
    @FXML
    private void Recarga(MouseEvent event) {
        if(event.getSource()==btnRecarga10)
            valorBoton=10;
        if(event.getSource()==btnRecarga20)
            valorBoton=20;
        if(event.getSource()==btnRecarga30)
            valorBoton=30;
        if(event.getSource()==btnRecarga50)
            valorBoton=50;
        if(event.getSource()==btnRecarga100)
            valorBoton=100;
        if(event.getSource()==btnRecarga150)
            valorBoton=150;
        tipoBoton=false;
        btnRecargar.setVisible(true);
    }

    @FXML
    private void Paquete(MouseEvent event) {
        if(event.getSource()==btnPaquete10)
            valorBoton=10;
        if(event.getSource()==btnPaquete30)
            valorBoton=30;
        if(event.getSource()==btnPaquete50)
            valorBoton=50;
        if(event.getSource()==btnPaquete100)
            valorBoton=100;
        if(event.getSource()==btnPaquete200)
            valorBoton=200;
        tipoBoton=true;
        btnRecargar.setVisible(true);
    }

    @FXML
    private void realizarRecarga(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MetodoPago.fxml"));
            Parent root = loader.load();
            MetodoPagoController controlador = loader.getController();
            controlador.infoRecargas(cbOperador.getValue(),tipoBoton);
            controlador.reciboTotal(valorBoton+.0);
            controlador.pasarVentanaAnterior("Recargas");
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> controlador.closeWindow());
            Stage myStage = (Stage) this.btnRecargar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(VentanaRecargasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean verificarNumero(String numero){
        boolean regreso=false;
        if(numero != null && numero.matches("[0-9]+")){
            System.out.println("Es un numero");
            //Comprobar si es de 10 digitos
            if(numero.length()==10){
                System.out.println("Es un numero correcto");
                regreso=true;
            }
        }
        return regreso;
    }
    
    private void llenarContenidoRecargas() throws SQLException{
        bd.conexionInfoRecargas(INFORMACION);
        //LLenamos los botones con la informacion de cada recarga en la BD
        btnRecarga10.setText(INFORMACION.get(2));
        btnRecarga20.setText(INFORMACION.get(6));
        btnRecarga30.setText((INFORMACION.get(10)));
        btnRecarga50.setText((INFORMACION.get(14)));
        btnRecarga100.setText((INFORMACION.get(18)));
        btnRecarga150.setText((INFORMACION.get(22)));
        //LLenamos los botones con la informacion de cada Paquete en la BD
        btnPaquete10.setText(INFORMACION.get(26));
        btnPaquete30.setText(INFORMACION.get(30));
        btnPaquete50.setText((INFORMACION.get(34)));
        btnPaquete100.setText((INFORMACION.get(38)));
        btnPaquete200.setText((INFORMACION.get(42)));
    }

    
    
}
