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
public class Tablero {
    int idTablero;
    String cierreMañana;
    String cierreNoche;
    int numerosTablero;
    String tiempo;
    String comercio;

    public Tablero(int idTablero, String cierreMañana, String cierreNoche, int numerosTablero, String tiempo, String comercio) {
        this.idTablero = idTablero;
        this.cierreMañana = cierreMañana;
        this.cierreNoche = cierreNoche;
        this.numerosTablero = numerosTablero;
        this.tiempo = tiempo;
        this.comercio = comercio;
    }

    public int getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(int idTablero) {
        this.idTablero = idTablero;
    }

    public String getCierreMañana() {
        return cierreMañana;
    }

    public void setCierreMañana(String cierreMañana) {
        this.cierreMañana = cierreMañana;
    }

    public String getCierreNoche() {
        return cierreNoche;
    }

    public void setCierreNoche(String cierreNoche) {
        this.cierreNoche = cierreNoche;
    }

    public int getNumerosTablero() {
        return numerosTablero;
    }

    public void setNumerosTablero(int numerosTablero) {
        this.numerosTablero = numerosTablero;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }    
}