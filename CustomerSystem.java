// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray




import java.util.Scanner;
// More packages may be imported in the space below
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class CustomerSystem{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below
        String postThis = "";   // global variable that will store the customer's info
        String comma = ", ";    // A string representing a comma

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                
                postThis = "";      // empty the string
                // storing the content from the method
                postThis += enterCustomerInfo(comma);      // call enterCustomerInfo method
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                
                // Detect for errors when generating customer data file
                try {
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
    * Description: take user input, validates the information and stores all information into a string
    *
    * @author   Ian
    * @param    comma - an string representing a comma
    * @return   String customerInfo - customer's ID, first/last name, city, postal code and credit card # with commas
    */
    public static String enterCustomerInfo(String comma) {
        Scanner reader = new Scanner(System.in);

        // prompt user inputs
        System.out.println("What is your first name?");
        String fName = reader.nextLine();
        System.out.println("\nWhat is your last name?");
        String lName = reader.nextLine();
        System.out.println("\nWhat city do you live in?");
        String place = reader.nextLine();

        // detects if the postal code has less than 3 characters
        String postCode;
        do {
            System.out.println("\nWhat is the postal code? (Please enter at least 3 characters)");
            postCode = reader.nextLine();
        } while (postCode.length() < 3);
        
        // detects if the credit card has less than 9 numbers
        String creditCardNum;
        do {
            System.out.println("\nWhat is your credit card number? (Please enter at least 9 numbers)");
            creditCardNum = reader.nextLine();     // even though it's suppose to be a number, still accept it as a string
        } while (creditCardNum.length() < 9);

        // call reverseDigits method
        String reversedNums = reverseDigits(creditCardNum);     // store the string that's returned

        // call identifyOdd method 
        String oddNum = identifyOdd(reversedNums);      // store the string that's returned
        // call identifyEven method 
        String evenIndex = identifyEven(reversedNums);      // store the string that's returned

        // call doubleEvenNUm method
        String evenNum = doubleEvenNum(evenIndex);      // store the string that's returned

        // call sumOfOdd method
        int sum1 = sumOfOdd(oddNum);        // store the integer that's returned
        // call sumOfEven method
        int sum2 = sumOfEven(evenNum);      // store the integer that's returned


        // storing customer's first name, last name, postal code, and credit card #
        String customerInfo = fName + comma + lName + comma + place + comma + postCode + comma + creditCardNum;


        // validation happens here
        // call validatePostalCode method and validateCreditCard method
        // condition: if the postal code and credit card are valid
        if (validatePostalCode(postCode) > 0 && validateCreditCard( sum1, sum2 ) == true) {
            System.out.println("\n\n");     // print empty lines

            // call customerID method
            return customerID(reversedNums) + customerInfo;    // unique ID # plus all the customer's info
        }
        // condition: if the credit card in invalid
        else if (validateCreditCard( sum1, sum2 ) == false) {
            System.out.println("The credit card number is invalid\n\n");

            return "";      // return nothing
        }
        // if the postal code is invalid
        else {
            System.out.println("The postal code is invalid\n\n");

            return "";      // return nothing
        }

        // don't close Scanner, because an error will occur

    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validatePostalCode(){
    }
    /*
    * Description: adds sum1 and sum2, then checks if the lsat digit is a 0. (multiple of 10)
    *
    * @author   Ian
    * @param    sum1 - the sum of odd placements after the credit card # is reversed
    * @param    sum2 - the sum of even placements after the credit card # is reversed and doubled
    * @return   boolean indicating if the last digit is 0
    */
    public static boolean validateCreditCard(int sum1, int sum2){
        int sum = sum1 + sum2;  // adds both parameters

        // condition: if the sum ends with 0
        if (sum % 10 == 0) {
            return true;    // credit card is valid
        }
        // if the sum doesn't end with 0
        else {
            return false;   // credit card is invalid
        }
    }
    /*
    * Description: writes all the customers' info in a csv file
    *
    * @author   Ian
    * @param    csvLine - string containing the customer's ID and all their information
    */
    public static void generateCustomerDataFile(String csvLine) throws IOException {
        // condition: if there are no customers checked in/ registered
        if (csvLine.equals("")) {
            System.out.println("\nThere are no users, can't generate file\n");
        }
        // if there are valid customers
        else {
            File outFile = new File("customerInfo.csv");    // creating a csv file

            String skip = "";
            // skips a line if the file already exists
            if (outFile.exists()) {
                skip = "\n";
            }

            FileWriter fr = new FileWriter(outFile, true);  // boolean to append
            BufferedWriter br = new BufferedWriter(fr);     // initialize buffer writer
            PrintWriter out = new PrintWriter(br);      // let printwriter access the file

            out.write(skip + csvLine);      // editing the file

            // provides the location and name of the file
            System.out.println("File located at: " + outFile.getAbsolutePath() + "\nName: customerInfo");
            System.out.println("\nDone\n");
            out.close();    // closing print writer
            br.close();     // closing buffer writer
            fr.close();     // closing file writer
        }
        
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/

    /*
    * Description: generate a random ID for each customer based on the customer's credit card #, 
    *              since everyone's credit card number is unique
    *
    * @author   Ian
    * @param    identification - String of the reversed credit card number
    * @return   the string of the first 4 digits from the reversed credit card
    */
    public static String customerID(String identification) {

        return identification.substring(0, 4) + ". ";   // the string of the ID number
        
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
    * Description: identify the odd placements of the reversed credit card #
    *
    * @author   Ian
    * @param    reversed - string containing the reversed order of the customer's credit card #
    * @return   oddNumbers - string containing all the digits in the odd placement
    */
    public static String identifyOdd(String reversed) {
        String oddNumbers = "";     // a string accumulator
        for (int i = 0; i < reversed.length(); i++) {
            String word = Character.toString(reversed.charAt(i));   // identifying digits in odd placements
            oddNumbers += word;     // accumulating the string
            i++;        // skip even placements
        }
        return oddNumbers;     // all the digits in the odd placement
        
    }
    /*
    * Description: identify the even placements of the reversed credit card #
    *
    * @author   Ian
    * @param    reversed - string containing the reversed order of the customer's credit card #
    * @return   evenNumbers - string containing all the digits in the even placement
    */
    public static String identifyEven(String reversed) {
        String evenNumbers = "";    // a string accumulator
        for (int i = 1; i < reversed.length(); i++) {
            String word = Character.toString(reversed.charAt(i));   // identifying digits in even placements
            evenNumbers += word;    // accumulating the string
            i++;        // skip odd placements
        }
        return evenNumbers;     // all the digits in the even placement
        
    }
    /*
    * Description: doubles the digits in the even placement from the reversed credit card #
    *
    * @author   Ian
    * @param    evenNumbers - string of digits in the even placement from the reversed credit card #
    * @return   combine - string of all the values of the doubled digits
    */
    public static String doubleEvenNum(String evenNumbers) {
        String combine = "";    // a string accumulator
        for (int i = 0; i < evenNumbers.length(); i++ ) {
            String list = Character.toString(evenNumbers.charAt(i));    // convert the digits from char to string
            int j = 2*Integer.parseInt(list);   // doubles and stores each digit
            combine += j;   // conbine each doubled digit without methematically adding
        }

        return combine;     // all the doubled digits
        
    }
}