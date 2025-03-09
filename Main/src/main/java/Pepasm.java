//code made by Evan Drake and Jemery Escobar
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Pepasm {
    public static void main(String[] args) {
        //checks for argument
        if (args.length < 1) {
            System.err.println("Please input a Pep9 file.");
            return;
        } else if (args.length > 1) {
            System.err.println("Please input only one Pep9 file.");
            return;
        }
        //argument becomes something easier to read
        String fileName = args[0];
        //makes array list out of the different lines
        ArrayList<String> assemblyLines = new ArrayList<>();
        //makes array list for different "words"
        ArrayList<String> assemblyWords = new ArrayList<String>();
        try {
            File inFile = new File(fileName);
            Scanner fileReader = new Scanner(inFile);
            while (fileReader.hasNext()) {
                assemblyLines.add(fileReader.nextLine());
            }
            //exception handling
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            return;
        }
        //prints file info
        System.out.println("Source code:");
        for (String line : assemblyLines) {
            System.out.println(line);
        }
        //splits lines into individual "words"
        System.out.println("Object code:");
        for (String line : assemblyLines) {
            String[] wordSplit = line.split(" ");
            for (String word : wordSplit) {
                assemblyWords.add(word);
            }
        }
        //checks "words" to change them
        for (int i = 0; i < assemblyWords.size(); i++) {
            String currWord = assemblyWords.get(i);
            //checking to see if instruction starts with 0x (hexadecimal)
            if (currWord.startsWith("0x")) {
                //removes the 0x from the word
                currWord = currWord.substring(2);
                while (currWord.length() < 5) {
                    currWord = "0" + currWord;
                }
                //separates the string into 2 separate parts for each instruction
                String[] memoryInstructions = { currWord.substring(0, 2), currWord.substring(2) };
                memoryInstructions[1] = memoryInstructions[1].replaceAll(",", "");
                //eliminates comments
                if (assemblyWords.get(i - 1).equals("Load")) {
                    System.out.print("");
                } else if (assemblyWords.get(i - 1).equals("load")) {
                    System.out.print("");
                } else {
                    System.out.print(memoryInstructions[0] + " " + memoryInstructions[1] + " ");
                }
            }
            //lotta case statements so be prepared
            switch (currWord) {
                case "STBA":
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("F1 ");
                    } else {
                        System.out.print("F0 ");
                    }
                    break;
                case "LDBA":
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("D1 ");
                    } else {
                        System.out.print("D0 ");
                    }
                    break;
                case "STWA":
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("E1 ");
                    } else {
                        System.out.print("E0 ");
                    }
                    break;
                case "LDWA":
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("C1 ");
                    } else {
                        System.out.print("C0 ");
                    }
                    break;
                case "ADDA":
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("61 ");
                    } else {
                        System.out.print("60 ");
                    }
                    break;
                case "ASLA":
                    System.out.print("0A ");
                    break;
                case "ASRA":
                    System.out.print("0C ");
                    break;
                case "CPBA":
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("B1 ");
                    } else {
                        System.out.print("B0 ");
                    }
                    break;
                case "BRNE":
                    System.out.print("1A 00 03 ");
                    break;
                case "STOP":
                    System.out.print("00 ");
                    break;
                case ".END":
                    System.out.print("zz ");
                    break;
                default:
                    System.out.print("");
            }
        }
    }
}
