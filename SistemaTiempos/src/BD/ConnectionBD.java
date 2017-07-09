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
    private String msAccDB = "SISTEMA_NUMEROS.MDB";
    private String dbURL = "jdbc:ucanaccess://" + msAccDB;                 
    
    public void bdConnection(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
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
    
     public int gteBoardOfTicket(String ticket){
        int result= 0;
        bdConnection();        
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT tablero "
                    + "FROM NumerosVendidos where tiquete= "+"'"+ticket+"'");
            while(resultSet.next()) {
                result=resultSet.getInt(1);                 
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
    
    public String getTimeFromBoard(String idBoard){
        String result= "";
        bdConnection();        
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 tiempo FROM numerosTiempo where tablero= "
                    + "'"+idBoard+"'"+ " order by id desc");
            while(resultSet.next()) {
                result=resultSet.getString(1);                 
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
    
    public List<TimeNumber>getSoldBoardNumbersDependingOnTime(String idBoard, String time, int pricing){        
        bdConnection();
        List<TimeNumber>list= new ArrayList<>();        
        TimeNumber newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            String query="SELECT id,numero,tiempo,totalPlataNumero,tablero,totalPlata from NumerosTiempo where tablero= "+idBoard+" "
                    + "and tiempo= '"+time+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {        
                newElement= new TimeNumber(resultSet.getInt(1),resultSet.getInt(2),
                        resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6)); 
                if(newElement.getTotal() != newElement.getTotalNumberAmount()){
                    System.out.print(list);
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
            resultSet = statement.executeQuery("SELECT id,numero,tiquete,tablero,plataVendido FROM NumerosVendidos where tablero= "+idBoard);
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
            resultSet = statement.executeQuery("SELECT TOP 1 tablero,cierreDia,cierreNoche,comercio,porcentajeEstadistico,"
                    + "contrasena,fecha,precioNumeros FROM Tablero order by tablero desc");
            while(resultSet.next()) {
                newElement= new Board(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), 
                        resultSet.getString(7), resultSet.getInt(8)); 
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
            resultSet = statement.executeQuery("SELECT tablero,cierreDia,cierreNoche,comercio,porcentajeEstadistico,"
                    + "contrasena,fecha,precioNumeros FROM Tablero WHERE tablero= "+id);
            while(resultSet.next()) {
                newElement= new Board(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getString(6), 
                        resultSet.getString(7), resultSet.getInt(8));                
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
            resultSet = statement.executeQuery("SELECT id,numero,tiempo,totalPlataNumero,tablero,totalPlata FROM NumerosTiempo WHERE tablero= '"+board+
                    "' AND numero = '"+ number+"' AND tiempo = '" + time+"'");
            while(resultSet.next()) {
                newElement= new TimeNumber(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3), resultSet.getInt(4),
                        resultSet.getInt(5),resultSet.getInt(6));                
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
            resultSet = statement.executeQuery("SELECT tiquete,format(fechaTiquete,'yyyy-mm-dd'),totalPlata,hora FROM Tiquete "
            + "where fechaTiquete BETWEEN #"+startDate+"# AND #"+finalDate+"#");    
            while(resultSet.next()) {                      
                newElement= new Ticket(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),resultSet.getString(4));
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
            resultSet = statement.executeQuery("SELECT id,numero,tiquete,tablero,plataVendido FROM NumerosVendidos where tiquete= "+ticketN);                         
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
            resultSet = statement.executeQuery("SELECT TOP 1 tiquete,format(fechaTiquete,'yyyy-mm-dd'),totalPlata,hora FROM Tiquete order by tiquete desc");
            while(resultSet.next()) {                  
                newElement= new Ticket(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),resultSet.getString(4));
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
            resultSet = statement.executeQuery("SELECT tiquete,format(fechaTiquete,'yyyy-mm-dd'),totalPlata,hora FROM Tiquete WHERE tiquete="+id);
            while(resultSet.next()) {                
                newElement= new Ticket(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),resultSet.getString(4));                        
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
            resultSet = statement.executeQuery("SELECT tiquete,tiempo FROM TiempoTiquete WHERE tiquete="+ticket);
            while(resultSet.next()) {                    
                newElement= new TicketTime(resultSet.getInt(1),resultSet.getString(2));
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
            String companyName, int percentage, String password, String date, int pricing){                       
        bdConnection();
        try {            
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "UPDATE Tablero SET cierreDia = '"+morningClosingTime+"', cierreNoche = '"+nightClosingTime+
                    "', comercio = '"+companyName+"', porcentajeEstadistico = '"+percentage+"', contrasena = '" + password + "', fecha = '" + date + "', precioNumeros = '" + pricing + "' WHERE tablero = " + idBoard;                        
            statement.executeUpdate(sql); 
            Board board = getBoardInformation();
            for (int i = 0; i < 100; i++) {
                updateTimeNumberAdmin(board.getBoard(), "Dia", i, pricing);
                updateTimeNumberAdmin(board.getBoard(), "Noche", i, pricing);
            }
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
            String sql = "UPDATE NumerosTiempo SET totalPlataNumero= '" + money + "' WHERE tablero= '"+idBoard+
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
    
    
    public void updateTimeNumberAdmin(int idBoard, String time, int number, int money){ 
        bdConnection();
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            String sql = "UPDATE NumerosTiempo SET totalPlataNumero= '" + money +"', totalPlata= '"+money+"' WHERE tablero= '"+idBoard+
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
    
    public void createTicket(int ticketTotalMoney, String time, List<Integer>numbersList, List<Integer>numbersMoneyList, int board,String hour){                       
        bdConnection();
        try {                          
            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();                        
            Calendar cal=Calendar.getInstance(); 
            String currentDate =(cal.get(cal.MONTH)+1)+"/"+cal.get(cal.DATE)+"/"+cal.get(cal.YEAR);
            String sql = "INSERT INTO Tiquete(fechaTiquete, totalPlata,hora)"
                    + "values(#"+currentDate+"#,'"+ticketTotalMoney+"','"+hour+"')";
            statement.executeUpdate(sql);
            Ticket ticket = getTicketInformation();
            createTicketTime(ticket.getTicket(), time);
            for (int i = 0; i < numbersList.size(); i++) {
                TimeNumber timeNumber = getBoardNumberPricing(board, time, numbersList.get(i));
                createSoldNumber(numbersList.get(i), ticket.getTicket(), board, numbersMoneyList.get(i));
                int money = timeNumber.getTotalNumberAmount()- numbersMoneyList.get(i);
                updateTimeNumber(board, time, numbersList.get(i), money);
            }
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
    
    public void createTimeNumbers(int number, String time, int totalMoney, int board, int total){                       
        bdConnection();
        try {                          
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "INSERT INTO NumerosTiempo(numero, tiempo, totalPlataNumero, tablero, totalPlata)"
                    + "values('"+number+"','"+time+"','"+totalMoney+"','"+board+"','"+total+"')";   
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
    
    public void createBoard(String morningClosing, String nightClose, String companyName, int percentage,
            String password, String date, int numberPricing){                       
        bdConnection();
        try {                          
            connection = DriverManager.getConnection(dbURL);             
            statement = connection.createStatement();            
            String sql = "INSERT INTO Tablero(cierreDia, cierreNoche, comercio, porcentajeEstadistico, "
                    + "contrasena, fecha, precioNumeros)"
                    + "values('"+morningClosing+"','"+nightClose+"','"+companyName+"','"+percentage+"','"
                    +password+"','"+date+"','"+numberPricing+"')"; 
            statement.executeUpdate(sql);   
            Board board = getBoardInformation();
            TimeNumber timeNumber = null;
            for (int i = 0; i < 100; i++) {  
                timeNumber = getBoardNumberPricing(board.getBoard(), "Dia", i);
                if (timeNumber == null) {
                    createTimeNumbers(i, "Dia", numberPricing, board.getBoard(), numberPricing);
                    createTimeNumbers(i, "Noche", numberPricing, board.getBoard(), numberPricing);
                }
                else {
                    if (timeNumber.getTotal() != board.getNumbersPrincing()) {
                    createTimeNumbers(i, "Dia", numberPricing, board.getBoard(), timeNumber.getTotal());
                    createTimeNumbers(i, "Noche", numberPricing, board.getBoard(), timeNumber.getTotal());
                    }
                    else {
                        createTimeNumbers(i, "Dia", numberPricing, board.getBoard(), numberPricing);
                        createTimeNumbers(i, "Noche", numberPricing, board.getBoard(), numberPricing);
                    }
                }
            }
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
}