/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bryan
 */
public class TicketPrinter {
    public static void imprimirFactura(String idTicket,String store,String time,String date, String hour,
       int tipo,DefaultTableModel tableModel,String totalAmount) throws IOException{       
       PrinterMatrix printer = new PrinterMatrix();
       Extenso e = new Extenso();
       e.setNumber(101.85);       
       int cantidad= tableModel.getRowCount()/5;
       if(tipo== -1){
           printer.setOutSize(12+cantidad, 80);       
       }
       else{
           printer.setOutSize(8+cantidad, 80); 
       }
       
       String printOpt= "Tiquete #: " + idTicket+"\n"+
               "  Tiempo: " + time+"\n";                                         
       if(tipo == -1){
           printOpt= printOpt+"  Fecha: "+date+"\n";            
           printer.printTextWrap(1, 1, 2, 50,store+"\t\tFirma");
       }
       else{
           printOpt= printOpt+"  Fecha/Hora: "+date+"  "+hour+"\n"; 
           printer.printTextWrap(1, 1, 2, 50,store);
       }       
       printer.printTextWrap(2, 4, 2, 80, printOpt);              
       String soldNunInf="";
       int cont= 3;                     
       for(int i = 0; i < tableModel.getRowCount();){                                            
           for(int j= 0; j<5 ;j++){
               System.out.println("\n\n\n................PROBANDO.............................\n\n\n");
               if(i>=tableModel.getRowCount()){
                   break;
               }                  
               soldNunInf=soldNunInf+"Numero "+tableModel.getValueAt(i, 0)+"\t" +tableModel.getValueAt(i, 1)+"\n  ";
               i++;   
               System.out.println("--------++---------"+soldNunInf+"--------++---------");
           }              
           printer.printTextWrap(cont,cont, 2, 80, soldNunInf);
           cont++; 
           soldNunInf="";
       }       
       printer.printTextWrap(cont, cont, 2, 80, "Total:"+"   "+totalAmount);              
       if(tipo!= -1){
           String conditions=  "   "+"** TIENE 7 DIAS PARA COBRAR"+"\n"+
                 "      "+"** NO SE ACEPTAN RECLAMOS";              
           String cond2="   "+"** REVISE ANTES DE PAGAR"+"\n"+               
                      "      "+"** SIN TIQUETE NO HAY PAGO";  
           printer.printTextWrap(cont+1, cont+2, 3, 80, conditions);
           cont++;
           printer.printTextWrap(cont+1, cont+3, 3, 80, cond2);                  
       }
       if(tipo== -1){           
           String firma="\n    "+"Firma: "+"_______________________\n";
           cont++;
           printer.printTextWrap(cont+1, cont+1, 3, 80, firma);       
       }
       printer.toFile("impresion.txt");
       FileInputStream inputStream = null;       
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }                        
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        
        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();            
            try {
                printJob.print(document, attributeSet);
                if(tipo!= -1){
                    try{                    
                        int width = Math.round(MediaSize.ISO.A4.getX(MediaSize.MM));//nuevo
                        int height = Math.round(MediaSize.ISO.A4.getY(MediaSize.MM));//nuevo                      
                        attributeSet.add(new MediaPrintableArea((float)0.5, (float)0.5, width, height-270, MediaPrintableArea.MM));//nuevo
                        attributeSet.add(new Copies(1));                     
                        printJob = defaultPrintService.createPrintJob();
                        FileInputStream fileInputStream= new FileInputStream("C:/Users/Joha/Desktop/codBarras.png");//nuevo
                        Doc doc = new SimpleDoc(fileInputStream, DocFlavor.INPUT_STREAM.PNG, null);//nuevo                    
                        printJob.print(doc, attributeSet);                                        
                        fileInputStream.close();                    
                    }
                    catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }                                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }
        inputStream.close();                        
          
    } /*FUNCIONA BIEN EL COD DE BARRAS*/  
    
    
    /*
    public static void imprimirFactura(String idTicket,String store,String time,String date, String hour,
       int tipo,String barCode,DefaultTableModel tableModel,String totalAmount) throws IOException{       
       PrinterMatrix printer = new PrinterMatrix();
       Extenso e = new Extenso();
       e.setNumber(101.85);       
       int cantidad= tableModel.getRowCount()/5;
       if(tipo== -1){
           printer.setOutSize(12+cantidad, 80);       
       }
       else{
           printer.setOutSize(8+cantidad, 80); 
       }
       
       String printOpt= "Tiquete #: " + idTicket+"\n"+
               "  Tiempo: " + time+"\n"+
               "  Fecha/Hora: "+date+"  "+hour+"\n";                      
       if(tipo == -1){
           printer.printTextWrap(1, 1, 2, 50,store+"\t\tFirma");
       }
       else{
           printer.printTextWrap(1, 1, 2, 50,store);
       }       
       printer.printTextWrap(2, 4, 2, 80, printOpt);              
       String soldNunInf="";
       int cont= 3;                     
       for(int i = 0; i < tableModel.getRowCount();){                                            
           for(int j= 0; j<5 ;j++){                              
               if(i>=tableModel.getRowCount()){
                   break;
               }                  
               soldNunInf=soldNunInf+"Numero "+tableModel.getValueAt(i, 0)+"\t" +tableModel.getValueAt(i, 1)+"\n  ";
               i++;               
           }              
           printer.printTextWrap(cont,cont, 2, 80, soldNunInf);
           cont++; 
           soldNunInf="";
       }       
       printer.printTextWrap(cont, cont, 2, 80, "Total:"+"   "+totalAmount);              
       String conditions=  "   "+"** TIENE 7 DIAS PARA COBRAR"+"\n"+
                 "      "+"** NO SE ACEPTAN RECLAMOS";              
       String cond2="   "+"** REVISE ANTES DE PAGAR"+"\n"+               
                 "      "+"** SIN TIQUETE NO HAY PAGO";  
       printer.printTextWrap(cont+1, cont+2, 3, 80, conditions);
       cont++;
       printer.printTextWrap(cont+1, cont+3, 3, 80, cond2);                  
       if(tipo== -1){
           String firma="\n    "+"Firma: "+"_______________________\n";
           cont++;
           printer.printTextWrap(cont+1, cont+1, 3, 80, firma);       
       }
       printer.toFile("impresion.txt");
       FileInputStream inputStream = null;       
       try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }                
        
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        
        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();            
            try {
                printJob.print(document, attributeSet);
                if(tipo!= -1){
                    try{                    
                        int width = Math.round(MediaSize.ISO.A4.getX(MediaSize.MM));//nuevo
                        int height = Math.round(MediaSize.ISO.A4.getY(MediaSize.MM));//nuevo                      
                        attributeSet.add(new MediaPrintableArea(1, (float)0.005, width, height-270, MediaPrintableArea.MM));//nuevo
                        attributeSet.add(new Copies(1));                     
                        printJob = defaultPrintService.createPrintJob();
                        FileInputStream fileInputStream= new FileInputStream("C:/Users/Joha/Desktop/codBar1.png");//nuevo
                        Doc doc = new SimpleDoc(fileInputStream, DocFlavor.INPUT_STREAM.PNG, null);//nuevo                    
                        printJob.print(doc, attributeSet);                                        
                        fileInputStream.close();                    
                    }
                    catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }                                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }
        inputStream.close();                        
          
    } /*FUNCIONA BIEN EL COD DE BARRAS*/  
    
}
