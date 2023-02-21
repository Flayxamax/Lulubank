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
public class Retiro {

    private Integer folio;
    private String contrasena;
    private String estado;
    private String fecha_creacion;
    private String fecha_operacion;
    private Double monto;
    private int idCuenta;
    
    /**
     * 
     * @param folio
     * @param contrasena
     * @param estado
     * @param fecha_creacion
     * @param fecha_operacion
     * @param monto
     * @param idCuenta 
     */
    public Retiro(Integer folio, String contrasena, String estado, String fecha_creacion, String fecha_operacion, Double monto, int idCuenta) {
        this.folio = folio;
        this.contrasena = contrasena;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_operacion = fecha_operacion;
        this.monto = monto;
        this.idCuenta = idCuenta;
    }

    public Retiro(String contrasena, String estado, String fecha_creacion, String fecha_operacion, Double monto, int idCuenta) {
        this.contrasena = contrasena;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_operacion = fecha_operacion;
        this.monto = monto;
        this.idCuenta = idCuenta;
    }

    public Retiro(Integer folio, String contrasena) {
        this.folio = folio;
        this.contrasena = contrasena;
    }
    
    public Retiro() {
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(String fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    private static final Logger LOG = Logger.getLogger(Retiro.class.getName());

    @Override
    public String toString() {
        return "Retiro{" + "folio=" + folio + ", contrasena=" + contrasena + ", estado=" + estado + ", fecha_creacion=" + fecha_creacion + ", fecha_operacion=" + fecha_operacion + ", monto=" + monto + ", idCuenta=" + idCuenta + '}';
    }

}
