import java.io.*;
 import java.net.*;
class ex3bserver {
  public static void main(String[] a) throws Exception {
    try (ServerSocket ss = new ServerSocket(2000);
         Socket sk = ss.accept();
         BufferedReader fromClient = new BufferedReader(new InputStreamReader(sk.getInputStream()));
         PrintWriter toClient = new PrintWriter(sk.getOutputStream(), true);
         BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
      String s;
      while ((s = fromClient.readLine()) != null) {
        if ("END".equalsIgnoreCase(s)) { toClient.println("BYE"); break; }
        System.out.println("Client : " + s);
        System.out.print("Server : ");
        String reply = stdin.readLine();
        toClient.println(reply);
      }
    }
  }
}
