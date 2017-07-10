/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import BD.ConnectionBD;
import Clases.Board;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sistematiempos.ChineseLanguage;
import sistematiempos.SpanishLanguage;

/**
 *
 * @author Bryan
 */
public class AdministratorEditOptions extends javax.swing.JFrame {

    /**
     * Creates new form AdministratorEditOptions
     */
    private String save,newBoard,password,percentage, night, morning, name, codeBar, number, money, moneyTitle, oneOne;
    private Board board;
    private String priceForManyNumbers,priceForOneNumber,numberLabel,moneyLabel,notValidValue,outOfRangeNumber, whiteSpace,okMensage;
    
    public AdministratorEditOptions() {
        initComponents();
        getBoardDataToInform();
        setLanguageToSpanish();
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage("W:/SystemConfigFilesProvidedByBranLabsToSistemaChinos/icono.png");
        return retValue;
    }
    
    private static boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    private void updateBoardInformation() {
        ConnectionBD con= new ConnectionBD();
        String morningClosing = cbMorningHour.getSelectedItem().toString() + ":" +cbMorningMinutes.getSelectedItem().toString();
        String nightClosing = cbNightHour.getSelectedItem().toString() + ":" +cbNightMinutes.getSelectedItem().toString();
        if(isNumeric(tfPercentage.getText())== false || isNumeric(tfPriceForNumber.getText())== false){                
            JOptionPane.showMessageDialog(null, notValidValue);
            return;
        }   
        int percentageNumber = Integer.parseInt(tfPercentage.getText());
        String companyName = tfName.getText();
        String adminPassword = new String(tfPassword.getPassword());
        int priceForNumber = Integer.parseInt(tfPriceForNumber.getText());
        Calendar cal=Calendar.getInstance(); 
        String currentDate =cal.get(cal.DATE)+"/"+(cal.get(cal.MONTH)+1)+"/"+cal.get(cal.YEAR);
        if (unoUno.isSelected()) {
            con.updateBoard(board.getBoard(), morningClosing, nightClosing, companyName, percentageNumber, adminPassword, currentDate, priceForNumber, true);
        }
        else {
            con.updateBoard(board.getBoard(), morningClosing, nightClosing, companyName, percentageNumber, adminPassword, currentDate, priceForNumber, false);
        }
        if(!tfNumberTxt.getText().isEmpty() && !tfMoneyTxt.getText().isEmpty()){            
            if(isNumeric(tfNumberTxt.getText())== false || isNumeric(tfMoneyTxt.getText())== false){
                JOptionPane.showMessageDialog(null, notValidValue);
                return;
            }   
            int number= Integer.valueOf(tfNumberTxt.getText());
            int money= Integer.valueOf(tfMoneyTxt.getText());
            if(number>= 0 && number <= 99){
                con.updateTimeNumberAdmin(board.getBoard(), "Dia", number, money);
                con.updateTimeNumberAdmin(board.getBoard(), "Noche", number, money);            
            }   
            else{
                JOptionPane.showMessageDialog(null,outOfRangeNumber );
                return;
            }
        }
        else if(!tfNumberTxt.getText().isEmpty() || !tfMoneyTxt.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,whiteSpace);
            return;
        }
        JOptionPane.showMessageDialog(null, okMensage);
    }
    
    private void getBoardDataToInform() {
        ConnectionBD con= new ConnectionBD();
        board= con.getBoardInformation();   
        String morningClosing = board.getDayClose();
        String nightClosing = board.getNightClose();
        String storeName = board.getStore();
        int stadisticsPercentage = board.getStadisticsPer();
        int pricing = board.getNumbersPrincing();
        String cdate = board.getDate();
        String password = board.getPassword();
        tfName.setText(storeName);
        String[] morningParts = morningClosing.split(":");
        String hour = morningParts[0];
        String minute = morningParts[1]; 
        cbMorningHour.setSelectedItem(hour);
        cbMorningMinutes.setSelectedItem(minute);
        String[] nightParts = nightClosing.split(":");
        hour = nightParts[0];
        minute = nightParts[1]; 
        cbNightHour.setSelectedItem(hour);
        cbNightMinutes.setSelectedItem(minute);
        tfPercentage.setText(String.valueOf(stadisticsPercentage));
        tfPassword.setText(password);
        tfPriceForNumber.setText(String.valueOf(pricing));
    }


    
    private void setAllToSelectedLanguage() {
        lblPriceForNumbers.setText(priceForManyNumbers);
        lbNumber.setText(numberLabel);
        lblMoney.setText(moneyLabel);        
        lblCompanyName.setText(name);
        lblMorningClosingTime.setText(morning);
        lblNightClosingTime.setText(night);
        lblPercentage.setText(percentage);
        lblPassword.setText(password);
        btnSave.setText(save);
        lbNumber.setText(number);
        lblMoney.setText(money);
        unoUno.setText(oneOne);
    }
    
    private void setWindowToSpanish(){
        SpanishLanguage spanishLanguage = SpanishLanguage.getInstance();
        notValidValue="Error no se permite letras en valores solo numericos";
        outOfRangeNumber="Error número fuera de rangos";
        whiteSpace= "Error falta dato para poder cambiar valor de numero";
        okMensage="Se ha realizado correctamente";
        priceForManyNumbers= "Precio de todos los numeros";
        priceForOneNumber= "Precio para un número específico";
        numberLabel= "Numero";
        moneyLabel= "Plata";
        oneOne = "Uno a Uno";
        
        save = spanishLanguage.getBtnSaveString();
        password = spanishLanguage.getLblPassword();
        percentage = spanishLanguage.getLblPercentage();
        night = spanishLanguage.getLblNightClosingString();
        morning = spanishLanguage.getLblMorningClosingString();
        name = spanishLanguage.getLblCompanyName();
        codeBar = spanishLanguage.getLblCodeBar();
        number = spanishLanguage.getLblNumber();
        moneyTitle = spanishLanguage.getLblMoneyTitle();
        money = spanishLanguage.getLblMoney();
    }
    
    private void setWindowToChinese(){
        ChineseLanguage chineseLanguage = ChineseLanguage.getInstance();
        priceForManyNumbers= "号码购买上限";
        priceForOneNumber= "一码价钱";
        numberLabel= "号码";
        moneyLabel= "号码";
        notValidValue="无效号码";
        outOfRangeNumber="错误号码超出范围";
        whiteSpace= "错误信息丢失";
        okMensage="添加成功";
        oneOne = "选择每个号码价钱上限";
        
        save = chineseLanguage.getBtnSaveString();
        password = chineseLanguage.getLblPassword();
        percentage = chineseLanguage.getLblPercentage();
        night = chineseLanguage.getLblNightClosingString();
        morning = chineseLanguage.getLblMorningClosingString();
        name = chineseLanguage.getLblCompanyName();
        codeBar = chineseLanguage.getLblCodeBar();
        number = chineseLanguage.getLblNumber();
        moneyTitle = chineseLanguage.getLblMoneyTitle();
        money = chineseLanguage.getLblMoney();
    }
    
    private void setLanguageToSpanish() {
        setWindowToSpanish();
        setAllToSelectedLanguage();
        cbSpanish.setSelected(true);
    }
    private void setLanguageToChinese() {
        setWindowToChinese();
        setAllToSelectedLanguage();
        cbChinese.setSelected(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgLanguage = new javax.swing.ButtonGroup();
        lblNameBoth1 = new javax.swing.JLabel();
        lblNameBoth2 = new javax.swing.JLabel();
        lblCompanyName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lblMorningClosingTime = new javax.swing.JLabel();
        lblNightClosingTime = new javax.swing.JLabel();
        lblPercentage = new javax.swing.JLabel();
        tfPercentage = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        cbSpanish = new javax.swing.JCheckBox();
        cbChinese = new javax.swing.JCheckBox();
        btnSave = new javax.swing.JButton();
        tfPassword = new javax.swing.JPasswordField();
        cbMorningHour = new javax.swing.JComboBox<>();
        cbMorningMinutes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbNightHour = new javax.swing.JComboBox<>();
        cbNightMinutes = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lblPriceForNumbers = new javax.swing.JLabel();
        tfPriceForNumber = new javax.swing.JTextField();
        tfNumberTxt = new javax.swing.JTextField();
        tfMoneyTxt = new javax.swing.JTextField();
        lbNumber = new javax.swing.JLabel();
        lblMoney = new javax.swing.JLabel();
        unoUno = new javax.swing.JCheckBox();

        lblNameBoth1.setText("天");

        lblNameBoth2.setText("天");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setResizable(false);

        lblCompanyName.setText("Nombre del comercio:");

        lblMorningClosingTime.setText("Cierre de la mañana:");

        lblNightClosingTime.setText("Cierre de la noche:");

        lblPercentage.setText("Porcentaje estadístico:");

        lblPassword.setText("Contraseña:");

        bgLanguage.add(cbSpanish);
        cbSpanish.setText("Español");
        cbSpanish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSpanishActionPerformed(evt);
            }
        });

        bgLanguage.add(cbChinese);
        cbChinese.setText("中国");
        cbChinese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChineseActionPerformed(evt);
            }
        });

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        cbMorningHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        cbMorningMinutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));

        jLabel1.setText(":");

        cbNightHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        cbNightMinutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));

        jLabel2.setText(":");

        lblPriceForNumbers.setText("Precio para numeros:");

        lbNumber.setText("Numero");

        lblMoney.setText("Plata");

        unoUno.setText("Uno a Uno");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(74, 74, 74))
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbSpanish)
                        .addGap(18, 18, 18)
                        .addComponent(cbChinese)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMorningClosingTime)
                            .addComponent(lblNightClosingTime)
                            .addComponent(lblCompanyName)
                            .addComponent(lblPercentage)
                            .addComponent(lblPassword)
                            .addComponent(lblPriceForNumbers)
                            .addComponent(unoUno))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(tfPercentage)
                            .addComponent(tfPassword)
                            .addComponent(tfPriceForNumber)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfMoneyTxt))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbNumber)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMoney)
                                .addGap(73, 73, 73))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbMorningHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbMorningMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbNightHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbNightMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbChinese)
                    .addComponent(cbSpanish))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCompanyName)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMorningClosingTime)
                    .addComponent(cbMorningHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbMorningMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNightClosingTime)
                    .addComponent(cbNightHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbNightMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPercentage)
                    .addComponent(tfPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPriceForNumbers)
                    .addComponent(tfPriceForNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumber)
                    .addComponent(lblMoney)
                    .addComponent(unoUno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMoneyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbSpanishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSpanishActionPerformed
        // TODO add your handling code here:
        setLanguageToSpanish();
    }//GEN-LAST:event_cbSpanishActionPerformed

    private void cbChineseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChineseActionPerformed
        // TODO add your handling code here:
        setLanguageToChinese();
    }//GEN-LAST:event_cbChineseActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:   
        updateBoardInformation();
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(AdministratorEditOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministratorEditOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministratorEditOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministratorEditOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministratorEditOptions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgLanguage;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbChinese;
    private javax.swing.JComboBox<String> cbMorningHour;
    private javax.swing.JComboBox<String> cbMorningMinutes;
    private javax.swing.JComboBox<String> cbNightHour;
    private javax.swing.JComboBox<String> cbNightMinutes;
    private javax.swing.JCheckBox cbSpanish;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbNumber;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JLabel lblMorningClosingTime;
    private javax.swing.JLabel lblNameBoth1;
    private javax.swing.JLabel lblNameBoth2;
    private javax.swing.JLabel lblNightClosingTime;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPercentage;
    private javax.swing.JLabel lblPriceForNumbers;
    private javax.swing.JTextField tfMoneyTxt;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfNumberTxt;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfPercentage;
    private javax.swing.JTextField tfPriceForNumber;
    private javax.swing.JCheckBox unoUno;
    // End of variables declaration//GEN-END:variables
}
