package com.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 生命表，处理各种变换.
 */
public class LifeList {
    public Life[][] lives;        // 保存生命的数组
    public int maxX;              // 最大x向数量
    public int maxY;              // 最大y向数量
    public int aliveNum;   // 存活数

    /**
     * 构造函数.
     * @param xx x向最大值
     * @param yy y向最大值
     */
    public LifeList(final int xx, final int yy) {
        maxX = xx;
        maxY = yy;
        lives = new Life[xx][yy];
        aliveNum = 0;
        for (int i = 0; i < maxX; i++) {         // 初始化生命列表
            for (int j = 0; j < maxY; j++) {
                lives[i][j] = new Life(i, j);
            }
        }
    }

    /**
     * 检查坐标是否越界，不越界返回true.
     * @param x x坐标
     * @param y y坐标
     * @return 不越界？
     */
    public boolean checkXY(final int x, final int y) {
        return x < maxX && y < maxY && x >= 0 && y >= 0;
    }

    /**
     * 更新所有生命的邻居数，不更新是否生存.
     */
    public void updateNeighbours() {
        int num;                             // 临时存放邻居数
        Position[] positions;                // 临时存放邻居坐标
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                num = 0;
                positions = lives[i][j].position.getNeighbours();
                for (Position position : positions) {
                    if (checkXY(position.x, position.y)) {         // 不越界
                        if (lives[position.x][position.y].alive) { // 邻居存活则增加邻居数
                            num++;
                        }
                    }
                }
                lives[i][j].neighbour = num;   // 保存邻居数
            }
        }
    }

    /**
     * 更新存活信息.
     * @return 存活数
     */
    public int updateLife() {
        int num = 0; //存放存活数量
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (lives[i][j].updateLife()) {
                    num++;
                }
            }
        }
        aliveNum = num;
        return num;
    }

    /**
     * 杀死所有生命.
     */
    public void clear() {
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                lives[i][j].alive = false;
                lives[i][j].neighbour = 0;
            }
        }
        aliveNum = 0;
    }

    /**
     * 使给定坐标的生命存活或死亡.
     * @param x x坐标
     * @param y y坐标
     * @param life 生存状态
     */
    public void makeAlive(final int x, final int y, boolean life) {
        if (checkXY(x, y)) {
            if (lives[x][y].alive != life) {
                lives[x][y].alive = life;
                if (life) {                // 更新存活数
                    aliveNum++;
                } else {
                    aliveNum--;
                }
            }
        }
    }

    /**
     * 返回所有存活生命的坐标表.
     * @return 所有存活生命的坐标表
     */
    public List<Position> getLifePosition() {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (lives[i][j].alive) {
                    positions.add(lives[i][j].position);
                }
            }
        }
        return positions;
    }

    /**
     * 打印lives表(测试用).
     */
    public void print() {
        int[][] map = new int[maxX][maxY];
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (lives[i][j].alive) {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }
        for (int[] t : map) {
            System.out.println(Arrays.toString(t));
        }
    }
}
