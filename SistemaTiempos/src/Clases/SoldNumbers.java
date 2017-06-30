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
public class SoldNumbers {
    private int id;
    private int number;
    private int ticket;
    private int board;
    private int moneySold;

    public SoldNumbers(int id, int number, int ticket, int board, int moneySold) {
        this.id = id;
        this.number = number;
        this.ticket = ticket;
        this.board = board;
        this.moneySold = moneySold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public int getBoard() {
        return board;
    }

    public void setBoard(int board) {
        this.board = board;
    }

    public int getMoneySold() {
        return moneySold;
    }

    public void setMoneySold(int moneySold) {
        this.moneySold = moneySold;
    }        
}
