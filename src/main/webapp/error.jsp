<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
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
        <header class="col-12 col-s-12">
            <%@include  file="loginLogout.jsp" %>
        </header>
        <div class="row">
            <%@include file="navegacion.jsp" %> 
            <main class="col-6 col-s-10">       
                <h1 style="text-align: center">Uppps ocurrió una excepción en la aplicación</h1>
                <h1 style="text-align: center">Estamos trabajando en la solución</h1>
                <%                    // Obtiene el mensaje de error y lo escribe en la pagina error.jsp
                    String mensage = (String) request.getAttribute("mensage");
                    if (mensage != null) {
                        out.print("El mensaje de error es: ");
                        out.print(mensage + "<br/>");
                    }

                    // Obtiene la traza de la pila y la escribe en la pagina
                    String trazaPila = (String) request.getAttribute("trazaPila");
                    if (trazaPila != null) {
                        out.print("La traza de la pila es: " + "<br/>");
                        out.print(trazaPila);
                    }
                %>

            </main>
        </div>
        <footer>
            <p>Todos los derechos reservados &copy; 2020</p>
        </footer>
    </body>
</html>

