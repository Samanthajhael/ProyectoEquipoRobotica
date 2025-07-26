public class Equipo {
    private int id;
    private String nombre;
    private int categoriaId;
    private int puntaje;
    private String resultado;

    public Equipo() {}

    public Equipo(String nombre, int categoriaId, int puntaje, String resultado) {
        this.nombre = nombre;
        this.categoriaId = categoriaId;
        this.puntaje = puntaje;
        this.resultado = resultado;
    }

    public Equipo(int id, String nombre, int categoriaId, int puntaje, String resultado) {
        this.id = id;
        this.nombre = nombre;
        this.categoriaId = categoriaId;
        this.puntaje = puntaje;
        this.resultado = resultado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoriaId=" + categoriaId +
                ", puntaje=" + puntaje +
                ", resultado='" + resultado + '\'' +
                '}';
    }
} 