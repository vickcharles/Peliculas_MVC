/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Peliculas;
import Model.mdlPeliculas;
import View.frm_peliculas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author viklercharles
 */
public class ctrlPeliculas implements ActionListener {
    
    // Modelo 
    private mdlPeliculas CrudP;
    private Peliculas ModeloP;
    private DefaultTableModel modelo = new DefaultTableModel();
    
    // Vista
    private frm_peliculas vista;

   
    public ctrlPeliculas(mdlPeliculas CrudP, Peliculas ModeloP, frm_peliculas vista) {
        this.CrudP = CrudP;
        this.ModeloP = ModeloP;
        this.vista = vista;
          
        
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.jcbSeleccionPeliculas.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        
    }
    
    public void IniciarVistaTabla() {
      vista.setTitle("Mis Peliculas");
      vista.setLocationRelativeTo(null);
      vista.jTable1.setModel(modelo);
      modelo.addColumn("Id");
      modelo.addColumn("Nombre");
      modelo.addColumn("Genero");
      modelo.addColumn("Año");
      modelo.addColumn("Actor");
      modelo.addColumn("Pais");
      cargarPeliculas();
    }
    
    public void limpiarCampos(){
        vista.txtActor_in.setText(null);
        vista.txtNombre_in.setText(null);
        vista.txtAño_in.setText(null);
        vista.jcbGenero_in.setSelectedIndex(0);
        vista.jcbPais_in.setSelectedIndex(0);
        vista.txtActor_mo.setText(null);
        vista.txtNombre_mo.setText(null);
        vista.txtAño_mo.setText(null);
        vista.jcbGenero_mo.setSelectedIndex(0);
        vista.jcbPais_MO.setSelectedIndex(0);
    }
    
     private void cargarPeliculas() {
        try {
            ResultSet rs = CrudP.Mostrar();
            while (rs.next()) {
                Object row [] = new Object[6];      
                    for (int i = 0; i < 6; i++) {
                    row[i] = rs.getObject(i+1);                  
                }
                modelo.addRow(row);
                vista.jcbSeleccionPeliculas.addItem(new Peliculas(Integer.parseInt(row[0].toString()), row[1].toString(), row[2].toString(), Integer.parseInt(row[3].toString()), row[4].toString(), row[5].toString()));
                vista.jcbSeleccionPeliculas_eli.addItem(new Peliculas(Integer.parseInt(row[0].toString()), row[1].toString(), row[2].toString(), Integer.parseInt(row[3].toString()), row[4].toString(), row[5].toString()));  
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos en la base de datos");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRegistrar) {
            if (vista.txtActor_in.getText().isEmpty() || vista.txtNombre_in.getText().isEmpty() ||
                vista.txtAño_in.getText().isEmpty() || vista.jcbGenero_in.getSelectedItem().equals("") || 
                vista.jcbPais_in.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese todo los campos");
            }else{
                ModeloP.setNombre(vista.txtNombre_in.getText());
                ModeloP.setActor(vista.txtActor_in.getText());
                ModeloP.setPais(vista.jcbPais_in.getSelectedItem().toString());
                ModeloP.setGenero(vista.jcbGenero_in.getSelectedItem().toString());
                ModeloP.setAno(Integer.parseInt(vista.txtAño_in.getText()));
                if (CrudP.Regitrar(ModeloP)) {
                    JOptionPane.showMessageDialog(null, "Registro completado exitosamente");
                    this.cargarPeliculas();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Hubo un error en el registro");
                    limpiarCampos();
                }
            }
           
        }
        if(e.getSource() == vista.btnActualizar){
            if (vista.txtActor_mo.getText().isEmpty() || vista.txtNombre_mo.getText().isEmpty() ||
                vista.txtAño_mo.getText().isEmpty() || vista.jcbGenero_mo.getSelectedItem().equals("") || 
                vista.jcbPais_MO.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese todo los campos");
            } else{
                ModeloP.setNombre(vista.txtNombre_mo.getText());
                ModeloP.setId(Integer.parseInt(vista.txtId_mo.getText()));
                ModeloP.setActor(vista.txtActor_mo.getText());
                ModeloP.setPais(vista.jcbPais_MO.getSelectedItem().toString());
                ModeloP.setGenero(vista.jcbGenero_mo.getSelectedItem().toString());
                ModeloP.setAno(Integer.parseInt(vista.txtAño_mo.getText()));
                if (CrudP.actualizar(ModeloP)) {
                   JOptionPane.showMessageDialog(null, "Pelicula actualizada");
                   this.cargarPeliculas();
                   limpiarCampos(); 
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Hubo un error");
                    limpiarCampos(); 
                }
                
            }
        
            }
        
        if (e.getSource() == vista.jcbSeleccionPeliculas) {
           Peliculas peliculas = (Peliculas) vista.jcbSeleccionPeliculas.getSelectedItem();
           if(peliculas == null) {
              return;
           }
           vista.txtId_mo.setText(String.valueOf(peliculas.getId()));
           vista.txtNombre_mo.setText(String.valueOf(peliculas.getNombre()));
           vista.txtActor_mo.setText(String.valueOf(peliculas.getActor()));
           for (int i = 0; i < vista.jcbGenero_mo.getItemCount(); i++) {
               if (peliculas.getGenero().equals(vista.jcbGenero_mo.getItemAt(i).toString())) {
                   vista.jcbGenero_mo.setSelectedIndex(i);
                   break;
               }
           }
           vista.txtAño_mo.setText(String.valueOf(peliculas.getAno()));
           vista.txtActor_mo.setText(String.valueOf(peliculas.getActor()));
           for (int i = 0; i < vista.jcbPais_MO.getItemCount(); i++) {
               if (peliculas.getPais().equals(vista.jcbPais_MO.getItemAt(i).toString())) {
                   vista.jcbPais_MO.setSelectedIndex(i);
                   break;
               }
        }
            
        }
        if (e.getSource() == vista.btnEliminar) {
            
           Peliculas peliculas = (Peliculas) vista.jcbSeleccionPeliculas_eli.getSelectedItem();
           if(peliculas == null) {
              return;
           }
            if (CrudP.Eliminar(peliculas.getId())) {
               JOptionPane.showMessageDialog(null, "Se elimino la pelicula");
            
        }
            
        }
    }
    
    
}
