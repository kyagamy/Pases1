package com.lords.pases.util

import java.sql.ResultSet

object Constants {
    val serverIP = "sql5027.site4now.net"
    val databasePassword = "ASDF1234"
    val databaseName = "DB_A3F5A1_pases"
    val databaseSA = "DB_A3F5A1_pases_admin"

      fun  msj (r : ResultSet): String {
        return if (r.getInt("ERROR") == 0) "Se ha ingresado correctamente" else "Ha ocurrido un error: " + r.getString("MensajeError")
    }

}
