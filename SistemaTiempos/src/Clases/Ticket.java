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
public class Ticket {
    int ticket;
    String date;
    int ticketTotalAmount;
    String timeHour;

    public Ticket(int ticket, String date, int ticketTotalAmount, String timeHour) {
        this.ticket = ticket;
        this.date = date;
        this.ticketTotalAmount = ticketTotalAmount;
        this.timeHour = timeHour;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTicketTotalAmount() {
        return ticketTotalAmount;
    }

    public void setTicketTotalAmount(int ticketTotalAmount) {
        this.ticketTotalAmount = ticketTotalAmount;
    }

    public String getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(String timeHour) {
        this.timeHour = timeHour;
    }    
}
