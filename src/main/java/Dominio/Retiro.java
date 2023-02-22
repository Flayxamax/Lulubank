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
     * Constructor que crea un objeto Retiro con todos los atributos
     * especificados.
     *
     * @param folio El folio del retiro.
     * @param contrasena La contraseña de la cuenta.
     * @param estado El estado del retiro.
     * @param fecha_creacion La fecha de creación del retiro.
     * @param fecha_operacion La fecha de la operación de retiro.
     * @param monto El monto del retiro.
     * @param idCuenta El identificador de la cuenta a la que se hace el retiro.
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

    /**
     *
     * Constructor que crea un objeto Retiro con algunos atributos
     * especificados.
     *
     * @param contrasena La contraseña de la cuenta.
     * @param estado El estado del retiro.
     * @param fecha_creacion La fecha de creación del retiro.
     * @param fecha_operacion La fecha de la operación de retiro.
     * @param monto El monto del retiro.
     * @param idCuenta El identificador de la cuenta a la que se hace el retiro.
     */
    public Retiro(String contrasena, String estado, String fecha_creacion, String fecha_operacion, Double monto, int idCuenta) {
        this.contrasena = contrasena;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_operacion = fecha_operacion;
        this.monto = monto;
        this.idCuenta = idCuenta;
    }

    /**
     *
     * Constructor que crea un objeto Retiro con los atributos folio y
     * contrasena especificados.
     *
     * @param folio El folio del retiro.
     * @param contrasena La contraseña de la cuenta.
     */
    public Retiro(Integer folio, String contrasena) {
        this.folio = folio;
        this.contrasena = contrasena;
    }

    /**
     *
     * Constructor vacío que crea un objeto Retiro con valores predeterminados
     * para todos los atributos.
     */
    public Retiro() {
    }

    /**
     * Constructor que recibe folio, estado, fecha de creación, fecha de
     * operación monto y el id de la cuenta
     *
     * @param folio Folio
     * @param estado Estado
     * @param fecha_creacion Fecha de creación
     * @param fecha_operacion Fecha de operación
     * @param monto Monto
     * @param idCuenta ID de cuenta
     */
    public Retiro(Integer folio, String estado, String fecha_creacion, String fecha_operacion, Double monto, int idCuenta) {
        this.folio = folio;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_operacion = fecha_operacion;
        this.monto = monto;
        this.idCuenta = idCuenta;
    }

    /**
     *
     * Obtiene el valor del atributo folio.
     *
     * @return El valor del atributo folio.
     */
    public Integer getFolio() {
        return folio;
    }

    /**
     *
     * Asigna un valor al atributo folio.
     *
     * @param folio El valor a asignar al atributo folio.
     */
    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    /**
     *
     * Obtiene el valor del atributo contrasena.
     *
     * @return El valor del atributo contrasena.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     *
     * Establece la contraseña de la cuenta.
     *
     * @param contrasena la contraseña a establecer.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     *
     * Obtiene el estado del retiro.
     *
     * @return el estado del retiro.
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     * Establece el estado del retiro.
     *
     * @param estado el estado a establecer.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     *
     * Obtiene la fecha de creación del retiro.
     *
     * @return la fecha de creación del retiro.
     */
    public String getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     *
     * Establece la fecha de creación del retiro.
     *
     * @param fecha_creacion la fecha de creación a establecer.
     */
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     *
     * Obtiene la fecha de la operación del retiro.
     *
     * @return la fecha de la operación del retiro.
     */
    public String getFecha_operacion() {
        return fecha_operacion;
    }

    /**
     *
     * Establece la fecha de la operación del retiro.
     *
     * @param fecha_operacion la fecha de la operación a establecer.
     */
    public void setFecha_operacion(String fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    /**
     *
     * Obtiene el monto del retiro.
     *
     * @return el monto del retiro.
     */
    public Double getMonto() {
        return monto;
    }

    /**
     *
     * Establece el monto del retiro.
     *
     * @param monto el monto a establecer.
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     *
     * Obtiene el identificador de la cuenta asociada al retiro.
     *
     * @return el identificador de la cuenta.
     */
    public int getIdCuenta() {
        return idCuenta;
    }

    /**
     *
     * Establece el identificador de la cuenta asociada al retiro.
     *
     * @param idCuenta el identificador de la cuenta a establecer.
     */
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     *
     * Logger para la clase Retiro.
     */
    private static final Logger LOG = Logger.getLogger(Retiro.class.getName());

    /**
     *
     * Devuelve una cadena que representa al objeto Retiro.
     *
     * @return una cadena que representa al objeto Retiro.
     */
    @Override
    public String toString() {
        return "Retiro{" + "folio=" + folio + ", contrasena=" + contrasena + ", estado=" + estado + ", fecha_creacion=" + fecha_creacion + ", fecha_operacion=" + fecha_operacion + ", monto=" + monto + ", idCuenta=" + idCuenta + '}';
    }

}
