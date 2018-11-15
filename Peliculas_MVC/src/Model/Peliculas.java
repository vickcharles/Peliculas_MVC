/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author viklercharles
 */
public class Peliculas {
    private int Id;
    private String Nombre;
    private String Genero;
    private int Ano;
    private String Pais;
    private String Actor;

    public Peliculas(int Id, String Nombre, String Genero, int Ano, String Pais, String Actor) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.Ano = Ano;
        this.Pais = Pais;
        this.Actor = Actor;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int Ano) {
        this.Ano = Ano;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getActor() {
        return Actor;
    }

    public void setActor(String Actor) {
        this.Actor = Actor;
    }  
    
}
