import javax.swing.*;

//Clase que maneja la interfaz gráfica del juego
 
public class BatallaNavalGUI {
    
    public static void iniciarJuego() {
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazGrafica();
            }
        });
    }
}