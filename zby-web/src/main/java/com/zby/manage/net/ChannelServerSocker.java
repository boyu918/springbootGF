package com.zby.manage.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelServerSocker {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer readBuffer = ByteBuffer.allocate(128);
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        ByteBuffer[] byteBuffers = {readBuffer,byteBuffer};
        socketChannel.read(byteBuffers);
        byteBuffer.flip();
        readBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.println((char)byteBuffer.get());
        }
        while (readBuffer.hasRemaining()){
            System.out.println((char)readBuffer.get());
        }
        socketChannel.close();
        serverSocketChannel.close();
    }

}
