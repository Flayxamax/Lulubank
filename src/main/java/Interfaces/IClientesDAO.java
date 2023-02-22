/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Dominio.Cliente;
import Excepciones.PersistenciaException;

/**
 *
 * @author ildex
 */
public interface IClientesDAO {

    /**
     *
     * Consulta los datos de un cliente a partir de su correo electrónico.
     *
     * @param correo el correo electrónico del cliente a buscar
     * @return un objeto de tipo Cliente con los datos del cliente encontrado
     * @throws PersistenciaException si se produce un error al acceder a la base
     * de datos
     */
    Cliente consultar(String correo) throws PersistenciaException;

    /**
     *
     * Inserta los datos de un nuevo cliente en la base de datos.
     *
     * @param cliente un objeto de tipo Cliente con los datos del nuevo cliente
     * @return un objeto de tipo Cliente con los datos del cliente insertado,
     * incluyendo el id asignado por la base de datos
     * @throws PersistenciaException si se produce un error al acceder a la base
     * de datos
     */
    Cliente insertar(Cliente cliente) throws PersistenciaException;

    /**
     *
     * Actualiza los datos de un cliente existente en la base de datos.
     *
     * @param cliente un objeto de tipo Cliente con los datos actualizados del
     * cliente
     * @param idCliente el id del cliente a actualizar
     * @return un objeto de tipo Cliente con los datos del cliente actualizado
     * @throws PersistenciaException si se produce un error al acceder a la base
     * de datos
     */
    Cliente actualizar(Cliente cliente, Integer idCliente) throws PersistenciaException;
}
