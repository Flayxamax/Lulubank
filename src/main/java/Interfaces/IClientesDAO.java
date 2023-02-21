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
    Cliente consultar(String correo) throws PersistenciaException;
    Cliente insertar (Cliente cliente) throws PersistenciaException;
    void actualizar (Cliente cliente) throws PersistenciaException;
}
