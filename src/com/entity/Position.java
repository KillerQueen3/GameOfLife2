package com.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 坐标辅助类.
 */
public class Position {
    public int x;         // x坐标
    public int y;         // y坐标

    /**
     * 构造函数.
     * @param xx x坐标
     * @param yy y坐标
     */
    public Position(int xx, int yy) {
        x = xx;
        y = yy;
    }

    /**
     * @return 保存坐标的8个邻居的数组
     */
    public Position[] getNeighbours() {
        Position[] positions = new Position[8];
        positions[0] = new Position(x - 1, y - 1);
        positions[1] = new Position(x - 1, y);
        positions[2] = new Position(x - 1, y + 1);
        positions[3] = new Position(x, y - 1);
        positions[4] = new Position(x, y + 1);
        positions[5] = new Position(x + 1, y - 1);
        positions[6] = new Position(x + 1, y);
        positions[7] = new Position(x + 1, y + 1);
        return positions;
    }

    /**
     * @param maxX 最大x值
     * @param maxY 最大y值
     * @return 一个在范围内，数量不大于范围乘积的0.6倍的随机坐标组
     */
    public static List<Position> randomPositions(int maxX, int maxY) {
        List<Position> positions = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        int x;
        int y;
        int size = (int) (maxX * maxY * 0.6);
        for (int i = 0; i < size; i++) {
            x = random.nextInt(maxX);
            y = random.nextInt(maxY);
            positions.add(new Position(x, y));
        }
        return positions;
    }
}
