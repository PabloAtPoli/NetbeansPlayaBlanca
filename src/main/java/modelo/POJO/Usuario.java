package modelo.POJO;

public class Usuario {

    private int idUsuario;
    private String email;
    private String nombre;
    private String genero;
    private String password;
    private int idPais;
    private String NombrePais;
    private boolean cine;
    private boolean musica;
    private boolean deportes;

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return NombrePais;
    }

    public void setNombrePais(String NombrePais) {
        this.NombrePais = NombrePais;
    }

    public boolean isCine() {
        return cine;
    }

    public void setCine(boolean cine) {
        this.cine = cine;
    }

    public boolean isMusica() {
        return musica;
    }

    public void setMusica(boolean musica) {
        this.musica = musica;
    }

    public boolean isDeportes() {
        return deportes;
    }

    public void setDeportes(boolean deportes) {
        this.deportes = deportes;
    }

    public Usuario(int idUsuario, String email, String nombre, String genero, String password, int idPais, String NombrePais, boolean cine, boolean musica, boolean deportes) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nombre = nombre;
        this.genero = genero;
        this.password = password;
        this.idPais = idPais;
        this.NombrePais = NombrePais;
        this.cine = cine;
        this.musica = musica;
        this.deportes = deportes;
    }

}
