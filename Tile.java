package com.example.ran.battleship.Logic;

/**
 * Created by ran on 05/12/2017.
 */

public class Tile {
    private Status tileStatus;
    private Ship ship;


    public Tile()
    {
        this.tileStatus = Status.NONE;
        this.ship = null;
    }

    public enum Status {
        NONE,NONE_X,HIT,MISS,SHIP,SUNK
    };

    public boolean hitTile(){
        this.ship.hitShip();
        return this.ship.isSunk();
    }

    public void setShip(Ship ship){
        this.ship = ship;
        setTileStatus(Tile.Status.SHIP);
    }


    public void setTileStatus(Status tileStatus) {
        this.tileStatus = tileStatus;
    }

    public Status getTileStatus() {
        return tileStatus;
    }

    public Point[] getShipPoints(){
        return this.ship.getPointsOnBoard();
    }

}
