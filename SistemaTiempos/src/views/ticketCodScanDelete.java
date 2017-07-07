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
import Clases.TicketPrinter;
import Clases.TicketTime;
import Clases.TimeNumber;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistematiempos.ChineseLanguage;
import sistematiempos.SpanishLanguage;

/**
 *
 * @author Joha
 */
public class ticketCodScanDelete extends javax.swing.JFrame {

    /**
     * Creates new form ticketCodScanDelete
     */
    private DefaultListModel numbersList = new DefaultListModel();
    private DefaultListModel moneyList = new DefaultListModel();
    private int idToFindTicket;
    private DefaultTableModel tableModel;
    private Ticket ticket;
    private List<SoldNumbers>list;
    private String hour;
    private Selling currentSelling;
    private String store, lblTicket, btnSearch, lblId, lblTotal, lblTime, lblDate, btnPrint, btnDelete;
    
    public ticketCodScanDelete() {
        initComponents();
        visibleFalseComponents(false); 
    }
    
    private void setWindowToSelectedLanguage() {
        labelTicket.setText(lblTicket);
        findTicketButton.setText(btnSearch);
        labelIdTicket.setText(lblId);
        labelTotal.setText(lblTotal);
        labelTime.setText(lblTime);
        labelDate.setText(lblDate);
        printButton.setText(btnPrint);
        deleteButton.setText(btnDelete);
    }
    
    public void setLanguageToSpanish() {
        SpanishLanguage spanishLanguage = SpanishLanguage.getInstance();
        lblTicket = spanishLanguage.getLblTicket();
        btnSearch = spanishLanguage.getBtnSearch();
        lblId = spanishLanguage.getLblTicket();
        lblTotal = spanishLanguage.getLblTotalString();
        lblTime = spanishLanguage.getLblTime();
        lblDate = spanishLanguage.getLblDate();
        btnPrint = spanishLanguage.getBtnPrint();
        btnDelete = spanishLanguage.getBtnDelete();
        setWindowToSelectedLanguage();
    }
    
    public void setLanguageToChinese() {
        ChineseLanguage chineseLanguage = ChineseLanguage.getInstance();
        lblTicket = chineseLanguage.getLblTicket();
        btnSearch = chineseLanguage.getBtnSearch();
        lblId = chineseLanguage.getLblTicket();
        lblTotal = chineseLanguage.getLblTotalString();
        lblTime = chineseLanguage.getLblTime();
        lblDate = chineseLanguage.getLblDate();
        btnPrint = chineseLanguage.getBtnPrint();
        btnDelete = chineseLanguage.getBtnDelete();
        setWindowToSelectedLanguage();
    }
    
    public void setSelling(Selling selling) {
        currentSelling = selling;
    }
    
    private void removeAllItemsFromList() {
        String[] headers = {"Numeros","Dinero"};
        tableModel = new DefaultTableModel(null, headers);
        jTable1.setModel(tableModel);
    }
    
    public void setAll(){
        mensaje.setForeground(Color.red);
        dateTxt.setText("");                
        ticketIdTxt.setText("");
        timeTxt.setText("");
        totalTxt.setText("");        
        mensaje.setText("");
        numbersList= new DefaultListModel();
        moneyList= new DefaultListModel();
        removeAllItemsFromList();
    }
    
    public void visibleFalseComponents(boolean bool){
        labelDate.setVisible(bool);
        dateTxt.setVisible(bool);    
        labelIdTicket.setVisible(bool);
        ticketIdTxt.setVisible(bool);
        labelTime.setVisible(bool);
        timeTxt.setVisible(bool);
        labelTotal.setVisible(bool);
        totalTxt.setVisible(bool);  
        deleteButton.setVisible(bool);
        printButton.setVisible(bool);             
        panel.setVisible(bool);
    }
    
