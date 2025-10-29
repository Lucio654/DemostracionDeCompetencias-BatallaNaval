# Batalla Naval En Bolivia

Un juego clásico de Batalla Naval implementado en Java con interfaz gráfica Swing, para poder compreender la progrmacion orientada a objetos.

## Características

- **🎮 Interfaz gráfica intuitiva** - Dos tableros interactivos
- **🤖 IA básica** - Computadora con disparos aleatorios
- **🎯 Sistema de turnos** - Alternancia entre jugador y computadora
- **💥 Detección inteligente** - Impactos, hundimientos y fin del juego
- **🎨 Visualización clara** - Colores y símbolos para cada estado
- **🔄 Reinicio completo** - Nuevas partidas con un clic

## Capturas de Pantalla

<img width="1226" height="919" alt="image" src="https://github.com/user-attachments/assets/e8a4815d-d33a-4a13-b071-db357fe752f9" />

## Tecnologías Utilizadas

- Java 20 - Lenguaje de programación
- Swing - Interfaz gráfica de usuario
- Random - Generación de posiciones aleatorias

## Estructura del Proyecto
BatallaNaval/
├── src/
│ ├── Principal.java 
│ ├── BatallaNavalGUI.java 
│ ├── InterfazGrafica.java
│ ├── Juego.java
│ ├── Jugador.java
│ ├── Tablero.java 
│ ├── Barco.java 
│ └── Celda.java
└── README.md


## Cómo Jugar

1. Iniciar juego: Haz clic en "Nuevo Juego"
2. Ingresar nombre: Escribe tu nombre cuando se solicite
3. Disparar: Haz clic en el tablero enemigo para disparar
4. Turnos: Alterna entre tu turno y el de la computadora
5. Objetivo: Hundir todos los barcos enemigos antes de que hundan los tuyos

## Ayuda Visual

| Símbolo | Color           | Descripción |
|---------|-----------------|-------------|
| `▓`     | 🟦 Gris Oscuro  | Tus barcos  |
| `X`     | 🔴 Rojo         | Impacto en barco |
| `Ø`     | 🟠 Naranja      | Barco hundido |
| `•`     | 🔵 Azul         | Disparo al agua |
| `·`     | 🔵 Azul Claro   | Océano/Agua |

# Instalación y Ejecución

## MANUAL DE USUARIO
Instalación: 

Requisitos del Sistema
Java Runtime Environment (JRE) 8 o superior

500 MB de espacio libre

Resolución mínima 1024x768

Pasos de Instalación

1. Descargar los archivos del proyecto
2. Abrir terminal en la carpeta del proyecto
3. Compilar: javac *.java
4. Ejecutar: java BatallaNaval

## Guía de Uso

1.Inicio del Juego
2.Ejecutar la aplicación
3.Hacer clic en "Nuevo Juego"
4.Ingresar nombre cuando se solicite
5.Los barcos se colocan automáticamente

Durante el Juego

1.Tu turno: Haz clic en el tablero enemigo (derecho)
2.Turno PC: Espera a que la computadora dispare

Símbolos:

▓ = Tus barcos

X = Impacto

Ø = Barco hundido

• = Agua

### Fin del Juego

1.Victoria: Hundes todos los barcos enemigos
2.Derrota: La PC hunde todos tus barcos
3.Reinicio: Botón "Nuevo Juego"

## 3. DIAGRAMAS UML

### 3.1 Diagrama de Casos de Uso:

```mermaid
flowchart TB
    subgraph ACTORES
        direction TB
        A[JUGADOR]
        B[SISTEMA]
    end
    
    subgraph CASOS_DE_USO_JUGADOR
        C[Iniciar Nuevo Juego]
        D[Realizar Disparo]
        E[Ver Tableros]
        F[Consultar Estado]
        G[Salir del Juego]
    end
    
    subgraph CASOS_DE_USO_SISTEMA
        H[Colocar Barcos Automáticamente]
        I[Generar Disparo Computadora]
        J[Validar Disparo]
        K[Verificar Hundimiento]
        L[Determinar Ganador]
        M[Actualizar Interfaz]
    end
    
    A --> C
    A --> D
    A --> E
    A --> F
    A --> G
    
    B --> H
    B --> I
    B --> J
    B --> K
    B --> L
    B --> M
    
    C ==> H
    D ==> J
    J ==> K
    K ==> L
    K -.-> M
    L -.-> M
```


### Explicación del Diagrama:

1. Este diagrama muestra las interacciones entre el jugador y el sistema, identificando todas las funcionalidades principales que puede realizar cada actor durante el juego.

