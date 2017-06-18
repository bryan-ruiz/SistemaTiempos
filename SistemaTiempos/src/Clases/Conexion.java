/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.Tiquete;
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
public class Conexion {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;        
    private String msAccDB = "C:/Users/Joha/Documents/GitHub/SistemaTiempos/SISTEMA_NUMEROS.MDB";
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
    
    public void cerrarConexionObtener(){        
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
    public String obtenerTerminosCondiciones(){        
        conexionBase();
        String nuevo= "";
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 * FROM CondicionesTerminos order by id desc");
            while(resultSet.next()) {                
                nuevo= resultSet.getString(2);                
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            cerrarConexionObtener();    
        }        
        return nuevo;
    }                    
    
    
    
/////////////////////////////////////////////////////////////////////////////////////////
//                          TABLERO
/////////////////////////////////////////////////////////////////////////////////////////
    
    public List<TableroNumerosVendidos>obtenerNumerosVendidosDeUnTablero(String idTablero){        
        conexionBase();
        List<TableroNumerosVendidos>lista= new ArrayList<>();        
        TableroNumerosVendidos nuevo= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM TableroNumerosVendidos where idTablero= "+idTablero);
            while(resultSet.next()) {         
                nuevo= new TableroNumerosVendidos(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(4));                                        
                lista.add(nuevo);
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            cerrarConexionObtener();    
        }        
        return lista;
    }                    
    
    public Tablero obtenerInformacionTablero(){        
        conexionBase();
        Tablero nuevo= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 * FROM Tableros order by idTablero desc");
            while(resultSet.next()) {
                nuevo= new Tablero(resultSet.getInt(6),resultSet.getString(1),
                        resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), 
                        resultSet.getString(5));                
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            cerrarConexionObtener();    
        }        
        return nuevo;
    }

    
    
    
/////////////////////////////////////////////////////////////////////////////////////////
//                          TIKETES    
/////////////////////////////////////////////////////////////////////////////////////////
    
    public List<NumerosVendidos>obtenerNumerosVendidosDeUnTiquete(String tiqueteN){        
        conexionBase();
        List<NumerosVendidos>lista= new ArrayList<>();
        NumerosVendidos nuevo= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM NumerosVendidos where tiquete= "+tiqueteN);                         
            while(resultSet.next()) {                       
                nuevo= new NumerosVendidos(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));                
                lista.add(nuevo);
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            cerrarConexionObtener();    
        }        
        return lista;
    }                
    
    
    public Tiquete obtenerInformacionTiquete(){        
        conexionBase();
        Tiquete nuevo= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT TOP 1 * FROM Tiquete order by idTiquete desc");
            while(resultSet.next()) {                
                nuevo= new Tiquete(resultSet.getInt(4),resultSet.getString(1),
                        resultSet.getInt(3),resultSet.getInt(3));                                
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            cerrarConexionObtener();    
        }        
        return nuevo;
    }
    
    public Tiempo obtenerTiempoTiquete(String tiquete){        
        conexionBase();
        Tiempo nuevo= null;
        try {                                    
            connection = DriverManager.getConnection(dbURL);            
            statement = connection.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM Tiempos WHERE tiquete="+tiquete);
            while(resultSet.next()) {                
                nuevo= new Tiempo(resultSet.getString(2),resultSet.getInt(1));
            }            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {            
            cerrarConexionObtener();    
        }        
        return nuevo;
    }


/////////////////////////////////////////////////////////////////////////////////////////
//                          insertar ejemplo 
/////////////////////////////////////////////////////////////////////////////////////////


    
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