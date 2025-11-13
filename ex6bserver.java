import java.net.*;
 import java.util.*;
class ex6bserver {
  static final int PORT = 6060;
  public static void main(String[] a) throws Exception {
    Map<String,String> table = new HashMap<>();
    table.put("00:1A:2B:3C:4D:5E","192.168.1.10");
    table.put("00:1A:2B:3C:4D:5F","192.168.1.11");
    try (DatagramSocket ds = new DatagramSocket(PORT)) {
      System.out.println("RARP Server on "+PORT);
      byte[] buf = new byte[1024];
      while (true) {
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        ds.receive(p);
        String mac = new String(p.getData(),0,p.getLength()).trim();
        String ip = table.getOrDefault(mac,"IP address not found");
        ds.send(new DatagramPacket(ip.getBytes(), ip.length(), p.getAddress(), p.getPort()));
      }
    }
  }
}
