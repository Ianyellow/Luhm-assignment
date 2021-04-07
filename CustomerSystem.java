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
        String postThis = "";   // global variable that will store the customer's info
        int id = 11;        // can be any number, used as the parameter

        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                
                postThis += enterCustomerInfo(id);      // storing the content from the method
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                
                // AAAAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaaaaaaaaDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
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
    * @param    idNum - an integer valued 11, used for generating a random customer ID 
    * @return   String customerInfo - customer's ID, first/last name, city, postal code and credit card # with commas
    */
    public static String enterCustomerInfo(int idNum) {
        Scanner reader = new Scanner(System.in);
        String comma = ", ";        // A string representing a comma

        // prompt user inputs
        System.out.println("What is your first name?");
        String fName = reader.nextLine();
        System.out.println("\nWhat is your last name?");
        String lName = reader.nextLine();
        System.out.println("\nWhat city do you live in?");
        String place = reader.nextLine();

        // make this into a method !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // call postalCode method
        String postCode;
        do {
            System.out.println("\nWhat is the postal code? (Please enter at least 3 characters)");
            postCode = reader.nextLine();
        } while (postCode.length() < 3);
        
        // make this into a method !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // call ccNum method
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
        String customerInfo = fName + comma + lName + comma + place + comma + postCode + comma + creditCardNum + "\n";


        // validation happens here
        // call validatePostalCode method and validateCreditCard method
        // condition: if the postal code and credit card are valid
        if (validatePostalCode(postCode) > 0 && validateCreditCard( sum1, sum2 ) == true) {
            System.out.println("\n\n");     // print empty lines
            return customerID(idNum) + customerInfo;    // unique ID # plus all the customer's info
        }
        // condition: if the credit card in invalid
        else if (validateCreditCard( sum1, sum2 ) == false) {
            System.out.println("The credit card number is invalid\n\n");
            return "";      // return nothing
            // re input????????
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
    public static void generateCustomerDataFile(String csvLine) throws FileNotFoundException {
        // condition: if there are no customers checked in/ registered
        if (csvLine.equals("")) {
            System.out.println("\nThere are no users, can't generate file\n");
        }
        // if there are valid customers
        else {
            System.out.println("\nThis is printed: " + csvLine + "\n");

            File outFile = new File("customerInfo.csv");    // creating a csv file
            PrintWriter out = new PrintWriter(outFile);     // let printwriter access the file
            out.write(csvLine);         // editing the file
    
            System.out.println("Done");

            out.close(); // closing print writer
        }
        
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/

    /*
    * Description: generate a random ID for each customer
    *
    * @author   Ian
    * @param    identification - integer valued 11
    * @return   the string of the sum of 2 random numbers which one is the product of 11 (param)
    */
    public static String customerID(int identification) {
        Random rand = new Random();         // initializing random number generator
        int randNum2 = rand.nextInt(100);   // randome number within 100
        int randNum1 = rand.nextInt(100)*identification;    // randome number within 100 times 11

        return Integer.toString(randNum1 + randNum2) + ". ";    // the string of the sum
        
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