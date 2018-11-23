/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author viklercharles
 */
public class mdlPeliculas extends Conexion {
     PreparedStatement ps = null;
     private ResultSet rs; // cuando hago select me va a almacenar todos los registros
      private Statement st;
     Connection con = getConexion();
    
    // Metodo para registrar
    public boolean Regitrar (Peliculas peliculas) {       
        String query = "INSERT INTO tbl_peliculas (Nom_peli, Gen_peli, Año_peli, Act_peli, Pais_peli) VALUES (?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, peliculas.getNombre());
            ps.setString(2, peliculas.getGenero());
            ps.setInt(3, peliculas.getAno());
            ps.setString(4, peliculas.getActor());
            ps.setString(5, peliculas.getPais());              
            ps.execute();
            return true;
   
        } catch (SQLException e) {
            System.out.println(e);
            return false; 
        } 
    } 
    
    // Metodo para actualizar
    public boolean actualizar(Peliculas peliculas) {
        try {
            String query = "UPDATE tbl_peliculas SET Nom_peli=?, Gen_peli=?, Año_peli=?, Act_peli=?, Pais_peli=? WHERE Id_peli=?";
            ps = con.prepareStatement(query);
            ps.setString(1, peliculas.getNombre());
            ps.setString(2, peliculas.getGenero());
            ps.setInt(3, peliculas.getAno());
            ps.setString(4, peliculas.getActor());
            ps.setString(5, peliculas.getPais());
            ps.setInt(6, peliculas.getId());
            ps.executeUpdate(); 
            System.out.println("Registro actualizado exitosamente");
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    // Metodo para Mostrar
    public ResultSet Mostrar() {
        try {
          String query = "SELECT * FROM tbl_peliculas";
          st = con.createStatement();
          rs = st.executeQuery(query);
          return rs;
          
        } catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
     public ResultSet BuscarGenero(String genero) {
        try {
          String query = "SELECT * FROM tbl_peliculas WHERE Gen_peli=?";
          ps = con.prepareStatement(query);
          ps.setString(1, genero);
          rs = ps.executeQuery();
          return rs;
          
        } catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
     
     public ResultSet BuscarPais(String pais) {
        try {
          String query = "SELECT * FROM tbl_peliculas WHERE Pais_peli=?";
          ps = con.prepareStatement(query);
          ps.setString(1, pais);
          rs = ps.executeQuery();
          return rs;
          
        } catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
     
      public ResultSet MostrarPaisGenero(String buscar) {
        try {
          String query = "SELECT * FROM tbl_peliculas WHERE Nom_peli LIKE '%"+buscar+"%' or Act_peli LIKE '%"+buscar+"%' ";
          ps = con.prepareStatement(query);
          rs = ps.executeQuery();
          return rs;
          
        } catch(SQLException e){
            System.err.println(e+"Error");
            return null;
        }
    }
    
    //Metodo para eliminar Peliculas
     public boolean Eliminar(int id) {        
        try {
            String query = "Delete from tbl_peliculas WHERE id_peli = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    
    }

}

   
