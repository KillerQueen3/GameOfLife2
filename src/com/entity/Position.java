package com.entity;

import java.util.ArrayList;
import java.util.Random;

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

    public static ArrayList<Position> randomPositions(int maxX, int maxY, int size)  //返回一个在范围内，数量不大于size的随机坐标组
    {
        ArrayList<Position> positions = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        int x;
        int y;
        for(int i = 0; i<size; i++)
        {
            x=random.nextInt(maxX);
            y=random.nextInt(maxY);
            positions.add(new Position(x,y));
        }
        return positions;
    }
}
