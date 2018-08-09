package com.lords.pases.entidades;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;

public class Solicitud {



        int id;
        String Motivo,FechaCreada,Estado,Respuesta,Salida,Regreso;
        Date dias_solicitado;
        Time HorapedidaSalida,HoraPedidaRegreso;

    public Time getHorapedidaSalida() {
        return HorapedidaSalida;
    }

    public void setHorapedidaSalida(Time horapedidaSalida) {
        HorapedidaSalida = horapedidaSalida;
    }

    public Time getHoraPedidaRegreso() {
        return HoraPedidaRegreso;
    }

    public void setHoraPedidaRegreso(Time horaPedidaRegreso) {
        HoraPedidaRegreso = horaPedidaRegreso;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public String getFechaCreada() {
        return FechaCreada;
    }

    public void setFechaCreada(String fechaCreada) {
        FechaCreada = fechaCreada;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getRespuesta() {
        return Respuesta;
    }

    public void setRespuesta(String respuesta) {
        Respuesta = respuesta;
    }

    public String getSalida() {
        return Salida;
    }

    public void setSalida(String salida) {
        Salida = salida;
    }

    public String getRegreso() {
        return Regreso;
    }

    public void setRegreso(String regreso) {
        Regreso = regreso;
    }

    public Date getDias_solicitado() {
        return dias_solicitado;
    }

    public void setDias_solicitado(Date dias_solicitado) {
        this.dias_solicitado = dias_solicitado;
    }



    public Solicitud(int id, String motivo, String fechaCreada, String estado, String respuesta, String salida, String regreso, Date dias_solicitado, Time horaDeSalida, Time horaderegreso) {
        this.id = id;
        Motivo = motivo;
        FechaCreada = fechaCreada;
        Estado = estado;
        Respuesta = respuesta;
        Salida = salida;
        Regreso = regreso;
        this.dias_solicitado = dias_solicitado;

    }

    public Solicitud() {
    }
}
