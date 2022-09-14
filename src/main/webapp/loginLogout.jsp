<h1>
    <span>Hotel Playa Blanca</span>

    <span id="loginLogoutRegistrese">
        <%
            // Recupera el tipo de usuario de las variables de session
            String tipoUsuario = (String) session.getAttribute("tipoUsuario");

            if (tipoUsuario == null) {
                // El usuario que está navegando es anónimo
                out.print("<a  href='login.jsp'>Login</a>");
                // Se invoca el método get del ControladorLogin con el parámetro registrese
                out.print("      <a  href='ControladorLogin?registreseOcrear=registrese'>Regístrese</a>");

            } else {
                // El usuario que está navegando está registrado o es administrador
                String nombreUsuario = (String) session.getAttribute("nombreUsuario");
                out.print("Hola " + nombreUsuario + "      <a  href='ControladorLogin?registreseOcrear=logout'>Logout</a>");
            }
        %>    
    </span>  
</h1>
