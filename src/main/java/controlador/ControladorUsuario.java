package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorUsuario extends HttpServlet {

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
        // Obtiene que operación se va realizar que viene de la pagina listarUsuarios.jsp o maestros.jsp
        String opcion = (String) request.getParameter("opcion");

        if (opcion.equals("listarUsuarios")) {
            ServiciosUsuario.listarUsuarios(request, response);
        }

        if (opcion.equals("borrar")) {
            // Obtiene el IdUsuario del Query String (cadena de consulta)
            // que viene de la página listarUsuarios.jsp
            int idUsuario = Integer.parseInt((String) request.getParameter("IdUsuario"));
            ServiciosUsuario.borrarUsuario(idUsuario, request, response);
        }

        if (opcion.equals("crearUsuario")) {
            ServiciosUsuario.mostrarPaginaCreacionUsuario(request, response, "crearUsuario");
        }

        if (opcion.equals("editar")) {
            ServiciosUsuario.mostrarPaginaEditarUsuario(request, response);
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
        // Recupera el valor del parámetro operacion que está en el cuadro de texto oculto
        // llamado operación
        String operacion = request.getParameter("operacion");

        if (operacion != null) {
            if (operacion.equals("crear")) {
                String registreseOcrear = (String) request.getParameter("registreseOcrear");
                ServiciosUsuario.crearUsuario(request, response, registreseOcrear);
            }
            if (operacion.equals("editar")) {
                ServiciosUsuario.editarUsuario(request, response);
            }
        }
    }

}
