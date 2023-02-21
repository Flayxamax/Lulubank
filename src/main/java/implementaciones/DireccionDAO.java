/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Dominio.Direccion;
import Excepciones.PersistenciaException;
import Interfaces.IConexionBD;
import Interfaces.IDireccionDAO;
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
public class DireccionDAO implements IDireccionDAO {

    private static final Logger LOG = Logger.getLogger(ClientesDAO.class.getName());
    private final IConexionBD GENERADOR_CONEXIONES;

    public DireccionDAO(IConexionBD generadorConexiones) {
        this.GENERADOR_CONEXIONES = generadorConexiones;
    }

    @Override
    public Direccion insertar(Direccion direccion) throws PersistenciaException {
        String codigoSQL = "INSERT INTO direcciones(calle,colonia,"
                + "numero) VALUES (?,?,?)";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, direccion.getCalle());
            comando.setString(2, direccion.getColonia());
            comando.setString(3, direccion.getNumCasa());
            comando.executeUpdate();
            ResultSet llavesGeneradas = comando.getGeneratedKeys();
            if (llavesGeneradas.next()) {
                Integer llavePrimaria = llavesGeneradas.getInt("GENERATED_KEY");
                direccion.setIdDireccion(llavePrimaria);
                return direccion;
            }
            LOG.log(Level.WARNING, "Dirección registrado, pero ID no fue registrado");
            throw new PersistenciaException("Dirección registrado, pero ID no generado");
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue posible registrar Dirección");
        }
    }

    @Override
    public Direccion consultar(Integer idCliente) throws PersistenciaException {
        Direccion direccion = new Direccion();
        String consulta = "SELECT id_direccion, calle, colonia, numero FROM direcciones WHERE id_direccion = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(consulta);) {
            comando.setInt(1, idCliente);
            ResultSet registro = comando.executeQuery();
            if (registro.next()) {
                direccion.setIdDireccion(registro.getInt("id_direccion"));
                direccion.setCalle(registro.getString("calle"));
                direccion.setColonia(registro.getString("colonia"));
                direccion.setNumCasa(registro.getString("numero"));
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return direccion;
    }

    @Override
    public Direccion actualizar(Direccion direccion, Integer idDireccion) throws PersistenciaException {
        String codigoSQL = "update direcciones set calle = ?, colonia = ?, numero = ? where id_direccion = ?";
        try (
                Connection conexion = GENERADOR_CONEXIONES.crearConexion(); PreparedStatement comando = conexion.prepareStatement(
                codigoSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, direccion.getCalle());
            comando.setString(2, direccion.getColonia());
            comando.setString(3, direccion.getNumCasa());
            comando.setInt(4, idDireccion);
            comando.executeUpdate();
            return direccion;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new PersistenciaException("No fue actualizar la dirección");
        }
    }
}
