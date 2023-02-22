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

    /**
     *
     * Crea una nueva Transferencia con todos los atributos especificados.
     *
     * @param id_transferencia Identificador de la transferencia.
     * @param fecha_operacion Fecha en que se realizó la transferencia.
     * @param idCuenta Identificador de la cuenta desde la que se realiza la
     * transferencia.
     * @param monto Monto de la transferencia.
     * @param idCuentaDestinatario Identificador de la cuenta destino de la
     * transferencia.
     */
    public Transferencia(Integer id_transferencia, String fecha_operacion, Integer idCuenta, Double monto, Integer idCuentaDestinatario) {
        this.id_transferencia = id_transferencia;
        this.fecha_operacion = fecha_operacion;
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.idCuentaDestinatario = idCuentaDestinatario;
    }

    /**
     *
     * Crea una nueva Transferencia con los atributos especificados, excepto el
     * identificador de la transferencia.
     *
     * @param fecha_operacion Fecha en que se realizó la transferencia.
     * @param idCuenta Identificador de la cuenta desde la que se realiza la
     * transferencia.
     * @param monto Monto de la transferencia.
     * @param idCuentaDestinatario Identificador de la cuenta destino de la
     * transferencia.
     */
    public Transferencia(String fecha_operacion, Integer idCuenta, Double monto, Integer idCuentaDestinatario) {
        this.fecha_operacion = fecha_operacion;
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.idCuentaDestinatario = idCuentaDestinatario;
    }

    /**
     *
     * Crea una nueva Transferencia con los atributos especificados, excepto el
     * identificador de la transferencia y la fecha de operación.
     *
     * @param idCuenta Identificador de la cuenta desde la que se realiza la
     * transferencia.
     * @param monto Monto de la transferencia.
     * @param idCuentaDestinatario Identificador de la cuenta destino de la
     * transferencia.
     */
    public Transferencia(Integer idCuenta, Double monto, Integer idCuentaDestinatario) {
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.idCuentaDestinatario = idCuentaDestinatario;
    }

    /**
     *
     * Crea una nueva Transferencia vacía, sin atributos inicializados.
     */
    public Transferencia() {
    }

    /**
     *
     * Retorna el identificador de la transferencia.
     *
     * @return Identificador de la transferencia.
     */
    public Integer getId_transferencia() {
        return id_transferencia;
    }

    /**
     *
     * Establece el ID de la transferencia.
     *
     * @param id_transferencia el ID de la transferencia a establecer.
     */
    public void setId_transferencia(Integer id_transferencia) {
        this.id_transferencia = id_transferencia;
    }

    /**
     *
     * Devuelve la fecha de operación de la transferencia.
     *
     * @return la fecha de operación de la transferencia.
     */
    public String getFecha_operacion() {
        return fecha_operacion;
    }

    /**
     *
     * Establece la fecha de operación de la transferencia.
     *
     * @param fecha_operacion la fecha de operación de la transferencia a
     * establecer.
     */
    public void setFecha_operacion(String fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    /**
     *
     * Devuelve el ID de la cuenta de origen de la transferencia.
     *
     * @return el ID de la cuenta de origen de la transferencia.
     */
    public Integer getIdCuenta() {
        return idCuenta;
    }

    /**
     *
     * Establece el ID de la cuenta de origen de la transferencia.
     *
     * @param idCuenta el ID de la cuenta de origen de la transferencia a
     * establecer.
     */
    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     *
     * Devuelve el monto de la transferencia.
     *
     * @return el monto de la transferencia.
     */
    public Double getMonto() {
        return monto;
    }

    /**
     *
     * Establece el monto de la transferencia.
     *
     * @param monto el monto de la transferencia a establecer.
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     *
     * Devuelve el ID de la cuenta destinataria de la transferencia.
     *
     * @return el ID de la cuenta destinataria de la transferencia.
     */
    public Integer getIdCuentaDestinatario() {
        return idCuentaDestinatario;
    }

    /**
     *
     * Establece el ID de la cuenta destinataria de la transferencia.
     *
     * @param idCuentaDestinatario el ID de la cuenta destinataria de la
     * transferencia a establecer.
     */
    public void setIdCuentaDestinatario(Integer idCuentaDestinatario) {
        this.idCuentaDestinatario = idCuentaDestinatario;
    }
    private static final Logger LOG = Logger.getLogger(Transferencia.class.getName());

    /**
     *
     * Devuelve una representación en cadena de la transferencia.
     *
     * @return una cadena que representa la transferencia.
     */
    @Override
    public String toString() {
        return "Transferencia{" + "id_transferencia=" + id_transferencia + ", fecha_operacion=" + fecha_operacion + ", idCuenta=" + idCuenta + ", monto=" + monto + ", idCuentaDestinatario=" + idCuentaDestinatario + '}';
    }

}
