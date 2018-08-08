package com.lords.pases.entidades;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Date;
import java.sql.Time;

public class Solicitud {

    private int idSolicitud, idEmpleado;
    private String estado, motivo;
    Date fechaPedida;
    Time horaSolicitud, horaInicio, horaFin, Regreso;
    DateTime fechaElaborada;

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaPedida() {
        return fechaPedida;
    }

    public void setFechaPedida(Date fechaPedida) {
        this.fechaPedida = fechaPedida;
    }

    public DateTime getFechaElaborada() {
        return fechaElaborada;
    }

    public void setFechaElaborada(DateTime fechaElaborada) {
        this.fechaElaborada = fechaElaborada;
    }

    public Time getHoraSolicitud() {
        return horaSolicitud;
    }

    public void setHoraSolicitud(Time horaSolicitud) {
        this.horaSolicitud = horaSolicitud;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Time getRegreso() {
        return Regreso;
    }

    public void setRegreso(Time regreso) {
        Regreso = regreso;
    }

    public Solicitud(int idSolicitud, int idEmpleado, String estado, String motivo, Date fechaPedida, DateTime fechaElaborada, Time horaSolicitud, Time horaInicio, Time horaFin, Time regreso) {
        this.idSolicitud = idSolicitud;
        this.idEmpleado = idEmpleado;
        this.estado = estado;
        this.motivo = motivo;
        this.fechaPedida = fechaPedida;
        this.fechaElaborada = fechaElaborada;
        this.horaSolicitud = horaSolicitud;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        Regreso = regreso;
    }
}
