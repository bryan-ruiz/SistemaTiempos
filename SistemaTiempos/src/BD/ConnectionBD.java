/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;


import Clases.Board;
import Clases.SoldNumbers;
import Clases.Ticket;
import Clases.TicketTime;
import Clases.TimeNumber;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
/**
 *
 * @author Joha
 */
public class ConnectionBD {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;        
    private int defectMoney= 20000;
    private String msAccDB = "C:/Users/Bryan/Documents/GitHub/SistemaTiempos/SISTEMA_NUMEROS.MDB";
    private String dbURL = "jdbc:ucanaccess://" + msAccDB;                 
    
    public void bdConnection(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
 
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
    }
    
    public void closeConnectionGet(){        
        try {
            if(null != connection) {                    
                resultSet.close();
                statement.close();                    
                connection.close();                    
            }
        }
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }        
    }

/////////////////////////////////////////////////////////////////////////////////////////
//                          TABLERO
/////////////////////////////////////////////////////////////////////////////////////////
    public String getTimeFromBoard(String idBoard){
        String result= "";
        bdConnection();        
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 * FROM numerosTiempo where tablero= "
                    + "'"+idBoard+"'"+ " order by id desc");
            while(resultSet.next()) {
                result=resultSet.getString(3);                 
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }                
        return result;
    }
    
    
    public boolean isInList(List<SoldNumbers>list, SoldNumbers object){
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getNumber()== object.getNumber()){
                int money= list.get(i).getMoneySold()+ object.getMoneySold();
                list.get(i).setMoneySold(money);                
                return true;
            }
        }
        return false;
    }
    /**
     * FALTA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * @param idBoard
     * @param time
     * @return 
     */
    public List<TimeNumber>getSoldBoardNumbersDependingOnTime(String idBoard, String time){        
        bdConnection();
        System.out.println("Entro a funcion de baase");
        List<TimeNumber>list= new ArrayList<>();        
        TimeNumber newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            String query="SELECT * from NumerosTiempo where tablero= "+idBoard+" "
                    + "and tiempo= '"+time+"'";
            System.out.println(query);
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {                                                                      
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getString(5));
                newElement= new TimeNumber(resultSet.getInt(1),resultSet.getInt(2),
                        resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5));   
                if(newElement.getTotalNumberAmount() != defectMoney){
                    list.add(newElement);
                }                
            }                        
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return list;
    }    
    
    public List<SoldNumbers>getSoldBoardNumbers(String idBoard){        
        bdConnection();
        List<SoldNumbers>list= new ArrayList<>();        
        SoldNumbers newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM NumerosVendidos where tablero= "+idBoard);
            while(resultSet.next()) {                                                      
                newElement= new SoldNumbers(resultSet.getInt(1),resultSet.getInt(2),
                        resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5));
                if(isInList(list,newElement)== false){
                    list.add(newElement);
                }                         
            }                        
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return list;
    }                    
        
    public Board getBoardInformation(){        
        bdConnection();
        Board newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 * FROM Tablero order by tablero desc");
            while(resultSet.next()) {
                newElement= new Board(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5),resultSet.getInt(6),
                        resultSet.getString(7));                
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return newElement;
    }

    public Board getBoardInformationFind(int id){        
        bdConnection();
        Board newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM Tablero WHERE tablero= "+id);
            while(resultSet.next()) {
                newElement= new Board(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5),resultSet.getInt(6),
                        resultSet.getString(7));                
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return newElement;
    }
    
    public TimeNumber getBoardNumberPricing(int board, String time, int number){        
        bdConnection();
        TimeNumber newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM NumerosTiempo WHERE tablero= '"+board+
                    "' AND numero = '"+ number+"' AND tiempo = '" + time+"'");
            while(resultSet.next()) {
                newElement= new TimeNumber(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3), resultSet.getInt(4),
                        resultSet.getInt(5));                
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return newElement;
    }
    
    
