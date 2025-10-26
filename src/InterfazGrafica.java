import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//Clase que contiene toda la interfaz gráfica del juego 

public class InterfazGrafica {
    private Juego juego;
    private JFrame ventana;
    private JButton[][] botonesJugador;
    private JButton[][] botonesComputadora;
    private JLabel labelEstado;
    private JLabel labelTurno;
    private JPanel panelTableros;
    
    // Iconos en lugar de símbolos Unicode
    private final Icon ICONO_BARCO = crearIcono(Color.DARK_GRAY, 30, 30);
    private final Icon ICONO_IMPACTO = crearIcono(Color.RED, 30, 30, "X");
    private final Icon ICONO_AGUA = crearIcono(Color.BLUE, 30, 30, "•");
    private final Icon ICONO_HUNDIDO = crearIcono(Color.ORANGE, 30, 30, "Ø");
    
    public InterfazGrafica() {
        juego = new Juego();
        crearVentanaPrincipal();
    }
    
    private Icon crearIcono(Color color, int width, int height) {
        return crearIcono(color, width, height, null);
    }
    
    private Icon crearIcono(Color color, int width, int height, String texto) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Dibujar fondo
                g2d.setColor(color);
                g2d.fillRect(x, y, width, height);
                
                // Dibujar borde
                g2d.setColor(Color.GRAY);
                g2d.drawRect(x, y, width, height);
                
                // Dibujar texto si se proporciona
                if (texto != null) {
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Arial", Font.BOLD, 14));
                    FontMetrics fm = g2d.getFontMetrics();
                    int textX = x + (width - fm.stringWidth(texto)) / 2;
                    int textY = y + (height - fm.getHeight()) / 2 + fm.getAscent();
                    g2d.drawString(texto, textX, textY);
                }
                
