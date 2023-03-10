/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Dominio.Cliente;
import Dominio.Direccion;
import Excepciones.PersistenciaException;
import Interfaces.IClientesDAO;
import Interfaces.ICuentasDAO;
import Interfaces.IDireccionDAO;
import Interfaces.IRetirosDAO;
import Interfaces.ITransferenciasDAO;
import Utils.ConfiguracionPaginado;
import Utils.TextPrompt;
import Utils.Validadores;
import implementaciones.ClientesDAO;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ildex
 */
public class actualizarCliente extends javax.swing.JFrame {

    private static final Logger LOG = Logger.getLogger(ClientesDAO.class.getName());
    private final IClientesDAO clientesDAO;
    private final IDireccionDAO direccionDAO;
    private final ICuentasDAO cuentasDAO;
    private final ITransferenciasDAO transferenciasDAO;
    private final IRetirosDAO retirosDAO;
    private final String correo;
    private ConfiguracionPaginado configPaginado;
    private final Validadores validadores = new Validadores();
    private int xMouse;
    private int yMouse;

    public actualizarCliente(IClientesDAO clientesDAO, IDireccionDAO direccionDAO, ICuentasDAO cuentasDAO, ITransferenciasDAO transferenciasDAO, String correo, IRetirosDAO retirosDAO) throws PersistenciaException {
        this.clientesDAO = clientesDAO;
        this.direccionDAO = direccionDAO;
        this.cuentasDAO = cuentasDAO;
        this.transferenciasDAO = transferenciasDAO;
        this.correo = correo;
        this.retirosDAO = retirosDAO;
        initComponents();
        ingresaCliente();
        LocalDate fechaActual = LocalDate.now();
        LocalDate minFecha = LocalDate.of(1900, 01, 01);
        calendar.getSettings().setDateRangeLimits(minFecha, fechaActual);
        TextPrompt nombre = new TextPrompt("Ingrese su nombre", txtNombre);
        TextPrompt aPaterno = new TextPrompt("Ingrese su apellido paterno", txtApellidoP);
        TextPrompt aMaterno = new TextPrompt("Ingrese su apellido materno", txtApellidoM);
        TextPrompt correoA = new TextPrompt("Ingrese su correo electr??nico", txtCorreo);
        TextPrompt contrasena = new TextPrompt("Ingrese su contrase??a", txtPass);
        TextPrompt calle = new TextPrompt("Ingrese su calle", txtCalle);
        TextPrompt colonia = new TextPrompt("Ingrese su nombre", txtColonia);
        TextPrompt numCasa = new TextPrompt("Ingrese su n??mero de casa", txtNumCasa);
    }

    public void ingresaCliente() throws PersistenciaException {
        Cliente clienteLogueado = this.clientesDAO.consultar(correo);
        Direccion direccion = this.direccionDAO.consultar(clienteLogueado.getIdCliente());
        txtNombre.setText(clienteLogueado.getNombre());
        txtApellidoP.setText(clienteLogueado.getApellidoPaterno());
        txtApellidoM.setText(clienteLogueado.getApellidoMaterno());
        txtCorreo.setText(clienteLogueado.getCorreo());
        txtPass.setText(clienteLogueado.getPassword());
        txtCalle.setText(direccion.getCalle());
        txtColonia.setText(direccion.getColonia());
        txtNumCasa.setText(direccion.getNumCasa());
    }

