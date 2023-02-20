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

    public CuentasDAO(IConexionBD generadorConexiones) {
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }

    @Override
    public List<Cuenta> consultarLista(ConfiguracionPaginado configPaginado) throws PersistenciaException {
        String codigoSQL = "SELECT "
                + "id_cuenta, "
                + "saldo, "
                + "fecha_apertura, "
                + "estado, "
                + "id_cliente "
                + "FROM cuentas LIMIT ? OFFSET ?;";
        List<Cuenta> listacuentas = new LinkedList<>(); // puede ser ArrayList
        try (
                Connection conexion = this.GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
            comando.setInt(1, configPaginado.getElementosPagina());
            comando.setInt(2, configPaginado.getElementosASaltar());
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) { // Se utiliza while porque no se sabe la cantidad específica de elementos. Cuando sí se conoce, se usa for
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
            LOG.log(Level.SEVERE, ex.getMessage()); // Sustituye los System.err
            throw new PersistenciaException("Error: No fue posible consultar la lista de cuentas");
        }
    }

    @Override
    public Cuenta consultar(Integer idCuenta) throws PersistenciaException {
        Cuenta cuenta = new Cuenta();
        String consulta = "SELECT id_cuenta, saldo, fecha_apertura, estado, id_cliente FROM cuentas WHERE id_cuenta = ?";
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

    @Override
    public void actualizarEstado(Cuenta cuenta) throws PersistenciaException {
        String codigoSQL = "update cuentas set estado = ? where id_cuenta = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, cuenta.getEstado());
            comando.executeUpdate();
            LOG.log(Level.WARNING, "cuenta registrado, pero ID no fue registrado");
            throw new PersistenciaException("cuenta registrado, pero ID no generado");
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible registrar cuenta");
        }
    }

    @Override
    public Cuenta actualizarMonto(Integer idCuenta, Double saldo) throws PersistenciaException {
        String codigoSQL = "update cuentas set saldo=saldo+? where id_cuenta = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setDouble(1, saldo);
            comando.setInt(2,idCuenta);
            comando.executeUpdate();           
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible agregar saldo.");
        }
        return null;
    }
    
    @Override
    public List<Cuenta> consultarCuentas(Integer idCliente) throws PersistenciaException {
      String codigoSQL = "select id_cuenta from cuentas where id_cliente = ?";
       List<Cuenta> listaCuentas = new LinkedList<>();
        try (Connection conexion = this.GENERADOR_CONEXIONES.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
            comando.setInt(1, idCliente);
             ResultSet resultado = comando.executeQuery();
              while (resultado.next()) {
                  Integer idCuenta = resultado.getInt("id_cuenta");
                  Cuenta cuenta = new Cuenta(idCuenta);
                  listaCuentas.add(cuenta);
              }
              return listaCuentas;
        }catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("No fue posible consultar la lista de clientes");
        }

    }
}