package com.zby.manage.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EpollServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1",8000));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer writeBuffer = ByteBuffer.allocate(128);
        ByteBuffer readBuffer = ByteBuffer.allocate(128);
        writeBuffer.put("recieve".getBytes());
        writeBuffer.flip();

        while (true) {
            int nReady = selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();

                if (key.isAcceptable()) {
                    // 创建新的连接，并且把连接注册到selector上，而且，
                    // 声明这个channel只对读操作感兴趣。
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    readBuffer.clear();
                    socketChannel.read(readBuffer);

                    readBuffer.flip();
                    System.out.println("received : " + new String(readBuffer.array()));
                    key.interestOps(SelectionKey.OP_WRITE);
                }
                else if (key.isWritable()) {
                    writeBuffer.rewind();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.write(writeBuffer);
                    key.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }
}
