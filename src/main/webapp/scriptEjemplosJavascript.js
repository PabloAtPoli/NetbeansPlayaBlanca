// Combo box en cascada
// 
// Asigna el apuntador al combo box con identicador country
var countryHandle = document.getElementById("country");

// Función anónima que se ejecuta cuando el usuario selecciona un país
countryHandle.onchange = function () {
    // cities es un arreglo (vector) asociativo, aquel cuyos índices son cadenas en vez de enteros.
    var cities = {
        'Germany': ['Duesseldorf', 'Leinfelden-Echterdingen', 'Eschborn'],
        'Spain': ['Barcelona', 'Madrid'],
        'Hungary': ['Pecs'],
        'USA': ['Downers Grove', 'New York', 'Washington', 'Boston'],
        'Mexico': ['Puebla', 'Ciudad de Mexico'],
        'South Africa': ['Midrand'],
        'China': ['Beijing'],
        'Russia': ['St. Petersburg', 'Moscow']
    };

// Asigna el apuntador a el combox city (el que contiene el nombre de las ciudades)
    var cityHandle = document.getElementById("city");

    // Asigna a la cadena strCountry el nombre (cadena) del país seleccionado
    var strCountry = countryHandle.options[countryHandle.selectedIndex].text;

    // Asigna al vector de cadenas strCities las ciudades asociadas con la cadena strCountry
    var strCities = cities[strCountry];

    cityHandle.innerHTML = "";

// Itera sobre el vector de cadenas de ciudades, es decir, pasa por cada entrada del vector strCities
    for (var i = 0; i < strCities.length; i++) {
        var el = document.createElement("option");
        el.textContent = strCities[i];
        el.value = strCities[i];
        // Agrega una ciudad al combo box de ciudades
        cityHandle.appendChild(el);
    }
};

// Calculadora sencilla

function multiplicar() {
    // Asigna lo que el usuario entra en los cuadros de texto num1 y num 2 a las variables num1 y num 2
    var num1 = document.getElementById('num1').value;
    var num2 = document.getElementById('num2').value;

    // Calcula la multiplicación
    var r = num1 * num2;
    
    // Almacena la multiplicación en el cuadro de texto resultado
    document.getElementById('resultado').value = r.toFixed(2);
}

function restar() {
    var num1 = document.getElementById('num1').value;
    var num2 = document.getElementById('num2').value;

    var r = num1 - num2;

    document.getElementById('resultado').value = r.toFixed(2);
}

function dividir() {
    var num1 = document.getElementById('num1').value;
    var num2 = document.getElementById('num2').value;

    var r = num1 / num2;
    document.getElementById('resultado').value = r.toFixed(2);
}

function limpiar() {
    document.getElementById('num1').value = "";
    document.getElementById('num2').value = "";

    document.getElementById('resultado').value = "";
}

// En vez de asociar la función sumar al evento onclick del botón sumar en la página HTML,
// también se puede asignar una función anónima al evento onclick del botón suma, que realiza la suma de los dos números digitados
document.getElementById('btnSuma').onclick = function () {
    var num1 = document.getElementById('num1').value;
    var num2 = document.getElementById('num2').value;

    var r = parseFloat(num1) + parseFloat(num2);
    document.getElementById('resultado').value = r.toFixed(2);
};




