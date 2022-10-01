package BasesDeDatos;

import Aplication.Mensaje;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.scene.control.Alert;

/**
 * Esta clase es la encargada de realizar la conexion a la BD remota
 * @author Alan Calderon
 */
public class BD {
    public static final String URL = "jdbc:mysql://remotemysql.com:3306/KCO6XG0rOp";
    public static final String USSER = "KCO6XG0rOp";
    public static final String PASSWORD = "pgdfQFKVpF";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";//mysql-conector-java 8.0.24
    
    public Connection getConexion() throws SQLException{
        Connection conexion = null;
        try{
            Class.forName(DRIVER);
            conexion = (Connection) DriverManager.getConnection(URL,USSER,PASSWORD);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("¡NO CONECTADO! "+ Level.SEVERE + "e: "+ e);
            Mensaje.notificar(Alert.AlertType.ERROR, "mysql","Algo salio mal...",e.getMessage());
        }
        return conexion;
    }
    
}