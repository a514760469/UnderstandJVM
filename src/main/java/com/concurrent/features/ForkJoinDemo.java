package com.concurrent.features;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhanglifeng
 * @since 2021-03-25
 */
public class ForkJoinDemo {

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(10000);
    }

    public static void main(String[] args) {

        // 创建2000个随机数组成的数组:
        long[] array = new long[2000];
        long expectedSum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);

        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(new SumTask(array, 0, array.length));
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");

    }


    static class SumTask extends RecursiveTask<Long> {

        static final int THRESHOLD = 500;

        private long[] array;
        private int start;
        private int end;

        public SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                // 如果任务足够小,直接计算:
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += this.array[i];
                    // 故意放慢计算速度:
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ignored) {
                    }
                }
                return sum;
            }

            // 任务太大,一分为二:
            int middle = (end + start) / 2;

            String format = String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end);
            System.out.println(format);
            SumTask subtask1 = new SumTask(this.array, start, middle);
            SumTask subtask2 = new SumTask(this.array, middle, end);
            invokeAll(subtask1, subtask2);

            Long subresult1 = subtask1.join();
            Long subresult2 = subtask2.join();

            Long result = subresult1 + subresult2;
            System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
            return result;
        }
    }
}