### Relaciones Principales:

1. Jugador → Sistema: El jugador inicia acciones y el sistema responde.
2. Inclusión (==>): Al iniciar juego se incluye colocar barcos, al disparar se incluye validación.
3.Extensión (-.->): La actualización de interfaz se extiende desde hundimiento y determinación de ganador.

### Características Claves:

1. 7 casos de uso del jugador que definen la experiencia de usuario
2. 6 casos de uso del sistema que gestionan la lógica del juego
3. Sistema de turnos automático gestionado internamente
4. Validación en tiempo real de todas las acciones del jugador



### 3.2 Diagrama de Clases:

```mermaid
classDiagram
    class Principal {
        +main(String[] args)
    }
    
    class BatallaNavalGUI {
        +iniciarJuego()
    }
    
    class InterfazGrafica {
        -Juego juego
        -JFrame ventana
        -JButton[][] botonesJugador
        -JButton[][] botonesComputadora
        +InterfazGrafica()
        -crearVentanaPrincipal()
        -realizarDisparo(int, int)
        -actualizarTableros()
    }
    
    class Juego {
        -Jugador jugador
        -Jugador computadora
        -boolean turnoJugador
        +jugadorDispara(int, int) boolean
        +computadoraDispara() boolean
        +verificarFinJuego() boolean
    }
    
    class Jugador {
        -String nombre
        -Tablero tablero
        +getNombre() String
        +getTablero() Tablero
    }
    
    class Tablero {
        -Celda[][] celdas
        -Barco[] barcos
        +recibirDisparo(int, int) boolean
        +todosBarcosHundidos() boolean
    }
    
    class Celda {
        -Barco barco
        -boolean disparada
        +tieneBarco() boolean
        +marcarDisparada()
    }
    
    class Barco {
        -String nombre
        -int tamaño
        -boolean hundido
        +recibirDanio()
        +estaHundido() boolean
    }
    
    Principal --> BatallaNavalGUI
    BatallaNavalGUI --> InterfazGrafica
    InterfazGrafica --> Juego
    Juego --> Jugador
    Jugador --> Tablero
    Tablero --> Celda
    Tablero --> Barco
    Celda --> Barco
```

### Explicación del Diagrama:
 1. Representa la estructura estática del sistema mostrando las clases, sus atributos, métodos y las relaciones entre ellas. Es el blueprint de toda la aplicación.

### Relaciones Principales:

1. Composición (◆): Tablero compuesto de Celdas y Barcos.
2. Agregación (◇): Juego agrega Jugadores.
3. Dependencia (→): InterfazGrafica depende de Juego para la lógica.
4. Navegabilidad: Flujo claro desde Principal hasta las entidades base.

### Características Claves:

1. Arquitectura MVC implícita: InterfazGrafica (Vista), Juego (Controlador), Entidades (Modelo).
2. Encapsulamiento robusto: Atributos privados con getters públicos.
3. Cohesión alta: Cada clase tiene responsabilidades bien definidas.
4. Bajo acoplamiento: Las clases se comunican através de interfaces claras.




### 3.3 Diagrama de Actividades:

```mermaid
flowchart TD
    Start([Inicio]) --> A[Lanzar Aplicación]
    A --> B[Cargar Interfaz Gráfica]
    B --> C{Usuario presiona Nuevo Juego}
    
    C -->|Sí| D[Ingresar Nombre Jugador]
    D --> E[Inicializar Juego]
    E --> F[Colocar Barcos Aleatoriamente]
    F --> G[Mostrar Tableros]
    
    G --> H{Turno del Jugador}
    H --> I[Jugador selecciona celda]
    I --> J[Validar Disparo]
    J --> K{¿Impacto?}
    K -->|Sí| L[Registrar impacto]
    L --> M{¿Barco hundido?}
    M -->|Sí| N[Mostrar ¡Hundido!]
    M -->|No| O[Mostrar ¡Impacto!]
    K -->|No| P[Mostrar ¡Agua!]
    
    N --> Q[Actualizar interfaz]
    O --> Q
    P --> Q
    
    Q --> R{¿Fin del juego?}
    R -->|Sí| S[Mostrar Ganador]
    R -->|No| T[Turno Computadora]
    
    T --> U[CPU genera disparo]
    U --> V[Procesar disparo CPU]
    V --> W{¿Impacto CPU?}
    W -->|Sí| X[Registrar impacto]
    X --> Y{¿Barco hundido?}
    Y -->|Sí| Z[Mostrar ¡Te hundieron!]
    Y -->|No| AA[Mostrar ¡Te impactaron!]
    W -->|No| BB[Mostrar ¡CPU falló!]
    
    Z --> CC[Actualizar interfaz]
    AA --> CC
    BB --> CC
    
    CC --> DD{¿Fin del juego?}
    DD -->|Sí| S
    DD -->|No| H
    
    S --> EE{¿Jugar de nuevo?}
    EE -->|Sí| E
    EE -->|No| FF([Fin])
    
    C -->|No| GG[Esperar acción] --> C
```

