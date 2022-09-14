
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
                <h1 style="text-align: center">Login</h1>
                <form action="ControladorLogin" method="post">
                    <table>
                        <tr>
                            <td>
                                <label for="email">Email</label>
                            </td>
                            <td>
                                <input type="text" name="email" id="email" value="" required />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="pass">Password</label>
                            </td>
                            <td>
                                <input type="password" name="pass" id ="pass" value="" required />
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center" colspan="2">
                                <input type="submit" value="Enviar" name="enviar" />
                                <input type="reset" value="Limpiar" name="limpiar" />  
                            </td>
                        </tr>
                    </table>
                    <%//                        
                        // Si el nombre de usuario o password es invalido muestra el siguiente mensaje
                        String usuarioInvalido = (String) request.getAttribute("usuarioInvalido");
                        if (usuarioInvalido != null) {
                            out.print("<br/>");
                            out.print("<center>");
                            out.print("<span style='color:red'>Email o password incorrectos</span>");
                            out.print("</center>");
                        }
                    %>
                </form>
            </main>
        </div>
        <footer>
            <p>Todos los derechos reservados &copy; 2020</p>
        </footer>
    </body>
</html>
