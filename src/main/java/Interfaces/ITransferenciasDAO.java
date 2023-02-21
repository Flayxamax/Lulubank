/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Dominio.Transferencia;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author ildex
 */
public interface ITransferenciasDAO {
    List<Transferencia> consultarListaT(Integer idCuenta) throws PersistenciaException;
    void insertar(Integer idCuenta, Integer idCuentaDestinatario, Double monto) throws PersistenciaException;
    void insertarT(Integer idCuenta, Integer idCuentaDestinatario, Double monto) throws PersistenciaException;
    
}
