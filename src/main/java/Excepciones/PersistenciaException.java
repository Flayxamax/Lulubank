/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author ildex
 */
public class PersistenciaException extends Exception {

    public PersistenciaException() {
    }

    /**
     *
     * Excepción que se lanza cuando se produce un error en la persistencia de
     * datos.
     *
     * @param string mensaje de descripción del error
     */
    public PersistenciaException(String string) {
        super(string);
    }

    /**
     *
     * Excepción que se lanza cuando se produce un error en la persistencia de
     * datos.
     *
     * @param string mensaje de descripción del error
     * @param thrwbl excepción original que causó el error
     */
    public PersistenciaException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    /**
     *
     * Excepción que se lanza cuando se produce un error en la persistencia de
     * datos.
     *
     * @param thrwbl excepción original que causó el error
     */
    public PersistenciaException(Throwable thrwbl) {
        super(thrwbl);
    }

    /**
     *
     * Excepción que se lanza cuando se produce un error en la persistencia de
     * datos.
     *
     * @param string mensaje de descripción del error
     * @param thrwbl excepción original que causó el error
     * @param bln determina si se habilita o no la supresión de excepciones
     * @param bln1 determina si se habilita o no la escritura en el registro de
     * la pila de excepciones
     */
    public PersistenciaException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
}
