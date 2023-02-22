/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Dominio.Retiro;
import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IRetirosDAO;
import Utils.ConfiguracionPaginado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ildex
 */
public class RetirosDAO implements IRetirosDAO {

    private static final Logger LOG = Logger.getLogger(CuentasDAO.class.getName());
    private final IConexionBD GENERADOR_CONEXIONES;

    /**
     *
     * Constructor de la clase que recibe un generador de conexiones a la base
     * de datos
     *
     * @param generadorConexiones Objeto que permite generar una conexión a la
     * base de datos
     */
    public RetirosDAO(IConexionBD generadorConexiones) {
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }

    /**
     *
     * Implementación del método insertar de la interfaz RetiroDAO. Inserta un
     * registro de retiro en la base de datos.
     *
     * @param idCuenta identificador de la cuenta.
     * @param folio folio del retiro.
     * @param monto cantidad de dinero a retirar.
     * @return objeto Retiro con los datos del retiro insertado.
     * @throws PersistenciaException si ocurre algún error al insertar el
     * registro.
     */
    @Override
    public Retiro insertar(Integer idCuenta, Integer folio, Double monto) throws PersistenciaException {
        try {
            Connection conexion = GENERADOR_CONEXIONES.crearConexion();
            CallableStatement cstmt = conexion.prepareCall("{call retiro(?, ?, ?)}");
            cstmt.setInt(1, folio);
            cstmt.setInt(2, idCuenta);
            cstmt.setDouble(3, monto);
            cstmt.execute();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible registrar retiro");
        }
        return null;
    }

    /**
     *
     * Implementación del método consultar de la interfaz RetiroDAO. Consulta la
     * información de un registro de retiro en la base de datos.
     *
     * @param folio folio del retiro a consultar.
     * @return objeto Retiro con los datos del retiro consultado.
     * @throws PersistenciaException si ocurre algún error al consultar el
     * registro.
     */
    @Override
    public Retiro consultar(Integer folio) throws PersistenciaException {
        Retiro retiro = new Retiro();
        String consulta = "SELECT aes_decrypt(contrasena, 'hunter2') as 'contrasena' FROM retiros WHERE folio = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(consulta);) {
            comando.setInt(1, folio);
            ResultSet registro = comando.executeQuery();
            if (registro.next()) {
                retiro.setContrasena(registro.getString("contrasena"));
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return retiro;
    }

    /**
     *
     * Implementación del método actualizar de la interfaz RetiroDAO. Actualiza
     * la fecha de operación de un registro de retiro en la base de datos.
     *
     * @param folio folio del retiro a actualizar.
     * @param contra contraseña del retiro a actualizar.
     * @param fechaA nueva fecha de operación del retiro.
     * @return true si la actualización se realizó correctamente, false en caso
     * contrario.
     * @throws PersistenciaException si ocurre algún error al actualizar el
     * registro.
     */
    @Override
    public boolean actualizar(Integer folio, String contra, String fechaA) throws PersistenciaException {
        String codigoSQL = "update retiros set fecha_operacion = ? where folio = ? and contrasena = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, fechaA);
            comando.setInt(2, folio);
            comando.setString(3, contra);
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible ingresar la fecha al retiro");
        }
    }

    /**
     *
     * Consulta un retiro por su folio.
     *
     * @param folio El folio del retiro a consultar.
     * @return El objeto Retiro correspondiente al folio proporcionado, o null
     * si no se encuentra.
     * @throws PersistenciaException Si ocurre un error durante la conexión a la
     * base de datos.
     */
    @Override
    public Retiro consultarCuenta(Integer folio) throws PersistenciaException {
        Retiro retiro = new Retiro();
        String consulta = "SELECT id_cuenta, monto FROM retiros WHERE folio = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(consulta);) {
            comando.setInt(1, folio);
            ResultSet registro = comando.executeQuery();
            if (registro.next()) {
                retiro.setIdCuenta(registro.getInt("id_cuenta"));
                retiro.setMonto(registro.getDouble("monto"));
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return retiro;
    }

    /**
     *
     * Actualiza el estado de un retiro en la base de datos.
     *
     * @param folio El folio del retiro a actualizar.
     * @param contra La contraseña del retiro a actualizar.
     * @param estado El nuevo estado que se asignará al retiro.
     * @return true si se pudo actualizar el estado del retiro, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error durante la conexión a la
     * base de datos.
     */
    @Override
    public boolean actualizarEstado(Integer folio, String contra, String estado) throws PersistenciaException {
        String codigoSQL = "update retiros set estado = ? where folio = ? and contrasena = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, estado);
            comando.setInt(2, folio);
            comando.setString(3, contra);
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible cambiar al retiro");
        }
    }

    /**
     *
     * Consulta una lista de retiros asociados a un cliente, utilizando
     * paginación.
     *
     * @param configPaginado La configuración de paginación a aplicar.
     * @param idCliente El identificador del cliente asociado a los retiros.
     * @return Una lista de objetos Retiro asociados al cliente proporcionado, o
     * una lista vacía si no se encuentran.
     * @throws PersistenciaException Si ocurre un error durante la conexión a la
     * base de datos.
     */
    @Override
    public List<Retiro> consultarListaR(ConfiguracionPaginado configPaginado, Integer idCliente) throws PersistenciaException {
        String codigoSQL = "SELECT re.folio, re.estado, re.fecha_creacion, re.fecha_operacion, re.monto, re.id_cuenta from retiros re "
                + "INNER JOIN Cuentas AS cu ON re.id_cuenta = cu.id_cuenta "
                + "INNER JOIN Clientes AS cli ON cu.id_cliente = cli.id_cliente "
                + "WHERE cli.id_cliente = ? "
                + "LIMIT ? OFFSET ?;";
        List<Retiro> listaRetiros = new LinkedList<>();
        try (
                Connection conexion = this.GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
            comando.setInt(1, idCliente);
            comando.setInt(2, configPaginado.getElementosPagina());
            comando.setInt(3, configPaginado.getElementosASaltar());
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Integer folio = resultado.getInt("folio");
                String estado = resultado.getString("estado");
                String fechaCreacion = resultado.getString("fecha_creacion");
                String fechaOperacion = resultado.getString("fecha_operacion");
                Double monto = resultado.getDouble("monto");
                Integer idCuenta = resultado.getInt("id_cuenta");
                Retiro retiros = new Retiro(folio, estado, fechaCreacion, fechaOperacion, monto, idCuenta);
                listaRetiros.add(retiros);
            }
            return listaRetiros;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("Error: No fue posible consultar la lista de retiros");
        }
    }
}
