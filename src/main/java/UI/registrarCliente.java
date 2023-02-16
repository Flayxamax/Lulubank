/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Dominio.Cliente;
import Dominio.Direccion;
import Excepciones.PersistenciaException;
import Interfaces.IClientesDAO;
import Interfaces.IDireccionDAO;
import Utils.ConfiguracionPaginado;
import Utils.Validadores;
import implementaciones.ClientesDAO;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ildex
 */
public class registrarCliente extends javax.swing.JFrame {

    private static final Logger LOG = Logger.getLogger(ClientesDAO.class.getName());
    private final IClientesDAO clientesDAO;
    private final IDireccionDAO direccionDAO;
    private ConfiguracionPaginado configPaginado;
    private final Validadores validadores = new Validadores();

    /**
     * Creates new form registrarCliente
     * @param clientesDAO
     * @param direccionDAO
     */
    public registrarCliente(IClientesDAO clientesDAO, IDireccionDAO direccionDAO) {
        this.clientesDAO = clientesDAO;
        this.direccionDAO = direccionDAO;
        initComponents();
    }
    
    private Cliente extraerDatosFormulario() {
        String nombre = this.txtNombre.getText();
        String apellidoPaterno = this.txtApellidoP.getText();
        String apellidoMaterno = this.txtApellidoM.getText();
        Integer idDireccion = 1;
        int edad = 18;
        String fechaNacimiento = "2003-01-01";
        String contrasena = this.txtPass.getText();
        Cliente cliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, edad, contrasena, idDireccion);
        return cliente;
    }
    
    private Direccion extraerDireccionFormulario(){
        String calle = this.txtCalle.getText();
        String colonia = this.txtColonia.getText();
        String numCasa = this.txtNumCasa.getText();
        Direccion direccion = new Direccion(calle, colonia, numCasa);
        return direccion;
    }

    private void mostrarMensajeClienteGuardado(Cliente clienteGuardado) {
        JOptionPane.showMessageDialog(this, "Se agregó el cliente: " + clienteGuardado.getIdCliente(), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarMensajeErrorGuardado() {
        JOptionPane.showMessageDialog(this, "No fue posible guardar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void guardar() {
        //Enviar a DAO
        try {
            Cliente cliente = this.extraerDatosFormulario();
            Cliente clienteGuardado = this.clientesDAO.insertar(cliente);
            
            Direccion direccion = this.extraerDireccionFormulario();
            Direccion direccionGuardada = this.direccionDAO.insertar(direccion);
            this.mostrarMensajeClienteGuardado(clienteGuardado);
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

        txtPass = new javax.swing.JPasswordField();
        lblApellidoM1 = new javax.swing.JLabel();
        lblNumCasa = new javax.swing.JLabel();
        txtNumCasa = new javax.swing.JTextField();
        lblColonia = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        lblSub2 = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        lblApellidoM = new javax.swing.JLabel();
        lblCalle = new javax.swing.JLabel();
        txtApellidoM = new javax.swing.JTextField();
        txtCalle = new javax.swing.JTextField();
        lblApellidop = new javax.swing.JLabel();
        txtApellidoP = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBtn = new javax.swing.JLabel();
        lblFormwp = new javax.swing.JLabel();
        lblwp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar cliente");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPass.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 180, -1));

        lblApellidoM1.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblApellidoM1.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidoM1.setText("Contraseña");
        getContentPane().add(lblApellidoM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, -1, -1));

        lblNumCasa.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblNumCasa.setForeground(new java.awt.Color(255, 255, 255));
        lblNumCasa.setText("Número casa");
        getContentPane().add(lblNumCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 550, -1, -1));

        txtNumCasa.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtNumCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumCasaActionPerformed(evt);
            }
        });
        getContentPane().add(txtNumCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 550, 180, -1));

        lblColonia.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblColonia.setForeground(new java.awt.Color(255, 255, 255));
        lblColonia.setText("Colonia");
        getContentPane().add(lblColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, -1, -1));

        txtColonia.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtColonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColoniaActionPerformed(evt);
            }
        });
        getContentPane().add(txtColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 180, -1));

        lblSub2.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblSub2.setForeground(new java.awt.Color(255, 255, 255));
        lblSub2.setText("-- Domicilio --");
        getContentPane().add(lblSub2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, -1, -1));

        lblSub.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblSub.setForeground(new java.awt.Color(255, 255, 255));
        lblSub.setText("-- Información personal --");
        getContentPane().add(lblSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, -1));

        lblApellidoM.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblApellidoM.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidoM.setText("Apellido materno");
        getContentPane().add(lblApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        lblCalle.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblCalle.setForeground(new java.awt.Color(255, 255, 255));
        lblCalle.setText("Calle");
        getContentPane().add(lblCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

        txtApellidoM.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtApellidoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoMActionPerformed(evt);
            }
        });
        getContentPane().add(txtApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 180, -1));

        txtCalle.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalleActionPerformed(evt);
            }
        });
        getContentPane().add(txtCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, 180, -1));

        lblApellidop.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblApellidop.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidop.setText("Apellido paterno");
        getContentPane().add(lblApellidop, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        txtApellidoP.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        txtApellidoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoPActionPerformed(evt);
            }
        });
        getContentPane().add(txtApellidoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 180, -1));

        txtNombre.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
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
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 180, -1));

        lblNombre.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Franklin Gothic Book", 1, 65)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("LuluBank");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Registrar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 660, 90, 40));

        lblBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/btnA (Custom).png"))); // NOI18N
        lblBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBtnMouseClicked(evt);
            }
        });
        getContentPane().add(lblBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 650, -1, -1));

        lblFormwp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ff7-                                                                                .png"))); // NOI18N
        getContentPane().add(lblFormwp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        lblwp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wpInvitado.jpg"))); // NOI18N
        getContentPane().add(lblwp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 762));

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

    private void lblBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBtnMouseClicked
        // TODO add your handling code here:
        if (!validadores.validaNombre(txtNombre.getText())) {
            JOptionPane.showMessageDialog(this, "Nombre inválido, debe contener entre 3 y 50 caracteres", "Error", 0);
        } else if (!validadores.validaApellido(txtApellidoP.getText())) {
            JOptionPane.showMessageDialog(this, "Apellido paterno inválido, debe contener entre 3 y 30 caracteres", "Error", 0);
        } else if (!validadores.validaApellido(txtApellidoM.getText())) {
            JOptionPane.showMessageDialog(this, "Apellido materno inválido, debe contener entre 3 y 30 caracteres", "Error", 0);
        } else if (!validadores.validaContrasena(txtPass.getText())) {
            JOptionPane.showMessageDialog(this, "Contraseña inválida, Tener al menos una letra minúscula, una letra mayúscula, un dígito y uno de los siguientes caracteres especiales: $, @, !, %, *, ?, &\n"
                    + "No tener espacios en blanco\n"
                    + "Tener entre 8 y 15 caracteres de longitud", "Error", 0);
        }

        guardar();
    }//GEN-LAST:event_lblBtnMouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if (txtNombre.getText().length() >= 50) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(registrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(registrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(registrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(registrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new registrarCliente().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblApellidoM;
    private javax.swing.JLabel lblApellidoM1;
    private javax.swing.JLabel lblApellidop;
    private javax.swing.JLabel lblBtn;
    private javax.swing.JLabel lblCalle;
    private javax.swing.JLabel lblColonia;
    private javax.swing.JLabel lblFormwp;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumCasa;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblSub2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblwp;
    private javax.swing.JTextField txtApellidoM;
    private javax.swing.JTextField txtApellidoP;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumCasa;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}