<nav class="col-3 col-s-2">
    <ul>
        <a href="index.jsp"> <li>Inicio</li></a>
        <!-- Si el usuario logueado es el administrador muestra el hipervínculo maestros -->
        <%
            if (session.getAttribute("tipoUsuario") != null) {
                // Esta navegando un usuario registrado o el administrador

                if (((String) session.getAttribute("tipoUsuario")).equalsIgnoreCase("admin")) {
                    out.print("<a  href='maestros.jsp'><li>Maestros</li></a>");
                }
            }
        %>      
        <a href="actividades.jsp"><li>Actividades</li></a>
        <!-- Si el usuario logueado es el administrador o un usuario, muestra el hipervínculo maestros -->
        <%
            if (session.getAttribute("tipoUsuario") != null) {
            // Esta navegando un usuario registrado o el administrador
                if (((String) session.getAttribute("tipoUsuario")).equalsIgnoreCase("usuario")
                        || ((String) session.getAttribute("tipoUsuario")).equalsIgnoreCase("admin")) {
                    // Esta navegando un usuario registrado o el administrador
                    out.print("<a  href='reservaciones.jsp'><li>Reservaciones</li></a>");
                }
            }
        %> 
        <a href="ejemplosJavaScript.jsp"><li>Ejemplos Javascript</li> </a> 
    </ul>
</nav> 