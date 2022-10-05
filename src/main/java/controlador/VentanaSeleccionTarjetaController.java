package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author alanc
 */
public class VentanaSeleccionTarjetaController implements Initializable {

    @FXML    private Label lbTitulo;
    @FXML    private Label LebelTC;
    @FXML    private Label LebelTD;
    @FXML    private Button btnRegresar;
    @FXML    private Button jButtonCredito;
    @FXML    private Button jButtonDebito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void regresaVentana(ActionEvent event) {
        
    }

    @FXML
    private void accionPagarACredito(ActionEvent event) {
    }

    @FXML
    private void accionPagarADebito(ActionEvent event) {
    }
    
}