                g2d.dispose();
            }
            
            @Override
            public int getIconWidth() { return width; }
            
            @Override
            public int getIconHeight() { return height; }
        };
    }
    
    private void crearVentanaPrincipal() {
        // Configurar ventana
        ventana = new JFrame("Batalla Naval en BOLIVIA");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1000, 750);
        ventana.setLayout(new BorderLayout());
        
        // Panel de información
        JPanel panelInfo = new JPanel(new GridLayout(2, 1));
        panelInfo.setBackground(new Color(70, 130, 180));
        
        labelEstado = new JLabel("¡Bienvenido a Batalla Naval en BOLIVIA!", JLabel.CENTER);
        labelEstado.setFont(new Font("Arial", Font.BOLD, 30));
        labelEstado.setForeground(Color.GREEN);
        
        labelTurno = new JLabel("Presiona 'Nuevo Juego' para comenzar", JLabel.CENTER);
        labelTurno.setFont(new Font("Arial", Font.BOLD, 16));
        labelTurno.setForeground(Color.YELLOW);
        
        panelInfo.add(labelEstado);
        panelInfo.add(labelTurno);
        
        // Panel de tableros
        panelTableros = new JPanel(new GridLayout(1, 2, 20, 0));
        panelTableros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTableros.setBackground(new Color(173, 216, 230));
        
        // Crear tableros iniciales vacíos
        crearTableros();
        
        // Panel de leyenda
        JPanel panelLeyenda = crearPanelLeyenda();
        
        // Botones de control
        JPanel panelControles = new JPanel(new FlowLayout());
        panelControles.setBackground(new Color(100, 149, 237));
        
        JButton btnIniciar = new JButton("Nuevo Juego");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 14));
        btnIniciar.setBackground(new Color(34, 139, 34));
        btnIniciar.setForeground(Color.WHITE);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setBackground(new Color(178, 34, 34));
        btnSalir.setForeground(Color.WHITE);
        
        btnIniciar.addActionListener(e -> iniciarJuego());
        btnSalir.addActionListener(e -> System.exit(0));
        
        panelControles.add(btnIniciar);
        panelControles.add(btnSalir);
        
        // Agregar todo a la ventana
        ventana.add(panelInfo, BorderLayout.NORTH);
        ventana.add(panelTableros, BorderLayout.CENTER);
        ventana.add(panelLeyenda, BorderLayout.WEST);
        ventana.add(panelControles, BorderLayout.SOUTH);
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    private JPanel crearPanelLeyenda() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        Border borde = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            "AYUDA",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 20),
            Color.BLUE
        );
        panel.setBorder(borde);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(200, 10));
        
        // Crear items de leyenda con iconos
        agregarItemLeyenda(panel, ICONO_BARCO, "Barco (Tu flota)", Color.DARK_GRAY);
        agregarItemLeyenda(panel, ICONO_IMPACTO, "Impacto", Color.RED);
        agregarItemLeyenda(panel, ICONO_HUNDIDO, "Barco Hundido", Color.ORANGE);
        agregarItemLeyenda(panel, ICONO_AGUA, "Agua", Color.BLUE);
        
        // Para el océano, crear un icono especial
        Icon iconoOceano = crearIcono(new Color(135, 206, 250), 25, 25);
        agregarItemLeyenda(panel, iconoOceano, "Océano", new Color(135, 206, 250));
        
        return panel;
    }
    
    private void agregarItemLeyenda(JPanel panel, Icon icono, String texto, Color colorFondo) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setMaximumSize(new Dimension(170, 30));
        
        // Botón para mostrar el icono
        JButton iconoBoton = new JButton(icono);
        iconoBoton.setPreferredSize(new Dimension(25, 25));
        iconoBoton.setBackground(colorFondo);
        iconoBoton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        iconoBoton.setEnabled(false);
        
        JLabel textoLabel = new JLabel(texto);
        textoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        itemPanel.add(iconoBoton);
        itemPanel.add(textoLabel);
        
        panel.add(itemPanel);
    }
    
    private void crearTableros() {
        panelTableros.removeAll();
        
        // Tablero del jugador
        JPanel tableroJugador = crearTableroIndividual("TU FLOTA", true);
        // Tablero de la computadora  
        JPanel tableroComputadora = crearTableroIndividual("FLOTA ENEMIGA", false);
        
        panelTableros.add(tableroJugador);
        panelTableros.add(tableroComputadora);
        
        panelTableros.revalidate();
        panelTableros.repaint();
    }
    
    private JPanel crearTableroIndividual(String titulo, boolean esJugador) {
        JPanel panel = new JPanel(new BorderLayout());

        Border borde = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
            titulo,
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 20),
            Color.BLACK
        );
        panel.setBorder(borde);
        panel.setBackground(Color.WHITE);

        JPanel gridTablero = new JPanel(new GridLayout(10, 10, 1, 1));
        gridTablero.setBackground(Color.BLACK);
        
        if (esJugador) {
            botonesJugador = new JButton[10][10];
        } else {
            botonesComputadora = new JButton[10][10];
        }
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton boton = crearBotonTablero();
                
                if (esJugador) {
                    botonesJugador[i][j] = boton;
                    boton.setEnabled(false);
                } else {
                    botonesComputadora[i][j] = boton;
                    final int fila = i;
                    final int columna = j;
                    boton.addActionListener(e -> realizarDisparo(fila, columna));
                }
                
                gridTablero.add(boton);
            }
        }
        
        panel.add(gridTablero, BorderLayout.CENTER);
        
        // Agregar coordenadas
        panel.add(crearCoordenadas(), BorderLayout.NORTH);
        panel.add(crearCoordenadasVerticales(), BorderLayout.WEST);
        
        return panel;
    }
    
    private JButton crearBotonTablero() {
        JButton boton = new JButton();
        boton.setPreferredSize(new Dimension(45, 45));
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(135, 206, 250)); // Azul claro - Océano
        boton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        boton.setFocusPainted(false);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        return boton;
    }
    
    private JPanel crearCoordenadas() {
        JPanel panel = new JPanel(new GridLayout(1, 10));
        panel.setBackground(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            JLabel label = new JLabel(String.valueOf(i), JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            panel.add(label);
        }
        return panel;
    }
    
    private JPanel crearCoordenadasVerticales() {
        JPanel panel = new JPanel(new GridLayout(10, 1));
        panel.setBackground(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            JLabel label = new JLabel(String.valueOf(i), JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 12));
            panel.add(label);
        }
        return panel;
    }
    
    private void iniciarJuego() {
        String nombre = JOptionPane.showInputDialog(ventana, 
            "Ingresa tu nombre:", "Batalla Naval", JOptionPane.QUESTION_MESSAGE);
        
        if (nombre == null || nombre.trim().isEmpty()) {
            nombre = "Jugador";
        }
        
        // Reiniciar completamente el juego
        juego = new Juego();
        juego.inicializarJugadores(nombre, "Computadora");
        juego.colocarBarcosAutomaticamente();
        
        // Limpiar y actualizar tableros
        limpiarTableros();
        actualizarTableros();
        
        labelEstado.setText("¡Juego Iniciado! - " + nombre + " vs Computadora");
        labelTurno.setText("Turno de: " + nombre);
        labelTurno.setForeground(Color.GREEN);
        
        habilitarTableroEnemigo(true);
    }
    
    private void limpiarTableros() {
        Color colorOceano = new Color(135, 206, 250);
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (botonesJugador != null) {
                    botonesJugador[i][j].setBackground(colorOceano);
                    botonesJugador[i][j].setIcon(null);
                    botonesJugador[i][j].setText("");
                    botonesJugador[i][j].setEnabled(false);
                }
                if (botonesComputadora != null) {
                    botonesComputadora[i][j].setBackground(colorOceano);
                    botonesComputadora[i][j].setIcon(null);
                    botonesComputadora[i][j].setText("");
                    botonesComputadora[i][j].setEnabled(true);
                }
            }
        }
    }
    
    private void realizarDisparo(int fila, int columna) {
        if (!juego.esTurnoJugador()) {
            JOptionPane.showMessageDialog(ventana, "¡Espera tu turno!", "Turno", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean impacto = juego.jugadorDispara(fila, columna);
        
        if (impacto) {
            labelEstado.setText("¡IMPACTO! Has golpeado un barco enemigo");
            // Verificar si hundió el barco
            Celda celda = juego.getComputadora().getTablero().getCelda(fila, columna);
            if (celda.tieneBarco() && celda.getBarco().estaHundido()) {
                labelEstado.setText("¡HUNDIDO! Destruiste un " + celda.getBarco().getNombre() + "!");
                // Marcar todo el barco como hundido
                marcarBarcoHundido(juego.getComputadora().getTablero(), celda.getBarco());
            }
        } else {
            labelEstado.setText("Agua... el disparo falló");
        }
        
        actualizarTableros();
        
        if (juego.verificarFinJuego()) {
            finDelJuego();
            return;
        }
        
        juego.cambiarTurno();
        labelTurno.setText("Turno de: Computadora");
        labelTurno.setForeground(Color.ORANGE);
        habilitarTableroEnemigo(false);
        
        Timer timer = new Timer(1500, e -> {
            boolean impactoCompu = juego.computadoraDispara();
            
            if (impactoCompu) {
                labelEstado.setText("La computadora golpeó tu barco!");
                // Verificar si hundió el barco
                Celda celda = encontrarCeldaImpactada(juego.getJugador().getTablero());
                if (celda != null && celda.getBarco().estaHundido()) {
                    labelEstado.setText("¡HUNDIDO! Perdiste tu " + celda.getBarco().getNombre());
                    marcarBarcoHundido(juego.getJugador().getTablero(), celda.getBarco());
                }
            } else {
                labelEstado.setText("La computadora falló su disparo");
            }
            
            actualizarTableros();
            
            if (juego.verificarFinJuego()) {
                finDelJuego();
            } else {
                juego.cambiarTurno();
                labelTurno.setText("Turno de: " + juego.getJugador().getNombre());
                labelTurno.setForeground(Color.GREEN);
                habilitarTableroEnemigo(true);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private Celda encontrarCeldaImpactada(Tablero tablero) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Celda celda = tablero.getCelda(i, j);
                if (celda.estaDisparada() && celda.tieneBarco() && !celda.getBarco().estaHundido()) {
                    return celda;
                }
            }
        }
        return null;
    }
    
    private void marcarBarcoHundido(Tablero tablero, Barco barco) {
        // Este método ahora marca permanentemente el barco como hundido
        // Los cambios se mantendrán en actualizarTableros()
    }
    
    private void actualizarTableros() {
        // Actualizar tablero del jugador (muestra barcos completos)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Celda celdaJugador = juego.getJugador().getTablero().getCelda(i, j);
                JButton boton = botonesJugador[i][j];
                
                // Limpiar icono y texto anterior
                boton.setIcon(null);
                boton.setText("");
                
                if (celdaJugador.estaDisparada()) {
                    if (celdaJugador.tieneBarco()) {
                        if (celdaJugador.getBarco().estaHundido()) {
                            // Barco hundido - NARANJA
                            boton.setIcon(ICONO_HUNDIDO);
                            boton.setBackground(Color.ORANGE);
                        } else {
                            // Impacto normal - ROJO
                            boton.setIcon(ICONO_IMPACTO);
                            boton.setBackground(Color.RED);
                        }
                    } else {
                        // Agua
                        boton.setIcon(ICONO_AGUA);
                        boton.setBackground(Color.BLUE);
                    }
                } else if (celdaJugador.tieneBarco()) {
                    // Mostrar barco completo en el tablero del jugador
                    if (celdaJugador.getBarco().estaHundido()) {
                        boton.setIcon(ICONO_HUNDIDO);
                        boton.setBackground(Color.ORANGE);
                    } else {
                        boton.setIcon(ICONO_BARCO);
                        boton.setBackground(Color.DARK_GRAY);
                    }
                } else {
                    boton.setBackground(new Color(135, 206, 250)); // Azul océano
                }
                boton.setForeground(Color.WHITE);
            }
        }
        
        // Actualizar tablero de la computadora (solo muestra disparos)
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Celda celdaCompu = juego.getComputadora().getTablero().getCelda(i, j);
                JButton boton = botonesComputadora[i][j];
                
                // Limpiar icono y texto anterior
                boton.setIcon(null);
                boton.setText("");
                
                if (celdaCompu.estaDisparada()) {
                    if (celdaCompu.tieneBarco()) {
                        if (celdaCompu.getBarco().estaHundido()) {
                            // Barco hundido - NARANJA
                            boton.setIcon(ICONO_HUNDIDO);
                            boton.setBackground(Color.ORANGE);
                        } else {
                            // Impacto normal - ROJO
                            boton.setIcon(ICONO_IMPACTO);
                            boton.setBackground(Color.RED);
                        }
                    } else {
                        // Agua
                        boton.setIcon(ICONO_AGUA);
                        boton.setBackground(Color.BLUE);
                    }
                } else {
                    boton.setBackground(new Color(135, 206, 250)); // Azul océano
                }
                boton.setForeground(Color.WHITE);
            }
        }
    }
    
    private void habilitarTableroEnemigo(boolean habilitar) {
        if (botonesComputadora != null) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    boolean yaDisparada = juego.getComputadora().getTablero().getCelda(i, j).estaDisparada();
                    botonesComputadora[i][j].setEnabled(habilitar && !yaDisparada);
                }
            }
        }
    }
    
    private void finDelJuego() {
        habilitarTableroEnemigo(false);
        
        String mensaje;
        if (juego.getJugador().getTablero().todosBarcosHundidos()) {
            mensaje = "¡LA COMPUTADORA GANÓ!\n\nPerdiste la salida al MAR";
            labelEstado.setText("Derrota - La computadora ganó");
            labelTurno.setText("JUEGO TERMINADO");
            labelTurno.setForeground(Color.RED);
        } else {
            mensaje = "¡FELICIDADES " + juego.getJugador().getNombre() + "!\n\n¡GANASTE EL JUEGO!";
            labelEstado.setText("Victoria - " + juego.getJugador().getNombre() + " ganó");
            labelTurno.setText("¡VICTORIA!");
            labelTurno.setForeground(Color.GREEN);
        }
        
        JOptionPane.showMessageDialog(ventana, mensaje, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
    }
}