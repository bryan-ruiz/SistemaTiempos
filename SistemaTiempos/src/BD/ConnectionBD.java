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
    /*public String getTermsConditions(){        
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
    
    
    */
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

    
    
    
/////////////////////////////////////////////////////////////////////////////////////////
//                          TIKETES    
/////////////////////////////////////////////////////////////////////////////////////////
    
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
            resultSet = statement.executeQuery("SELECT TOP 1 * FROM Tiquete order by tiquete desc");
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