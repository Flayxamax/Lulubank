/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Dominio.Cliente;
import Dominio.Cuenta;
import Dominio.Retiro;
import Dominio.Transferencia;
import Excepciones.PersistenciaException;
import Interfaces.IClientesDAO;
import Interfaces.ICuentasDAO;
import Interfaces.IDireccionDAO;
import Interfaces.IRetirosDAO;
import Interfaces.ITransferenciasDAO;
import Utils.ConfiguracionPaginado;
import Utils.Validadores;
import implementaciones.CuentasDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ildex
 */
public class Operaciones extends javax.swing.JFrame {

    private static final Logger LOG = Logger.getLogger(CuentasDAO.class.getName());
    private final IClientesDAO clientesDAO;
    private final IDireccionDAO direccionDAO;
    private final ICuentasDAO cuentasDAO;
    private final ITransferenciasDAO transferenciasDAO;
    private final IRetirosDAO retirosDAO;
    private final String correo;
    private final ConfiguracionPaginado configPaginado;
    private final Validadores validadores = new Validadores();
    private int xMouse;
    private int yMouse;

    /**
     * Creates new form Operaciones
     *
     * @param clientesDAO
     * @param direccionDAO
     * @param correo
     * @param cuentasDAO
     * @throws Excepciones.PersistenciaException
     */
    public Operaciones(IClientesDAO clientesDAO, IDireccionDAO direccionDAO, String correo, ICuentasDAO cuentasDAO, ITransferenciasDAO transferenciasDAO, IRetirosDAO retirosDAO) throws PersistenciaException {
        this.clientesDAO = clientesDAO;
        this.direccionDAO = direccionDAO;
        this.correo = correo;
        this.cuentasDAO = cuentasDAO;
        this.transferenciasDAO = transferenciasDAO;
        this.retirosDAO = retirosDAO;
        this.configPaginado= new ConfiguracionPaginado(0, 3);
        initComponents();
        Cliente clienteLogueado = this.clientesDAO.consultar(correo);
        lblCliente.setText("Cliente: " + clienteLogueado.getNombre() + (" ") + clienteLogueado.getApellidoPaterno() + (" ") + clienteLogueado.getApellidoMaterno());
        comboCuenta();
        comboCuenta2();
        panelCuentaV();
        panelDepositoV();
        panelRetiroV();
        panelTransferenciaV();
        panelHistorialV();
    }

    private void panelCuentaV() {
        //Panel cuenta
        cbxCuentas.setVisible(false);
        btnCrear.setVisible(false);
        lblCrear.setVisible(false);
        lblTitulo.setVisible(false);
        jsc1.setVisible(false);
        tblCuentas.setVisible(false);
        btnBorrar.setVisible(false);
        lblBorrar.setVisible(false);
        txtMonto.setVisible(false);
    }

    private void panelDepositoV() {
        //Panel depósito
        txtCuenta.setVisible(false);
        lblTituloD.setVisible(false);
        lblCuentaD.setVisible(false);
        jSp2.setVisible(false);
        jSp1.setVisible(false);
        lblSaldo.setVisible(false);
        txtSaldo.setVisible(false);
        btnDepositar.setVisible(false);
        lblDepositar.setVisible(false);
    }

    private void panelRetiroV() {
        //Panel Retiro
        lblKk2.setVisible(false);
        txtFolio.setVisible(false);
        lblFolio.setVisible(false);
        jSp3.setVisible(false);
        lblContrasena.setVisible(false);
        jSp4.setVisible(false);
        btnRetirar.setVisible(false);
        lblRetirar.setVisible(false);
        txtPass.setVisible(false);
    }

    private void panelTransferenciaV() {
        //Panel transferencia
        lblTitulo1.setVisible(false);
        lblCuentaDes.setVisible(false);
        txtCuentaD.setVisible(false);
        jSp7.setVisible(false);
        lblCuentaO.setVisible(false);
        txtMontoO.setVisible(false);
        jSp5.setVisible(false);
        lblMontoO.setVisible(false);
        jSp6.setVisible(false);
        lblDineroImg.setVisible(false);
        btnTransferir.setVisible(false);
        lblTransferir.setVisible(false);
        cbxCuentas1.setVisible(false);
    }

    private void panelHistorialV() {
        //Panel historial
        lblHisto.setVisible(false);
        tblTransferencia.setVisible(false);
        btnAnterior.setVisible(false);
        cbxElementosPágina.setVisible(false);
        btnSiguiente.setVisible(false);
        btnAnterior1.setVisible(false);
        cbxElementosPágina1.setVisible(false);
        btnSiguiente1.setVisible(false);
    }

    public void crearCuenta() {
        try {
            Cliente clienteLogueado = this.clientesDAO.consultar(correo);
            Cuenta cuentaCreada = this.cuentasDAO.insertar(clienteLogueado.getIdCliente());
        } catch (PersistenciaException e) {
            this.mostrarMensajeErrorCuentaCreada();
        }
    }

