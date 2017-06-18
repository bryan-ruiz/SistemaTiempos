/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Joha
 */
public class Tiempo {
    String tiempo;
    int tiquete;

    public Tiempo(String tiempo, int tiquete) {
        this.tiempo = tiempo;
        this.tiquete = tiquete;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getTiquete() {
        return tiquete;
    }

    public void setTiquete(int tiquete) {
        this.tiquete = tiquete;
    }
    
    
}
