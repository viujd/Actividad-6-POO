package CRUD;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

class ActualizarContacto {
    public static void main(String datos[]) {
        try {
            String nuevoNombre = datos[0];
            long nuevoNumero = Long.parseLong(datos[1]);

            File archivo = new File("contactos.txt");
            if (!archivo.exists()) archivo.createNewFile();

            RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
            File archivoTemporal = new File("temporal.txt");
            RandomAccessFile rafTemporal = new RandomAccessFile(archivoTemporal, "rw");

            boolean encontrado = false;

            while (raf.getFilePointer() < raf.length()) {
                String linea = raf.readLine();
                String[] partes = linea.split("!");
                String nombre = partes[0];

                if (nombre.equals(nuevoNombre)) {
                    linea = nuevoNombre + "!" + nuevoNumero;
                    encontrado = true;
                }

                rafTemporal.writeBytes(linea + System.lineSeparator());
            }

            raf.seek(0);
            rafTemporal.seek(0);

            while (rafTemporal.getFilePointer() < rafTemporal.length()) {
                raf.writeBytes(rafTemporal.readLine() + System.lineSeparator());
            }

            raf.setLength(rafTemporal.length());
            rafTemporal.close();
            raf.close();
            archivoTemporal.delete();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }
}
