/**
 * Representa un jugador en el juego
 */
public class Jugador {
    private String nombre;
    private Tablero tablero;
    private int puntuacion;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.tablero = new Tablero();
        this.puntuacion = 0;
    }
    
    // Getters simples
    public String getNombre() {
        return nombre;
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }
    
    public void incrementarPuntuacion() {
        puntuacion++;
    }
}