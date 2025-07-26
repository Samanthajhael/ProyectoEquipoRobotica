import java.sql.*;

public class conexion{
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://ukezh1ojnunqubzv:QUAHmatVNNTzTbPltHFd@bcvjojpqho2j01wr9g3b-mysql.services.clever-cloud.com:3306/bcvjojpqho2j01wr9g3b";
        String user = "ukezh1ojnunqubzv";
        String password = "QUAHmatVNNTzTbPltHFd";
        String query="Select * from cliente"; // acceder a la consulta
        try (Connection conecta = DriverManager.getConnection(url, user, password)) {
            System.out.println("CONEXION EXITOSA");
            PreparedStatement statement=conecta.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                int codigos=resultSet.getInt("codigo");
                String nombres=resultSet.getString("nombre");
                String correos=resultSet.getString("correo");
                String pass=resultSet.getString("password");
                System.out.println("CODIGO: "+codigos+ " NOMBRE: "+nombres+ " CORREOS: "+correos +" PASSWORD"+pass);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
