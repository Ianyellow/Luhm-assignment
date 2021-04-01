import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriteFile{
  public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(System.in);
        File outFile = new File("customer info.csv");

        String firstName = "Ian";
        String lastName = "Huang";
        String city = "Toronto";
        int creditCardNum = 123456789;
        

        PrintWriter out = new PrintWriter(outFile);
        out.print(firstName + lastName + creditCardNum);

        // Echo keyboard input out to the file.
        // press "enter" then ctrl + Z
        /*while (reader.hasNextLine() ) {
            String line = reader.nextLine();
            out.print(line);
        }*/
    
        System.out.println("Done");
        out.close(); // Important: don't forget to close!
        reader.close();
        
    }
}
