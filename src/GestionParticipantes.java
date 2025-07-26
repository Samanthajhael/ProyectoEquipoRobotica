import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionParticipantes {
    
    public void guardarParticipante(Participante participante) throws SQLException {
        String sql = "INSERT INTO participantes (nombre_completo, edad, rol, id_equipo) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, participante.getNombreCompleto());
            pstmt.setInt(2, participante.getEdad());
            pstmt.setString(3, participante.getRol());
            pstmt.setInt(4, participante.getIdEquipo());
            
            pstmt.executeUpdate();
        }
    }
    
    public void modificarParticipante(Participante participante) throws SQLException {
        String sql = "UPDATE participantes SET nombre_completo = ?, edad = ?, rol = ?, id_equipo = ? WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, participante.getNombreCompleto());
            pstmt.setInt(2, participante.getEdad());
            pstmt.setString(3, participante.getRol());
            pstmt.setInt(4, participante.getIdEquipo());
            pstmt.setInt(5, participante.getId());
            
            pstmt.executeUpdate();
        }
    }
    
    public void borrarParticipante(int id) throws SQLException {
        String sql = "DELETE FROM participantes WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public Participante buscarParticipante(int id) throws SQLException {
        String sql = "SELECT * FROM participantes WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Participante(
                    rs.getInt("id"),
                    rs.getString("nombre_completo"),
                    rs.getInt("edad"),
                    rs.getString("rol"),
                    rs.getInt("id_equipo")
                );
            }
        }
        return null;
    }
    
    public List<Participante> obtenerTodosLosParticipantes() throws SQLException {
        List<Participante> participantes = new ArrayList<>();
        String sql = "SELECT * FROM participantes ORDER BY id";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Participante participante = new Participante(
                    rs.getInt("id"),
                    rs.getString("nombre_completo"),
                    rs.getInt("edad"),
                    rs.getString("rol"),
                    rs.getInt("id_equipo")
                );
                participantes.add(participante);
            }
        }
        return participantes;
    }
    
    public List<Participante> obtenerParticipantesPorEquipo(int idEquipo) throws SQLException {
        List<Participante> participantes = new ArrayList<>();
        String sql = "SELECT * FROM participantes WHERE id_equipo = ? ORDER BY id";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idEquipo);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Participante participante = new Participante(
                    rs.getInt("id"),
                    rs.getString("nombre_completo"),
                    rs.getInt("edad"),
                    rs.getString("rol"),
                    rs.getInt("id_equipo")
                );
                participantes.add(participante);
            }
        }
        return participantes;
    }
} 