
package BasesDeDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para hacer conexion a la BD por medio de la Clase BD
 * y obtener los datos que estan almacenados en la misma.
 * @author alanc
 */
public class DatosBD {
    private Statement sentencia;
    private BD con = new BD();
    private Connection conexion;
    
    public void conexionProductos(ArrayList<String> PRODUCTOS)throws SQLException{
        conexion = con.getConexion();
        try{
            sentencia = conexion.createStatement();
            boolean execute = sentencia.execute("SELECT * FROM `productos`");
            if(execute){
                ResultSet resultset = sentencia.getResultSet();
                while(resultset.next()){
                    //Guardamos en el ArrayList
                    PRODUCTOS.add(Integer.toString(resultset.getInt("idProducto")));
                    PRODUCTOS.add(resultset.getString("Nombre"));
                    PRODUCTOS.add(Double.toString(resultset.getDouble("Precio")));
                    PRODUCTOS.add(Integer.toString(resultset.getInt("Cantidad")));
                    PRODUCTOS.add(resultset.getString("Peso"));
                    PRODUCTOS.add(resultset.getString("Descripcion"));
                }
            }
        }catch(SQLException ex){
            Logger.getLogger(DatosBD.class.getName()).log(Level.SEVERE,null,ex); 
        }
        conexion.close();
    }
    
    public void conexionEmpleadoRegistrado(ArrayList<String> EMPLEADOS) throws SQLException {
        conexion = con.getConexion();
        try {
            sentencia = conexion.createStatement();
            boolean execute = sentencia.execute("SELECT * FROM `Empleados`");
            if (execute) {
                ResultSet resultset = sentencia.getResultSet();
                while (resultset.next()) {
                    //Guardamos en el ArrayList
                    EMPLEADOS.add(Integer.toString(resultset.getInt("idEmpleado")));
                    EMPLEADOS.add(resultset.getString("Usuario"));
                    EMPLEADOS.add(resultset.getString("Contraseña"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.close();
    }
    public void conexionInfoRecargas(ArrayList<String> INFORMACION) throws SQLException {
        conexion = con.getConexion();
        try {
            sentencia = conexion.createStatement();
            boolean execute = sentencia.execute("SELECT * FROM `InformacionRecargas`");
            if (execute) {
                ResultSet resultset = sentencia.getResultSet();
                while (resultset.next()) {
                    //Guardamos en el ArrayList
                    INFORMACION.add(Integer.toString(resultset.getInt("IDInformacion")));
                    INFORMACION.add(resultset.getString("Tipo"));
                    INFORMACION.add(resultset.getString("Mensaje"));
                    INFORMACION.add(Integer.toString(resultset.getInt("Precio")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.close();
    }
}
