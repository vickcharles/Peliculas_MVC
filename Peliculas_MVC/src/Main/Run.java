/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.ctrlPeliculas;
import Model.Peliculas;
import Model.mdlPeliculas;
import View.frm_peliculas;

/**
 *
 * @author viklercharles
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Peliculas modelo = new Peliculas();
       mdlPeliculas crud = new mdlPeliculas();
       frm_peliculas vista = new frm_peliculas();
       
       ctrlPeliculas control = new ctrlPeliculas(crud, modelo, vista);
       control.IniciarVistaTabla();
       vista.setVisible(true);
    }
    
}
