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

    /**
     *
     * Constructor de la clase
     *
     * @param generadorConexiones objeto que permite generar conexiones a la
     * base de datos
     */
    public ClientesDAO(IConexionBD generadorConexiones) {
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }

    /**
     *
     * Método que permite consultar un cliente a partir de su correo electrónico
     *
     * @param correo correo electrónico del cliente a consultar
     * @return objeto Cliente que contiene los datos del cliente consultado
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
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

    /**
     *
     * Método que permite insertar un nuevo cliente en la base de datos
     *
     * @param cliente objeto Cliente que contiene los datos del cliente a
     * insertar
     * @return objeto Cliente que contiene los datos del cliente insertado,
     * incluyendo su id generado
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
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

    /**
     *
     * Actualiza un registro de la tabla 'clientes' en la base de datos
     *
     * @param cliente Objeto Cliente con los nuevos datos a actualizar
     * @param idCliente Identificador del registro a actualizar en la tabla
     * @return Objeto Cliente actualizado
     * @throws PersistenciaException Si ocurre un error al actualizar el
     * registro en la base de datos
     */
    @Override
    public Cliente actualizar(Cliente cliente, Integer idCliente) throws PersistenciaException {
        String codigoSQL = "update clientes set nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, edad = ?, correo = ?, contrasena = aes_encrypt(?, 'hunter2'), id_direccion = ? where id_cliente = ?";
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
            comando.setInt(8, idCliente);
            comando.setInt(9, idCliente);
            comando.executeUpdate();
            return cliente;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible actualizar la cuenta");
        }
    }
}
