package com.example.ran.battleship.Logic;
import java.util.Random;
/**
 * Created by ran on 05/12/2017.
 */

public class Ship {

  //  private Point[] points;
    private int size;
    private int hitPoints;
    public String direction;
    private boolean isSunk;
    private Point pointsOnBoard[];


    public Ship(int size){
        this.size = size;
        this.hitPoints = size;
        this.isSunk = false;

        pointsOnBoard = new Point[size];
       // points= new Point[size];

    }


    public String getDirection() {
        return direction;
    }


    public void setDirection(String direction){
        this.direction = direction;
    }

    public void hitShip() {
        this.hitPoints--;
        if (this.hitPoints == 0 )
            isSunk = true;
    }

  //  public Point[] getPoints() {
    //    return points;
   // }

    public int getSize() {
        return size;
    }

    public boolean isSunk() {
        return isSunk;
    }


    public Point[] getPointsOnBoard() {
        return pointsOnBoard;
    }


    public void setPointsOnBoard(Point[] pointsOnBoard) {
        this.pointsOnBoard = pointsOnBoard;
    }
}