    public void findIdTicket(){
        String idTicket= idFindTxt.getText();
        if(idTicket.equals("")){
            mensaje.setText("Se debe de ingresar un dato");
            return;            
        }
        idToFindTicket= Integer.parseInt(idTicket);                       
        ConnectionBD con= new ConnectionBD();                                
        ticket= con.getTicketInformationFind(idToFindTicket);        
        if(ticket== null){            
            mensaje.setText("El tiquete no es válido");
            return;
        }                
        list=con.GetNumberSoldFromTiicket(idTicket);                
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
            store=board.getStore();
        }        
        totalTxt.setText(String.valueOf(ticket.getTicketTotalAmount())); 
        TicketTime tiempo= con.getTicketTime(idTicket);
        timeTxt.setText(tiempo.getTime());                      
        dateTxt.setText(ticket.getDate());                                 
        ticketIdTxt.setText(idTicket);  
        hour= ticket.getTimeHour();
        visibleFalseComponents(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idFindTxt = new javax.swing.JTextField();
        labelTicket = new javax.swing.JLabel();
        findTicketButton = new javax.swing.JButton();
        labelTime = new javax.swing.JLabel();
        labelIdTicket = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        labelDate = new javax.swing.JLabel();
        dateTxt = new javax.swing.JTextField();
        ticketIdTxt = new javax.swing.JTextField();
        totalTxt = new javax.swing.JTextField();
        timeTxt = new javax.swing.JTextField();
        printButton = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        mensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTicket.setText("Tiquete");

        findTicketButton.setText("Buscar");
        findTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findTicketButtonActionPerformed(evt);
            }
        });

        labelTime.setText("Tiempo");

        labelIdTicket.setText("Tiquete");

        labelTotal.setText("Total");

        labelDate.setText("Fecha");

        dateTxt.setEditable(false);

        ticketIdTxt.setEditable(false);

        totalTxt.setEditable(false);

        timeTxt.setEditable(false);

        printButton.setText("Imprimir");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Eliminar");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(deleteButton)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(deleteButton))
        );

        mensaje.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(labelTicket)
                .addGap(18, 18, 18)
                .addComponent(idFindTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(findTicketButton)
                .addContainerGap(156, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(printButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDate)
                            .addComponent(labelTime)
                            .addComponent(labelTotal)
                            .addComponent(labelIdTicket))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dateTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(timeTxt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ticketIdTxt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalTxt, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(mensaje)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findTicketButton)
                    .addComponent(idFindTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTicket))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(mensaje)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ticketIdTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelIdTicket))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTotal)
                            .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTime)
                            .addComponent(timeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDate)
                            .addComponent(dateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addComponent(printButton)
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void findTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findTicketButtonActionPerformed
        // TODO add your handling code here:
        setAll();
        findIdTicket();        
    }//GEN-LAST:event_findTicketButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:        
        ConnectionBD con= new ConnectionBD();
        Board board = con.getBoardInformation();
        TicketTime ticketTime = con.getTicketTime(String.valueOf(ticket.getTicket()));
        for (int i = 0; i < list.size(); i++) {
            TimeNumber timeNumber = con.getBoardNumberPricing(board.getBoard(),ticketTime.getTime(), list.get(i).getNumber());
            int total = timeNumber.getTotalNumberAmount() + list.get(i).getMoneySold();
            con.updateTimeNumber(board.getBoard(), ticketTime.getTime(), list.get(i).getNumber(), total);
        }
        con.deleteTicket(idToFindTicket);
        setAll();
        visibleFalseComponents(false);
        mensaje.setText("El tiquete ha sido eliminado");
        mensaje.setForeground(Color.green);
        currentSelling.removeAllItemsFromList();
        currentSelling.soldNumbersOfTableSetColors();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:    
        TicketPrinter ticketPrinter= new TicketPrinter();
        try {
            ticketPrinter.imprimirFactura(ticketIdTxt.getText(),store,timeTxt.getText(),dateTxt.getText(), hour,
                    idToFindTicket, tableModel, totalTxt.getText());
        } catch (IOException ex) {
            Logger.getLogger(TicketWindowPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            java.util.logging.Logger.getLogger(ticketCodScanDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ticketCodScanDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ticketCodScanDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ticketCodScanDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ticketCodScanDelete().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dateTxt;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton findTicketButton;
    private javax.swing.JTextField idFindTxt;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelIdTicket;
    private javax.swing.JLabel labelTicket;
    private javax.swing.JLabel labelTime;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel mensaje;
    private javax.swing.JPanel panel;
    private javax.swing.JButton printButton;
    private javax.swing.JTextField ticketIdTxt;
    private javax.swing.JTextField timeTxt;
    private javax.swing.JTextField totalTxt;
    // End of variables declaration//GEN-END:variables
}
