package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

       /* Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] args = line.split(" ");
        scanner.close();
*/
        String operation = "enc";
        String text = "";
        int key = 0;
        File fileIn = null;
        File fileOut = null;
        FileWriter writer = null;
        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i])) {
                operation = args[i + 1];
                continue;
            }
            if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
                continue;
            }
            if ("-data".equals(args[i])) {
                text = args[i + 1];
                continue;
            }
            if ("-in".equals(args[i])) {
                fileIn = new File(args[i + 1]);
                continue;
            }
            if ("-out".equals(args[i])) {
                fileOut = new File(args[i + 1]);
            }
        }
        if (fileOut != null) {
            writer = new FileWriter(fileOut);
        }
        if (fileIn != null && writer != null) {
            Scanner sc = new Scanner(fileIn);
            while (sc.hasNextLine()) {
                text = sc.nextLine();
            }
            sc.close();
        }

        if ("enc".equals(operation)) {
            encryption(text, key, writer);
        }
        if ("dec".equals(operation)) {
            decryption(text, key, writer);
        }
    }

    public static void encryption(String text, int key, FileWriter writer) throws IOException {
        String referenceForCharacters = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOQPRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        String encryptedReferenceForChars = referenceForCharacters.substring(key) + referenceForCharacters.substring(0, key);
        String character;
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < encryptedReferenceForChars.length(); j++) {
                if (text.charAt(i) == referenceForCharacters.charAt(j)) {
                    character = (text.replace(text.charAt(i), encryptedReferenceForChars.charAt(j)));
                    newText.append(character.charAt(i));
                    break;
                }
            }
        }
        try (writer) {
            if (writer != null) {
                writer.write(String.valueOf(newText));
            } else {
                System.out.println(newText);
            }
        }
    }

    public static void decryption(String text, int key, FileWriter writer) throws IOException {
        String referenceForCharacters = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOQPRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        String character;
        StringBuilder newText = new StringBuilder();
        if (key == 0) {
            newText.append(text);
        } else {
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < referenceForCharacters.length(); j++) {
                    if (text.charAt(i) == referenceForCharacters.charAt(j)) {
                        character = (text.replace(text.charAt(i), referenceForCharacters.charAt(j - key)));
                        newText.append(character.charAt(i));
                        break;
                    }
                }
            }
        }
        try (writer) {
            if (writer != null) {
                writer.write(String.valueOf(newText));
            } else {
                System.out.println(newText);
            }
        }
    }
}



