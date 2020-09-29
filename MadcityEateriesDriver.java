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

public class MadcityEateriesDriver {
  private final static String WELCOME_PAGE = "~~~~~~~~~~~Welcome to MadcityEateries!~~~~~~~~~~~~";
  private final static String FINISH_PAGE =
      "~~~~~~~~~~~~ Thank you for MadcityEateries! See you next time!~~~~~~~~~~~~";
  private static String command;
  public static void driverForUsers() {
    try {
      ReadData getData = new ReadData();
      //getData.storeData();
      Scanner scanner = new Scanner(System.in);
      command = "";
      while (!command.equals("no")) {
        System.out.println("Enter your current time to check restaurants' availablity: ");
        command = scanner.nextLine().trim();
        int time = Integer.parseInt(command);
        if (time > 22) {
          System.out.println("Sorry that restaurants are closed now.\n");
        } else {
          System.out.println("Enter the phone numbers of the restaurant you want to search: ");
          command = scanner.nextLine().trim();
          long inputNumber = Long.parseLong(command);
          System.out.println("!: "+inputNumber);
          System.out.println("get: "+ getData.hashMap.get(inputNumber));
          if (getData.hashMap.get(inputNumber) != null) {
            System.out.println("Your restaurant name is: " + getData.hashMap.get(inputNumber));
          }
        }
        System.out.println("Do you want to search other restaurants? [yes / no]");
        command = scanner.nextLine().trim();
      }
    } catch (NoSuchElementException e) {
      if (e.getMessage() != null && e.getMessage().toLowerCase().contains("no such element")) {
        System.out.println(e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(WELCOME_PAGE);
    driverForUsers();
    System.out.println(FINISH_PAGE);
  }
}
