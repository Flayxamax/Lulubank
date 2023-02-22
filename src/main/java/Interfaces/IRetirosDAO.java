/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Dominio.Retiro;
import Excepciones.PersistenciaException;
import Utils.ConfiguracionPaginado;
import java.util.List;

/**
 *
 * @author ildex
 */
public interface IRetirosDAO {

    /**
     *
     * Devuelve una lista de Retiros para un cliente específico según la
     * configuración de paginado proporcionada.
     *
     * @param configPaginado la configuración de paginado a aplicar en la
     * consulta.
     * @param idCliente el ID del cliente para el cual se desea obtener la lista
     * de retiros.
     * @return una lista de Retiros para el cliente especificado.
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos.
     */
    public List<Retiro> consultarListaR(ConfiguracionPaginado configPaginado, Integer idCliente) throws PersistenciaException;

    /**
     *
     * Inserta un nuevo Retiro en la base de datos para una cuenta bancaria
     * específica.
     *
     * @param idCuenta el ID de la cuenta bancaria a la cual se debe asociar el
     * nuevo Retiro.
     * @param folio el número de folio del nuevo Retiro.
     * @param monto el monto del nuevo Retiro.
     * @return el Retiro recién insertado.
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos.
     */
    Retiro insertar(Integer idCuenta, Integer folio, Double monto) throws PersistenciaException;

    /**
     *
     * Devuelve un objeto Retiro correspondiente al folio especificado.
     *
     * @param folio el número de folio del Retiro que se desea obtener.
     * @return el Retiro correspondiente al folio especificado.
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos.
     */
    Retiro consultar(Integer folio) throws PersistenciaException;

    /**
     *
     * Devuelve un objeto Retiro correspondiente al folio especificado para una
     * cuenta bancaria específica.
     *
     * @param folio el número de folio del Retiro que se desea obtener.
     * @return el Retiro correspondiente al folio especificado para la cuenta
     * bancaria especificada.
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos.
     */
    Retiro consultarCuenta(Integer folio) throws PersistenciaException;

    /**
     *
     * Actualiza la fecha y hora del Retiro correspondiente al folio
     * especificado, siempre y cuando se proporcione la contraseña correcta.
     *
     * @param folio el número de folio del Retiro que se desea actualizar.
     * @param contra la contraseña del retiro
     * @param fechaA la nueva fecha y hora de la operación de retiro.
     * @return true si la actualización se realizó correctamente, false en caso
     * contrario.
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos.
     */
    boolean actualizar(Integer folio, String contra, String fechaA) throws PersistenciaException;

    /**
     *
     * Actualiza el estado de un retiro identificado por su folio, siempre y
     * cuando la contraseña sea correcta.
     *
     * @param folio El identificador del retiro.
     * @param contra La contraseña del retiro que realizó el retiro.
     * @param estado El nuevo estado a asignar al retiro.
     * @return true si se pudo actualizar el estado del retiro, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error al acceder a la base de
     * datos.
     */
    boolean actualizarEstado(Integer folio, String contra, String estado) throws PersistenciaException;
}
