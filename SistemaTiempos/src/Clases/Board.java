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
    int idBoard;
    String closeMorning;
    String closeNight;
    int boardNumbers;
    String time;
    String store;

    public Board(int idBoard, String closeMorning, String CloseNight, int BoardNumbers, String time, String store) {
        this.idBoard = idBoard;
        this.closeMorning = closeMorning;
        this.closeNight = CloseNight;
        this.boardNumbers = BoardNumbers;
        this.time = time;
        this.store = store;
    }

    public int getIdTablero() {
        return idBoard;
    }

    public void setIdTablero(int idTablero) {
        this.idBoard = idTablero;
    }

    public String getCloseMorning() {
        return closeMorning;
    }

    public void setCloseMorning(String closeMorning) {
        this.closeMorning = closeMorning;
    }

    public String getCloseNight() {
        return closeNight;
    }

    public void setCloseNight(String closeNight) {
        this.closeNight = closeNight;
    }

    public int getBoardNumbers() {
        return boardNumbers;
    }

    public void setBoardNumbers(int boardNumbers) {
        this.boardNumbers = boardNumbers;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }    
}