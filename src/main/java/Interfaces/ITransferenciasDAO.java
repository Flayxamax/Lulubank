/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Dominio.Transferencia;
import Excepciones.PersistenciaException;
import Utils.ConfiguracionPaginado;
import java.util.List;

/**
 *
 * @author ildex
 */
public interface ITransferenciasDAO {

    /**
     *
     * Consulta la lista de transferencias asociadas a una cuenta específica.
     *
     * @param idCuenta El ID de la cuenta cuyas transferencias se van a
     * consultar.
     * @return Una lista de objetos Transferencia que representan las
     * transferencias asociadas a la cuenta especificada.
     * @throws PersistenciaException Si hay un problema al interactuar con la
     * base de datos.
     */
    List<Transferencia> consultarListaT(Integer idCuenta) throws PersistenciaException;

    /**
     *
     * Consulta la lista de transferencias asociadas a un cliente específico,
     * utilizando paginación.
     *
     * @param configPaginado Una instancia de ConfiguracionPaginado que define
     * los parámetros de paginación para la consulta.
     * @param idCliente El ID del cliente cuyas transferencias se van a
     * consultar.
     * @return Una lista de objetos Transferencia que representan las
     * transferencias asociadas al cliente especificado.
     * @throws PersistenciaException Si hay un problema al interactuar con la
     * base de datos.
     */
    List<Transferencia> consultarLista(ConfiguracionPaginado configPaginado, Integer idCliente) throws PersistenciaException;

    /**
     *
     * Inserta una nueva transferencia en la base de datos.
     *
     * @param idCuenta El ID de la cuenta desde la que se envía la
     * transferencia.
     * @param idCuentaDestinatario El ID de la cuenta a la que se envía la
     * transferencia.
     * @param monto El monto de la transferencia.
     * @throws PersistenciaException Si hay un problema al interactuar con la
     * base de datos.
     */
    void insertar(Integer idCuenta, Integer idCuentaDestinatario, Double monto) throws PersistenciaException;

    /**
     *
     * Inserta una nueva transferencia en la base de datos y actualiza el saldo
     * de las cuentas involucradas.
     *
     * @param idCuenta El ID de la cuenta desde la que se envía la
     * transferencia.
     * @param idCuentaDestinatario El ID de la cuenta a la que se envía la
     * transferencia.
     * @param monto El monto de la transferencia.
     * @throws PersistenciaException Si hay un problema al interactuar con la
     * base de datos.
     */
    void insertarT(Integer idCuenta, Integer idCuentaDestinatario, Double monto) throws PersistenciaException;
}
