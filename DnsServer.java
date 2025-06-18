package cn;
import java.net.*;
import java.util.HashMap;

public class DnsServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(8888);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        // Fake DNS Stable
        HashMap<String, String> dnsTable = new HashMap<>();
        dnsTable.put("google.com", "142.250.190.14");
        dnsTable.put("openai.com", "104.21.77.131");
        dnsTable.put("rit.ac.in", "202.141.80.8");

        System.out.println("DNS Server is running...");

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String domain = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Query received: " + domain);

            String ip = dnsTable.getOrDefault(domain, "Domain not found");
            sendData = ip.getBytes();

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }
}
