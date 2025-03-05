import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Pepasm {
    public static void main(String[] args) throws FileNotFoundException {
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
            if (currWord.equals("STBA")) {
                if (assemblyWords.get(i+2).equals("d")) {
                    System.out.print("F1 ");
                }else{System.out.print("F0 ");}
            }
            else if (currWord.equals("LDBA")) {
                if (assemblyWords.get(i + 2).equals("d")) {
                    System.out.print("D1 ");
                } else {
                    System.out.print("D0 ");
                }
            }
            else if (currWord.equals("STWA")) {
                if (assemblyWords.get(i + 2).equals("d")) {
                    System.out.print("E1 ");
                } else {
                    System.out.print("E0 ");
                }
            }
            else if (currWord.equals("LDWA")) {
                if (assemblyWords.get(i + 2).equals("d")) {
                    System.out.print("C1 ");
                } else {
                    System.out.print("C0 ");
                }
            }
            else if (currWord.equals("ADDA")) {
                if (assemblyWords.get(i + 2).equals("d")) {
                    System.out.print("61 ");
                } else {
                    System.out.print("60 ");
                }
            }
            else if(currWord.equals("ASLA")){
                System.out.print("0A ");
            }
            else if(currWord.equals("ASRA")){
                System.out.print("0C ");
            }
            else if (currWord.equals("CPBA")) {
                if (assemblyWords.get(i + 2).equals("d")) {
                    System.out.print("B1 ");
                } else {
                    System.out.print("B0 ");
                }
            }
            else if (currWord.equals("BRNE")) {
                System.out.print("1A003 ");
                }
            else if(currWord.equals("STOP")){
                System.out.print("00 ");
            }
            else if(currWord.equals(".END")){
                System.out.print("zz ");
            }
            else {
                System.out.print(currWord + " ");
            }
        }
    }
}
