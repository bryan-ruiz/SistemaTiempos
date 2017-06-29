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
import Clases.TimeNumber;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import sistematiempos.ChineseLanguage;
import sistematiempos.SpanishLanguage;

/**
 *
 * @author Joha
 */
public class TicketWindowPrint extends javax.swing.JFrame {
    
    /**
     * Creates new form VentanaTiquete
     */
    private String language;
    private int totalAmount= 0;
    private Board board;
    private DefaultTableModel tableModel;
    private int defectMoney= 20000;
    private String checkBoxTimeselected;
    private String idBoard;
    
    public TicketWindowPrint(int action) {
        initComponents();
        removeAllItemsFromList();        
        if(action== -1){
            showInformationPrintTotalU();
        }                
        else{                        
            showInformationPay(action);
        }
    }

    
    private void removeAllItemsFromList() {
        String[] headers = {"Numeros","Dinero"};
        tableModel = new DefaultTableModel(null, headers);
        jTable1.setModel(tableModel);
    }
    
    public void showInformationPay(int idToFindTicket){
        firmaLabelAbajo.setVisible(false);
        espacioFirma.setVisible(false);
        checkDay.setVisible(false);
        checkNight.setVisible(false);
        Ticket ticket= null;
        ConnectionBD con= new ConnectionBD();                
        if(idToFindTicket== 0){
            
            ticket= con.getTicketInformation();                
        }
        else{
            ticket= con.getTicketInformationFind(idToFindTicket);        
        }
        
        String idTicket=String.valueOf(ticket.getTicket());                
        
        ticketTxt.setText(idTicket);
        dateTxt.setText(ticket.getDate());                                 
        hourTxt.setText(ticket.getTimeHour());    
        List<SoldNumbers>list=con.GetNumberSoldFromTiicket(idTicket);                        
        for (int i = 0; i < list.size(); i++) {
            String[] row = new String[2];
            row[0] = String.valueOf(list.get(i).getNumber());            
            String showIt = String.valueOf((list.get(i).getMoneySold()));
            row[1] = showIt;
            tableModel.addRow(row);                                 
        }
        if(list.size()>0){
            int idBoard= list.get(0).getBoard();
            Board board= con.getBoardInformationFind(idBoard);
            barCodeTxt.setText(String.valueOf(board.getBarCode()));
            storeTxt.setText(board.getStore());        
        }
        
        totalMoneyTxt.setText(String.valueOf(ticket.getTicketTotalAmount()));
        
        TicketTime tiempo= con.getTicketTime(idTicket);
        timeTxt.setText(tiempo.getTime());        
    }
    
    public void setLanguageToChinese() {
        ChineseLanguage chineseLanguage = ChineseLanguage.getInstance();
        language = chineseLanguage.getBtnPrint();
        printButton.setText(language);
    }
    
    public void setLanguageToSpanish() {
        SpanishLanguage spanishLanguage = SpanishLanguage.getInstance();
        language = spanishLanguage.getBtnPrint();
        printButton.setText(language);
    }
    
    
    public boolean selectCheckBox(){
        if(checkDay.isSelected() ){
            checkBoxTimeselected= checkDay.getText();
            return true;
        }
        else if (checkNight.isSelected()){
            checkBoxTimeselected= checkNight.getText();
            return true;
        }
        else{
            return false;
        }        
    }
    
    private void listOfNumbers(ConnectionBD con){        
        totalAmount= 0;        
        if(selectCheckBox()== false){
            return;
        }
        List<TimeNumber>list=con.getSoldBoardNumbersDependingOnTime(idBoard,checkBoxTimeselected);
        for (int i = 0; i < list.size(); i++) {            
            totalAmount= totalAmount+ (defectMoney -(list.get(i).getTotalNumberAmount()));
            String[] row = new String[2];
            row[0] = String.valueOf(list.get(i).getNumero());            
            String showIt = String.valueOf(defectMoney-(list.get(i).getTotalNumberAmount()));
            row[1] = showIt;
            tableModel.addRow(row);            
        }                 
        totalMoneyTxt.setText(String.valueOf(totalAmount));        
    }
    
