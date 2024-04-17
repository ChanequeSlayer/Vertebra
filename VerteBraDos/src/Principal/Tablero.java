package Principal;

//Paquetes utilizados
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Código hecho por el Equipo 2 compuesto por:
José Raymundo Baca Hernández - 20550378
Victor Andrés Garduño Ramos - 20550354
Jorge Alejandro Martínez Espinoza - 20550364
Jose Kaleb Ruelas Loo - 20550387

Fecha del: 13/04/2024
Título: Práctica 3.1 Búsqueda
Objetivo: Un agente debe localizar el objetivo ubicado en algún 
extremo de una costilla y posteriormente dirigirse a la meta.
 */
// Definición de la clase Tablero que hereda de JFrame
public class Tablero extends JFrame {

    // Lista para almacenar las casillas válidas
    ArrayList<JButton> casillasValidas = new ArrayList<>();

    // Lista para almacenar las casillas de camion
    ArrayList<JButton> casillasCamino = new ArrayList<>();

    // Declaración de variables
    private JButton SigMov; // Botón para realizar el siguiente movimiento
    private JButton Reiniciar; // Botón para reiniciar la partida
    private boolean bandera = true;
    private Thread hilo;
// Constructor de la clase Tablero
    private int vertebra = 0;

    public Tablero() {
        // Inicialización de la interfaz de usuario
        initUI();
    }

    // Método privado para inicializar la interfaz de usuario
    private void initUI() {
        // Configuración del título y operación de cierre
        setTitle("Práctica 3.1 Búsqueda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creación del panel superior y adición de botones
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        SigMov = new JButton("Siguiente movimiento");
        Reiniciar = new JButton("Reiniciar partida");
        topPanel.add(SigMov);
        topPanel.add(Reiniciar);

        // Creación del panel del tablero
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 13)); // 7 filas y 13 columnas

