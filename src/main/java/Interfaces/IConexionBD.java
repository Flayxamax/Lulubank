/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ildex
 */
public interface IConexionBD {

    /**
     *
     * Crea y devuelve una conexión con la base de datos.
     *
     * @return Una conexión con la base de datos.
     * @throws SQLException Si ocurre un error al conectarse a la base de datos.
     */
    Connection crearConexion() throws SQLException;
}
