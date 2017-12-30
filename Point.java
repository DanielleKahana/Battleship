package com.example.ran.battleship.Logic;

/**
 * Created by ran on 05/12/2017.
 */

public class Point {
    private int row;
    private int col;

    public Point(int row, int col){
        setRow(row);
        setCol(col);
    }

    public Point(){
        setRow(0);
        setCol(0);
    }

    public void setRow(int row){
        this.row=row;
    }
    public void setCol(int col){
        this.col =col;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
}
