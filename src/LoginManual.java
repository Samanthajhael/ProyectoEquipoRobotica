import javax.swing.*;
import java.awt.event.*;

public class LoginManual extends JFrame {
    private JTextField usuarioField;
    private JPasswordField passwordField;
    private JButton ingresarButton;

    public LoginManual() {
        setTitle("Login Concurso de Robótica");
        setSize(350, 220);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usuarioField = new JTextField();
        usuarioField.setBounds(120, 30, 180, 25);
        add(usuarioField);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 180, 25);
        add(passwordField);

        ingresarButton = new JButton("Ingresar");
        ingresarButton.setBounds(120, 120, 120, 30);
        add(ingresarButton);

        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });

        setVisible(true);
    }

    private void autenticarUsuario() {
        String usuario = usuarioField.getText();
        String contraseña = new String(passwordField.getPassword());

        if ((usuario.equals("admin1") && contraseña.equals("admin123")) ||
                (usuario.equals("jurado1") && contraseña.equals("jurado123"))) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario);
            new PanelInscripcion();  // Aquí abrimos la segunda ventana
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales inválidas.");
        }
    }

    public static void main(String[] args) {
        new LoginManual();
    }
}