package com.concurrent.features;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * fork join 框架
 * RecursiveAction  用于表示没有返回结果的 Fork/Join 任务。
 * RecursiveTask    用于表示有返回结果的 Fork/Join 任务。
 *
 *
 * @author zhanglifeng
 * @since 2021-08-26
 */
public class SumTask extends RecursiveTask<Long> {

    private static final int SEQUENTIAL_THRESHOLD = 50;

    private final List<Long> data;

    public SumTask(List<Long> data) {
        this.data = data;
    }

    @Override
    protected Long compute() {
        if (data.size() <= SEQUENTIAL_THRESHOLD) {
            long sum = computeSumDirectly();
            System.out.format("Sum of %s: %d\n", data.toString(), sum);
            return sum;
        }
        else {
            int mid = data.size() / 2;
            SumTask firstSubtask = new SumTask(data.subList(0, mid));
            SumTask secondSubtask = new SumTask(data.subList(mid, data.size()));
            firstSubtask.fork();
            secondSubtask.fork();
            Long firstSubtaskResult = firstSubtask.join();
            Long secondSubtaskResult = secondSubtask.join();
            return firstSubtaskResult + secondSubtaskResult;
        }
    }

    private long computeSumDirectly() {
        long sum = 0;
        for (Long l : data) {
            sum += l;
        }
        return sum;
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<Long> data = random.longs(1000, 1, 100)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(data.size());

        ForkJoinPool pool = new ForkJoinPool();
        Long sums = pool.invoke(new SumTask(data));
        System.out.println("sums = " + sums);
    }
}
