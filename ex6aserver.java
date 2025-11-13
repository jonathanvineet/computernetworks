import java.net.*;

class ex6aserver {
    static final int PORT = 5051;
    
    public static void main(String[] args) throws Exception {
        // ARP table: IP addresses and corresponding MAC addresses
        String[] ips = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
        String[] macs = {"00:1A:2B:3C:4D:5E", "00:1A:2B:3C:4D:5F", "00:1A:2B:3C:4D:60"};
        
        DatagramSocket ds = new DatagramSocket(PORT);
        System.out.println("ARP Server started on port " + PORT);
        byte[] buffer = new byte[1024];
        
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            
            String requestedIP = new String(packet.getData(), 0, packet.getLength()).trim();
            String response = "Not found";
            
            // Search for IP in array
            for (int i = 0; i < ips.length; i++) {
                if (ips[i].equals(requestedIP)) {
                    response = macs[i];
                    break;
                }
            }
            
            byte[] responseData = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(
                responseData, responseData.length, packet.getAddress(), packet.getPort());
            ds.send(responsePacket);
        }
    }
}
