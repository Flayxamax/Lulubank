/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.lulubank;

import Interfaces.IClientesDAO;
import Interfaces.IConexionBD;
import Interfaces.ICuentasDAO;
import Interfaces.IDireccionDAO;
import Interfaces.IRetirosDAO;
import Interfaces.ITransferenciasDAO;
import UI.inicio;
import implementaciones.ClientesDAO;
import implementaciones.ConexionBD;
import implementaciones.CuentasDAO;
import implementaciones.DireccionDAO;
import implementaciones.RetirosDAO;
import implementaciones.TransferenciasDAO;
import java.util.logging.Logger;

/**
 *
 * @author ildex
 */
public class Lulubank {
    private static final Logger LOG = Logger.getLogger(ClientesDAO.class.getName());

    public static void main(String[] args) {
        IConexionBD generadorConexiones = new ConexionBD(
                "jdbc:mysql://localhost/lulubank",
                "root",
                "1234");
        IClientesDAO clientesDAO = new ClientesDAO(generadorConexiones);
        IDireccionDAO direccionDAO = new DireccionDAO(generadorConexiones);
        ICuentasDAO cuentasDAO = new CuentasDAO(generadorConexiones);
        ITransferenciasDAO transferenciasDAO = new TransferenciasDAO(generadorConexiones);
        IRetirosDAO retirosDAO = new RetirosDAO(generadorConexiones);
        new inicio(clientesDAO, direccionDAO, cuentasDAO, transferenciasDAO, retirosDAO).setVisible(true);
    }
}
