package command_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cmdGUI extends JFrame implements KeyListener {

    private JPanel panel;
    private JScrollPane scroll;
    private JTextArea areaTxt;
    private ImageIcon cmdImage;

    logica file = new logica();
    
    public cmdGUI() {
        initComponents();
    }

    private void initComponents() {
        
        cmdImage = new ImageIcon("src\\logo.png");
        this.setIconImage(cmdImage.getImage());
        this.setTitle("Administrator: Command Prompt");
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        areaTxt = new JTextArea();
        areaTxt.setText("Microsoft Windows [Version 10.0.19045.5131]\n(c) Microsoft Corporation. All rights reserved.\n\n" + options()+"\n");
        areaTxt.setBackground(Color.black);
        areaTxt.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaTxt.setForeground(Color.white);
        areaTxt.setCaretColor(Color.white);
        areaTxt.addKeyListener(this); 

        scroll = new JScrollPane(areaTxt);

        panel.add(scroll, BorderLayout.CENTER);
        this.add(panel);
    }

    String options() {
        return "1 - Mkdir \n"
                + "2 - Mfile \n"
                + "3 - Rm \n"
                + "4 - Cd \n"
                + "5 - Regresar de Carpeta\n"
                + "6 - Dir\n"
                + "7 - Date\n"
                + "8 - Time\n"
                + "9 - Escribir\n"
                + "10 - Leer\n"
                + "11 - Cerrar cmd\n"
                + "Escoja una de las opciones: (escribir abajo de esta linea la opcion como entero)";
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_ENTER) {
            String fullText = areaTxt.getText();

            String[] lines = fullText.split("\n");
            String lastLine = lines[lines.length - 1].trim();

            try {
                int option = Integer.parseInt(lastLine);

                switch (option) {
                    case 1:
                        String carpeta = JOptionPane.showInputDialog("Nombre de la carpeta:");
                        file.crearFolder(carpeta);
                        break;
                    case 2:
                        String archivo = JOptionPane.showInputDialog("Nombre del archivo: ");
                        file.crearFile(archivo);
                        break;
                    case 3:
                        file.borrar();
                        break;
                    case 4:
                        String eleccion = JOptionPane.showInputDialog("Ingrese la direccion del archivo: ");
                        file.setFile(eleccion);
                        break;
                    case 5:
                        file.regresar();
                        break;
                    case 6:
                        file.Dir();
                        break;
                    case 7:
                        file.Date();
                        break;
                    case 8:
                        file.Time();
                        break;
                    case 9:
                        String message = JOptionPane.showInputDialog("Ingrese texto a agregar: ");
                        file.escribir(message);
                        break;
                    case 10:
                        file.leer();
                        break;
                    case 11:
                        JOptionPane.showMessageDialog(this, "CMD finalizado");
                        this.dispose(); 
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Opción fuera del rango (1-11)");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor, ingrese un número.");
            } catch (IOException ex) {
                Logger.getLogger(cmdGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
