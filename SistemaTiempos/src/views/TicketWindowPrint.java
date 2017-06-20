/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import BD.ConnectionBD;
import Clases.Board;
import Clases.SoldNumbers;
import Clases.Ticket;
import Clases.TicketTime;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Joha
 */
public class TicketWindowPrint extends javax.swing.JFrame {
    
    /**
     * Creates new form VentanaTiquete
     */
    private DefaultListModel numbersList = new DefaultListModel();
    private DefaultListModel moneyList = new DefaultListModel();
    private String conditionsBD= "";
    private int totalAmount= 0;
    
    public TicketWindowPrint(int action) {
        initComponents();
        if(action== 1){
            showInformationPay();
        }                
        else{
            showInformationPrintTotalU();
        }
    }
    
    public void showInformationPrintTotalU(){
        barCodeTxt.setVisible(false);
        dateLabel.setVisible(false);
        dateTxt.setVisible(false);
        
        ConnectionBD con= new ConnectionBD();
        Board board= con.getBoardInformation();                
        String idBoard=String.valueOf(board.getBoard());        
        
        ticketLabel.setText("Tablero");        
        ticketTxt.setText(idBoard);                        
        storeTxt.setText(board.getStore());
        
        con.getSoldBoardNumbers(idBoard); 
        List<SoldNumbers>list=con.getSoldBoardNumbers(idBoard);        
        for (int i = 0; i < list.size(); i++) {
            numbersList.addElement(list.get(i).getNumber());
            moneyList.addElement(list.get(i).getMoneySold());            
            totalAmount= list.get(i).getMoneySold()+totalAmount;
        }    
        listNumbersTxt.setModel(numbersList);
        listMoneyTxt.setModel(moneyList);
        totalMoneyTxt.setText(String.valueOf(totalAmount));                        
    }
    
    public void showInformationPay(){
        firmaLabelAbajo.setVisible(false);
        espacioFirma.setVisible(false);
        
        ConnectionBD con= new ConnectionBD();                
        Ticket ticket= con.getTicketInformation();                
        String idTicket=String.valueOf(ticket.getTicket());                
        
        ticketTxt.setText(idTicket);
        dateTxt.setText(ticket.getDate());         
        
        Board board= con.getBoardInformation();                
        storeTxt.setText(board.getStore());
        barCodeTxt.setText(String.valueOf(board.getBarCode()));
        
        List<SoldNumbers>lista=con.GetNumberSoldFromTiicket(idTicket);                
        for (int i = 0; i < lista.size(); i++) {
            numbersList.addElement(lista.get(i).getNumber());
            moneyList.addElement(lista.get(i).getMoneySold());            
            totalAmount= lista.get(i).getMoneySold()+totalAmount;
        }
        listNumbersTxt.setModel(numbersList);
        listMoneyTxt.setModel(moneyList);
        totalMoneyTxt.setText(String.valueOf(ticket.getTicketTotalAmount()));
        
        TicketTime tiempo= con.getTicketTime(idTicket);
        timeTxt.setText(tiempo.getTime());
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        printButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        storeTxt = new javax.swing.JLabel();
        firmaTxt = new javax.swing.JLabel();
        ticketLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        numberLabel = new javax.swing.JLabel();
        moneyLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listNumbersTxt = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMoneyTxt = new javax.swing.JList<>();
        totalLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        conditionsTxt = new javax.swing.JTextArea();
        firmaLabelAbajo = new javax.swing.JLabel();
        espacioFirma = new javax.swing.JSeparator();
        barCodeTxt = new javax.swing.JLabel();
        ticketTxt = new javax.swing.JLabel();
        timeTxt = new javax.swing.JLabel();
        dateTxt = new javax.swing.JLabel();
        totalMoneyTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        printButton.setText("Imprimir");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        storeTxt.setText("Nombre del comercio");

        firmaTxt.setText("Firma");

        ticketLabel.setText("Tiquete");

        timeLabel.setText("Tiempo");

        dateLabel.setText("Fecha");

        numberLabel.setText("Número");

        moneyLabel.setText("Plata");

        listNumbersTxt.setEnabled(false);
        listNumbersTxt.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(listNumbersTxt);

        listMoneyTxt.setEnabled(false);
        jScrollPane2.setViewportView(listMoneyTxt);

        totalLabel.setText("Total");

        conditionsTxt.setEditable(false);
        conditionsTxt.setColumns(20);
        conditionsTxt.setRows(5);
        conditionsTxt.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        conditionsTxt.setEnabled(false);
        jScrollPane3.setViewportView(conditionsTxt);

        firmaLabelAbajo.setText("Firma");

        barCodeTxt.setText("CódigoBarra");

        ticketTxt.setText("txtTiquete");

        timeTxt.setText("txtTiempo");

        dateTxt.setText("txtFecha");

        totalMoneyTxt.setText("montoTotal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(barCodeTxt)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(totalLabel)
                                .addGap(63, 63, 63))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(totalMoneyTxt))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(firmaTxt)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(firmaLabelAbajo)
                        .addGap(18, 18, 18)
                        .addComponent(espacioFirma, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(numberLabel)
                        .addGap(79, 79, 79)
                        .addComponent(moneyLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(storeTxt)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dateLabel)
                                    .addComponent(timeLabel)
                                    .addComponent(ticketLabel))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeTxt)
                                    .addComponent(ticketTxt)
                                    .addComponent(dateTxt))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(firmaTxt)
                .addGap(18, 18, 18)
                .addComponent(storeTxt)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ticketTxt)
                            .addComponent(ticketLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateTxt))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(timeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateLabel)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numberLabel)
                            .addComponent(moneyLabel))))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalLabel)
                            .addComponent(totalMoneyTxt))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(espacioFirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(firmaLabelAbajo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(45, 45, 45)
                .addComponent(barCodeTxt)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(printButton)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printButtonActionPerformed

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
            java.util.logging.Logger.getLogger(TicketWindowPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicketWindowPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicketWindowPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicketWindowPrint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barCodeTxt;
    private javax.swing.JTextArea conditionsTxt;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dateTxt;
    private javax.swing.JSeparator espacioFirma;
    private javax.swing.JLabel firmaLabelAbajo;
    private javax.swing.JLabel firmaTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listMoneyTxt;
    private javax.swing.JList<String> listNumbersTxt;
    private javax.swing.JLabel moneyLabel;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel storeTxt;
    private javax.swing.JLabel ticketLabel;
    private javax.swing.JLabel ticketTxt;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel timeTxt;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalMoneyTxt;
    // End of variables declaration//GEN-END:variables
}
