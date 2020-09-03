package encryptdecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        String type = "";
        int key = 0;
        String input = "";
        String result;
        String inFile = "";
        String outFile = "";
        String algorithm = "";
        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i])) {
                type = args[i + 1];
            } else if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
            } else if ("-data".equals(args[i])) {
                input = args[i + 1];
            } else if ("-in".equals(args[i])) {
                inFile = args[i + 1];
            } else if ("-out".equals(args[i])) {
                outFile = args[i + 1];
            } else if ("-alg".equals(args[i])) {
                algorithm = args[i + 1];
            }
        }
        if (type.isEmpty()) {
            type = "enc";
        }
        if (!inFile.isEmpty()) {
            Path file = Path.of(inFile);
            input = Files.readString(file);
        }
        if (type.equals("enc")) {
            if ("unicode".equals(algorithm)) {
                result = encryptUni(input, key);
            } else {
                result = encryptShift(input, key);
            }
        } else {
            if ("unicode".equals(algorithm)) {
                result = decryptUni(input, key);
            } else {
                result = decryptShift(input, key);
            }
        }
        if (outFile.isEmpty()) {
            System.out.println(result);
        } else {
            Path out = Path.of(outFile);
            Files.writeString(out,result);
        }

    }
    private static String encryptUni(String input, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            c = (char)(c + key);
            result.append(c);
        }
        return result.toString();
    }
    private static String decryptUni(String input, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
                c = (char)(c - key);
                result.append(c);
        }
        return result.toString();
    }
    private static String encryptShift(String input, int key) {
        StringBuilder message = new StringBuilder();
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char)(c + key);
                if (c > 'z') {
                    c = (char)(c - 'z' + 'a' - 1);
                }
                message.append(c);
            } else if (c >= 'A' && c <= 'Z') {
                c = (char)(c + key);
                if (c > 'Z') {
                    c = (char)(c - 'Z' + 'A' - 1);
                }
                message.append(c);
            } else {
                message.append(c);
            }
        }
        return message.toString();
    }
    private static String decryptShift(String input, int key) {
        StringBuilder message = new StringBuilder();
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char)(c - key);
                if (c < 'a') {
                    c = (char)(c + 'z' - 'a' + 1);
                }
                message.append(c);
            } else if (c >= 'A' && c <= 'Z') {
                c = (char)(c - key);
                if (c < 'A') {
                    c = (char)(c + 'Z' - 'A' + 1);
                }
                message.append(c);
            } else {
                message.append(c);
            }
        }
        return message.toString();
    }
}