    public void showInformationPrintTotalU(){
        barCodeTxt.setVisible(false);               
        hourTxt.setVisible(false);
        
        ConnectionBD con= new ConnectionBD();        
        board= con.getBoardInformation();                
        idBoard=String.valueOf(board.getBoard());        
        dateTxt.setText(board.getDate());
        ticketLabel.setText("Tablero");        
        ticketTxt.setText(idBoard);                        
        storeTxt.setText(board.getStore());
        String boarTime= con.getTimeFromBoard(idBoard);        
        timeTxt.setText(boarTime);                
        listOfNumbers(con);
    }        
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        printButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        storeTxt = new javax.swing.JLabel();
        firmaTxt = new javax.swing.JLabel();
        ticketLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        firmaLabelAbajo = new javax.swing.JLabel();
        espacioFirma = new javax.swing.JSeparator();
        barCodeTxt = new javax.swing.JLabel();
        ticketTxt = new javax.swing.JLabel();
        timeTxt = new javax.swing.JLabel();
        dateTxt = new javax.swing.JLabel();
        totalMoneyTxt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        checkNight = new javax.swing.JCheckBox();
        checkDay = new javax.swing.JCheckBox();
        hourTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

        totalLabel.setText("Total");

        firmaLabelAbajo.setText("Firma");

        barCodeTxt.setText("CódigoBarra");

        ticketTxt.setText("txtTiquete");

        timeTxt.setText("txtTiempo");

        dateTxt.setText("txtFecha");

        totalMoneyTxt.setText("montoTotal");

        jLabel1.setText("Tiene 7 dias para cobrar");

        jLabel2.setText("Revise antes de pagar");

        jLabel3.setText("No se aceptan reclamos");

        jLabel4.setText("Sin tiquete no hay pago");

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        buttonGroup1.add(checkNight);
        checkNight.setText("Noche");
        checkNight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkNightActionPerformed(evt);
            }
        });

        buttonGroup1.add(checkDay);
        checkDay.setSelected(true);
        checkDay.setText("Dia");
        checkDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDayActionPerformed(evt);
            }
        });

        hourTxt.setText("txtHour");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeTxt)
                                    .addComponent(dateLabel)
                                    .addComponent(ticketLabel))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeLabel)
                                    .addComponent(ticketTxt)
                                    .addComponent(dateTxt)))
                            .addComponent(storeTxt, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(88, 88, 88)
                                        .addComponent(firmaTxt))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(96, 96, 96)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkNight)
                                            .addComponent(checkDay))))
                                .addGap(75, 75, 75))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hourTxt)
                                .addGap(139, 139, 139))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(totalLabel)
                        .addGap(114, 114, 114)
                        .addComponent(totalMoneyTxt)
                        .addGap(124, 124, 124))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(firmaLabelAbajo)
                        .addGap(35, 35, 35)
                        .addComponent(espacioFirma, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(barCodeTxt)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(storeTxt)
                    .addComponent(firmaTxt))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(checkDay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkNight))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ticketTxt)
                            .addComponent(ticketLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timeLabel)
                            .addComponent(timeTxt))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateLabel)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dateTxt)
                        .addComponent(hourTxt)))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalMoneyTxt)
                    .addComponent(totalLabel))
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(espacioFirma, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(barCodeTxt)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(firmaLabelAbajo)
                        .addGap(95, 95, 95))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(printButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(545, Short.MAX_VALUE)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printButtonActionPerformed

    private void checkNightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNightActionPerformed
        // TODO add your handling code here:
        removeAllItemsFromList();
        ConnectionBD con= new ConnectionBD();
        listOfNumbers(con);
    }//GEN-LAST:event_checkNightActionPerformed

    private void checkDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDayActionPerformed
        // TODO add your handling code here:
        removeAllItemsFromList();
        ConnectionBD con= new ConnectionBD();
        listOfNumbers(con);
    }//GEN-LAST:event_checkDayActionPerformed

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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkDay;
    private javax.swing.JCheckBox checkNight;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dateTxt;
    private javax.swing.JSeparator espacioFirma;
    private javax.swing.JLabel firmaLabelAbajo;
    private javax.swing.JLabel firmaTxt;
    private javax.swing.JLabel hourTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
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
