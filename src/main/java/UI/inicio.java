/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Interfaces.IClientesDAO;
import Interfaces.IDireccionDAO;

/**
 *
 * @author ildex
 */
public class inicio extends javax.swing.JFrame {

    private final IClientesDAO clientesDAO;
    private final IDireccionDAO direccionDAO;

    /**
     * Creates new form inicio
     */
    public inicio(IClientesDAO clientesDAO, IDireccionDAO direccionDAO) {
        initComponents();
        this.clientesDAO = clientesDAO;
        this.direccionDAO = direccionDAO;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblRegister = new javax.swing.JLabel();
        lblIniciar = new javax.swing.JLabel();
        lblInvitado = new javax.swing.JLabel();
        wp = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lulubank");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Invitado");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, -1, -1));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Registrar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, -1, -1));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Iniciar sesión");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Franklin Gothic Book", 1, 65)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("LuluBank");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        lblRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botonGris.png"))); // NOI18N
        lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegisterMouseClicked(evt);
            }
        });
        getContentPane().add(lblRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, -1, -1));

        lblIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botonGris.png"))); // NOI18N
        getContentPane().add(lblIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));

        lblInvitado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/btnA.png"))); // NOI18N
        getContentPane().add(lblInvitado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 190, 50));

        wp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/wp2.jpg"))); // NOI18N
        getContentPane().add(wp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegisterMouseClicked
        // TODO add your handling code here:
        registrarCliente rc = new registrarCliente(clientesDAO, direccionDAO);
        rc.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblRegisterMouseClicked

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
//            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new inicio().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblIniciar;
    private javax.swing.JLabel lblInvitado;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel wp;
    // End of variables declaration//GEN-END:variables
}