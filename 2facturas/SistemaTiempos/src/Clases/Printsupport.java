/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Joha
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author All Open source developers
 * @version 1.0.0.0
 * @since 2014/12/22
 */

public class Printsupport {
    static String idTiketG;
    static String storeG;
    static String timeG;
    static String dateG;
    static String hourG;        
    static int actionG;
    //W:/SystemConfigFilesProvidedToToSistemaChinos/
    static ImageIcon printImage = new javax.swing.ImageIcon("W:/SystemConfigFilesProvidedToToSistemaChinos/codigoBarras.png");
    static ImageIcon printImage2 = new javax.swing.ImageIcon("W:/SystemConfigFilesProvidedToToSistemaChinos/firma.png");
    static String totalG;
    static JTable itemsTable;
    public static  int total_item_count=0;
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd  HH:mm:ss";
    public  static String title[] = new String[] {"",""};
	
    public static void setItems(Object[][] printitem){
        Object data[][]=printitem;
        DefaultTableModel model = new DefaultTableModel();       
        model.addColumn(title[0]);
        model.addColumn(title[1]);        
        int rowcount=printitem.length;        
        addtomodel(model, data, rowcount);       
        itemsTable = new JTable(model);
    }

    public static void addtomodel(DefaultTableModel model,Object [][]data,int rowcount){
        int count=0;
        while(count < rowcount){
            model.addRow(data[count]);
            count++;
        }
        if(model.getRowCount()!=rowcount)
            addtomodel(model, data, rowcount);    
    }


    public Object[][] getTableData (JTable table,String idTiket,String store, String time,String date, String hour,
        int action,String total) {
        int itemcount=table.getRowCount();
        idTiketG= idTiket;
        storeG= store;
        timeG= time;
        dateG= date;
        hourG= hour;        
        actionG= action;
        totalG= total;     
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol =dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        if(itemcount==nRow)                                        //check is there any data loss.
        {
            for (int i = 0 ; i < nRow ; i++){
                for (int j = 0 ; j < nCol ; j++){
                    tableData[i][j] = dtm.getValueAt(i,j);           //pass data into object array.
                }
            }
            if(tableData.length!=itemcount){                      //check for data losses in object array
                getTableData(table ,idTiket, store, time,date, hour,
                action, total);                                  //recursively call method back to collect data
            }       
        }
        else{                                                           
            getTableData(table ,idTiket, store, time,date, hour,
            action, total);
        }
        return tableData;                                       //return object array with data.
    }     

