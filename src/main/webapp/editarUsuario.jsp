
<%@page import="modelo.POJO.UsuarioIdioma"%>
<%@page import="modelo.POJO.Usuario"%>
<%@page import="modelo.POJO.Idioma"%>
<%@page import="modelo.POJO.Pais"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Proyecto Hotel Editar Usuario</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="misEstilos.css" rel="stylesheet" type="text/css"/>
        <style>
            table{
                margin:auto /* Centra la tabla */
            }

            #emailAst, #nombreAst, #passwordAst, #repitaPasswordAst {
                color:red;
            }

            /* Rellena con 5 pixels arriba, abajo, izquierda y derecha
               de cada celda de la tabla */
            td {
                padding:5px; 
            }
        </style>
        <script defer src="scriptCrearUsuario.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="col-12 col-s-12">
            <%@include file="loginLogout.jsp" %>  
        </header>
        <div class="row">
            <%@include file="navegacion.jsp" %> 
            <main class="col-6 col-s-10">
                <center><h1>Editar Usuario</h1></center>

                <%  // Obtiene el usuario que se va modificar de las variables de request
                    Usuario u = (Usuario) request.getAttribute("usuario");
                %>
                <form method="post" name="frmRegistrese" id = "frmRegistrese" action="ControladorUsuario">
                    <!-- En este campo oculto se pasa el parámetro operacion=editar al ControladorUsuario -->
                    <input type="hidden" name="operacion" id="operacion" value="editar" >  
                    <table>
                        <tr>
                            <td><label for="idUsuario">Id Usuario </label></td>
                            <td>                           
                                <%
                                    out.println("<input type='text' name='idUsuario' id='idUsuario' readonly value='"
                                            + u.getIdUsuario() + "'/><br>");
                                %>  </td>
                        </tr>
                        <tr>
                            <td><label for="email" >Email</label></td>
                            <td> <% out.println("<input type='text' name='email' id='email' value='" + u.getEmail() + "'/>"); %>   </td>
                        <span id="emailAst">*</span>
                        </tr>
                        <tr>
                            <td><label for="nombreUsuario" >Nombre Usuario </label></td>
                                <td><% out.println("<input type='text' name='nombreUsuario' id='nombreUsuario' value='"
                                            + u.getNombre() + "'/>"); %></td>
                        <span id="nombreAst">*</span>
                        </tr>
                        <tr>
                            <td><label for="password">Password </label></td>
                                <td><% out.println("<input type='password' name='password'"
                                        + " id='password' value='" + u.getPassword() + "'>"); %></td>
                        <span id="passwordAst">*</span>
                        </tr>
                        <tr>
                            <td><label for="repitaPassword">Repita Password</label></td>
                                <td> <% out.println("<input  type='password' name='repitaPassword'"
                                            + " id='repitaPassword' required value='" + "'>"); %></td>
                        <span id="repitaPasswordAst">*</span>
                        </tr>
                        <tr>
                            <td> <label for="genero">Género</label> </td>
                            <td>                 
                                <% if (u.getGenero().equalsIgnoreCase("Masculino")) { %>
                                <input type="radio" name="genero" value="Masculino" 
                                       checked="checked"> Masculino
                                <% } else { %>
                                <input type="radio" name="genero" value="Masculino"> Masculino
                                <% } %>

                                <% if (u.getGenero().equalsIgnoreCase("Femenino")) { %>
                                <input type="radio" name="genero" value="Femenino"
                                       checked="checked"> Femenino <br/>
                                <% } else { %>
                                <input type="radio" name="genero" value="Femenino"> Femenino <br/>
                                <% } %> 
                            </td>

                        </tr>
                        <tr>
                            <td>  <label for="Hobbies">Hobbies</label> </td>
                            <td>                            
                                <% if (u.isCine()) { %>
                                <input type="checkbox" name="Hobbies" value="cine" checked=""> Cine
                                <% } else { %>
                                <input type="checkbox" name="Hobbies" value="cine"> Cine
                                <% } %>

                                <% if (u.isMusica()) { %>
                                <input type="checkbox" name="Hobbies" value="musica" checked="checked"> Música
                                <% } else { %>
                                <input type="checkbox" name="Hobbies" value="musica"> Música
                                <% } %>


                                <% if (u.isDeportes()) { %>
                                <input type="checkbox" name="Hobbies" value="deportes" checked=""> Deportes <br/>
                                <% } else { %>
                                <input type="checkbox" name="Hobbies" value="deportes"> Deportes <br/>
                                <% } %>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="pais">País</label> </td>
                            <td>                            
                                <select name="pais" required>
                                    <%
                                        // Se recupera de la variable de request la listaPais
                                        List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
                                        for (Pais pais : listaPais) {
                                            if (pais.getIdPais() == u.getIdPais()) {
                                                out.println("<option value='" + pais.getIdPais()
                                                        + "' selected>" + pais.getNombre() + "</option>");
                                            } else {
                                                out.println("<option value='" + pais.getIdPais()
                                                        + "' >" + pais.getNombre() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </td> 
                        </tr>
                        <tr>
                            <td> <label for="idiomas" >Idiomas</label> </td>
                            <td>                            
                                <div id="listaIdiomas">
                                    <%
                                        // Se recupera de la variable de request la listaIdioma
                                        List<Idioma> listaIdioma
                                                = (List<Idioma>) request.getAttribute("listaIdioma");

                                        List<UsuarioIdioma> listaUsuarioIdioma
                                                = (List<UsuarioIdioma>) request.getAttribute("listaUsuarioIdioma");
                                        for (Idioma idioma : listaIdioma) {
                                            // Chequea si el idioma de la lista idioma esta en la lista usuarioIdioma
                                            boolean usuarioSeleccionoIdioma = false;

                                            for (UsuarioIdioma usuarioIdioma : listaUsuarioIdioma) {
                                                if (usuarioIdioma.getIdIdioma() == idioma.getIdIdioma()) {
                                                    usuarioSeleccionoIdioma = true;
                                                }
                                            }
                                            if (usuarioSeleccionoIdioma) {
                                                out.println("<input type='checkbox' checked name='idiomas' value='" + idioma.getIdIdioma() + "' >" + idioma.getNombre() + "<br/>");
                                            } else {
                                                out.println("<input type='checkbox' name='idiomas' value='" + idioma.getIdIdioma() + "' >" + idioma.getNombre() + "<br/>");
                                            }
                                        }

                                    %>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="text-align: center">
                                <input type="submit" value="Enviar" id="botonEnviar"/>
                                <input type="reset" value="Limpiar Campos"/>
                            </td>
                        </tr>
                    </table>
                </form> 
            </main>
            <aside class="col-3 col-s-12">
                <p><b>¿Qué?</b></p>
                <p>Hotel Playa Blanca queda ubicado en la playa Taganga </p>
                <p><b>¿Dónde?</b></p>
                <p>Taganga es una playa del oceano Atlantico ubicada a 40 minutos de Santa Marta, Colombia</p>
                <p><b>¿Cómo?</b></p>
                <p>Puedes viajar en avión hasta Santa Marta y luego tomas un carro</p>
            </aside>
        </div>
        <footer>
            <p>Todos los derechos reservados &copy; 2020</p>
        </footer>
    </body>
</html>
