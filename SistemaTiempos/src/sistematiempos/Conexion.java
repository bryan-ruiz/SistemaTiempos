/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistematiempos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Joha
 */
public class Conexion {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;        
    private String msAccDB = "C:/Users/Joha/Documents/UNO.MDB";
    private String dbURL = "jdbc:ucanaccess://" + msAccDB;             
    
    
    public void conexionBase(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
 
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
    }
    
    
    public void baseLeerPersonal(){        
        conexionBase();
        try {            
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM Personal"); 
            System.out.println("ID\tName\t\t\tAge\tMatches");
            System.out.println("==\t================\t===\t=======");            
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "\t" + 
                        resultSet.getString(2) + "\t" + 
                        resultSet.getString(3) + "\t" +
                        resultSet.getString(4));
            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
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
    }
    
    public void baseEscribirPersonal(String nombre,String apellido, String cargo, String sueldo){                       
        conexionBase();
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
