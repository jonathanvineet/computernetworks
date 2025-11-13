import java.io.*;
import java.net.*;
class ex3aclient{
  public static void main(String[]a)throws Exception{
    Socket s=new Socket("127.0.0.1",9999);
    BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
    PrintWriter out=new PrintWriter(s.getOutputStream());
    BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
    String line;
    System.out.println(in.readLine());
    while((line=kb.readLine())!=null){
      out.println(line);
      if(line.trim().equals("bye")) break;
      System.out.println(in.readLine());
    }
    s.close();
  }
}
