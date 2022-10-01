package controlador;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alanc
 */
public class VentanaLoginController implements Initializable {

    @FXML    private Label lbOlvideContraseña;
    @FXML    private TextField tfUsuario;
    @FXML    private PasswordField pfContrasenia;
    @FXML    private ImageView imgLogin;
    @FXML    private Button btnLogin;
    private ArrayList<String> arregloUsuarios = new ArrayList<>();
    private DatosBD bd = new DatosBD();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            bd.conexionEmpleadoRegistrado(arregloUsuarios);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML    private void OlvideContraseña(MouseEvent event) {
        
    }

    @FXML    private void Login(MouseEvent event) throws IOException, Exception{
        if (!(datosCorrectos() == 1)) {
            throw new Exception("Los datos ingresados no son correctos");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Ventas.fxml"));
        Parent root = loader.load();
        VentasController controlador = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> controlador.closeWindow());
        Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
        myStage.close();
    }
    
    private int datosCorrectos() {
        int valor = 0;
        for(int i=0;i<arregloUsuarios.size()/3;i++){
            if(arregloUsuarios.get((i*3)+2).equals(pfContrasenia.getText())){
                if(arregloUsuarios.get((i*3)+1).equals(tfUsuario.getText())){
                    valor=1;
                    break;
                }else{
                    valor=0;
                }
            }else{
                valor = 0;
            }
        }
        return valor;
    }
    
    public void closeWindow() {
        System.exit(0);
    }
}