        // Iteración para crear las casillas del tablero
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 13; j++) {
                JButton casilla = new JButton();
                // Coloreamos el tablero
                if (i == 3 || j == 3 || j == 5 || j == 7 || j == 9) {
                    casilla.setBackground(new Color(189, 255, 189)); // Verde claro
                    casillasCamino.add(casilla);
                } else {
                    casilla.setBackground(Color.WHITE);
                }

                // Agregamos "META" e "INICIO" en los extremos
                if (i == 3 && j == 0) {
                    casilla.setText("META");
                    casilla.setEnabled(false); // Para que no sea clickeable
                } else if (i == 3 && j == 12) {
                    casilla.setText("INICIO");
                    casilla.setEnabled(false); // Para que no sea clickeable
                }

                // Verificamos si la casilla cumple con las condiciones y la agregamos a la lista de casillas válidas
                if ((i == 0 || i == 6) && (j == 3 || j == 5 || j == 7 || j == 9)) {
                    casillasValidas.add(casilla);
                }
                panel.add(casilla);
            }
        }

        // Si hay casillas válidas, seleccionamos aleatoriamente una y establecemos el texto "OBJ"
        if (!casillasValidas.isEmpty()) {
            int indiceAleatorio = (int) (Math.random() * casillasValidas.size());
            JButton casillaAleatoria = casillasValidas.get(indiceAleatorio);
            casillaAleatoria.setText("OBJ");
            casillaAleatoria.setBackground(Color.ORANGE);
            casillaAleatoria.setEnabled(false); // Para que no sea clickeable
        }

        // Creación del panel inferior
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Configuración del diseño y adición de los paneles al contenedor principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Configuración del tamaño y posición de la ventana
        setPreferredSize(new Dimension(900, 500));
        pack();
        setLocationRelativeTo(null);

        // Asignación de acciones a los botones
        SigMov.addActionListener(e -> {
            try {
                inicio();// Lógica para el siguiente movimiento del Agente
            } catch (InterruptedException ex) {
                Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Reiniciar.addActionListener(e -> {
            reiniciarPartida(); // Lógica para reiniciar la partida
        });
    }

    // Método para reiniciar la partida
    private void reiniciarPartida() {
        SwingUtilities.invokeLater(() -> {
            // Cerrar la ventana actual
            dispose();
            // Iniciar una nueva instancia del juego
            new Tablero().setVisible(true);
        });
    }

    // Método para iniciar la partida.
    private void inicio() throws InterruptedException {
        int ayuda = 0;
        //Inicio del metodo.
        JOptionPane.showMessageDialog(this, "Empezare la Busqueda :D");
        casillasCamino.get(23).setBackground(Color.BLACK);
        ayuda = 23;
        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                
                int a = avance(23);
                System.out.println("No se encontro nada y estamos en la posicion: " + a);
                casillasCamino.get(a).setBackground(Color.RED);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                int b = avance(a);
                System.out.println("No se encontro nada y estamos en la posicion:" + b);
                casillasCamino.get(b).setBackground(Color.RED);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
                }
                int c = avance(b);
                System.out.println("No se encontro nada y estamos en la posicion:" + b);
                casillasCamino.get(c).setBackground(Color.RED);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
                }
                int d = avance(c);
                System.out.println("No se encontro nada y estamos en la posicion:" + c);
                casillasCamino.get(d).setBackground(Color.RED);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        hilo.start();
    }

    private int avance(int pos) {
        while(bandera){
  
        int posF = pos;
        vertebra++;
        try {
            for (int i = 0; i < 14; i++) {
                if (i <= 1) {

                    casillasCamino.get(pos - 1).setBackground(Color.BLACK);
                    System.out.println("Izquierda");
                    Thread.sleep(500);
                    pos--;
                    System.out.println(pos);

                } else if (i >= 2 && i < 5) {

                    if (i == 2) {
                        if (vertebra == 1) {
                            casillasCamino.get(pos - 10).setBackground(Color.BLACK);
                            System.out.println("Arriba");
                            Thread.sleep(500);
                            pos -= 10;
                            System.out.println(pos);
                        } else if (vertebra == 2) {
                            casillasCamino.get(pos - 9).setBackground(Color.BLACK);
                            System.out.println("Arriba");
                            Thread.sleep(500);
                            pos -= 9;
                            System.out.println(pos);
                        } else if (vertebra == 3) {
                            casillasCamino.get(pos - 8).setBackground(Color.BLACK);
                            System.out.println("Arriba");
                            Thread.sleep(500);
                            pos -= 8;
                            System.out.println(pos);
                        } else if (vertebra == 4) {
                            casillasCamino.get(pos - 7).setBackground(Color.BLACK);
                            System.out.println("Arriba");
                            Thread.sleep(500);
                            pos -= 7;
                            System.out.println(pos);
                        }

                    } else {
                        casillasCamino.get(pos - 4).setBackground(Color.BLACK);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        pos -= 4;
                        System.out.println(pos);
                    }
                    if(casillasCamino.get(pos).getText().equalsIgnoreCase("OBJ")){
                        bandera=false;
                        System.out.println("La posicion actual es:"+pos);
                        alerta(pos);
                        
                        break;
                    }
                } else if (i >= 5 && i <= 10) {

                    if (i == 7) {
                        if (vertebra == 1) {
                            casillasCamino.get(pos + 10).setBackground(Color.BLACK);
                            System.out.println("Abajo");
                            Thread.sleep(500);
                            pos += 10;
                            System.out.println(pos);
                        } else if (vertebra == 2) {
                            casillasCamino.get(pos + 9).setBackground(Color.BLACK);
                            System.out.println("Abajo");
                            Thread.sleep(500);
                            pos += 9;
                            System.out.println(pos);
                        } else if (vertebra == 3) {
                            casillasCamino.get(pos + 8).setBackground(Color.BLACK);
                            System.out.println("Abajo");
                            Thread.sleep(500);
                            pos += 8;
                            System.out.println(pos);
                        } else if (vertebra == 4) {
                            casillasCamino.get(pos + 7).setBackground(Color.BLACK);
                            System.out.println("Abajo");
                            Thread.sleep(500);
                            pos += 7;
                            System.out.println(pos);
                        }
                        
                    } else if (i == 8) {
                        if (vertebra == 1) {
                            casillasCamino.get(pos + 7).setBackground(Color.BLACK);
                            System.out.println("Abajo");
                            Thread.sleep(500);
                            pos += 7;
                            System.out.println(pos);
                        } else if (vertebra == 2) {
                            casillasCamino.get(pos + 8).setBackground(Color.BLACK);
                            System.out.println("Abajo");
                            Thread.sleep(500);
                            pos += 8;
                            System.out.println(pos);
                        } else if (vertebra == 3) {
                            casillasCamino.get(pos + 9).setBackground(Color.BLACK);
                            System.out.println("Abajo");
                            Thread.sleep(500);
                            pos += 9;
                            System.out.println(pos);
                        } else if (vertebra == 4) {
                            casillasCamino.get(pos + 10).setBackground(Color.BLACK);
                            System.out.println("Abajo");
                            Thread.sleep(500);
                            pos += 10;
                            System.out.println(pos);
                        }
                    } else {
                        casillasCamino.get(pos + 4).setBackground(Color.BLACK);
                        System.out.println("Abajo");
                        Thread.sleep(500);
                        pos += 4;
                        System.out.println(pos);
                    }
                     if(casillasCamino.get(pos).getText().equalsIgnoreCase("OBJ")){
                        bandera=false;
                        System.out.println("La posicion actual es:"+pos);
                        alerta(pos);
                        break;
                    }
                } else {
                    if (i == 13) {
                        if (vertebra == 1) {
                            casillasCamino.get(pos - 7).setBackground(Color.BLACK);
                            System.out.println("Arriba");
                            Thread.sleep(500);
                            pos -= 7;
                            System.out.println(pos);
                        } else if (vertebra == 2) {
                            casillasCamino.get(pos - 8).setBackground(Color.BLACK);
                            System.out.println("Arriba");
                            Thread.sleep(500);
                            pos -= 8;
                            System.out.println(pos);
                        } else if (vertebra == 3) {
                            casillasCamino.get(pos - 9).setBackground(Color.BLACK);
                            System.out.println("Arriba");
                            Thread.sleep(500);
                            pos -= 9;
                            System.out.println(pos);
                        } else if (vertebra == 4) {
                            casillasCamino.get(pos - 10).setBackground(Color.BLACK);
                            System.out.println("Abajo");
                            Thread.sleep(500);
                            pos -= 10;
                            System.out.println(pos);
                        }

                    } else {

                        casillasCamino.get(pos - 4).setBackground(Color.BLACK);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        pos -= 4;
                        System.out.println(pos);

                    }

                }

            }
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        }

        return posF = pos;
        }return 0;
    }

    public void alerta(int posi) {
        try {
            JOptionPane.showMessageDialog(this, "OBTUVIMOS EL PREMIO, YEII :D");
            //int array[] = {12,13,14,15,16,17,18,19,20,21,22,23,24};
            for (int i = 0; i < 37; i++) {
                if (i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17 || i == 18 || i == 19 || i == 20 || i == 21 || i == 22 || i == 23 || i == 24) {
                    casillasCamino.get(i).setBackground(Color.GREEN);
                } else {
                    casillasCamino.get(i).setBackground(Color.magenta);
                }
            }
            switch (posi) {
                case 3:
                    for (int i = 0; i < 3; i++) {
                        if(i==2){
                        casillasCamino.get(posi + 10).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi +=10;
                        System.out.println(posi);   
                        }else{
                        casillasCamino.get(posi + 4).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi += 4;
                        System.out.println(posi);
                        }
                    }
                    for (int i = 0; i < 8; i++) {
                        casillasCamino.get(posi - 1).setBackground(Color.YELLOW);
                        System.out.println("Izquierda");
                        Thread.sleep(500);
                        posi -= 1;
                        System.out.println(posi);
                    }
                    break;
                case 2:
                    for (int i = 0; i < 3; i++) {
                        if(i==2){
                        casillasCamino.get(posi + 9).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi +=9;
                        System.out.println(posi);   
                        }else{
                        casillasCamino.get(posi + 4).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi += 4;
                        System.out.println(posi);
                        }
                    }
                    for (int i = 0; i < 6; i++) {
                        casillasCamino.get(posi - 1).setBackground(Color.YELLOW);
                        System.out.println("Izquierda");
                        Thread.sleep(500);
                        posi -= 1;
                        System.out.println(posi);
                    }
                    break;
                case 1:
                    for (int i = 0; i < 3; i++) {
                        if(i==2){
                        casillasCamino.get(posi + 8).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi +=8;
                        System.out.println(posi);   
                        }else{
                        casillasCamino.get(posi + 4).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi += 4;
                        System.out.println(posi);
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        casillasCamino.get(posi - 1).setBackground(Color.YELLOW);
                        System.out.println("Izquierda");
                        Thread.sleep(500);
                        posi -= 1;
                        System.out.println(posi);
                    }
                    break;
                case 0:
                    for (int i = 0; i < 2; i++) {
                        if(i==1){
                        casillasCamino.get(posi + 7).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi +=7;
                        System.out.println(posi);   
                        }else{
                        casillasCamino.get(posi + 4).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi += 4;
                        System.out.println(posi);
                        }
                    }
                    for (int i = 0; i < 2; i++) {
                        casillasCamino.get(posi - 1).setBackground(Color.YELLOW);
                        System.out.println("Izquierda");
                        Thread.sleep(500);
                        posi -= 1;
                        System.out.println(posi);
                    }
                    break;
                case 33:
                    for (int i = 0; i < 3; i++) {
                        if(i==2){
                        casillasCamino.get(posi - 10).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi -=10;
                        System.out.println(posi);   
                        }else{
                        casillasCamino.get(posi - 4).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi -= 4;
                        System.out.println(posi);
                        }
                    }
                    for (int i = 0; i < 2; i++) {
                        casillasCamino.get(posi - 1).setBackground(Color.YELLOW);
                        System.out.println("Izquierda");
                        Thread.sleep(500);
                        posi -= 1;
                        System.out.println(posi);
                    }
                    break;
                case 34:
                    for (int i = 0; i < 3; i++) {
                        if(i==2){
                        casillasCamino.get(posi - 9).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi -=9;
                        System.out.println(posi);   
                        }else{
                        casillasCamino.get(posi - 4).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi -= 4;
                        System.out.println(posi);
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        casillasCamino.get(posi - 1).setBackground(Color.YELLOW);
                        System.out.println("Izquierda");
                        Thread.sleep(500);
                        posi -= 1;
                        System.out.println(posi);
                    }
                    break;
                case 35:
                    for (int i = 0; i < 3; i++) {
                        if(i==2){
                        casillasCamino.get(posi - 8).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi -=8;
                        System.out.println(posi);   
                        }else{
                        casillasCamino.get(posi - 4).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi -= 4;
                        System.out.println(posi);
                        }
                    }
                    for (int i = 0; i < 6; i++) {
                        casillasCamino.get(posi - 1).setBackground(Color.YELLOW);
                        System.out.println("Izquierda");
                        Thread.sleep(500);
                        posi -= 1;
                        System.out.println(posi);
                    }
                    break;
                case 36:
                    for (int i = 0; i < 3; i++) {
                        if(i==2){
                        casillasCamino.get(posi - 7).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi -= 7;
                        System.out.println(posi);   
                        }else{
                        casillasCamino.get(posi - 4).setBackground(Color.YELLOW);
                        System.out.println("Arriba");
                        Thread.sleep(500);
                        posi -= 4;
                        System.out.println(posi);
                        }
                    }
                    for (int i = 0; i < 8; i++) {
                        casillasCamino.get(posi - 1).setBackground(Color.yellow);
                        System.out.println("Izquierda");
                        Thread.sleep(500);
                        posi -= 1;
                        System.out.println(posi);
                    }
                    break;

            }
            JOptionPane.showMessageDialog(this, "Ya ganaste");
              System.out.println("Terminando programa...");
        
        // Terminar el programa de golpe
        System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Tablero().setVisible(true);
        });
    }
}
