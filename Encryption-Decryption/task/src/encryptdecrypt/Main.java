package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
/*
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] args = line.split(" ");
        scanner.close();
*/
        String operation = "enc";
        String algorithm = "shift";
        String text = "";
        int key = 0;
        File fileIn = null;
        File fileOut = null;
        FileWriter writer = null;
        for (int i = 0; i < args.length; i++) {
            if ("-alg".equals(args[i])) {
                algorithm = args[i + 1];
                continue;
            }
            if ("-mode".equals(args[i])) {
                operation = args[i + 1];
                continue;
            }
            if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
                continue;
            }
            if ("-data".equals(args[i])) {
                text = args[i + 1].replace("\"", "");
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
            if ("unicode".equals(algorithm)) {
                encryptionUnicode(text, key, writer);
            } else {
                encryptionShift(text, key, writer);
            }
        }
        if ("dec".equals(operation)) {
            if ("unicode".equals(algorithm)) {
                decryptionUnicode(text, key, writer);
            } else {
                decryptionShift(text, key, writer);
            }
        }
    }

    public static void encryptionUnicode(String text, int key, FileWriter writer) throws IOException {
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

    public static void encryptionShift(String text, int key, FileWriter writer) throws IOException {
        String referenceForSymbols = " !\"#$%&'()*+,-./0123456789:;<=>?[\\]^_`{|}~";
        String referenceForCharactersLower = "abcdefghijklmnopqrstuvwxyz";
        String referenceForCharactersUpper = "ABCDEFGHIJKLMNOQPRSTUVWXYZ";
        String encryptedReferenceForCharsLower = referenceForCharactersLower.substring(key) + referenceForCharactersLower.substring(0, key);
        String encryptedReferenceForCharsUpper = referenceForCharactersUpper.substring(key) + referenceForCharactersUpper.substring(0, key);
        String character;
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            for (int k = 0; k < referenceForSymbols.length(); k++) {
                if (text.charAt(i) == referenceForSymbols.charAt(k)) {
                    newText.append(referenceForSymbols.charAt(k));
                    break;
                }
            }
            for (int j = 0; j < encryptedReferenceForCharsLower.length(); j++) {
                if (text.charAt(i) == referenceForCharactersLower.charAt(j)) {
                    character = (text.replace(text.charAt(i), encryptedReferenceForCharsLower.charAt(j)));
                    newText.append(character.charAt(i));
                    break;
                }
            }
            for (int j = 0; j < encryptedReferenceForCharsUpper.length(); j++) {
                if (text.charAt(i) == referenceForCharactersUpper.charAt(j)) {
                    character = (text.replace(text.charAt(i), encryptedReferenceForCharsUpper.charAt(j)));
                    newText.append(character.charAt(i));
                    break;
                }
            }
        }
        //String newerText= newText.toString().u
        try (writer) {
            if (writer != null) {
                writer.write(String.valueOf(newText));
            } else {
                System.out.println(newText);
            }
        }
    }

    public static void decryptionUnicode(String text, int key, FileWriter writer) throws IOException {
        StringBuilder referenceForCharacters = new StringBuilder(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOQPRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");
        //key = -key;
        //  char[] array = text.toCharArray();
        String encryptedReferenceForChars = referenceForCharacters.substring(key) + referenceForCharacters.substring(0, key);
        String character;
        StringBuilder newText = new StringBuilder();
        if (key == 0) {
            newText.append(text);
        } else {
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < referenceForCharacters.length(); j++) {
                    if (text.charAt(i) == referenceForCharacters.charAt(j)) {
                        if (j-key<0){
                            referenceForCharacters.reverse();
                            j--;
                            character = (text.replace(text.charAt(i), referenceForCharacters.charAt(j)));
                        }else {
                            character = (text.replace(text.charAt(i), referenceForCharacters.charAt(j-key)));
                        }
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

    public static void decryptionShift(String text, int key, FileWriter writer) throws IOException {
        String referenceForSymbols = " !\"#$%&'()*+,-./0123456789:;<=>?[\\]^_`{|}~";
        StringBuilder referenceForCharactersLower =new StringBuilder( "abcdefghijklmnopqrstuvwxyz")  ;
        StringBuilder referenceForCharactersUpper =new StringBuilder("ABCDEFGHIJKLMNOQPRSTUVWXYZ")    ;
        String encryptedReferenceForCharsLower = referenceForCharactersLower.substring(key) + referenceForCharactersLower.substring(0, key);
        String encryptedReferenceForCharsUpper = referenceForCharactersUpper.substring(key) + referenceForCharactersUpper.substring(0, key);
        String character;
        StringBuilder newText = new StringBuilder();
        if (key == 0) {
            newText.append(text);
        } else {
            for (int i = 0; i < text.length(); i++) {
                for (int k = 0; k < referenceForSymbols.length(); k++) {
                    if (text.charAt(i) == referenceForSymbols.charAt(k)) {
                        newText.append(referenceForSymbols.charAt(k));
                        break;
                    }
                }
                int temp=0;
                int tempj=0;
                for (int j = 0; j < referenceForCharactersLower.length(); j++) {
                    tempj = j;
                    if (referenceForCharactersLower.charAt(0)=='z'){
                        referenceForCharactersLower.reverse();
                    }
                    if (text.charAt(i) == referenceForCharactersLower.charAt(j)) {
                        if (j<key){ //3-10
                            temp =key - j-1;
                            // referenceForCharactersLower.reverse();
                            // temp = j;
                            //  j= referenceForCharactersLower.length()-2; // 25-2
                            //   j-=2;
                            character = (text.replace(text.charAt(i), referenceForCharactersLower.reverse().charAt(temp)));
                        }else {
                            // j=temp;
                            character = (text.replace(text.charAt(i), referenceForCharactersLower.charAt(j-key)));
                        }
                        newText.append(character.charAt(i));
                        break;
                    }
                }
                for (int j = 0; j < referenceForCharactersUpper.length(); j++) {
                    if (text.charAt(i) == referenceForCharactersUpper.charAt(j)) {
                        if (j-key<0){
                            j= referenceForCharactersUpper.length()-2;
                            j-=2;
                            character = (text.replace(text.charAt(i), referenceForCharactersUpper.charAt(j)));
                        }else {
                            character = (text.replace(text.charAt(i), referenceForCharactersUpper.charAt(j-key)));
                        }
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



