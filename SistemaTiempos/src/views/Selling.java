/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import BD.ConnectionBD;
import Clases.Board;
import Clases.Printsupport;
import Clases.Printsupport.MyPrintable;
import Clases.SoldNumbers;
import Clases.Ticket;
import Clases.TimeNumber;
import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sistematiempos.ChineseLanguage;
import sistematiempos.SpanishLanguage;

/**
 *
 * @author Bryan
 */
public class Selling extends javax.swing.JFrame {

    /**
     * Creates new form Selling
     */
    private JButton button;
    private Board board;
    private String checkBoxTimeselected;
    private List<Integer>numbersList, numberTable, moneyTable;
    private DefaultListModel numbers = new DefaultListModel();
    private DefaultListModel money = new DefaultListModel();
    private DefaultTableModel tableModel;
    private String idBoard, lastSave;
    private int hour, minuts, seconds, pricing;
    private String dayHour;
    private static SpanishLanguage spanishStrings = SpanishLanguage.getInstance();
    private static ChineseLanguage chineseStrings = ChineseLanguage.getInstance();
    private String notNumberErrorString, btnScanCodeBarString, btnTotalUniquePrintString, 
            btnStadisticsString, lblMorningClosingString, lblNightClosingString, lblPeriodString, 
            lblDayString, lblNightString, lblMoneyString, btnSaveString, lblNumbersListString,
            btnRemoveString, lblTotalString, btnPayString, lblNumberMoneyString, language, boardCurrentTime, btnReset,
            btnPrint;
    private boolean isTicketPaid = false;
    private boolean changeNumber = false;
    
