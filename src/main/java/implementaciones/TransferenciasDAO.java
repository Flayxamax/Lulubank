/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Dominio.Transferencia;
import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.ITransferenciasDAO;
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
import javax.swing.JOptionPane;

/**
 *
 * @author ildex
 */
public class TransferenciasDAO implements ITransferenciasDAO {

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
    public TransferenciasDAO(IConexionBD generadorConexiones) {
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }

    /**
     *
     * Método que consulta la lista de transferencias de una cuenta específica
     *
     * @param idCuenta el identificador de la cuenta
     * @return una lista de transferencias asociadas a la cuenta
     * @throws PersistenciaException si ocurre un error al consultar la lista de
     * transferencias
     */
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

    /**
     *
     * Método que registra una transferencia entre cuentas
     *
     * @param idCuenta el identificador de la cuenta origen
     * @param idCuentaDestinatario el identificador de la cuenta destino
     * @param monto el monto de la transferencia
     * @throws PersistenciaException si ocurre un error al registrar la
     * transferencia
     */
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

    /**
     *
     * Método que registra una transferencia entre cuentas utilizando un
     * procedimiento almacenado
     *
     * @param idCuenta el identificador de la cuenta origen
     * @param idCuentaDestinatario el identificador de la cuenta destino
     * @param monto el monto de la transferencia
     * @throws PersistenciaException si ocurre un error al registrar la
     * transferencia
     */
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

    /**
     *
     * Consulta la lista de transferencias realizadas por un cliente en
     * particular utilizando el objeto ConfiguracionPaginado para establecer la
     * cantidad de elementos por página y el elemento a partir del cual se deben
     * mostrar los resultados.
     *
     * @param configPaginado objeto que establece la configuración de paginado
     * @param idCliente identificador del cliente cuyas transferencias se
     * quieren consultar
     * @return una lista de objetos Transferencia que representan las
     * transferencias realizadas por el cliente
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    @Override
    public List<Transferencia> consultarLista(ConfiguracionPaginado configPaginado, Integer idCliente) throws PersistenciaException {
        String codigoSQL = "SELECT "
                + "tra.id_transferencia, tra.fecha_operacion, tra.id_cuenta, tra.monto, tra.id_cuentaDestinatario from transferencias tra "
                + "INNER JOIN Cuentas AS cu ON tra.id_cuenta = cu.id_cuenta "
                + "INNER JOIN Clientes AS cli ON cu.id_cliente = cli.id_cliente "
                + "where cu.id_cliente = ? "
                + "LIMIT ? OFFSET ?;";
        List<Transferencia> listaTransferencias = new LinkedList<>();
        try (
                Connection conexion = this.GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL);) {
            comando.setInt(1, idCliente);
            comando.setInt(2, configPaginado.getElementosPagina());
            comando.setInt(3, configPaginado.getElementosASaltar());
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Integer idTransferencia = resultado.getInt("id_transferencia");
                String fechaOperacion = resultado.getString("fecha_operacion");
                Integer idCuenta = resultado.getInt("id_cuenta");
                Double monto = resultado.getDouble("monto");
                Integer idCuentaDestinatario = resultado.getInt("id_cuentaDestinatario");
                Transferencia transferencia = new Transferencia(idTransferencia, fechaOperacion, idCuenta, monto, idCuentaDestinatario);
                listaTransferencias.add(transferencia);
            }

            return listaTransferencias;
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            throw new PersistenciaException("Error: No fue posible consultar la lista de clientes");
        }
    }

}
