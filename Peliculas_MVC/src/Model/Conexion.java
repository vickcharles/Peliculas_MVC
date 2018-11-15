/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author viklercharles
 */
public class Conexion {
   private final String host = "jdbc:mysql://localhost:3306/";
    private final String bd = "bd_peliculas";
    private final String user = "root";
    private final String pass = "";
    
    Connection conexion = null;
    
    
    
    public Connection getConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  //driver para conexión con Mysql -> busca dentro de mysql conector el paq jdbc el driver
            conexion = DriverManager.getConnection(host + bd, user, pass);  //manager de las conexiones
            if (conexion != null) {
                System.out.println("Conexión a la base de datos " + bd + "OK\n" );
            }
        } catch (SQLException e) {  //errores de conexion de MySql
            System.out.println(e);
        } catch (ClassNotFoundException e) {  //errores por falta de Clases
            System.out.println(e);
        } catch (Exception e) {   //errores desconocidos
            System.out.println(e);
        }
        return conexion;
    }
    public void desconection() {
        conexion = null;
    } 
    
//    
//    
//    public static void main(String[] args) {
//           Conexion con = new Conexion();
//           con.getConexion();
//        
//    }
    
    
    
}
