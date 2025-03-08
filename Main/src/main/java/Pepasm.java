import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Pepasm {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the file name (Do not include extensions): ");
        //opens file info
        String fileName = in.nextLine() + ".pep";
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

            //Exception handling
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }

        //prints file info
        for (String line : assemblyLines) {
            System.out.println(line);
        }

        //splits lines into individual "words"
        System.out.println("The words:");
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
            if (currWord.startsWith("0x")){
                //removes the 0x from the word
                currWord = currWord.substring(2);
                //separates the string into 2 separate parts for each instruction
                String[] memoryInstructions = {currWord.substring(0,2), currWord.substring(2)};
                System.out.print(memoryInstructions[0] + " " + memoryInstructions[1]);
                }
            switch (currWord) {
                case "STBA" -> {
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("F1 ");
                    } else {
                        System.out.print("F0 ");
                    }
                }
                case "LDBA" -> {
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("D1 ");
                    } else {
                        System.out.print("D0 ");
                    }
                }
                case "STWA" -> {
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("E1 ");
                    } else {
                        System.out.print("E0 ");
                    }
                }
                case "LDWA" -> {
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("C1 ");
                    } else {
                        System.out.print("C0 ");
                    }
                }
                case "ADDA" -> {
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("61 ");
                    } else {
                        System.out.print("60 ");
                    }
                }
                case "ASLA" -> System.out.print("0A ");
                case "ASRA" -> System.out.print("0C ");
                case "CPBA" -> {
                    if (assemblyWords.get(i + 2).equals("d")) {
                        System.out.print("B1 ");
                    } else {
                        System.out.print("B0 ");
                    }
                }
                case "BRNE" -> System.out.print("1A003 ");
                case "STOP" -> System.out.print("00 ");
                case ".END" -> System.out.print("zz ");
                default -> System.out.print(" ");
            }
        }
    }
}
