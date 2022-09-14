
/* Accede a la referencia (handle) para los cuadros de texto */
var nombreUsuario = document.getElementById("nombreUsuario");
var password = document.getElementById("password");
var repitaPassword = document.getElementById("repitaPassword");
var email = document.getElementById("email");

/* Accede a la referencia para los labels que tienen asterisco */
var nombreAst = document.getElementById("nombreAst");
var passwordAst = document.getElementById("passwordAst");
var repitaPasswordAst = document.getElementById("repitaPasswordAst");
var emailAst = document.getElementById("emailAst");

// Si los cuadros de texto tienen algún valor, borra los asteriscos
if (email.value != "") {
    // No muestra el asterisko
    emailAst.style.display = "none";
}

if (nombreUsuario.value != "") {
    // Esconde el nombre de usuario
    nombreAst.style.display = "none";
}
if (password.value != "") {
    passwordAst.style.display = "none";
}
if (repitaPassword.value != "") {
    repitaPasswordAst.style.display = "none";
}

function validarCampos() {
    /* Esta función se asocia con el evento click del botón submitir, botonEnviar, del formulario
     * y retorna verdadero si todos los campos son válidos; falso, en caso contrario
     * @type {Boolean}
     */
    var camposValidos = true;

    if (email.value === "") {
        camposValidos = false;
        alert("Email es requerido");
    }

    if (nombreUsuario.value === "") {
        camposValidos = false;
        alert("Nombre es requerido");
    }
    if (password.value === "") {
        camposValidos = false;
        alert("Password es requerido");
    }
    if (repitaPassword.value === "") {
        camposValidos = false;
        alert("Repita passoword es requerido");
    }

    if (password.value != repitaPassword.value) {
        camposValidos = false;
        alert("Los campos password y repita password no coinciden");
    }

    return camposValidos;
}


/* Asocia la función validarCampos con el evento click del botón enviar */
document.getElementById("botonEnviar").onclick = validarCampos;

// Las siguientes son funciones anónimas, no tienen identifiador, y se están asignando a 
// eventos de los elementos de la pagina web


// Se asignan funciones anónimas a eventos de cuadros de texto
email.onfocus = function () {
    // Borra el asterisco del email cuando el campo email obtiene el focus
    emailAst.style.display = "none";
};

email.onblur = function () {
    if (email.value === "") {
        // Muestra el asterisco del email cuando se pierde el foco de este campo y  esta vacío
        emailAst.style.display = "inline";
    }
};


nombreUsuario.onfocus = function () {
    // Borra el asterisco del nombre del usuario cuando este campo obtiene el focus
    nombreAst.style.display = "none";
};

nombreUsuario.onblur = function () {
    if (nombreUsuario.value === "") {
        // Muestra el asterisco del nombre del usuario cuando se pierde el foco de este campo y  esta vacío
        nombreAst.style.display = "inline";
    }
};
password.onfocus = function () {
    // Borra el asterisco del password cuando este campo obtiene el focus
    passwordAst.style.display = "none";
};
password.onblur = function () {
    if (password.value === "") {
        // Muestra el asterisco del password cuando se pierde el foco de este campo y  esta vacío
        passwordAst.style.display = "inline";
    }
};

repitaPassword.onfocus = function () {
    // Borra el asterisco del repita password cuando este campo obtiene el focus
    repitaPasswordAst.style.display = "none";
};
repitaPassword.onblur = function () {
    if (repitaPassword.value === "") {
        // Muestra el asterisco del repita password cuando se pierde el foco de este campo y  esta vacío
        repitaPasswordAst.style.display = "inline";
    }
};

function mostrarPassword() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}
function mostrarRepitaPassword() {
    var x = document.getElementById("repitaPassword");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}








