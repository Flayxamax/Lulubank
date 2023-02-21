/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.logging.Logger;

/**
 *
 * @author ildex
 */
public class Transferencia {

    private Integer id_transferencia;
    private String fecha_operacion;
    private Integer idCuenta;
    private Double monto;
    private Integer idCuentaDestinatario;

    public Transferencia(Integer id_transferencia, String fecha_operacion, Integer idCuenta, Double monto, Integer idCuentaDestinatario) {
        this.id_transferencia = id_transferencia;
        this.fecha_operacion = fecha_operacion;
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.idCuentaDestinatario = idCuentaDestinatario;
    }

    public Transferencia(String fecha_operacion, Integer idCuenta, Double monto, Integer idCuentaDestinatario) {
        this.fecha_operacion = fecha_operacion;
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.idCuentaDestinatario = idCuentaDestinatario;
    }

    public Transferencia(Integer idCuenta, Double monto, Integer idCuentaDestinatario) {
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.idCuentaDestinatario = idCuentaDestinatario;
    }

    public Transferencia() {
    }


    public Integer getId_transferencia() {
        return id_transferencia;
    }

    public void setId_transferencia(Integer id_transferencia) {
        this.id_transferencia = id_transferencia;
    }

    public String getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(String fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getIdCuentaDestinatario() {
        return idCuentaDestinatario;
    }

    public void setIdCuentaDestinatario(Integer idCuentaDestinatario) {
        this.idCuentaDestinatario = idCuentaDestinatario;
    }
    private static final Logger LOG = Logger.getLogger(Transferencia.class.getName());

    @Override
    public String toString() {
        return "Transferencia{" + "id_transferencia=" + id_transferencia + ", fecha_operacion=" + fecha_operacion + ", idCuenta=" + idCuenta + ", monto=" + monto + ", idCuentaDestinatario=" + idCuentaDestinatario + '}';
    }

}
