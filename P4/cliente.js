var user = null;
var password = null;
var client = Stomp.client("ws://localhost:61614"); // configurar!!!
client.connect(user, password, onconnect, onerror);

function onconnect() {
    console.log("Se ha conectado")
}

function onerror() {
    // manejamos el error
    console.log("Ha ocurrido un error")

}