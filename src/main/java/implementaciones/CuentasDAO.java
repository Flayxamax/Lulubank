/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Dominio.Cuenta;
import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.ICuentasDAO;
import Utils.ConfiguracionPaginado;
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
public class CuentasDAO implements ICuentasDAO {

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
    public CuentasDAO(IConexionBD generadorConexiones) {
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }

    /**
     *
     * Consulta la lista de cuentas bancarias, paginada y ordenada de acuerdo a
     * la configuración dada
     *
     * @param configPaginado Configuración de paginado y ordenamiento de la
     * lista
     * @return Lista de cuentas bancarias que cumple con la configuración de
     * paginado y ordenamiento dada
     * @throws PersistenciaException Si ocurre un error en la consulta a la base
     * de datos
     */
    @Override
    public List<Cuenta> consultarLista(ConfiguracionPaginado configPaginado) throws PersistenciaException {
        String codigoSQL = "SELECT "
                + "id_cuenta, "
                + "saldo, "
                + "fecha_apertura, "
                + "estado, "
                + "id_cliente "
                + "FROM cuentas LIMIT ? OFFSET ?;";
        List<Cuenta> listacuentas = new LinkedList<>();
        try (
                Connection conexion = this.GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
            comando.setInt(1, configPaginado.getElementosPagina());
            comando.setInt(2, configPaginado.getElementosASaltar());
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Integer idCuenta = resultado.getInt("id_cuenta");
                Double saldo = resultado.getDouble("saldo");
                String fecha_apertura = resultado.getString("fecha_apertura");
                String estado = resultado.getString("estado");
                Integer idCliente = resultado.getInt("id_cliente");
                Cuenta cuenta = new Cuenta(idCuenta, saldo, fecha_apertura, estado, idCliente);
                listacuentas.add(cuenta);
            }
            return listacuentas;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("Error: No fue posible consultar la lista de cuentas");
        }
    }

    /**
     *
     * Consulta la información de una cuenta bancaria con un idCuenta dado
     *
     * @param idCuenta Identificador de la cuenta bancaria que se desea
     * consultar
     * @return Objeto de tipo Cuenta con la información de la cuenta bancaria
     * consultada
     * @throws PersistenciaException Si ocurre un error en la consulta a la base
     * de datos
     */
    @Override
    public List<Cuenta> consultarListaC(Integer idCuenta) throws PersistenciaException {
        String codigoSQL = "SELECT saldo, fecha_apertura, estado FROM cuentas where id_cuenta = ?;";
        List<Cuenta> listacuentas = new LinkedList<>();
        try (
                Connection conexion = this.GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
            comando.setInt(1, idCuenta);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Double saldo = resultado.getDouble("saldo");
                String fecha_apertura = resultado.getString("fecha_apertura");
                String estado = resultado.getString("estado");
                Cuenta cuenta = new Cuenta(saldo, fecha_apertura, estado);
                listacuentas.add(cuenta);
            }
            return listacuentas;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("Error: No fue posible consultar la lista de cuentas");
        }
    }

    /**
     *
     * Este método consulta una cuenta por su ID.
     *
     * @param idCuenta el ID de la cuenta a consultar.
     * @return la cuenta encontrada, o una cuenta vacía si no se encontró
     * ninguna.
     * @throws PersistenciaException si ocurre un error al consultar la cuenta
     * en la base de datos.
     */
    @Override
    public Cuenta consultar(Integer idCuenta) throws PersistenciaException {
        Cuenta cuenta = new Cuenta();
        String consulta = "SELECT id_cuenta, saldo, fecha_apertura, estado, id_cliente FROM cuentas WHERE id_cuenta = ? and estado = 'activa'";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(consulta);) {
            comando.setInt(1, idCuenta);
            ResultSet registro = comando.executeQuery();
            if (registro.next()) {
                cuenta.setIdCuenta(registro.getInt("id_cuenta"));
                cuenta.setSaldo(registro.getDouble("saldo"));
                cuenta.setFechaApertura(registro.getString("fecha_apertura"));
                cuenta.setEstado(registro.getString("estado"));
                cuenta.setIdCliente(registro.getInt("id_cliente"));
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return cuenta;
    }

    /**
     *
     * Este método inserta una nueva cuenta para un cliente específico.
     *
     * @param idCliente el ID del cliente al que se asociará la nueva cuenta.
     * @return null.
     * @throws PersistenciaException si ocurre un error al insertar la cuenta en
     * la base de datos.
     */
    @Override
    public Cuenta insertar(Integer idCliente) throws PersistenciaException {
        String codigoSQL = "INSERT INTO cuentas(id_cliente) VALUES (?)";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setInt(1, idCliente);
            comando.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible registrar cuenta");
        }
        return null;
    }

    /**
     *
     * Este método actualiza el estado de una cuenta a "desactivada".
     *
     * @param idCuenta el ID de la cuenta a desactivar.
     * @throws PersistenciaException si ocurre un error al actualizar el estado
     * de la cuenta en la base de datos.
     */
    @Override
    public void actualizarEstado(Integer idCuenta) throws PersistenciaException {
        String codigoSQL = "update cuentas set estado = 'desactivada' where id_cuenta = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setInt(1, idCuenta);
            comando.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible cambiar el estado a la cuenta");
        }
    }

    /**
     *
     * Este método actualiza el saldo de una cuenta en un monto específico.
     *
     * @param idCuenta el ID de la cuenta a actualizar.
     * @param saldo el monto a agregar al saldo de la cuenta.
     * @return null.
     * @throws PersistenciaException si ocurre un error al actualizar el saldo
     * de la cuenta en la base de datos.
     */
    @Override
    public Cuenta actualizarMonto(Integer idCuenta, Double saldo) throws PersistenciaException {
        String codigoSQL = "update cuentas set saldo=saldo+? where id_cuenta = ? and estado = 'activa'";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setDouble(1, saldo);
            comando.setInt(2, idCuenta);
            comando.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible agregar saldo.");
        }
        return null;
    }

    /**
     *
     * Este método consulta los IDs de las cuentas activas asociadas a un
     * cliente especificado.
     *
     * @param idCliente el ID del cliente para el cual se quieren consultar las
     * cuentas activas.
     * @return un arreglo de enteros con los IDs de las cuentas activas
     * asociadas al cliente.
     * @throws PersistenciaException si ocurre algún error al realizar la
     * consulta.
     */
    @Override
    public int[] consultarIdsCuentas(Integer idCliente) throws PersistenciaException {
        String codigoSQL = "SELECT id_cuenta FROM cuentas WHERE id_cliente = ? AND estado = 'activa'";
        List<Integer> listaIdsCuentas = new LinkedList<>();
        try (Connection conexion = this.GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL)) {
            comando.setInt(1, idCliente);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Integer idCuenta = resultado.getInt("id_cuenta");
                listaIdsCuentas.add(idCuenta);
            }
            int[] arregloIdsCuentas = new int[listaIdsCuentas.size()];
            for (int i = 0; i < listaIdsCuentas.size(); i++) {
                arregloIdsCuentas[i] = listaIdsCuentas.get(i);
            }
            return arregloIdsCuentas;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("Error al consultar la lista de cuentas activas");
        }
    }

    /**
     *
     * Este método actualiza el saldo de una cuenta especificada, descontando un
     * monto determinado.
     *
     * @param idCuenta el ID de la cuenta a actualizar.
     * @param saldo el monto a descontar del saldo actual de la cuenta.
     * @return la cuenta actualizada.
     * @throws PersistenciaException si ocurre algún error al realizar la
     * actualización.
     */
    @Override
    public Cuenta actualizarDescMonto(Integer idCuenta, Double saldo) throws PersistenciaException {
        String codigoSQL = "update cuentas set saldo=saldo-? where id_cuenta = ? and estado = 'activa'";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setDouble(1, saldo);
            comando.setInt(2, idCuenta);
            comando.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible descontar saldo.");
        }
        return null;
    }
}
