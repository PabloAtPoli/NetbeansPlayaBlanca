
<%@page import="java.util.List"%>
<%@page import="modelo.POJO.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Listar Usuarios</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="misEstilos.css" rel="stylesheet" type="text/css"/>
        <style>
            table{
                margin: auto;
            }
            td {
                padding:5px;
            }
            tbody tr:nth-child(odd){
                background-color: #33b5e5;
                color: #ffffff;
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
                <h1 style="text-align: center">Lista de Usuarios</h1>

                <center> <a id="btnAgregar" href="ControladorUsuario?opcion=crearUsuario"><input type="button" value="Agregar" name="btnAgregar" /></a></center>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Email</th>
                            <th>Nombre</th>
                            <th>Genero</th>
                            <th>Password</th>
                            <th>País</th>
                            <th colspan="2">Operación</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  //
                            // Obtiene la lista de usuario de las variable de request
                            List<Usuario> lstUsuario = (List<Usuario>) request.getAttribute("lstUsuario");
                            for (Usuario u : lstUsuario) {
                                out.print("<tr>");
                                out.print("<td>" + u.getIdUsuario() + "</td>");
                                out.print("<td>" + u.getEmail() + "</td>");
                                out.print("<td>" + u.getNombre() + "</td>");
                                out.print("<td>" + u.getGenero() + "</td>");
                                out.print("<td>" + u.getPassword() + "</td>");
                                out.print("<td>" + u.getNombrePais() + "</td>");
                                out.print("<td><a href='ControladorUsuario?opcion=borrar&IdUsuario="
                                        + u.getIdUsuario() + "'><input type='button' value='Borrar' name='borrar' /><a/></td>");
                                out.print("<td><a href='ControladorUsuario?opcion=editar&IdUsuario="
                                        + u.getIdUsuario() + "'><input type='button' value='Editar' name='editar' /><a/></td>");
                                out.print("</tr>");
                            }
                        %>
                    </tbody>
                </table>
            </main>
        </div>
        <footer>
            <p>Todos los derechos reservados &copy; 2020</p>
        </footer>
    </body>
</html>
