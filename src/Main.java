import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Iniciar la aplicación con la interfaz gráfica de login
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginInterface().setVisible(true);
            }
        });
    }
}

