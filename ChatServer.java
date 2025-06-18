package cn;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3111);
        System.out.println("Chat Server running...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected.");

        BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

        Thread readThread = new Thread(() -> {
            String msg;
            try {
                while ((msg = clientIn.readLine()) != null) {
                    System.out.println("Client: " + msg);
                }
            } catch (IOException e) {
                System.out.println("Connection closed.");
            }
        });

        readThread.start();

        String msgOut;
        while ((msgOut = serverInput.readLine()) != null) {
            clientOut.println(msgOut);
        }

        clientSocket.close();
        serverSocket.close();
    }
}
