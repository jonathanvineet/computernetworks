import java.net.*;
 import java.util.*;
class ex6bclient {
  static final int PORT = 6060;
  public static void main(String[] a) throws Exception {
    try (DatagramSocket ds = new DatagramSocket(); Scanner sc = new Scanner(System.in)) {
      System.out.print("MAC to resolve: ");
      String mac = sc.nextLine().trim();
      ds.send(new DatagramPacket(mac.getBytes(), mac.length(), InetAddress.getByName("127.0.0.1"), PORT));
      byte[] buf = new byte[1024];
      DatagramPacket r = new DatagramPacket(buf, buf.length);
      ds.receive(r);
      System.out.println("IP: "+new String(r.getData(),0,r.getLength()));
    }
  }
}
