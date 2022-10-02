package controlador;

import Aplication.CerrarVentanas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author alan_
 */
public class VentanaRecargasController implements Initializable {
    
    @FXML    private Button btnRecarga10;
    private CerrarVentanas cerrar = new CerrarVentanas();
    @FXML    private Button btnCancelar;
    private static final String[] companiasCB = {"UNEFON", "TELCEL", "AT&T", "MOVISTAR"};
    @FXML    private ComboBox<String> cbOperador;
    @FXML    private TextField tfNumeroCelular;
    @FXML    private Button btnSiguiente;
    @FXML    private TextField tfNumeroConfirmado;
    @FXML    private Accordion accRecarga;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accRecarga.setVisible(false);
        cbOperador.getItems().addAll(companiasCB);
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
    
}
