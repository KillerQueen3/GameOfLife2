package com.ui;

import com.entity.LifeList;
import com.entity.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    public LifeList lifeList = new LifeList(20,20);
    ArrayList<Position> lifePosition;

    public MyPanel()
    {

        this.setBounds(0,0,220,210);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        for (int i = 0;i<21;i++)
        {
            g.drawLine(i*10,0,i*10, 200);
            g.drawLine(0,i*10,200,i*10);
        }
        lifePosition = lifeList.getLifePosition();
        for(Position p:lifePosition)
        {
            g.fillRect(p.x*10,p.y*10,10,10);
        }
    }
}