    public Selling() {
        initComponents();
        getBoardDataToInform();  
    }     
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage("W:/SystemConfigFilesProvidedToToSistemaChinos/icono.png");
        return retValue;
    }
    
    private void printOnceTicketIsPaid() {
        if (isTicketPaid) {
            ConnectionBD con= new ConnectionBD();
            Ticket ticket= con.getTicketInformation();
            Printsupport ps=new Printsupport();
            String morningSplitHour[] = ticket.getTimeHour().split(": ");
            String splitHour = morningSplitHour[0];
            String splitMinute = morningSplitHour[1];
            String splitSecond = morningSplitHour[2];
            if (morningSplitHour[0].length() == 1) {
                splitHour = "0"+morningSplitHour[0];
            }
            if (morningSplitHour[1].length() == 1) {
                splitMinute = "0"+morningSplitHour[1];
            }
            if (morningSplitHour[2].length() == 1) {
                splitSecond = "0"+morningSplitHour[2];
            }
            String finaHour = splitHour+": "+splitMinute+": "+splitSecond;

            Object printitem [][]=ps.getTableData(jTable1,String.valueOf(ticket.getTicket()),board.getStore(),boardCurrentTime,ticket.getDate(), finaHour,
                    0, String.valueOf(ticket.getTicketTotalAmount()));
            ps.setItems(printitem);
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable(new MyPrintable(),ps.getPageFormat(pj));
            try {
                pj.print();
                removeAllItemsFromList();
                getTotalAndShowIT();
                setButtonsAvailableOrNot(true);
            }
            catch (PrinterException ex) {
                ex.printStackTrace();
            }
            isTicketPaid = false;
        }
        else {
            if (language == "spanish") {
                JOptionPane.showMessageDialog(null, "Primero pague un tiquete.");
            }
            else {
                JOptionPane.showMessageDialog(null, "首先你付出");
            }
        }
    }
    
    private void setButtonsAvailableOrNot(boolean bool) {
        btnSave.setEnabled(bool);
        btnSaveChinese.setEnabled(bool);        
        btnPay.setEnabled(bool);
        btnPayChinese.setEnabled(bool);        
        cbChinese.setEnabled(bool);
        cbSpanish.setEnabled(bool);
        cbNight.setEnabled(bool);
        cbMorning.setEnabled(bool);
        btnStadistics.setEnabled(bool);
        btnTotalPrintImpression.setEnabled(bool);
        btnScanBarCode.setEnabled(bool);
        setEnableOrNotButtons(false);
    }
    
    private void setbackgroundColorToGreen(JButton buttonParam) {   
        
        if(checkBoxTimeselected.equals("Dia")){
            buttonParam.setBackground(Color.GREEN);
        }
        else if(checkBoxTimeselected.equals("Noche")){
            buttonParam.setBackground(new Color(83, 140, 226));
        }             
    }
    
    private void setColorsToNull(){
        btn00.setBackground(null);
        btn01.setBackground(null);
        btn02.setBackground(null);
        btn03.setBackground(null);
        btn04.setBackground(null);
        btn05.setBackground(null);
        btn06.setBackground(null);
        btn07.setBackground(null);
        btn08.setBackground(null);
        btn09.setBackground(null);
        btn10.setBackground(null);
        btn11.setBackground(null);
        btn12.setBackground(null);
        btn13.setBackground(null);
        btn14.setBackground(null);
        btn15.setBackground(null);
        btn16.setBackground(null);
        btn17.setBackground(null);
        btn18.setBackground(null);
        btn19.setBackground(null);
        btn20.setBackground(null);
        btn21.setBackground(null);
        btn22.setBackground(null);
        btn23.setBackground(null);
        btn24.setBackground(null);
        btn25.setBackground(null);
        btn26.setBackground(null);
        btn27.setBackground(null);
        btn28.setBackground(null);
        btn29.setBackground(null);
        btn30.setBackground(null);        
        btn31.setBackground(null);
        btn32.setBackground(null);
        btn33.setBackground(null);
        btn34.setBackground(null);
        btn35.setBackground(null);
        btn36.setBackground(null);
        btn37.setBackground(null);
        btn38.setBackground(null);
        btn39.setBackground(null);
        btn40.setBackground(null);
        btn41.setBackground(null);
        btn42.setBackground(null);
        btn43.setBackground(null);
        btn44.setBackground(null);
        btn45.setBackground(null);
        btn46.setBackground(null);
        btn47.setBackground(null);
        btn48.setBackground(null);
        btn49.setBackground(null);
        btn50.setBackground(null);
        btn51.setBackground(null);
        btn52.setBackground(null);
        btn53.setBackground(null);
        btn54.setBackground(null);
        btn55.setBackground(null);
        btn56.setBackground(null);
        btn57.setBackground(null);
        btn58.setBackground(null);
        btn59.setBackground(null);
        btn60.setBackground(null);
        btn61.setBackground(null);
        btn62.setBackground(null);
        btn63.setBackground(null);
        btn64.setBackground(null);
        btn65.setBackground(null);
        btn66.setBackground(null);
        btn67.setBackground(null);
        btn68.setBackground(null);
        btn69.setBackground(null);
        btn70.setBackground(null);
        btn71.setBackground(null);
        btn72.setBackground(null);
        btn73.setBackground(null);
        btn74.setBackground(null);
        btn75.setBackground(null);
        btn76.setBackground(null);
        btn77.setBackground(null);
        btn78.setBackground(null);
        btn79.setBackground(null);
        btn80.setBackground(null);
        btn81.setBackground(null);
        btn82.setBackground(null);
        btn83.setBackground(null);
        btn84.setBackground(null);
        btn85.setBackground(null);
        btn86.setBackground(null);
        btn87.setBackground(null);
        btn88.setBackground(null);
        btn89.setBackground(null);
        btn90.setBackground(null);
        btn91.setBackground(null);
        btn92.setBackground(null);
        btn93.setBackground(null);
        btn94.setBackground(null);
        btn95.setBackground(null);
        btn96.setBackground(null);
        btn97.setBackground(null);
        btn98.setBackground(null);
        btn99.setBackground(null);        
    }    
    
    public void soldNumbersOfTableSetColors(){
        setColorsToNull();
        ConnectionBD con= new ConnectionBD();        
        numbersList= new ArrayList<>();
        if(selectCheckBox()== false){
            return;
        }       
        List<TimeNumber>list=con.getSoldBoardNumbersDependingOnTime(idBoard,checkBoxTimeselected, pricing);  
        for (int i = 0; i < list.size(); i++) { 
            numbersList.add(list.get(i).getNumero());
        }        
        if(numbersList.contains(0)){
            setbackgroundColorToGreen(btn00);
        }
        if(numbersList.contains(1)){
            setbackgroundColorToGreen(btn01);
        }
        if(numbersList.contains(2)){
            setbackgroundColorToGreen(btn02);
        }
        if(numbersList.contains(3)){
            setbackgroundColorToGreen(btn03);
        }
        if(numbersList.contains(4)){
            setbackgroundColorToGreen(btn04);
        }
        if(numbersList.contains(5)){
            setbackgroundColorToGreen(btn05);            
        }
        if(numbersList.contains(6)){
            setbackgroundColorToGreen(btn06);            
        }
        if(numbersList.contains(7)){
            setbackgroundColorToGreen(btn07);
        }
        if(numbersList.contains(8)){
            setbackgroundColorToGreen(btn08);
        }
        if(numbersList.contains(9)){
            setbackgroundColorToGreen(btn09);
        }
        if(numbersList.contains(10)){
            setbackgroundColorToGreen(btn10);
        }
        if(numbersList.contains(11)){
            setbackgroundColorToGreen(btn11);
        }
        if(numbersList.contains(12)){
            setbackgroundColorToGreen(btn12);
        }
        if(numbersList.contains(13)){
            setbackgroundColorToGreen(btn13);
        }
        if(numbersList.contains(14)){
            setbackgroundColorToGreen(btn14);
        }
        if(numbersList.contains(15)){
            setbackgroundColorToGreen(btn15);
        }
        if(numbersList.contains(16)){
            setbackgroundColorToGreen(btn16);
        }        
        if(numbersList.contains(17)){
            setbackgroundColorToGreen(btn17);
        }
        if(numbersList.contains(18)){
            setbackgroundColorToGreen(btn18);
        }
        if(numbersList.contains(19)){
            setbackgroundColorToGreen(btn19);
        }
        if(numbersList.contains(20)){
            setbackgroundColorToGreen(btn20);
        }
        if(numbersList.contains(21)){
            setbackgroundColorToGreen(btn21);
        }
        if(numbersList.contains(22)){
            setbackgroundColorToGreen(btn22);
        }
        if(numbersList.contains(23)){
            setbackgroundColorToGreen(btn23);
        }
        if(numbersList.contains(24)){
            setbackgroundColorToGreen(btn24);
        }
        if(numbersList.contains(25)){
            setbackgroundColorToGreen(btn25);
        }
        if(numbersList.contains(26)){
            setbackgroundColorToGreen(btn26);
        }
        if(numbersList.contains(27)){
            setbackgroundColorToGreen(btn27);
        }
        if(numbersList.contains(28)){
            setbackgroundColorToGreen(btn28);
        }
        if(numbersList.contains(29)){
            setbackgroundColorToGreen(btn29);
        }
        if(numbersList.contains(30)){
            setbackgroundColorToGreen(btn30);
        }
        if(numbersList.contains(31)){
            setbackgroundColorToGreen(btn31);
        }
        if(numbersList.contains(32)){
            setbackgroundColorToGreen(btn32);
        }
        if(numbersList.contains(33)){
            setbackgroundColorToGreen(btn33);
        }
        if(numbersList.contains(34)){
            setbackgroundColorToGreen(btn34);
        }
        if(numbersList.contains(35)){
            setbackgroundColorToGreen(btn35);
        }
        if(numbersList.contains(36)){
            setbackgroundColorToGreen(btn36);
        }
        if(numbersList.contains(37)){
            setbackgroundColorToGreen(btn37);
        }
        if(numbersList.contains(38)){
            setbackgroundColorToGreen(btn38);
        }
        if(numbersList.contains(39)){
            setbackgroundColorToGreen(btn39);
        }
        if(numbersList.contains(40)){
            setbackgroundColorToGreen(btn40);
        }
        if(numbersList.contains(41)){
            setbackgroundColorToGreen(btn41);
        }
        if(numbersList.contains(42)){
            setbackgroundColorToGreen(btn42);
        }
        if(numbersList.contains(43)){
            setbackgroundColorToGreen(btn43);
        }
        if(numbersList.contains(44)){
            setbackgroundColorToGreen(btn44);
        }
        if(numbersList.contains(45)){
            setbackgroundColorToGreen(btn45);
        }
        if(numbersList.contains(46)){
            setbackgroundColorToGreen(btn46);
        }
        if(numbersList.contains(47)){
            setbackgroundColorToGreen(btn47);
        }
        if(numbersList.contains(48)){
            setbackgroundColorToGreen(btn48);
        }
        if(numbersList.contains(49)){
            setbackgroundColorToGreen(btn49);
        }
        if(numbersList.contains(50)){
            setbackgroundColorToGreen(btn50);
        }
        if(numbersList.contains(51)){
            setbackgroundColorToGreen(btn51);
        }
        if(numbersList.contains(52)){
            setbackgroundColorToGreen(btn52);
        }
        if(numbersList.contains(53)){
            setbackgroundColorToGreen(btn53);
        }
        if(numbersList.contains(54)){
            setbackgroundColorToGreen(btn54);
        }
        if(numbersList.contains(55)){
            setbackgroundColorToGreen(btn55);
        }
        if(numbersList.contains(56)){
            setbackgroundColorToGreen(btn56);
        }
        if(numbersList.contains(57)){
            setbackgroundColorToGreen(btn57);
        }
        if(numbersList.contains(58)){
            setbackgroundColorToGreen(btn58);
        }
        if(numbersList.contains(59)){
            setbackgroundColorToGreen(btn59);
        }
        if(numbersList.contains(60)){
            setbackgroundColorToGreen(btn60);
        }
        if(numbersList.contains(61)){
            setbackgroundColorToGreen(btn61);
        }
        if(numbersList.contains(62)){
            setbackgroundColorToGreen(btn62);
        }
        if(numbersList.contains(63)){
            setbackgroundColorToGreen(btn63);
        }
        if(numbersList.contains(64)){
            setbackgroundColorToGreen(btn64);
        }
        if(numbersList.contains(65)){
            setbackgroundColorToGreen(btn65);
        }
        if(numbersList.contains(66)){
            setbackgroundColorToGreen(btn66);
        }
        if(numbersList.contains(67)){
            setbackgroundColorToGreen(btn67);
        }
        if(numbersList.contains(68)){
            setbackgroundColorToGreen(btn68);
        }
        if(numbersList.contains(69)){
            setbackgroundColorToGreen(btn69);
        }
        if(numbersList.contains(70)){
            setbackgroundColorToGreen(btn70);
        }
        if(numbersList.contains(71)){
            setbackgroundColorToGreen(btn71);
        }
        if(numbersList.contains(72)){
            setbackgroundColorToGreen(btn72);
        }
        if(numbersList.contains(73)){
            setbackgroundColorToGreen(btn73);
        }
        if(numbersList.contains(74)){
            setbackgroundColorToGreen(btn74);
        }
        if(numbersList.contains(75)){
            setbackgroundColorToGreen(btn75);
        }
        if(numbersList.contains(76)){
            setbackgroundColorToGreen(btn76);
        }
        if(numbersList.contains(77)){
            setbackgroundColorToGreen(btn77);
        }
        if(numbersList.contains(78)){
            setbackgroundColorToGreen(btn78);
        }
        if(numbersList.contains(79)){
            setbackgroundColorToGreen(btn79);
        }
        if(numbersList.contains(80)){
            setbackgroundColorToGreen(btn80);
        }
        if(numbersList.contains(81)){
            setbackgroundColorToGreen(btn81);
        }
        if(numbersList.contains(82)){
            setbackgroundColorToGreen(btn82);
        }
        if(numbersList.contains(83)){
            setbackgroundColorToGreen(btn83);
        }
        if(numbersList.contains(84)){
            setbackgroundColorToGreen(btn84);
        }
        if(numbersList.contains(85)){
            setbackgroundColorToGreen(btn85);
        }
        if(numbersList.contains(86)){
            setbackgroundColorToGreen(btn86);
        }
        if(numbersList.contains(87)){
            setbackgroundColorToGreen(btn87);
        }
        if(numbersList.contains(88)){
            setbackgroundColorToGreen(btn88);
        }
        if(numbersList.contains(89)){
            setbackgroundColorToGreen(btn89);
        }
        if(numbersList.contains(90)){
            setbackgroundColorToGreen(btn90);
        }
        if(numbersList.contains(91)){
            setbackgroundColorToGreen(btn91);
        }
        if(numbersList.contains(92)){
            setbackgroundColorToGreen(btn92);
        }
        if(numbersList.contains(93)){
            setbackgroundColorToGreen(btn93);
        }
        if(numbersList.contains(94)){
            setbackgroundColorToGreen(btn94);
        }
        if(numbersList.contains(95)){
            setbackgroundColorToGreen(btn95);
        }
        if(numbersList.contains(96)){
            setbackgroundColorToGreen(btn96);
        }
        if(numbersList.contains(97)){
            setbackgroundColorToGreen(btn97);
        }
        if(numbersList.contains(98)){
            setbackgroundColorToGreen(btn98);
        }
        if(numbersList.contains(99)){
            setbackgroundColorToGreen(btn99);
        }        
    }
        
    
    public boolean selectCheckBox(){
        if(cbMorning.isSelected() ){            
            checkBoxTimeselected= "Dia";
            return true;
        }
        else if (cbNight.isSelected()){
            checkBoxTimeselected= "Noche";
            return true;
        }
        else{
            return false;
        }        
    }
    
    private void setCurrentTime() {
        if (cbMorning.isSelected()) {
            boardCurrentTime = "Dia";
        }
        else {
            boardCurrentTime = "Noche";
        }
    }
    
    private void setEnableOrNotButtons(boolean bool){
        btnRemove.setEnabled(bool);
        btnRemoveChinese.setEnabled(bool);
        btnRemoveAll.setEnabled(bool);
        btnRemoveAllChinese.setEnabled(bool);
    }
    
    private void getBoardDataToInform() {
        ConnectionBD con= new ConnectionBD();
        board = con.getBoardInformation();
        String morningClosing = board.getDayClose();
        String nightClosing = board.getNightClose();
        String storeName = board.getStore();
        tfMorningClosingTime.setText(morningClosing);
        tfNightClosingTime.setText(nightClosing);
        idBoard= String.valueOf(board.getBoard());
        pricing = board.getNumbersPrincing();
        setEnableOrNotButtons(false);
        setCurrentTime();
        soldNumbersOfTableSetColors();
    }
    
    private void getBoardNumberPriceFromDB(int numberToFind){
        ConnectionBD con= new ConnectionBD();
        TimeNumber timeNumber = con.getBoardNumberPricing(board.getBoard(), boardCurrentTime, numberToFind);
        String morningClosing = timeNumber.getTiempo();
        int idb = timeNumber.getBoard();
        int idn = timeNumber.getId();
        int n = timeNumber.getNumero();
        int t = timeNumber.getTotalNumberAmount();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(button.getText())) {                
                int total = Integer.parseInt((String) tableModel.getValueAt(i, 1));
                t= t- total;                
            }        
        }
        lblTotalQuantityNumber.setText(String.valueOf(t));
    }
    
    private void getBoardNumberPrice() {        
        int numberToFind = Integer.parseInt(button.getText());
        getBoardNumberPriceFromDB(numberToFind);
    }
    
    public void createTicketForPurchase(){
        ConnectionBD con= new ConnectionBD();
        Calendar cal=Calendar.getInstance(); 
        String currentDate =cal.get(cal.DATE)+"/"+(cal.get(cal.MONTH)+1)+"/"+cal.get(cal.YEAR);
        int totalValue = Integer.parseInt(lblTotalAmount.getText());
        moneyTable = new ArrayList<>();
        numberTable = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int moneyForThisRow = Integer.parseInt((String) tableModel.getValueAt(i, 1));
            moneyTable.add(moneyForThisRow);
            int numberForThisRow = Integer.parseInt((String) tableModel.getValueAt(i, 0));
            numberTable.add(numberForThisRow);
        }
        Calendar calendario = new GregorianCalendar();
        hour =calendario.get(Calendar.HOUR_OF_DAY);
        minuts = calendario.get(Calendar.MINUTE);
        seconds = calendario.get(Calendar.SECOND);
        dayHour= hour+": "+minuts+": "+seconds;        
        con.createTicket(totalValue, boardCurrentTime, numberTable, moneyTable, board.getBoard(),dayHour);
        
    }
    
    private void setAllToSelectedLanguage() {
        lblMorningClosingTime.setText(lblMorningClosingString);
        lblNightClosingTime.setText(lblNightClosingString);
        lblBettingPeriod.setText(lblPeriodString);
        cbMorning.setText(lblDayString);
        cbNight.setText(lblNightString);
        btnScanBarCode.setText(btnScanCodeBarString);
        btnTotalPrintImpression.setText(btnTotalUniquePrintString);
        btnStadistics.setText(btnStadisticsString);
        lblMoney.setText(lblMoneyString);
        btnSave.setText(btnSaveString);
        btnRemove.setText(btnRemoveString);
        lblTotal.setText(lblTotalString);
        btnPay.setText(btnPayString);
        lblNumberMoney.setText(lblNumberMoneyString);
        btnSaveChinese.setText(btnSaveString);
        btnRemoveChinese.setText(btnRemoveString);
        btnPayChinese.setText(btnPayString);
        btnRemoveAll.setText(btnReset);
        btnRemoveAllChinese.setText(btnReset);
        //printButton.setText(btnPrint);
        removeAllItemsFromList();
    }
    
    private void setWindowToChinese(){
        notNumberErrorString = chineseStrings.getNotNumberError();
        lblMorningClosingString = chineseStrings.getLblMorningClosingString();
        lblNightClosingString = chineseStrings.getLblNightClosingString();
        lblPeriodString = chineseStrings.getLblPeriodString();
        lblDayString = chineseStrings.getLblDayString();
        lblNightString = chineseStrings.getLblNightString();
        btnScanCodeBarString = chineseStrings.getBtnScanCodeBar();
        btnTotalUniquePrintString = chineseStrings.getBtnTotalUniquePrint();
        btnStadisticsString = chineseStrings.getBtnStadisticsString();
        lblMoneyString = chineseStrings.getLblMoneyString();
        lblNumbersListString = chineseStrings.getLblNumbersListString();
        btnSaveString = chineseStrings.getBtnSaveString();
        btnRemoveString = chineseStrings.getBtnRemoveString();
        lblTotalString = chineseStrings.getLblTotalString();
        btnPayString = chineseStrings.getBtnPayString();
        lblNumberMoneyString = chineseStrings.getLblNumberMoneyString();
        btnReset = chineseStrings.getBtnReset();
        btnPrint = chineseStrings.getBtnPrint();
    }
    
    private void setWindowToSpanish(){
        notNumberErrorString = spanishStrings.getNotNumberError();
        lblMorningClosingString = spanishStrings.getLblMorningClosingString();
        lblNightClosingString = spanishStrings.getLblNightClosingString();
        lblPeriodString = spanishStrings.getLblPeriodString();
        lblDayString = spanishStrings.getLblDayString();
        lblNightString = spanishStrings.getLblNightString();
        btnScanCodeBarString = spanishStrings.getBtnScanCodeBar();
        btnTotalUniquePrintString = spanishStrings.getBtnTotalUniquePrint();
        btnStadisticsString = spanishStrings.getBtnStadisticsString();
        lblMoneyString = spanishStrings.getLblMoneyString();
        lblNumbersListString = spanishStrings.getLblNumbersListString();
        btnSaveString = spanishStrings.getBtnSaveString();
        btnRemoveString = spanishStrings.getBtnRemoveString();
        lblTotalString = spanishStrings.getLblTotalString();
        btnPayString = spanishStrings.getBtnPayString();
        lblNumberMoneyString = spanishStrings.getLblNumberMoneyString();
        btnReset = spanishStrings.getBtnReset();
        btnPrint = spanishStrings.getBtnPrint();
    }
    
    public void showSpanishButtons() {
        btnSave.setVisible(true);
        btnRemove.setVisible(true);
        btnPay.setVisible(true);
        btnRemoveAll.setVisible(true);
    }
    
    public void showChineseButtons() {
        btnSaveChinese.setVisible(true);
        btnRemoveChinese.setVisible(true);
        btnPayChinese.setVisible(true);
        btnRemoveAllChinese.setVisible(true);
    }
    
    public void hideSpanishButtons() {
        btnSave.setVisible(false);
        btnRemove.setVisible(false);
        btnPay.setVisible(false);
        btnRemoveAll.setVisible(false);
    }
    
    public void hideChineseButtons() {
        btnSaveChinese.setVisible(false);
        btnRemoveChinese.setVisible(false);
        btnPayChinese.setVisible(false);
        btnRemoveAllChinese.setVisible(false);
    }
    
    public void setLanguageToSpanish() {
        cbSpanish.setSelected(true);
        setWindowToSpanish();
        setAllToSelectedLanguage();
        language = "spanish";
    }
    
    public void setLanguageToChinese() {
        cbChinese.setSelected(true);
        setWindowToChinese();
        setAllToSelectedLanguage();
        language = "chinese";
    }
            
    
    private void showSelectedNumber() {
        tfSelectedNumber.setText(button.getText());
        getBoardNumberPrice();
    }
    
    private void showSelectedMoney(int money) {
        if (priceAct.getText().equals("")) {
            priceAct.setText("0");
        }
        int total = Integer.parseInt(priceAct.getText()) + money;
        priceAct.setText(String.valueOf(total));
    }
    
    private void removeItemFromList() {
        int position = jTable1.getSelectedRow();
        tableModel.removeRow(position);
        jTable1.setModel(tableModel);
        
        if(tableModel.getRowCount() == 0){
            setEnableOrNotButtons(false);
        }
    }
    
    public void removeAllItemsFromList() {
        setEnableOrNotButtons(false);
        tfSelectedNumber.setText("");
        //priceAct.setText("");
        lblTotalQuantityNumber.setText("0");
        lblTotalAmount.setText("0");
        String[] headers = {lblNumbersListString,lblMoneyString};
        tableModel = new DefaultTableModel(null, headers){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };        
        jTable1.setModel(tableModel);
    }
    
    private void getTotalAndShowIT() {
        int total = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int totalForThisNumber = Integer.parseInt((String) tableModel.getValueAt(i, 1));
            total += totalForThisNumber;
        }
        lblTotalAmount.setText(String.valueOf(total));
    }
    
    private void setPriceToNumber(int price) {        
        boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(button.getText())) {                
                int total = Integer.parseInt((String) tableModel.getValueAt(i, 1));
                String showIt = String.valueOf(total + price);                
                tableModel.setValueAt(showIt, i, 1);                
                selectCheckBox();                                                
                found = true;
            }
        }
        if (found == false) {
            String[] row = new String[2];
            row[0] = button.getText();
            String showIt = String.valueOf(price);
            row[1] = showIt;
            tableModel.addRow(row); 
            selectCheckBox();                                
        }        
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
        bgPeriodType = new javax.swing.ButtonGroup();
        btn00 = new javax.swing.JButton();
        btn02 = new javax.swing.JButton();
        btn01 = new javax.swing.JButton();
        btn03 = new javax.swing.JButton();
        btn06 = new javax.swing.JButton();
        btn05 = new javax.swing.JButton();
        btn07 = new javax.swing.JButton();
        btn04 = new javax.swing.JButton();
        btn08 = new javax.swing.JButton();
        btn09 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn71 = new javax.swing.JButton();
        btn88 = new javax.swing.JButton();
        btn82 = new javax.swing.JButton();
        btn81 = new javax.swing.JButton();
        btn84 = new javax.swing.JButton();
        btn83 = new javax.swing.JButton();
        btn86 = new javax.swing.JButton();
        btn85 = new javax.swing.JButton();
        btn87 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        btn29 = new javax.swing.JButton();
        btn30 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        btn37 = new javax.swing.JButton();
        btn38 = new javax.swing.JButton();
        btn39 = new javax.swing.JButton();
        btn40 = new javax.swing.JButton();
        btn41 = new javax.swing.JButton();
        btn42 = new javax.swing.JButton();
        btn43 = new javax.swing.JButton();
        btn44 = new javax.swing.JButton();
        btn45 = new javax.swing.JButton();
        btn46 = new javax.swing.JButton();
        btn47 = new javax.swing.JButton();
        btn48 = new javax.swing.JButton();
        btn49 = new javax.swing.JButton();
        btn50 = new javax.swing.JButton();
        btn51 = new javax.swing.JButton();
        btn52 = new javax.swing.JButton();
        btn53 = new javax.swing.JButton();
        btn54 = new javax.swing.JButton();
        btn55 = new javax.swing.JButton();
        btn56 = new javax.swing.JButton();
        btn57 = new javax.swing.JButton();
        btn58 = new javax.swing.JButton();
        btn59 = new javax.swing.JButton();
        btn60 = new javax.swing.JButton();
        btn61 = new javax.swing.JButton();
        btn62 = new javax.swing.JButton();
        btn63 = new javax.swing.JButton();
        btn64 = new javax.swing.JButton();
        btn65 = new javax.swing.JButton();
        btn66 = new javax.swing.JButton();
        btn67 = new javax.swing.JButton();
        btn68 = new javax.swing.JButton();
        btn69 = new javax.swing.JButton();
        btn70 = new javax.swing.JButton();
        btn89 = new javax.swing.JButton();
        btn72 = new javax.swing.JButton();
        btn73 = new javax.swing.JButton();
        btn74 = new javax.swing.JButton();
        btn75 = new javax.swing.JButton();
        btn76 = new javax.swing.JButton();
        btn77 = new javax.swing.JButton();
        btn78 = new javax.swing.JButton();
        btn79 = new javax.swing.JButton();
        btn80 = new javax.swing.JButton();
        btn99 = new javax.swing.JButton();
        btn92 = new javax.swing.JButton();
        btn91 = new javax.swing.JButton();
        btn93 = new javax.swing.JButton();
        btn96 = new javax.swing.JButton();
        btn95 = new javax.swing.JButton();
        btn97 = new javax.swing.JButton();
        btn90 = new javax.swing.JButton();
        btn94 = new javax.swing.JButton();
        btn98 = new javax.swing.JButton();
        btnStadistics = new javax.swing.JButton();
        btnTotalPrintImpression = new javax.swing.JButton();
        btnScanBarCode = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btn100 = new javax.swing.JButton();
        btn200 = new javax.swing.JButton();
        btn300 = new javax.swing.JButton();
        btn400 = new javax.swing.JButton();
        btn500 = new javax.swing.JButton();
        btn600 = new javax.swing.JButton();
        btn700 = new javax.swing.JButton();
        btn800 = new javax.swing.JButton();
        btn900 = new javax.swing.JButton();
        btn1000 = new javax.swing.JButton();
        btn2000 = new javax.swing.JButton();
        btn5000 = new javax.swing.JButton();
        lblMorningClosingTime = new javax.swing.JLabel();
        lblNightClosingTime = new javax.swing.JLabel();
        lblBettingPeriod = new javax.swing.JLabel();
        cbMorning = new javax.swing.JCheckBox();
        cbNight = new javax.swing.JCheckBox();
        cbSpanish = new javax.swing.JCheckBox();
        cbChinese = new javax.swing.JCheckBox();
        btnPay = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        lblTotalAmount = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        lblTotalQuantityNumber = new javax.swing.JLabel();
        priceAct = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnSaveChinese = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblMoney = new javax.swing.JLabel();
        lblNumberMoney = new javax.swing.JLabel();
        btnRemoveChinese = new javax.swing.JButton();
        btnPayChinese = new javax.swing.JButton();
        tfMorningClosingTime = new javax.swing.JLabel();
        tfNightClosingTime = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnRemoveAll = new javax.swing.JButton();
        btnRemoveAllChinese = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tfSelectedNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setSize(new java.awt.Dimension(0, 0));

        btn00.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn00.setText("00");
        btn00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn00ActionPerformed(evt);
            }
        });

        btn02.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn02.setText("02");
        btn02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn02ActionPerformed(evt);
            }
        });

        btn01.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn01.setText("01");
        btn01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn01ActionPerformed(evt);
            }
        });

        btn03.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn03.setText("03");
        btn03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn03ActionPerformed(evt);
            }
        });

        btn06.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn06.setText("06");
        btn06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn06ActionPerformed(evt);
            }
        });

        btn05.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn05.setText("05");
        btn05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn05ActionPerformed(evt);
            }
        });

        btn07.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn07.setText("07");
        btn07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn07ActionPerformed(evt);
            }
        });

        btn04.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn04.setText("04");
        btn04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn04ActionPerformed(evt);
            }
        });

        btn08.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn08.setText("08");
        btn08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn08ActionPerformed(evt);
            }
        });

        btn09.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn09.setText("09");
        btn09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn09ActionPerformed(evt);
            }
        });

        btn10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn10.setText("10");
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });

        btn71.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn71.setText("71");
        btn71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn71ActionPerformed(evt);
            }
        });

        btn88.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn88.setText("88");
        btn88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn88ActionPerformed(evt);
            }
        });

        btn82.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn82.setText("82");
        btn82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn82ActionPerformed(evt);
            }
        });

        btn81.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn81.setText("81");
        btn81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn81ActionPerformed(evt);
            }
        });

        btn84.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn84.setText("84");
        btn84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn84ActionPerformed(evt);
            }
        });

        btn83.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn83.setText("83");
        btn83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn83ActionPerformed(evt);
            }
        });

        btn86.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn86.setText("86");
        btn86.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn86ActionPerformed(evt);
            }
        });

        btn85.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn85.setText("85");
        btn85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn85ActionPerformed(evt);
            }
        });

        btn87.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn87.setText("87");
        btn87.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn87ActionPerformed(evt);
            }
        });

        btn11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn11.setText("11");
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });

        btn12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn12.setText("12");
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });

        btn13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn13.setText("13");
        btn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn13ActionPerformed(evt);
            }
        });

        btn14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn14.setText("14");
        btn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn14ActionPerformed(evt);
            }
        });

        btn15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn15.setText("15");
        btn15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn15ActionPerformed(evt);
            }
        });

        btn16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn16.setText("16");
        btn16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16ActionPerformed(evt);
            }
        });

        btn17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn17.setText("17");
        btn17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn17ActionPerformed(evt);
            }
        });

        btn18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn18.setText("18");
        btn18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn18ActionPerformed(evt);
            }
        });

        btn19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn19.setText("19");
        btn19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn19ActionPerformed(evt);
            }
        });

        btn20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn20.setText("20");
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20ActionPerformed(evt);
            }
        });

        btn21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn21.setText("21");
        btn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn21ActionPerformed(evt);
            }
        });

        btn22.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn22.setText("22");
        btn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn22ActionPerformed(evt);
            }
        });

        btn23.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn23.setText("23");
        btn23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn23ActionPerformed(evt);
            }
        });

        btn24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn24.setText("24");
        btn24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn24ActionPerformed(evt);
            }
        });

        btn25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn25.setText("25");
        btn25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn25ActionPerformed(evt);
            }
        });

        btn26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn26.setText("26");
        btn26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn26ActionPerformed(evt);
            }
        });

        btn27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn27.setText("27");
        btn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn27ActionPerformed(evt);
            }
        });

        btn28.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn28.setText("28");
        btn28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn28ActionPerformed(evt);
            }
        });

        btn29.setBackground(new java.awt.Color(255, 255, 255));
        btn29.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn29.setText("29");
        btn29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn29ActionPerformed(evt);
            }
        });

        btn30.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn30.setText("30");
        btn30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn30ActionPerformed(evt);
            }
        });

        btn31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn31.setText("31");
        btn31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn31ActionPerformed(evt);
            }
        });

        btn32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn32.setText("32");
        btn32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn32ActionPerformed(evt);
            }
        });

        btn33.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn33.setText("33");
        btn33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn33ActionPerformed(evt);
            }
        });

        btn34.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn34.setText("34");
        btn34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn34ActionPerformed(evt);
            }
        });

        btn35.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn35.setText("35");
        btn35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn35ActionPerformed(evt);
            }
        });

        btn36.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn36.setText("36");
        btn36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn36ActionPerformed(evt);
            }
        });

        btn37.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn37.setText("37");
        btn37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn37ActionPerformed(evt);
            }
        });

        btn38.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn38.setText("38");
        btn38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn38ActionPerformed(evt);
            }
        });

        btn39.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn39.setText("39");
        btn39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn39ActionPerformed(evt);
            }
        });

        btn40.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn40.setText("40");
        btn40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn40ActionPerformed(evt);
            }
        });

        btn41.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn41.setText("41");
        btn41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn41ActionPerformed(evt);
            }
        });

        btn42.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn42.setText("42");
        btn42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn42ActionPerformed(evt);
            }
        });

        btn43.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn43.setText("43");
        btn43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn43ActionPerformed(evt);
            }
        });

        btn44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn44.setText("44");
        btn44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn44ActionPerformed(evt);
            }
        });

        btn45.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn45.setText("45");
        btn45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn45ActionPerformed(evt);
            }
        });

        btn46.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn46.setText("46");
        btn46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn46ActionPerformed(evt);
            }
        });

        btn47.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn47.setText("47");
        btn47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn47ActionPerformed(evt);
            }
        });

        btn48.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn48.setText("48");
        btn48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn48ActionPerformed(evt);
            }
        });

        btn49.setBackground(new java.awt.Color(255, 255, 255));
        btn49.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn49.setText("49");
        btn49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn49ActionPerformed(evt);
            }
        });

        btn50.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn50.setText("50");
        btn50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn50ActionPerformed(evt);
            }
        });

        btn51.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn51.setText("51");
        btn51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn51ActionPerformed(evt);
            }
        });

        btn52.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn52.setText("52");
        btn52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn52ActionPerformed(evt);
            }
        });

        btn53.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn53.setText("53");
        btn53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn53ActionPerformed(evt);
            }
        });

        btn54.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn54.setText("54");
        btn54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn54ActionPerformed(evt);
            }
        });

        btn55.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn55.setText("55");
        btn55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn55ActionPerformed(evt);
            }
        });

        btn56.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn56.setText("56");
        btn56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn56ActionPerformed(evt);
            }
        });

        btn57.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn57.setText("57");
        btn57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn57ActionPerformed(evt);
            }
        });

        btn58.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn58.setText("58");
        btn58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn58ActionPerformed(evt);
            }
        });

        btn59.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn59.setText("59");
        btn59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn59ActionPerformed(evt);
            }
        });

        btn60.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn60.setText("60");
        btn60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn60ActionPerformed(evt);
            }
        });

        btn61.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn61.setText("61");
        btn61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn61ActionPerformed(evt);
            }
        });

        btn62.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn62.setText("62");
        btn62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn62ActionPerformed(evt);
            }
        });

        btn63.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn63.setText("63");
        btn63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn63ActionPerformed(evt);
            }
        });

        btn64.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn64.setText("64");
        btn64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn64ActionPerformed(evt);
            }
        });

        btn65.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn65.setText("65");
        btn65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn65ActionPerformed(evt);
            }
        });

        btn66.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn66.setText("66");
        btn66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn66ActionPerformed(evt);
            }
        });

        btn67.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn67.setText("67");
        btn67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn67ActionPerformed(evt);
            }
        });

        btn68.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn68.setText("68");
        btn68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn68ActionPerformed(evt);
            }
        });

        btn69.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn69.setText("69");
        btn69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn69ActionPerformed(evt);
            }
        });

        btn70.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn70.setText("70");
        btn70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn70ActionPerformed(evt);
            }
        });

        btn89.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn89.setText("89");
        btn89.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn89ActionPerformed(evt);
            }
        });

        btn72.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn72.setText("72");
        btn72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn72ActionPerformed(evt);
            }
        });

        btn73.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn73.setText("73");
        btn73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn73ActionPerformed(evt);
            }
        });

        btn74.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn74.setText("74");
        btn74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn74ActionPerformed(evt);
            }
        });

        btn75.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn75.setText("75");
        btn75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn75ActionPerformed(evt);
            }
        });

        btn76.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn76.setText("76");
        btn76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn76ActionPerformed(evt);
            }
        });

        btn77.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn77.setText("77");
        btn77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn77ActionPerformed(evt);
            }
        });

        btn78.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn78.setText("78");
        btn78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn78ActionPerformed(evt);
            }
        });

        btn79.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn79.setText("79");
        btn79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn79ActionPerformed(evt);
            }
        });

        btn80.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn80.setText("80");
        btn80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn80ActionPerformed(evt);
            }
        });

        btn99.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn99.setText("99");
        btn99.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn99ActionPerformed(evt);
            }
        });

        btn92.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn92.setText("92");
        btn92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn92ActionPerformed(evt);
            }
        });

        btn91.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn91.setText("91");
        btn91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn91ActionPerformed(evt);
            }
        });

        btn93.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn93.setText("93");
        btn93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn93ActionPerformed(evt);
            }
        });

        btn96.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn96.setText("96");
        btn96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn96ActionPerformed(evt);
            }
        });

        btn95.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn95.setText("95");
        btn95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn95ActionPerformed(evt);
            }
        });

        btn97.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn97.setText("97");
        btn97.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn97ActionPerformed(evt);
            }
        });

        btn90.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn90.setText("90");
        btn90.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn90ActionPerformed(evt);
            }
        });

        btn94.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn94.setText("94");
        btn94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn94ActionPerformed(evt);
            }
        });

        btn98.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn98.setText("98");
        btn98.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn98ActionPerformed(evt);
            }
        });

        btnStadistics.setText("Estadisticas");
        btnStadistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStadisticsActionPerformed(evt);
            }
        });

        btnTotalPrintImpression.setText("Impresion total único");
        btnTotalPrintImpression.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalPrintImpressionActionPerformed(evt);
            }
        });

        btnScanBarCode.setText("Escanear código de barras");
        btnScanBarCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanBarCodeActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn100.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn100.setForeground(new java.awt.Color(204, 0, 0));
        btn100.setText("₡100");
        btn100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn100ActionPerformed(evt);
            }
        });

        btn200.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn200.setForeground(new java.awt.Color(204, 0, 0));
        btn200.setText("₡200");
        btn200.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn200ActionPerformed(evt);
            }
        });

        btn300.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn300.setForeground(new java.awt.Color(204, 0, 0));
        btn300.setText("₡300");
        btn300.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn300ActionPerformed(evt);
            }
        });

        btn400.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn400.setForeground(new java.awt.Color(204, 0, 0));
        btn400.setText("₡400");
        btn400.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn400ActionPerformed(evt);
            }
        });

        btn500.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn500.setForeground(new java.awt.Color(204, 0, 0));
        btn500.setText("₡500");
        btn500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn500ActionPerformed(evt);
            }
        });

        btn600.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn600.setForeground(new java.awt.Color(204, 0, 0));
        btn600.setText("₡600");
        btn600.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn600ActionPerformed(evt);
            }
        });

        btn700.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn700.setForeground(new java.awt.Color(204, 0, 0));
        btn700.setText("₡700");
        btn700.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn700ActionPerformed(evt);
            }
        });

        btn800.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn800.setForeground(new java.awt.Color(204, 0, 0));
        btn800.setText("₡800");
        btn800.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn800ActionPerformed(evt);
            }
        });

        btn900.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn900.setForeground(new java.awt.Color(204, 0, 0));
        btn900.setText("₡900");
        btn900.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn900ActionPerformed(evt);
            }
        });

        btn1000.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn1000.setForeground(new java.awt.Color(204, 0, 0));
        btn1000.setText("₡1000");
        btn1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1000ActionPerformed(evt);
            }
        });

        btn2000.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn2000.setForeground(new java.awt.Color(204, 0, 0));
        btn2000.setText("₡2000");
        btn2000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2000ActionPerformed(evt);
            }
        });

        btn5000.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn5000.setForeground(new java.awt.Color(204, 0, 0));
        btn5000.setText("₡5000");
        btn5000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5000ActionPerformed(evt);
            }
        });

        lblMorningClosingTime.setText("Cierre de la mañana:");

        lblNightClosingTime.setText("Cierre de la noche:");

        lblBettingPeriod.setText("Periodo de apuestas:");

        bgPeriodType.add(cbMorning);
        cbMorning.setSelected(true);
        cbMorning.setText("Día");
        cbMorning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMorningActionPerformed(evt);
            }
        });

        bgPeriodType.add(cbNight);
        cbNight.setText("Noche");
        cbNight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNightActionPerformed(evt);
            }
        });

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

        btnPay.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPay.setText("Pagar");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        lblTotal.setText("Total:");

        lblTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotalAmount.setText("0");

        btnRemove.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRemove.setText("Excluir");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        lblTotalQuantityNumber.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotalQuantityNumber.setText("0");

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnSaveChinese.setText("天");
        btnSaveChinese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChineseActionPerformed(evt);
            }
        });

        lblMoney.setText("Plata");

        lblNumberMoney.setText("Total numero:");

        btnRemoveChinese.setText("天");
        btnRemoveChinese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveChineseActionPerformed(evt);
            }
        });

        btnPayChinese.setText("天");
        btnPayChinese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayChineseActionPerformed(evt);
            }
        });

        tfMorningClosingTime.setText("jLabel1");

        tfNightClosingTime.setText("jLabel2");

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
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
        jTable1.setRowHeight(28);
        jScrollPane2.setViewportView(jTable1);

        btnRemoveAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRemoveAll.setText("Reajustar");
        btnRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllActionPerformed(evt);
            }
        });

        btnRemoveAllChinese.setText("天");
        btnRemoveAllChinese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllChineseActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(136, 145));

        tfSelectedNumber.setFont(new java.awt.Font("Tahoma", 1, 55)); // NOI18N
        tfSelectedNumber.setText("    ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(tfSelectedNumber)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(tfSelectedNumber)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn00)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn01)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn02)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn03)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn04)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn05)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn06)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn07)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn08)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn09))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnScanBarCode)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnTotalPrintImpression)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnStadistics)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbSpanish))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblNightClosingTime)
                                        .addComponent(lblMorningClosingTime))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfMorningClosingTime)
                                        .addComponent(tfNightClosingTime))
                                    .addGap(134, 134, 134)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblBettingPeriod)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(cbMorning)
                                            .addGap(28, 28, 28)
                                            .addComponent(cbNight)))))
                            .addGap(2, 2, 2)
                            .addComponent(cbChinese)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn90)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn91)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn92)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn93)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn94)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn95)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn96)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn97)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn98)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn99))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn19))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn27)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn29))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn30)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn31)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn33)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn34)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn35)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn36)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn37)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn38)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn39))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn40)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn41)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn42)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn43)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn44)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn45)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn46)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn47)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn48)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn49))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn50)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn51)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn52)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn53)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn54)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn55)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn56)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn57)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn58)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn59))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn60)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn61)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn62)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn63)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn64)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn65)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn66)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn67)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn68)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn69))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn70)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn71)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn72)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn73)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn74)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn75)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn76)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn77)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn78)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn79))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn80)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn81)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn82)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn83)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn84)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn85)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn86)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn87)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn88)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn89))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMoney)
                            .addComponent(lblNumberMoney))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalQuantityNumber)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(priceAct, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSaveChinese, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalAmount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPayChinese)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRemove)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoveChinese))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRemoveAll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemoveAllChinese))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn400, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn700, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn1000, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn2000, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn800, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn500, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn200, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn5000, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn900, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn600, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn300, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn100, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn200)
                                    .addComponent(btn300))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn400)
                                    .addComponent(btn500)
                                    .addComponent(btn600))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn700)
                                    .addComponent(btn800)
                                    .addComponent(btn900))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn1000)
                                    .addComponent(btn2000)
                                    .addComponent(btn5000)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMoney)
                            .addComponent(priceAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSaveChinese, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalQuantityNumber)
                            .addComponent(lblNumberMoney))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnRemove)
                                    .addComponent(btnRemoveChinese)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotal)
                            .addComponent(lblTotalAmount)
                            .addComponent(btnPay)
                            .addComponent(btnPayChinese)
                            .addComponent(btnRemoveAll)
                            .addComponent(btnRemoveAllChinese))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnScanBarCode)
                            .addComponent(btnTotalPrintImpression)
                            .addComponent(btnStadistics)
                            .addComponent(cbSpanish)
                            .addComponent(cbChinese))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMorningClosingTime)
                            .addComponent(tfMorningClosingTime)
                            .addComponent(lblBettingPeriod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNightClosingTime)
                                    .addComponent(tfNightClosingTime))
                                .addComponent(cbNight, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(cbMorning))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn00, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn01, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn02, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn03, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn04, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn05, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn06, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn07, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn08, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn09, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn22, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn23, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn24, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn25, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn26, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn27, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn28, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn29, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn31, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn32, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn33, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn34, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn35, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn36, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn37, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn38, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn39, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn40, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn41, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn42, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn43, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn44, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn45, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn46, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn47, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn48, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn49, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn50, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn51, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn52, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn53, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn54, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn55, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn56, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn57, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn58, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn59, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn60, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn61, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn62, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn63, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn64, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn65, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn66, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn67, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn68, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn69, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn70, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn71, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn72, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn73, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn74, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn75, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn76, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn77, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn78, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn79, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn80, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn81, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn82, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn83, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn84, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn85, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn86, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn87, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn88, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn89, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn90, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn91, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn92, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn93, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn94, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn95, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn96, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn97, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn98, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn99, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(155, 155, 155))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn00ActionPerformed
        // TODO add your handling code here:
        button = btn00;
        showSelectedNumber();
    }//GEN-LAST:event_btn00ActionPerformed

    private void btn02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn02ActionPerformed
        // TODO add your handling code here:
        button = btn02;
        showSelectedNumber();
    }//GEN-LAST:event_btn02ActionPerformed

    private void btn01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn01ActionPerformed
        // TODO add your handling code here:
        button = btn01;
        showSelectedNumber();
    }//GEN-LAST:event_btn01ActionPerformed

    private void btn03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn03ActionPerformed
        // TODO add your handling code here:
        button = btn03;
        showSelectedNumber();
    }//GEN-LAST:event_btn03ActionPerformed

    private void btn06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn06ActionPerformed
        // TODO add your handling code here:
        button = btn06;
        showSelectedNumber();
    }//GEN-LAST:event_btn06ActionPerformed

    private void btn05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn05ActionPerformed
        // TODO add your handling code here:
        button = btn05;
        showSelectedNumber();
    }//GEN-LAST:event_btn05ActionPerformed

    private void btn07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn07ActionPerformed
        // TODO add your handling code here:
        button = btn07;
        showSelectedNumber();
    }//GEN-LAST:event_btn07ActionPerformed

    private void btn04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn04ActionPerformed
        // TODO add your handling code here:
        button = btn04;
        showSelectedNumber();
    }//GEN-LAST:event_btn04ActionPerformed

    private void btn08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn08ActionPerformed
        // TODO add your handling code here:
        button = btn08;
        showSelectedNumber();
    }//GEN-LAST:event_btn08ActionPerformed

    private void btn09ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn09ActionPerformed
        // TODO add your handling code here:
        button = btn09;
        showSelectedNumber();
    }//GEN-LAST:event_btn09ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        // TODO add your handling code here:
        button = btn10;
        showSelectedNumber();
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn71ActionPerformed
        // TODO add your handling code here:
        button = btn71;
        showSelectedNumber();
    }//GEN-LAST:event_btn71ActionPerformed

    private void btn88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn88ActionPerformed
        // TODO add your handling code here:
        button = btn88;
        showSelectedNumber();
    }//GEN-LAST:event_btn88ActionPerformed

    private void btn82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn82ActionPerformed
        // TODO add your handling code here:
        button = btn82;
        showSelectedNumber();
    }//GEN-LAST:event_btn82ActionPerformed

    private void btn81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn81ActionPerformed
        // TODO add your handling code here:
        button = btn81;
        showSelectedNumber();
    }//GEN-LAST:event_btn81ActionPerformed

    private void btn84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn84ActionPerformed
        // TODO add your handling code here:
        button = btn84;
        showSelectedNumber();
    }//GEN-LAST:event_btn84ActionPerformed

    private void btn83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn83ActionPerformed
        // TODO add your handling code here:
        button = btn83;
        showSelectedNumber();
    }//GEN-LAST:event_btn83ActionPerformed

    private void btn86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn86ActionPerformed
        // TODO add your handling code here:
        button = btn86;
        showSelectedNumber();
    }//GEN-LAST:event_btn86ActionPerformed

    private void btn85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn85ActionPerformed
        // TODO add your handling code here:
        button = btn85;
        showSelectedNumber();
    }//GEN-LAST:event_btn85ActionPerformed

    private void btn87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn87ActionPerformed
        // TODO add your handling code here:
        button = btn87;
        showSelectedNumber();
    }//GEN-LAST:event_btn87ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        // TODO add your handling code here:
        button = btn11;
        showSelectedNumber();
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        // TODO add your handling code here:
        button = btn12;
        showSelectedNumber();
    }//GEN-LAST:event_btn12ActionPerformed

    private void btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn13ActionPerformed
        // TODO add your handling code here:
        button = btn13;
        showSelectedNumber();
    }//GEN-LAST:event_btn13ActionPerformed

    private void btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn14ActionPerformed
        // TODO add your handling code here:
        button = btn14;
        showSelectedNumber();
    }//GEN-LAST:event_btn14ActionPerformed

    private void btn15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn15ActionPerformed
        // TODO add your handling code here:
        button = btn15;
        showSelectedNumber();
    }//GEN-LAST:event_btn15ActionPerformed

    private void btn16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16ActionPerformed
        // TODO add your handling code here:
        button = btn16;
        showSelectedNumber();
    }//GEN-LAST:event_btn16ActionPerformed

    private void btn17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn17ActionPerformed
        // TODO add your handling code here:
        button = btn17;
        showSelectedNumber();
    }//GEN-LAST:event_btn17ActionPerformed

    private void btn18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn18ActionPerformed
        // TODO add your handling code here:
        button = btn18;
        showSelectedNumber();
    }//GEN-LAST:event_btn18ActionPerformed

    private void btn19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn19ActionPerformed
        // TODO add your handling code here:
        button = btn19;
        showSelectedNumber();
    }//GEN-LAST:event_btn19ActionPerformed

    private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20ActionPerformed
        // TODO add your handling code here:
        button = btn20;
        showSelectedNumber();
    }//GEN-LAST:event_btn20ActionPerformed

    private void btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn21ActionPerformed
        // TODO add your handling code here:
        button = btn21;
        showSelectedNumber();
    }//GEN-LAST:event_btn21ActionPerformed

    private void btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn22ActionPerformed
        // TODO add your handling code here:
        button = btn22;
        showSelectedNumber();
    }//GEN-LAST:event_btn22ActionPerformed

    private void btn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn23ActionPerformed
        // TODO add your handling code here:
        button = btn23;
        showSelectedNumber();
    }//GEN-LAST:event_btn23ActionPerformed

    private void btn24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn24ActionPerformed
        // TODO add your handling code here:
        button = btn24;
        showSelectedNumber();
    }//GEN-LAST:event_btn24ActionPerformed

    private void btn25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn25ActionPerformed
        // TODO add your handling code here:
        button = btn25;
        showSelectedNumber();
    }//GEN-LAST:event_btn25ActionPerformed

    private void btn26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn26ActionPerformed
        // TODO add your handling code here:
        button = btn26;
        showSelectedNumber();
    }//GEN-LAST:event_btn26ActionPerformed

    private void btn27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn27ActionPerformed
        // TODO add your handling code here:
        button = btn27;
        showSelectedNumber();
    }//GEN-LAST:event_btn27ActionPerformed

    private void btn28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn28ActionPerformed
        // TODO add your handling code here:
        button = btn28;
        showSelectedNumber();
    }//GEN-LAST:event_btn28ActionPerformed

    private void btn29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn29ActionPerformed
        // TODO add your handling code here:
        button = btn29;
        showSelectedNumber();
    }//GEN-LAST:event_btn29ActionPerformed

    private void btn30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn30ActionPerformed
        // TODO add your handling code here:
        button = btn30;
        showSelectedNumber();
    }//GEN-LAST:event_btn30ActionPerformed

    private void btn31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn31ActionPerformed
        // TODO add your handling code here:
        button = btn31;
        showSelectedNumber();
    }//GEN-LAST:event_btn31ActionPerformed

    private void btn32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn32ActionPerformed
        // TODO add your handling code here:
        button = btn32;
        showSelectedNumber();
    }//GEN-LAST:event_btn32ActionPerformed

    private void btn33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn33ActionPerformed
        // TODO add your handling code here:
        button = btn33;
        showSelectedNumber();
    }//GEN-LAST:event_btn33ActionPerformed

    private void btn34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn34ActionPerformed
        // TODO add your handling code here:
        button = btn34;
        showSelectedNumber();
    }//GEN-LAST:event_btn34ActionPerformed

    private void btn35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn35ActionPerformed
        // TODO add your handling code here:
        button = btn35;
        showSelectedNumber();
    }//GEN-LAST:event_btn35ActionPerformed

    private void btn36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn36ActionPerformed
        // TODO add your handling code here:
        button = btn36;
        showSelectedNumber();
    }//GEN-LAST:event_btn36ActionPerformed

    private void btn37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn37ActionPerformed
        // TODO add your handling code here:
        button = btn37;
        showSelectedNumber();
    }//GEN-LAST:event_btn37ActionPerformed

    private void btn38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn38ActionPerformed
        // TODO add your handling code here:
        button = btn38;
        showSelectedNumber();
    }//GEN-LAST:event_btn38ActionPerformed

    private void btn39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn39ActionPerformed
        // TODO add your handling code here:
        button = btn39;
        showSelectedNumber();
    }//GEN-LAST:event_btn39ActionPerformed

    private void btn40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn40ActionPerformed
        // TODO add your handling code here:
        button = btn40;
        showSelectedNumber();
    }//GEN-LAST:event_btn40ActionPerformed

    private void btn41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn41ActionPerformed
        // TODO add your handling code here:
        button = btn41;
        showSelectedNumber();
    }//GEN-LAST:event_btn41ActionPerformed

    private void btn42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn42ActionPerformed
        // TODO add your handling code here:
        button = btn42;
        showSelectedNumber();
    }//GEN-LAST:event_btn42ActionPerformed

    private void btn43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn43ActionPerformed
        // TODO add your handling code here:
        button = btn43;
        showSelectedNumber();
    }//GEN-LAST:event_btn43ActionPerformed

    private void btn44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn44ActionPerformed
        // TODO add your handling code here:
        button = btn44;
        showSelectedNumber();
    }//GEN-LAST:event_btn44ActionPerformed

    private void btn45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn45ActionPerformed
        // TODO add your handling code here:
        button = btn45;
        showSelectedNumber();
    }//GEN-LAST:event_btn45ActionPerformed

    private void btn46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn46ActionPerformed
        // TODO add your handling code here:
        button = btn46;
        showSelectedNumber();
    }//GEN-LAST:event_btn46ActionPerformed

    private void btn47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn47ActionPerformed
        // TODO add your handling code here:
        button = btn47;
        showSelectedNumber();
    }//GEN-LAST:event_btn47ActionPerformed

    private void btn48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn48ActionPerformed
        // TODO add your handling code here:
        button = btn48;
        showSelectedNumber();
    }//GEN-LAST:event_btn48ActionPerformed

    private void btn49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn49ActionPerformed
        // TODO add your handling code here:
        button = btn49;
        showSelectedNumber();
    }//GEN-LAST:event_btn49ActionPerformed

    private void btn50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn50ActionPerformed
        // TODO add your handling code here:
        button = btn50;
        showSelectedNumber();
    }//GEN-LAST:event_btn50ActionPerformed

    private void btn51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn51ActionPerformed
        // TODO add your handling code here:
        button = btn51;
        showSelectedNumber();
    }//GEN-LAST:event_btn51ActionPerformed

    private void btn52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn52ActionPerformed
        // TODO add your handling code here:
        button = btn52;
        showSelectedNumber();
    }//GEN-LAST:event_btn52ActionPerformed

    private void btn53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn53ActionPerformed
        // TODO add your handling code here:
        button = btn53;
        showSelectedNumber();
    }//GEN-LAST:event_btn53ActionPerformed

    private void btn54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn54ActionPerformed
        // TODO add your handling code here:
        button = btn54;
        showSelectedNumber();
    }//GEN-LAST:event_btn54ActionPerformed

    private void btn55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn55ActionPerformed
        // TODO add your handling code here:
        button = btn55;
        showSelectedNumber();
    }//GEN-LAST:event_btn55ActionPerformed

    private void btn56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn56ActionPerformed
        // TODO add your handling code here:
        button = btn56;
        showSelectedNumber();
    }//GEN-LAST:event_btn56ActionPerformed

    private void btn57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn57ActionPerformed
        // TODO add your handling code here:
        button = btn57;
        showSelectedNumber();
    }//GEN-LAST:event_btn57ActionPerformed

    private void btn58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn58ActionPerformed
        // TODO add your handling code here:
        button = btn58;
        showSelectedNumber();
    }//GEN-LAST:event_btn58ActionPerformed

    private void btn59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn59ActionPerformed
        // TODO add your handling code here:
        button = btn59;
        showSelectedNumber();
    }//GEN-LAST:event_btn59ActionPerformed

    private void btn60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn60ActionPerformed
        // TODO add your handling code here:
        button = btn60;
        showSelectedNumber();
    }//GEN-LAST:event_btn60ActionPerformed

    private void btn61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn61ActionPerformed
        // TODO add your handling code here:
        button = btn61;
        showSelectedNumber();
    }//GEN-LAST:event_btn61ActionPerformed

    private void btn62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn62ActionPerformed
        // TODO add your handling code here:
        button = btn62;
        showSelectedNumber();
    }//GEN-LAST:event_btn62ActionPerformed

    private void btn63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn63ActionPerformed
        // TODO add your handling code here:
        button = btn63;
        showSelectedNumber();
    }//GEN-LAST:event_btn63ActionPerformed

    private void btn64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn64ActionPerformed
        // TODO add your handling code here:
        button = btn64;
        showSelectedNumber();
    }//GEN-LAST:event_btn64ActionPerformed

    private void btn65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn65ActionPerformed
        // TODO add your handling code here:
        button = btn65;
        showSelectedNumber();
    }//GEN-LAST:event_btn65ActionPerformed

    private void btn66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn66ActionPerformed
        // TODO add your handling code here:
        button = btn66;
        showSelectedNumber();
    }//GEN-LAST:event_btn66ActionPerformed

    private void btn67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn67ActionPerformed
        // TODO add your handling code here:
        button = btn67;
        showSelectedNumber();
    }//GEN-LAST:event_btn67ActionPerformed

    private void btn68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn68ActionPerformed
        // TODO add your handling code here:
        button = btn68;
        showSelectedNumber();
    }//GEN-LAST:event_btn68ActionPerformed

    private void btn69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn69ActionPerformed
        // TODO add your handling code here:
        button = btn69;
        showSelectedNumber();
    }//GEN-LAST:event_btn69ActionPerformed

    private void btn70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn70ActionPerformed
        // TODO add your handling code here:
        button = btn70;
        showSelectedNumber();
    }//GEN-LAST:event_btn70ActionPerformed

    private void btn89ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn89ActionPerformed
        // TODO add your handling code here:
        button = btn89;
        showSelectedNumber();
    }//GEN-LAST:event_btn89ActionPerformed

    private void btn72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn72ActionPerformed
        // TODO add your handling code here:
        button = btn72;
        showSelectedNumber();
    }//GEN-LAST:event_btn72ActionPerformed

    private void btn73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn73ActionPerformed
        // TODO add your handling code here:
        button = btn73;
        showSelectedNumber();
    }//GEN-LAST:event_btn73ActionPerformed

    private void btn74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn74ActionPerformed
        // TODO add your handling code here:
        button = btn74;
        showSelectedNumber();
    }//GEN-LAST:event_btn74ActionPerformed

    private void btn75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn75ActionPerformed
        // TODO add your handling code here:
        button = btn75;
        showSelectedNumber();
    }//GEN-LAST:event_btn75ActionPerformed

    private void btn76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn76ActionPerformed
        // TODO add your handling code here:
        button = btn76;
        showSelectedNumber();
    }//GEN-LAST:event_btn76ActionPerformed

    private void btn77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn77ActionPerformed
        // TODO add your handling code here:
        button = btn77;
        showSelectedNumber();
    }//GEN-LAST:event_btn77ActionPerformed

    private void btn78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn78ActionPerformed
        // TODO add your handling code here:
        button = btn78;
        showSelectedNumber();
    }//GEN-LAST:event_btn78ActionPerformed

    private void btn79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn79ActionPerformed
        // TODO add your handling code here:
        button = btn79;
        showSelectedNumber();
    }//GEN-LAST:event_btn79ActionPerformed

    private void btn80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn80ActionPerformed
        // TODO add your handling code here:
        button = btn80;
        showSelectedNumber();
    }//GEN-LAST:event_btn80ActionPerformed

    private void btn99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn99ActionPerformed
        // TODO add your handling code here:
        button = btn99;
        showSelectedNumber();
    }//GEN-LAST:event_btn99ActionPerformed

    private void btn92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn92ActionPerformed
        // TODO add your handling code here:
        button = btn92;
        showSelectedNumber();
    }//GEN-LAST:event_btn92ActionPerformed

    private void btn91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn91ActionPerformed
        // TODO add your handling code here:
        button = btn91;
        showSelectedNumber();
    }//GEN-LAST:event_btn91ActionPerformed

    private void btn93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn93ActionPerformed
        // TODO add your handling code here:
        button = btn93;
        showSelectedNumber();
    }//GEN-LAST:event_btn93ActionPerformed

    private void btn96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn96ActionPerformed
        // TODO add your handling code here:
        button = btn96;
        showSelectedNumber();
    }//GEN-LAST:event_btn96ActionPerformed

    private void btn95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn95ActionPerformed
        // TODO add your handling code here:
        button = btn95;
        showSelectedNumber();
    }//GEN-LAST:event_btn95ActionPerformed

    private void btn97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn97ActionPerformed
        // TODO add your handling code here:
        button = btn97;
        showSelectedNumber();
    }//GEN-LAST:event_btn97ActionPerformed

    private void btn90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn90ActionPerformed
        // TODO add your handling code here:
        button = btn90;
        showSelectedNumber();
    }//GEN-LAST:event_btn90ActionPerformed

    private void btn94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn94ActionPerformed
        // TODO add your handling code here:
        button = btn94;
        showSelectedNumber();
    }//GEN-LAST:event_btn94ActionPerformed

    private void btn98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn98ActionPerformed
        // TODO add your handling code here:
        button = btn98;
        showSelectedNumber();
    }//GEN-LAST:event_btn98ActionPerformed

    private void btnStadisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStadisticsActionPerformed
        // TODO add your handling code here:
        StadisticsCenter oClienteCrear = new StadisticsCenter();
        oClienteCrear.setAlwaysOnTop(true);
        oClienteCrear.setVisible(true);
        oClienteCrear.setLocationRelativeTo(null);       
        if (language == "spanish") {
            oClienteCrear.setLanguageToSpanish();
        }
        else if (language == "chinese") {
            oClienteCrear.setLanguageToChinese();
        }
    }//GEN-LAST:event_btnStadisticsActionPerformed

    private void btnTotalPrintImpressionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalPrintImpressionActionPerformed
        // TODO add your handling code here:
        TicketWindowPrint oClienteCrear = new TicketWindowPrint(-1);
        oClienteCrear.setAlwaysOnTop(true);
        oClienteCrear.setVisible(true);
        oClienteCrear.setLocationRelativeTo(null);       
        if (language == "spanish") {
            oClienteCrear.setLanguageToSpanish();
        }
        else if (language == "chinese") {
            oClienteCrear.setLanguageToChinese();
        }
    }//GEN-LAST:event_btnTotalPrintImpressionActionPerformed

    private void btnScanBarCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanBarCodeActionPerformed
        // TODO add your handling code here:
        ticketCodScanDelete oClienteCrear = new ticketCodScanDelete();
        oClienteCrear.setAlwaysOnTop(true);
        oClienteCrear.setVisible(true);
        oClienteCrear.setLocationRelativeTo(null);   
        oClienteCrear.setSelling(this);
        if (language == "spanish") {
            oClienteCrear.setLanguageToSpanish();
        }
        else if (language == "chinese") {
            oClienteCrear.setLanguageToChinese();
        }
    }//GEN-LAST:event_btnScanBarCodeActionPerformed

    private void btn100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn100ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(100);
    }//GEN-LAST:event_btn100ActionPerformed

    private void btn200ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn200ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(200);
    }//GEN-LAST:event_btn200ActionPerformed

    private void btn300ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn300ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(300);
    }//GEN-LAST:event_btn300ActionPerformed

    private void btn400ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn400ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(400);
    }//GEN-LAST:event_btn400ActionPerformed

    private void btn500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn500ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(500);
    }//GEN-LAST:event_btn500ActionPerformed

    private void btn600ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn600ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(600);
    }//GEN-LAST:event_btn600ActionPerformed

    private void btn700ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn700ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(700);
    }//GEN-LAST:event_btn700ActionPerformed

    private void btn800ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn800ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(800);
    }//GEN-LAST:event_btn800ActionPerformed

    private void btn900ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn900ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(900);
    }//GEN-LAST:event_btn900ActionPerformed

    private void btn1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1000ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(1000);
    }//GEN-LAST:event_btn1000ActionPerformed

    private void btn2000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2000ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(2000);
    }//GEN-LAST:event_btn2000ActionPerformed

    private void btn5000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5000ActionPerformed
        // TODO add your handling code here:
        priceAct.setText("");
        showSelectedMoney(5000);
    }//GEN-LAST:event_btn5000ActionPerformed

    private void cbSpanishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSpanishActionPerformed
        // TODO add your handling code here:
        showSpanishButtons();
        setEnableOrNotButtons(false);
        hideChineseButtons();
        setLanguageToSpanish();
        ConnectionBD con= new ConnectionBD();
        con.createLanguage("spanish");
    }//GEN-LAST:event_cbSpanishActionPerformed

    private void cbChineseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChineseActionPerformed
        // TODO add your handling code here:
        showChineseButtons();
        setEnableOrNotButtons(false);
        hideSpanishButtons(); 
        setLanguageToChinese();
        ConnectionBD con= new ConnectionBD();
        con.createLanguage("chinese");
    }//GEN-LAST:event_cbChineseActionPerformed

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        
        // TODO add your handling code here:
        Calendar cal = Calendar.getInstance(); 
        String hour = String.valueOf(cal.get(cal.HOUR_OF_DAY));
        String minute = String.valueOf(cal.get(cal.MINUTE));
        String hora = cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE); 
        int appHour = 0;
        int currentHour = 0;
        int appMinute = 0;
        int currentMinute = 0;
        boolean error = false;
        if (cbMorning.isSelected()) {
            String morningSplitHour[] = tfMorningClosingTime.getText().split(":");
            appHour = Integer.parseInt(morningSplitHour[0]);
            currentHour = Integer.parseInt(hour);
            appMinute = Integer.parseInt(morningSplitHour[1]);
            currentMinute = Integer.parseInt(minute);
        }
        else if (cbNight.isSelected()) {
            String nightSplitHour[] = tfNightClosingTime.getText().split(":");
            appHour = Integer.parseInt(nightSplitHour[0]);
            currentHour = Integer.parseInt(hour);
            appMinute = Integer.parseInt(nightSplitHour[1]);
            currentMinute = Integer.parseInt(minute);
        }
        if (currentHour < appHour) {
            error = false;
        }
        else if (currentHour == appHour) {
            if (currentMinute < appMinute) {
                error = false;
            }
            else {
                JOptionPane.showMessageDialog(null, "fuera de tiempo");
                error = true;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "fuera de tiempo");
            error = true;
        }
        if (tableModel.getRowCount() == 0) {
            error = true;
            JOptionPane.showMessageDialog(null, "seleccione numero");
        }
        if (error == false) {
            createTicketForPurchase();
            soldNumbersOfTableSetColors();
            setButtonsAvailableOrNot(false);
            //JOptionPane.showMessageDialog(null, "Pago listo. Imprima el tiquete.");
            isTicketPaid = true;
            printOnceTicketIsPaid();
        }
    }//GEN-LAST:event_btnPayActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        lastSave= tfSelectedNumber.getText();        
        try{
            if(lastSave.equals(tableModel.getValueAt(jTable1.getSelectedRow(),0))){                        
                int priceTotl=Integer.parseInt(lblTotalQuantityNumber.getText());
                int total = Integer.parseInt((String) tableModel.getValueAt(jTable1.getSelectedRow(), 1));
                priceTotl= priceTotl+total;            
                lblTotalQuantityNumber.setText(String.valueOf(priceTotl));                        
            }
            removeItemFromList();        
            getTotalAndShowIT();
        }catch(Exception e){
            return;
        }        
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
       try {            
            lastSave= tfSelectedNumber.getText();            
            int money = Integer.parseInt(priceAct.getText());
            int priceTotl=Integer.parseInt(lblTotalQuantityNumber.getText());
            if(priceTotl < money){                
                JOptionPane.showMessageDialog(null,"ERROR, el monto supera al establecido"); //Error por numero inferior..
                return;
            }            
            priceTotl= priceTotl-money;            
            lblTotalQuantityNumber.setText(String.valueOf(priceTotl));
            setPriceToNumber(money);
            getTotalAndShowIT();
            setEnableOrNotButtons(true);
            Rectangle r = jTable1.getCellRect( jTable1.getRowCount()-1, 0, true);
            jScrollPane2.getViewport().scrollRectToVisible (r);
            changeNumber = true;
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, notNumberErrorString);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSaveChineseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveChineseActionPerformed
        // TODO add your handling code here:
        try {
            lastSave= tfSelectedNumber.getText();            
            int money = Integer.parseInt(priceAct.getText());
            int priceTotl=Integer.parseInt(lblTotalQuantityNumber.getText());
            if(priceTotl < money){                
                JOptionPane.showMessageDialog(null,"误差量超过设定"); //Error por numero inferior..
                return;
            }            
            priceTotl= priceTotl-money;            
            lblTotalQuantityNumber.setText(String.valueOf(priceTotl));
            setPriceToNumber(money);
            getTotalAndShowIT();
            setEnableOrNotButtons(true);
            Rectangle r = jTable1.getCellRect( jTable1.getRowCount()-1, 0, true);
            jScrollPane2.getViewport().scrollRectToVisible (r);
            changeNumber = true;
        }
        catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, notNumberErrorString);
        }
    }//GEN-LAST:event_btnSaveChineseActionPerformed

    private void btnRemoveChineseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveChineseActionPerformed
        // TODO add your handling code here:
        lastSave= tfSelectedNumber.getText();        
        try{
            if(lastSave.equals(tableModel.getValueAt(jTable1.getSelectedRow(),0))){                        
                int priceTotl=Integer.parseInt(lblTotalQuantityNumber.getText());
                int total = Integer.parseInt((String) tableModel.getValueAt(jTable1.getSelectedRow(), 1));
                priceTotl= priceTotl+total;            
                lblTotalQuantityNumber.setText(String.valueOf(priceTotl));                        
            }
            removeItemFromList();        
            getTotalAndShowIT();
        }catch(Exception e){
            return;
        }
    }//GEN-LAST:event_btnRemoveChineseActionPerformed

    private void btnPayChineseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayChineseActionPerformed
        // TODO add your handling code here:
        Calendar cal = Calendar.getInstance(); 
        String hour = String.valueOf(cal.get(cal.HOUR_OF_DAY));
        String minute = String.valueOf(cal.get(cal.MINUTE));
        String hora = cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE); 
        int appHour = 0;
        int currentHour = 0;
        int appMinute = 0;
        int currentMinute = 0;
        boolean error = false;
        if (cbMorning.isSelected()) {
            String morningSplitHour[] = tfMorningClosingTime.getText().split(":");
            appHour = Integer.parseInt(morningSplitHour[0]);
            currentHour = Integer.parseInt(hour);
            appMinute = Integer.parseInt(morningSplitHour[1]);
            currentMinute = Integer.parseInt(minute);
        }
        else if (cbNight.isSelected()) {
            String nightSplitHour[] = tfNightClosingTime.getText().split(":");
            appHour = Integer.parseInt(nightSplitHour[0]);
            currentHour = Integer.parseInt(hour);
            appMinute = Integer.parseInt(nightSplitHour[1]);
            currentMinute = Integer.parseInt(minute);
        }
        if (currentHour < appHour) {
            error = false;
        }
        else if (currentHour == appHour) {
            if (currentMinute < appMinute) {
                error = false;
            }
            else {
                JOptionPane.showMessageDialog(null, "截止时间已过");
                error = true;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "截止时间已过");
            error = true;
        }
        if (tableModel.getRowCount() == 0) {
            error = true;
            JOptionPane.showMessageDialog(null, "选择一个号码");
        }
        if (error == false) {
            createTicketForPurchase();
            soldNumbersOfTableSetColors();
            setButtonsAvailableOrNot(false);
            //JOptionPane.showMessageDialog(null, "打印");
            isTicketPaid = true;
            printOnceTicketIsPaid();
        }
    }//GEN-LAST:event_btnPayChineseActionPerformed

    private void cbMorningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMorningActionPerformed
        // TODO add your handling code here:        
        setCurrentTime();        
        removeAllItemsFromList();        
        soldNumbersOfTableSetColors();
    }//GEN-LAST:event_cbMorningActionPerformed

    private void cbNightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNightActionPerformed
        // TODO add your handling code here:
        setCurrentTime();        
        removeAllItemsFromList();
        soldNumbersOfTableSetColors();
    }//GEN-LAST:event_cbNightActionPerformed

    private void btnRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllActionPerformed
        // TODO add your handling code here:
        removeAllItemsFromList();        
        getTotalAndShowIT();
    }//GEN-LAST:event_btnRemoveAllActionPerformed

    private void btnRemoveAllChineseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllChineseActionPerformed
        // TODO add your handling code here:
        removeAllItemsFromList();
        getTotalAndShowIT();
    }//GEN-LAST:event_btnRemoveAllChineseActionPerformed

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
            java.util.logging.Logger.getLogger(Selling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Selling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Selling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Selling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Selling().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgLanguage;
    private javax.swing.ButtonGroup bgPeriodType;
    private javax.swing.JButton btn00;
    private javax.swing.JButton btn01;
    private javax.swing.JButton btn02;
    private javax.swing.JButton btn03;
    private javax.swing.JButton btn04;
    private javax.swing.JButton btn05;
    private javax.swing.JButton btn06;
    private javax.swing.JButton btn07;
    private javax.swing.JButton btn08;
    private javax.swing.JButton btn09;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn100;
    private javax.swing.JButton btn1000;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn19;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn200;
    private javax.swing.JButton btn2000;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn26;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn28;
    private javax.swing.JButton btn29;
    private javax.swing.JButton btn30;
    private javax.swing.JButton btn300;
    private javax.swing.JButton btn31;
    private javax.swing.JButton btn32;
    private javax.swing.JButton btn33;
    private javax.swing.JButton btn34;
    private javax.swing.JButton btn35;
    private javax.swing.JButton btn36;
    private javax.swing.JButton btn37;
    private javax.swing.JButton btn38;
    private javax.swing.JButton btn39;
    private javax.swing.JButton btn40;
    private javax.swing.JButton btn400;
    private javax.swing.JButton btn41;
    private javax.swing.JButton btn42;
    private javax.swing.JButton btn43;
    private javax.swing.JButton btn44;
    private javax.swing.JButton btn45;
    private javax.swing.JButton btn46;
    private javax.swing.JButton btn47;
    private javax.swing.JButton btn48;
    private javax.swing.JButton btn49;
    private javax.swing.JButton btn50;
    private javax.swing.JButton btn500;
    private javax.swing.JButton btn5000;
    private javax.swing.JButton btn51;
    private javax.swing.JButton btn52;
    private javax.swing.JButton btn53;
    private javax.swing.JButton btn54;
    private javax.swing.JButton btn55;
    private javax.swing.JButton btn56;
    private javax.swing.JButton btn57;
    private javax.swing.JButton btn58;
    private javax.swing.JButton btn59;
    private javax.swing.JButton btn60;
    private javax.swing.JButton btn600;
    private javax.swing.JButton btn61;
    private javax.swing.JButton btn62;
    private javax.swing.JButton btn63;
    private javax.swing.JButton btn64;
    private javax.swing.JButton btn65;
    private javax.swing.JButton btn66;
    private javax.swing.JButton btn67;
    private javax.swing.JButton btn68;
    private javax.swing.JButton btn69;
    private javax.swing.JButton btn70;
    private javax.swing.JButton btn700;
    private javax.swing.JButton btn71;
    private javax.swing.JButton btn72;
    private javax.swing.JButton btn73;
    private javax.swing.JButton btn74;
    private javax.swing.JButton btn75;
    private javax.swing.JButton btn76;
    private javax.swing.JButton btn77;
    private javax.swing.JButton btn78;
    private javax.swing.JButton btn79;
    private javax.swing.JButton btn80;
    private javax.swing.JButton btn800;
    private javax.swing.JButton btn81;
    private javax.swing.JButton btn82;
    private javax.swing.JButton btn83;
    private javax.swing.JButton btn84;
    private javax.swing.JButton btn85;
    private javax.swing.JButton btn86;
    private javax.swing.JButton btn87;
    private javax.swing.JButton btn88;
    private javax.swing.JButton btn89;
    private javax.swing.JButton btn90;
    private javax.swing.JButton btn900;
    private javax.swing.JButton btn91;
    private javax.swing.JButton btn92;
    private javax.swing.JButton btn93;
    private javax.swing.JButton btn94;
    private javax.swing.JButton btn95;
    private javax.swing.JButton btn96;
    private javax.swing.JButton btn97;
    private javax.swing.JButton btn98;
    private javax.swing.JButton btn99;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnPayChinese;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnRemoveAll;
    private javax.swing.JButton btnRemoveAllChinese;
    private javax.swing.JButton btnRemoveChinese;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveChinese;
    private javax.swing.JButton btnScanBarCode;
    private javax.swing.JButton btnStadistics;
    private javax.swing.JButton btnTotalPrintImpression;
    private javax.swing.JCheckBox cbChinese;
    private javax.swing.JCheckBox cbMorning;
    private javax.swing.JCheckBox cbNight;
    private javax.swing.JCheckBox cbSpanish;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBettingPeriod;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JLabel lblMorningClosingTime;
    private javax.swing.JLabel lblNightClosingTime;
    private javax.swing.JLabel lblNumberMoney;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalAmount;
    private javax.swing.JLabel lblTotalQuantityNumber;
    private javax.swing.JTextField priceAct;
    private javax.swing.JLabel tfMorningClosingTime;
    private javax.swing.JLabel tfNightClosingTime;
    private javax.swing.JLabel tfSelectedNumber;
    // End of variables declaration//GEN-END:variables
}
