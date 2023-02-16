/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Interfaces.IConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ildex
 */
public class ConexionBD implements IConexionBD{
    private final String CADENA_CONEXION;
    private final String USUARIO;
    private final String PASSWORD;

    public ConexionBD(String cadenaConexion, String usuario, String password) {
        this.CADENA_CONEXION = cadenaConexion;
        this.USUARIO = usuario;
        this.PASSWORD = password;
    }

    @Override
    public Connection crearConexion() throws SQLException{
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION,USUARIO,PASSWORD);
        return conexion;
    }
}
