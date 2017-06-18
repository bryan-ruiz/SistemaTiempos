/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joha
 */
public class ConnectionBD {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;        
    private String msAccDB = "C:/Users/Joha/Documents/GitHub/SistemaTiempos/SISTEMA_NUMEROS.MDB";
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
//                          TERMINOS Y CONDICIONES
/////////////////////////////////////////////////////////////////////////////////////////    
    public String getTermsConditions(){        
        bdConnection();
        String newElement= "";
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 * FROM CondicionesTerminos order by id desc");
            while(resultSet.next()) {                
                newElement= resultSet.getString(2);                
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
//                          TABLERO
/////////////////////////////////////////////////////////////////////////////////////////
    
    public List<BoardSoldNumbers>getSoldBoardNumbers(String idBoard){        
        bdConnection();
        List<BoardSoldNumbers>list= new ArrayList<>();        
        BoardSoldNumbers newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM TableroNumerosVendidos where idTablero= "+idBoard);
            while(resultSet.next()) {         
                newElement= new BoardSoldNumbers(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(4));                                        
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
    
    public Board getBoardInformation(){        
        bdConnection();
        Board newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 * FROM Tableros order by idTablero desc");
            while(resultSet.next()) {
                newElement= new Board(resultSet.getInt(6),resultSet.getString(1),
                        resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), 
                        resultSet.getString(5));                
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
    
    public List<SellNumber>GetNumberSoldFromTiicket(String ticketN){        
        bdConnection();
        List<SellNumber>list= new ArrayList<>();
        SellNumber newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM NumerosVendidos where tiquete= "+ticketN);                         
            while(resultSet.next()) {                       
                newElement= new SellNumber(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));                
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
    
    
    public Ticket getTicketInformation(){        
        bdConnection();
        Ticket newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 * FROM Tiquete order by idTiquete desc");
            while(resultSet.next()) {                
                newElement= new Ticket(resultSet.getInt(4),resultSet.getString(1),
                        resultSet.getInt(3),resultSet.getInt(3));                                
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
    
    public Time getTicketTime(String ticket){        
        bdConnection();
        Time newElement= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM Tiempos WHERE tiquete="+ticket);
            while(resultSet.next()) {                
                newElement= new Time(resultSet.getString(2),resultSet.getInt(1));
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
//                          insertar ejemplo 
/////////////////////////////////////////////////////////////////////////////////////////


    
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
    }       
}