var user = null;
var password = null;
var client = Stomp.client("ws://localhost:61614"); // configurar!!!
const minLuzPermisible = 450
const maxLuzPermisible = 750
const minTempPermisible = 15
const maxTempPermisible = 35
const envioMensajesTiempo = 5
const cambiosTiempo = 4
const accionAleatoriaTiempo = 20


client.connect(user, password, onconnect, onerror);

function onconnect() {
    console.log("Se ha conectado")
}

function onerror() {
    console.log("Ha ocurrido un error")

}
$(document).ready(function valoresAleatoriosSubirBajar() {
    var accion = Math.floor(Math.random() * 5) + 0
    var accion2 = Math.floor(Math.random() * 5) + 0
    if (accion > 2) {
        document.getElementById("inputLuzOficina1Accion").value = "subir"
    } else {
        document.getElementById("inputLuzOficina1Accion").value = "bajar"
    }
    if (accion > 2) {
        document.getElementById("inputTempOficina1Accion").value = "subir"
    } else {
        document.getElementById("inputTempOficina1Accion").value = "bajar"
    }
    setTimeout(valoresAleatoriosSubirBajar, accionAleatoriaTiempo * 1000);
})

//<!-- OFICINA 1 -->
//<!-- ACTIVADOR LUZ OFICINA 1 -->

$(document).ready(function subscribeMessageQueueLuz() {
    client.subscribe('/topic/activador.activadorLuz1', processMessageLuz1);
})

function processMessageLuz1(message) {
    var bodyLuz1 = message.body
    var messageBodyLuz1 = JSON.parse(bodyLuz1);
    console.log(messageBodyLuz1.action)

    if(messageBodyLuz1.action == "bajar"){
        document.getElementById("inputLuzOficina1Accion").value = "bajar"
        $('#dialog_title_span_luz_1').text('Regulando luces: ');
        $('#flechaAbajoLuz1').css("visibility", "visible");
        $('#flechaArribaLuz1').css("visibility", "hidden")

    }else if(messageBodyLuz1.action == "subir"){
        document.getElementById("inputLuzOficina1Accion").value = "subir"
        $('#dialog_title_span_luz_1').text('Regulando luces:');
        $('#flechaArribaLuz1').css("visibility", "visible");
        $('#flechaAbajoLuz1').css("visibility", "hidden")
    }else{
        $('#dialog_title_span_luz_1').text("");
        $('#flechaArribaLuz1').css("visibility", "hidden")
        $('#flechaAbajoLuz1').css("visibility", "hidden")
    }
}

//<!-- ACTIVADOR TEMPERATURA OFICINA 1 -->

$(document).ready(function subscribeMessageQueueTemp() {
    client.subscribe('/topic/activador.activadorTemp1' , processMessageTemp1);
})

function processMessageTemp1(message) {
    var body = message.body
    var messageBody = JSON.parse(body);

    if (messageBody.action == "subir" ) {
        document.getElementById("inputTempOficina1Accion").value = "subir"
        $('#dialog_title_span_temp_1').text('Regulando temperatura:');
        $('#flechaArribaTemp1').css("visibility", "visible");

    } else if (messageBody.action == "bajar" ) {
        document.getElementById("inputTempOficina1Accion").value = "bajar"
        $('#dialog_title_span_temp_1').text('Regulando temperatura: ');
        $('#flechaAbajoTemp1').css("visibility", "visible");

    } else {
        $('#dialog_title_span_temp_1').text("");
        $('#flechaArribaTemp1').css("visibility", "hidden")
        $('#flechaAbajoTemp1').css("visibility", "hidden")
    }
}
//<!-- SENSOR LUZ OFICINA 1 -->
$(document).ready(function simuladorLuz1() {
    var actualLuz1 = document.getElementById("inputLuzOficina1").value
    var accionLuz1 = document.getElementById("inputLuzOficina1Accion").value
    if (accionLuz1 == "subir") {
        for (i = 0; i < Math.floor(Math.random() * 80) + 20; i++) {
            actualLuz1++
            if (actualLuz1 >= 1000) {
                actualLuz1 = 1000
            }

        }
    } else {
        for (i = 0; i < Math.floor(Math.random() * 80) + 20; i++) {
            actualLuz1--
            if (actualLuz1 <= 200) {
                actualLuz1 = 200
            }
        }
    }


    porcentajeLuz1 = (actualLuz1 - 200) * 0.125


    $("#luzOficina1")
        .removeClass("progress-bar-danger")
        .addClass("progress-bar-info")
        .css("width", porcentajeLuz1 + "%")
        .attr("aria-valuenow", actualLuz1)
        .text(actualLuz1);
    if (actualLuz1 <= minLuzPermisible ) {
        $("#luzOficina1")
            .removeClass("progress-bar-info")
            .addClass("progress-bar-danger")
            .css("width", porcentajeLuz1 + "%")
            .attr("aria-valuenow", actualLuz1)
            .text(actualLuz1);
    } else if (actualLuz1 >= maxLuzPermisible ) {
        $("#luzOficina1")
            .removeClass("progress-bar-info")
            .addClass("progress-bar-danger")
            .css("width", porcentajeLuz1 + "%")
            .attr("aria-valuenow", actualLuz1)
            .text(actualLuz1);
    }


    document.getElementById("inputLuzOficina1").value = actualLuz1
    setTimeout(simuladorLuz1, cambiosTiempo * 1000);

})
//<!-- Simaldor TEMPERATURA OFICINA 1 -->

$(document).ready(function simuladorTemp1() {
    var actualTemp1 = document.getElementById("inputTempOficina1").value
    var accionTemp1 = document.getElementById("inputTempOficina1Accion").value

    if (accionTemp1 == "subir") {
        for (i = 0; i < Math.floor(Math.random() * 5) + 2; i++) {
            actualTemp1++
            if (actualTemp1 >= 50) {
                actualTemp1 = 50
            }

        }
    } else {
        for (i = 0; i < Math.floor(Math.random() * 5) + 2; i++) {
            actualTemp1--
            if (actualTemp1 <= 0) {
                actualTemp1 = 0
            }
        }
    }


    porcentajeTemp1 = (actualTemp1) * 2


    $("#tempOficina1")
        .removeClass("progress-bar-danger")
        .addClass("progress-bar-success")
        .css("width", porcentajeTemp1 + "%")
        .attr("aria-valuenow", actualTemp1)
        .text(actualTemp1);
    if (actualTemp1 <= minTempPermisible) {
        $("#tempOficina1")
            .removeClass("progress-bar-success")
            .addClass("progress-bar-danger")
            .css("width", porcentajeTemp1 + "%")
            .attr("aria-valuenow", actualTemp1)
            .text(actualTemp1);
    } else if (actualTemp1 >= maxTempPermisible) {
        $("#tempOficina1")
            .removeClass("progress-bar-success")
            .addClass("progress-bar-danger")
            .css("width", porcentajeTemp1 + "%")
            .attr("aria-valuenow", actualTemp1)
            .text(actualTemp1);
    }


    document.getElementById("inputTempOficina1").value = actualTemp1
    setTimeout(simuladorTemp1,cambiosTiempo * 1000);

})


$(document).ready(function enviarDatosLuz1() {
    consola("activadorLuz1", document.getElementById("inputLuzOficina1").value )
    setTimeout(enviarDatosLuz1,1 * 1000);
})
$(document).ready(function enviarDatosTemp1() {
    consola("activadorTemp1", document.getElementById("inputTempOficina1").value )
    setTimeout(enviarDatosTemp1,1 * 1000);
})