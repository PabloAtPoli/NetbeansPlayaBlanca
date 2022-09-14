package modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import modelo.POJO.Usuario;
import modelo.POJO.UsuarioIdioma;
import modelo.utilidades.DBUtil;

public class DAOUsuarioIdioma {

    private Connection conexion;

    public DAOUsuarioIdioma() throws Exception {
        conexion = DBUtil.getConexion();
    }

    public List<UsuarioIdioma> obtenerTodosIdiomasDeUnUsuario(int idUsuarioPar) throws SQLException {
        List<UsuarioIdioma> listaUsuarioIdioma = new LinkedList<>();

        String sql = "SELECT ID_USUARIO, ID_IDIOMA FROM USUARIO_IDIOMA WHERE ID_USUARIO=?";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuarioPar);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("ID_USUARIO");
                int idIdioma = rs.getInt("ID_IDIOMA");

                UsuarioIdioma usuarioIdioma = new UsuarioIdioma(idUsuario, idIdioma);
                listaUsuarioIdioma.add(usuarioIdioma);
            }
        }
        return listaUsuarioIdioma;
    }

    public void guardar(UsuarioIdioma uI) throws SQLException {
        String sql = "INSERT INTO USUARIO_IDIOMA "
                + "(ID_USUARIO, ID_IDIOMA)"
                + "VALUES(?,?)";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, uI.getIdUsuario());
        ps.setInt(2, uI.getIdIdioma());

        ps.executeUpdate();
    }

    public void borrarIdiomasDeUnUsuario(int idUsuario) throws SQLException {
        // Borra los idiomas de la tabla USUARIO_IDIOMA del usuario que se borró
        String sql = "DELETE FROM USUARIO_IDIOMA WHERE ID_USUARIO=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.executeUpdate();

        ps.close();
    }

}
