package modelo;

/**
 *
 * @author alanc
 */
public class Producto {
    private int idProducto;
    private String Nombre;
    private Double Precio;
    private int Cantidad;
    private String Peso;
    private String Descripcion;

    public Producto() {
    }

    public Producto(int idProducto, String Nombre, Double Precio, int Cantidad, String Peso, String Descripcion) {
        this.idProducto = idProducto;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Peso = Peso;
        this.Descripcion = Descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String Peso) {
        this.Peso = Peso;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
   
    
}
