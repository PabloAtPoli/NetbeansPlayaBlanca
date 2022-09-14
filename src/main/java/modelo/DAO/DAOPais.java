package modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import modelo.POJO.Pais;
import modelo.POJO.Usuario;
import modelo.utilidades.DBUtil;

public class DAOPais {

    private Connection conexion;

    public DAOPais() throws Exception {
        conexion = DBUtil.getConexion();
    }

    public List<Pais> obtenerTodosLosPaises() throws SQLException {
        // Crea una lista de ojetos país
        List<Pais> listaPais = new LinkedList<>();

        Statement stmt = conexion.createStatement();
        String sql = "SELECT ID_PAIS, NOMBRE FROM PAIS";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int idPais = rs.getInt("ID_PAIS");
            String nombre = rs.getString("NOMBRE");

            Pais p = new Pais(idPais, nombre);
            listaPais.add(p);
        }

        stmt.close();
        return listaPais;
    }

    public String obtenerNombrePaisPor(int idPais) throws SQLException {

        String sql = "SELECT ID_PAIS, NOMBRE FROM PAIS WHERE ID_PAIS = ?";

        // La clase PreparedStatement se usa cuando la instrucción de SQL tiene parámetros
        PreparedStatement ps = conexion.prepareStatement(sql);

        // Asigna el idPais al parámetro 1 de la instrucción SQL
        ps.setInt(1, idPais);

        // Como el identificador del país es único en la tabla país,
        // el resultado de la consulta anterior es un solo registro
        ResultSet rs = ps.executeQuery();

        String nombrePais = null;
        if (rs.next()) {
            nombrePais = rs.getString("NOMBRE");
        }
        return nombrePais;
    }

    public void guardar(Pais pais) throws SQLException {
        String sql = "INSERT INTO PAIS (NOMBRE) "
                + "VALUES(?)";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, pais.getNombre());

        ps.executeUpdate();
    }

}
