package Aplication;

import javafx.scene.control.Alert;

/**
 * Clase para mandar notificaciones de los posibles errores o ACEPTACIONES
 * @author Alan Calderon
 */
public class Mensaje {
    public static void notificar(Alert.AlertType tipo, String titulo, String cabecera, String contenido){
        Alert notification = new Alert(tipo);
        notification.setHeaderText(cabecera);
        notification.setTitle(titulo);
        notification.setContentText(contenido);
        notification.showAndWait();
    }
}
