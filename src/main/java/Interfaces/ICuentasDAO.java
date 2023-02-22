/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Dominio.Cuenta;
import Excepciones.PersistenciaException;
import Utils.ConfiguracionPaginado;
import java.util.List;

/**
 *
 * @author ildex
 */
public interface ICuentasDAO {

    /**
     *
     * Método que permite consultar una cuenta en base a su identificador único.
     *
     * @param idCuenta el identificador único de la cuenta a consultar
     * @return la cuenta consultada
     * @throws PersistenciaException si ocurre un error al consultar la cuenta
     * en la base de datos
     */
    Cuenta consultar(Integer idCuenta) throws PersistenciaException;

    /**
     *
     * Método que permite insertar una nueva cuenta en la base de datos en base
     * a un identificador de cliente.
     *
     * @param idCliente el identificador de cliente al cual pertenecerá la nueva
     * cuenta
     * @return la nueva cuenta insertada
     * @throws PersistenciaException si ocurre un error al insertar la cuenta en
     * la base de datos
     */
    Cuenta insertar(Integer idCliente) throws PersistenciaException;

    /**
     *
     * Método que permite consultar una lista de cuentas en base a una
     * configuración de paginado.
     *
     * @param configPaginado la configuración de paginado a aplicar en la
     * consulta
     * @return la lista de cuentas consultadas
     * @throws PersistenciaException si ocurre un error al consultar la lista de
     * cuentas en la base de datos
     */
    List<Cuenta> consultarLista(ConfiguracionPaginado configPaginado) throws PersistenciaException;

    /**
     *
     * Método que permite actualizar el estado de una cuenta en base a su
     * identificador único.
     *
     * @param idCuenta el identificador único de la cuenta a actualizar
     * @throws PersistenciaException si ocurre un error al actualizar la cuenta
     * en la base de datos
     */
    void actualizarEstado(Integer idCuenta) throws PersistenciaException;

    /**
     *
     * Método que permite actualizar el monto de una cuenta en base a su
     * identificador único.
     *
     * @param idCuenta el identificador único de la cuenta a actualizar
     * @param saldo el nuevo saldo de la cuenta a actualizar
     * @return la cuenta actualizada
     * @throws PersistenciaException si ocurre un error al actualizar la cuenta
     * en la base de datos
     */
    Cuenta actualizarMonto(Integer idCuenta, Double saldo) throws PersistenciaException;

    /**
     *
     * Método que permite actualizar el descubierto de una cuenta en base a su
     * identificador único.
     *
     * @param idCuenta el identificador único de la cuenta a actualizar
     * @param saldo el nuevo descubierto de la cuenta a actualizar
     * @return la cuenta actualizada
     * @throws PersistenciaException si ocurre un error al actualizar la cuenta
     * en la base de datos
     */
    Cuenta actualizarDescMonto(Integer idCuenta, Double saldo) throws PersistenciaException;

    /**
     *
     * Método que permite consultar una lista de identificadores de cuenta en
     * base a un identificador de cliente.
     *
     * @param idCliente el identificador de cliente del cual se buscarán las
     * cuentas asociadas
     * @return un array de enteros con los identificadores de cuenta encontrados
     * @throws PersistenciaException si ocurre un error al consultar los
     * identificadores de cuenta en la base de datos
     */
    public int[] consultarIdsCuentas(Integer idCliente) throws PersistenciaException;

    /**
     *
     * Método que permite consultar una lista de cuentas en base a su
     * identificador único.
     *
     * @param idCuenta el identificador único de la cuenta a consultar
     * @return la lista de cuentas consultadas
     * @throws PersistenciaException si ocurre un error al consultar la lista de
     * cuentas en la base de datos
     */
    List<Cuenta> consultarListaC(Integer idCuenta) throws PersistenciaException;
}
