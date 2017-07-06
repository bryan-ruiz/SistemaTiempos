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
public class Board {
    private int board;    
    private String dayClose;
    private String nightClose;
    private String store;
    private int stadisticsPer;
    private String password;    
    private String date;
    private int numbersPrincing;

    public Board(int board, String dayClose, String nightClose, String store, int stadisticsPer, String password, String date, int numbersPrincing) {
        this.board = board;
        this.dayClose = dayClose;
        this.nightClose = nightClose;
        this.store = store;
        this.stadisticsPer = stadisticsPer;
        this.password = password;
        this.date = date;
        this.numbersPrincing = numbersPrincing;
    }

    public String getDate() {
        return date;
    }

    public int getNumbersPrincing() {
        return numbersPrincing;
    }

    public int getBoard() {
        return board;
    }

    public void setBoard(int board) {
        this.board = board;
    }

    public String getDayClose() {
        return dayClose;
    }

    public void setDayClose(String dayClose) {
        this.dayClose = dayClose;
    }

    public String getNightClose() {
        return nightClose;
    }

    public void setNightClose(String nightClose) {
        this.nightClose = nightClose;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getStadisticsPer() {
        return stadisticsPer;
    }

    public void setStadisticsPer(int stadisticsPer) {
        this.stadisticsPer = stadisticsPer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }        
}