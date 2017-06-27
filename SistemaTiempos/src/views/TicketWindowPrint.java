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
    private DefaultListModel numbersList = new DefaultListModel();
    private DefaultListModel moneyList = new DefaultListModel();
    private String conditionsBD= "", language;
    private int totalAmount= 0;
    private Board board;
    
    public TicketWindowPrint(int action) {
        initComponents();
        if(action== -1){
            showInformationPrintTotalU();
        }                
        else{            
            showInformationPay(action);
        }
    }
    
    public void showInformationPay(int idToFindTicket){
        firmaLabelAbajo.setVisible(false);
        espacioFirma.setVisible(false);
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
        
        List<SoldNumbers>lista=con.GetNumberSoldFromTiicket(idTicket);                        
        for (int i = 0; i < lista.size(); i++) {
            numbersList.addElement(lista.get(i).getNumber());
            moneyList.addElement(lista.get(i).getMoneySold());            
            totalAmount= lista.get(i).getMoneySold()+totalAmount;
        }
        if(lista.size()>0){
            int idBoard= lista.get(0).getBoard();
            Board board= con.getBoardInformationFind(idBoard);
            barCodeTxt.setText(String.valueOf(board.getBarCode()));
            storeTxt.setText(board.getStore());        
        }
        listNumbersTxt.setModel(numbersList);
        listMoneyTxt.setModel(moneyList);
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
    
    public void showInformationPrintTotalU(){
        barCodeTxt.setVisible(false);
        dateLabel.setVisible(false);
        dateTxt.setVisible(false);
        
        ConnectionBD con= new ConnectionBD();
        board= con.getBoardInformation();                
        String idBoard=String.valueOf(board.getBoard());        
        
        ticketLabel.setText("Tablero");        
        ticketTxt.setText(idBoard);                        
        storeTxt.setText(board.getStore());
        String boarTime= con.getTimeFromBoard(idBoard);
        System.out.println("ressss:   "+boarTime);
        timeTxt.setText(boarTime);
        //con.getSoldBoardNumbers(idBoard); 
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
        dateTxt.setText(String.valueOf(ticket.getDate()));         
        
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

        numberLabel.setText("Número");

        moneyLabel.setText("Plata");

        listNumbersTxt.setEnabled(false);
        listNumbersTxt.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(listNumbersTxt);

        listMoneyTxt.setEnabled(false);
        jScrollPane2.setViewportView(listMoneyTxt);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(firmaLabelAbajo)
                            .addGap(18, 18, 18)
                            .addComponent(espacioFirma, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(58, 58, 58)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(storeTxt)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(timeLabel)
                                                .addComponent(dateLabel))
                                            .addGap(35, 35, 35)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(timeTxt)
                                                .addComponent(dateTxt)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(ticketLabel)
                                            .addGap(33, 33, 33)
                                            .addComponent(ticketTxt))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(5, 5, 5)
                                            .addComponent(numberLabel)
                                            .addGap(71, 71, 71)
                                            .addComponent(moneyLabel))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(85, 85, 85)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(totalLabel)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(19, 19, 19)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(115, 115, 115)
                                            .addComponent(firmaTxt))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(73, 73, 73)
                                            .addComponent(totalMoneyTxt))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(10, 10, 10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(barCodeTxt)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(firmaTxt)
                .addGap(11, 11, 11)
                .addComponent(storeTxt)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ticketLabel)
                        .addGap(9, 9, 9)
                        .addComponent(timeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(ticketTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateTxt)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numberLabel)
                            .addComponent(moneyLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalMoneyTxt)))
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(espacioFirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firmaLabelAbajo))
                .addGap(18, 18, 18)
                .addComponent(barCodeTxt)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(printButton)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(513, Short.MAX_VALUE)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dateTxt;
    private javax.swing.JSeparator espacioFirma;
    private javax.swing.JLabel firmaLabelAbajo;
    private javax.swing.JLabel firmaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
