import java.util.Random;

/**
 * Representa el tablero de juego con celdas
 */
public class Tablero {
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;
    private Celda[][] celdas;
    private Barco[] barcos;
    private Random random;
    
    public Tablero() {
        this.celdas = new Celda[FILAS][COLUMNAS];
        this.barcos = new Barco[5]; // 5 barcos en total
        this.random = new Random();
        inicializarTablero();
        crearBarcos();
    }
    
    private void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }
    
    private void crearBarcos() {
        barcos[0] = new Barco("Portaaviones", 5);
        barcos[1] = new Barco("Destructor", 4);
        barcos[2] = new Barco("Submarino", 3);
        barcos[3] = new Barco("Fragata", 3);
        barcos[4] = new Barco("Lancha", 2);
    }
    
    public void colocarBarcosAleatorios() {
        for (Barco barco : barcos) {
            boolean colocado = false;
            
            while (!colocado) {
                int fila = random.nextInt(FILAS);
                int columna = random.nextInt(COLUMNAS);
                boolean horizontal = random.nextBoolean();
                
                colocado = intentarColocarBarco(barco, fila, columna, horizontal);
            }
        }
    }
    
    private boolean intentarColocarBarco(Barco barco, int filaInicio, int columnaInicio, boolean horizontal) {
        int tamaño = barco.getTamaño();
        
        // Verificar si cabe en el tablero
        if (horizontal) {
            if (columnaInicio + tamaño > COLUMNAS) return false;
        } else {
            if (filaInicio + tamaño > FILAS) return false;
        }
        
        // Verificar que no haya otros barcos
        for (int i = 0; i < tamaño; i++) {
            int fila = horizontal ? filaInicio : filaInicio + i;
            int columna = horizontal ? columnaInicio + i : columnaInicio;
            
            if (celdas[fila][columna].tieneBarco()) {
                return false;
            }
        }
        
        // Colocar el barco
        for (int i = 0; i < tamaño; i++) {
            int fila = horizontal ? filaInicio : filaInicio + i;
            int columna = horizontal ? columnaInicio + i : columnaInicio;
            
            celdas[fila][columna].setBarco(barco);
        }
        
        return true;
    }
    
    public boolean recibirDisparo(int fila, int columna) {
        if (fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) {
            return false; // Disparo inválido
        }
        
        Celda celda = celdas[fila][columna];
        celda.marcarDisparada();
        
        if (celda.tieneBarco()) {
            celda.getBarco().recibirDanio();
            return true;
        }
        
        return false;
    }
    
    public boolean yaDisparado(int fila, int columna) {
        return celdas[fila][columna].estaDisparada();
    }
    
    public boolean todosBarcosHundidos() {
        for (Barco barco : barcos) {
            if (!barco.estaHundido()) {
                return false;
            }
        }
        return true;
    }
    
    public int contarBarcosHundidos() {
        int count = 0;
        for (Barco barco : barcos) {
            if (barco.estaHundido()) {
                count++;
            }
        }
        return count;
    }
    
    // ===== MÉTODO GETTER QUE FALTABA =====
    public Celda getCelda(int fila, int columna) {
        if (fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS) {
            return celdas[fila][columna];
        }
        return null;
    }
}