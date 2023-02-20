/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Dominio.Cliente;
import Excepciones.PersistenciaException;
import Interfaces.IClientesDAO;
import Interfaces.IConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ildex
 */
public class ClientesDAO implements IClientesDAO {

    private static final Logger LOG = Logger.getLogger(ClientesDAO.class.getName());
    private final IConexionBD GENERADOR_CONEXIONES;

    public ClientesDAO(IConexionBD generadorConexiones) {
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }

    /**
     *
     * @param correo
     * @return null
     * @throws PersistenciaException
     */
    @Override
    public Cliente consultar(String correo) throws PersistenciaException {
        Cliente cliente = new Cliente();
        String consulta = "SELECT id_cliente, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, edad, correo, aes_decrypt(contrasena,'hunter2') as 'contrasena' FROM clientes WHERE correo = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(consulta);) {
            comando.setString(1, correo);
            ResultSet registro = comando.executeQuery();
            if (registro.next()) {
                cliente.setId(registro.getInt("id_cliente"));
                cliente.setNombre(registro.getString("nombre"));
                cliente.setApellidoPaterno(registro.getString("apellido_paterno"));
                cliente.setApellidoMaterno(registro.getString("apellido_materno"));
                cliente.setFechaNacimiento(registro.getString("fecha_nacimiento"));
                cliente.setEdad(registro.getInt("edad"));
                cliente.setCorreo(registro.getString("correo"));
                cliente.setPassword(registro.getString("contrasena"));
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return cliente;
    }

    @Override
    public Cliente insertar(Cliente cliente) throws PersistenciaException {
        String codigoSQL = "INSERT INTO clientes(nombre,apellido_paterno,"
                + "apellido_materno,fecha_nacimiento, edad,correo, contrasena, id_direccion) VALUES (?,?,?,?,?,?,aes_encrypt(?,'hunter2'),?)";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, cliente.getNombre());
            comando.setString(2, cliente.getApellidoPaterno());
            comando.setString(3, cliente.getApellidoMaterno());
            comando.setString(4, cliente.getFechaNacimiento());
            comando.setInt(5, cliente.getEdad());
            comando.setString(6, cliente.getCorreo());
            comando.setString(7, cliente.getPassword());
            comando.setInt(8, cliente.getIdDireccion());
            comando.executeUpdate();
            ResultSet llavesGeneradas = comando.getGeneratedKeys();
            if (llavesGeneradas.next()) {
                Integer llavePrimaria = llavesGeneradas.getInt("GENERATED_KEY");
                cliente.setId(llavePrimaria);
                return cliente;
            }
            LOG.log(Level.WARNING, "Cliente registrado, pero ID no fue registrado");
            throw new PersistenciaException("Cliente registrado, pero ID no generado");
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible registrar cliente");
        }
    }
}