    public static PageFormat getPageFormat(PrinterJob pj){
        PageFormat pf = pj.defaultPage();
        Paper paper = new Paper();                
        double middleHeight =total_item_count*1.0;  //dynamic----->change with the row count of jtable
        double headerHeight = 5.0;                  //fixed----->but can be mod
        double footerHeight = 5.0;                  //fixed----->but can be mod
        double width = convert_CM_To_PPI(7);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight+total_item_count)*(total_item_count*total_item_count); 
        paper.setSize(9999999, 9999999);//height
        paper.setImageableArea(
                    convert_CM_To_PPI(0.25), 
                    convert_CM_To_PPI(0.50), 
                    width, 
                    height);   //define boarder size    after that print area width is about 180 points         
        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);    
        return pf;
    }
        
        
    protected static double convert_CM_To_PPI(double cm) {            
        return toPPI(cm * 0.393600787);            
    }

    protected static double toPPI(double inch) {            
        return inch * 72d;            
    }

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String hora = cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND);
        return dateG + "  " +hora;
    }

    public static class MyPrintable implements Printable {
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, 
            int pageIndex) throws PrinterException {    
            int result = NO_SUCH_PAGE;    
            if (pageIndex == 0) {                    
            Graphics2D g2d = (Graphics2D) graphics;                    
            double width = pageFormat.getImageableWidth();
            double height = pageFormat.getImageableHeight();    
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());                        
            try{	        
                int y=40;
                g2d.drawString(storeG, 40,y);                                  
                g2d.drawString("Tiempo: "+ timeG, 10, y+35);
                if(actionG!= -1){
                    g2d.drawString("Tiquete: "+ idTiketG, 10, y+20);
                    g2d.drawString("Fecha: "+ dateG+"  "+hourG, 10, y+50);
                }
                else{
                    g2d.drawString("Tablero: "+ idTiketG, 10, y+20);
                    g2d.drawString("Fecha: "+ now(), 10, y+50);
                }
                int cH = 0;
                TableModel mod = itemsTable.getModel();
                for(int i = 0;i < mod.getRowCount() ; i++){	                	
                    String itemid = mod.getValueAt(i, 0).toString();
                    String itemname = mod.getValueAt(i, 1).toString();                                	                	
                    cH = (y+70) + (12*i);          
                    if (itemid.length()==1) {
                        itemid = "0"+itemid;
                    }
                    g2d.drawString("Numero  "+itemid, 10, cH);
                    g2d.drawString("  "+itemname,80, cH);            	                              
                }	                                        
                g2d.drawString("Total:   "+ totalG,30, cH+17);
                
                if(actionG != -1){
                    g2d.drawString("** TIENE 7 DIAS PARA COBRAR **",10, cH+37);
                    g2d.drawString("** REVISE ANTES DE PAGAR **",10, cH+52);
                    g2d.drawString("** NO SE ACEPTAN RECLAMOS **",10, cH+67);
                    g2d.drawString("** SIN TIQUETE NO HAY PAGO **",10, cH+82);
                    
                }                                                                                
                if (pageIndex == 0) {
                    double pageWidth = pageFormat.getImageableWidth();
                    double pageHeight = pageFormat.getImageableHeight();
                    double imageWidth = printImage.getIconWidth();
                    double imageHeight = printImage.getIconHeight();
                    double scaleX = pageWidth / imageWidth;
                    double scaleY = pageHeight / imageHeight;
                    double scaleFactor = Math.min(scaleX, scaleY);
                    g2d.scale(scaleFactor, scaleFactor);                            
                    int hammer = (mod.getRowCount() / 5) * 800;
                    int rowsDown = 200 * mod.getRowCount();
                    float afterDot;
                    if (mod.getRowCount() % 5 == 1) {                                
                        //no hace nada
                    }
                    if (mod.getRowCount() % 5 == 2) {                                
                        rowsDown -= 200;
                    }
                    if (mod.getRowCount() % 5 == 3) {                                
                        rowsDown -= 330;
                    }
                    if (mod.getRowCount() % 5 == 4) {
                        rowsDown -= 530;                                
                    }
                    if (mod.getRowCount() % 5 == 0) {                                
                        rowsDown += 130;
                    }
                    g2d.drawImage(printImage2.getImage(), 10, 10, null);
                    if(actionG != -1){ 
                        if (mod.getRowCount() == 64) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 24) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 34) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 29) {
                            rowsDown = rowsDown + 70;
                        }
                        else if (mod.getRowCount() == 37) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 39) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 67) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 69) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 42) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 44) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 47) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 49) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 52) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 54) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 55) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 57) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 59) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 60) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 62) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 99) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() == 100) {
                            rowsDown = rowsDown + 90;
                        }
                        else if (mod.getRowCount() > 65 && mod.getRowCount() < 81) {
                            rowsDown = rowsDown + ((mod.getRowCount() - 65)*5);
                        }
                        else if (mod.getRowCount() >= 81 && mod.getRowCount() < 90) {
                            if (mod.getRowCount() >= 85 && mod.getRowCount() < 90) {
                                rowsDown = rowsDown + ((mod.getRowCount() - 65)*3);
                            }
                            else {
                                rowsDown = rowsDown + ((mod.getRowCount() - 65)*4);
                            }
                        }
                        else if (mod.getRowCount() >= 90 && mod.getRowCount() < 99) {
                            rowsDown = rowsDown + ((mod.getRowCount() - 65)) * 3;
                        }
                        g2d.drawImage(printImage.getImage(), 10, (cH+(602+rowsDown))-hammer, null);
                    }                            
                    else{ 
                        if(mod.getRowCount() > 50){
                            rowsDown-= 200;
                            g2d.drawImage(printImage2.getImage(), 10, (cH+(602+rowsDown))-hammer, null);
                        }                        
                    }                                                        
                    return Printable.PAGE_EXISTS;
                }
            }
            catch(Exception r){ 
                r.printStackTrace();
            }
            result = PAGE_EXISTS;    
        }    
        return result;    
      }
   }     
}