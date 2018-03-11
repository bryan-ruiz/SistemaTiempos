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
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultListModel;
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
    private DefaultTableModel tableModel, tab1, tab2;
    private String[] headers = {"Numeros","Plata"};
    private Ticket ticket;
    private List<SoldNumbers>list;
    private String hour, mensageAddData,menssageNotFoundTicket,menssageOk,menssageCantBuyNumber;
    private Selling currentSelling;
    private String store, lblTicket, btnSearch, lblId, lblTotal, lblTime, lblDate, btnPrint, btnDelete,dayHour,
            messageDay, messageNight, buy, doPurachase, messageToShow, timeOut, printTicket;
    private String dayDate= "";
    public ticketCodScanDelete() {
        initComponents();
        visibleFalseComponents(false); 
        jPanel1.setVisible(false);
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage("W:/SystemConfigFilesProvidedToToSistemaChinos/icono.png");
        return retValue;
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
        butonBuy.setText(buy);
        radButtonDay.setText(messageDay);
        radButtonNigth.setText(messageNight);
        buyTicketNowButton.setText(doPurachase);
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
        mensageAddData= "Se debe de ingresar un dato";
        menssageNotFoundTicket="El tiquete no es válido";
        menssageOk= "El tiquete ha sido eliminado";
        menssageCantBuyNumber= "Error no se puede comprar numero";
        
        messageDay = spanishLanguage.getLblDay();
        messageNight = spanishLanguage.getLblNightString();
        buy = "Comprar";
        doPurachase = "Realizar compra";
        messageToShow = spanishLanguage.getLblPeriodString();
        timeOut = "Fuera de tiempo";
        printTicket = spanishLanguage.getBtnPrint();
        setWindowToSelectedLanguage();
    }
    
    public void setLanguageToChinese() {
        
        timeOut = "推出时间";
        ChineseLanguage chineseLanguage = ChineseLanguage.getInstance();
        printTicket = chineseLanguage.getBtnPrint();
        lblTicket = chineseLanguage.getLblTicket();
        btnSearch = chineseLanguage.getBtnSearch();
        lblId = chineseLanguage.getLblTicket();
        lblTotal = chineseLanguage.getLblTotalString();
        lblTime = chineseLanguage.getLblTime();
        lblDate = chineseLanguage.getLblDate();
        btnPrint = chineseLanguage.getBtnPrint();
        btnDelete = chineseLanguage.getBtnDelete();
        mensageAddData= "购买现在号码";
        menssageNotFoundTicket="搜索错误";
        menssageOk= "删除成功";
        buy = "重新购买";
        doPurachase = "确定购买";
        menssageCantBuyNumber= "你可以不买";
        messageDay = chineseLanguage.getLblDay();
        messageNight = chineseLanguage.getLblNightString();
        messageToShow = chineseLanguage.getLblPeriodString();
        setWindowToSelectedLanguage();
    }
    
    public void setSelling(Selling selling) {
        currentSelling = selling;
    }
    
    private void removeAllItemsFromList() {
        
        tableModel = new DefaultTableModel(null, headers){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        
        jTable2.setModel(tableModel);
    }
    
    public void setAll(){
        mensaje.setForeground(Color.red);
        dateTxt.setText("");                
        ticketIdTxt.setText("");
        timeTxt.setText("");
        totalTxt.setText("");        
        mensaje.setText("");
        jPanel1.setVisible(false);
        numbersList= new DefaultListModel();
        moneyList= new DefaultListModel();
        removeAllItemsFromList();
    }
    
    public void visibleFalseComponents(boolean bool){
        labelDate.setVisible(bool);
        butonBuy.setVisible(bool);
        findTicketButton.setVisible(true);
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
            mensaje.setText(mensageAddData);
            return;            
        }
        idToFindTicket= Integer.parseInt(idTicket);                       
        ConnectionBD con= new ConnectionBD();                                
        ticket= con.getTicketInformationFind(idToFindTicket);        
        if(ticket== null){            
            mensaje.setText(menssageNotFoundTicket);
            return;
        }                
        list=con.GetNumberSoldFromTiicket(idTicket); 
        tab1 = new DefaultTableModel(null, headers){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        tab2 = new DefaultTableModel(null, headers){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        for (int i = 0; i < list.size(); i++) {
            String[] row = new String[2];
            String numberToConvert = String.valueOf(list.get(i).getNumber());
            if (numberToConvert.length() == 1) {
                numberToConvert = "0"+numberToConvert;
            }
            row[0] = numberToConvert;         
            String showIt = String.valueOf((list.get(i).getMoneySold()));
            row[1] = showIt;
            tableModel.addRow(row); 
            if (i < 51) {
                System.out.println("Tab1 -------  " + row[0] + row[1]);
                tab1.addRow(row);
            }
            else { 
                System.out.println("Tab2 -------  " + row);
                tab2.addRow(row);
            }
        
        }
        if(list.size()>0){
            int idBoard= list.get(0).getBoard();
            Board board= con.getBoardInformationFind(idBoard);
            store=board.getStore();
        }        
        totalTxt.setText(String.valueOf(ticket.getTicketTotalAmount())); 
        TicketTime tiempo= con.getTicketTime(idTicket);
        timeTxt.setText(tiempo.getTime());      
        
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
        
        dateTxt.setText(ticket.getDate()+"  "+finaHour); 
        dayDate= ticket.getDate();
        ticketIdTxt.setText(idTicket);  
        hour= finaHour;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jTable2 = new javax.swing.JTable();
        butonBuy = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        radButtonDay = new javax.swing.JRadioButton();
        radButtonNigth = new javax.swing.JRadioButton();
        buyTicketNowButton = new javax.swing.JButton();
        mensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

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

        jTable2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(deleteButton))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        butonBuy.setText("Comprar");
        butonBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonBuyActionPerformed(evt);
            }
        });

        buttonGroup1.add(radButtonDay);
        radButtonDay.setText("Dia");

        buttonGroup1.add(radButtonNigth);
        radButtonNigth.setText("Noche");

        buyTicketNowButton.setText("Realizar Compra");
        buyTicketNowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyTicketNowButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radButtonDay)
                    .addComponent(radButtonNigth)
                    .addComponent(buyTicketNowButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(radButtonDay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radButtonNigth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(buyTicketNowButton)
                .addContainerGap())
        );

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelTime)
                                    .addComponent(labelTotal)
                                    .addComponent(labelIdTicket)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(labelDate))
                                    .addComponent(printButton))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(timeTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                .addComponent(ticketIdTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(totalTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dateTxt, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(butonBuy)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mensaje)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(printButton)
                        .addComponent(butonBuy))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findTicketButton)
                    .addComponent(idFindTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTicket))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mensaje)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
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
        int board= con.gteBoardOfTicket(ticketIdTxt.getText());
        TicketTime ticketTime = con.getTicketTime(String.valueOf(ticket.getTicket()));
        for (int i = 0; i < list.size(); i++) {
            TimeNumber timeNumber = con.getBoardNumberPricing(board,ticketTime.getTime(), list.get(i).getNumber());
            int total = timeNumber.getTotalNumberAmount() + list.get(i).getMoneySold();
            con.updateTimeNumber(board, ticketTime.getTime(), list.get(i).getNumber(), total);
        }
        con.deleteTicket(idToFindTicket);
        setAll();
        visibleFalseComponents(false);
        mensaje.setText(menssageOk);
        mensaje.setForeground(Color.blue);
        currentSelling.removeAllItemsFromList();
        currentSelling.soldNumbersOfTableSetColors();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here: 
        tab1 = new DefaultTableModel(null, headers){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        tab2 = new DefaultTableModel(null, headers){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        Printsupport ps=new Printsupport();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String[] row = new String[2];
            row[0] = (String) tableModel.getValueAt(i, 0);    
            row[1] = (String) tableModel.getValueAt(i, 1);    
            if (i < 51) {
                System.out.println("Tab1 -------  " + row[0] + row[1]);
                tab1.addRow(row);
            }
            else { 
                System.out.println("Tab2 -------  " + row);
                tab2.addRow(row);
            }
        }
        if(isInBuy == false){
            jTable2.setModel(tab1);
            Object printitem [][]=ps.getTableData(jTable2,ticketIdTxt.getText(),store,timeTxt.getText(),dayDate, hour,
                0, totalTxt.getText());
            ps.setItems(printitem);
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
            try {
                pj.print();
                //time= "";
                //isInBuy= false;
            }
            catch (PrinterException ex) {
                ex.printStackTrace();
            }
            if (tab2.getRowCount() > 0) {
                jTable2.setModel(tab2);
                printitem =ps.getTableData(jTable2,ticketIdTxt.getText(),store,timeTxt.getText(),dayDate, hour,
                0, totalTxt.getText());
                ps.setItems(printitem);
                pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
                try {
                    pj.print();
                    //time= "";
                    //isInBuy= false;
                }
                catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
            time= "";
            isInBuy= false;
        }
        else{
            ConnectionBD con= new ConnectionBD();
            Ticket ticket= con.getTicketInformation();
            int board= con.gteBoardOfTicket(String.valueOf(ticket.getTicket()));
            TicketTime ticketTime = con.getTicketTime(String.valueOf(ticket.getTicket()));    
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
            jTable2.setModel(tab1);
            Object printitem [][]=ps.getTableData(jTable2,String.valueOf(ticket.getTicket()),store,time,ticket.getDate(), finaHour,
                0, String.valueOf(ticket.getTicketTotalAmount()));
            ps.setItems(printitem);
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
            try {
                pj.print();
                //time= "";
                //isInBuy= false;
            }
            catch (PrinterException ex) {
                ex.printStackTrace();
            }
            if (tab2.getRowCount() > 0) {
                jTable2.setModel(tab2);
                printitem =ps.getTableData(jTable2,String.valueOf(ticket.getTicket()),store,time,ticket.getDate(), finaHour,
                0, String.valueOf(ticket.getTicketTotalAmount()));
                ps.setItems(printitem);
                pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
                try {
                    pj.print();
                    //time= "";
                    //isInBuy= false;
                }
                catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
            time= "";
            isInBuy= false;
        }
        visibleFalseComponents(true);
    }//GEN-LAST:event_printButtonActionPerformed

    private List<Integer>moneyTable,numberTable;
    private int hourTicket, minuts, seconds, pricing;
    private void butonBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonBuyActionPerformed
        // TODO add your handling code here:
        jPanel1.setVisible(true);
    }//GEN-LAST:event_butonBuyActionPerformed

    
    private boolean verifyHour(int currentHour, int appHour,int currentMinute,int appMinute){
        boolean error;
        if (currentHour < appHour) {
            error = false;
        }
        else if (currentHour == appHour) {
            if (currentMinute < appMinute) {
                error = false;
            }
            else {
                mensaje.setText(timeOut);
                error = true;
            }
        }
        else {
            mensaje.setText(timeOut);
            error = true;
        }
        return error;
    }
    
    private void printOnceTicketIsPaid() {
        tab1 = new DefaultTableModel(null, headers){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        tab2 = new DefaultTableModel(null, headers){
            @Override
            public boolean isCellEditable (int fila, int columna) {
                return false;
            }
        };
        Printsupport ps=new Printsupport();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String[] row = new String[2];
            row[0] = (String) tableModel.getValueAt(i, 0);    
            row[1] = (String) tableModel.getValueAt(i, 1);    
            if (i < 51) {
                System.out.println("Tab1 -------  " + row[0] + row[1]);
                tab1.addRow(row);
            }
            else { 
                System.out.println("Tab2 -------  " + row);
                tab2.addRow(row);
            }
        }
        if(isInBuy == false){
            jTable2.setModel(tab1);
            Object printitem [][]=ps.getTableData(jTable2,ticketIdTxt.getText(),store,timeTxt.getText(),dayDate, hour,
                0, totalTxt.getText());
            ps.setItems(printitem);
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
            try {
                pj.print();
                //time= "";
                //isInBuy= false;
            }
            catch (PrinterException ex) {
                ex.printStackTrace();
            }
            if (tab2.getRowCount() > 0) {
                jTable2.setModel(tab2);
                printitem =ps.getTableData(jTable2,ticketIdTxt.getText(),store,timeTxt.getText(),dayDate, hour,
                0, totalTxt.getText());
                ps.setItems(printitem);
                pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
                try {
                    pj.print();
                    //time= "";
                    //isInBuy= false;
                }
                catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
            time= "";
            isInBuy= false;
        }
        else{
            ConnectionBD con= new ConnectionBD();
            Ticket ticket= con.getTicketInformation();
            int board= con.gteBoardOfTicket(String.valueOf(ticket.getTicket()));
            TicketTime ticketTime = con.getTicketTime(String.valueOf(ticket.getTicket()));    
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
            jTable2.setModel(tab1);
            Object printitem [][]=ps.getTableData(jTable2,String.valueOf(ticket.getTicket()),store,time,ticket.getDate(), finaHour,
                0, String.valueOf(ticket.getTicketTotalAmount()));
            ps.setItems(printitem);
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
            try {
                pj.print();
                //time= "";
                //isInBuy= false;
            }
            catch (PrinterException ex) {
                ex.printStackTrace();
            }
            if (tab2.getRowCount() > 0) {
                jTable2.setModel(tab2);
                printitem =ps.getTableData(jTable2,String.valueOf(ticket.getTicket()),store,time,ticket.getDate(), finaHour,
                0, String.valueOf(ticket.getTicketTotalAmount()));
                ps.setItems(printitem);
                pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new Printsupport.MyPrintable(),ps.getPageFormat(pj));
                try {
                    pj.print();
                    //time= "";
                    //isInBuy= false;
                }
                catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
            time= "";
            isInBuy= false;
        }
        
        visibleFalseComponents(true);
    }
    
    boolean isInBuy= false;
    String time= "";
    private void buyTicketNowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyTicketNowButtonActionPerformed
        // TODO add your handling code here:
        ConnectionBD con= new ConnectionBD(); 
        Board board= con.getBoardInformation();
        time= "";
        
        Calendar cal = Calendar.getInstance(); 
        String hour = String.valueOf(cal.get(cal.HOUR_OF_DAY));
        String minute = String.valueOf(cal.get(cal.MINUTE));
        String hora = cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE); 
        boolean isAtTime;
        
        if(radButtonDay.isSelected()){
            String morningSplitHour[] = board.getDayClose().split(":");
            int appHour = Integer.parseInt(morningSplitHour[0]);
            int currentHour = Integer.parseInt(hour);
            int appMinute = Integer.parseInt(morningSplitHour[1]);
            int currentMinute = Integer.parseInt(minute);
            time= "Dia";            
            isAtTime=verifyHour(currentHour, appHour,currentMinute,appMinute);
            System.out.println(isAtTime);
        }
        else if(radButtonNigth.isSelected()){
            String morningSplitHour[] = board.getNightClose().split(":");
            int appHour = Integer.parseInt(morningSplitHour[0]);
            int currentHour = Integer.parseInt(hour);
            int appMinute = Integer.parseInt(morningSplitHour[1]);
            int currentMinute = Integer.parseInt(minute);
            time= "Noche";            
            isAtTime=verifyHour(currentHour, appHour,currentMinute,appMinute);
            System.out.println(isAtTime);
        }
        else{
            mensaje.setForeground(Color.red);
            mensaje.setText(messageToShow);
            return;
        }
        
        if(isAtTime != false){
            return;
        }
        
        int moneyForThisRow;
        int numberForThisRow;
        TimeNumber number;
        moneyTable = new ArrayList<>();
        numberTable = new ArrayList<>();
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            moneyForThisRow= Integer.parseInt((String) tableModel.getValueAt(i, 1));            
            numberForThisRow = Integer.parseInt((String) tableModel.getValueAt(i, 0));            
            number= con.getBoardNumberPricing(board.getBoard(), time, numberForThisRow);            
            if(number.getTotalNumberAmount() >= moneyForThisRow){
                moneyTable.add(moneyForThisRow);
                numberTable.add(numberForThisRow);                
            }
            else{
                mensaje.setForeground(Color.red);
                mensaje.setText(menssageCantBuyNumber + "  " + numberForThisRow);
                return;
            }
        }         
        String currentDate =cal.get(cal.DATE)+"/"+(cal.get(cal.MONTH)+1)+"/"+cal.get(cal.YEAR);
        int totalValue = Integer.parseInt(totalTxt.getText());
        Calendar calendario = new GregorianCalendar();
        hourTicket =calendario.get(Calendar.HOUR_OF_DAY);
        minuts = calendario.get(Calendar.MINUTE);
        seconds = calendario.get(Calendar.SECOND);
        dayHour= hour+": "+minuts+": "+seconds; 
        con.createTicket(totalValue, time, numberTable, moneyTable, board.getBoard(),dayHour);
        currentSelling.removeAllItemsFromList();
        currentSelling.soldNumbersOfTableSetColors();
        jPanel1.setVisible(false);
        butonBuy.setVisible(false);
        deleteButton.setVisible(false);
        findTicketButton.setVisible(false);
        mensaje.setForeground(Color.blue);
        mensaje.setText(printTicket);        
        isInBuy= true;
        printOnceTicketIsPaid();
    }//GEN-LAST:event_buyTicketNowButtonActionPerformed

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
    private javax.swing.JButton butonBuy;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buyTicketNowButton;
    private javax.swing.JTextField dateTxt;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton findTicketButton;
    private javax.swing.JTextField idFindTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelIdTicket;
    private javax.swing.JLabel labelTicket;
    private javax.swing.JLabel labelTime;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel mensaje;
    private javax.swing.JPanel panel;
    private javax.swing.JButton printButton;
    private javax.swing.JRadioButton radButtonDay;
    private javax.swing.JRadioButton radButtonNigth;
    private javax.swing.JTextField ticketIdTxt;
    private javax.swing.JTextField timeTxt;
    private javax.swing.JTextField totalTxt;
    // End of variables declaration//GEN-END:variables
}
