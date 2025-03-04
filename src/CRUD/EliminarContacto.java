package CRUD;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class EliminarContacto {
    public static void main(String datos[]) {
        try {
            String nombreEliminar = datos[0];

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

                if (!nombre.equals(nombreEliminar)) {
                    rafTemporal.writeBytes(linea + System.lineSeparator());
                } else {
                    encontrado = true;
                }
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
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
