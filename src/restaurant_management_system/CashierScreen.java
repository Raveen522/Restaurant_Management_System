/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restaurant_management_system;
import customPackages.RoundedButton;
import java.util.Date ;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlBaseWriter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author ravin
 */
public class CashierScreen extends javax.swing.JFrame {
    Date date = new Date();
    SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
    
    Timer timer;
    SimpleDateFormat time_format = new SimpleDateFormat("hh:mm:ss a");
    
    Connection dbConnection;
    Statement dbStatement;
    PreparedStatement dbPrepareStatement;
    String query;
    double bill_total = 0.0;
    int current_Bill_ID=0;

        
    /**
     * Creates new form CashierScreen
     */
    public CashierScreen() {
        initComponents();
        
        lblDate.setText(date_format.format(date));
        
        current_time();
        load_data("");
        load_current_bill_ID();
        load_categories();
    }
    
    public void load_categories(){
        try {
            query = "SELECT DISTINCT category_name FROM categories;";
            ResultSet db_resultSet = dbStatement.executeQuery(query);
                   
            while (db_resultSet.next()) {
                String category = db_resultSet.getString("category_name");
                
                RoundedButton cat_button = new RoundedButton();
                
                cat_button.setRadius(20);
                cat_button.setPreferredSize(new Dimension(80,45));
                cat_button.setColor(new java.awt.Color(255,204,0)); 
                cat_button.setBorderColor(Color.white);
                cat_button.setColorClick(new java.awt.Color(230,184,0));
                cat_button.setColorOver(new java.awt.Color(255,219,77));
                cat_button.setText(category);
                cat_button.getText();
                cat_button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DefaultTableModel item_table_Model = (DefaultTableModel)tblFoodItems.getModel();
                        item_table_Model.setRowCount(0);
                        load_data(cat_button.getText());
                        
                    }
                });       
                pnlCategory.add(cat_button);
            }
        } catch (SQLException sQLException) {
            System.out.println(sQLException);
        }
        
    }
    public void load_current_bill_ID(){
        try {
            query = "SELECT MAX(ID) FROM bill_details;";
            ResultSet db_resultSet = dbStatement.executeQuery(query);
            
            while (db_resultSet.next()) {
                current_Bill_ID = db_resultSet.getInt("MAX(ID)");
            }   
            lblRefNo.setText(Integer.toString(current_Bill_ID+1));
            
        } catch (SQLException sQLException) {
            System.out.print(sQLException);
        }
    }
    public void reset_entries(){
        DefaultTableModel item_table_Model = (DefaultTableModel)tblFoodItems.getModel();
        DefaultTableModel bill_table_Model = (DefaultTableModel)tblBill.getModel();
        item_table_Model.setRowCount(0);
        bill_table_Model.setRowCount(0);
        lblTotalPrice.setText("Rs.00.00");
        load_current_bill_ID();
    }
    public void load_data(String seleted_category){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_management_system_db","root","");
            dbStatement = dbConnection.createStatement();
            
            if(seleted_category!=""){
                query = "SELECT * FROM food_items WHERE Category='"+seleted_category+"'";
            }else{
                query = "SELECT * FROM food_items;";
            }

            ResultSet db_resultSet = dbStatement.executeQuery(query);
            
            while (db_resultSet.next()){
                String id = String.valueOf(db_resultSet.getInt("ID"));
                String food_name = db_resultSet.getString("Name");
                String category = db_resultSet.getString("Category");
                String price = db_resultSet.getString("Price");
                
                String tbl_Data[]= {id,food_name,category,price};
                DefaultTableModel item_table_Model = (DefaultTableModel)tblFoodItems.getModel();
                item_table_Model.addRow(tbl_Data);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CashierScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CashierScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
    public void current_time(){
        timer = new Timer(0, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date time = new java.util.Date();
                lblTime.setText(time_format.format(time));
            }
        });
        timer.start();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRefNo = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btn_Pay_Now = new customPackages.RoundedButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBill = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFoodItems = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        pnlCategory = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAllCategories = new customPackages.RoundedButton();
        lblTitle = new javax.swing.JLabel();
        btnBack = new customPackages.RoundedButton();
        imgBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cashier");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRefNo.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lblRefNo.setText("0120");
        getContentPane().add(lblRefNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 130, 80, 20));

        lblDate.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lblDate.setText("14/11/2022");
        getContentPane().add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 160, 110, 20));

        lblTime.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        lblTime.setText("5:07 AM");
        getContentPane().add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 180, 110, 20));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("Time:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 80, 20));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setText("Bill Ref. No:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 130, 80, 20));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel4.setText("Date:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160, 80, 20));

        lblUserName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUserName.setText("Cashier");
        getContentPane().add(lblUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 220, 330, 10));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 680, 330, 10));

        btn_Pay_Now.setText("Pay Now");
        btn_Pay_Now.setBorderColor(new java.awt.Color(0, 0, 0));
        btn_Pay_Now.setColor(new java.awt.Color(255, 205, 0));
        btn_Pay_Now.setColorClick(new java.awt.Color(255, 153, 0));
        btn_Pay_Now.setColorOver(new java.awt.Color(255, 230, 0));
        btn_Pay_Now.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_Pay_Now.setRadius(40);
        btn_Pay_Now.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Pay_NowMouseClicked(evt);
            }
        });
        btn_Pay_Now.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Pay_NowActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Pay_Now, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 720, 110, 40));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 690, 330, 10));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 640, 330, 10));

        tblBill.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        tblBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Unit Price", "Quantity", "Total"
            }
        ));
        tblBill.setSelectionBackground(new java.awt.Color(255, 204, 51));
        jScrollPane1.setViewportView(tblBill);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, 320, 400));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Total");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 645, -1, -1));

        lblTotalPrice.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalPrice.setText("Rs.00.00");
        lblTotalPrice.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(lblTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 645, 170, -1));

        tblFoodItems.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        tblFoodItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Category", "Price"
            }
        ));
        tblFoodItems.setOpaque(false);
        tblFoodItems.setSelectionBackground(new java.awt.Color(255, 204, 0));
        tblFoodItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFoodItemsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFoodItems);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 730, 440));

        pnlCategory.setOpaque(false);
        pnlCategory.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jScrollPane3.setViewportView(pnlCategory);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 750, 80));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 0, 153));
        jLabel5.setText("Food Items");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 0, 153));
        jLabel6.setText("Categories");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 115, -1, -1));

        btnAllCategories.setText("All");
        btnAllCategories.setBorderColor(new java.awt.Color(255, 255, 255));
        btnAllCategories.setColor(new java.awt.Color(255, 204, 0));
        btnAllCategories.setColorClick(new java.awt.Color(230, 184, 0));
        btnAllCategories.setColorOver(new java.awt.Color(255, 219, 77));
        btnAllCategories.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAllCategories.setRadius(20);
        btnAllCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAllCategoriesMouseClicked(evt);
            }
        });
        getContentPane().add(btnAllCategories, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 80, -1));

        lblTitle.setFont(new java.awt.Font("Fredericka the Great", 0, 34)); // NOI18N
        lblTitle.setText("FOOD CORNER");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

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

        imgBackground.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\images\\CashierBackground.jpg"));
        imgBackground.setText("jLabel1");
        getContentPane().add(imgBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 830));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Pay_NowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Pay_NowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Pay_NowActionPerformed

    private void btn_Pay_NowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Pay_NowMouseClicked
        
        
        
        int bill_confirmation = JOptionPane.showConfirmDialog(this,"Are you sure do you want to continue payment?","Payment Confirmation",JOptionPane.YES_NO_OPTION);
        if (bill_confirmation == JOptionPane.YES_OPTION){
            try {
                String items_list = "";

                int num_rows = tblBill.getRowCount();

                for (int i =0; i < num_rows; i++){
                  items_list = items_list + "," + tblBill.getValueAt(i, 0).toString() + " x" + tblBill.getValueAt(i, 2).toString() ;
                }
              
                query = "INSERT INTO bill_details VALUES(?,?,?,?,?,?)";
                dbPrepareStatement = dbConnection.prepareStatement(query);

                dbPrepareStatement.setInt(1, Integer.parseInt(lblRefNo.getText()));
                dbPrepareStatement.setString(2,lblDate.getText() );
                dbPrepareStatement.setString(3, lblTime.getText());
                dbPrepareStatement.setInt(4, num_rows);
                dbPrepareStatement.setString(5, items_list);
                dbPrepareStatement.setDouble(6, bill_total);


                dbPrepareStatement.executeUpdate();

                for (int i =0; i < num_rows; i++){
                
                    query = "INSERT INTO temp_bill VALUES(?,?,?,?)";
                    dbPrepareStatement = dbConnection.prepareStatement(query);

                    
                    dbPrepareStatement.setString(1,tblBill.getValueAt(i, 0).toString());
                    dbPrepareStatement.setDouble(2, Double.parseDouble(tblBill.getValueAt(i, 1).toString()) );
                    dbPrepareStatement.setInt(3, Integer.parseInt(tblBill.getValueAt(i, 2).toString()));
                    dbPrepareStatement.setDouble(4, Double.parseDouble(tblBill.getValueAt(i, 3).toString()));

                    dbPrepareStatement.executeUpdate();
                }
                
                // report printing code here.....
                URL reportFileURL = getClass().getResource("/restaurant_management_system/bill.jrxml");
                
                JasperDesign report_file = JRXmlLoader.load(reportFileURL.openStream());
                String report_query = "SELECT * FROM temp_bill";
                
                JRDesignQuery update_query = new JRDesignQuery();
                update_query.setText(report_query);
                report_file.setQuery(update_query);
                
                JasperReport jreport = JasperCompileManager.compileReport(report_file);
                JasperPrint jprint = JasperFillManager.fillReport(jreport, null,dbConnection);
                
//                JasperViewer.viewReport(jprint);
                JasperViewer.viewReport(jprint, false);
                
//                JOptionPane.showMessageDialog(this,"Bill payed and saved succesfully","Bill pay",JOptionPane.INFORMATION_MESSAGE);

                String deleteQuery = "DELETE FROM temp_bill";
                dbPrepareStatement = dbConnection.prepareStatement(deleteQuery);
                dbPrepareStatement.executeUpdate();
                
                dbPrepareStatement.close();
                reset_entries();
                load_data("");

            } catch (SQLException sQLException) {
                System.out.println(sQLException);
            } catch (NumberFormatException numberFormatException) {
                System.out.println(numberFormatException);
            } catch (JRException ex) {
                Logger.getLogger(CashierScreen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CashierScreen.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }else{
            String path = getClass().getClassLoader().getResource("").getPath();
            System.out.println(path);
        }
        
      
    }//GEN-LAST:event_btn_Pay_NowMouseClicked

    private void tblFoodItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFoodItemsMouseClicked
        try {
            DefaultTableModel item_table_Model = (DefaultTableModel) tblFoodItems.getModel();
            DefaultTableModel bill_table_Model = (DefaultTableModel) tblBill.getModel();
            
            String item_Name = item_table_Model.getValueAt(tblFoodItems.getSelectedRow(), 1).toString();
            double item_price = Double.parseDouble(item_table_Model.getValueAt(tblFoodItems.getSelectedRow(), 3).toString());
            
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, item_Name,"Enter Quantity",JOptionPane.PLAIN_MESSAGE));
            
            if(quantity!=0){
                double item_total = item_price * quantity;
            
                String bill_item_row[] = {item_Name, Double.toString(item_price), Integer.toString(quantity), Double.toString(item_total)};
                bill_table_Model.addRow(bill_item_row);
            
                bill_total = bill_total + item_total;
            
                lblTotalPrice.setText("Rs." + Double.toString(bill_total));
            }

        } catch (NumberFormatException numberFormatException) {
            
        } catch (HeadlessException headlessException) {
        }
    }//GEN-LAST:event_tblFoodItemsMouseClicked

    private void btnAllCategoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAllCategoriesMouseClicked
        DefaultTableModel item_table_Model = (DefaultTableModel)tblFoodItems.getModel();
        item_table_Model.setRowCount(0);
        load_data("");
    }//GEN-LAST:event_btnAllCategoriesMouseClicked

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
            java.util.logging.Logger.getLogger(CashierScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashierScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashierScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashierScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashierScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customPackages.RoundedButton btnAllCategories;
    private customPackages.RoundedButton btnBack;
    private customPackages.RoundedButton btn_Pay_Now;
    private javax.swing.JLabel imgBackground;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblRefNo;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel pnlCategory;
    private javax.swing.JTable tblBill;
    private javax.swing.JTable tblFoodItems;
    // End of variables declaration//GEN-END:variables
}
