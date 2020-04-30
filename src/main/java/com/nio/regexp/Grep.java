package com.nio.regexp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A file searching class, similar to grep, which returns information
 * about lines matched in the specified files. Instances of this class
 * are tied to a specific regular expression pattern and may be applied
 * repeatedly to multiple files. Instances of Grep are thread safe, they
 * may be shared.
 *
 * 类似于grep的文件搜索类，它返回指定文件中匹配的行信息。该类的实例绑定到特定的正则表达式模式，可以重复地应用于多个文件。
 * Grep实例是线程安全的，它们可以被共享。
 *
 * @author zhanglifeng
 * @since 2020-04-30 16:38
 */
public class Grep {

    private Pattern pattern;

    public Grep(Pattern pattern) {
        this.pattern = pattern;
    }

    public Grep(String regex) {
        this(regex, false);
    }

    /**
     * Instantiate a Grep object and compile the given regular expression
     * string.
     * @param regex The regular expression string to compile into a
     * Pattern for internal use.
     * @param ignoreCase If true, pass Pattern.CASE_INSENSITIVE to the
     * Pattern constructor so that seaches will be done without regard
     * to alphabetic case. Note, this only applies to the ASCII
     * character set. Use embedded expressions to set other options.
     */
    public Grep(String regex, boolean ignoreCase) {
        this.pattern = Pattern.compile(regex, ignoreCase ? Pattern.CASE_INSENSITIVE : 0);
    }

    public MatchedLine[] grep(File file) throws IOException {
        List<MatchedLine> matchedLines = grepList(file);
        return matchedLines.toArray(new MatchedLine[0]);
    }

    public MatchedLine[] grep(String fileName) throws IOException {
        return grep(new File(fileName));
    }

    public MatchedLine[] grep(File[] files) throws IOException {
        LinkedList<MatchedLine> aggregate = new LinkedList<>();
        for (File file : files) {
            List<MatchedLine> list = grepList(file);
            aggregate.addAll(list);
        }
        MatchedLine[] arr = new MatchedLine[aggregate.size()];
        aggregate.toArray(arr);
        return arr;
    }




    /**
     * 运行grep操作的测试代码。
     * Test code to run grep operations. Accepts two command-line options: -i or --ignore-case,
     * compile the given pattern so that case of alpha characters is ignored.
     * Or -1, which runs the grep operation on each individual file,
     * rather that passing them all to one invocation. This is just to test the different methods.
     * The printed output is slightly different when -1 is specified.
     */
    public static void main(String[] args) {
        boolean ignoreCase = false;
        boolean onebyone = false;
        LinkedList<String> argList = new LinkedList<>();

        // 循环遍历变量，查找转换并保存模式及文件名
        for (String arg : args) {
            if (arg.startsWith("-")) {
                if (arg.equals("-i") || arg.equals("--ignore-case")) {
                    ignoreCase = true;
                }
            }

            if (arg.equals("-1")) {
                onebyone = true;
            }
            argList.add(arg);
        }

        if (argList.size() < 2) {
            System.err.println("usage: [options] pattern filename ...");
            return;
        }

        // 列表中第一个变量将被作为正则模式。
        // 将模式及忽略大小写标志的当前值传递给新的Grep对象。
        Grep grepper = new Grep(argList.remove(0), ignoreCase);

        if (onebyone) {
            // 遍历文件名并用grep处理他们
            for (String fileName : argList) {
                // 在每次grep前先打印文件名
                System.out.println(fileName + ":");

                MatchedLine[] matches;
                try {
                    matches = grepper.grep(fileName);
                } catch (IOException e) {
                    System.err.println("\t*** " + e);
                    continue;
                }

                // 打印匹配行的资料
                for (MatchedLine match : matches) {
                    System.out.println(" " + match.getLineNumber()
                            + " [" + match.start() + "-" + (match.end() - 1) + "]: "
                            + match.getLineText());
                }
            }
        } else {
            // 把文件名列表转换到File阵列中
            File[] files = new File[argList.size()];
            for (int i = 0; i < files.length; i++) {
                files[i] = new File(argList.get(i));
            }
            // 运行grep程序；忽略无法读取的文件
            MatchedLine[] matches = new MatchedLine[0];
            try {
                matches = grepper.grep(files);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 打印匹配行的资料
            for (MatchedLine match : matches) {
                System.out.println(match.getFile().getName() + ", "
                        + match.getLineNumber() + ": "
                        + match.getLineText());
            }
        }
    }

    /**
     * 对给出的文件进行grep
     */
    private List<MatchedLine> grepList(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("文件不存在：" + file);
        }

        if (!file.isFile()) {
            throw new IOException("不是文件：" + file);
        }

        if (!file.canRead()) {
            throw new IOException("不可读文件：" + file);
        }

        LinkedList<MatchedLine> list = new LinkedList<>();

        FileReader fileReader = new FileReader(file);

        LineNumberReader reader = new LineNumberReader(fileReader);

        Matcher matcher = this.pattern.matcher("");

        String line;
        while ((line = reader.readLine()) != null) {
            matcher.reset(line);
            if (matcher.find()) {
                list.add(new MatchedLine(file, reader.getLineNumber(), line, matcher.start(), matcher.end()));
            }
        }
        reader.close();
        return list;
    }

    // -------------------------------------------------------------
    /**
     * Encapsulation of a matched line from a file. This immutable object has five read-only properties:
     *
     * <ul>
     * <li>getFile(): The File this match pertains to.</li>
     * <li>getLineNumber(): The line number (1-relative) within the
     * file where the match was found.</li>
     * <li>getLineText( ): The text of the matching line</li>
     * <li>start( ): The index within the line where the matching
     * pattern begins.</li>
     * <li>end( ): The index, plus one, of the end of the matched
     * character sequence.</li>
     * </ul>
     */
    public static class MatchedLine {
        private File file;
        private int lineNumber;
        private String lineText;
        private int start;
        private int end;

        MatchedLine(File file, int lineNumber, String lineText, int start, int end) {
            this.file = file;
            this.lineNumber = lineNumber;
            this.lineText = lineText;
            this.start = start;
            this.end = end;
        }

        public File getFile() {
            return this.file;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public String getLineText() {
            return lineText;
        }

        public int start() {
            return start;
        }

        public int end() {
            return end;
        }
    }

}
