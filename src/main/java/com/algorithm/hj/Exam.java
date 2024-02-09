package com.algorithm.hj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhanglifeng
 * @since 2023-09-01
 */
public class Exam {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            String[] split = in.nextLine().split("\\s+");
            int[] arr = new int[split.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(split[i]);
            }

            int ans = process(arr);
            System.out.println(ans);

        }
    }

    public static int process(int[] arr) {
        Arrays.sort(arr);
        int ans = -1;

        for (int i = arr[arr.length - 1]; i <= arr[arr.length - 1] + arr[0]; i ++) {
            boolean[] use = new boolean[arr.length];
            ans = Math.max(ans, f(arr, use, 0, i, 0));
        }
        return ans;
    }

    public static int f(int[] arr, boolean[] use, int i, int c, int h) {
        if (i == arr.length) {
            for (boolean u : use) {
                if (!u) {
                    return -1;
                }
            }
            return h;
        }

        if (arr[i] == c) {
            use[i] = true;
            return f(arr, use, i + 1, c, h + 1);
        }
        if (arr[i] < c) {
            for (int j = i; j < arr.length; j++) {
                if (arr[j] == arr[i] - c && !use[j]) {
                    use[j] = true;
                    return f(arr, use, i + 1, c, h + 1);
                }
            }
        }
        return -1;
    }

}
