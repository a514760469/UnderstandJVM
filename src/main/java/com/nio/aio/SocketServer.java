package com.nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhanglifeng
 * @since 2023-05-24
 */
public class SocketServer {

    private static final Object waitObject = new Object();

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(20);

        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(threadPool);

        // 这个线程池是用来得到操作系统的“IO事件通知”的，不是用来进行“得到IO数据后的业务处理的”。要进行后者的操作，您可以再使用一个池(最好不要混用)
        // 您也可以不使用线程池(不推荐)，如果决定不使用线程池，直接AsynchronousServerSocketChannel.open()就行了
        try (final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(group)) {
            serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", 7777));
            // 为AsynchronousServerSocketChannel注册监听，注意只是为AsynchronousServerSocketChannel通道注册监听
            serverSocketChannel.accept(null, new ServerSocketChannelHandle(serverSocketChannel));

            synchronized (waitObject) {
                waitObject.wait();
            }
        }


    }
}

class ServerSocketChannelHandle implements CompletionHandler<AsynchronousSocketChannel, Void> {

    private final AsynchronousServerSocketChannel serverSocketChannel;

    public ServerSocketChannelHandle(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
        System.out.println("completed(AsynchronousSocketChannel result, ByteBuffer attachment)");

        // 每次都要重新注册监听(一次注册，一次响应)，但是由于“文件状态标示符”是独享的，所以不需要担心有“漏掉的”事件

        this.serverSocketChannel.accept(attachment, this);

        ByteBuffer readBuffer = ByteBuffer.allocate(50);

        socketChannel.read(readBuffer, new StringBuffer(), new SocketChannelReadHandle(socketChannel, readBuffer));
    }

    @Override
    public void failed(Throwable exc, Void attachment) {
        System.out.println("failed(Throwable exc, ByteBuffer attachment)");
    }
}

class SocketChannelReadHandle implements CompletionHandler<Integer, StringBuffer> {


    private final AsynchronousSocketChannel socketChannel;

    private final ByteBuffer byteBuffer;

    public SocketChannelReadHandle(AsynchronousSocketChannel socketChannel, ByteBuffer byteBuffer) {
        this.socketChannel = socketChannel;
        this.byteBuffer = byteBuffer;
    }

    @Override
    public void completed(Integer result, StringBuffer historyContext) {
        // 如果条件成立，说明客户端主动终止了TCP套接字，这时服务端终止就可以了
        if (result == -1) {
            try {
                this.socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        System.out.println("completed(Integer result, Void attachment) : 然后我们来取出通道中准备好的值");
        /*
         * 实际上，由于我们从Integer result知道了本次channel从操作系统获取数据总长度
         * 所以实际上，我们不需要切换成“读模式”的，但是为了保证编码的规范性，还是建议进行切换。
         *
         * 另外，无论是JAVA AIO框架还是JAVA NIO框架，都会出现“buffer的总容量”小于“当前从操作系统获取到的总数据量”，
         * 但区别是，JAVA AIO框架中，我们不需要专门考虑处理这样的情况，因为JAVA AIO框架已经帮我们做了处理(做成了多次通知)
         * */
        this.byteBuffer.flip();
        byte[] contexts = new byte[1024];
        this.byteBuffer.get(contexts, 0, result);
        this.byteBuffer.clear();
        String nowContent = new String(contexts, 0, result, StandardCharsets.UTF_8);
        historyContext.append(nowContent);
        System.out.println("================目前的传输结果: " + historyContext);

        //如果条件成立，说明还没有接收到“结束标记”
        if (historyContext.indexOf("over") == -1) {
            return;
        }

        //=========================================================================
        //          和上篇文章的代码相同，我们以“over”符号作为客户端完整信息的标记
        //=========================================================================
        System.out.println("=======收到完整信息，开始处理业务=========");
        historyContext = new StringBuffer();

        //还要继续监听(一次监听一次通知)
        this.socketChannel.read(this.byteBuffer, historyContext, this);

    }

    @Override
    public void failed(Throwable exc, StringBuffer attachment) {
        System.out.println("=====发现客户端异常关闭，服务器将关闭TCP通道");
        try {
            this.socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}