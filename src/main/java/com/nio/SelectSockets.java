package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Simple echo-back server which listens for incoming stream connections and
 * echoes back whatever it reads. A single Selector object is used to listen to
 * the server socket (to accept new connections) and all the active socket
 * channels.
 * 简单的回显服务器，监听传入的流连接并回显它所读取的任何内容。
 * 单个选择器对象用于监听服务器套接字(以接受新连接)和所有活动的套接字通道。
 *
 * @author zhanglifeng
 * @since 2020-04-27 18:25
 */
public class SelectSockets {

    public static int PORT = 1324;

    public static void main(String[] args) throws Exception {
        new SelectSockets().go(args);
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
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 是否有新的连接？
                if (key.isAcceptable()) {
                    ServerSocketChannel scc = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = scc.accept();
                    registerChannel(selector, socketChannel, SelectionKey.OP_READ);
                    sayHello(socketChannel);
                }

                if (key.isReadable()) {
                    readDataFromSocket(key);
                }
                iterator.remove();
            }
        }
    }


    // ----------------------------------------------------------

    /**
     * Register the given channel with the given selector for the given operations of interest
     * channel注册到选择器
     */
    protected void registerChannel(Selector selector, SelectableChannel channel, int ops) throws IOException {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }
    // ----------------------------------------------------------
    // Use the same byte buffer for all channels. A single thread is servicing all the channels, so no danger of concurrent acccess.
    // 对所有通道使用相同的字节缓冲区, 单线程为所有通道提供服务, 没有并发访问的危险
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    /**
     * Sample data handler method for a channel with data ready to read.
     * @param key
     * A SelectionKey object associated with a channel determined by the selector to be ready for reading.
     * If the channel returns an EOF condition, it is closed here, which automatically
     * invalidates the associated key. The selector will then de-register the channel on the next select call.
     * @throws IOException  IOException
     */
    protected void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();

        int count;
        buffer.clear();// 清空
        while ((count = sc.read(buffer)) > 0) {
            buffer.flip();// 让缓冲区可读
            // 发送数据
            while (buffer.hasRemaining()) {
                sc.write(buffer);
            }
            // WARNING:
            // the above loop is evil. Because it's writing back to the same nonblocking channel it read the data from,
            // this code can potentially spin in a busy loop.
            // In real life you'd do something more useful than this.
            buffer.clear();
        }

        if (count < 0) {
            sc.close();
        }
    }


    // ----------------------------------------------------------
    /**
     * Spew a greeting to the incoming client connection.
     *
     * @param channel
     * The newly connected SocketChannel to say hello to.
     */
    private void sayHello(SocketChannel channel) throws Exception {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }
}
