package CRUD;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gestor ventana = new Gestor();
            ventana.setVisible(true);
        });
    }
}
