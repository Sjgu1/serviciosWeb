const minLuzPermisibleConsola = 450
const maxLuzPermisibleConsola = 750
const minTempPermisibleConsola = 15
const maxTempPermisibleConsola = 35
var clientConsola = Stomp.client("ws://localhost:61614"); // configurar!!!
var userConsola = null;
var passwordConsola = null;

clientConsola.connect(userConsola, passwordConsola, onconnectConsola, onerrorConsola);

function onconnectConsola() {
    console.log("Se ha conectado la consola")

}

function onerrorConsola() {
    console.log("Error al conectar consola")

    setTimeout(function () {
        clientConsola.connect(userConsola, passwordConsola, onconnectConsola, onerrorConsola);
    }, 2000);

}

$(window).on("load", function subscribeMessageQueueTemp() {
    setTimeout(function () {
        clientConsola.subscribe('/topic/sensor.sensorLuz1', consola);
        clientConsola.subscribe('/topic/sensor.sensorLuz2', consola);
        clientConsola.subscribe('/topic/sensor.sensorTemp1', consola);
        clientConsola.subscribe('/topic/sensor.sensorTemp2', consola);

    }, 5000);
})

function consola(message) {
    var body = message.body
    var messageBody = JSON.parse(body);

    if (messageBody.origen == "luz1") {
        if (messageBody.valor >= maxLuzPermisibleConsola) {
            sendMessage("activadorLuz1", '{ "action":"bajar"}')
        } else if (messageBody.valor <= minLuzPermisibleConsola) {
            sendMessage("activadorLuz1", '{ "action":"subir"}')
        } else {
            sendMessage("activadorLuz1", '{ "action": ""}')
        }
    } else if (messageBody.origen == "luz2") {
        if (messageBody.valor >= maxLuzPermisibleConsola) {
            sendMessage("activadorLuz2", '{ "action":"bajar"}')
        } else if (messageBody.valor <= minLuzPermisibleConsola) {
            sendMessage("activadorLuz2", '{ "action":"subir"}')
        } else {
            sendMessage("activadorLuz2", '{ "action": ""}')
        }
    } else if (messageBody.origen == "temp1") {
        var sensor = "activadorTemp1"
        if (messageBody.valor >= maxTempPermisibleConsola) {
            sendMessage(sensor, '{ "action":"bajar"}')
        } else if (messageBody.valor <= minTempPermisibleConsola) {
            sendMessage(sensor, '{ "action":"subir"}')
        } else {
            sendMessage(sensor, '{ "action": ""}')
        }

    } else if (messageBody.origen == "temp2") {
        var sensor = "activadorTemp2"
        if (messageBody.valor >= maxTempPermisibleConsola) {
            sendMessage(sensor, '{ "action":"bajar"}')
        } else if (messageBody.valor <= minTempPermisibleConsola) {
            sendMessage(sensor, '{ "action":"subir"}')
        } else {
            sendMessage(sensor, '{ "action": ""}')
        }
    }
}
function sendMessage(activador, message) {
    clientConsola.send('/topic/activador.' + activador, {}, message);
}

