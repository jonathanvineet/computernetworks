import java.net.*;
 import java.util.*;
class ex4dnsclient {
  public static void main(String[] a) throws Exception {
    try (DatagramSocket ds = new DatagramSocket();
         Scanner sc = new Scanner(System.in)) {
      InetAddress srv = InetAddress.getByName("localhost");
      while (true) {
        System.out.print("Domain (or exit): ");
        String d = sc.nextLine().trim();
        if (d.equalsIgnoreCase("exit")) break;
        byte[] q = d.getBytes();
        ds.send(new DatagramPacket(q,q.length,srv,53));
        byte[] buf = new byte[512];
        DatagramPacket r = new DatagramPacket(buf,buf.length);
        ds.receive(r);
        System.out.println("Response: "+new String(r.getData(),0,r.getLength()));
      }
    }
  }
}
