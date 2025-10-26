import java.util.Random;

/**
 * Controla la lógica principal del juego
 */
public class Juego {
    private Jugador jugador;
    private Jugador computadora;
    private boolean turnoJugador;
    private Random random;
    
    public Juego() {
        this.random = new Random();
        this.turnoJugador = true; // El jugador comienza
    }
    
    public void inicializarJugadores(String nombreJugador, String nombreComputadora) {
        this.jugador = new Jugador(nombreJugador);
        this.computadora = new Jugador(nombreComputadora);
    }
    
    public void colocarBarcosAutomaticamente() {
        // Colocar barcos para el jugador
        jugador.getTablero().colocarBarcosAleatorios();
        computadora.getTablero().colocarBarcosAleatorios();
    }
    
    public boolean jugadorDispara(int fila, int columna) {
        return computadora.getTablero().recibirDisparo(fila, columna);
    }
    
    public boolean computadoraDispara() {
        int fila, columna;
        boolean disparoValido = false;
        
        // La computadora dispara aleatoriamente
        do {
            fila = random.nextInt(10);
            columna = random.nextInt(10);
            disparoValido = !jugador.getTablero().yaDisparado(fila, columna);
        } while (!disparoValido);
        
        System.out.println("Computadora dispara en: " + fila + "," + columna);
        return jugador.getTablero().recibirDisparo(fila, columna);
    }
    
    public boolean verificarFinJuego() {
        return jugador.getTablero().todosBarcosHundidos() || 
               computadora.getTablero().todosBarcosHundidos();
    }
    
    public void cambiarTurno() {
        turnoJugador = !turnoJugador;
    }
    
    public boolean esTurnoJugador() {
        return turnoJugador;
    }
    
    // ===== MÉTODOS GETTER QUE FALTABAN =====
    public Jugador getJugador() {
        return jugador;
    }
    
    public Jugador getComputadora() {
        return computadora;
    }
}