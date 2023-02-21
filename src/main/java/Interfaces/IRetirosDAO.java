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
     public List<Retiro> consultarListaR(ConfiguracionPaginado configPaginado) throws PersistenciaException;
    Retiro insertar(Integer idCuenta, Integer folio, Double monto) throws PersistenciaException;
    Retiro consultar(Integer folio) throws PersistenciaException;
    Retiro consultarCuenta(Integer folio) throws PersistenciaException;
    boolean actualizar(Integer folio, String contra, String fechaA) throws PersistenciaException;
    boolean actualizarEstado(Integer folio, String contra, String estado) throws PersistenciaException;
    List<Retiro> consultarLista(Integer idCuenta) throws PersistenciaException;
    Retiro insertar(Integer idCuenta, Double monto) throws PersistenciaException;
}
