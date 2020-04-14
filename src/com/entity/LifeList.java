package com.entity;

import java.util.ArrayList;
import java.util.Arrays;

public class LifeList {
    Life[][] lives;   //保存生命的数组
    int maxX;       //最大x向数量
    int maxY;       //最大y向数量
    public int aliveNum;   //存活数

    public LifeList(int xx, int yy)
    {
        maxX=xx;
        maxY=yy;
        lives = new Life[xx][yy];
        aliveNum = 0;
        for(int i = 0;i<maxX;i++)           //初始化生命列表
        {
            for(int j = 0; j<maxY ;j++)
            {
                lives[i][j] = new Life(i,j);
            }
        }
    }

    boolean CheckXY(int x, int y)          //检查坐标是否越界，不越界返回true
    {
        return x < maxX && y < maxY && x >= 0 && y >= 0;
    }

    public void UpdateNeighbours()           //更新所有生命的邻居数，不更新是否生存
    {
        int num;   //临时存放邻居数
        Position[] positions; //临时存放邻居坐标
        for(int i = 0; i<maxX; i++)
        {
            for(int j=0; j<maxY; j++)
            {
                num = 0;
                positions = lives[i][j].position.getNeighbours();
                for(Position position:positions)
                {
                    if (CheckXY(position.x,position.y))        //不越界
                    {
                        if(lives[position.x][position.y].alive)  //邻居存活则增加邻居数
                            num++;
                    }
                }
                lives[i][j].neighbour = num;   //保存邻居数
            }
        }
    }

    public int UpdateLife()          //更新存活信息，并返回存活数
    {
        int num=0; //存放存活数量
        for(int i = 0; i <maxX; i++)
        {
            for(int j = 0; j<maxY; j++)
            {
                if(lives[i][j].UpdateLive())
                    num++;
            }
        }
        aliveNum = num;
        return num;
    }

    public void clear()                  //杀死所有生命
    {
        for(int i = 0; i <maxX; i++)
        {
            for(int j = 0; j<maxY; j++)
            {
                lives[i][j].alive = false;
            }
        }
        aliveNum = 0;
        UpdateNeighbours();
        UpdateLife();
    }

    public void makeAlive(int x, int y, boolean life)   //使给定坐标的生命存活或死亡
    {
        if(CheckXY(x,y))
        {
            if(lives[x][y].alive!=life)
            {
                lives[x][y].alive=life;
                if(life)                 // 更新存活数
                    aliveNum++;
                else
                    aliveNum--;
            }
        }
    }

    public ArrayList<Position> getLifePosition()     //返回所有存活生命的坐标表
    {
        ArrayList<Position> positions = new ArrayList<>();
        for(int i = 0; i <maxX; i++)
        {
            for(int j = 0; j<maxY; j++)
            {
                if(lives[i][j].alive)
                    positions.add(lives[i][j].position);
            }
        }
        return positions;
    }

    public void print()         //打印lives表(测试用)
    {
        int[][] l = new int[maxX][maxY];
        for(int i = 0; i <maxX; i++)
        {
            for(int j = 0; j<maxY; j++)
            {
                if(lives[i][j].alive)
                    l[i][j]=1;
                else
                    l[i][j]=0;
            }
        }
        for(int[] t:l)
            System.out.println(Arrays.toString(t));
    }
}