    private Cliente extraerDatosFormulario() {
        try {
            Cliente clienteLogueado = this.clientesDAO.consultar(correo);
            String nombre = this.txtNombre.getText();
            String apellidoPaterno = this.txtApellidoP.getText();
            String apellidoMaterno = this.txtApellidoM.getText();
            String correo = this.txtCorreo.getText();
            String contrasena = this.txtPass.getText();
            LocalDate fechaN = this.calendar.getSelectedDate();
            LocalDate fechaActual = LocalDate.now();
            Date fechaNacimiento = new Date(fechaN.getYear() - 1900, fechaN.getMonthValue() - 1, fechaN.getDayOfMonth());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNa = sdf.format(fechaNacimiento);
            Period periodo = Period.between(fechaActual, fechaN);
            int edad = Math.abs(periodo.getYears());
            if (edad < 18) {
                JOptionPane.showMessageDialog(this, "Fecha invalida: Necesitas ser mayor de edad para registrarse", "Error", JOptionPane.ERROR_MESSAGE);
                this.calendar.setSelectedDate(null);
                fechaNa = null;
            } else {

            }
            Cliente cliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, fechaNa, edad, correo, contrasena, clienteLogueado.getIdDireccion());
            return cliente;
        } catch (PersistenciaException ex) {
            Logger.getLogger(actualizarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Direccion extraerDireccionFormulario() {
        try {
            Cliente clienteLogueado = this.clientesDAO.consultar(correo);
            String calle = this.txtCalle.getText();
            String colonia = this.txtColonia.getText();
            String numCasa = this.txtNumCasa.getText();
            Direccion direccion = new Direccion(clienteLogueado.getIdDireccion(), calle, colonia, numCasa);
            return direccion;
        } catch (PersistenciaException ex) {
            Logger.getLogger(actualizarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void mostrarMensajeClienteGuardado(Cliente clienteGuardado) {
        JOptionPane.showMessageDialog(this, "Se actualiz?? el cliente: " + clienteGuardado.getIdCliente(), "Informaci??n", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarMensajeErrorGuardado() {
        JOptionPane.showMessageDialog(this, "No fue posible actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void guardar() {
        try {
            Cliente clienteLogueado = this.clientesDAO.consultar(correo);
            Direccion direccion = this.extraerDireccionFormulario();
            this.direccionDAO.actualizar(direccion, clienteLogueado.getIdCliente());
            Cliente cliente = this.extraerDatosFormulario();
            this.clientesDAO.actualizar(cliente, clienteLogueado.getIdCliente());
            this.mostrarMensajeClienteGuardado(clienteLogueado);
        } catch (PersistenciaException e) {
            this.mostrarMensajeErrorGuardado();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblSub = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidoP = new javax.swing.JTextField();
        lblApellidop = new javax.swing.JLabel();
        lblApellidoM = new javax.swing.JLabel();
        txtApellidoM = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        lblApellidoM1 = new javax.swing.JLabel();
        lblSub2 = new javax.swing.JLabel();
        lblCalle = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        txtColonia = new javax.swing.JTextField();
        lblColonia = new javax.swing.JLabel();
        lblNumCasa = new javax.swing.JLabel();
        txtNumCasa = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        sep8 = new javax.swing.JSeparator();
        sep7 = new javax.swing.JSeparator();
        sep6 = new javax.swing.JSeparator();
        sep5 = new javax.swing.JSeparator();
        sep4 = new javax.swing.JSeparator();
        sep3 = new javax.swing.JSeparator();
        sep2 = new javax.swing.JSeparator();
        sep1 = new javax.swing.JSeparator();
        sep = new javax.swing.JSeparator();
        calendar = new com.github.lgooddatepicker.components.CalendarPanel();
        btnActualizar = new javax.swing.JPanel();
        lblActualizar = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JPanel();
        lblRegresar = new javax.swing.JLabel();
        btnSalir = new javax.swing.JPanel();
        lblX = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblwp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar cliente");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFecha.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblFecha.setText("Fecha de nacimiento");
        jPanel1.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, -1, -1));

        lblCorreo.setText("Correo");
        lblCorreo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel1.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, -1, -1));

        txtCorreo.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtCorreo.setBorder(null);
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCorreoMousePressed(evt);
            }
        });
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 330, -1));

        lblSub.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblSub.setText("-- Informaci??n personal --");
        jPanel1.add(lblSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, -1));

