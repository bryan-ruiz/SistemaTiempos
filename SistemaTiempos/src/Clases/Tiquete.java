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
public class Tiquete {
    int numero;
    String fechaTiquete;
    int total;
    int codigoBarra;

    public Tiquete(int numero, String fechaTiquete, int total, int codigoBarra) {
        this.numero = numero;
        this.fechaTiquete = fechaTiquete;
        this.total = total;
        this.codigoBarra = codigoBarra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFechaTiquete() {
        return fechaTiquete;
    }

    public void setFechaTiquete(String fechaTiquete) {
        this.fechaTiquete = fechaTiquete;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(int codigoBarra) {
        this.codigoBarra = codigoBarra;
    }        
}