### Explicación del Diagrama:

1. Describe el flujo de trabajo completo del juego, desde el inicio hasta el final, mostrando todas las decisiones, procesos y alternativas que ocurren durante la partida.

### Relaciones Principales:

1. Secuencia lineal: Flujo principal de izquierda a derecha.
2. Bifurcaciones (rombo): Decisiones que alteran el flujo (impacto/agua, hundimiento, fin de juego).
3. Bucles cíclicos: Turnos que se repiten hasta fin del juego.
4. Concurrencia: Procesos paralelos para jugador y CPU.

### Características Claves:

1. Flujo de turnos bien definido: Jugador → Validación → CPU → Repeat.
2. Manejo de errores: Validación de celdas ya disparadas.
3. Estados de resultado claros: Impacto, Agua, Hundido.
4 .Recursividad: Posibilidad de jugar múltiples partidas.

### Diagrama de Secuencia

```mermaid
sequenceDiagram
    participant U as Usuario
    participant IG as InterfazGrafica
    participant J as Juego
    participant JUG as Jugador
    participant CPU as Computadora
    participant T_JUG as TableroJugador
    participant T_CPU as TableroCPU
    
    U->>IG: Click 'Nuevo Juego'
    IG->>J: inicializarJugadores(nombre)
    J->>JUG: new Jugador(nombre)
    J->>CPU: new Jugador('Computadora')
    J->>J: colocarBarcosAutomaticamente()
    J->>T_JUG: colocarBarcosAleatorios()
    J->>T_CPU: colocarBarcosAleatorios()
    IG->>IG: crearTableros()
    
    loop Hasta fin del juego
        U->>IG: Click celda (fila, col)
        IG->>J: jugadorDispara(fila, col)
        J->>T_CPU: recibirDisparo(fila, col)
        T_CPU-->>J: resultado (boolean)
        J-->>IG: resultado impacto
        IG->>IG: actualizarTableros()
        
        alt Impacto y barco hundido
            IG->>U: Mostrar '¡Barco hundido!'
        else Impacto sin hundir
            IG->>U: Mostrar '¡Impacto!'
        else Fallo
            IG->>U: Mostrar '¡Agua!'
        end
        
        IG->>J: verificarFinJuego()
        break si juego terminado
            J-->>IG: true
            IG->>U: Mostrar ganador
        end
        
        IG->>J: cambiarTurno()
        
        J->>CPU: generarDisparoAleatorio()
        CPU->>J: computadoraDispara()
        J->>T_JUG: recibirDisparo(fila, col)
        T_JUG-->>J: resultado (boolean)
        J-->>IG: resultado impacto CPU
        IG->>IG: actualizarTableros()
        
        alt CPU impacta y hunde
            IG->>U: Mostrar '¡Te hundieron!'
        else CPU impacta
            IG->>U: Mostrar '¡Te impactaron!'
        else CPU falla
            IG->>U: Mostrar '¡CPU falló!'
        end
        
        IG->>J: verificarFinJuego()
        break si juego terminado
            J-->>IG: true
            IG->>U: Mostrar ganador
        end
        
        IG->>J: cambiarTurno()
    end
```

### Explicación del Diagrama:

1. Muestra la secuencia temporal de mensajes entre objetos durante una partida completa, detallando las interacciones en el orden exacto en que ocurren.

### Relaciones Principales:

1. Mensajes síncronos (->>): Llamadas que esperan respuesta
2. Mensajes asíncronos (--): Notificaciones sin espera
3. Fragmentos alt: Alternativas condicionales (impacto/agua/hundido)
4. Bucles loop: Ciclo principal de turnos
5. Break: Condiciones de salida del bucle

### Características Claves:

1. Comunicación en tiempo real entre todos los componentes.
2. Patrón observador: InterfazGrafica observa cambios en Juego.
3. Validación bidireccional: Confirmaciones en cada paso.
4. Manejo de estados: Transiciones claras entre estados del juego.



