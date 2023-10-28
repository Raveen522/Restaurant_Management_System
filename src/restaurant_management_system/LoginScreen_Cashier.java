/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restaurant_management_system;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ravin
 */
public class LoginScreen_Cashier extends javax.swing.JFrame {

    /**
     * Creates new form LoginScreen_Cashier
     */
    public LoginScreen_Cashier() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblPassword = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        btnLogin = new customPackages.RoundedButton();
        lblTitle = new javax.swing.JLabel();
        imgTitle = new javax.swing.JLabel();
        btnBack = new customPackages.RoundedButton();
        imgBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cashier Login");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUserName.setBorder(null);
        getContentPane().add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 172, 125, 25));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPassword.setBorder(null);
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 226, 135, -1));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password");
        getContentPane().add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 222, 100, -1));

        lblUserName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));
        lblUserName.setText("User Name");
        getContentPane().add(lblUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 171, 100, -1));

        btnLogin.setText("Login");
        btnLogin.setBorderColor(new java.awt.Color(255, 255, 255));
        btnLogin.setColor(new java.awt.Color(255, 204, 0));
        btnLogin.setColorClick(new java.awt.Color(255, 172, 0));
        btnLogin.setColorOver(new java.awt.Color(255, 230, 0));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogin.setRadius(40);
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 110, 40));

        lblTitle.setFont(new java.awt.Font("Fredericka the Great", 0, 34)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("FOOD CORNER");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 50, -1, -1));

        imgTitle.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\images\\Cashier.png"));
        getContentPane().add(imgTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 87, -1, -1));

        btnBack.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\images\\back-button 32.png"));
        btnBack.setBorderColor(new java.awt.Color(0, 0, 0));
        btnBack.setColor(new java.awt.Color(255, 204, 0));
        btnBack.setColorClick(new java.awt.Color(230, 184, 0));
        btnBack.setColorOver(new java.awt.Color(255, 219, 77));
        btnBack.setRadius(50);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 50, 50));

        imgBackground.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\images\\LoginBackground.png"));
        imgBackground.setText("jLabel1");
        getContentPane().add(imgBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection db_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_management_system_db", "root", "");
            String query = "select * from users where (UserName=? and Password=?) and Position='Cashier'";
            PreparedStatement prepared_statement = db_connection.prepareStatement(query);
            prepared_statement.setString(1, txtUserName.getText());
            prepared_statement.setString(2, txtPassword.getText());
            ResultSet db_resultSet = prepared_statement.executeQuery();

            if(db_resultSet.next()){       
                CashierScreen cashierScreen = new CashierScreen();
                cashierScreen.setVisible(true);
                this.setVisible(false);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this,"Invaild User Name or Password","Error",JOptionPane.WARNING_MESSAGE);
            }
            
        }catch(Exception e){   
            JOptionPane.showMessageDialog(this,e,"Database Error",JOptionPane.ERROR_MESSAGE);     
        }
    }//GEN-LAST:event_btnLoginMouseClicked

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked

        this.setVisible(false);
        this.dispose();

    }//GEN-LAST:event_btnBackMouseClicked

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
            java.util.logging.Logger.getLogger(LoginScreen_Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginScreen_Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginScreen_Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen_Cashier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen_Cashier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customPackages.RoundedButton btnBack;
    private customPackages.RoundedButton btnLogin;
    private javax.swing.JLabel imgBackground;
    private javax.swing.JLabel imgTitle;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
