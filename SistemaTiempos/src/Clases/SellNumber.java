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
public class SellNumber {
    int number;
    int ticket;
    int moneyForSold;

    public SellNumber(int number, int ticket, int moneyForSold) {
        this.number = number;
        this.ticket = ticket;
        this.moneyForSold = moneyForSold;
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

    public int getMoneyForSold() {
        return moneyForSold;
    }

    public void setMoneyForSold(int moneyForSold) {
        this.moneyForSold = moneyForSold;
    }    
}
