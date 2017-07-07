/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import BD.ConnectionBD;
import Clases.Board;
import Clases.Ticket;
import java.util.List;
import sistematiempos.ChineseLanguage;
import sistematiempos.SpanishLanguage;

/**
 *
 * @author Bryan
 */
public class StadisticsCenter extends javax.swing.JFrame {

    /**
     * Creates new form StadisticsCenter
     */
    private String start, finish, porcentage, total, search,initialDate,finalDate, day, month, year;
    private Board board;    
    private int percentage, totalBetweenDates;

    public StadisticsCenter() {
        initComponents();        
        getDaysOfComboBox();
        getBoardDataToInform();
    }
    
private void getDaysOfComboBox(){
        for(int i= 1; i<=31; i++){
            dayBegin.addItem(String.valueOf(i));
            dayEnd.addItem(String.valueOf(i));
        }
        for(int i= 1; i<=12; i++){
            monthBegin.addItem(String.valueOf(i));
            monthEnd.addItem(String.valueOf(i));
        }
        for(int i= 2017; i<=2030; i++){
            yearBegin.addItem(String.valueOf(i));
            yearEnd.addItem(String.valueOf(i));
        }
    }
    
    private boolean getFinalAndInitialDates(){
        if(Integer.parseInt(monthBegin.getSelectedItem().toString())== 2 &&
            Integer.parseInt(dayBegin.getSelectedItem().toString()) > 28 ||
            Integer.parseInt(monthEnd.getSelectedItem().toString())== 2 && 
            Integer.parseInt(dayEnd.getSelectedItem().toString()) > 28){                        
                menssage.setText("ERROR mes 2 no tiene mas de 28 días");                
                return false;
        }         
        if(Integer.parseInt(monthBegin.getSelectedItem().toString())== 4 ||
            Integer.parseInt(monthBegin.getSelectedItem().toString())== 6 ||
            Integer.parseInt(monthBegin.getSelectedItem().toString())== 9 ||
            Integer.parseInt(monthBegin.getSelectedItem().toString())== 11){
                if(Integer.parseInt(dayBegin.getSelectedItem().toString()) > 30){
                    menssage.setText("ERROR mes "+monthBegin.getSelectedItem().toString()+"solo posee 30 dias");                
                    return false;               
                }                
        }
        if(Integer.parseInt(monthEnd.getSelectedItem().toString())== 4 ||
            Integer.parseInt(monthEnd.getSelectedItem().toString())== 6 ||
            Integer.parseInt(monthEnd.getSelectedItem().toString())== 9 ||
            Integer.parseInt(monthEnd.getSelectedItem().toString())== 11){
                if(Integer.parseInt(dayEnd.getSelectedItem().toString()) > 30){
                    menssage.setText("ERROR mes "+monthEnd.getSelectedItem().toString()+" solo posee 30 dias");                
                    return false;               
                }   
        }
        
        initialDate= monthBegin.getSelectedItem().toString()+"/"+dayBegin.getSelectedItem().toString()+"/"+yearBegin.getSelectedItem().toString();
        finalDate= monthEnd.getSelectedItem().toString()+"/"+dayEnd.getSelectedItem().toString()+"/"+yearEnd.getSelectedItem().toString();        
        return true;        
    }
    
    private void calculatePrcent(){                
        ConnectionBD con= new ConnectionBD();
        menssage.setText("");
        float percentageToUse = (float) percentage;        
        if(getFinalAndInitialDates()== true){
            totalBetweenDates=0;        
            List<Ticket>list= con.GetAllTiicketsBetweenDates(initialDate,finalDate);
            for (int i = 0; i < list.size(); i++) {
                totalBetweenDates= totalBetweenDates+ list.get(i).getTicketTotalAmount();
            }                
            float total = ((float) totalBetweenDates) * (percentageToUse / 100);
            tfTotal.setText(String.valueOf(total));   
        }        
    }
    
    private void getBoardDataToInform() {
        ConnectionBD con= new ConnectionBD();
        board = con.getBoardInformation();
        percentage = board.getStadisticsPer();                
        String percentageToShow = String.valueOf(board.getStadisticsPer());
        tfPercentage.setText(percentageToShow);        
    }
    
