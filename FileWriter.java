// --== CS400 File Header Information ==--
// Name: Karthik Devarakonda
// Email: devarakonda2@wisc.edu
// Team: MC
// Role: Data Wrangler
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: Utilized StackOverflow, Greeks for Geeks, and other CS websites/forums for help. Did not copy code but used it as a template/skeleton. Also collaborated a lot with Yi, fellow data wrangler. Bounced ideas off each other and made similar working code that could fit easily into our other group members' projects. 
// Notes to Grader (cont.): I used this following link to help me with paths and finding directories on my machine in eclipse: https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileWriter {
	
	//HashTableMap object with Long and String generic types because those are the two types of variables we will be storing the name and number with
	static HashTableMap<String, String> hashMap = new HashTableMap<String, String>();
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		
		//The current location of the user in the path is stored in a string here 
		 String currentDirectory = System.getProperty("user.dir");
		 
		 //That location and the location of the csv file we need to use are stored in a Path type variable
		 Path filePath = Paths.get(currentDirectory, "restaurants.csv");
		 
		 //We create a mock ArrayList variable of type String[] and use the method created below to iterate through the csv file after we have found its location in our machine using the path variables
		 ArrayList<String[]> iterator = FileWriter.restaurantReader(filePath.toString());
		 
		 //then a for loop loops through the ArrayList of type String[] and then properly formats the name and number of the restaurant to be ready for the backend usage and then adds it to the HashTableMap object which can then be used by the other roles in the project
		 for(int i = 0; i < iterator.size(); i++) {
			 
			 //because both the names and numbers had quotes around them that we no longer wanted
			 String restaurantName = iterator.get(i)[0].replace('"', ' ');
			 
			 //gets rid of unnecessary spaces
			 restaurantName = restaurantName.trim();
			 
			 //because both the names and numbers had quotes around them that we no longer wanted
			 String restaurantNumber = iterator.get(i)[1].replace('"', ' ');
			 
			//gets rid of unnecessary spaces
			 restaurantNumber = restaurantNumber.trim();
			 
			 //because the numbers were too big to store in a variable so we got rid of the area code
			 restaurantNumber = restaurantNumber.substring(3);
			 
			 //used our generic put method to add it to the hashMap
			 hashMap.put(restaurantNumber, restaurantName);
		 }
	}
	
	/**
	 * reads the csv file we have input using a file reader and a buffered reader and loops through it using a while loop and splits both columns at where there is a comma separating the name and number
	 * @param restaurantFile
	 * @return returns an ArrayList of type String that holds the names and numbers of the restaurants
	 * @throws IOException in case there is an error with reading the file with the buffered reader or file reader
	 * @throws FileNotFoundException in case there is no file to be found that needs to be read
	 */
	public static ArrayList<String[]> restaurantReader(String restaurantFile) throws IOException, FileNotFoundException {
		
		//the ArrayList object of type String that will be added to and returned once all the names and numbers are split properly
		ArrayList<String[]> toReturn = new ArrayList<String[]>();

		//try catch block for the IOException and the FileNotFoundException that were stated earlier
		try {
			
		//String type array that is used to add to the ArrayList of type String[]
		String[] restaurantInfo;
		
		//empty String variable that will later be used to store the line given by the buffered reader and will be used to split the two by a comma
		String reader = "";
		
		//File type variable to hold the info of the restaurants from the csv file
		File restaurants = new File("restaurants.csv");
		
		//FileReader type variable that holds the file with the csv info in it
		FileReader fileReader = new FileReader(restaurants);
					
		//BufferedReader used to read the FileReader type variable
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//While loop to iterate as long as the line to read is not null meaning there are still restaurants to look at in the csv file
		while(bufferedReader.readLine()!= null) {
			
			//the String type variable reader now holds the line given/read by the bufferedReader
			reader = bufferedReader.readLine();
			
			//the String[] variable now holds both name and number based on which side of the comma they are on
			restaurantInfo = reader.split(",");
			
			//now the Array List of type String[] is added to, the String[] array that contains the name and number is added into it
			toReturn.add(restaurantInfo);
				
		}
		
		//the bufferedReader is closed
		bufferedReader.close();
		
		}
		
		//if there are any FileNotFoundExceptions -- could only really come from the File restaurants variable -- they will be caught here and a message will print
		catch(FileNotFoundException e) {
			System.out.print("File not found");
		}
		
		//If there are any IOExceptions they will be caught here and a message will be printed
		catch(IOException e) {
			System.out.print("Input or Outpout did not work");
		}
		
		//The ArrayList of type String[] will be returned here
		return toReturn;
		}
}
	




