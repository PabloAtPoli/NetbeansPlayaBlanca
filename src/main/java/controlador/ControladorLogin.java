package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO.DAOAdministrador;
import modelo.DAO.DAOUsuario;
import modelo.POJO.Administrador;
import modelo.POJO.Usuario;

public class ControladorLogin extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera el valor del parámetro registreseOcrear del objeto request
        String registreseOcrear = (String) request.getParameter("registreseOcrear");

        if (registreseOcrear.equals("logout")) {
            // Cierra la sesión del usuario
            HttpSession session = request.getSession(); // Recupera la sesión actual
            session.invalidate(); // Destruye la sesión actual

            // Invoca la página index.jsp
            response.sendRedirect("index.jsp");
        }

        if (registreseOcrear.equals("registrese")) {
            ServiciosUsuario.mostrarPaginaCreacionUsuario(request, response, "registrese");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Captura el valor de los campos email y pass de la página login.jsp
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        try {
            DAOAdministrador daoAdministrador = new DAOAdministrador();
            Administrador admin = daoAdministrador.obtenerAdministradorPorEmail(email);

            if ((admin != null) && admin.getPassword().equals(pass)) {
                //Crea una session para el administrador de la aplicación
                HttpSession session = request.getSession();
                // La session del administrador nunca expirará
                session.setMaxInactiveInterval(0);

                // Se coloca el tipo de usuario para que las páginas de la vista
                // sepan que está logueao el administrador
                session.setAttribute("nombreUsuario", admin.getNombre());
                session.setAttribute("tipoUsuario", "admin");

                // Invoca la página index
                response.sendRedirect("index.jsp");
            } else {

                // Busca si existe un usuario en la tabla Usuario con el email entrado 
                // en la página login.jsp
                DAOUsuario daoUsuario = new DAOUsuario();
                Usuario usuario = daoUsuario.obtenerUsuarioPorEmail(email);

                if ((usuario != null) && usuario.getPassword().equals(pass)) {
                    // El usuario ya está registrado
                    // Crea la sesión para el usuario
                    HttpSession session = request.getSession();

                    // Almacena en las variables de sesión el nombre y el tipo de usuario
                    session.setAttribute("nombreUsuario", usuario.getNombre());
                    session.setAttribute("tipoUsuario", "usuario");

                    // invoca la página index.jsp
                    response.sendRedirect("index.jsp");
                } else {
                    // Trató de loguearse un usuario que no está registrado
                    request.setAttribute("usuarioInvalido", "usuarioInvalido");

                    // Se invoca la página login.jsp
                    RequestDispatcher vista = request.getRequestDispatcher("/login.jsp");
                    vista.forward(request, response);
                }
            }
        } catch (Exception ex) {
            // obtiene el mensaje de error del objecto excepcion ex
            String mensage = ex.getMessage();

            // La traza de la pila se lleva a la variable string trazaPila
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            String trazaPila = errors.toString();

            // Se almacena el mensaje de error y la traza de la pila en variables del objeto request
            request.setAttribute("mensage", mensage);
            request.setAttribute("trazaPila", trazaPila);

            // Se invoca la página de error
            RequestDispatcher vista = request.getRequestDispatcher("/error.jsp");
            vista.forward(request, response);
        }
    }
}
