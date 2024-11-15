package command_gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class logica {

    private File file = null;

    void setFile(String dirreccion) {
        file = new File(dirreccion);
    }

    boolean crearFile(String dirreccion) throws IOException {
        file = new File(dirreccion);
        return file.createNewFile();
    }

    boolean crearFolder(String dirreccion) {
        file = new File(dirreccion);
        return file.mkdirs();
    }

    void Date() {
        LocalDate fecha = LocalDate.now();
        JOptionPane.showMessageDialog(null, "Fecha: "+fecha.toString());
    }

    void Time() {
    LocalTime horaActual = LocalTime.now();
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    String horaFormateada = horaActual.format(formatoHora);
    JOptionPane.showMessageDialog(null, "Hora: " + horaFormateada);
}
    
    public void regresar() {
        if (file != null) {
            String parentDir = file.getParent();
            if (parentDir != null) {
                file = new File(parentDir);
                JOptionPane.showMessageDialog(null, "Ahora estas en: "+file.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "Este directorio no tiene directorio padre.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha establecido un directorio.");
        }
    }

    public boolean escribir(String message) {
        if (file.exists() && file.isFile()) {
            try (FileWriter texto = new FileWriter(file, true)) {
                texto.write(message);
                return true;
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo valido.");
            return false;
        }
    }

    public void leer() {
        if (file.exists() && file.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String read;
                while ((read = reader.readLine()) != null) {
                JOptionPane.showMessageDialog(null, read);
            }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo: " + e.getMessage()+"\n");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El archivo no existe o no es n archivo valido.");
        }
    }
    
    public void Dir() {
    if (file.isDirectory()) {
        int cfiles = 0, folders = 0;
        String archivos = "";
        String Folders = "";
        
        for (File child : file.listFiles()) {
            if (!child.isHidden()) {
                if (child.isFile()) {
                    cfiles++;
                    archivos += child.getName() + "\n";
                } else if (child.isDirectory()) {
                    folders++;
                    Folders += child.getName() + "\n";
                }
            }
        }
        
        String mensaje = cfiles + " archivos:\n" + archivos + "\n" +
                         folders + " folders:\n" + Folders;

        JOptionPane.showMessageDialog(null, mensaje, "Contenido de Directorio", JOptionPane.INFORMATION_MESSAGE);
    }
}
    
    void borrar(){
        if(borrado(file)){
            JOptionPane.showMessageDialog(null, "Borrado");
        }else{
            JOptionPane.showMessageDialog(null, "No se pudo borrar");
        }
              
    }
    public boolean borrado(File file){
        if(file.isDirectory()){
            for(File child: file.listFiles()){
                borrado(child);
            }
        }
        return file.delete();
    }
    
}
