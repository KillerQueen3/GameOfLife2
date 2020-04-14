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
 * @author Brainrain
 */
public class GUI extends JFrame {
    MyThread myThread = new MyThread();
    boolean stopFlag = true;     //停止变换的标志
    int lifeNum;                 //存活生命数
    int generation=0;              //代数

    public GUI() {
        initComponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        myPanel1.repaint();
        myThread.start();
    }

    class MyThread extends Thread       //变换的线程
    {
        public void run()
        {
            while(true){
                if(!stopFlag)
                {
                    myPanel1.lifeList.UpdateNeighbours();
                    lifeNum = myPanel1.lifeList.UpdateLife();
                    label1.setText(String.valueOf(lifeNum));
                    generation++;
                    label2.setText(String.valueOf(generation));
                    myPanel1.repaint();
                }
                try
                {
                    sleep(1000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
            }
            }
        }
    }

    private void myPanel1MouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1&&stopFlag)                  //左击添加生命
            myPanel1.lifeList.makeAlive(x/10,y/10, true);
        else if(e.getButton() == MouseEvent.BUTTON3&&stopFlag)             //右击杀死生命
            myPanel1.lifeList.makeAlive(x/10,y/10,false);
        lifeNum=myPanel1.lifeList.aliveNum;
        label1.setText(String.valueOf(lifeNum));
        myPanel1.repaint();
    }

    private void button2ActionPerformed(ActionEvent e) {
        stopFlag = false;
        button3.setEnabled(false);
        button4.setEnabled(false);
    }

    private void button1ActionPerformed(ActionEvent e) {
        stopFlag = true;
        button3.setEnabled(true);
        button4.setEnabled(true);
    }

    private void button3ActionPerformed(ActionEvent e) {
        if(stopFlag) {
            myPanel1.lifeList.clear();
            myPanel1.repaint();
            generation=0;
            lifeNum=0;
            label1.setText(String.valueOf(lifeNum));
            label2.setText(String.valueOf(generation));
        }
    }

    private void button4ActionPerformed(ActionEvent e) {
        if(stopFlag)
        {
            myPanel1.lifeList.clear();
            ArrayList<Position> positions= Position.randomPositions(20,20, 200);
            for(Position p: positions)
            {
                myPanel1.lifeList.makeAlive(p.x,p.y,true);
            }
            lifeNum = myPanel1.lifeList.aliveNum;
            myPanel1.repaint();
            label1.setText(String.valueOf(lifeNum));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        myPanel1 = new MyPanel();
        button2 = new JButton();
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
            "[97,fill]" +
            "[105,fill]" +
            "[42,fill]",
            // rows
            "[69]" +
            "[58]" +
            "[83]0" +
            "[27]0" +
            "[30]"));

        //---- myPanel1 ----
        myPanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                myPanel1MouseClicked(e);
            }
        });
        contentPane.add(myPanel1, "cell 0 0 2 3,growy");

        //---- button2 ----
        button2.setText("start");
        button2.addActionListener(e -> {
			button2ActionPerformed(e);
			button2ActionPerformed(e);
		});
        contentPane.add(button2, "cell 2 0");

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
    private MyPanel myPanel1;
    private JButton button2;
    private JButton button1;
    private JButton button3;
    private JLabel label3;
    private JLabel label4;
    private JButton button4;
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
