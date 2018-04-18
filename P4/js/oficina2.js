var user2 = null;
var password2 = null;
var client2 = Stomp.client("ws://localhost:61614"); // configurar!!!
const minLuzPermisible2 = 450
const maxLuzPermisible2 = 750
const minTempPermisible2 = 15
const maxTempPermisible2 = 35
const envioMensajesTiempo2 = 2
const cambiosTiempo2 = 1
const accionAleatoriaTiempo2 = 20


client2.connect(user2, password2, onconnect2, onerror2);

function onconnect2() {
    console.log("Se ha conectado")
}

function onerror2() {
    console.log("Ha ocurrido un error")
    setTimeout(function () {
        client2.connect(user2, password2, onconnect2, onerror2);
    }, 2000);

}
$(document).ready(function valoresAleatoriosSubirBajar2() {
    var accion = Math.floor(Math.random() * 5) + 0
    var accion2 = Math.floor(Math.random() * 5) + 0
    if (accion > 2) {
        document.getElementById("inputLuzOficina2Accion").value = "subir"
    } else {
        document.getElementById("inputLuzOficina2Accion").value = "bajar"
    }
    if (accion2 > 2) {
        document.getElementById("inputTempOficina2Accion").value = "subir"
    } else {
        document.getElementById("inputTempOficina2Accion").value = "bajar"
    }
    setTimeout(valoresAleatoriosSubirBajar2, accionAleatoriaTiempo2 * 1000);
})

//<!-- OFICINA 2 -->
//<!-- ACTIVADOR LUZ OFICINA 2 -->

$(window).on("load", function subscribeMessageQueueLuz() {

    setTimeout(function () {
        client2.subscribe('/topic/activador.activadorLuz2', processMessageLuz2);
    }, 5000);

})

function processMessageLuz2(message) {
    var bodyLuz2 = message.body
    var messageBodyLuz2 = JSON.parse(bodyLuz2);

    if (messageBodyLuz2.action == "bajar") {
        document.getElementById("inputLuzOficina2Accion").value = "bajar"
        $('#dialog_title_span_luz_2').text('Regulando luces: ');
        $('#flechaAbajoLuz2').css("visibility", "visible");
        $('#flechaArribaLuz2').css("visibility", "hidden")

    } else if (messageBodyLuz2.action == "subir") {
        document.getElementById("inputLuzOficina2Accion").value = "subir"
        $('#dialog_title_span_luz_2').text('Regulando luces:');
        $('#flechaArribaLuz2').css("visibility", "visible");
        $('#flechaAbajoLuz2').css("visibility", "hidden")
    } else {
        $('#dialog_title_span_luz_2').text("");
        $('#flechaArribaLuz2').css("visibility", "hidden")
        $('#flechaAbajoLuz2').css("visibility", "hidden")
    }
}

//<!-- ACTIVADOR TEMPERATURA OFICINA 2 -->

$(window).on("load", function subscribeMessageQueueTemp() {
    setTimeout(function () {
        client2.subscribe('/topic/activador.activadorTemp2', processMessageTemp2);
    }, 5000);
})

