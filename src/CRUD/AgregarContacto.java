package CRUD;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

class AgregarContacto {
    public static void main(String datos[]) {
        try {
            String nombre = datos[0];
            long numero = Long.parseLong(datos[1]);

            File archivo = new File("contactos.txt");
            if (!archivo.exists()) archivo.createNewFile();

            RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
            boolean encontrado = false;

            while (raf.getFilePointer() < raf.length()) {
                String linea = raf.readLine();
                String[] partes = linea.split("!");
                if (partes[0].equals(nombre)) {
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                raf.writeBytes(nombre + "!" + numero);
                raf.writeBytes(System.lineSeparator());
            }

            raf.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }
}
