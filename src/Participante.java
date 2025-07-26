public class Participante {
    private int id;
    private String nombreCompleto;
    private int edad;
    private String rol;
    private int idEquipo;

    public Participante() {}

    public Participante(String nombreCompleto, int edad, String rol, int idEquipo) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.rol = rol;
        this.idEquipo = idEquipo;
    }

    public Participante(int id, String nombreCompleto, int edad, String rol, int idEquipo) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.rol = rol;
        this.idEquipo = idEquipo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", edad=" + edad +
                ", rol='" + rol + '\'' +
                ", idEquipo=" + idEquipo +
                '}';
    }
} 