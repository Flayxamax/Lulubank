/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Dominio.Transferencia;
import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.ITransferenciasDAO;
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
import javax.swing.JOptionPane;

/**
 *
 * @author ildex
 */
public class TransferenciasDAO implements ITransferenciasDAO {

    private static final Logger LOG = Logger.getLogger(CuentasDAO.class.getName());
    private final IConexionBD GENERADOR_CONEXIONES;

    public TransferenciasDAO(IConexionBD generadorConexiones) {
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }

    @Override
    public List<Transferencia> consultarListaT(Integer idCuenta) throws PersistenciaException {
        String codigoSQL = "SELECT id_transferencia, fecha_operacion, id_cuenta, monto, id_cuentaDestinatario FROM transferencias where id_cuenta = ?;";
        List<Transferencia> listatransferencias = new LinkedList<>();
        try (
                Connection conexion = this.GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
            comando.setInt(1, idCuenta);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Integer idTransferencia = resultado.getInt("id_transferencias");
                String fechaOperacion = resultado.getString("fecha_operacion");
                Double monto = resultado.getDouble("monto");
                Integer idCuentaDestinatario = resultado.getInt("id_cuentaDestinatario");
                Transferencia transferencia = new Transferencia(idTransferencia, fechaOperacion, idCuenta, monto, idCuentaDestinatario);
                listatransferencias.add(transferencia);
            }
            return listatransferencias;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("Error: No fue posible consultar la lista de transferencias");
        }
    }

    @Override
    public void insertar(Integer idCuenta, Integer idCuentaDestinatario, Double monto) throws PersistenciaException {
        String codigoSQL = "INSERT INTO transferencias(id_cuenta, monto, id_cuentaDestinatario) VALUES (?,?,?)";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setInt(1, idCuenta);
            comando.setDouble(2, monto);
            comando.setInt(3, idCuentaDestinatario);
            comando.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al hacer la transfencia", "LuluAdmin", 0);
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible registrar transferencia");
        }
    }

    @Override
    public void insertarT(Integer idCuenta, Integer idCuentaDestinatario, Double monto) throws PersistenciaException {
        try {
            Connection conexion = GENERADOR_CONEXIONES.crearConexion();
            CallableStatement cstmt = conexion.prepareCall("{call transferencia(?, ?, ?)}");
            cstmt.setInt(1, idCuenta);
            cstmt.setInt(2, idCuentaDestinatario);
            cstmt.setDouble(3, monto);
            cstmt.execute();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible registrar transferencia");
        }
    }

}