    private void mostrarMensajeErrorCuentaCreada() {
        JOptionPane.showMessageDialog(this, "No fue posible crear una cuenta", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private Retiro extraerDatosRetiro() {
        String folio = this.txtFolio.getText();
        String contrasena = this.txtPass.getText();
        Retiro retiro = new Retiro(Integer.parseInt(folio), contrasena);
        return retiro;
    }

    private Cuenta extraerDatosFrmDeposito() {
        String idCuenta = this.txtCuenta.getText();
        String deposito = this.txtSaldo.getText();
        Cuenta cuenta = new Cuenta(Integer.parseInt(idCuenta), Double.parseDouble(deposito));
        return cuenta;
    }

//    private Retiro retirar() throws PersistenciaException{
//        Retiro retiro = this.extraerDatosRetiro();
////        Retiro retiroGuardado = this.retirosDAO.insertar(retiro.getFolio(), retiro.getContrasena());
//        return retiroGuardado;
//    }
    private Cuenta depositar() throws PersistenciaException {
        Cuenta cuenta = this.extraerDatosFrmDeposito();
        Cuenta cuentaGuardada = this.cuentasDAO.actualizarMonto(cuenta.getIdCuenta(), cuenta.getSaldo());
        return cuentaGuardada;
    }

    private void comboCuenta() throws PersistenciaException {
        Cliente clienteLogueado = this.clientesDAO.consultar(correo);
        int[] idsCuentas = null;
        idsCuentas = this.cuentasDAO.consultarIdsCuentas(clienteLogueado.getIdCliente());
        cbxCuentas.removeAllItems();
        for (int i = 0; i < idsCuentas.length; i++) {
            cbxCuentas.addItem(Integer.toString(idsCuentas[i]));
        }
    }

    private void cargarTablaCuentas(Integer idCuenta) {
        try {
            List<Cuenta> listaCuentas = this.cuentasDAO.consultarListaC(idCuenta);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tblCuentas.getModel();
            modeloTabla.setRowCount(0);
            for (Cuenta cuenta : listaCuentas) {
                Object[] fila = {
                    cuenta.getSaldo(),
                    cuenta.getFechaApertura(),
                    cuenta.getEstado()
                };
                modeloTabla.addRow(fila);
            }
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }

    private void transferir() throws PersistenciaException {
        Integer cuentaD = Integer.valueOf(txtCuentaD.getText());
        Double monto = Double.valueOf(txtMontoO.getText());
        int cuenta = Integer.parseInt((String) cbxCuentas1.getSelectedItem());
        this.transferenciasDAO.insertar(cuenta, cuentaD, monto);
        this.transferenciasDAO.insertarT(cuenta, cuentaD, monto);
    }

    private void comboCuenta2() throws PersistenciaException {
        Cliente clienteLogueado = this.clientesDAO.consultar(correo);
        int[] idsCuentas = null;
        idsCuentas = this.cuentasDAO.consultarIdsCuentas(clienteLogueado.getIdCliente());
        cbxCuentas1.removeAllItems();
        for (int i = 0; i < idsCuentas.length; i++) {
            cbxCuentas1.addItem(Integer.toString(idsCuentas[i]));
        }
    }
    
    private void cargarTablaTransferencias(){
        try {
            List<Transferencia> listaTransferencias = this.transferenciasDAO.consultarLista(configPaginado);
            DefaultTableModel modeloTabla = (DefaultTableModel)this.tblTransferencia.getModel();
            modeloTabla.setRowCount(0);
            for(Transferencia transferencia: listaTransferencias){
                Object[] fila = {
                        transferencia.getId_transferencia(),
                        transferencia.getFecha_operacion(),
                        transferencia.getMonto(),
                        transferencia.getIdCuentaDestinatario(),
                        transferencia.getIdCuenta(),
                };
                modeloTabla.addRow(fila);
            }
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }
    
    private void avanzarPaginaT(){
        this.configPaginado.avanzarPagina();
        this.cargarTablaTransferencias();
    }
    
    private void retrocederPaginaT(){
        this.configPaginado.retrocederPagina();
        this.cargarTablaTransferencias();
    }
    
    private void cargarTablaRetiros(){
        try {
            List<Retiro> listaRetiros = this.retirosDAO.consultarListaR(configPaginado);
            DefaultTableModel modeloTabla = (DefaultTableModel)this.tblRetiros.getModel();
            modeloTabla.setRowCount(0);
            for(Retiro retiro: listaRetiros){
                Object[] fila = {
                        retiro.getFolio(),
                        retiro.getEstado(),
                        retiro.getFecha_creacion(),
                        retiro.getFecha_operacion(),
                        retiro.getMonto(),
                        retiro.getIdCuenta(),
                };
                modeloTabla.addRow(fila);
            }
        } catch (PersistenciaException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }
    
    private void avanzarPaginaR(){
        this.configPaginado.avanzarPagina();
        this.cargarTablaRetiros();
    }
    
    private void retrocederPaginaR(){
        this.configPaginado.retrocederPagina();
        this.cargarTablaRetiros();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JPanel();
        lblRegresar = new javax.swing.JLabel();
        btnSalir = new javax.swing.JPanel();
        lblX = new javax.swing.JLabel();
        barraIzq = new javax.swing.JPanel();
        btnHistorial = new javax.swing.JPanel();
        lblHistorial = new javax.swing.JLabel();
        btnRetiroT = new javax.swing.JPanel();
        lblRetiro = new javax.swing.JLabel();
        btnDeposito = new javax.swing.JPanel();
        lblDeposito = new javax.swing.JLabel();
        btnTransferencia = new javax.swing.JPanel();
        lblTransferencia = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnCuenta = new javax.swing.JPanel();
        lblCuenta = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        barraTop = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelContenido = new javax.swing.JPanel();
        lblKk = new javax.swing.JLabel();
        panelCuenta = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        cbxCuentas = new javax.swing.JComboBox<>();
        btnGenre = new javax.swing.JPanel();
        lblGenre = new javax.swing.JLabel();
        btnCrear = new javax.swing.JPanel();
        lblCrear = new javax.swing.JLabel();
        jsc1 = new javax.swing.JScrollPane();
        tblCuentas = new javax.swing.JTable();
        btnBorrar = new javax.swing.JPanel();
        lblBorrar = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jSp8 = new javax.swing.JSeparator();
        panelTransferencia = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        lblCuentaDes = new javax.swing.JLabel();
        txtCuentaD = new javax.swing.JTextField();
        jSp7 = new javax.swing.JSeparator();
        lblCuentaO = new javax.swing.JLabel();
        txtMontoO = new javax.swing.JTextField();
        jSp5 = new javax.swing.JSeparator();
        lblMontoO = new javax.swing.JLabel();
        jSp6 = new javax.swing.JSeparator();
        lblDineroImg = new javax.swing.JLabel();
        btnTransferir = new javax.swing.JPanel();
        lblTransferir = new javax.swing.JLabel();
        cbxCuentas1 = new javax.swing.JComboBox<>();
        panelDeposito = new javax.swing.JPanel();
        txtCuenta = new javax.swing.JTextField();
        lblTituloD = new javax.swing.JLabel();
        lblCuentaD = new javax.swing.JLabel();
        jSp2 = new javax.swing.JSeparator();
        lblSaldo = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        jSp1 = new javax.swing.JSeparator();
        btnDepositar = new javax.swing.JPanel();
        lblDepositar = new javax.swing.JLabel();
        panelRetiro = new javax.swing.JPanel();
        lblKk2 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        lblFolio = new javax.swing.JLabel();
        jSp3 = new javax.swing.JSeparator();
        lblContrasena = new javax.swing.JLabel();
        jSp4 = new javax.swing.JSeparator();
        btnRetirar = new javax.swing.JPanel();
        lblRetirar = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        panelHistorial = new javax.swing.JPanel();
        lblHisto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransferencia = new javax.swing.JTable();
        btnAnterior1 = new javax.swing.JButton();
        cbxElementosPágina1 = new javax.swing.JComboBox<>();
        btnSiguiente1 = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        cbxElementosPágina = new javax.swing.JComboBox<>();
        btnSiguiente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRetiros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresarMouseExited(evt);
            }
        });

        lblRegresar.setText("<");
        lblRegresar.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 16)); // NOI18N
        lblRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblRegresarMouseEntered(evt);
            }
        });
        btnRegresar.add(lblRegresar);

        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalirMouseExited(evt);
            }
        });

        lblX.setText("X");
        lblX.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 18)); // NOI18N
        btnSalir.add(lblX);

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, -1));

        barraIzq.setBackground(new java.awt.Color(102, 102, 255));
        barraIzq.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHistorial.setBackground(new java.awt.Color(102, 102, 255));
        btnHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHistorialMouseClicked(evt);
            }
        });

        lblHistorial.setFont(new java.awt.Font("Franklin Gothic Book", 0, 20)); // NOI18N
        lblHistorial.setForeground(new java.awt.Color(255, 255, 255));
        lblHistorial.setText("Historial");

        javax.swing.GroupLayout btnHistorialLayout = new javax.swing.GroupLayout(btnHistorial);
        btnHistorial.setLayout(btnHistorialLayout);
        btnHistorialLayout.setHorizontalGroup(
            btnHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHistorialLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblHistorial)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        btnHistorialLayout.setVerticalGroup(
            btnHistorialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHistorialLayout.createSequentialGroup()
                .addGap(0, 26, Short.MAX_VALUE)
                .addComponent(lblHistorial))
        );

        barraIzq.add(btnHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 160, 50));

        btnRetiroT.setBackground(new java.awt.Color(102, 102, 255));
        btnRetiroT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRetiroT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetiroTMouseClicked(evt);
            }
        });

        lblRetiro.setFont(new java.awt.Font("Franklin Gothic Book", 0, 20)); // NOI18N
        lblRetiro.setForeground(new java.awt.Color(255, 255, 255));
        lblRetiro.setText("Retiro");

        javax.swing.GroupLayout btnRetiroTLayout = new javax.swing.GroupLayout(btnRetiroT);
        btnRetiroT.setLayout(btnRetiroTLayout);
        btnRetiroTLayout.setHorizontalGroup(
            btnRetiroTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRetiroTLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblRetiro)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        btnRetiroTLayout.setVerticalGroup(
            btnRetiroTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRetiroTLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(lblRetiro)
                .addContainerGap())
        );

        barraIzq.add(btnRetiroT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 160, 50));

        btnDeposito.setBackground(new java.awt.Color(102, 102, 255));
        btnDeposito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeposito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDepositoMouseClicked(evt);
            }
        });

        lblDeposito.setFont(new java.awt.Font("Franklin Gothic Book", 0, 20)); // NOI18N
        lblDeposito.setForeground(new java.awt.Color(255, 255, 255));
        lblDeposito.setText("Depósito");

        javax.swing.GroupLayout btnDepositoLayout = new javax.swing.GroupLayout(btnDeposito);
        btnDeposito.setLayout(btnDepositoLayout);
        btnDepositoLayout.setHorizontalGroup(
            btnDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDepositoLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(lblDeposito)
                .addGap(42, 42, 42))
        );
        btnDepositoLayout.setVerticalGroup(
            btnDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDepositoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDeposito)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        barraIzq.add(btnDeposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 160, 50));

        btnTransferencia.setBackground(new java.awt.Color(102, 102, 255));
        btnTransferencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTransferencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransferenciaMouseClicked(evt);
            }
        });

        lblTransferencia.setFont(new java.awt.Font("Franklin Gothic Book", 0, 20)); // NOI18N
        lblTransferencia.setForeground(new java.awt.Color(255, 255, 255));
        lblTransferencia.setText("Transferencia");

        javax.swing.GroupLayout btnTransferenciaLayout = new javax.swing.GroupLayout(btnTransferencia);
        btnTransferencia.setLayout(btnTransferenciaLayout);
        btnTransferenciaLayout.setHorizontalGroup(
            btnTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTransferenciaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        btnTransferenciaLayout.setVerticalGroup(
            btnTransferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTransferenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTransferencia)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        barraIzq.add(btnTransferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 160, 50));
        barraIzq.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 140, 10));

        btnCuenta.setBackground(new java.awt.Color(102, 102, 255));
        btnCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCuentaMouseClicked(evt);
            }
        });

        lblCuenta.setFont(new java.awt.Font("Franklin Gothic Book", 0, 20)); // NOI18N
        lblCuenta.setForeground(new java.awt.Color(255, 255, 255));
        lblCuenta.setText("Cuenta");

        javax.swing.GroupLayout btnCuentaLayout = new javax.swing.GroupLayout(btnCuenta);
        btnCuenta.setLayout(btnCuentaLayout);
        btnCuentaLayout.setHorizontalGroup(
            btnCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCuentaLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblCuenta)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        btnCuentaLayout.setVerticalGroup(
            btnCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnCuentaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCuenta)
                .addGap(34, 34, 34))
        );

        barraIzq.add(btnCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 160, 50));
        barraIzq.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, 10));
        barraIzq.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 140, 10));
        barraIzq.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 140, 10));
        barraIzq.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 140, 10));

        getContentPane().add(barraIzq, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 160, 500));

        barraTop.setBackground(new java.awt.Color(204, 204, 255));

        lblCliente.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Editar Cliente");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout barraTopLayout = new javax.swing.GroupLayout(barraTop);
        barraTop.setLayout(barraTopLayout);
        barraTopLayout.setHorizontalGroup(
            barraTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                .addGap(67, 67, 67)
                .addComponent(jLabel1)
                .addGap(33, 33, 33))
        );
        barraTopLayout.setVerticalGroup(
            barraTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraTopLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(barraTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        getContentPane().add(barraTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 120));

        panelContenido.setBackground(new java.awt.Color(255, 255, 255));
        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblKk.setFont(new java.awt.Font("Eras Medium ITC", 0, 48)); // NOI18N
        lblKk.setText("Contenido");
        panelContenido.add(lblKk, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 25, -1, -1));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 680, 500));

        panelCuenta.setBackground(new java.awt.Color(255, 255, 255));
        panelCuenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Eras Medium ITC", 0, 48)); // NOI18N
        lblTitulo.setText("Cuenta");
        panelCuenta.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        cbxCuentas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCuentasItemStateChanged(evt);
            }
        });
        cbxCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCuentasActionPerformed(evt);
            }
        });
        panelCuenta.add(cbxCuentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 170, -1));

        btnGenre.setBackground(new java.awt.Color(51, 102, 255));
        btnGenre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGenreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGenreMouseExited(evt);
            }
        });

        lblGenre.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblGenre.setForeground(new java.awt.Color(255, 255, 255));
        lblGenre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGenre.setText("GENERAR RETIRO");
        lblGenre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblGenre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGenreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGenreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblGenreMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnGenreLayout = new javax.swing.GroupLayout(btnGenre);
        btnGenre.setLayout(btnGenreLayout);
        btnGenreLayout.setHorizontalGroup(
            btnGenreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblGenre, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        btnGenreLayout.setVerticalGroup(
            btnGenreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblGenre, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        panelCuenta.add(btnGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 170, 60));

        btnCrear.setBackground(new java.awt.Color(51, 102, 255));
        btnCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrearMouseExited(evt);
            }
        });

        lblCrear.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblCrear.setForeground(new java.awt.Color(255, 255, 255));
        lblCrear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCrear.setText("CREAR CUENTA");
        lblCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCrearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCrearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCrearMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnCrearLayout = new javax.swing.GroupLayout(btnCrear);
        btnCrear.setLayout(btnCrearLayout);
        btnCrearLayout.setHorizontalGroup(
            btnCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCrear, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        btnCrearLayout.setVerticalGroup(
            btnCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCrear, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        panelCuenta.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 170, 60));

        tblCuentas.setFont(new java.awt.Font("Franklin Gothic Book", 0, 14)); // NOI18N
        tblCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Saldo", "Fecha de apertura", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCuentas.getTableHeader().setReorderingAllowed(false);
        jsc1.setViewportView(tblCuentas);

        panelCuenta.add(jsc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, 40));

        btnBorrar.setBackground(new java.awt.Color(255, 51, 51));
        btnBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBorrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBorrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBorrarMouseExited(evt);
            }
        });

        lblBorrar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblBorrar.setForeground(new java.awt.Color(255, 255, 255));
        lblBorrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBorrar.setText("BORRAR CUENTA");
        lblBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBorrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBorrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBorrarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnBorrarLayout = new javax.swing.GroupLayout(btnBorrar);
        btnBorrar.setLayout(btnBorrarLayout);
        btnBorrarLayout.setHorizontalGroup(
            btnBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        btnBorrarLayout.setVerticalGroup(
            btnBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        panelCuenta.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 170, 60));

        lblMonto.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(0, 0, 0));
        lblMonto.setText("Monto");
        panelCuenta.add(lblMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 130, -1));

        txtMonto.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtMonto.setBorder(null);
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });
        panelCuenta.add(txtMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 130, 16));

        jSp8.setForeground(new java.awt.Color(0, 0, 0));
        panelCuenta.add(jSp8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 130, 10));

        getContentPane().add(panelCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 680, 500));

        panelTransferencia.setBackground(new java.awt.Color(255, 255, 255));
        panelTransferencia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo1.setFont(new java.awt.Font("Eras Medium ITC", 0, 48)); // NOI18N
        lblTitulo1.setText("Transferencia");
        panelTransferencia.add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 290, -1));

        lblCuentaDes.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        lblCuentaDes.setText("Cuenta destino");
        panelTransferencia.add(lblCuentaDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 240, -1));

        txtCuentaD.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtCuentaD.setBorder(null);
        txtCuentaD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuentaDKeyTyped(evt);
            }
        });
        panelTransferencia.add(txtCuentaD, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 130, 16));

        jSp7.setForeground(new java.awt.Color(0, 0, 0));
        panelTransferencia.add(jSp7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 270, 130, 10));

        lblCuentaO.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        lblCuentaO.setText("Cuenta origen");
        panelTransferencia.add(lblCuentaO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 220, -1));

        txtMontoO.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtMontoO.setBorder(null);
        txtMontoO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoOKeyTyped(evt);
            }
        });
        panelTransferencia.add(txtMontoO, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 130, 16));

        jSp5.setForeground(new java.awt.Color(0, 0, 0));
        panelTransferencia.add(jSp5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 130, 10));

        lblMontoO.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        lblMontoO.setText("Monto");
        panelTransferencia.add(lblMontoO, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 160, -1));

        jSp6.setForeground(new java.awt.Color(0, 0, 0));
        panelTransferencia.add(jSp6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 130, 10));

        lblDineroImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/money (Custom).png"))); // NOI18N
        panelTransferencia.add(lblDineroImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, -1));

        btnTransferir.setBackground(new java.awt.Color(51, 102, 255));
        btnTransferir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTransferir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTransferirbtnDepositar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTransferirbtnDepositar1MouseExited(evt);
            }
        });

        lblTransferir.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTransferir.setForeground(new java.awt.Color(255, 255, 255));
        lblTransferir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransferir.setText("TRANSFERIR");
        lblTransferir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblTransferir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTransferirlblDepositar1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTransferirlblDepositar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTransferirlblDepositar1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnTransferirLayout = new javax.swing.GroupLayout(btnTransferir);
        btnTransferir.setLayout(btnTransferirLayout);
        btnTransferirLayout.setHorizontalGroup(
            btnTransferirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnTransferirLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnTransferirLayout.setVerticalGroup(
            btnTransferirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTransferir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        panelTransferencia.add(btnTransferir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 210, 60));

        cbxCuentas1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCuentas1ItemStateChanged(evt);
            }
        });
        cbxCuentas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCuentas1ActionPerformed(evt);
            }
        });
        panelTransferencia.add(cbxCuentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 130, -1));

        getContentPane().add(panelTransferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 680, 500));

        panelDeposito.setBackground(new java.awt.Color(255, 255, 255));
        panelDeposito.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCuenta.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtCuenta.setBorder(null);
        txtCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuentaKeyTyped(evt);
            }
        });
        panelDeposito.add(txtCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 130, 16));

        lblTituloD.setFont(new java.awt.Font("Eras Medium ITC", 0, 48)); // NOI18N
        lblTituloD.setText("Depósito");
        panelDeposito.add(lblTituloD, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        lblCuentaD.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        lblCuentaD.setText("Cuenta");
        panelDeposito.add(lblCuentaD, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, -1, -1));

        jSp2.setForeground(new java.awt.Color(0, 0, 0));
        panelDeposito.add(jSp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 130, 10));

        lblSaldo.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        lblSaldo.setText("Saldo");
        panelDeposito.add(lblSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        txtSaldo.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtSaldo.setBorder(null);
        txtSaldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaldoKeyTyped(evt);
            }
        });
        panelDeposito.add(txtSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 130, 16));

        jSp1.setForeground(new java.awt.Color(0, 0, 0));
        panelDeposito.add(jSp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 130, 10));

        btnDepositar.setBackground(new java.awt.Color(51, 102, 255));
        btnDepositar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDepositar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDepositarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDepositarMouseExited(evt);
            }
        });

        lblDepositar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblDepositar.setForeground(new java.awt.Color(255, 255, 255));
        lblDepositar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDepositar.setText("DEPOSITAR");
        lblDepositar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblDepositar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDepositarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDepositarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDepositarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnDepositarLayout = new javax.swing.GroupLayout(btnDepositar);
        btnDepositar.setLayout(btnDepositarLayout);
        btnDepositarLayout.setHorizontalGroup(
            btnDepositarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDepositar, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        btnDepositarLayout.setVerticalGroup(
            btnDepositarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDepositar, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        panelDeposito.add(btnDepositar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 210, 60));

        getContentPane().add(panelDeposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 680, 500));

        panelRetiro.setBackground(new java.awt.Color(255, 255, 255));
        panelRetiro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblKk2.setFont(new java.awt.Font("Eras Medium ITC", 0, 48)); // NOI18N
        lblKk2.setText("Retiro");
        panelRetiro.add(lblKk2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        txtFolio.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtFolio.setBorder(null);
        txtFolio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFolioKeyTyped(evt);
            }
        });
        panelRetiro.add(txtFolio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 130, 16));

        lblFolio.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        lblFolio.setText("Folio");
        panelRetiro.add(lblFolio, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 80, -1));

        jSp3.setForeground(new java.awt.Color(0, 0, 0));
        panelRetiro.add(jSp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 130, 10));

        lblContrasena.setFont(new java.awt.Font("Franklin Gothic Book", 0, 36)); // NOI18N
        lblContrasena.setText("Contraseña");
        panelRetiro.add(lblContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, -1));

        jSp4.setForeground(new java.awt.Color(0, 0, 0));
        panelRetiro.add(jSp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 130, 10));

        btnRetirar.setBackground(new java.awt.Color(51, 102, 255));
        btnRetirar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRetirar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetirarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDepositar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDepositar1MouseExited(evt);
            }
        });

        lblRetirar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblRetirar.setForeground(new java.awt.Color(255, 255, 255));
        lblRetirar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRetirar.setText("RETIRAR");
        lblRetirar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblRetirar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDepositar1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDepositar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDepositar1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnRetirarLayout = new javax.swing.GroupLayout(btnRetirar);
        btnRetirar.setLayout(btnRetirarLayout);
        btnRetirarLayout.setHorizontalGroup(
            btnRetirarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRetirarLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(lblRetirar)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        btnRetirarLayout.setVerticalGroup(
            btnRetirarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRetirarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRetirar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRetiro.add(btnRetirar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 210, 60));

        txtPass.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtPass.setBorder(null);
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });
        panelRetiro.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 130, -1));

        getContentPane().add(panelRetiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 680, 500));

        panelHistorial.setBackground(new java.awt.Color(255, 255, 255));
        panelHistorial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHisto.setFont(new java.awt.Font("Eras Medium ITC", 0, 48)); // NOI18N
        lblHisto.setForeground(new java.awt.Color(0, 0, 0));
        lblHisto.setText("Historial");
        panelHistorial.add(lblHisto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        tblTransferencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_transferencia", "Fecha operación", "Monto", "ID Cuenta destinatario", "ID Cuenta origen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTransferencia);

        panelHistorial.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 100, 640, 90));

        btnAnterior1.setText("Anterior");
        btnAnterior1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnterior1ActionPerformed(evt);
            }
        });
        panelHistorial.add(btnAnterior1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));

        cbxElementosPágina1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "5", "10" }));
        cbxElementosPágina1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxElementosPágina1ItemStateChanged(evt);
            }
        });
        panelHistorial.add(cbxElementosPágina1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, -1, -1));

        btnSiguiente1.setText("Siguiente");
        btnSiguiente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSiguiente1MouseClicked(evt);
            }
        });
        btnSiguiente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguiente1ActionPerformed(evt);
            }
        });
        panelHistorial.add(btnSiguiente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, -1, -1));

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        panelHistorial.add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        cbxElementosPágina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "5", "10" }));
        cbxElementosPágina.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxElementosPáginaItemStateChanged(evt);
            }
        });
        panelHistorial.add(cbxElementosPágina, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, -1, -1));

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSiguienteMouseClicked(evt);
            }
        });
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        panelHistorial.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, -1, -1));

        tblRetiros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Estado", "Fecha creación", "Fecha operación", "Monto", "ID Cuenta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblRetiros);

        panelHistorial.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 640, 90));

        getContentPane().add(panelHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 680, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegresarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblRegresarMouseEntered

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        // TODO add your handling code here:
        inicio in = new inicio(clientesDAO, direccionDAO, cuentasDAO, transferenciasDAO, retirosDAO);
        in.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRegresarMouseClicked

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        // TODO add your handling code here:
        btnRegresar.setBackground(Color.gray);
        lblRegresar.setForeground(Color.white);
    }//GEN-LAST:event_btnRegresarMouseEntered

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        // TODO add your handling code here:
        btnRegresar.setBackground(new Color(238, 238, 238));
        lblRegresar.setForeground(Color.black);
    }//GEN-LAST:event_btnRegresarMouseExited

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseEntered
        // TODO add your handling code here:
        btnSalir.setBackground(Color.red);
        lblX.setForeground(Color.white);
    }//GEN-LAST:event_btnSalirMouseEntered

    private void btnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseExited
        // TODO add your handling code here:
        btnSalir.setBackground(new Color(238, 238, 238));
        lblX.setForeground(Color.black);
    }//GEN-LAST:event_btnSalirMouseExited

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void btnCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCuentaMouseClicked
        // TODO add your handling code here:
        panelCuenta.setVisible(true);
        cbxCuentas.setVisible(true);
        btnCrear.setVisible(true);
        lblCrear.setVisible(true);
        jsc1.setVisible(true);
        tblCuentas.setVisible(true);
        btnBorrar.setVisible(true);
        lblBorrar.setVisible(true);
        txtMonto.setVisible(true);
        panelContenido.setVisible(false);
        panelTransferencia.setVisible(false);
        panelDeposito.setVisible(false);
        panelRetiro.setVisible(false);
        panelHistorial.setVisible(false);
    }//GEN-LAST:event_btnCuentaMouseClicked

    private void btnTransferenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransferenciaMouseClicked
        // TODO add your handling code here:
        panelTransferencia.setVisible(true);
        lblTitulo1.setVisible(true);
        lblCuentaDes.setVisible(true);
        txtCuentaD.setVisible(true);
        jSp7.setVisible(true);
        lblCuentaO.setVisible(true);
        txtMontoO.setVisible(true);
        jSp5.setVisible(true);
        lblMontoO.setVisible(true);
        jSp6.setVisible(true);
        lblDineroImg.setVisible(true);
        btnTransferir.setVisible(true);
        lblTransferir.setVisible(true);
        cbxCuentas1.setVisible(true);

        panelContenido.setVisible(false);
        panelCuenta.setVisible(false);
        panelDeposito.setVisible(false);
        panelRetiro.setVisible(false);
        panelHistorial.setVisible(false);
    }//GEN-LAST:event_btnTransferenciaMouseClicked

    private void lblDepositarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDepositarMouseClicked
        // TODO add your handling code here:
        int idCuenta = Integer.parseInt((String) cbxCuentas.getSelectedItem());
        if (!validadores.esVacia(txtSaldo.getText())) {
            JOptionPane.showMessageDialog(this, "Campo de saldo vacío", "Error", 0);
        } else if (!validadores.esVacia(txtCuenta.getText())) {
            JOptionPane.showMessageDialog(this, "Campo de cuenta vacío", "Error", 0);
        } else {
            try {
                depositar();
                this.cargarTablaCuentas(idCuenta);
                JOptionPane.showMessageDialog(null, "¡Depósito logrado con éxito!", "LuluAdmin", 1);
            } catch (PersistenciaException ex) {
                Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblDepositarMouseClicked

    private void lblDepositarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDepositarMouseEntered
        // TODO add your handling code here:
        btnDepositar.setBackground(new Color(98, 137, 255));
    }//GEN-LAST:event_lblDepositarMouseEntered

    private void lblDepositarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDepositarMouseExited
        // TODO add your handling code here:
        btnDepositar.setBackground(new Color(32, 92, 255));
    }//GEN-LAST:event_lblDepositarMouseExited

    private void btnDepositarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDepositarMouseEntered

    }//GEN-LAST:event_btnDepositarMouseEntered

    private void btnDepositarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDepositarMouseExited

    }//GEN-LAST:event_btnDepositarMouseExited

    private void btnDepositoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDepositoMouseClicked
        // TODO add your handling code here:
        panelCuenta.setVisible(false);
        txtCuenta.setVisible(true);
        lblTituloD.setVisible(true);
        lblCuentaD.setVisible(true);
        jSp2.setVisible(true);
        jSp1.setVisible(true);
        lblSaldo.setVisible(true);
        txtSaldo.setVisible(true);
        btnDepositar.setVisible(true);
        lblDepositar.setVisible(true);

        panelContenido.setVisible(false);
        panelTransferencia.setVisible(false);
        panelDeposito.setVisible(true);
        panelRetiro.setVisible(false);
        panelHistorial.setVisible(false);
    }//GEN-LAST:event_btnDepositoMouseClicked

    private void cbxCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCuentasActionPerformed

    }//GEN-LAST:event_cbxCuentasActionPerformed

    private void lblCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearMouseClicked
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Quieres crear una cuenta?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                this.crearCuenta();
                this.comboCuenta();
                JOptionPane.showMessageDialog(null, "¡Cuenta creada con éxito!", "LuluAdmin", 1);
            } catch (PersistenciaException ex) {
                Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lblCrearMouseClicked

    private void lblCrearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearMouseEntered
        // TODO add your handling code here:
        btnCrear.setBackground(new Color(98, 137, 255));
    }//GEN-LAST:event_lblCrearMouseEntered

    private void lblCrearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCrearMouseExited
        // TODO add your handling code here:
        btnCrear.setBackground(new Color(32, 92, 255));
    }//GEN-LAST:event_lblCrearMouseExited

    private void btnCrearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearMouseEntered

    private void btnCrearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearMouseExited

    private void lblBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBorrarMouseClicked
        int idCuenta = Integer.parseInt((String) cbxCuentas.getSelectedItem());
        JPanel panel = new JPanel();
        JPasswordField contra = new JPasswordField();
        contra.setColumns(20);
        panel.add(BorderLayout.NORTH, new JLabel("Ingrese su contraseña para eliminar la cuenta " + idCuenta));
        panel.add(BorderLayout.CENTER, contra);
        JOptionPane.showConfirmDialog(null, panel, "ELIMINAR CUENTA", JOptionPane.OK_CANCEL_OPTION);
        if (!validadores.esVacia(contra.getText())) {
            JOptionPane.showMessageDialog(this, "Campo de contraseña vacío", "Error", 0);
        } else {
            try {
                Cliente clienteLogueado = this.clientesDAO.consultar(correo);
                if (contra.getText().equals(clienteLogueado.getPassword())) {
                    if (this.cuentasDAO.consultar(idCuenta).getSaldo() > 0.0) {
                        int respuesta = JOptionPane.showConfirmDialog(null, "La cuenta a eliminar contiene saldo disponible ¿Estás seguro de que quieres eliminarla?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (respuesta == JOptionPane.YES_OPTION) {
                            this.cuentasDAO.actualizarEstado(idCuenta);
                            this.cargarTablaCuentas(idCuenta);
                        }
                    } else {
                        this.cuentasDAO.actualizarEstado(idCuenta);
                        this.cargarTablaCuentas(idCuenta);
                        JOptionPane.showMessageDialog(this, "Se eliminó la cuenta " + idCuenta, "LuluAdmin", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", 0);
                }
            } catch (PersistenciaException ex) {
                Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.cargarTablaCuentas(idCuenta);
    }//GEN-LAST:event_lblBorrarMouseClicked

    private void lblBorrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBorrarMouseEntered
        // TODO add your handling code here:
        btnBorrar.setBackground(new Color(204, 40, 40));
    }//GEN-LAST:event_lblBorrarMouseEntered

    private void lblBorrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBorrarMouseExited
        // TODO add your handling code here:
        btnBorrar.setBackground(new Color(255, 51, 51));
    }//GEN-LAST:event_lblBorrarMouseExited

    private void btnBorrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBorrarMouseEntered

    }//GEN-LAST:event_btnBorrarMouseEntered

    private void btnBorrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBorrarMouseExited

    }//GEN-LAST:event_btnBorrarMouseExited

    private void cbxCuentasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCuentasItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int idCuenta = Integer.parseInt((String) cbxCuentas.getSelectedItem());
            this.cargarTablaCuentas(idCuenta);
        }
    }//GEN-LAST:event_cbxCuentasItemStateChanged

    private void btnDepositar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDepositar1MouseExited
        // TODO add your handling code here:
        btnRetiroT.setBackground(new Color(32, 92, 255));
    }//GEN-LAST:event_btnDepositar1MouseExited

    private void btnDepositar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDepositar1MouseEntered
        // TODO add your handling code here:
        btnRetiroT.setBackground(new Color(98, 137, 255));
    }//GEN-LAST:event_btnDepositar1MouseEntered

    private void lblDepositar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDepositar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDepositar1MouseExited

    private void lblDepositar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDepositar1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDepositar1MouseEntered

    private void lblDepositar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDepositar1MouseClicked

    }//GEN-LAST:event_lblDepositar1MouseClicked

    private void btnBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBorrarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarMouseClicked

    private void btnRetiroTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetiroTMouseClicked
        // TODO add your handling code here:
        lblKk2.setVisible(true);
        txtFolio.setVisible(true);
        lblFolio.setVisible(true);
        jSp3.setVisible(true);
        lblContrasena.setVisible(true);
        jSp4.setVisible(true);
        btnRetirar.setVisible(true);
        lblRetirar.setVisible(true);
        txtPass.setVisible(true);

        panelCuenta.setVisible(false);
        panelContenido.setVisible(false);
        panelTransferencia.setVisible(false);
        panelDeposito.setVisible(false);
        panelRetiro.setVisible(true);
        panelHistorial.setVisible(false);
    }//GEN-LAST:event_btnRetiroTMouseClicked

    private void btnHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseClicked
        // TODO add your handling code here:
        panelHistorial.setVisible(true);
        lblHisto.setVisible(true);
        tblTransferencia.setVisible(true);
        btnAnterior.setVisible(true);
        cbxElementosPágina.setVisible(true);
        btnSiguiente.setVisible(true);
        btnAnterior1.setVisible(true);
        cbxElementosPágina1.setVisible(true);
        btnSiguiente1.setVisible(true);
        this.cargarTablaRetiros();
        this.cargarTablaTransferencias();

        panelContenido.setVisible(false);
        panelCuenta.setVisible(false);
        panelDeposito.setVisible(false);
        panelRetiro.setVisible(false);
    }//GEN-LAST:event_btnHistorialMouseClicked

    private void lblTransferirlblDepositar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransferirlblDepositar1MouseClicked
        try {
            if (!validadores.esVacia(txtMontoO.getText())) {
                JOptionPane.showMessageDialog(this, "Campo de monto vacío", "Error", 0);
            } else if (!validadores.esVacia(txtCuentaD.getText())) {
                JOptionPane.showMessageDialog(this, "Campo de cuenta vacío", "Error", 0);
            } else {
                this.transferir();
                JOptionPane.showMessageDialog(this, "Transferencia satisfactorio", "LuluAdmin", 1);
            }
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo concrecar la transferencia", "Error", 0);
        }

    }//GEN-LAST:event_lblTransferirlblDepositar1MouseClicked

    private void lblTransferirlblDepositar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransferirlblDepositar1MouseEntered
        // TODO add your handling code here:
        btnTransferir.setBackground(new Color(32, 92, 255));
    }//GEN-LAST:event_lblTransferirlblDepositar1MouseEntered

    private void lblTransferirlblDepositar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransferirlblDepositar1MouseExited
        // TODO add your handling code here:
        btnTransferir.setBackground(new Color(98, 137, 255));
    }//GEN-LAST:event_lblTransferirlblDepositar1MouseExited

    private void btnTransferirbtnDepositar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransferirbtnDepositar1MouseEntered

    }//GEN-LAST:event_btnTransferirbtnDepositar1MouseEntered

    private void btnTransferirbtnDepositar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransferirbtnDepositar1MouseExited

    }//GEN-LAST:event_btnTransferirbtnDepositar1MouseExited

    private void cbxCuentas1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCuentas1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCuentas1ItemStateChanged

    private void cbxCuentas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCuentas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCuentas1ActionPerformed

    private void lblGenreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGenreMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Random random = new Random();
            int folioRan = random.nextInt(90000000) + 10000000;
            int cuenta = Integer.parseInt((String) cbxCuentas.getSelectedItem());

            Double monto = Double.valueOf(txtMonto.getText());
            this.retirosDAO.insertar(cuenta, folioRan, monto);
            Retiro contraR = this.retirosDAO.consultar(folioRan);
            JOptionPane.showMessageDialog(this, "FOLIO: " + folioRan + " CONTRASEÑA: " + contraR.getContrasena(), "CREDENCIALES RETIRO", 0);

        } catch (PersistenciaException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_lblGenreMouseClicked

    private void lblGenreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGenreMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblGenreMouseEntered

    private void lblGenreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGenreMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblGenreMouseExited

    private void btnGenreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenreMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenreMouseEntered

    private void btnGenreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenreMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenreMouseExited

    private void btnGenreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenreMouseClicked

    }//GEN-LAST:event_btnGenreMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        try {
            actualizarCliente ac = new actualizarCliente(clientesDAO, direccionDAO, cuentasDAO, transferenciasDAO, correo, retirosDAO);
            ac.setVisible(true);
            dispose();
        } catch (PersistenciaException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtMontoOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoOKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < ',' || car > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoOKeyTyped

    private void txtCuentaDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaDKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < ',' || car > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCuentaDKeyTyped

    private void txtCuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < ',' || car > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCuentaKeyTyped

    private void txtSaldoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaldoKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < ',' || car > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSaldoKeyTyped

    private void txtFolioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFolioKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < ',' || car > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFolioKeyTyped

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < ',' || car > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPassKeyTyped

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < ',' || car > '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void btnRetirarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetirarMouseClicked
        // TODO add your handling code here:
        if (!validadores.esVacia(txtFolio.getText())) {
            JOptionPane.showMessageDialog(this, "Campo de folio vacío", "Error", 0);
        } else if (!validadores.esVacia(txtPass.getText())) {
            JOptionPane.showMessageDialog(this, "Campo de contraseña vacío", "Error", 0);
        } else {
            try {
                int folio = Integer.parseInt(txtFolio.getText());
                String contra = txtPass.getText();
                Date fechaActual = new Date();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
                String fechaA = formatoFecha.format(fechaActual);
                String es = "cobrado";
                this.retirosDAO.actualizar(folio, contra, fechaA);
                this.retirosDAO.actualizarEstado(folio, contra, es);
                if (this.retirosDAO.actualizar(folio, contra, fechaA) == true) {
                    Retiro cuenta = this.retirosDAO.consultarCuenta(folio);
                    this.cuentasDAO.actualizarDescMonto(cuenta.getIdCuenta(), cuenta.getMonto());
                    this.cargarTablaCuentas(cuenta.getIdCuenta());
                    JOptionPane.showMessageDialog(this, "Retiro satisfactorio", "LuluAdmin", 1);
                }
            } catch (PersistenciaException ex) {
                Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRetirarMouseClicked

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        retrocederPaginaT();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void cbxElementosPáginaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxElementosPáginaItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            int elementoPorPagina = Integer.parseInt(evt.getItem().toString());
            this.configPaginado.setElementosPagina(elementoPorPagina);
            this.cargarTablaTransferencias();
        }
    }//GEN-LAST:event_cbxElementosPáginaItemStateChanged

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        avanzarPaginaT();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiguienteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguienteMouseClicked

    private void btnAnterior1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnterior1ActionPerformed
        // TODO add your handling code here:
        retrocederPaginaR();
    }//GEN-LAST:event_btnAnterior1ActionPerformed

    private void cbxElementosPágina1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxElementosPágina1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxElementosPágina1ItemStateChanged

    private void btnSiguiente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiguiente1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguiente1MouseClicked

    private void btnSiguiente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguiente1ActionPerformed
        // TODO add your handling code here:
        avanzarPaginaR();
    }//GEN-LAST:event_btnSiguiente1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraIzq;
    private javax.swing.JPanel barraTop;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnAnterior1;
    private javax.swing.JPanel btnBorrar;
    private javax.swing.JPanel btnCrear;
    private javax.swing.JPanel btnCuenta;
    private javax.swing.JPanel btnDepositar;
    private javax.swing.JPanel btnDeposito;
    private javax.swing.JPanel btnGenre;
    private javax.swing.JPanel btnHistorial;
    private javax.swing.JPanel btnRegresar;
    private javax.swing.JPanel btnRetirar;
    private javax.swing.JPanel btnRetiroT;
    private javax.swing.JPanel btnSalir;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnSiguiente1;
    private javax.swing.JPanel btnTransferencia;
    private javax.swing.JPanel btnTransferir;
    private javax.swing.JComboBox<String> cbxCuentas;
    private javax.swing.JComboBox<String> cbxCuentas1;
    private javax.swing.JComboBox<String> cbxElementosPágina;
    private javax.swing.JComboBox<String> cbxElementosPágina1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSp1;
    private javax.swing.JSeparator jSp2;
    private javax.swing.JSeparator jSp3;
    private javax.swing.JSeparator jSp4;
    private javax.swing.JSeparator jSp5;
    private javax.swing.JSeparator jSp6;
    private javax.swing.JSeparator jSp7;
    private javax.swing.JSeparator jSp8;
    private javax.swing.JScrollPane jsc1;
    private javax.swing.JLabel lblBorrar;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblCrear;
    private javax.swing.JLabel lblCuenta;
    private javax.swing.JLabel lblCuentaD;
    private javax.swing.JLabel lblCuentaDes;
    private javax.swing.JLabel lblCuentaO;
    private javax.swing.JLabel lblDepositar;
    private javax.swing.JLabel lblDeposito;
    private javax.swing.JLabel lblDineroImg;
    private javax.swing.JLabel lblFolio;
    private javax.swing.JLabel lblGenre;
    private javax.swing.JLabel lblHisto;
    private javax.swing.JLabel lblHistorial;
    private javax.swing.JLabel lblKk;
    private javax.swing.JLabel lblKk2;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblMontoO;
    private javax.swing.JLabel lblRegresar;
    private javax.swing.JLabel lblRetirar;
    private javax.swing.JLabel lblRetiro;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTituloD;
    private javax.swing.JLabel lblTransferencia;
    private javax.swing.JLabel lblTransferir;
    private javax.swing.JLabel lblX;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelCuenta;
    private javax.swing.JPanel panelDeposito;
    private javax.swing.JPanel panelHistorial;
    private javax.swing.JPanel panelRetiro;
    private javax.swing.JPanel panelTransferencia;
    private javax.swing.JTable tblCuentas;
    private javax.swing.JTable tblRetiros;
    private javax.swing.JTable tblTransferencia;
    private javax.swing.JTextField txtCuenta;
    private javax.swing.JTextField txtCuentaD;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtMontoO;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables
}
