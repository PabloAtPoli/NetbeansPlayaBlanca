
package modelo.POJO;

public class UsuarioIdioma {
    private int idUsuario;
    private int idIdioma;

    public UsuarioIdioma(int idUsuario, int idIdioma) {
        this.idUsuario = idUsuario;
        this.idIdioma = idIdioma;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }  
}
