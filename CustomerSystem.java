// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




import java.util.Scanner;
// More packages may be imported in the space below
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random; 

class CustomerSystem{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below
        String firstName = "";
        String lastName = "";
        String city = "";
        String postalCode = "";
        long creditCardNumber = 0;
        String postThis = "";
        int iD = 11; // change later to a random??

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                
                postThis += customerID(iD) + enterCustomerInfo(firstName, lastName, city, postalCode, creditCardNumber);
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                try {
                    // replace postThis with the enterCustomerInfo(firstName, lastName, city, postalCode, creditCardNumber) method
                    generateCustomerDataFile(postThis);
                }
                catch (Exception e) {
                    System.out.println("An error has occured");
                }
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static String enterCustomerInfo(String fName, String lName, String place, String postCode, long creditCardNum) {
        Scanner reader = new Scanner(System.in);
        String comma = ", ";

        System.out.println("What is your first name?");
        fName = reader.nextLine();
        System.out.println("\nWhat is your last name?");
        lName = reader.nextLine();
        System.out.println("\nWhat city do you live in?");
        place = reader.nextLine();
        System.out.println("\nWhat is the postal code?");
        postCode = reader.nextLine();
        System.out.println("\nWhat is your credit card number?");
        creditCardNum = reader.nextLong();


        String ccNumtoString = Long.toString(creditCardNum);

        while (ccNumtoString.length() < 9) {
            System.out.println("You must enter more than 9 digits for your credit card");
            creditCardNum = reader.nextInt();
            ccNumtoString = Long.toString(creditCardNum);
        }

        String reversedNums = reverseDigits(ccNumtoString);
        
        String oddNum = identifyOdd(reversedNums);
        String evenIndex = identifyEven(reversedNums);

        String evenNum = doubleEvenNum(evenIndex);

        int sum1 = sumOfOdd(oddNum);
        int sum2 = sumOfEven(evenNum);


        String customerInfo = fName + comma + lName + comma + place + comma + postCode + comma + ccNumtoString + "\n";

        // validation happens here
        if (validatePostalCode(postCode) > 0 && validateCreditCard( sum1, sum2 ) == true) {

            return customerInfo;
        }
        else if (validateCreditCard( sum1, sum2 ) == false) {
            System.out.println("The credit card number is invalid\n\n");
            return "";
            // re input????????
        }
        else {
            System.out.println("The postal code is invalid\n\n");
            return "";
        }

        reader.close();
        return customerInfo;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validatePostalCode(){
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static boolean validateCreditCard(int sum1, int sum2){
        int sum = sum1 + sum2;

        if (sum % 10 == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void generateCustomerDataFile(String csvLine) throws FileNotFoundException {
        System.out.println("\nThis is printed: " + csvLine + "\n");

        File outFile = new File("customerInfo.csv");
        PrintWriter out = new PrintWriter(outFile);
        out.write(csvLine);
    
        System.out.println("Done");

        out.close(); // closing print writer
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/

    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static String customerID(int identification) {
        Random rand = new Random();
        int randNum2 = rand.nextInt(100);
        int randNum1 = rand.nextInt(100)*identification; 

        return Integer.toString(randNum1 + randNum2) + ". ";
        
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static int sumOfOdd(String sumOdd) {
        
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static int sumOfEven(String sumEven) {
        
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static String reverseDigits(String ccNumtoString) {
        
        
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static String identifyOdd(String reversed) {
        String oddNumbers = "";
        for (int i = 0; i < reversed.length(); i++) {
            String word = Character.toString(reversed.charAt(i));
            oddNumbers += word;
            i++;
        }
        return oddNumbers;
        
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static String identifyEven(String reversed) {
        String evenNumbers = "";
        for (int i = 1; i < reversed.length(); i++) {
            String word = Character.toString(reversed.charAt(i));
            evenNumbers += word;
            i++;
        }
        return evenNumbers;
        
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static String doubleEvenNum(String evenNumbers) {
        
        
    }
}