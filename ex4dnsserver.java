import java.net.*;
class ex4dnsserver {
  static final int PORT = 53;
  public static void main(String[] a) throws Exception {
    String[] hosts = {"google.com","facebook.com","instagram.com","twitter.com"};
    String[] ips = {"142.250.190.14","157.240.22.35","157.240.20.35","104.244.42.129"};
    try (DatagramSocket ds = new DatagramSocket(PORT)) {
      System.out.println("DNS Server running on port "+PORT);
      byte[] buf = new byte[512];
      while (true) {
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        ds.receive(p);
        String q = new String(p.getData(),0,p.getLength()).trim();
        String resp = "Unknown domain";
        for (int i=0;i<hosts.length;i++) if (hosts[i].equalsIgnoreCase(q)) { resp = ips[i]; break; }
        byte[] r = resp.getBytes();
        ds.send(new DatagramPacket(r, r.length, p.getAddress(), p.getPort()));
      }
    }
  }
}
