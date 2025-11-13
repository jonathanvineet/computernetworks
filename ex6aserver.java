import java.net.*;
 import java.util.*;
class ex6aserver {
  static final int PORT = 5051;
  public static void main(String[] a) throws Exception {
    Map<String,String> table = new HashMap<>();
    table.put("192.168.1.1","00:1A:2B:3C:4D:5E");
    table.put("192.168.1.2","00:1A:2B:3C:4D:5F");
    try (DatagramSocket ds = new DatagramSocket(PORT)) {
      System.out.println("ARP Server on "+PORT);
      byte[] buf = new byte[1024];
      while (true) {
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        ds.receive(p);
        String ip = new String(p.getData(),0,p.getLength()).trim();
        String mac = table.getOrDefault(ip,"MAC address not found");
        ds.send(new DatagramPacket(mac.getBytes(), mac.length(), p.getAddress(), p.getPort()));
      }
    }
  }
}
