/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Dominio.Retiro;
import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IRetirosDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    public Retiro insertar(Integer idCuenta, Double monto) throws PersistenciaException {
        try {
            Connection conexion = GENERADOR_CONEXIONES.crearConexion();
            CallableStatement cstmt = conexion.prepareCall("{call retiro(?, ?)}");
            cstmt.setInt(1, idCuenta);
            cstmt.setDouble(2, monto);
            cstmt.execute();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible registrar transferencia");
        }
        return null;
    }

    @Override
    public List<Retiro> consultarLista(Integer idCuenta) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
