/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Dominio.Direccion;
import Excepciones.PersistenciaException;

/**
 *
 * @author ildex
 */
public interface IDireccionDAO {
    Direccion insertar (Direccion direccion) throws PersistenciaException;
}
