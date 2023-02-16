/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Dominio.Cliente;
import Excepciones.PersistenciaException;
import Interfaces.IClientesDAO;
import Interfaces.IConexionBD;
import Utils.ConfiguracionPaginado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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

//    @Override
    public Cliente consultar(Integer idCliente) throws PersistenciaException {
//        String consulta = "SELECT id,nombre,apellido_paterno,apellido_materno,"
//                + "idDireccion FROM clientes WHERE id=?";
//        try (
//                Connection conexion = GENERADOR_CONEXIONES.crearConexion();
//                PreparedStatement comando = conexion.prepareStatement(consulta);) {
//            comando.setInt(1, idCliente);
//            ResultSet registro = comando.executeQuery();
//            Cliente cliente = null;
//            if (registro.next()) {
//                Integer id = registro.getInt("id");
//                String nombre = registro.getString("nombre");
//                String apellidoPaterno = registro.getString("apellido_paterno");
//                String apellidoMaterno = registro.getString("apellido_materno");
//                Integer idDireccion = registro.getInt("idDireccion");
//                cliente = new Cliente();
//                cliente.setId(id);
//                cliente.setNombre(nombre);
//                cliente.setApellidoPaterno(apellidoPaterno);
//                cliente.setApellidoMaterno(apellidoMaterno);
//                cliente.setIdDireccion(idDireccion);
//            }
//            return cliente;
//        } catch (SQLException e) {
//            LOG.log(Level.SEVERE, e.getMessage());
//            return null;
//        }
        return null;
    }

//    @Override
    public List<Cliente> consultarLista(ConfiguracionPaginado configPaginado) throws PersistenciaException {
//        String codigoSQL = "SELECT "
//                + "id, "
//                + "nombre, "
//                + "apellido_paterno, "
//                + "apellido_materno, "
//                + "idDireccion "
//                + "FROM clientes LIMIT ? OFFSET ?;";
//        List<Cliente> listaClientes = new LinkedList<>(); // puede ser ArrayList
//        try (
//                Connection conexion = this.GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
//            comando.setInt(1, configPaginado.getElementosPagina());
//            comando.setInt(2, configPaginado.getElementosASaltar());
//            ResultSet resultado = comando.executeQuery();
//            while (resultado.next()) { // Se utiliza while porque no se sabe la cantidad específica de elementos. Cuando sí se conoce, se usa for
//                Integer IDCliente = resultado.getInt("id");
//                String nombre = resultado.getString("nombre");
//                String apellidoPaterno = resultado.getString("apellido_paterno");
//                String apellidoMaterno = resultado.getString("apellido_materno");
//                Integer idDireccion = resultado.getInt("idDireccion");
//
//                Cliente cliente = new Cliente(IDCliente, nombre, apellidoPaterno, apellidoMaterno, idDireccion);
//                listaClientes.add(cliente);
//            }
//
//            return listaClientes;
//        } catch (SQLException ex) {
//            LOG.log(Level.SEVERE, ex.getMessage()); // Sustituye los System.err
//            throw new PersistenciaException("Error: No fue posible consultar la lista de clientes");
//        }
        return null;
    }

    @Override
    public Cliente insertar(Cliente cliente) throws PersistenciaException {
        String codigoSQL = "INSERT INTO clientes(nombre,apellido_paterno,"
                + "apellido_materno,fecha_nacimiento, edad, contrasena, id_direccion) VALUES (?,?,?,?,?,?,?)";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, cliente.getNombre());
            comando.setString(2, cliente.getApellidoPaterno());
            comando.setString(3, cliente.getApellidoMaterno());
            comando.setString(4, cliente.getFechaNacimiento());
            comando.setInt(5, cliente.getEdad());
            comando.setString(6, cliente.getPassword());
            comando.setInt(7, cliente.getIdDireccion());
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

    @Override
    public Cliente eliminar(Integer idCliente) throws PersistenciaException {
        String codigoSQL = "DELETE FROM clientes where id = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
            Cliente cliente = consultar(idCliente);
            comando.setInt(1, idCliente);
            comando.executeUpdate();
            return cliente;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
}