package Interfaces;

/**
 * 
 * @author Alan Calderon
 */
public interface ITarjetas {
    public boolean validarNombre(String nombre);
    public boolean validarTarjeta(String tarjeta);
    public boolean validarAnio(String anio);
    public boolean validarCvv(String cvv);
}
