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
     * Constructor que recibe los valores de idCuenta, saldo, fechaApertura, estado e idCliente.
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
     * Constructor que recibe los valores de saldo, fechaApertura, estado e idCliente.
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
     * 
     * 
     * @param idCuenta 
     */
    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }
    
    public Cuenta(Integer idCuenta, Double saldo){
        this.idCuenta = idCuenta;
        this.saldo = saldo;
    }
    
    public Cuenta(Double saldo, String fechaApertura, String estado){
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "idCuenta=" + idCuenta + ", saldo=" + saldo + ", fechaApertura=" + fechaApertura + ", estado=" + estado + ", idCliente=" + idCliente + '}';
    }
}
