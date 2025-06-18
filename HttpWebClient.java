package cn;

import java.io.*;
import java.net.*;
public class HttpWebClient {
    public static void main(String[] args) {
        String host = "example.com"; 
        int port = 80;

        try {
            Socket socket = new Socket(host, port); 
            PrintWriter request = new PrintWriter(socket.getOutputStream(), true);
            request.println("GET / HTTP/1.1");
            request.println("Host: " + host);
            request.println("Connection: close");
            request.println(); 

            BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            System.out.println("---- Web Page Content ----");
            while ((line = response.readLine()) != null) {
                System.out.println(line);
            }
            response.close();
            request.close();
            socket.close();


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}