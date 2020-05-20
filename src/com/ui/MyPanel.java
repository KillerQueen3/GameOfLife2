package com.ui;

import com.entity.LifeList;
import com.entity.Position;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * 用于把LifeList中的内容画出来并更新.
 */
public class MyPanel extends JPanel {
    public int maxX;                     // 最大x向数量
    public int maxY;                     // 最大y向数量
    public LifeList lifeList;
    private final Random random = new Random(System.currentTimeMillis());
    public boolean colorful = false;

    public MyPanel() {       // 无参构造函数，默认大小20*20
        maxX = 20;
        maxY = 20;
        lifeList = new LifeList(maxX, maxY);
    }

    public void setMax(int x, int y) {
        if (x > 0 && x <= 100 && y > 0 && y <= 100) {       // 规定范围
            maxX = x;
            maxY = y;
            lifeList = new LifeList(maxX, maxY);
        }
    }

    @Override
    public void paint(Graphics g) {              // 绘制life表
        super.paint(g);
        int size = 10;
        for (int i = 0; i < maxX + 1; i++) {             // 画竖线
            g.drawLine(i * size, 0, i * size, maxY * size);
        }
        for (int i = 0; i < maxY + 1; i++) {             // 画横线
            g.drawLine(0, i * size, maxX * size, i * size);
        }
        List<Position> lifePosition = lifeList.getLifePosition();

        if (colorful) {                                   // 让颜色每次都变化
            int cr = random.nextInt(225);
            int cg = random.nextInt(225);
            int cb = random.nextInt(225);
            g.setColor(new Color(cr, cg, cb));
        } else {
            g.setColor(Color.BLACK);
        }

        for (Position p : lifePosition) {                // 画life表
            g.fillRect(p.x * size, p.y * size, size, size);
        }
    }
}
