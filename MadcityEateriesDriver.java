// --== CS400 File Header Information ==--
// Name: Sihan Ren
// Email: sren33@wisc.edu
// Team: MC
// Role: Front End developer 1
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The MadcityEateriesDriver implemented an application for users to access the name of the 
 * restaurant they want to search.
 * 
 * @author Sihan Ren
 *
 */
public class MadcityEateriesDriver {
  private final static String WELCOME_PAGE = "~~~~~~~~~~~Welcome to MadcityEateries!~~~~~~~~~~~~";
  // The first line of the application interface design
  private final static String FINISH_PAGE =
      "~~~~~~~~~~~~ Thank you for MadcityEateries! See you next time!~~~~~~~~~~~~";
  // The end line of the application interface design 
  private static String command; // the command sentence that users will input
  private static ReadData getData; // get the data from the data wranglers and store them in the hash table 

  /**
   * The method is used to get the name of the restaurants when users use the search function
   * 
   * @param scanner scan the input commands from the users
   * @param time the current time of users based on the hour
   */
  public static void driverForUsers() {
    try {
      getData = new ReadData();
      Scanner scanner = new Scanner(System.in);
      command = "";
      System.out.println("Enter your current time to check restaurants' availablity: ");
      command = scanner.nextLine().trim();
      int time = Integer.parseInt(command);
      if (time > 22 || time < 9) {
        System.out.println("Sorry that restaurants are closed now.\n");
      }
      // users enter their time currently to check whether the restaurants are closed, we assume that
      // all the restaurants are closed during 22pm to 9am
      else {
        while (!command.contentEquals("no")) {
          System.out.println("Enter the phone numbers of the restaurant you want to search: ");
          command = scanner.nextLine().trim(); // get the key of the hashtable from the users
          if (getData.hashMap.get(command) != null) {
            System.out.println("Your restaurant name is: " + getData.hashMap.get(command));
          } // use the get() method in hashTableMap and output the correct restaurant's name
          System.out.println("Do you want to search other restaurants? [yes / no]");
          command = scanner.nextLine().trim(); // Ask users if they would like to continue searching
        }
      }


    } catch (NoSuchElementException e) {
      if (e.getMessage() != null) {
        System.out.println(e.getMessage());
        // Tell the users that the phone numbers cannot be found now
        System.out.println("Do you want to search other restaurants? [yes / no]");
        Scanner scanner = new Scanner(System.in);
        command = scanner.nextLine().trim();
        if(command.equals("yes")) {
          driverForUsers();
        }
        // if users would like to continuing searching, return to the beginning of the method
      }
    }
  }

  /**
   * Main method for the application to run the whole program
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(WELCOME_PAGE);
    driverForUsers();
    System.out.println(FINISH_PAGE);
  }
}
