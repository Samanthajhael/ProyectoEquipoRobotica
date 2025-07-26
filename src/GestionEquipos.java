import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionEquipos {
    
    public void guardarEquipo(Equipo equipo) throws SQLException {
        String sql = "INSERT INTO equipos (nombre, categoriaId, puntaje, resultado) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, equipo.getNombre());
            pstmt.setInt(2, equipo.getCategoriaId());
            pstmt.setInt(3, equipo.getPuntaje());
            pstmt.setString(4, equipo.getResultado());
            
            pstmt.executeUpdate();
        }
    }
    
    public void modificarEquipo(Equipo equipo) throws SQLException {
        String sql = "UPDATE equipos SET nombre = ?, categoriaId = ?, puntaje = ?, resultado = ? WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, equipo.getNombre());
            pstmt.setInt(2, equipo.getCategoriaId());
            pstmt.setInt(3, equipo.getPuntaje());
            pstmt.setString(4, equipo.getResultado());
            pstmt.setInt(5, equipo.getId());
            
            pstmt.executeUpdate();
        }
    }
    
    public void borrarEquipo(int id) throws SQLException {
        String sql = "DELETE FROM equipos WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public Equipo buscarEquipo(int id) throws SQLException {
        String sql = "SELECT * FROM equipos WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Equipo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("categoriaId"),
                    rs.getInt("puntaje"),
                    rs.getString("resultado")
                );
            }
        }
        return null;
    }
    
    public List<Equipo> obtenerTodosLosEquipos() throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos ORDER BY id";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Equipo equipo = new Equipo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("categoriaId"),
                    rs.getInt("puntaje"),
                    rs.getString("resultado")
                );
                equipos.add(equipo);
            }
        }
        return equipos;
    }
    
    public List<Equipo> obtenerEquiposPorCategoria(int categoriaId) throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE categoriaId = ? ORDER BY puntaje DESC";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, categoriaId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Equipo equipo = new Equipo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("categoriaId"),
                    rs.getInt("puntaje"),
                    rs.getString("resultado")
                );
                equipos.add(equipo);
            }
        }
        return equipos;
    }
    
    public void cambiarPuntaje(int id, int puntaje, String resultado) throws SQLException {
        String sql = "UPDATE equipos SET puntaje = ?, resultado = ? WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, puntaje);
            pstmt.setString(2, resultado);
            pstmt.setInt(3, id);
            
            pstmt.executeUpdate();
        }
    }
} 