```mermaid
    classDiagram
    class BatallaNaval {
        +main(String[] args)
    }
    
    class InterfazGrafica {
        -Juego juego
        -JButton[][] botonesJugador
        -JButton[][] botonesComputadora
        -JLabel labelEstado
        +crearVentanaPrincipal()
        +iniciarJuego()
        +realizarDisparo(int, int)
    }
    
      class Juego {
        -Jugador jugador
        -Jugador computadora
        -boolean turnoJugador
        +inicializarJugadores(String, String)
        +colocarBarcosAutomaticamente()
        +jugadorDispara(int, int) boolean
        +computadoraDispara() boolean
        +verificarFinJuego() boolean
    }
    
    class Jugador {
        -String nombre
        -Tablero tablero
        -int puntuacion
        +getTablero() Tablero
        +getNombre() String
    }
    
    class Tablero {
        -Celda[][] celdas
        -Barco[] barcos
        -final int FILAS = 10
        -final int COLUMNAS = 10
        +colocarBarcosAleatorios()
        +recibirDisparo(int, int) boolean
        +todosBarcosHundidos() boolean
        +getCelda(int, int) Celda
    }
    
    class Barco {
        -String nombre
        -int tamaño
        -int danioRecibido
        -boolean hundido
        +recibirDanio()
        +estaHundido() boolean
    }
    
    class Celda {
        -Barco barco
        -boolean disparada
        +tieneBarco() boolean
        +marcarDisparada()
        +estaDisparada() boolean
    }
    
    BatallaNaval --> InterfazGrafica
    InterfazGrafica --> Juego
    Juego "1" *-- "2" Jugador
    Jugador "1" *-- "1" Tablero
    Tablero "1" *-- "100" Celda
    Tablero "1" *-- "5" Barco
    Celda "1" *-- "0..1" Barco
