var express = require('express');
var bodyParser = require('body-parser')
var cors = require('cors')
var app = express();

app.use(cors())

var http = require('http');
var path = require('path');
var validator = require('validator');


// all environments
app.set('port', process.env.PORT || 3000);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');
app.use(express.static(path.join(__dirname, 'public')));
// parse application/json
app.use(bodyParser.json())


// Add headers
app.use(function (req, res, next) {

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', '*');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

    // Pass to next layer of middleware
    next();
});
//llamamos al paquete mysql que hemos instalado
var mysql = require('mysql')

http.createServer(app).listen(app.get('port'), function () {
    console.log('Express server listening on port ' + app.get('port'));

    var connection = mysql.createConnection(
        {
            host: 'localhost',
            user: 'root',
            database: 'mtis'
        }
    );


    /********************************************************************************************************************************************/
    /************************************************************VALIDAR DNI*********************************************************************/
    /********************************************************************************************************************************************/

    app.get("/validarNIF/:nif", function (req, res) {
        var key = req.headers.restkey;
        if (connection) {
            try {
                connection.query("SELECT count(1) AS recuento from soap where clave like '" + key + "' ;", function (error, rows) {
                    if (error) {
                        console.log(error);
                        return res.status(500).send({ correcto: false, status: 500, message: "Problemas con el servidor o la base de datos" });
                    } else {
                        if (rows[0].recuento == 0) {
                            return res.status(401).send({ correcto: false, status: 401, message: "RestKey incorrecta" });
                        } else {
                            var dni = req.params.nif
                            var numero, let1, letra;
                            var expresion_regular_dni = /^[XYZ]?\d{5,8}[A-Z]$/;

                            dni = dni.toUpperCase();

                            if (expresion_regular_dni.test(dni) === true) {
                                numero = dni.substr(0, dni.length - 1);
                                numero = numero.replace('X', 0);
                                numero = numero.replace('Y', 1);
                                numero = numero.replace('Z', 2);
                                let1 = dni.substr(dni.length - 1, 1);
                                numero = numero % 23;
                                letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
                                letra = letra.substring(numero, numero + 1);
                                if (letra != let1) {
                                    //alert('Dni erroneo, la letra del NIF no se corresponde');
                                    return res.status(200).send({ correcto: false, status: 200, message: "El NIF no es válido" });
                                } else {
                                    //alert('Dni correcto');
                                    return res.status(200).send({ correcto: true, status: 200, message: "El NIF es válido" });
                                }
                            } else {
                                //alert('Dni erroneo, formato no válido');
                                return res.status(200).send({ correcto: false, status: 200, message: "El NIF no es válido, formato erroneo" });
                            }
                        }
                    }
                });
            } catch (err) {
                return res.status(500).send({ correcto: false, status: 500, message: "Problemas con el servidor o la base de datos" });

            }
        } else {
            return res.status(500).send({ correcto: false, status: 500, message: "Problemas con el servidor o la base de datos" });
        }
    });

    /********************************************************************************************************************************************/
    /************************************************************VALIDAR IBAN********************************************************************/
    /********************************************************************************************************************************************/
    app.get("/validarIBAN/:iban", function (req, res) {
        var key = req.headers.restkey;
        if (connection) {
            try {
                connection.query("SELECT count(1) AS recuento from soap where clave like '" + key + "' ;", function (error, rows) {
                    if (error) {
                        console.log(error);
                        return res.status(500).send({ correcto: false, status: 500, message: "Problemas con el servidor o la base de datos" });
                    } else {
                        if (rows[0].recuento == 0) {
                            return res.status(401).send({ correcto: false, status: 401, message: "RestKey incorrecta" });
                        } else {
                            var IBAN = require('iban');

                            var iban = req.params.iban

                            if (IBAN.isValid(iban)) {
                                return res.status(200).send({ correcto: true, status: 200, message: "El IBAN es válido" });
                            } else {
                                return res.status(200).send({ correcto: false, status: 200, message: "El IBAN NO es válido" });
                            }
                        }
                    }
                });
            } catch (err) {
                return res.status(500).send({ correcto: false, status: 500, message: "Problemas con el servidor o la base de datos" });

            }
        } else {
            return res.status(500).send({ correcto: false, status: 500, message: "Problemas con el servidor o la base de datos" });
        }
    });

    /********************************************************************************************************************************************/
    /********************************************************CONSULTA CÓDIGO POSTAL**************************************************************/
    /********************************************************************************************************************************************/

    app.get("/consultaCodigoPostal/:cp", function (req, res) {
        var key = req.headers.restkey;
        if (connection) {
            try {
                connection.query("SELECT count(1) AS recuento from soap where clave like '" + key + "' ;", function (error, rows) {
                    if (error) {
                        console.log(error);
                        return res.status(500).send({ correcto: false, status: 500, message: "Problemas con el servidor o la base de datos" });
                    } else {
                        if (rows[0].recuento == 0) {
                            return res.status(401).send({
                                correcto: false,
                                status: 401,
                                message: "RestKey incorrecta",
                                Poblacion:
                                    {
                                        codigoPostal: null,
                                        poblacion: null,
                                        provincia: null,
                                        existe: null
                                    }
                            });
                        } else {
                            var cp = req.params.cp
                            connection.query("select cod_postal, poblacion, provincia from poblaciones INNER JOIN provincias ON provincias.cod_prov=poblaciones.cod_prov where cod_postal = " + cp + " LIMIT 1;", function (error, rows) {
                                if (rows.length >= 1) {
                                    return res.status(200).send({
                                        correcto: true,
                                        status: 200,
                                        message: "Se han encontrado datos sobre el código consultado",
                                        Poblacion:
                                            {
                                                codigoPostal: rows[0].cod_postal,
                                                poblacion: rows[0].poblacion,
                                                provincia: rows[0].provincia,
                                                existe: true
                                            }
                                    });

                                } else {
                                    return res.status(200).send({
                                        correcto: false,
                                        status: 200,
                                        message: "No existen datos sobre el código consultado",
                                        Poblacion:
                                            {
                                                codigoPostal: req.params.cp,
                                                poblacion: null,
                                                provincia: null,
                                                existe: false
                                            }
                                    });
                                }
                            })
                        }
                    }
                });
            } catch (err) {
                return res.status(500).send({
                    correcto: false,
                    status: 500,
                    message: "Problemas con el servidor",
                    Poblacion:
                        {
                            codigoPostal: null,
                            poblacion: null,
                            provincia: null,
                            existe: null
                        }
                });

            }
        } else {
            return res.status(500).send({
                correcto: false,
                status: 500,
                message: "Problemas con el servidor",
                Poblacion:
                    {
                        codigoPostal: null,
                        poblacion: null,
                        provincia: null,
                        existe: null
                    }
            });
        }
    });


    /********************************************************************************************************************************************/
    /************************************************************Generar Presupuesto*************************************************************/
    /********************************************************************************************************************************************/

    app.post("/generarPresupuesto", function (req, res) {
        var key = req.headers.restkey;
        console.log(req.body)
        if (connection) {
            try {
                connection.query("SELECT count(1) AS recuento from soap where clave like '" + key + "' ;", function (error, rows) {
                    if (error) {
                        console.log(error);
                        return res.status(500).send({
                            "correcto": false,
                            "status": 500,
                            "message": "Problemas con el servidor",
                            "Presupuesto":
                                {
                                    "idPresupuesto": false,
                                    "presupuestoGeneradoCorrectamente": null
                                }
                        });
                    } else {
                        if (rows[0].recuento == 0) {
                            return res.status(401).send({
                                "correcto": false,
                                "status": 401,
                                "message": "RestKey incorrecta",
                                "Presupuesto":
                                    {
                                        "idPresupuesto": null,
                                        "presupuestoGeneradoCorrectamente": false
                                    }
                            });
                        } else {
                            if (req.body.fechaPresupuesto == "" || req.body.idcliente == "" || req.body.referenciaProducto == "" || req.body.cantidadProducto == "") {
                                return res.status(500).send({
                                    "correcto": false,
                                    "status": 500,
                                    "message": "Problema a la hora de crear el recurso",
                                    "Presupuesto":
                                        {
                                            "idPresupuesto": false,
                                            "presupuestoGeneradoCorrectamente": false
                                        }
                                });
                            }
                            var values = "('" + req.body.fechaPresupuesto + "', '" + req.body.idCliente + "', '" + req.body.referenciaProducto + "', '" + req.body.cantidadProducto + "')";
                            connection.query("INSERT INTO presupuestos (fechaPresupuesto, idCliente, referenciaProducto, cantidadProducto) VALUES " + values + ";", function (error2, rows) {
                                console.log(rows)
                                if (error2) {
                                    return res.status(500).send({
                                        "correcto": false,
                                        "status": 500,
                                        "message": "Problema a la hora de crear el recurso",
                                        "Presupuesto":
                                            {
                                                "idPresupuesto": false,
                                                "presupuestoGeneradoCorrectamente": false
                                            }
                                    });
                                } else {
                                    return res.status(201).send({
                                        "correcto": true,
                                        "status": 201,
                                        "message": "Recurso creado correctamente",
                                        "Presupuesto":
                                            {
                                                "idPresupuesto": rows.insertId,
                                                "presupuestoGeneradoCorrectamente": true
                                            }
                                    });
                                }

                            })
                        }
                    }
                });
            } catch (err) {
                return res.status(500).send({
                    "correcto": false,
                    "status": 500,
                    "message": "Problemas con el servidor",
                    "Presupuesto":
                        {
                            "idPresupuesto": false,
                            "presupuestoGeneradoCorrectamente": null
                        }
                });

            }
        } else {
            return res.status(500).send({
                "correcto": false,
                "status": 500,
                "message": "Problemas con el servidor",
                "Presupuesto":
                    {
                        "idPresupuesto": false,
                        "presupuestoGeneradoCorrectamente": null
                    }
            });
        }
    });
});

