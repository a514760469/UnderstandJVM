package com.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 低配 cat 程序
 *
 * @author zhanglifeng
 * @since 2020-04-27 14:44
 */
public class ChannelTransfer {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage filename...");
            return;
        }
        catFiles(Channels.newChannel(System.out), args);
    }

    private static void catFiles(WritableByteChannel target, String[] files) throws IOException {
        for (String file : files) {
            FileInputStream fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();
            fc.transferTo(0, fc.size(), target);
            fc.close();
            fis.close();
        }
    }

}
