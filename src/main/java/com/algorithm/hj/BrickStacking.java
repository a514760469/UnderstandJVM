package com.algorithm.hj;

import java.util.Map;
import java.util.TreeMap;

/**
 * 华为机试题：叠积木
 * https://blog.csdn.net/wj_phm/article/details/130122923
 *
 * @author zhanglifeng
 * @since 2023-11-26
 */
public class BrickStacking {

    // 保存每一个长度有几个
    static Map<Integer, Integer> bricks = new TreeMap<>();

    public static void main(String[] args) {
        int[] arr = {3,3,6,6,3,3};
        int f = f(arr);
        System.out.println("f = " + f);
    }

    public static int f(int[] arr) {
        int maxLen = 0;// 最长的积木
        int sumLen = 0;// 积木总长度
        for (int i : arr) {
            maxLen = Math.max(maxLen, i);
            sumLen += i;
            bricks.put(i, bricks.getOrDefault(i, 0) + 1);
        }

        // 开始遍历 每一层长度wallLen 初始为maxLen, 因为这样叠成是最高的, wallLen长度范围[maxLen, sumLen]
        for (int wallLen = maxLen; wallLen < sumLen; wallLen ++) {

            // 如果不能整除当前wallLen 则无论如何不能叠成
            if (sumLen % wallLen != 0) {
                continue;
            }
            // 高度为定下来为h 接下来验证砖块是否能拼成
            int h = sumLen / wallLen;

            for (int i = 0; i < arr.length; i++) {
                int curBrick = arr[i];
                // 当前积木的数量
                Integer count = bricks.get(curBrick);
                if (count > 0) {
                    // 积木被使用后，该长度的量 -1
                    bricks.put(curBrick, bricks.get(curBrick) - 1);
                    // 当前积木长度刚好可以搭建一层 或者可以找到和curBrick拼接使得长度能达到wallLen
                    if (curBrick == wallLen || process(wallLen, curBrick)) {
                        h--;
                    }
                    // 否则的话回溯积木
                    else {
                        bricks.put(curBrick, bricks.get(curBrick) + 1);
                    }
                }
            }

            // 最后如果搭建成功 h应该为0
            if (h == 0) {
                return sumLen / wallLen;
            }
        }
        return -1;
    }

    private static boolean process(int wallLen, int curBrick) {
        Integer otherCnt = bricks.get(wallLen - curBrick);
        if (curBrick == wallLen || (otherCnt != null && otherCnt > 0)) {
            bricks.put(wallLen - curBrick, otherCnt - 1);
            return true;
        }
        else {
            // 遍历剩余的积木 尝试把它们和当前积木长度拼接达到wallLen
            for (Integer brick : bricks.keySet()) {
                int newLen = curBrick + brick;
                // 如果长度已经超过wallLen，则说明后面都会超过长度，因为Map是TreeMap
                if (newLen > wallLen) {
                    return false;
                }
                // 看该长度的积木数量
                if (bricks.get(brick) > 0) {
                    // 使用该积木 数量-1
                    bricks.put(brick, bricks.get(brick) - 1);
                    if (newLen == wallLen || process(wallLen, newLen)) {
                        return true;
                    } else {
                        bricks.put(brick, bricks.get(brick) + 1);
                    }
                }
            }
        }
        return false;
    }
}
