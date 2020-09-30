// --== CS400 File Header Information ==--
// Name: Yi Xiao
// Email: yxiao84@wisc.edu
// Team: MC
// Role: Data Wrangler 1
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: I learned how to read csv file through the GitHub: https://gist.github.com/timothyshort/e17dda748b4b3ab83cb135589037f786

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class used to read the csv file (our database), and store data through put method from HashTableMap class
 * to a hash table.
 * 
 * @author yixiao
 *
 */
public class ReadData {	
	public RestaurantHashTableMap restaurantHashMap = new RestaurantHashTableMap(); // create a new object of HashTableMap used to store all data
	public final String delimiter = ",";
	
	/**
	 * Class constructor with storing are data info from our data file "restaurants.csv" into the hashTableMap hashMap
	 */
	public ReadData() {
		this.storeData();
	}
	
	/**
	 * Returns a list of String array, each array contains one restaurant's info (name and phone number)
	 * 
	 * @param csvFile -- the file stored all data
	 * 
	 * @return a List of String Array
	 */
	public List<String[]> read(String csvFile) {
		// create a new array list (list of String[])
		List<String[]> data = new ArrayList<String[]>();
		try {
			// open and read the file
			File file = new File(csvFile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String[] tempArr;
			// read data as long as it is not empty
			// parse the data by comma using .split() method
			// place into a temporary array, then add to list
			
			while((line = br.readLine()) != null) {
				tempArr = line.split(delimiter);
				data.add(tempArr);
			}
			br.close();
		}catch(FileNotFoundException e) {
			System.out.println("ERROR: File not found " + csvFile);
		}catch(IOException e) {
			System.out.println("ERROR Could not read " + csvFile);
		}
		return data;
	}
	
	/**
	 * Help to store all restaurants info into the hashMap
	 *  
	 */
	public void storeData() {
		// find the absolute path to the data file
		Path pathingToDic = Path.of(System.getProperty("user.dir"));
		Path filePath = Paths.get(pathingToDic.toString(), "restaurants.csv");
		String csvFile = filePath.toString();
		List<String[]> restaurants = read(csvFile);
		// use a for loop to go through all restaurants' info, and store them into a hash table
		for (int i = 1; i < restaurants.size(); i++) {
			// get the name of restaurant
			String name = restaurants.get(i)[0].replace('"', ' ');
			// use .trim() function to delete unwanted white space
			name = name.trim();
			// get the number of restaurant
			String number = restaurants.get(i)[1].replace('"', ' ');
			// use .trim() to delete unwanted white space
			number = number.trim();
			// use put method to store the restaurant into the hash table
			restaurantHashMap.put(number, name);
		}	
	}
		
		
}