    private void setWindowToSelectedLanguage() {
        lblStart.setText(start);
        lblFinish.setText(finish);
        lblPercentage.setText(porcentage);
        lblTotal.setText(total);
        btnSearch.setText(search);
        lblDay.setText(day);
        lblMonth.setText(month);
        lblYear.setText(year);
    }
    
    public void setLanguageToSpanish() {
        SpanishLanguage spanishLanguage = SpanishLanguage.getInstance();
        start = spanishLanguage.getStartDate();
        finish = spanishLanguage.getFinishDate();
        porcentage = spanishLanguage.getLblPercentage();
        total = spanishLanguage.getLblTotalString();
        search = spanishLanguage.getSearch();
        day = spanishLanguage.getLblDay();
        month = spanishLanguage.getLblMonth();
        year = spanishLanguage.getLblYear();
        setWindowToSelectedLanguage();
    }
    
    public void setLanguageToChinese() {
        ChineseLanguage chineseLanguage = ChineseLanguage.getInstance();
        start = chineseLanguage.getStartDate();
        finish = chineseLanguage.getFinishDate();
        porcentage = chineseLanguage.getLblPercentage();
        total = chineseLanguage.getLblTotalString();
        search = chineseLanguage.getSearch();
        day = chineseLanguage.getLblDay();
        month = chineseLanguage.getLblMonth();
        year = chineseLanguage.getLblYear();
        setWindowToSelectedLanguage();
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMonth = new javax.swing.JLabel();
        dayBegin = new javax.swing.JComboBox<>();
        lblStart = new javax.swing.JLabel();
        dayEnd = new javax.swing.JComboBox<>();
        lblTotal = new javax.swing.JLabel();
        monthBegin = new javax.swing.JComboBox<>();
        tfTotal = new javax.swing.JTextField();
        monthEnd = new javax.swing.JComboBox<>();
        lblFinish = new javax.swing.JLabel();
        yearBegin = new javax.swing.JComboBox<>();
        lblPercentage = new javax.swing.JLabel();
        yearEnd = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        lblDay = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        menssage = new javax.swing.JLabel();
        tfPercentage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblMonth.setText("mes");

        lblStart.setText("Dia inicio:");

        lblTotal.setText("Total:");

        lblFinish.setText("Dia fin:");

        lblPercentage.setText("Porcentaje:");

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblDay.setText("día");

        lblYear.setText("año");

        menssage.setForeground(new java.awt.Color(204, 0, 0));

        tfPercentage.setText("%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(273, Short.MAX_VALUE)
                .addComponent(lblTotal)
                .addGap(18, 18, 18)
                .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(menssage))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(tfPercentage)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblPercentage)
                        .addComponent(lblStart)
                        .addComponent(lblFinish))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(btnSearch))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dayBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblDay))
                            .addGap(29, 29, 29)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(monthBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(monthEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMonth))
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(yearBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(yearEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblYear))))
                    .addContainerGap(272, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(tfPercentage)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(menssage)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMonth)
                        .addComponent(lblDay)
                        .addComponent(lblYear))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblStart)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dayBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFinish)
                        .addComponent(dayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(monthEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(lblPercentage)
                    .addGap(18, 18, 18)
                    .addComponent(btnSearch)
                    .addContainerGap(94, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        calculatePrcent();
    }//GEN-LAST:event_btnSearchActionPerformed

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
            java.util.logging.Logger.getLogger(StadisticsCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StadisticsCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StadisticsCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StadisticsCenter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StadisticsCenter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> dayBegin;
    private javax.swing.JComboBox<String> dayEnd;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblFinish;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblPercentage;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblYear;
    private javax.swing.JLabel menssage;
    private javax.swing.JComboBox<String> monthBegin;
    private javax.swing.JComboBox<String> monthEnd;
    private javax.swing.JLabel tfPercentage;
    private javax.swing.JTextField tfTotal;
    private javax.swing.JComboBox<String> yearBegin;
    private javax.swing.JComboBox<String> yearEnd;
    // End of variables declaration//GEN-END:variables
}
