package com.zby.manage.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ChannelClickSocket {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        ByteBuffer write2 = ByteBuffer.allocate(128);
        byteBuffer.put("byte buffer ready".getBytes());
        write2.put("new message".getBytes());
        byteBuffer.flip();
        write2.flip();

        ByteBuffer[] bufferArray = {byteBuffer, write2};

        socketChannel.write(bufferArray);


        socketChannel.close();
    }
}
