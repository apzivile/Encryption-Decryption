package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String operation = "enc";
        String text = "";
        int key = 0;
        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i])) {
                operation = args[i + 1];
            }
            if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
            }
            if ("-data".equals(args[i])) {
                text = args[i + 1];
            }
        }
        if ("enc".equals(operation)) {
            encryption(text, key);
        }
        if ("dec".equals(operation)) {
            decryption(text, key);
        }
    }

    public static void encryption(String text, int key) {
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
        System.out.println(newText);
    }

    public static void decryption(String text, int key) {
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
        System.out.println(newText);
    }
}



