<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Confirmación</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="misEstilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header class="col-12 col-s-12">
            <%@include file="loginLogout.jsp" %>  
        </header>
        <div class="row">
            <%@include file="navegacion.jsp" %>            
            <main>
                <img src="beach.jpg" alt="" class="col-3 col-s-5"/>
                <div class="col-3 col-s-5" >
                    <h2>Disfrute la Naturaleza</h2>
                    <p>Hotel Playa Blanca queda situado en una hermosa playa de aguas cristalinas.</p>

                    <p>Disfrute las siguientes actividades:</p>
                    <ul>
                        <li>Piscina climatizada</li>
                        <li>Caminata guiada a las montañas</li>
                        <li>Moto acuática</li>
                    </ul>   
                </div>
                <aside class="col-3 col-s-12">
                    <p><b>¿Qué?</b></p>
                    <p>Hotel Playa Blanca queda ubicado en la playa Taganga </p>
                    <p><b>¿Dónde?</b></p>
                    <p>Taganga es una playa del oceano Atlantico ubicada a 40 minutos de Santa Marta, Colombia</p>
                    <p><b>¿Cómo?</b></p>
                    <p>Puedes viajar en avión hasta Santa Marta y luego tomas un carro</p>
                </aside>
            </main>
        </div>
        <footer>
            <p>Todos los derechos reservados &copy; 2020</p>
        </footer>
    </body>
</html>
