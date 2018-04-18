const minLuzPermisibleConsola = 450
const maxLuzPermisibleConsola = 750
const minTempPermisibleConsola = 15
const maxTempPermisibleConsola = 35

function consola(sensor, valor){

    if (sensor == "activadorLuz1" || sensor == "activadorLuz2"){
        if( valor >= maxLuzPermisibleConsola){
            sendMessage(sensor, '{ "action":"bajar"}')
        }else if(valor <= minLuzPermisibleConsola){
            sendMessage(sensor, '{ "action":"subir"}')
        }else {
            sendMessage(sensor, '{ "action": ""}')
        }
    }else if (sensor == "activadorTemp1" || sensor == "activadorTemp2"){
        if( valor >= maxTempPermisibleConsola){
            sendMessage(sensor,'{ "action":"bajar"}')
        }else if(valor <= minTempPermisibleConsola){
            sendMessage(sensor, '{ "action":"subir"}')
        }else {
            sendMessage(sensor, '{ "action": ""}')
        }
    }
    //console.log("Sensor " + sensor + " valor: " + valor)

    function sendMessage(activador, message) {
        client.send('/topic/activador.' + activador, {}, message);
    }
}