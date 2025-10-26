/**
 * Representa una celda individual en el tablero
 */
public class Celda {
    private Barco barco;
    private boolean disparada;
    
    public Celda() {
        this.barco = null;
        this.disparada = false;
    }
    
    public boolean tieneBarco() {
        return barco != null;
    }
    
    public void setBarco(Barco barco) {
        this.barco = barco;
    }
    
    public Barco getBarco() {
        return barco;
    }
    
    public void marcarDisparada() {
        this.disparada = true;
    }
    
    public boolean estaDisparada() {
        return disparada;
    }
}