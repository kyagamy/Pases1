package com.lords.pases.util;


import android.annotation.SuppressLint;
import android.os.StrictMode;

import com.lords.pases.entidades.Solicitud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Conector {
    static     String ip = Constants.INSTANCE.getServerIP();
    static String classs = "net.sourceforge.jtds.jdbc.Driver";
    static String db = Constants.INSTANCE.getDatabaseName();
    static String un = Constants.INSTANCE.getDatabaseSA();
    static String password = Constants.INSTANCE.getDatabasePassword();
    Connection con;

    public Conector() throws SQLException, ClassNotFoundException {
        con = this.CONN();
    }

    @SuppressLint("NewApi")
    public static Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL;
        try {

            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";

            String s2= "jdbc:jtds:sqlserver://"+ip+":1433/SQLPASES;user=" + un + ";password=" + password;
            conn = DriverManager.getConnection(ConnURL);
            String x = "";
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public ArrayList<Solicitud> consultarSoliMaestro(String... params) {

        /*
        *consultarMaestro
        consulta las solicitudes del maestro
        pide la matricula(id) como parametro
        */
        return null;
    }

    public boolean insertarSoli(String... params) {
        /*
        * crearSolicitud
es para cuando el maestro cree la solicitud
pide los parametros 'Motivo', Estado,'yyyy-mm-dd hh:mm','yyyy-mm-dd',  'hh:mm',   'hh:mm',  matricula(id)
                     @Motivo,@Estado,@FechaElaborada,  @FechaPedida,  @HoraInicio,@HoraFin,@idEmpleados

----------------------------------------------------------
        * */
        return true;
    }

    public boolean eliminarSoli(String... params) {
 /*eliminarSolicitud
pos elimina la solicitud :v
solo pide el idSolicitud     * */


        return true;


    }


    public boolean editSols(String... params) {
        /*modificarSolicitudMaestro
        si el maestro se equivoco puede modificar el motivo o la fecha solicitado
        pide motivo, fechaPedida, horaInicio, HoraFin*/

        return true;
    }



    public boolean registrarSalida() {
        /*registrarSalidaMaestro
aqui solo es para cuando el maestro se va y registra la salida
solo pide idsolicitud y la hora de salida 'yyyy-mm-dd hh:mm'
*/

        return true;
    }


    public boolean registrarMaestro() {
  /*registrarRegresoMaestro
aqui solo es para cuando el maestro regresa
solo pide idsolicitud y la hora de regreso 'yyyy-mm-dd hh:*/

        return true;
    }


    public static  String msj(ResultSet r) throws SQLException {
        return r.getInt("ERROR")==1 ?"Ha ocurrido un error: "+ r.getString("MensajeError"):"Se ha ejecutado correctamente";
    }

}