/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import BD.ConnectionBD;
import Clases.Board;
import Clases.Printsupport;
import Clases.SoldNumbers;
import Clases.Ticket;
import Clases.TicketTime;
import Clases.TimeNumber;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
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
    private int pricing, totalAmount= 0;
    private Board board;
    private int actionSelected;
    private DefaultTableModel tableModel;
    private String checkBoxTimeselected;
    private String idBoard;
    
    public TicketWindowPrint(int action) {
        initComponents();
        removeAllItemsFromList();        
        if(action== -1){
            actionSelected= action;
            showInformationPrintTotalU();
        }                
        else{                        
            actionSelected= action;
            showInformationPay(action);
        }
        getDaysOfComboBox();
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage("W:/SystemConfigFilesProvidedToToSistemaChinos/icono.png");
        return retValue;
    }

    private void removeAllItemsFromList() {
        String[] headers = {"Numeros","Plata"};
        tableModel = new DefaultTableModel(null, headers){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
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
        //dateTxt.setText(ticket.getDate());                                 
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
            pricing = board.getNumbersPrincing();
            storeTxt.setText(board.getStore());        
        }
        totalMoneyTxt.setText(String.valueOf(ticket.getTicketTotalAmount()));
        //TicketTime tiempo= con.getTicketTime(idTicket);
        String tiempo = "Noche";
        if (checkDay.isSelected()) {
            tiempo = "Dia";
        }
        timeTxt.setText(tiempo);        
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
        List<TimeNumber>list=con.getSoldBoardNumbersDependingOnTime(idBoard,checkBoxTimeselected, pricing);
        for (int i = 0; i < list.size(); i++) {            
            totalAmount= totalAmount+ (list.get(i).getTotal() -(list.get(i).getTotalNumberAmount()));
            String[] row = new String[2];
            String numberToConvert = String.valueOf(list.get(i).getNumero());
            if (numberToConvert.length() == 1) {
                numberToConvert = "0"+numberToConvert;
            }
            row[0] = numberToConvert;            
            String showIt = String.valueOf(list.get(i).getTotal() -(list.get(i).getTotalNumberAmount()));
            row[1] = showIt;
            tableModel.addRow(row);            
        }                 
        totalMoneyTxt.setText(String.valueOf(totalAmount));        
        if(list.size()== 0){
            printButton.setEnabled(false);
        }
        else{
            printButton.setEnabled(true);
        }
    }
    
    private void getDaysOfComboBox(){
        for(int i= 1; i<=31; i++){
            dayBegin.addItem(String.valueOf(i));
        }
        for(int i= 1; i<=12; i++){
            monthBegin.addItem(String.valueOf(i));
        }
        for(int i= 2017; i<=2030; i++){
            yearBegin.addItem(String.valueOf(i));
        }
        Calendar cal = Calendar.getInstance(); 
        System.out.println(String.valueOf(cal.get(cal.DATE)));
        System.out.println(String.valueOf(cal.get(cal.MONTH)));
        System.out.println(String.valueOf(cal.get(cal.YEAR)));
        dayBegin.setSelectedItem(String.valueOf(cal.get(cal.DATE)));
        monthBegin.setSelectedItem(String.valueOf(cal.get(cal.MONTH) + 1));
        yearBegin.setSelectedItem(String.valueOf(cal.get(cal.YEAR)));
    }
    
    boolean pressed = false;
    public void showInformationPrintTotalU(){         
        hourTxt.setVisible(false);
        ConnectionBD con= new ConnectionBD();       
        if (pressed) {
            String initialDate= dayBegin.getSelectedItem().toString()+"/"+monthBegin.getSelectedItem().toString()+"/"+yearBegin.getSelectedItem().toString();
            board= con.getBoardInformationByDate(initialDate);
            pressed = false;
        }
        else {
            board= con.getBoardInformation(); 
        }        
        pricing = board.getNumbersPrincing();
        idBoard=String.valueOf(board.getBoard());  
        ticketLabel.setText("Tablero");        
        int numero= Integer.parseInt(idBoard)-1;
        ticketTxt.setText(String.valueOf(numero));                        
        storeTxt.setText(board.getStore());       
        timeTxt.setText("Dia");
        
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
        ticketTxt = new javax.swing.JLabel();
        timeTxt = new javax.swing.JLabel();
        totalMoneyTxt = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        checkNight = new javax.swing.JCheckBox();
        checkDay = new javax.swing.JCheckBox();
        hourTxt = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblMonth = new javax.swing.JLabel();
        dayBegin = new javax.swing.JComboBox<>();
        monthBegin = new javax.swing.JComboBox<>();
        yearBegin = new javax.swing.JComboBox<>();
        lblDay = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
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

        ticketTxt.setText("txtTiquete");

        timeTxt.setText("txtTiempo");

        totalMoneyTxt.setText("montoTotal");

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

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblMonth.setText("mes");

        lblDay.setText("día");

        lblYear.setText("año");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateLabel)
                                    .addComponent(ticketLabel))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ticketTxt)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dayBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblDay))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblMonth)
                                            .addComponent(monthBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(13, 13, 13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(timeLabel)
                                .addGap(31, 31, 31)
                                .addComponent(timeTxt))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(storeTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkDay)
                            .addComponent(checkNight)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(hourTxt))
                            .addComponent(firmaTxt)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(yearBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))))
                .addGap(196, 196, 196))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(totalLabel)
                                .addGap(18, 18, 18)
                                .addComponent(totalMoneyTxt))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(firmaLabelAbajo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(espacioFirma, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeTxt)
                    .addComponent(firmaTxt))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ticketTxt)
                    .addComponent(ticketLabel)
                    .addComponent(checkDay))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeTxt)
                    .addComponent(timeLabel)
                    .addComponent(checkNight))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(hourTxt)
                    .addComponent(lblDay)
                    .addComponent(lblMonth)
                    .addComponent(lblYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalLabel)
                    .addComponent(totalMoneyTxt))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firmaLabelAbajo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(espacioFirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(printButton)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(297, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:                
        
        Printsupport ps=new Printsupport();
        String initialDate= yearBegin.getSelectedItem().toString()+"-"+monthBegin.getSelectedItem().toString()+"-"+dayBegin.getSelectedItem().toString();
        Object printitem [][]=ps.getTableData(jTable1,ticketTxt.getText(),storeTxt.getText(),timeTxt.getText(),initialDate, hourTxt.getText(),
                -1, totalMoneyTxt.getText());
        ps.setItems(printitem);
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
        try {
            //JOptionPane.showMessageDialog(null, dateTxt.getText());
            pj.print();
        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }       
    }//GEN-LAST:event_printButtonActionPerformed

    private void checkNightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkNightActionPerformed
        // TODO add your handling code here:
        removeAllItemsFromList();
        ConnectionBD con= new ConnectionBD();
        listOfNumbers(con);
        timeTxt.setText("Noche");  
        
    }//GEN-LAST:event_checkNightActionPerformed

    private void checkDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDayActionPerformed
        // TODO add your handling code here:
        removeAllItemsFromList();
        ConnectionBD con= new ConnectionBD();
        listOfNumbers(con);
        timeTxt.setText("Dia"); 
    }//GEN-LAST:event_checkDayActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        pressed = true;
        removeAllItemsFromList();
        showInformationPrintTotalU();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkDay;
    private javax.swing.JCheckBox checkNight;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JComboBox<String> dayBegin;
    private javax.swing.JSeparator espacioFirma;
    private javax.swing.JLabel firmaLabelAbajo;
    private javax.swing.JLabel firmaTxt;
    private javax.swing.JLabel hourTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblYear;
    private javax.swing.JComboBox<String> monthBegin;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel storeTxt;
    private javax.swing.JLabel ticketLabel;
    private javax.swing.JLabel ticketTxt;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel timeTxt;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalMoneyTxt;
    private javax.swing.JComboBox<String> yearBegin;
    // End of variables declaration//GEN-END:variables
}
