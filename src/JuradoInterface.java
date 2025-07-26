import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class JuradoInterface extends JFrame {
    private JTable tableEquipos;
    private DefaultTableModel tableModel;
    private JTextField txtPuntaje, txtResultado;
    private JButton btnActualizarPuntaje, btnActualizarLista, btnCerrar;
    private GestionEquipos gestionEquipos;
    private int selectedRow = -1;

    public JuradoInterface() {
        gestionEquipos = new GestionEquipos();
        initComponents();
        cargarEquipos();
    }

    private void initComponents() {
        setTitle("Panel de Jurado - Gestión de Equipos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel infoPanel = createInfoPanel();
        JPanel tablePanel = createTablePanel();
        JPanel buttonPanel = createButtonPanel();

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Editar Puntaje y Resultado"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Puntaje:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtPuntaje = new JTextField(10);
        panel.add(txtPuntaje, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Resultado:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtResultado = new JTextField(20);
        panel.add(txtResultado, gbc);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Equipos"));

        String[] columnNames = {"ID", "Nombre", "Categoría ID", "Puntaje", "Resultado"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableEquipos = new JTable(tableModel);
        tableEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableEquipos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = tableEquipos.getSelectedRow();
                if (selectedRow >= 0) {
                    cargarDatosSeleccionados();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableEquipos);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        
        btnActualizarPuntaje = new JButton("Actualizar Puntaje");
        btnActualizarLista = new JButton("Actualizar Lista");
        btnCerrar = new JButton("Cerrar Sesión");

        btnActualizarPuntaje.addActionListener(e -> actualizarPuntaje());
        btnActualizarLista.addActionListener(e -> cargarEquipos());
        btnCerrar.addActionListener(e -> cerrarSesion());

        panel.add(btnActualizarPuntaje);
        panel.add(btnActualizarLista);
        panel.add(btnCerrar);

        return panel;
    }

    private void cargarEquipos() {
        tableModel.setRowCount(0);
        try {
            java.util.List<Equipo> equipos = gestionEquipos.obtenerTodosLosEquipos();
            for (Equipo e : equipos) {
                Vector<Object> row = new Vector<>();
                row.add(e.getId());
                row.add(e.getNombre());
                row.add(e.getCategoriaId());
                row.add(e.getPuntaje());
                row.add(e.getResultado());
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar equipos: " + e.getMessage());
        }
    }

    private void cargarDatosSeleccionados() {
        if (selectedRow >= 0) {
            txtPuntaje.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtResultado.setText(tableModel.getValueAt(selectedRow, 4).toString());
        }
    }

    private void actualizarPuntaje() {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un equipo para actualizar");
            return;
        }

        if (!validarFormulario()) return;

        try {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            String nombre = tableModel.getValueAt(selectedRow, 1).toString();
            int categoriaId = (Integer) tableModel.getValueAt(selectedRow, 2);
            
            Equipo equipo = new Equipo(
                id,
                nombre,
                categoriaId,
                Integer.parseInt(txtPuntaje.getText().trim()),
                txtResultado.getText().trim()
            );

            gestionEquipos.modificarEquipo(equipo);
            JOptionPane.showMessageDialog(this, "Puntaje y resultado actualizados exitosamente");
            cargarEquipos();
            limpiarFormulario();
            selectedRow = -1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar equipo: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtPuntaje.setText("");
        txtResultado.setText("");
        selectedRow = -1;
        tableEquipos.clearSelection();
    }

    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea cerrar sesión?", 
            "Confirmar cierre de sesión", 
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            this.dispose();
            new LoginInterface().setVisible(true);
        }
    }

    private boolean validarFormulario() {
        try {
            int puntaje = Integer.parseInt(txtPuntaje.getText().trim());
            if (puntaje < 0 || puntaje > 100) {
                JOptionPane.showMessageDialog(this, "El puntaje debe estar entre 0 y 100");
                txtPuntaje.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El puntaje debe ser un número válido");
            txtPuntaje.requestFocus();
            return false;
        }

        if (txtResultado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El resultado es obligatorio");
            txtResultado.requestFocus();
            return false;
        }

        return true;
    }
} 