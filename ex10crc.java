import java.util.*;

class ex10crc {
    static String divide(String data, String gen) {
        char[] arr = data.toCharArray();
        int n = gen.length();
        for (int i = 0; i <= arr.length - n; i++) {
            if (arr[i] == '1') {
                for (int j = 0; j < n; j++) {
                    arr[i + j] = (arr[i + j] == gen.charAt(j)) ? '0' : '1';
                }
            }
        }
        return new String(arr, arr.length - (n - 1), n - 1);
    }

    static String generate(String data, String gen) {
        String padded = data + "0".repeat(gen.length() - 1);
        String remainder = divide(padded, gen);
        return data + remainder;
    }

    static boolean check(String data, String gen) {
        String remainder = divide(data, gen);
        return !remainder.contains("1");
    }

    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Data: ");
        String data = sc.nextLine();
        System.out.print("Generator: ");
        String gen = sc.nextLine();

        String codeword = generate(data, gen);
        System.out.println("Codeword: " + codeword);

        System.out.print("Received: ");
        String received = sc.nextLine();
        if (received.isEmpty()) received = codeword;

        System.out.println(check(received, gen) ? "OK" : "ERROR");
        sc.close();
    }
}
