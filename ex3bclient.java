import java.io.*;
 import java.net.*;
class ex3bclient {
  public static void main(String[] a) throws Exception {
    try (Socket sk = new Socket("127.0.0.1", 2000);
         BufferedReader sin = new BufferedReader(new InputStreamReader(sk.getInputStream()));
         PrintWriter sout = new PrintWriter(sk.getOutputStream(), true);
         BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        System.out.print("Client : ");
        String msg = stdin.readLine();
        sout.println(msg);
        String resp = sin.readLine();
        System.out.println("Server : " + resp);
        if ("BYE".equalsIgnoreCase(resp)) break;
      }
    }
  }
}
