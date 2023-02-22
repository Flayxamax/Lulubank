/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author ildex
 */
public class Direccion {

    private Integer idDireccion;
    private String calle;
    private String colonia;
    private String numCasa;

    /**
     *
     * Constructor vacío para la clase Direccion
     */
    public Direccion() {

    }

    /**
     *
     * Constructor para la clase Direccion que recibe el id de la dirección, la
     * calle, la colonia y el número de casa
     *
     * @param idDireccion el id de la dirección
     * @param calle la calle de la dirección
     * @param colonia la colonia de la dirección
     * @param numCasa el número de casa de la dirección
     */
    public Direccion(Integer idDireccion, String calle, String colonia, String numCasa) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.colonia = colonia;
        this.numCasa = numCasa;
    }

    /**
     *
     * Constructor para la clase Direccion que recibe la calle, la colonia y el
     * número de casa
     *
     * @param calle la calle de la dirección
     * @param colonia la colonia de la dirección
     * @param numCasa el número de casa de la dirección
     */
    public Direccion(String calle, String colonia, String numCasa) {
        this.calle = calle;
        this.colonia = colonia;
        this.numCasa = numCasa;
    }

    /**
     *
     * Obtiene el id de la dirección
     *
     * @return el id de la dirección
     */
    public Integer getIdDireccion() {
        return idDireccion;
    }

    /**
     *
     * Establece el id de la dirección
     *
     * @param idDireccion el id de la dirección
     */
    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    /**
     *
     * Obtiene la calle de la dirección
     *
     * @return la calle de la dirección
     */
    public String getCalle() {
        return calle;
    }

    /**
     *
     * Establece la calle de la dirección
     *
     * @param calle la calle de la dirección
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     *
     * Obtiene la colonia de la dirección
     *
     * @return la colonia de la dirección
     */
    public String getColonia() {
        return colonia;
    }

    /**
     *
     * Establece la colonia de la dirección
     *
     * @param colonia la colonia de la dirección
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     *
     * Obtiene el número de casa de la dirección
     *
     * @return el número de casa de la dirección
     */
    public String getNumCasa() {
        return numCasa;
    }

    /**
     *
     * Establece el número de casa de la dirección
     *
     * @param numCasa el número de casa de la dirección
     */
    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    /**
     *
     * Representación en cadena de la clase Direccion con información de id,
     * calle, colonia y número de casa
     *
     * @return la representación en cadena de la clase Direccion
     */
    @Override
    public String toString() {
        return "Direccion{" + "idDireccion=" + idDireccion + ", calle=" + calle + ", colonia=" + colonia + ", numCasa=" + numCasa + '}';
    }

}
