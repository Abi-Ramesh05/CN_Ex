package cn;
import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1114);
            System.out.println("Connected to Echo Server.");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String input;
            System.out.println("Type messages (type 'exit' to quit):");
            while ((input = userInput.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")) break;

                out.println(input);
                System.out.println("Sent: " + input); // Debug message
                String response = serverInput.readLine();
                System.out.println(response); // Show echo from server
            }

            userInput.close();
            serverInput.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
