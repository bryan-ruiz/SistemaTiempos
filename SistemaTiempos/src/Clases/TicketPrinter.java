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
       int tipo,String barCode,DefaultTableModel tableModel,String totalAmount) throws IOException{
       PrinterMatrix printer = new PrinterMatrix();
       Extenso e = new Extenso();
       e.setNumber(101.85);       
       int cantidad= tableModel.getRowCount()/8;
       printer.setOutSize(9+cantidad, 80);       
       String printOpt= "   Tiquete #: " + idTicket+"\n"+
               "    Tiempo: " + time+"\n"+
               "    Fecha/Hora: "+date+"  "+hour+"\n";                      
       printer.printTextWrap(1, 1, 1, 50, "    "+store);
       printer.printTextWrap(2, 4, 1, 80, printOpt);       
       printer.printTextWrap(3, 4, 1, 80, "    Numero\t\t\tPlata\n");             
       int cont= 4;       
       String soldNunInf="      ";
       int conta= 0;       
       for (int i = 0; i < tableModel.getRowCount(); i++){           
           soldNunInf=soldNunInf+tableModel.getValueAt(i, 0)+"\t\t\t" +tableModel.getValueAt(i, 1)+"\n";
           conta++;           
       }       
       cont= cont+(conta/8)+1;
       printer.printTextWrap(4,cont , 3, 80, soldNunInf);
       printer.printTextWrap(cont, cont, 1, 80, "    "+"Total:");
       printer.printTextWrap(cont, cont, 30, 80, " "+totalAmount);
       
       String conditions=  "   "+"** TIENE 7 DIAS PARA COBRAR"+"\n"+
               "      "+"** NO SE ACEPTAN RECLAMOS";              
       String cond2="   "+"** REVISE ANTES DE PAGAR"+"\n"+               
               "      "+"** SIN TIQUETE NO HAY PAGO"+"\n\n\n";                             
       String firma="    "+"Firma: "+"_______________________\n";                           
       printer.printTextWrap(cont+1, cont+2, 3, 80, conditions);
       cont++;
       printer.printTextWrap(cont+1, cont+3, 3, 80, cond2);
       cont++;
       printer.printTextWrap(cont+1, cont+1, 3, 80, firma);
       
       
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
                try{
                    
                    int width = Math.round(MediaSize.ISO.A4.getX(MediaSize.MM));//nuevo
                    int height = Math.round(MediaSize.ISO.A4.getY(MediaSize.MM));//nuevo                      
                    attributeSet.add(new MediaPrintableArea(1, (float)0.01, width-60, height-255, MediaPrintableArea.MM));//nuevo                
                    attributeSet.add(new Copies(1));                     
                    printJob = defaultPrintService.createPrintJob();
                    FileInputStream fileInputStream= new FileInputStream("C:/Users/Joha/Desktop/codigoDeBarras.png");//nuevo
                    Doc doc = new SimpleDoc(fileInputStream, DocFlavor.INPUT_STREAM.PNG, null);//nuevo                    
                    printJob.print(doc, attributeSet);                                        
                    fileInputStream.close();                    
                }
                catch(Exception ex) {
                    ex.printStackTrace();
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
