# Batalla Naval En Bolivia

Un juego clásico de Batalla Naval implementado en Java con interfaz gráfica Swing, para poder compreender la progrmacion orientada a objetos.

# Características

- **🎮 Interfaz gráfica intuitiva** - Dos tableros interactivos
- **🤖 IA básica** - Computadora con disparos aleatorios
- **🎯 Sistema de turnos** - Alternancia entre jugador y computadora
- **💥 Detección inteligente** - Impactos, hundimientos y fin del juego
- **🎨 Visualización clara** - Colores y símbolos para cada estado
- **🔄 Reinicio completo** - Nuevas partidas con un clic

# Capturas de Pantalla

<img width="1226" height="919" alt="image" src="https://github.com/user-attachments/assets/e8a4815d-d33a-4a13-b071-db357fe752f9" />

# Tecnologías Utilizadas

- Java 20 - Lenguaje de programación
- Swing - Interfaz gráfica de usuario
- Random - Generación de posiciones aleatorias

## 📦 Estructura del Proyecto
BatallaNaval/
├── src/
│ ├── BatallaNaval.java # Clase principal
│ ├── InterfazGrafica.java # Interfaz gráfica
│ ├── Juego.java # Lógica del juego
│ ├── Jugador.java # Modelo de jugador
│ ├── Tablero.java # Gestión del tablero
│ ├── Barco.java # Modelo de barco
│ └── Celda.java # Modelo de celda
└── README.md


# Cómo Jugar

1. Iniciar juego: Haz clic en "Nuevo Juego"
2. Ingresar nombre: Escribe tu nombre cuando se solicite
3. Disparar: Haz clic en el tablero enemigo para disparar
4. Turnos: Alterna entre tu turno y el de la computadora
5. Objetivo: Hundir todos los barcos enemigos antes de que hundan los tuyos

# Ayuda Visual

| Símbolo | Color           | Descripción |
|---------|-----------------|-------------|
| `▓`     | 🟦 Gris Oscuro  | Tus barcos  |
| `X`     | 🔴 Rojo         | Impacto en barco |
| `Ø`     | 🟠 Naranja      | Barco hundido |
| `•`     | 🔵 Azul         | Disparo al agua |
| `·`     | 🔵 Azul Claro   | Océano/Agua |

# Instalación y Ejecución

# 2. MANUAL DE USUARIO
6.1 Instalación
Requisitos del Sistema
Java Runtime Environment (JRE) 8 o superior

500 MB de espacio libre

Resolución mínima 1024x768

Pasos de Instalación
Descargar los archivos del proyecto

Abrir terminal en la carpeta del proyecto

Compilar: javac *.java

Ejecutar: java BatallaNaval

# 6.2 Guía de Uso
1. Inicio del Juego
Ejecutar la aplicación

Hacer clic en "Nuevo Juego"

Ingresar nombre cuando se solicite

Los barcos se colocan automáticamente

Durante el Juego
Tu turno: Haz clic en el tablero enemigo (derecho)

Turno PC: Espera a que la computadora dispare

Símbolos:

▓ = Tus barcos

X = Impacto

Ø = Barco hundido

• = Agua

Fin del Juego
Victoria: Hundes todos los barcos enemigos

Derrota: La PC hunde todos tus barcos

Reinicio: Botón "Nuevo Juego"

3. DIAGRAMAS UML


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
