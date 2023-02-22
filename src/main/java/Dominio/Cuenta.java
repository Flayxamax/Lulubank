/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author ildex
 */
public class Cuenta {

    private Integer idCuenta;
    private Double saldo;
    private String fechaApertura;
    private String estado;
    private Integer idCliente;

    /**
     * Constructor vacío que no recibe ningún parámetro.
     */
    public Cuenta() {
    }

    /**
     * Constructor que recibe los valores de idCuenta, saldo, fechaApertura,
     * estado e idCliente.
     *
     * @param idCuenta id Cuenta
     * @param saldo saldo
     * @param fechaApertura fecha de apertura
     * @param estado Estado
     * @param idCliente id_cliente
     */
    public Cuenta(Integer idCuenta, Double saldo, String fechaApertura, String estado, Integer idCliente) {
        this.idCuenta = idCuenta;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    /**
     * Constructor que recibe los valores de saldo, fechaApertura, estado e
     * idCliente.
     *
     * @param saldo saldo
     * @param fechaApertura fecha de apertura
     * @param estado Estado
     * @param idCliente id_cliente
     */
    public Cuenta(Double saldo, String fechaApertura, String estado, Integer idCliente) {
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    /**
     * Constructor que recibe solo idCuenta
     *
     * @param idCuenta idCuenta
     */
    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * Constructor que recibe idCuenta y saldo
     *
     * @param idCuenta idCuenta
     * @param saldo Saldo
     */
    public Cuenta(Integer idCuenta, Double saldo) {
        this.idCuenta = idCuenta;
        this.saldo = saldo;
    }

    /**
     * Constructor que recibe saldo, fecha de apertura y estado
     *
     * @param saldo Saldo
     * @param fechaApertura fecha de apertura
     * @param estado Estado
     */
    public Cuenta(Double saldo, String fechaApertura, String estado) {
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
    }

    /**
     * Regresa idCuenta
     *
     * @return idCuenta
     */
    public Integer getIdCuenta() {
        return idCuenta;
    }

    /**
     *
     * Setea el id de la cuenta.
     *
     * @param idCuenta El id de la cuenta a setear.
     */
    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     *
     * Obtiene el saldo de la cuenta.
     *
     * @return El saldo actual de la cuenta.
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     *
     * Setea el saldo de la cuenta.
     *
     * @param saldo El saldo de la cuenta a setear.
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    /**
     *
     * Obtiene la fecha de apertura de la cuenta.
     *
     * @return La fecha de apertura de la cuenta.
     */
    public String getFechaApertura() {
        return fechaApertura;
    }

    /**
     *
     * Setea la fecha de apertura de la cuenta.
     *
     * @param fechaApertura La fecha de apertura de la cuenta a setear.
     */
    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     *
     * Obtiene el estado de la cuenta.
     *
     * @return El estado actual de la cuenta.
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     * Setea el estado de la cuenta.
     *
     * @param estado El estado de la cuenta a setear.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     *
     * Obtiene el id del cliente asociado a la cuenta.
     *
     * @return El id del cliente asociado a la cuenta.
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     *
     * Setea el id del cliente asociado a la cuenta.
     *
     * @param idCliente El id del cliente asociado a la cuenta a setear.
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     *
     * Devuelve una representación en formato de String del objeto Cuenta.
     *
     * @return Una representación en formato de String del objeto Cuenta.
     */
    @Override
    public String toString() {
        return "Cuenta{" + "idCuenta=" + idCuenta + ", saldo=" + saldo + ", fechaApertura=" + fechaApertura + ", estado=" + estado + ", idCliente=" + idCliente + '}';
    }
}
