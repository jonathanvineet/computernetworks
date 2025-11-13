import java.net.*;
import java.io.*;

class ex10crcserver {
    static String crc(String data) {
        String gen = "1011";
        String cur = data.substring(0, 4);
        
        for (int i = 4; i < data.length(); i++) {
            if (cur.charAt(0) == '1') {
                String x = "";
                for (int j = 0; j < 4; j++) {
                    x += (cur.charAt(j) == gen.charAt(j)) ? '0' : '1';
                }
                cur = x.substring(1) + data.charAt(i);
            } else {
                cur = cur.substring(1) + data.charAt(i);
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
        ServerSocket ss = new ServerSocket(8888);
        System.out.println("CRC Server started");
        
        while (true) {
            Socket s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            
            String codeword = in.readLine();
            String remainder = crc(codeword);
            
            if (remainder.contains("1")) {
                out.println("ERROR");
            } else {
                out.println("OK");
            }
            s.close();
        }
    }
}
