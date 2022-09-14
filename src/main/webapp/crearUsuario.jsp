<%@page import="modelo.POJO.Idioma"%>
<%@page import="modelo.POJO.Pais"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Regístrese</title>
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
        <!-- Difiere el procesamiento de las instrucciones de Javascript para cuando se
          se cargue toda la página; es decir, se haya creado el DOM -->
        <script defer src="scriptCrearUsuario.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="col-12 col-s-12">
            <%@include file="loginLogout.jsp" %>  
        </header>

        <div class="row">
            <%@include file="navegacion.jsp" %> 
            <main class="col-6 col-s-10">
                <%//
                    // Se recupera el parametro que dice si esta es la página de crear usuario o registrarse
                    String registreseOcrear = (String) request.getParameter("registreseOcrear");
                    if (registreseOcrear.equals("registrese")) {
                        out.println("<h1 style='text-align: center'>Regístrese</h1>");
                    }

                    if (registreseOcrear.equals("crearUsuario")) {
                        out.println("<h1 style='text-align: center'>Crear Usuario</h1>");
                    }
                    out.println("<form  method = 'post'  id = 'frmRegistrese' action ='ControladorUsuario?registreseOcrear=" + registreseOcrear + "'>");
                %>

                <input type="hidden" name="operacion" id="operacion" value="crear" >  
                <label for="idUsuario" style="display: none;">Id Usuario </label>
                <input type="hidden" name="idUsuario" id="idUsuario" value="0" >
                <table>
                    <tr>
                        <td>
                            <label for="email" >Email</label>
                        </td>
                        <td>
                            <input type="email" name="email" id="email" required >
                            <span id="emailAst">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="nombreUsuario" >Nombre y Apellido </label>
                        </td>
                        <td>
                            <input type="text" name="nombreUsuario" id="nombreUsuario" required>
                            <span id="nombreAst">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="password">Password </label>
                        </td>
                        <td>
                            <input type="password" name="password" id="password" required>
                            <span id="passwordAst">*</span>
                            <!-- se asocia el evento mostrarPassword de Javascript con el evento click del checkbox -->
                            <input type="checkbox" onclick="mostrarPassword()">Mostrar Password
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="repitaPassword">Repita Password</label>
                        </td>
                        <td>
                            <input  type="password" name="repitaPassword" id="repitaPassword" required>
                            <span id="repitaPasswordAst">*</span>
                            <!-- se asocia el evento mostrarRepitaPassword de Javascript con el evento click del checkbox -->
                            <input type="checkbox" onclick="mostrarRepitaPassword()">Mostrar Password
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="genero">Género</label>
                        </td>
                        <td>
                            <input type="radio" name="genero" value="Masculino" checked> Masculino
                            <input type="radio" name="genero" value="Femenino"> Femenino
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="Hobbies">Hobbies</label>
                        </td>
                        <td>
                            <input type="checkbox" name="Hobbies" value="cine"> Cine
                            <input type="checkbox" name="Hobbies" value="musica"> Música
                            <input type="checkbox" name="Hobbies" value="deportes"> Deportes
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="pais">País</label>
                        </td>
                        <td>
                            <select name="pais" required>
                                <%
                                    // Se recupera la variable del objeto request listaPais
                                    List< Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
                                    // Genera el código HTML para la lista desplegable país
                                    for (Pais pais : listaPais) {
                                        out.println("<option value='" + pais.getIdPais() + "' >"
                                                + pais.getNombre() + "</option>");
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="idiomas" >Idiomas</label>
                        </td>
                        <td>
                            <div id="listaIdiomas">
                                <%
                                    // Se recupera de la variable del objeto request la listaIdioma
                                    List<Idioma> listaIdioma = (List<Idioma>) request.getAttribute("listaIdioma");
                                    // Genera el código HTML para la lista de idiomas
                                    for (Idioma idioma : listaIdioma) {
                                        out.println("<input type='checkbox' name='idiomas' value='" + idioma.getIdIdioma() + "' >" + idioma.getNombre() + "<br/>");
                                    }
                                %>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <input type="submit" value="Enviar" id="botonEnviar"/>
                            <input type="reset" value="Limpiar Campos"/>
                        </td>   
                    </tr>
                </table>
                </form> 

            </main>
        </div>
        <footer>
            <p>Todos los derechos reservados &copy; 2020</p>
        </footer>
    </body>
</html>
