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

    public Direccion() {

    }

    public Direccion(Integer idDireccion, String calle, String colonia, String numCasa) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.colonia = colonia;
        this.numCasa = numCasa;
    }

    public Direccion(String calle, String colonia, String numCasa) {
        this.calle = calle;
        this.colonia = colonia;
        this.numCasa = numCasa;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    @Override
    public String toString() {
        return "Direccion{" + "idDireccion=" + idDireccion + ", calle=" + calle + ", colonia=" + colonia + ", numCasa=" + numCasa + '}';
    }

}
