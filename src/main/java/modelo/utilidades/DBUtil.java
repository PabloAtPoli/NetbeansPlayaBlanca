package modelo.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static Connection conexion = null;

    // Permite conectarse con un usuario de una base de datos ORACLE
    public static Connection getConexion() throws SQLException, ClassNotFoundException  {
        // Carga el driver de la base de datos
        Class.forName("oracle.jdbc.OracleDriver");

        System.out.println("Driver de ORACLE cargado");

        // Credenciales para conectarse con la base de datos
        String nombreUsuario = "USUARIO5";
        String password = "USUARIO5";
        
        // Este es el string de conexion para conectarse con el Oracle express que esta almacenado
        // en localhost; es decir, en esta máquina
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        
        // Este método conecta la aplicación con la base de datos y retorna el objeto conexion
        conexion = DriverManager.getConnection(url, nombreUsuario, password);
        System.out.println("Conexión establecida satisfactoriamente");

        return conexion;
    }
}
