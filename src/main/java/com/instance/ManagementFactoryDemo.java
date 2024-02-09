package com.instance;

import java.lang.management.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanglifeng
 * @since 2022-03-08
 */
public class ManagementFactoryDemo {

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            TimeUnit.SECONDS.sleep(2);
            osInfo();
        }
    }

    public static void threadInfo() {
        System.out.println("线程信息");
        // 线程信息
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println("thread count: " + threadMXBean.getThreadCount());
        System.out.println("peak thread count: " + threadMXBean.getPeakThreadCount());// 峰值线程数
        System.out.println("daemon thread count:" + threadMXBean.getDaemonThreadCount());
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        System.out.println("deadlockedThreads = " + Arrays.toString(deadlockedThreads));
        long[] allThreadIds = threadMXBean.getAllThreadIds();
        System.out.println("allThreadIds = " + Arrays.toString(allThreadIds));
        long[] monitorDeadlockedThreads = threadMXBean.findMonitorDeadlockedThreads();
        System.out.println("monitorDeadlockedThreads = " + Arrays.toString(monitorDeadlockedThreads));
    }

    public static void memory() {
        System.out.println("内存信息");
        // 运行时内存管理接口
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemory = memoryMXBean.getHeapMemoryUsage();
        System.out.println("init mem:" + (heapMemory.getInit() >> 10) + "k");
        System.out.println("used mem:" + (heapMemory.getUsed() >> 10) + "k");
        System.out.println("committed mem:" + (heapMemory.getCommitted() >> 10) + "k");
        System.out.println("max mem:" + (heapMemory.getMax() >> 10) + "k");
        System.out.println(memoryMXBean.getHeapMemoryUsage());
        MemoryUsage nonHeapMemory = memoryMXBean.getNonHeapMemoryUsage();

        System.out.println("=== 堆外内存信息 ===");
        System.out.println("init mem:" + (nonHeapMemory.getInit() >> 10) + "k");
        System.out.println("used mem:" + (nonHeapMemory.getUsed() >> 10) + "k");
        System.out.println("committed mem:" + (nonHeapMemory.getCommitted() >> 10) + "k");
        System.out.println("max mem:" + (nonHeapMemory.getMax() >> 10) + "k");
        System.out.println("nonHeapMemory = " + nonHeapMemory);

    }

    public static void osInfo() {
        System.out.println("系统信息");
        OperatingSystemMXBean systemMXBean = ManagementFactory.getOperatingSystemMXBean();
        int availableProcessors = systemMXBean.getAvailableProcessors();
        System.out.println("availableProcessors = " + availableProcessors);
        System.out.println("Arch = " + systemMXBean.getArch());
        System.out.println("系统平均负载：" + systemMXBean.getSystemLoadAverage());
    }
}
