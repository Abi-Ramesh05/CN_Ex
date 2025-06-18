package cn;
import java.net.*;
import java.util.Scanner;

public class DnsClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData;
        byte[] receiveData = new byte[1024];
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter domain name to resolve: ");
        String domain = sc.nextLine();
        sendData = domain.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8888);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String ip = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Resolved IP: " + ip);
        clientSocket.close();
    }
}
