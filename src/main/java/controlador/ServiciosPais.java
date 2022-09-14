package controlador;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO.DAOPais;
import modelo.DAO.DAOAdministrador;
import modelo.DAO.DAOUsuarioIdioma;
import modelo.POJO.Pais;
import modelo.POJO.Usuario;
import modelo.POJO.UsuarioIdioma;

public class ServiciosPais {

    public static void listarPaises(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DAOPais daoPais = new DAOPais();

            List<Pais> lstPais = null;

            lstPais = daoPais.obtenerTodosLosPaises();

            // Coloca la lista de países en variables de request para que sea
            // recuperado luego en la página listarPaises.jsp
            request.setAttribute("lstPais", lstPais);

            // Invoca la página listarPaises.jsp
            RequestDispatcher vista = request.getRequestDispatcher("/listarPaises.jsp");
            vista.forward(request, response);

            // Otra forma de invocar la página
//                request.getRequestDispatcher("/listarPaises.jsp").forward(request, response);
        } catch (Exception ex) {
            String mensage = ex.getMessage();
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            String trazaPila = errors.toString();

            request.setAttribute("mensage", mensage);
            request.setAttribute("trazaPila", trazaPila);

            RequestDispatcher vista = request.getRequestDispatcher("/error.jsp");
            vista.forward(request, response);

        }
    }

    public static void crearPais(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Captura los valores que el usuario escribió en el formulario crearPais.jsp
            String nombre = request.getParameter("nombrePais");

            Pais pais = new Pais(0, nombre);

            DAOPais daoPais = new DAOPais();
            daoPais.guardar(pais);
            listarPaises(request, response);

        } catch (Exception ex) {
            String mensage = ex.getMessage();
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            String trazaPila = errors.toString();

            request.setAttribute("mensage", mensage);
            request.setAttribute("trazaPila", trazaPila);

            RequestDispatcher vista = request.getRequestDispatcher("/error.jsp");
            vista.forward(request, response);
        }
    }

}
