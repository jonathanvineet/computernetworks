import java.io.*;
import java.net.*;
class ex3aserver{
  public static void main(String[]a)throws Exception{
    ServerSocket ss=new ServerSocket(9999);
    while(true){
      Socket s=ss.accept();
      BufferedReader r=new BufferedReader(new InputStreamReader(s.getInputStream()));
      PrintWriter w=new PrintWriter(s.getOutputStream(),true);
      w.println("Welcome. type bye");
      String l;
      while((l=r.readLine())!=null){
        if(l.trim().equals("bye")) break;
        w.println("Got: "+l);
      }
      s.close();
    }
  }
}
