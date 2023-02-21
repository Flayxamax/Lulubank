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

    public RetirosDAO(IConexionBD generadorConexiones) {
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }

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

    @Override
    public List<Retiro> consultarListaR(ConfiguracionPaginado configPaginado) throws PersistenciaException {
        String codigoSQL = "SELECT "
                + "folio, "
                + "estado, "
                + "fecha_creacion, "
                + "fecha_operacion, "
                + "monto,"
                + "id_cuenta"
                + "FROM retiros LIMIT ? OFFSET ?;";
        List<Retiro> listaRetiros = new LinkedList<>();
        try (
                Connection conexion = this.GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
            comando.setInt(1, configPaginado.getElementosPagina());
            comando.setInt(2, configPaginado.getElementosASaltar());
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
