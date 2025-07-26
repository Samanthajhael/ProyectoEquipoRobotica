import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ParticipantesCRUDInterface extends JFrame {
    private JTable tableParticipantes;
    private DefaultTableModel tableModel;
    private JTextField txtNombre, txtEdad, txtRol, txtIdEquipo;
    private JButton btnAgregar, btnEditar, btnEliminar;
    private GestionParticipantes gestionParticipantes;
    private int selectedRow = -1;

    public ParticipantesCRUDInterface() {
        gestionParticipantes = new GestionParticipantes();
        initComponents();
        cargarParticipantes();
    }

    private void initComponents() {
        setTitle("Gestión de Participantes - Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = createFormPanel();
        JPanel tablePanel = createTablePanel();
        JPanel buttonPanel = createButtonPanel();

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos del Participante"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre Completo:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtNombre = new JTextField(20);
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Edad:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtEdad = new JTextField(10);
        panel.add(txtEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Rol:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtRol = new JTextField(15);
        panel.add(txtRol, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        panel.add(new JLabel("ID Equipo:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtIdEquipo = new JTextField(10);
        panel.add(txtIdEquipo, gbc);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Participantes"));

        String[] columnNames = {"ID", "Nombre Completo", "Edad", "Rol", "ID Equipo"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableParticipantes = new JTable(tableModel);
        tableParticipantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableParticipantes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = tableParticipantes.getSelectedRow();
                if (selectedRow >= 0) {
                    cargarDatosSeleccionados();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableParticipantes);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(e -> agregarParticipante());
        btnEditar.addActionListener(e -> editarParticipante());
        btnEliminar.addActionListener(e -> eliminarParticipante());

        panel.add(btnAgregar);
        panel.add(btnEditar);
        panel.add(btnEliminar);

        return panel;
    }

    private void cargarParticipantes() {
        tableModel.setRowCount(0);
        try {
            java.util.List<Participante> participantes = gestionParticipantes.obtenerTodosLosParticipantes();
            for (Participante p : participantes) {
                Vector<Object> row = new Vector<>();
                row.add(p.getId());
                row.add(p.getNombreCompleto());
                row.add(p.getEdad());
                row.add(p.getRol());
                row.add(p.getIdEquipo());
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar participantes: " + e.getMessage());
        }
    }

    private void cargarDatosSeleccionados() {
        if (selectedRow >= 0) {
            txtNombre.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtEdad.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtRol.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtIdEquipo.setText(tableModel.getValueAt(selectedRow, 4).toString());
        }
    }

    private void agregarParticipante() {
        if (!validarFormulario()) return;

        try {
            Participante participante = new Participante(
                txtNombre.getText().trim(),
                Integer.parseInt(txtEdad.getText().trim()),
                txtRol.getText().trim(),
                Integer.parseInt(txtIdEquipo.getText().trim())
            );

            gestionParticipantes.guardarParticipante(participante);
            JOptionPane.showMessageDialog(this, "Participante agregado exitosamente");
            cargarParticipantes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar participante: " + e.getMessage());
        }
    }

    private void editarParticipante() {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un participante para editar");
            return;
        }

        if (!validarFormulario()) return;

        try {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            Participante participante = new Participante(
                id,
                txtNombre.getText().trim(),
                Integer.parseInt(txtEdad.getText().trim()),
                txtRol.getText().trim(),
                Integer.parseInt(txtIdEquipo.getText().trim())
            );

            gestionParticipantes.modificarParticipante(participante);
            JOptionPane.showMessageDialog(this, "Participante actualizado exitosamente");
            cargarParticipantes();
            selectedRow = -1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar participante: " + e.getMessage());
        }
    }

    private void eliminarParticipante() {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un participante para eliminar");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este participante?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                gestionParticipantes.borrarParticipante(id);
                JOptionPane.showMessageDialog(this, "Participante eliminado exitosamente");
                cargarParticipantes();
                selectedRow = -1;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar participante: " + e.getMessage());
            }
        }
    }

    private boolean validarFormulario() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
            txtNombre.requestFocus();
            return false;
        }

        try {
            Integer.parseInt(txtEdad.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido");
            txtEdad.requestFocus();
            return false;
        }

        if (txtRol.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El rol es obligatorio");
            txtRol.requestFocus();
            return false;
        }

        try {
            Integer.parseInt(txtIdEquipo.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID del equipo debe ser un número válido");
            txtIdEquipo.requestFocus();
            return false;
        }

        return true;
    }
} 