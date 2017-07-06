/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import BD.ConnectionBD;
import Clases.Board;
import Clases.TimeNumber;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sistematiempos.SpanishLanguage;
import sun.security.util.Password;

/**
 *
 * @author Bryan
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    private static SpanishLanguage appStrings = SpanishLanguage.getInstance();
    private Board board;
    private String pass, currentDate;
    private int boardQuantity;
    
    public Login() {
        initComponents();
        //++deleteAll();
    }
    
    private void getBoardPassword() {
        ConnectionBD con= new ConnectionBD();
        board = con.getBoardInformation(); 
        String password = board.getPassword();
        pass = password;
    }
    
    private void createBoard(boolean isOnNullState) {
        ConnectionBD con= new ConnectionBD();
        System.out.println("crear");
        if (isOnNullState) {
            System.out.println("+++");
            con.createBoard("12:30", "19:00", "FENG", 15, "123", currentDate, 20000);   
        }
        else {
            System.out.println("#$");
            con.createBoard(board.getDayClose(), board.getNightClose(), board.getStore(), board.getStadisticsPer()
                    , board.getPassword(), currentDate, board.getNumbersPrincing());  
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

        etPassword = new javax.swing.JPasswordField();
        lblPassword = new javax.swing.JLabel();
        btnAccess = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        etPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etPasswordActionPerformed(evt);
            }
        });

        lblPassword.setText("Contraseña:");

        btnAccess.setText("Ingresar");
        btnAccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAccess)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addGap(40, 40, 40)
                .addComponent(btnAccess)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void etPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_etPasswordActionPerformed
    
    private void btnAccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccessActionPerformed
        // TODO add your handling code here:
        getBoardPassword();
        String pwd = new String(etPassword.getPassword());
        Calendar cal=Calendar.getInstance(); 
        currentDate =cal.get(cal.DATE)+"/"+(cal.get(cal.MONTH)+1)+"/"+cal.get(cal.YEAR);
        if (board == null) {
            createBoard(true);
        }
        else if (!board.getDate().equals(currentDate)) {
            createBoard(false);
        }
        if (pwd.equals("8888")) {
            Selling selling = new Selling();
            selling.setVisible(true);
            selling.setLocationRelativeTo(null);
            selling.setExtendedState(JFrame.MAXIMIZED_BOTH);
            selling.hideChineseButtons();
            selling.setLanguageToSpanish();
            dispose();
        }
        else if (pass.equals(pwd)) {
            AdministratorEditOptions administratorEditOptions = new AdministratorEditOptions();
            administratorEditOptions.setVisible(true);
            administratorEditOptions.setLocationRelativeTo(null);
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(null, appStrings.getPasswordError());
        }
    }//GEN-LAST:event_btnAccessActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccess;
    private javax.swing.JPasswordField etPassword;
    private javax.swing.JLabel lblPassword;
    // End of variables declaration//GEN-END:variables
}
