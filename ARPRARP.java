package cn;
import java.util.HashMap;
import java.util.Scanner;

public class ARPRARP {

    // IP to MAC table for ARP
    static HashMap<String, String> arpTable = new HashMap<>();
    // MAC to IP table for RARP
    static HashMap<String, String> rarpTable = new HashMap<>();

    // Initialize tables with sample data
    static {
        arpTable.put("192.168.1.1", "00:0A:95:9D:68:16");
        arpTable.put("192.168.1.2", "00:0A:95:9D:68:17");
        arpTable.put("192.168.1.3", "00:0A:95:9D:68:18");

        for (String ip : arpTable.keySet()) {
            rarpTable.put(arpTable.get(ip), ip);
        }
    }

    // ARP: Find MAC address from IP
    public static void arpRequest(String ip) {
        System.out.println("\n[ARP] Requesting MAC address for IP: " + ip);
        if (arpTable.containsKey(ip)) {
            System.out.println("[ARP] Response: MAC = " + arpTable.get(ip));
        } else {
            System.out.println("[ARP] Error: IP address not found.");
        }
    }

    // RARP: Find IP address from MAC
    public static void rarpRequest(String mac) {
        System.out.println("\n[RARP] Requesting IP address for MAC: " + mac);
        if (rarpTable.containsKey(mac)) {
            System.out.println("[RARP] Response: IP = " + rarpTable.get(mac));
        } else {
            System.out.println("[RARP] Error: MAC address not found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- ARP / RARP Simulation ---");
            System.out.println("1. ARP Request (IP ➜ MAC)");
            System.out.println("2. RARP Request (MAC ➜ IP)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter IP address: ");
                    String ip = scanner.nextLine();
                    arpRequest(ip);
                    break;
                case 2:
                    System.out.print("Enter MAC address: ");
                    String mac = scanner.nextLine();
                    rarpRequest(mac);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
