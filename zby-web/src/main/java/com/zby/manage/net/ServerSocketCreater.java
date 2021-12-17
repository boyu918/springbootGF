package com.zby.manage.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketCreater {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
        String s = bufferedReader.readLine();
        while (s != null) {
            System.out.println(s);
            bufferedWriter.write(s.toUpperCase() + "\n");
            bufferedWriter.flush();
            s = bufferedReader.readLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }
}
