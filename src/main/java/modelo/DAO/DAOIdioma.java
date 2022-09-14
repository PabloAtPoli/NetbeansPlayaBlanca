
package modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import modelo.POJO.Idioma;
import modelo.utilidades.DBUtil;


public class DAOIdioma {

    private Connection conexion;

    public DAOIdioma() throws Exception {
        conexion = DBUtil.getConexion();
    }

    public List<Idioma> obtenerTodosLosIdiomas() throws SQLException {
        List<Idioma> listaIdioma = new LinkedList<>();

        Statement stmt = conexion.createStatement();
        String sql = "SELECT ID_IDIOMA, NOMBRE FROM IDIOMA";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int idIdioma = rs.getInt("ID_IDIOMA");
            String nombre = rs.getString("NOMBRE");

            Idioma i = new Idioma(idIdioma, nombre);
            listaIdioma.add(i);
        }

        stmt.close();
        return listaIdioma;
    }

    public boolean hayUsuariosConEsteIdioma(int idIdioma) throws SQLException {
        Statement stmt = conexion.createStatement();
        String sql = "SELECT ID_USUARIO, ID_IDIOMA FROM USUARIO_IDIOMA";
        ResultSet rs = stmt.executeQuery(sql);

        boolean hayUsuarioConEsteIdioma;
        
        if (!rs.next()) {
            hayUsuarioConEsteIdioma = true;
        } else {
            hayUsuarioConEsteIdioma = false;
        }

        stmt.close();
        return hayUsuarioConEsteIdioma;
    }

    public void guardar(Idioma i) throws SQLException {
        String sql = "INSERT INTO IDIOMA "
                + "(ID_IDIOMA, NOMBRE)"
                + "VALUES(?,?)";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, i.getIdIdioma());
        ps.setString(2, i.getNombre());

        ps.executeUpdate();
    }

    public void borrar(int idIdioma) throws SQLException {
        String sql = "DELETE FROM IDIOMA WHERE ID_IDIOMA=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idIdioma);
        ps.executeUpdate();
        sql = "DELETE FROM IDIOMA WHERE ID_IDIOMA=?";
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, idIdioma);
        ps.executeUpdate();
    }

    public Idioma obtenerIdiomaPorId(int idIdioma) throws SQLException {
        String sql = "SELECT ID_IDIOMA, NOMBRE FROM IDIOMA WHERE ID_IDIOMA=?";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idIdioma);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            idIdioma = rs.getInt("ID_IDIOMA");
            String nombre = rs.getString("NOMBRE");

            Idioma i = new Idioma(idIdioma, nombre);
            return i;
        }

        return null;
    }

    public void editar(Idioma i) throws SQLException {
        String sql = "UPDATE IDIOMA SET NOMBRE= ? WHERE ID_IDIOMA=?";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, i.getNombre());
        ps.setInt(2, i.getIdIdioma());

        ps.executeUpdate();
    }
}
