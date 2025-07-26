import javax.swing.*;

public class PanelInscripcion extends JFrame {
    private JTextField nombreEquipoField;
    private JTextField categoriaField;
    private JTextField integranteField;
    private JTextField edadField;
    private JTextField rolField;
    private JTextField experienciaField;
    private JTextField resultadoField;
    private JButton agregarButton;
    private JButton verButton;

    public PanelInscripcion() {
        setTitle("Inscripción de Equipos");
        setSize(450, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Formulario de Inscripción");
        titleLabel.setBounds(140, 10, 200, 25);
        add(titleLabel);

        JLabel equipoLabel = new JLabel("Nombre del equipo:");
        equipoLabel.setBounds(30, 50, 140, 25);
        add(equipoLabel);

        nombreEquipoField = new JTextField();
        nombreEquipoField.setBounds(180, 50, 200, 25);
        add(nombreEquipoField);

        JLabel categoriaLabel = new JLabel("Categoría:");
        categoriaLabel.setBounds(30, 90, 140, 25);
        add(categoriaLabel);

        categoriaField = new JTextField();
        categoriaField.setBounds(180, 90, 200, 25);
        add(categoriaField);

        JLabel integranteLabel = new JLabel("Nombre del integrante:");
        integranteLabel.setBounds(30, 130, 140, 25);
        add(integranteLabel);

        integranteField = new JTextField();
        integranteField.setBounds(180, 130, 200, 25);
        add(integranteField);

        JLabel edadLabel = new JLabel("Edad:");
        edadLabel.setBounds(30, 170, 140, 25);
        add(edadLabel);

        edadField = new JTextField();
        edadField.setBounds(180, 170, 200, 25);
        add(edadField);

        JLabel rolLabel = new JLabel("Rol:");
        rolLabel.setBounds(30, 210, 140, 25);
        add(rolLabel);

        rolField = new JTextField();
        rolField.setBounds(180, 210, 200, 25);
        add(rolField);

        JLabel experienciaLabel = new JLabel("Experiencia:");
        experienciaLabel.setBounds(30, 250, 140, 25);
        add(experienciaLabel);

        experienciaField = new JTextField();
        experienciaField.setBounds(180, 250, 200, 25);
        add(experienciaField);

        JLabel resultadoLabel = new JLabel("Resultado:");
        resultadoLabel.setBounds(30, 290, 140, 25);
        add(resultadoLabel);

        resultadoField = new JTextField();
        resultadoField.setBounds(180, 290, 200, 25);
        add(resultadoField);

        agregarButton = new JButton("Agregar");
        agregarButton.setBounds(80, 330, 120, 30);
        add(agregarButton);

        verButton = new JButton("Ver Equipos");
        verButton.setBounds(240, 330, 120, 30);
        add(verButton);

        agregarButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Función AGREGAR aún no implementada.");
        });

        verButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Función VER aún no implementada.");
        });

        setVisible(true);
    }
}