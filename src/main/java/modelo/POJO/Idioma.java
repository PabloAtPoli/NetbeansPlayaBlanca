
package modelo.POJO;

public class Idioma {
    private int idIdioma;
    private String nombre;

    public Idioma(int idIdioma, String nombre) {
        this.idIdioma = idIdioma;
        this.nombre = nombre;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    } 
}
