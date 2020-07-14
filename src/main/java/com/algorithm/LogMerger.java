package com.algorithm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 有n个⽇志⽂件,每个⽂件有m⾏,每⼀⾏记录⼀次⽇志,格式是[time  log],
 * 单个⽇志⽂件⾥⾯的⽇志已经按照时间递增排序完成,现在需要将n 个⽇志⽂件合并为同⼀个⽇志⽂件,
 * 要求:最后得到的⽇志⽂件中的⽇志按照时间递增排序, 不能使⽤库函数或脚本中已经实现好的排序算法和⼯具, 需要⾃⼰实现数据结构和所需要的算法.
 * @author zhanglifeng
 * @since 2020-06-30
 */
public class LogMerger {

    /**
     * 合并日志文件
     * @param logFiles 模拟n个日志文件
     */
    public LogFile mergeLog(List<LogFile> logFiles) {
        List<String> mergedResult = new ArrayList<>();// 存放结果
        while (readFinished(logFiles)) {
            LogLine logLine = null;
            for (LogFile logFile : logFiles) {
                if (logFile.isEnd()) {
                    continue;
                }
                String currentLine = logFile.getCurrentLine();
                // 截取当前行的时间，进行比较
                String time = currentLine.substring(1, currentLine.indexOf(" "));
                LocalDateTime currentDataTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (logLine == null) {
                    logLine = new LogLine(currentLine, currentDataTime, logFile);
                    continue;
                }
                if (logLine.getTime().compareTo(currentDataTime) > 0) {
                    logLine = new LogLine(currentLine, currentDataTime, logFile);
                }
            }
            if (logLine != null) {
                logLine.getLogFile().indexIncrement();
                mergedResult.add(logLine.getLine());
            } else {
                break;
            }
        }

        return new LogFile(mergedResult);
    }

    /**
     * 判断是否所有文件都读完
     */
    public boolean readFinished(List<LogFile> logFiles) {
        for (LogFile logFile : logFiles) {
            boolean isEnd = logFile.isEnd();
            // 当前文件没读完
            if (!isEnd) {
                return false;
            }
        }
        return true;
    }

    /**
     * 日志中的一行内容
     */
    static class LogLine {
        private final String line;
        private final LocalDateTime time;// 当前行日志时间
        private final LogFile logFile;// 属于哪个日志文件
        public LogLine(String line, LocalDateTime time, LogFile logFile) {
            this.line = line;
            this.time = time;
            this.logFile = logFile;
        }
        public String getLine() {
            return line;
        }
        public LocalDateTime getTime() {
            return time;
        }
        public LogFile getLogFile() {
            return logFile;
        }
    }

    /**
     * 一个日志文件
     */
    static class LogFile {
        private final List<String> logs;
        private final int size;
        private int index;// 当前索引，从0开始

        public LogFile(List<String> logs) {
            this.logs = logs;
            this.size = logs.size();
        }
        /**
         * 移动索引
         */
        public void indexIncrement() {
            index++;
        }
        /**
         * 是否到文件末尾
         */
        public boolean isEnd() {
            return index == size - 1;
        }
        /**
         * 获取当前行内容
         */
        public String getCurrentLine() {
            return logs.get(index);
        }
    }
}