/////////////////////////////////////////////////////////////////////////////////////////
//                          TIKETES    
/////////////////////////////////////////////////////////////////////////////////////////
    
    
    public List<Ticket>GetAllTiicketsBetweenDates(String startDate, String finalDate){        
        bdConnection();
        List<Ticket>list= new ArrayList<>();
        Ticket newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();                        
            resultSet = statement.executeQuery("SELECT tiquete,format(fechaTiquete,'dd/mm/yyyy'),totalPlata FROM Tiquete "
            + "where fechaTiquete BETWEEN #"+startDate+"# AND #"+finalDate+"#");                        
            System.out.println("imprimiendo resultados de base"); 
            while(resultSet.next()) {                      
                newElement= new Ticket(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));                
                System.out.println(resultSet.getString(3));
                list.add(newElement);
            } 
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return list;
    }    
    
    public List<SoldNumbers>GetNumberSoldFromTiicket(String ticketN){        
        bdConnection();
        List<SoldNumbers>list= new ArrayList<>();
        SoldNumbers newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM NumerosVendidos where tiquete= "+ticketN);                         
            while(resultSet.next()) {                       
                newElement= new SoldNumbers(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5));
                if(isInList(list,newElement)== false){
                    list.add(newElement);
                } 
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return list;
    }                
    
    
    public Ticket getTicketInformation(){        
        bdConnection();
        Ticket newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 tiquete,format(fechaTiquete,'dd/mm/yyyy'),totalPlata FROM Tiquete order by tiquete desc");
            while(resultSet.next()) {                  
                newElement= new Ticket(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return newElement;
    }
    
    public Ticket getTicketInformationFind(int id){        
        bdConnection();
        Ticket newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT tiquete,format(fechaTiquete,'dd/mm/yyyy'),totalPlata FROM Tiquete WHERE tiquete="+id);
            while(resultSet.next()) {                
                newElement= new Ticket(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));                        
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return newElement;
    }
    
    public TicketTime getTicketTime(String ticket){        
        bdConnection();
        TicketTime newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM TiempoTiquete WHERE tiquete="+ticket);
            while(resultSet.next()) {                
                newElement= new TicketTime(resultSet.getInt(2),resultSet.getString(1));
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            closeConnectionGet();    
        }        
        return newElement;
    }


/////////////////////////////////////////////////////////////////////////////////////////
//                          actualizar  
/////////////////////////////////////////////////////////////////////////////////////////
    
    
    public void updateBoard(int idBoard, String morningClosingTime, String nightClosingTime, 
            String companyName, int percentage, int barCode, String password){                       
        bdConnection();
        try {            
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "UPDATE Tablero SET cierreDia = '"+morningClosingTime+"', cierreNoche = '"+nightClosingTime+
                    "', comercio = '"+companyName+"', porcentajeEstadistico = '"+percentage+"', codigoBarra = '" + barCode+
                    "', contrasena = '" + password + "' WHERE tablero = " + idBoard;                        
            statement.executeUpdate(sql);            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            try {
                if(null != connection) {                                        
                    statement.close();                    
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }             
    }   
    
    
    public void updateTimeNumber(int idBoard, String time, int number, int money){                       
        bdConnection();
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            String sql = "UPDATE NumerosTiempo SET totalPlataNumero = '" + money + "' WHERE tablero= '"+idBoard+
                    "' AND numero = '"+ number+"' AND tiempo = '" + time+"'";  
            statement.executeUpdate(sql);   
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            try {
                if(null != connection) {                                        
                    statement.close();                    
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }            
    } 
    
/////////////////////////////////////////////////////////////////////////////////////////
//                          eliminar  
/////////////////////////////////////////////////////////////////////////////////////////
    
    public void deleteTicket(int id){                       
        bdConnection();
        try {            
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "DELETE FROM Tiquete where tiquete= "+id;                        
            statement.executeUpdate(sql);            
            sql = "DELETE FROM NumerosVendidos where tiquete= "+id;                        
            statement.executeUpdate(sql);            
            sql = "DELETE FROM TiempoTiquete where tiquete= "+id;                        
            statement.executeUpdate(sql);           
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            try {
                if(null != connection) {                                        
                    statement.close();                    
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }             
    }   

    /*public void createDate(String date) {
        bdConnection();
        try {            
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "INSERT INTO Fecha(fecha)"
                    + "values('"+date+"')";            
            statement.executeUpdate(sql);  
            System.out.println("date is added");           
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            try {
                if(null != connection) {                                        
                    statement.close();                    
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }*/
/////////////////////////////////////////////////////////////////////////////////////////
//                          insertar  
/////////////////////////////////////////////////////////////////////////////////////////
    public void createSoldNumber(int number, int ticket,int board, int money){                       
        bdConnection();
        try {  
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "INSERT INTO NumerosVendidos(numero, tiquete, tablero, plataVendido)" 
                        + "values('"+number+"','"+ticket+"','"+board+"','"+money+"')";            
            statement.executeUpdate(sql);  
            System.out.println("number is added");
            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            try {
                if(null != connection) {                                        
                    statement.close();                    
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }             
    }
    
    public void createTicketTime(int ticket, String time){                       
        bdConnection();
        try {  
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "INSERT INTO TiempoTiquete(tiquete, tiempo)"
                    + "values('"+ticket+"','"+time+"')";            
            statement.executeUpdate(sql);  
            System.out.println("ticketTime is added");
            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            try {
                if(null != connection) {                                        
                    statement.close();                    
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }             
    }
    
    public void createTicket(int ticketTotalMoney, String time, List<Integer>numbersList, List<Integer>numbersMoneyList, int board){                       
        bdConnection();
        try {                          
            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();                        
            Calendar cal=Calendar.getInstance(); 
            String currentDate =(cal.get(cal.MONTH)+1)+"/"+cal.get(cal.DATE)+"/"+cal.get(cal.YEAR);
            System.out.println(currentDate);
            String sql = "INSERT INTO Tiquete(fechaTiquete, totalPlata)"
                    + "values(#"+currentDate+"#,'"+ticketTotalMoney+"')";
            statement.executeUpdate(sql);
            System.out.println("ticket is added");
            Ticket ticket = getTicketInformation();
            createTicketTime(ticket.getTicket(), time);
            System.out.println("ticket is added FOR NOW");
            System.out.println(numbersList.size());
            for (int i = 0; i < numbersList.size(); i++) {
                System.out.println("FOR DB");
                TimeNumber timeNumber = getBoardNumberPricing(board, time, numbersList.get(i));
                System.out.println("1!");
                createSoldNumber(numbersList.get(i), ticket.getTicket(), board, numbersMoneyList.get(i));
                System.out.println("2!");
                int money = timeNumber.getTotalNumberAmount() - numbersMoneyList.get(i);
                System.out.println("3!");
                updateTimeNumber(board, time, numbersList.get(i), money);
                System.out.println("4!");
            }
            System.out.println("DONE!");
            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            try {
                if(null != connection) {                                        
                    statement.close();                    
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }             
    }
    
    /*public void createSoldNumbers(int number, int ticket, int board, int price){                       
        bdConnection();
        try {  
            //createDate(date);
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "INSERT INTO Tiquete(fechaTiquete, totalPlata)"
                    + "values('"+date+"','"+ticketTotalMoney+"')";            
            statement.executeUpdate(sql);  
            System.out.println("ticket is added");
            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            try {
                if(null != connection) {                                        
                    statement.close();                    
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }             
    }  
    
    public void baseEscribirPersonal(String nombre,String apellido, String cargo, String sueldo){                       
        bdConnection();
        try {            
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "INSERT INTO Personal(nom_per,ape_per,carg_per,suel_per)"
                    + "values('"+nombre+"','"+apellido+"','"+cargo
            +"','"+sueldo+"')";            
           statement.executeUpdate(sql);             
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            try {
                if(null != connection) {                                        
                    statement.close();                    
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }             
    }       */
}