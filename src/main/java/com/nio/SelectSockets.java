package com.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;

/**
 * Simple echo-back server which listens for incoming stream connections and
 * echoes back whatever it reads. A single Selector object is used to listen to
 * the server socket (to accept new connections) and all the active socket
 * channels.
 * 简单的回显服务器，监听传入的流连接并回显它所读取的任何内容。
 * 单个选择器对象用于监听服务器套接字(以接受新连接)和所有活动的套接字通道。
 * @author zhanglifeng
 * @since 2020-04-27 18:25
 */
public class SelectSockets {

    public static int PORT = 1324;

    public static void main(String[] args) {

    }

    public void go(String[] args) throws Exception {
        int port = PORT;
        // 覆盖默认监听端口
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        System.out.println("监听：" + port);
        // 分配一个未绑定的 ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 获取关联的ServerSocket
        ServerSocket socket = ssc.socket();
        socket.bind(new InetSocketAddress(port));

        ssc.configureBlocking(false);// 设置非阻塞

        // 注册到选择器
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int n = selector.select();
            //  可能会阻塞很长时间， Upon returning, the selected set contains keys of the ready channels.
            if (n == 0) {
                continue;
            }
            for (SelectionKey key : selector.selectedKeys()) {
                // 是否有新的连接？
                if (key.isAcceptable()) {
                    ServerSocketChannel scc = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = scc.accept();

                }

            }

        }
    }


    // ----------------------------------------------------------
    /**
     * Register the given channel with the given selector for the given
     * operations of interest
     */
    protected void registerChannel(Selector selector, SelectableChannel selectableChannel, int ops) {

    }
}
