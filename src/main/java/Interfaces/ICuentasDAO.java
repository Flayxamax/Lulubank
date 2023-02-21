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
    Cuenta consultar(Integer idCuenta) throws PersistenciaException;
    Cuenta insertar(Integer idCliente) throws PersistenciaException;
    List<Cuenta> consultarLista(ConfiguracionPaginado configPaginado) throws PersistenciaException;
    void actualizarEstado(Integer idCuenta) throws PersistenciaException;
    Cuenta actualizarMonto(Integer idCuenta, Double saldo) throws PersistenciaException;
    Cuenta actualizarDescMonto(Integer idCuenta, Double saldo) throws PersistenciaException;
    public int[] consultarIdsCuentas(Integer idCliente) throws PersistenciaException;
    List<Cuenta> consultarListaC(Integer idCuenta) throws PersistenciaException;
}
