package com.lords.pases.entidades

import java.sql.Date
import java.sql.Time

class Solicitud {

    var id: Int = 0
    var motivo: String = ""
    lateinit var fechaCreada: String
    var estado: String = ""
    var respuesta: String = ""
    var salida: String = ""
    var regreso: String = ""
    var dias_solicitado: Date? = null
    var horapedidaSalida: Time? = null
    var horaPedidaRegreso: Time? = null

    constructor()
}
