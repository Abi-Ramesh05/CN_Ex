package cn;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 3111);
        System.out.println("Connected to chat server.");

        BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        Thread readThread = new Thread(() -> {
            String msg;
            try {
                while ((msg = serverIn.readLine()) != null) {
                    System.out.println("Server: " + msg);
                }
            } catch (IOException e) {
                System.out.println("Disconnected.");
            }
        });

        readThread.start();

        String msgOut;
        while ((msgOut = userInput.readLine()) != null) {
            serverOut.println(msgOut);
        }

        socket.close();
    }
}
