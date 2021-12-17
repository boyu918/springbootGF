package com.zby.manage.net;

import org.apache.commons.io.input.ReaderInputStream;

import java.io.*;
import java.net.Socket;

public class ClientSockerCreater {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8080);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        bufferedWriter.write("hello\n");
        bufferedWriter.flush();
        String s  = bufferedReader.readLine();
        System.out.println(s);

        bufferedWriter.write("world\n");
        bufferedWriter.flush();
        s = bufferedReader.readLine();
        System.out.println(s);


        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
    }
}
