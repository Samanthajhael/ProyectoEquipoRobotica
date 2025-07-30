import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private UsuarioBase usuarioBase;

    public LoginInterface() {
        usuarioBase = new UsuarioBase();
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Login - Equipo Robótica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel del título
        JPanel titlePanel = new JPanel();
        JLabel lblTitle = new JLabel("SISTEMA DE LOGIN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(lblTitle);

        // Panel del formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtUsername = new JTextField(20);
        formPanel.add(txtUsername, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtPassword = new JPasswordField(20);
        formPanel.add(txtPassword, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        btnLogin = new JButton("Iniciar Sesión");
        btnCancel = new JButton("Cancelar");
        
        btnLogin.setPreferredSize(new Dimension(120, 35));
        btnCancel.setPreferredSize(new Dimension(120, 35));

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCancel);

        // Agregar componentes al panel principal
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Eventos
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        getRootPane().setDefaultButton(btnLogin);

        add(mainPanel);
    }

    private void login() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());


        Usuario usuario = usuarioBase.login(username, password);

        if (usuario == null) {
            JOptionPane.showMessageDialog(this, 
                "Usuario o contraseña incorrectos", 
                "Error de Login", 
                JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtPassword.requestFocus();
            return;
        }

        JOptionPane.showMessageDialog(this, 
            "Bienvenido " + usuario.getUsername() + " (" + usuario.getRol() + ")", 
            "Login Exitoso", 
            JOptionPane.INFORMATION_MESSAGE);

        if (usuario.getRol().equalsIgnoreCase("administrador")) {
            new ParticipantesCRUDInterface().setVisible(true);
        } else if (usuario.getRol().equalsIgnoreCase("jurado")) {
            new JuradoInterface().setVisible(true);
        }

        this.dispose(); // Cerrar la ventana de login
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginInterface().setVisible(true);
            }
        });
    }
} 