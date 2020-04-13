package com.entity;

public class Position {
    public int x; //x坐标
    public int y; //y坐标

    Position(int xx, int yy)
    {
        x=xx;
        y=yy;
    }

    public Position[] getNeighbours()  //返回保存坐标的8个邻居的数组
    {
        Position[] positions = new Position[8];
        positions[0] = new Position(x-1,y-1);
        positions[1] = new Position(x-1,y);
        positions[2] = new Position(x-1,y+1);
        positions[3] = new Position(x,y-1);
        positions[4] = new Position(x,y+1);
        positions[5] = new Position(x+1,y-1);
        positions[6] = new Position(x+1,y);
        positions[7] = new Position(x+1,y+1);
        return positions;
    }
}
