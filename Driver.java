import java.util.NoSuchElementException;
import java.util.Scanner;

// --== CS400 File Header Information ==--
// Name: John Shields
// Email: jfshields@wisc.edu
// Team: MC
// Role: Front End Developer 2
// TA: Harit
// Lecturer: Florian Heimerl
public class Driver {
  
  
  public static void main(String[] args) {
    HashTableMap<String,String> csvHashMap = new ReadData().hashMap;
    System.out.println("Welcome to MadCity-Eateries");
    Scanner scan = new Scanner(System.in);
    String key = "";
    String value = "";
    while(true) {
      System.out.println("Please enter a to add a phone number, l to lookup a phone number, or q to quit");
      key = scan.nextLine();
      if( key.equals("q")) {
        break;
      }
      if( key.equals("a")) {
        System.out.println("Please enter the name of the restaurant");
        value = scan.nextLine();
        System.out.println("Please enter the Phone number of the restaurant");
        key = scan.nextLine();
        csvHashMap.put(key, value);
        continue;
      }
      if( key.equals("l")) {
        try {
          System.out.println(csvHashMap.get(key));
        }catch(NoSuchElementException e) {
          System.out.println("That is not a phone number in our record please enter another ohone number.");
        }
        continue;
      //System.out.println(key);
      }
     System.out.println("Invalid Key: Please see below for examples of valid keys.");
    
    
  }
  

}
}
