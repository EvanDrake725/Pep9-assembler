import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Pepasm {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the file name (Do not include extensions): ");
        String fileName = in.nextLine()+".pep";
        ArrayList<String> assemblyLines = new ArrayList<>();
        try {
            File inFile = new File(fileName);
            Scanner fileReader = new Scanner(inFile);
            while (fileReader.hasNext()){
                assemblyLines.add(fileReader.nextLine());
            }

        }catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
        for(String line : assemblyLines) {
            System.out.println(line);
        }

    }
}
