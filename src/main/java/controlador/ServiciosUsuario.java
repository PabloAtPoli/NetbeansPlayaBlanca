package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO.DAOIdioma;
import modelo.DAO.DAOPais;
import modelo.DAO.DAOAdministrador;
import modelo.DAO.DAOUsuario;
import modelo.DAO.DAOUsuarioIdioma;
import modelo.POJO.Idioma;
import modelo.POJO.Pais;
import modelo.POJO.Usuario;
import modelo.POJO.UsuarioIdioma;

public class ServiciosUsuario {
    
    public static void crearUsuario(HttpServletRequest request, HttpServletResponse response, String registreseOcrear) throws ServletException, IOException {
        try {
            // Captura los valores que el usuario escribió en el formulario de la página crearUsuario.jsp
            String email = request.getParameter("email");
            String nombre = request.getParameter("nombreUsuario");
            String genero = request.getParameter("genero");
            String password = request.getParameter("password");
            int idPais = Integer.parseInt(request.getParameter("pais"));
            DAOPais daoPais = new DAOPais();
            
            String pais = daoPais.obtenerNombrePaisPor(idPais);
            
            // Como los hobbies están en varios cuadros de verificación, se asigna a un vector de cadenas (strings)
            String[] hobbies = request.getParameterValues("Hobbies");
            
            boolean cine = false;
            boolean musica = false;
            boolean deportes = false;
            for (int i = 0; hobbies != null && i < hobbies.length; i++) {
                if (hobbies[i].equalsIgnoreCase("cine")) {
                    cine = true;
                }
                if (hobbies[i].equalsIgnoreCase("musica")) {
                    musica = true;
                }
                if (hobbies[i].equalsIgnoreCase("deportes")) {
                    deportes = true;
                }
            }
            
            Usuario u = new Usuario(0, email, nombre, genero, password, idPais, pais, cine, musica, deportes);
            
            DAOAdministrador daoUsuario = new DAOAdministrador();
            daoUsuario.guardar(u);

            // Guarda en la tabla UsuarioIdioma los idiomas que seleccionó el usuario
            String[] idiomas = request.getParameterValues("idiomas");
            
            if (idiomas != null) {
                DAOUsuarioIdioma daoUsuarioIdioma = new DAOUsuarioIdioma();
                
                for (int i = 0; i < idiomas.length; i++) {
                    int idIdioma = Integer.parseInt(idiomas[i]);
                    UsuarioIdioma usuarioIdioma
                            = new UsuarioIdioma(daoUsuario.obtnerIdMasGrande(), idIdioma);
                    daoUsuarioIdioma.guardar(usuarioIdioma);
                }
            }
            
            if (registreseOcrear.equals("crearUsuario")) {
                // El administrador está logueado y esta creando un Usuario 
                listarUsuarios(request, response);
            }
            
            if (registreseOcrear.equals("registrese")) {
                //Crea una session
                HttpSession session = request.getSession(false);
                session.setAttribute("tipoUsuario", "usuario");
                session.setAttribute("nombreUsuario", nombre);
                response.sendRedirect("index.jsp");
            }
            
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
    
    public static void editarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Captura los valores que el usuario escribió en el formulario
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String email = request.getParameter("email");
            String nombre = request.getParameter("nombreUsuario");
            String genero = request.getParameter("genero");
            String password = request.getParameter("password");
            int idPais = Integer.parseInt(request.getParameter("pais"));
            DAOPais daoPais = new DAOPais();
            
            String pais = daoPais.obtenerNombrePaisPor(idPais);

            // Obtiene los hobbies de la página editarUsuario.jsp y los
            // coloca en el vector de cadena de caracteres hobbies
            String[] hobbies = request.getParameterValues("Hobbies");
            
            boolean cine = false;
            boolean musica = false;
            boolean deportes = false;
            
            if (hobbies != null) {
                for (String hobbie : hobbies) {
                    if (hobbie.equalsIgnoreCase("cine")) {
                        cine = true;
                    }
                    if (hobbie.equalsIgnoreCase("musica")) {
                        musica = true;
                    }
                    if (hobbie.equalsIgnoreCase("deportes")) {
                        deportes = true;
                    }
                }
            }
            
            Usuario u = new Usuario(idUsuario, email, nombre, genero, password, idPais, pais,
                    cine, musica, deportes);
            
            DAOAdministrador daoUsuario = new DAOAdministrador();
            daoUsuario.editar(u);

            // Borrar los idiomas del usuario que se borro de la tabla Usuario
            DAOUsuarioIdioma daoUsuarioIdioma = new DAOUsuarioIdioma();
            daoUsuarioIdioma.borrarIdiomasDeUnUsuario(idUsuario);

            //Almacena en el vector idioma los idiomas que selecciono el usuario en la página editar.jsp
            String[] idiomas = request.getParameterValues("idiomas");

            // Guarda en la tabla UsuarioIdioma los idiomas que seleccionó el usuario
            for (int i = 0; i < idiomas.length; i++) {
                int idIdioma = Integer.parseInt(idiomas[i]);
                UsuarioIdioma usuarioIdioma
                        = new UsuarioIdioma(idUsuario, idIdioma);
                daoUsuarioIdioma.guardar(usuarioIdioma);
            }
            
            listarUsuarios(request, response);
            
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
    
    public static void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DAOAdministrador daoUsuario = new DAOAdministrador();
            
            List<Usuario> lstUsuario = null;
            
            lstUsuario = daoUsuario.obtenerTodosLosUsuarios();

            // Coloca la lista de usuario en variables de sesión para que sea
            // recuperado luego en la página listarUsuarios.jsp
            request.setAttribute("lstUsuario", lstUsuario);

            // Invoca la página listarUsuarios.jsp
            RequestDispatcher vista = request.getRequestDispatcher("/listarUsuarios.jsp");
            vista.forward(request, response);

            // Otra forma de invocar la página
//                request.getRequestDispatcher("/listarUsuarios.jsp").forward(request, response);
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
    
    public static void borrarUsuario(int idUsuario, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DAOAdministrador daoUsuario = new DAOAdministrador();
            
            daoUsuario.borrar(idUsuario);
            List<Usuario> lstUsuario = daoUsuario.obtenerTodosLosUsuarios();
            // Coloca la listaUsuario en variables del objeto request para que ésta pueda ser
            // accedida en la página listarUsuarios.jsp
            request.setAttribute("lstUsuario", lstUsuario);
            // Invoca la página de listar usuario
            RequestDispatcher vista = request.getRequestDispatcher("/listarUsuarios.jsp");
            vista.forward(request, response);
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
    
    public static void mostrarPaginaCreacionUsuario(HttpServletRequest request, HttpServletResponse response, String registreseOcrear) throws ServletException, IOException {
        try {
            DAOPais daoPais = new DAOPais();
            List<Pais> listaPais = daoPais.obtenerTodosLosPaises();
            // Coloca la listaPais en variables del objeto request para que ésta pueda ser
            // accedida en la página registrese.jsp
            request.setAttribute("listaPais", listaPais);
            
            DAOIdioma daoIdioma = new DAOIdioma();
            List<Idioma> listaIdioma = daoIdioma.obtenerTodosLosIdiomas();
            // Coloca la listaIdioma en variables de del objeto request para que ésta pueda ser
            // accedida en la página registrese.jsp
            request.setAttribute("listaIdioma", listaIdioma);

            // Invoca la página crearUsuario.jsp
//            RequestDispatcher vista = request.getRequestDispatcher("crearUsuario.jsp?registreseOcrear=" + registreseOcrear);
//            vista.forward(request, response);


            // Otra forma de invocar la página crearUsuario es así:
            request.getRequestDispatcher("crearUsuario.jsp?registreseOcrear=" + registreseOcrear).forward(request, response);
        } catch (Exception ex) {
            // Captura el mensaje de error
            String mensage = ex.getMessage();

            // Captura la traza pila
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            String trazaPila = errors.toString();
            
            request.setAttribute("mensage", mensage);
            request.setAttribute("trazaPila", trazaPila);
            
            // Se invoca la página error.jsp
            RequestDispatcher vista = request.getRequestDispatcher("/error.jsp");
            vista.forward(request, response);
        }
    }
    
    public static void mostrarPaginaEditarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            DAOPais daoPais = new DAOPais();
            List<Pais> listaPais = daoPais.obtenerTodosLosPaises();
            // Coloca la listaPais en las variables del objeto request para que ésta pueda ser
            // accedida en la página editarUsuario.jsp
            request.setAttribute("listaPais", listaPais);
            
            DAOIdioma daoIdioma = new DAOIdioma();
            List<Idioma> listaIdioma = daoIdioma.obtenerTodosLosIdiomas();
            // Coloca la listaIdioma en las variables del objeto request para que pueda ser
            // accedida en la página editarUsuario.jsp
            request.setAttribute("listaIdioma", listaIdioma);
            
            int idUsuario = Integer.parseInt((String) request.getParameter("IdUsuario"));
            DAOUsuario daoUsuario = new DAOUsuario();
            Usuario u = daoUsuario.obtenerUsuarioPorId(idUsuario);
            request.setAttribute("usuario", u);
            
            DAOUsuarioIdioma daoUsuarioIdioma = new DAOUsuarioIdioma();
            
            List<UsuarioIdioma> listaUsuarioIdioma = new LinkedList<>();
            
            listaUsuarioIdioma = daoUsuarioIdioma.obtenerTodosIdiomasDeUnUsuario(idUsuario);
            request.setAttribute("listaUsuarioIdioma", listaUsuarioIdioma);
            
            // Se invoca la página editarUsuario.jsp
            RequestDispatcher vista = request.getRequestDispatcher("editarUsuario.jsp");
            vista.forward(request, response);
            
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
