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
public class ConexionBD implements IConexionBD {

    private final String CADENA_CONEXION;
    private final String USUARIO;
    private final String PASSWORD;

    /**
     *
     * Crea una nueva instancia de ConexionBD con la cadena de conexión, usuario
     * y contraseña especificados.
     *
     * @param cadenaConexion la cadena de conexión a la base de datos
     * @param usuario el nombre de usuario para conectarse a la base de datos
     * @param password la contraseña para conectarse a la base de datos
     */
    public ConexionBD(String cadenaConexion, String usuario, String password) {
        this.CADENA_CONEXION = cadenaConexion;
        this.USUARIO = usuario;
        this.PASSWORD = password;
    }

    /**
     *
     * Crea una nueva conexión a la base de datos con los parámetros
     * especificados.
     *
     * @return una conexión a la base de datos
     * @throws SQLException si ocurre algún error al conectarse a la base de
     * datos
     */
    @Override
    public Connection crearConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASSWORD);
        return conexion;
    }
}
