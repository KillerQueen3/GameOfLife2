package com.ui;

import com.entity.LifeList;
import com.entity.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * 用于把LifeList中的内容画出来并更新
 */
public class MyPanel extends JPanel {
    public int maxX;                     // 最大x向数量
    public int maxY;                     // 最大y向数量
    public LifeList lifeList;

    public MyPanel()         // 无参构造函数，默认大小20*20
    {
        maxX = 20;
        maxY = 20;
        lifeList = new LifeList(maxX, maxY);
    }

    public void setMax(int x, int y)
    {
        if (x > 0 && x <= 100 && y > 0 && y <= 100) {       // 规定范围
            maxX = x;
            maxY = y;
            lifeList = new LifeList(maxX, maxY);
            this.setBounds(0, 0, maxX * 10 + 20, maxY * 10 + 20);   // 设置大小
        }
    }

    @Override
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
        ArrayList<Position> lifePosition = lifeList.getLifePosition();

        // 让颜色每次都变化
        Random random = new Random(System.currentTimeMillis());
        int cr = random.nextInt(225);
        int cg = random.nextInt(225);
        int cb = random.nextInt(225);
        g.setColor(new Color(cr, cg, cb));

        for(Position p : lifePosition)                   // 画life表
        {
            g.fillRect(p.x * 10, p.y * 10, 10, 10);
        }
    }
}