        lblNombre.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblNombre.setText("Nombre");
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, -1, -1));

        txtNombre.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtNombre.setBorder(null);
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        txtNombre.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNombreMousePressed(evt);
            }
        });
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 330, -1));

        txtApellidoP.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtApellidoP.setBorder(null);
        txtApellidoP.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidoP.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtApellidoP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtApellidoPMousePressed(evt);
            }
        });
        txtApellidoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoPActionPerformed(evt);
            }
        });
        jPanel1.add(txtApellidoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 330, -1));

        lblApellidop.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblApellidop.setText("Apellido paterno");
        jPanel1.add(lblApellidop, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, -1, -1));

        lblApellidoM.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblApellidoM.setText("Apellido materno");
        jPanel1.add(lblApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, -1, -1));

        txtApellidoM.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtApellidoM.setBorder(null);
        txtApellidoM.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidoM.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtApellidoM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtApellidoMMousePressed(evt);
            }
        });
        txtApellidoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoMActionPerformed(evt);
            }
        });
        jPanel1.add(txtApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 330, -1));

        txtPass.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtPass.setBorder(null);
        txtPass.setForeground(new java.awt.Color(0, 0, 0));
        txtPass.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPassMousePressed(evt);
            }
        });
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        jPanel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, 330, -1));

        lblApellidoM1.setText("Contrase??a");
        lblApellidoM1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jPanel1.add(lblApellidoM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, -1, -1));

        lblSub2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblSub2.setText("-- Domicilio --");
        jPanel1.add(lblSub2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 500, -1, -1));

        lblCalle.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblCalle.setText("Calle");
        jPanel1.add(lblCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 520, -1, -1));

        txtCalle.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtCalle.setBorder(null);
        txtCalle.setForeground(new java.awt.Color(0, 0, 0));
        txtCalle.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtCalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCalleMousePressed(evt);
            }
        });
        txtCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalleActionPerformed(evt);
            }
        });
        jPanel1.add(txtCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, 330, -1));

        txtColonia.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtColonia.setBorder(null);
        txtColonia.setForeground(new java.awt.Color(0, 0, 0));
        txtColonia.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtColonia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtColoniaMousePressed(evt);
            }
        });
        txtColonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColoniaActionPerformed(evt);
            }
        });
        jPanel1.add(txtColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 630, 330, -1));

        lblColonia.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblColonia.setText("Colonia");
        jPanel1.add(lblColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 600, -1, -1));

        lblNumCasa.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblNumCasa.setText("N??mero casa");
        jPanel1.add(lblNumCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 680, -1, -1));

        txtNumCasa.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtNumCasa.setBorder(null);
        txtNumCasa.setForeground(new java.awt.Color(0, 0, 0));
        txtNumCasa.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtNumCasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNumCasaMousePressed(evt);
            }
        });
        txtNumCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumCasaActionPerformed(evt);
            }
        });
        jPanel1.add(txtNumCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 710, 330, -1));

        lblTitulo.setText("Actualizar cliente");
        lblTitulo.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));
        jPanel1.add(sep8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 370, 230, 10));
        jPanel1.add(sep7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 740, 330, 10));
        jPanel1.add(sep6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 660, 330, 10));
        jPanel1.add(sep5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 580, 330, 10));
        jPanel1.add(sep4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, 330, 10));
        jPanel1.add(sep3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 330, 10));
        jPanel1.add(sep2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 330, 10));
        jPanel1.add(sep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 330, 10));
        jPanel1.add(sep, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 330, 10));

        calendar.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 150, -1, -1));

        btnActualizar.setBackground(new java.awt.Color(51, 102, 255));
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });

        lblActualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblActualizar.setText("ACTUALIZAR");
        lblActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblActualizar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblActualizar.setForeground(new java.awt.Color(255, 255, 255));
        lblActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblActualizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblActualizarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnActualizarLayout = new javax.swing.GroupLayout(btnActualizar);
        btnActualizar.setLayout(btnActualizarLayout);
        btnActualizarLayout.setHorizontalGroup(
            btnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        btnActualizarLayout.setVerticalGroup(
            btnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnActualizarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 700, 210, 40));

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
                .addGap(0, 1066, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.setBackground(new java.awt.Color(82, 113, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 380, 70));

        lblwp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wpRegister.png"))); // NOI18N
        jPanel1.add(lblwp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPActionPerformed

    private void txtApellidoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoMActionPerformed

    private void txtCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCalleActionPerformed

    private void txtColoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColoniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColoniaActionPerformed

    private void txtNumCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumCasaActionPerformed

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if (txtNombre.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void lblActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActualizarMouseEntered
        // TODO add your handling code here:
        btnActualizar.setBackground(new Color(98, 137, 255));
    }//GEN-LAST:event_lblActualizarMouseEntered

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited

    }//GEN-LAST:event_btnActualizarMouseExited

    private void btnActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseEntered

    }//GEN-LAST:event_btnActualizarMouseEntered

    private void lblActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActualizarMouseExited
        // TODO add your handling code here:
        btnActualizar.setBackground(new Color(32, 92, 255));
    }//GEN-LAST:event_lblActualizarMouseExited

    private void txtNombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMousePressed

    private void txtApellidoPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtApellidoPMousePressed

    }//GEN-LAST:event_txtApellidoPMousePressed

    private void txtApellidoMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtApellidoMMousePressed

    }//GEN-LAST:event_txtApellidoMMousePressed

    private void txtCorreoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCorreoMousePressed

    }//GEN-LAST:event_txtCorreoMousePressed

    private void txtPassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMousePressed

    }//GEN-LAST:event_txtPassMousePressed

    private void txtCalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCalleMousePressed

    }//GEN-LAST:event_txtCalleMousePressed

    private void txtColoniaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtColoniaMousePressed

    }//GEN-LAST:event_txtColoniaMousePressed

    private void txtNumCasaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNumCasaMousePressed

    }//GEN-LAST:event_txtNumCasaMousePressed

    private void lblActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActualizarMouseClicked
        // TODO add your handling code here:
        if (!validadores.validaNombre(txtNombre.getText())) {
            JOptionPane.showMessageDialog(this, "Nombre inv??lido, debe contener entre 3 y 50 caracteres", "Error", 0);
        } else if (!validadores.validaApellido(txtApellidoP.getText())) {
            JOptionPane.showMessageDialog(this, "Apellido paterno inv??lido, debe contener entre 3 y 30 caracteres", "Error", 0);
        } else if (!validadores.validaApellido(txtApellidoM.getText())) {
            JOptionPane.showMessageDialog(this, "Apellido materno inv??lido, debe contener entre 3 y 30 caracteres", "Error", 0);
        } else if (!validadores.validaContrasena(txtPass.getText())) {
            JOptionPane.showMessageDialog(this, "Contrase??a inv??lida, Tener al menos una letra min??scula, una letra may??scula, un d??gito y uno de los siguientes caracteres especiales: $, @, !, %, *, ?, &\n"
                    + "No tener espacios en blanco\n"
                    + "Tener entre 8 y 15 caracteres de longitud", "Error", 0);
        } else if (!validadores.validaEmail(txtCorreo.getText())) {
            JOptionPane.showMessageDialog(this, "Correo invalido", "Error", 0);
        } else if (calendar.getSelectedDate() == null) {
            JOptionPane.showMessageDialog(this, "Fecha vacia", "Error", 0);
        } else if (!validadores.esVacia(txtColonia.getText())) {
            JOptionPane.showMessageDialog(this, "Colonia vacia", "Error", 0);
        } else if (!validadores.esVacia(txtNumCasa.getText())) {
            JOptionPane.showMessageDialog(this, "N??mero de casa vac??o", "Error", 0);
        } else {
            guardar();
        }
    }//GEN-LAST:event_lblActualizarMouseClicked

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void btnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseExited
        // TODO add your handling code here:
        btnSalir.setBackground(new Color(238, 238, 238));
        lblX.setForeground(Color.black);
    }//GEN-LAST:event_btnSalirMouseExited

    private void btnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseEntered
        // TODO add your handling code here:
        btnSalir.setBackground(Color.red);
        lblX.setForeground(Color.white);
    }//GEN-LAST:event_btnSalirMouseEntered

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        // TODO add your handling code here:
        btnRegresar.setBackground(new Color(238, 238, 238));
        lblRegresar.setForeground(Color.black);
    }//GEN-LAST:event_btnRegresarMouseExited

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        // TODO add your handling code here:
        btnRegresar.setBackground(Color.gray);
        lblRegresar.setForeground(Color.white);
    }//GEN-LAST:event_btnRegresarMouseEntered

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        try {
            // TODO add your handling code here:
            Operaciones in = new Operaciones(clientesDAO, direccionDAO, txtCorreo.getText(), cuentasDAO, transferenciasDAO, retirosDAO);
            in.setVisible(true);
            dispose();
        } catch (PersistenciaException ex) {
            Logger.getLogger(actualizarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegresarMouseClicked

    private void lblRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegresarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lblRegresarMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnActualizar;
    private javax.swing.JPanel btnRegresar;
    private javax.swing.JPanel btnSalir;
    private com.github.lgooddatepicker.components.CalendarPanel calendar;
    private javax.swing.JPanel header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblActualizar;
    private javax.swing.JLabel lblApellidoM;
    private javax.swing.JLabel lblApellidoM1;
    private javax.swing.JLabel lblApellidop;
    private javax.swing.JLabel lblCalle;
    private javax.swing.JLabel lblColonia;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumCasa;
    private javax.swing.JLabel lblRegresar;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblSub2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblX;
    private javax.swing.JLabel lblwp;
    private javax.swing.JSeparator sep;
    private javax.swing.JSeparator sep1;
    private javax.swing.JSeparator sep2;
    private javax.swing.JSeparator sep3;
    private javax.swing.JSeparator sep4;
    private javax.swing.JSeparator sep5;
    private javax.swing.JSeparator sep6;
    private javax.swing.JSeparator sep7;
    private javax.swing.JSeparator sep8;
    private javax.swing.JTextField txtApellidoM;
    private javax.swing.JTextField txtApellidoP;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumCasa;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}
