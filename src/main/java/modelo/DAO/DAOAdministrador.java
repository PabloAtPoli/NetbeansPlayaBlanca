package modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import modelo.POJO.Administrador;
import modelo.POJO.Usuario;
import modelo.utilidades.DBUtil;

public class DAOAdministrador {

    private Connection conexion;

    public DAOAdministrador() throws Exception {
        conexion = DBUtil.getConexion();
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> listaUsuario = new LinkedList<>();

        Statement stmt = conexion.createStatement();
        String sql = "SELECT ID_USUARIO, EMAIL, USUARIO.NOMBRE,"
                + " PASSWORD, GENERO, PAIS.ID_PAIS AS ID_PAIS, "
                + "PAIS.NOMBRE AS PAIS, CINE, MUSICA, DEPORTES"
                + " FROM USUARIO INNER JOIN PAIS ON "
                + "USUARIO.ID_PAIS = PAIS.ID_PAIS ORDER BY USUARIO.ID_USUARIO";

        // El resultado de la anterior consulta queda en un ResultSet (tabla en memoria)
        ResultSet rs = stmt.executeQuery(sql);

        // cada vez que se ejecuta el método rs.next(), se avanza el cursor una fila 
        // Cuando se alcalza el fin de la tabla, la funcion rs.next() retorna false
        while (rs.next()) {
            int idUsuario = rs.getInt("ID_USUARIO");
            String email = rs.getString("EMAIL");
            String nombre = rs.getString("NOMBRE");
            String genero = rs.getString("GENERO");
            String password = rs.getString("PASSWORD");
            int idPais = rs.getInt("ID_PAIS");
            String pais = rs.getString("PAIS");
            boolean isCine = rs.getBoolean("CINE");
            boolean isMusica = rs.getBoolean("MUSICA");
            boolean isDeporte = rs.getBoolean("DEPORTES");

            Usuario u = new Usuario(idUsuario, email, nombre, genero, password, idPais, pais, isCine, isMusica, isDeporte);
            listaUsuario.add(u);
        }

        stmt.close();
        return listaUsuario;
    }

    public void borrar(int idUsuario) throws SQLException {
        // Primero borra los idiomas del usuario en la tabla USUARIO_IDIOMA
        String sql = "DELETE FROM USUARIO_IDIOMA WHERE ID_USUARIO=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.executeUpdate();

        // Luego borra el usuario de la tabla USUARIO
        sql = "DELETE FROM USUARIO WHERE ID_USUARIO=?";
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ps.executeUpdate();
    }

    public Administrador obtenerAdministradorPorEmail(String emailAdmin) throws SQLException {
         // SELECT EMAIL, NOMBRE, PASSWORD FROM ADMINISTRADOR WHERE EMAIL = 'elon@gmail.com' ;  
        String sql = "SELECT EMAIL, NOMBRE, PASSWORD FROM ADMINISTRADOR WHERE EMAIL=?";
      
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, emailAdmin);
        
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String email = rs.getString("EMAIL");
            String nombre = rs.getString("NOMBRE");
            String password = rs.getString("PASSWORD");

            Administrador a = new Administrador(email, nombre, password);
            return a;
        }

        return null;
    }

    public Usuario obtenerUsuarioPorEmail(String email) throws SQLException {
        String sql = "SELECT ID_USUARIO,EMAIL, USUARIO.NOMBRE,"
                + " PASSWORD, GENERO, PAIS.ID_PAIS AS ID_PAIS, "
                + "PAIS.NOMBRE AS PAIS, CINE, MUSICA, DEPORTES"
                + " FROM USUARIO INNER JOIN PAIS ON "
                + "USUARIO.ID_PAIS = PAIS.ID_PAIS WHERE EMAIL=?";

        // Se asigna el parámetro email a instrucción de SQL
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, email);

        // El resultado de la consulta queda en una tabla en memoria conocida
        // como ResultSet
        ResultSet rs = ps.executeQuery();

        // El método next hace avanzar el cursor del ResultSet al siguiente registro
        if (rs.next()) {
            int idUsuario = rs.getInt("ID_USUARIO");
            String nombre = rs.getString("NOMBRE");
            String genero = rs.getString("GENERO");
            String password = rs.getString("PASSWORD");
            int idPais = rs.getInt("ID_PAIS");
            String pais = rs.getString("PAIS");
            boolean isCine = rs.getBoolean("CINE");
            boolean isMusica = rs.getBoolean("MUSICA");
            boolean isDeporte = rs.getBoolean("DEPORTES");

            Usuario u = new Usuario(idUsuario, email, nombre, genero, password, idPais, pais, isCine, isMusica, isDeporte);
            return u;
        }

        return null;
    }

    public void guardar(Usuario u) throws SQLException {
        String sql = "INSERT INTO USUARIO (EMAIL, NOMBRE, PASSWORD, GENERO, ID_PAIS, CINE, MUSICA, DEPORTES)"
                + "VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getNombre());
        ps.setString(3, u.getPassword());
        ps.setString(4, u.getGenero());
        ps.setInt(5, u.getIdPais());
        ps.setBoolean(6, u.isCine());
        ps.setBoolean(7, u.isMusica());
        ps.setBoolean(8, u.isDeportes());

        ps.executeUpdate();
    }

    public void editar(Usuario u) throws SQLException {
        String sql = "UPDATE USUARIO SET EMAIL=?, NOMBRE= ?, "
                + "PASSWORD=?, GENERO= ?, ID_PAIS= ?,"
                + " CINE= ?,  MUSICA=?, DEPORTES = ? WHERE ID_USUARIO=?";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, u.getEmail());
        ps.setString(2, u.getNombre());
        ps.setString(3, u.getPassword());
        ps.setString(4, u.getGenero());
        ps.setInt(5, u.getIdPais());
        ps.setBoolean(6, u.isCine());
        ps.setBoolean(7, u.isMusica());
        ps.setBoolean(8, u.isDeportes());
        ps.setInt(9, u.getIdUsuario());

        ps.executeUpdate();

        ps.close();
    }

    public int obtnerIdMasGrande() throws SQLException {
        Statement stmt = conexion.createStatement();
        String sql = "SELECT MAX(ID_USUARIO) AS MAXID FROM  USUARIO";
        ResultSet rs = stmt.executeQuery(sql);

        int idMasGrande = 0;

        // cada vez que se ejecuta el método rs.next(), se avanza el cursor una fila 
        // Cuando se alcalza el fin del cursor, la funcion rs.next() retorna false
        if (rs.next()) {
            idMasGrande = rs.getInt("MAXID");
        }

        stmt.close();
        return idMasGrande;
    }
}
