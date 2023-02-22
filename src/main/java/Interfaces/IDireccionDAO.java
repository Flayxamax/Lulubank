/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Dominio.Direccion;
import Excepciones.PersistenciaException;

/**
 *
 * @author ildex
 */
public interface IDireccionDAO {

    /**
     *
     * Inserta una nueva dirección en la base de datos.
     *
     * @param direccion La dirección que se desea insertar.
     * @return La dirección insertada.
     * @throws PersistenciaException Si ocurre un error al insertar la dirección
     * en la base de datos.
     */
    Direccion insertar(Direccion direccion) throws PersistenciaException;

    /**
     *
     * Consulta una dirección de la base de datos.
     *
     * @param idCliente El ID del cliente cuya dirección se desea consultar.
     * @return La dirección del cliente.
     * @throws PersistenciaException Si ocurre un error al consultar la
     * dirección en la base de datos.
     */
    Direccion consultar(Integer idCliente) throws PersistenciaException;

    /**
     *
     * Actualiza una dirección en la base de datos.
     *
     * @param direccion La dirección actualizada.
     * @param idDireccion El ID de la dirección que se desea actualizar.
     * @return La dirección actualizada.
     * @throws PersistenciaException Si ocurre un error al actualizar la
     * dirección en la base de datos.
     */
    Direccion actualizar(Direccion direccion, Integer idDireccion) throws PersistenciaException;
}
