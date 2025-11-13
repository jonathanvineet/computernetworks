import java.net.*;
 import java.util.*;
class ex6aclient {
  static final int PORT = 5051;
  public static void main(String[] a) throws Exception {
    try (DatagramSocket ds = new DatagramSocket(); Scanner sc = new Scanner(System.in)) {
      System.out.print("IP to resolve: ");
      String ip = sc.nextLine().trim();
      ds.send(new DatagramPacket(ip.getBytes(), ip.length(), InetAddress.getByName("127.0.0.1"), PORT));
      byte[] buf = new byte[1024];
      DatagramPacket r = new DatagramPacket(buf, buf.length);
      ds.receive(r);
      System.out.println("MAC: "+new String(r.getData(),0,r.getLength()));
    }
  }
}

