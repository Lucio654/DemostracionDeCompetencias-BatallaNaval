/**
 * Representa un barco en el juego
 */
public class Barco {
    private String nombre;
    private int tamaño;
    private int danioRecibido;
    private boolean hundido;
    
    public Barco(String nombre, int tamaño) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.danioRecibido = 0;
        this.hundido = false;
    }
    
    public void recibirDanio() {
        danioRecibido++;
        if (danioRecibido >= tamaño) {
            hundido = true;
            System.out.println("💥 ¡" + nombre + " hundido!");
        }
    }
    
    public boolean estaHundido() {
        return hundido;
    }
    
    // Getters simples
    public String getNombre() {
        return nombre;
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public int getDanioRecibido() {
        return danioRecibido;
    }
}