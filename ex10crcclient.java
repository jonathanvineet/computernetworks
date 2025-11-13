import java.io.*;
import java.net.*;
import java.util.*;

class ex10crcclient {
    static String crc(String data) {
        String gen = "1011";
        String d = data + "000";
        String cur = d.substring(0, 4);
        
        for (int i = 4; i < d.length(); i++) {
            if (cur.charAt(0) == '1') {
                String x = "";
                for (int j = 0; j < 4; j++) {
                    x += (cur.charAt(j) == gen.charAt(j)) ? '0' : '1';
                }
                cur = x.substring(1) + d.charAt(i);
            } else {
                cur = cur.substring(1) + d.charAt(i);
            }
        }
        
        if (cur.charAt(0) == '1') {
            String x = "";
            for (int j = 0; j < 4; j++) {
                x += (cur.charAt(j) == gen.charAt(j)) ? '0' : '1';
            }
            return x.substring(1);
        }
        return cur.substring(1);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter data: ");
        String data = sc.nextLine();
        
        String checksum = crc(data);
        String codeword = data + checksum;
        
        System.out.println("Codeword: " + codeword);
        
        Socket s = new Socket("127.0.0.1", 8888);
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        out.println(codeword);
        System.out.println("Server: " + in.readLine());
        s.close();
    }
}
