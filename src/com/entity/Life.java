package com.entity;

public class Life {
    public Position position; // 坐标
    public boolean alive;     // 是否活着
    public int neighbour;     // 邻居存活数


    Life(int xx, int yy)      // 构造函数
    {
        position = new Position(xx, yy);
        alive=false;
        neighbour=0;
    }

    public boolean UpdateLive()               // 更新存活状态，并返回更新后的状态
    {
        if(alive)
        {
            if(neighbour!=2&&neighbour!=3)    // 若存活，邻居不为2或3则死亡
                alive = false;
        }
        else
        {
            if(neighbour == 3)                // 若死亡，邻居为3则存活
                alive = true;
        }
        return alive;
    }
}
