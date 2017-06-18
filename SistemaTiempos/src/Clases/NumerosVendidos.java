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
public class NumerosVendidos {
    int numero;
    int tiquete;
    int plataVendido;

    public NumerosVendidos(int numero, int tiquete, int plataVendido) {
        this.numero = numero;
        this.tiquete = tiquete;
        this.plataVendido = plataVendido;
    }    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTiquete() {
        return tiquete;
    }

    public void setTiquete(int tiquete) {
        this.tiquete = tiquete;
    }

    public int getPlataVendido() {
        return plataVendido;
    }

    public void setPlataVendido(int plataVendido) {
        this.plataVendido = plataVendido;
    }    
}
