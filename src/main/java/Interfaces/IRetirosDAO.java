/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Dominio.Retiro;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author ildex
 */
public interface IRetirosDAO {
    List<Retiro> consultarLista(Integer idCuenta) throws PersistenciaException;
    Retiro insertar(Integer idCuenta, Double monto) throws PersistenciaException;
}
