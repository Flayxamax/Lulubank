/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Dominio.Cliente;
import Excepciones.PersistenciaException;
import Utils.ConfiguracionPaginado;
import java.util.List;

/**
 *
 * @author ildex
 */
public interface IClientesDAO {
    Cliente consultar(Integer idCliente) throws PersistenciaException;
    Cliente insertar (Cliente cliente) throws PersistenciaException;
    Cliente eliminar (Integer idCliente) throws PersistenciaException;
    List<Cliente> consultarLista(ConfiguracionPaginado configPaginado) throws PersistenciaException;
}
