import java.net.*;

class ex6bserver {
    static final int PORT = 6060;
    
    public static void main(String[] args) throws Exception {
        // RARP table: MAC addresses and corresponding IP addresses
        String[] macs = {"00:1A:2B:3C:4D:5E", "00:1A:2B:3C:4D:5F", "00:1A:2B:3C:4D:60"};
        String[] ips = {"192.168.1.10", "192.168.1.11", "192.168.1.12"};
        
        DatagramSocket ds = new DatagramSocket(PORT);
        System.out.println("RARP Server started on port " + PORT);
        byte[] buffer = new byte[1024];
        
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            
            String requestedMAC = new String(packet.getData(), 0, packet.getLength()).trim();
            String response = "Not found";
            
            // Search for MAC in array
            for (int i = 0; i < macs.length; i++) {
                if (macs[i].equals(requestedMAC)) {
                    response = ips[i];
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