function processMessageTemp2(message) {
    var body = message.body
    var messageBody = JSON.parse(body);

    if (messageBody.action == "subir") {
        document.getElementById("inputTempOficina2Accion").value = "subir"
        $('#dialog_title_span_temp_2').text('Regulando temperatura:');
        $('#flechaArribaTemp2').css("visibility", "visible");

    } else if (messageBody.action == "bajar") {
        document.getElementById("inputTempOficina2Accion").value = "bajar"
        $('#dialog_title_span_temp_2').text('Regulando temperatura: ');
        $('#flechaAbajoTemp2').css("visibility", "visible");

    } else {
        $('#dialog_title_span_temp_2').text("");
        $('#flechaArribaTemp2').css("visibility", "hidden")
        $('#flechaAbajoTemp2').css("visibility", "hidden")
    }
}
//<!-- SENSOR LUZ OFICINA 2 -->
$(document).ready(function simuladorLuz2() {
    var actualLuz2 = document.getElementById("inputLuzOficina2").value
    var accionLuz2 = document.getElementById("inputLuzOficina2Accion").value
    if (accionLuz2 == "subir") {
        for (i = 0; i < Math.floor(Math.random() * 80) + 20; i++) {
            actualLuz2++
            if (actualLuz2 >= 1000) {
                actualLuz2 = 1000
            }

        }
    } else {
        for (i = 0; i < Math.floor(Math.random() * 80) + 20; i++) {
            actualLuz2--
            if (actualLuz2 <= 200) {
                actualLuz2 = 200
            }
        }
    }


    porcentajeLuz2 = (actualLuz2 - 200) * 0.125


    $("#luzOficina2")
        .removeClass("progress-bar-danger")
        .addClass("progress-bar-info")
        .css("width", porcentajeLuz2 + "%")
        .attr("aria-valuenow", actualLuz2)
        .text(actualLuz2);
    if (actualLuz2 <= minLuzPermisible2) {
        $("#luzOficina2")
            .removeClass("progress-bar-info")
            .addClass("progress-bar-danger")
            .css("width", porcentajeLuz2 + "%")
            .attr("aria-valuenow", actualLuz2)
            .text(actualLuz2);
    } else if (actualLuz2 >= maxLuzPermisible2) {
        $("#luzOficina2")
            .removeClass("progress-bar-info")
            .addClass("progress-bar-danger")
            .css("width", porcentajeLuz2 + "%")
            .attr("aria-valuenow", actualLuz2)
            .text(actualLuz2);
    }


    document.getElementById("inputLuzOficina2").value = actualLuz2
    setTimeout(simuladorLuz2, cambiosTiempo2 * 1000);

})
//<!-- Simaldor TEMPERATURA OFICINA 2 -->

$(document).ready(function simuladorTemp2() {
    var actualTemp2 = document.getElementById("inputTempOficina2").value
    var accionTemp2 = document.getElementById("inputTempOficina2Accion").value

    if (accionTemp2 == "subir") {
        for (i = 0; i < Math.floor(Math.random() * 5) + 2; i++) {
            actualTemp2++
            if (actualTemp2 >= 50) {
                actualTemp2 = 50
            }

        }
    } else {
        for (i = 0; i < Math.floor(Math.random() * 5) + 2; i++) {
            actualTemp2--
            if (actualTemp2 <= 0) {
                actualTemp2 = 0
            }
        }
    }


    porcentajeTemp2 = (actualTemp2) * 2


    $("#tempOficina2")
        .removeClass("progress-bar-danger")
        .addClass("progress-bar-success")
        .css("width", porcentajeTemp2 + "%")
        .attr("aria-valuenow", actualTemp2)
        .text(actualTemp2);
    if (actualTemp2 <= minTempPermisible2) {
        $("#tempOficina2")
            .removeClass("progress-bar-success")
            .addClass("progress-bar-danger")
            .css("width", porcentajeTemp2 + "%")
            .attr("aria-valuenow", actualTemp2)
            .text(actualTemp2);
    } else if (actualTemp2 >= maxTempPermisible2) {
        $("#tempOficina2")
            .removeClass("progress-bar-success")
            .addClass("progress-bar-danger")
            .css("width", porcentajeTemp2 + "%")
            .attr("aria-valuenow", actualTemp2)
            .text(actualTemp2);
    }


    document.getElementById("inputTempOficina2").value = actualTemp2
    setTimeout(simuladorTemp2, cambiosTiempo2 * 1000);

})


$(window).on("load", function enviarDatosLuz2() {
    consola("activadorLuz2", document.getElementById("inputLuzOficina2").value)
    setTimeout(enviarDatosLuz2, envioMensajesTiempo2 * 1000);
})
$(window).on("load", function enviarDatosTemp2() {
    consola("activadorTemp2", document.getElementById("inputTempOficina2").value)
    setTimeout(enviarDatosTemp2, envioMensajesTiempo2 * 1000);
})