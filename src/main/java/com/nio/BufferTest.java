package com.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author zhanglifeng
 * @since 2020-04-17 14:43
 */
public class BufferTest {

    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(100);
        buffer.put('H').put('E').put('L').put('L').put('O');
        // 返回当前位置和限制之间的元素数。
        int length = buffer.remaining();

        char[] bigArray = new char[1000];
        buffer.get(bigArray, 0, length);

        System.out.println(bigArray);
        System.out.println(length);


        CharBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(20);
        LongBuffer longBuffer = byteBuffer.asLongBuffer();
//        byteBuffer.compact();
//        byteBuffer.equals()
//        byteBuffer.compareTo()
    }


    @Test
    public void lookup() throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost", 8080));
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));


        DatagramChannel dc = DatagramChannel.open();
        RandomAccessFile raf = new RandomAccessFile("somefile", "r");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mappedByteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

        FileLock fl = fc.lock();
        ServerSocketChannel sc = ServerSocketChannel.open();
        SocketChannel accept = sc.accept();
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sink = pipe.sink();
        int i = sink.validOps();

//        sink.register(, )
    }

    @Test
    public void testSelectors() throws IOException {
        Selector selector = Selector.open();
        SelectorProvider provider = selector.provider();
        boolean open = selector.isOpen();
        int select = selector.select(100);
//        SelectionKey.OP_READ | SelectionKey.OP_WRITE


    }


    /**
     * EOF: end of file
     * Channel copy method 1. This method copies data from the src
     * channel and writes it to the dest channel until EOF on src.
     * This implementation makes use of compact( ) on the temp buffer
     * to pack down the data if the buffer wasn't fully drained. This
     * may result in data copying, but minimizes system calls. It also
     * requires a cleanup loop to make sure all the data gets sent.
     */
    private static void channelCopy1 (ReadableByteChannel src, WritableByteChannel dest)
            throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect (16 * 1024);
        while (src.read (buffer) != -1) {
            // Prepare the buffer to be drained
            buffer.flip( );
            // Write to the channel; may block
            dest.write (buffer);
            // If partial transfer, shift remainder down
            // If buffer is empty, same as doing clear( )
            buffer.compact( );
        }
        // EOF will leave buffer in fill state
        buffer.flip( );
        // Make sure that the buffer is fully drained
        while (buffer.hasRemaining( )) {
            dest.write (buffer);
        }
    }

    /**
     * Channel copy method 2. This method performs the same copy, but
     * assures the temp buffer is empty before reading more data. This
     * never requires data copying but may result in more systems calls.
     * No post-loop cleanup is needed because the buffer will be empty
     * when the loop is exited.
     */
    private static void channelCopy2 (ReadableByteChannel src, WritableByteChannel dest)
            throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect (16 * 1024);
        while (src.read (buffer) != -1) {
            // Prepare the buffer to be drained
            buffer.flip( );
            // Make sure that the buffer was fully drained
            while (buffer.hasRemaining( )) {
                dest.write (buffer);
            }
            // Make the buffer empty, ready for filling
            buffer.clear( );
        }
    }
}
