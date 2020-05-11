package com.ui;

import com.entity.LifeList;
import com.entity.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    public int maxX;                     // 最大x向数量
    public int maxY;                     // 最大y向数量
    public LifeList lifeList;
    ArrayList<Position> lifePosition;

    public MyPanel(int x, int y)
    {
        maxX = x;
        maxY = y;
        lifeList = new LifeList(maxX, maxY);
        this.setBounds(0, 0, maxX * 10 + 20, maxY * 10 + 20);   // 设置大小
    }

    public MyPanel()
    {
        maxX = 20;
        maxY = 20;
        lifeList = new LifeList(maxX, maxY);
    }

    public void setMax(int x, int y)
    {
        maxX = x;
        maxY = y;
        lifeList = new LifeList(maxX, maxY);
        this.setBounds(0, 0, maxX * 10 + 20, maxY * 10 + 20);   // 设置大小
    }

    public void paint(Graphics g)               // 绘制life表
    {
        super.paint(g);
        for (int i = 0; i < maxX + 1; i++)               // 画竖线
        {
            g.drawLine(i * 10, 0, i * 10, maxY * 10);
        }
        for (int i = 0; i < maxY + 1; i++)               // 画横线
        {
            g.drawLine(0, i * 10, maxX * 10, i * 10);
        }
        lifePosition = lifeList.getLifePosition();
        for(Position p : lifePosition)                   // 画life表
        {
            g.fillRect(p.x * 10, p.y * 10, 10, 10);
        }
    }
}
