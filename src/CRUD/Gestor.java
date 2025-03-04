package CRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Gestor extends JFrame {
    private JTextField campoNombre, campoNumero;
    private JTextArea areaContactos;

    public Gestor() {
        setTitle("Gestor de Contactos");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel etiquetaNombre = new JLabel("Nombre:", SwingConstants.RIGHT);
        gbc.gridx = 0; gbc.gridy = 0;
        panelCampos.add(etiquetaNombre, gbc);

        campoNombre = new JTextField("Ingrese nombre", 15);
        campoNombre.setForeground(Color.GRAY);
        agregarPlaceholder(campoNombre, "Ingrese nombre");
        gbc.gridx = 1; gbc.gridy = 0;
        panelCampos.add(campoNombre, gbc);

        JLabel etiquetaNumero = new JLabel("Número:", SwingConstants.RIGHT);
        gbc.gridx = 0; gbc.gridy = 1;
        panelCampos.add(etiquetaNumero, gbc);

        campoNumero = new JTextField("Ingrese número", 15);
        campoNumero.setForeground(Color.GRAY);
        agregarPlaceholder(campoNumero, "Ingrese número");
        gbc.gridx = 1; gbc.gridy = 1;
        panelCampos.add(campoNumero, gbc);

        add(panelCampos, BorderLayout.NORTH);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JButton botonAgregar = new JButton("Agregar");
        JButton botonMostrar = new JButton("Mostrar");
        JButton botonActualizar = new JButton("Actualizar");
        JButton botonEliminar = new JButton("Eliminar");
        JButton botonLimpiar = new JButton("Limpiar");

        // Reducir tamaño de los botones
        Dimension botonTamano = new Dimension(100, 30);
        botonAgregar.setPreferredSize(botonTamano);
        botonMostrar.setPreferredSize(botonTamano);
        botonActualizar.setPreferredSize(botonTamano);
        botonEliminar.setPreferredSize(botonTamano);
        botonLimpiar.setPreferredSize(botonTamano);

        panelBotones.add(botonAgregar);
        panelBotones.add(botonMostrar);
        panelBotones.add(botonActualizar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonLimpiar);

        add(panelBotones, BorderLayout.CENTER);

        // Área de contactos más grande
        areaContactos = new JTextArea();
        areaContactos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaContactos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Contactos"));
        scrollPane.setPreferredSize(new Dimension(580, 250));

        add(scrollPane, BorderLayout.SOUTH);

        // Acciones de los botones
        botonAgregar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            String numero = campoNumero.getText();
            if (!nombre.isEmpty() && !numero.isEmpty() && !nombre.equals("Ingrese nombre") && !numero.equals("Ingrese número")) {
                AgregarContacto.main(new String[]{nombre, numero});
                JOptionPane.showMessageDialog(this, "Contacto agregado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre y un número válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonMostrar.addActionListener(e -> {
            MostrarContactos mostrar = new MostrarContactos();
            areaContactos.setText(mostrar.obtenerContactos());
        });

        botonActualizar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            String numero = campoNumero.getText();
            if (!nombre.isEmpty() && !numero.isEmpty() && !nombre.equals("Ingrese nombre") && !numero.equals("Ingrese número")) {
                ActualizarContacto.main(new String[]{nombre, numero});
                JOptionPane.showMessageDialog(this, "Contacto actualizado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre y número válidos para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonEliminar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            if (!nombre.isEmpty() && !nombre.equals("Ingrese nombre")) {
                EliminarContacto.main(new String[]{nombre});
                JOptionPane.showMessageDialog(this, "Contacto eliminado.", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonLimpiar.addActionListener(e -> {
            campoNombre.setText("Ingrese nombre");
            campoNombre.setForeground(Color.GRAY);
            campoNumero.setText("Ingrese número");
            campoNumero.setForeground(Color.GRAY);
        });
    }

    private void agregarPlaceholder(JTextField campo, String textoPlaceholder) {
        campo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(textoPlaceholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(textoPlaceholder);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }

}

