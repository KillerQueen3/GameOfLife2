/*
 * Created by JFormDesigner on Tue Apr 14 17:40:43 CST 2020
 */

package com.ui;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import com.entity.Position;
import net.miginfocom.swing.*;

/**
 * @author INK
 */
public class GUI extends JFrame {
    MyThread myThread = new MyThread();
    boolean stopFlag = true;         // 停止变换的标志
    int lifeNum;                     // 存活生命数
    int generation = 0;              // 代数

    public GUI() {
        initComponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawer.setMax(60, 60);        // 设置最大xy
        drawer.repaint();
        myThread.start();
    }

    class MyThread extends Thread       // 变换的线程
    {
        public void run()
        {
            while(true){
                if(!stopFlag)
                {
                    drawer.lifeList.UpdateNeighbours();
                    lifeNum = drawer.lifeList.UpdateLife();     // 更新life表
                    label1.setText(String.valueOf(lifeNum));
                    generation++;
                    label2.setText(String.valueOf(generation));   // 更新代数
                    drawer.repaint();
                }
                try
                {
                    sleep(1000);     // 时间间隔1s
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void button2ActionPerformed(ActionEvent e) {        // 开始按钮，同时禁止编辑
        stopFlag = false;
        button3.setEnabled(false);
        button4.setEnabled(false);
    }

    private void button1ActionPerformed(ActionEvent e) {        // 停止按钮，同时允许编辑
        stopFlag = true;
        button3.setEnabled(true);
        button4.setEnabled(true);
    }

    private void button3ActionPerformed(ActionEvent e) {        // 清除按钮
        if(stopFlag) {
            drawer.lifeList.clear();
            drawer.repaint();
            generation = 0;
            lifeNum = 0;
            label1.setText(String.valueOf(lifeNum));
            label2.setText(String.valueOf(generation));
        }
    }

    private void button4ActionPerformed(ActionEvent e) {         // 随机按钮
        if(stopFlag)
        {
            drawer.lifeList.clear();
            ArrayList<Position> positions= Position.randomPositions(drawer.maxX, drawer.maxY);  // 随机坐标组
            for(Position p: positions)                           // 更新表
            {
                drawer.lifeList.makeAlive(p.x, p.y, true);
            }
            lifeNum = drawer.lifeList.aliveNum;
            generation = 0;
            drawer.repaint();
            label1.setText(String.valueOf(lifeNum));
            label2.setText(String.valueOf(generation));
        }
    }

    private void drawerMouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1&&stopFlag)                  //左击添加生命
            drawer.lifeList.makeAlive(x / 10, y / 10, true);
        else if(e.getButton() == MouseEvent.BUTTON3&&stopFlag)             //右击杀死生命
            drawer.lifeList.makeAlive(x / 10, y / 10, false);
        lifeNum = drawer.lifeList.aliveNum;
        label1.setText(String.valueOf(lifeNum));
        drawer.repaint();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button2 = new JButton();
        drawer = new MyPanel();
        button1 = new JButton();
        button3 = new JButton();
        label3 = new JLabel();
        label4 = new JLabel();
        button4 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        setTitle("GameOfLife");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[333,fill]0" +
            "[272,fill]" +
            "[42,fill]",
            // rows
            "[85]0" +
            "[123]0" +
            "[393]0" +
            "[27]0" +
            "[30]0" +
            "[]"));

        //---- button2 ----
        button2.setText("start");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2, "cell 2 0");

        //---- drawer ----
        drawer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawerMouseClicked(e);
            }
        });
        contentPane.add(drawer, "cell 0 0 2 3,growy");

        //---- button1 ----
        button1.setText("stop");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1, "cell 2 1");

        //---- button3 ----
        button3.setText("clear");
        button3.addActionListener(e -> button3ActionPerformed(e));
        contentPane.add(button3, "cell 2 2");

        //---- label3 ----
        label3.setText("number");
        contentPane.add(label3, "cell 0 3");

        //---- label4 ----
        label4.setText("generation");
        contentPane.add(label4, "cell 1 3");

        //---- button4 ----
        button4.setText("random");
        button4.addActionListener(e -> button4ActionPerformed(e));
        contentPane.add(button4, "cell 2 3");

        //---- label1 ----
        label1.setText("0");
        contentPane.add(label1, "cell 0 4");

        //---- label2 ----
        label2.setText("0");
        contentPane.add(label2, "cell 1 4");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button2;
    private MyPanel drawer;
    private JButton button1;
    private JButton button3;
    private JLabel label3;
    private JLabel label4;
    private JButton button4;
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
