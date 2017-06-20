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
public class TimeNumber {
    int id;
    int numero;
    String tiempo;
    int totalNumberAmount;
    int board;

    public TimeNumber(int id, int numero, String tiempo, int totalNumberAmount) {
        this.id = id;
        this.numero = numero;
        this.tiempo = tiempo;
        this.totalNumberAmount = totalNumberAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getTotalNumberAmount() {
        return totalNumberAmount;
    }

    public void setTotalNumberAmount(int totalNumberAmount) {
        this.totalNumberAmount = totalNumberAmount;
    }

    public int getBoard() {
        return board;
    }

    public void setBoard(int board) {
        this.board = board;
    }    
}
