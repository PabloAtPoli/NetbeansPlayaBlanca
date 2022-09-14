
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tablas Maestros</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="misEstilos.css" rel="stylesheet" type="text/css"/>
        <style>
            table{
                /* centra la tabla de login */
                margin: auto;
            }
            td {
                padding:5px;
            }
        </style>
    </head>
    <body>
        <%
            // No permite acceder a la página si la sesión del usuario expiró
            if (session.getAttribute("tipoUsuario") == null) {
                response.sendRedirect("login.jsp");
            }
        %> 
        <header class="col-12 col-s-12">
            <%@include file="loginLogout.jsp" %>  
        </header>

        <div class="row">
            <%@include file="navegacion.jsp" %> 
            <main class="col-6 col-s-10">       
                <h1 style="text-align: center">Tablas Maestras</h1>
                <nav>
                    <ul style="text-align: center">                      
                        <a style="display:inline-block" href="ControladorUsuario?opcion=listarUsuarios"><li>Usuario</li></a>
                        <a style="display:inline-block" href="ControladorPais?opcion=listarPaises"><li>Pais</li></a>
                        <a style="display:inline-block" href=""> <li>Idioma</li></a>
                    </ul>
                </nav>
            </main>
        </div>
        <footer>
            <p>Todos los derechos reservados &copy; 2020</p>
        </footer>
    </body>
</html>
