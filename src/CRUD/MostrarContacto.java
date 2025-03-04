package CRUD;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class MostrarContactos {
    public String obtenerContactos() {
        StringBuilder contactos = new StringBuilder();
        try {
            File archivo = new File("contactos.txt");
            if (!archivo.exists()) archivo.createNewFile();

            RandomAccessFile raf = new RandomAccessFile(archivo, "r");

            while (raf.getFilePointer() < raf.length()) {
                String linea = raf.readLine();
                String[] partes = linea.split("!");
                contactos.append("Nombre: ").append(partes[0])
                        .append("\nNúmero: ").append(partes[1]).append("\n\n");
            }

            raf.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return contactos.toString();
    }
}
