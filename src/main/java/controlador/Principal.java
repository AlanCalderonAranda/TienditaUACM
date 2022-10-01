package controlador;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Version 0.1 Manda a la ventana de Ventas 
 * Clase Principal que Inicia nuestro sistema en la Pantalla Principal
 * @author Alan Calderon
 */
public class Principal extends Application{
    private static Scene scene;
    
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        scene = new Scene(loadFXML("/vista/ventanaLogin"));
        primaryStage.setScene(scene);
        primaryStage.show();

    } 
    private static Parent loadFXML(String fxml) throws IOException    {
        FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource(fxml+".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
        launch(args);
    } 
}
