//Package:
package views;

//Imports:
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Customer;
import models.Order;


public class ViewPreviousOrders extends javax.swing.JFrame
{

    //Attributes:
    private Customer loggedInCustomer;
    
    /**
     * Creates new form ViewPreviousOrders
     */
    public ViewPreviousOrders(Customer cust)
    {
        loggedInCustomer = cust;
        
        initComponents();//Load view
        
        DefaultTableModel ordersModel = (DefaultTableModel)tblPreviousOrders.getModel();
        
        for(Map.Entry<Integer, Order> oEntry : loggedInCustomer.getOrders().entrySet())
        {
            Order order = oEntry.getValue();
            
            if(order.getStatus().equals("Complete"))//Shows all completed orders
            {
                ordersModel.addRow(new Object[]//Populate table with previous orders
                {order.getOrderId(),
                    order.getOrderDate(),
                    "£" + String.format("%.02f",order.getOrderTotal())});
            }           
        }
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPreviousOrders = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnViewOrder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tblPreviousOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblPreviousOrders);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Previous Orders");

        btnViewOrder.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnViewOrder.setText("View Order Details");
        btnViewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addGap(77, 77, 77))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnViewOrder)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewOrder)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewOrderActionPerformed
        
        int selectedRow = tblPreviousOrders.getSelectedRow();//Retrieve selection
        
        if(selectedRow == -1)//i.e. nothing selected
        {
            JOptionPane.showMessageDialog(null, "Please select an order to view.");
        }
        
        else
        {
            int orderId = Integer.parseInt(String.valueOf(tblPreviousOrders.getValueAt(selectedRow, 0)));//Retrieve order ID to pass onto View Order Lines
            
            //Navigate to the View Order Lines view for more details on selected order
            ViewPreviousOrderLines previousOrderLines = new ViewPreviousOrderLines(loggedInCustomer, orderId);
            this.dispose();
            previousOrderLines.setVisible(true);          
        }
        
    }//GEN-LAST:event_btnViewOrderActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        
        //Navigate back to the Customer Home page
        CustomerHome home = new CustomerHome(loggedInCustomer);
        this.dispose();
        home.setVisible(true);
        
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
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
            java.util.logging.Logger.getLogger(ViewPreviousOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPreviousOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPreviousOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPreviousOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                //EMPTY
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnViewOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPreviousOrders;
    // End of variables declaration//GEN-END:variables
}
