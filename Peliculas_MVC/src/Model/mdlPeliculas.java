/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author viklercharles
 */
public class mdlPeliculas extends Conexion {
    
    public boolean Regitrar (Peliculas peliculas) {       
        PreparedStatement ps = null;
        Connection con = getConexion();
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
            
        } finally {
            try {
              con.close();
            } catch (SQLException e) {
               System.out.println(e);
            }
        }
    }  
    
    public static void main(String[] args) {
        mdlPeliculas ps = new mdlPeliculas();
        Peliculas p = new Peliculas(1, "Titanic", "Drama", 2102, "Usa", "Will Smitch");
        ps.Regitrar(p);

     }
   
}

   
