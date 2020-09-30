
// --== CS400 File Header Information ==--
// Name: Karthik Devarakonda
// Email: devarakonda2@wisc.edu
// Team: MC
// Role: Data Wrangler
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: Utilized StackOverflow, Greeks for Geeks, and other CS websites/forums for help.  Also collaborated with Yi, fellow data wrangler, and Sihan, front end developer to make sure the output was satisfactory. Bounced ideas off each other and made working code that could fit easily into our other group members' projects. 
// Notes to Grader (cont.): I used this following link to help me with paths and finding directories on my machine in eclipse: https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java since I was not sure how to use the Path type object
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {

	/**
	 * constructor for the FileWriter class, calls the restaurantInfo() method
	 * anytime a new FileWriter() is created
	 */
	public FileWriter() {
		restaurantInfo();
	}

	// HashTableMap object used to transfer to back and front end
	@SuppressWarnings("rawtypes")
	public HashTableMap hashMap = new HashTableMap();

	/**
	 * this method adds the names and numbers of the restaurant in the forms of long
	 * and String into the HashTableMap object we created above
	 */
	public void restaurantInfo() {

		// String type object to store the directory of the user (guessing this is the
		// src folder...found this implementation on StackOverflow)
		String directoryPath = System.getProperty("user.dir");

		// Path type object that gets the path of the restaurants.csv file located in
		// the directory
		Path restaurantPath = Paths.get(directoryPath, "restaurants.csv");

		// using an ArrayList of type String[] to store all the restaurant names and
		// numbers as separated by the comma in the method below, convert the
		// restaurantPath into a string because that is the parameter type
		ArrayList<String[]> iterator = restaurantReader(restaurantPath.toString());

		// iterated through the array present in the csv file which is stored in the
		// ArrayList created above
		for (int i = 1; i < iterator.size(); i++) {

			// store the name of the restaurant in a String type variable and use the built
			// in substring method to get rid of the quotes around the name of the
			// restaurant
			String restaurantName = iterator.get(i)[0].substring(1, (iterator.get(i)[0].length() - 1));

			// store the number of the restaurant in a String type variable and use the
			// built
			// in substring method to get rid of the quotes around the number of the
			// restaurant
			String restaurantNumberString = iterator.get(i)[1].substring(1, (iterator.get(i)[1].length() - 1));

			// since we are using this app in a specific location we negated the area code
			// as it made it easier to use the hashCode for the rest of the numbers
			restaurantNumberString = restaurantNumberString.substring(3);

			// stored the String object for the number in a long type variable because there
			// was too much data for an int and because it reduced redundancies later on
			// with converting the String into a series of numbers. Used parseLong, built in
			// method for long to convert
			long restaurantNumber = Long.parseLong(restaurantNumberString);

			// put method from the HashTableMap class to add the data into a hashTable
			hashMap.put(restaurantNumber, restaurantName);
		}
	}

	/**
	 * reads the csv file we have input using a file reader and a buffered reader
	 * and loops through it using a while loop and splits both columns at where
	 * there is a comma separating the name and number
	 * 
	 * @param restaurantCsv String type object that represents the file we are using
	 * @return returns an ArrayList of type String that holds the names and numbers
	 *         of the restaurants
	 * @throws IOException           in case there is an error with reading the file
	 *                               with the buffered reader or file reader
	 * @throws FileNotFoundException in case there is no file to be found that needs
	 *                               to be read
	 */
	public static ArrayList<String[]> restaurantReader(String restaurantCsv) {

		// the ArrayList object of type String that will be added to and returned once
		// all the names and numbers are split properly
		ArrayList<String[]> toReturn = new ArrayList<String[]>();

		// try catch block for the IOException and the FileNotFoundException that were
		// stated earlier
		try {

			// String type array that is used to add to the ArrayList of type String[]
			String[] restaurantInfo;

			// empty String variable that will later be used to store the line given by the
			// buffered reader and will be used to split the two by a comma
			String reader = "";

			// File type variable to hold the info of the restaurants from the csv file
			File restaurants = new File(restaurantCsv);

			// FileReader type variable that holds the file with the csv info in it
			FileReader fileReader = new FileReader(restaurants);

			// BufferedReader type variable used to read the FileReader type variable
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// While loop to iterate as long as the line to read is not null meaning there
			// are still restaurants to look at in the csv file
			while ((reader = bufferedReader.readLine()) != null) {

				// the String[] variable now holds both name and number based on which side of
				// the comma they are on using the split function
				restaurantInfo = reader.split(",");

				// now the Array List of type String[] is added to, the String[] array that
				// contains the name and number is added into it
				toReturn.add(restaurantInfo);
			}

			// the bufferedReader is closed
			bufferedReader.close();

		}

		// if there are any FileNotFoundExceptions -- could only really come from the
		// File restaurants variable -- they will be caught here and a message will
		// print
		catch (FileNotFoundException e) {
			System.out.print("File not found");
		}

		// if there are any IOExceptions they will be caught here and a message will
		// print
		catch (IOException e) {
			System.out.print("Input or Output Exception");
		}

		// The ArrayList of type String[] will be returned here
		return toReturn;

	}
}
