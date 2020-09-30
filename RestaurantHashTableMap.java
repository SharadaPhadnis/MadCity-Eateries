// --== CS400 File Header Information ==--
// Name: Sharada Phadnis
// Email: sphadnis@wisc.edu
// Team: your team name: MadCity Eateries
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class RestaurantHashTableMap<K, V> extends HashTableMap {
	private int capacity;
	private int size;
	private LinkedList<RestaurantPair>[] list;
	
	//default constructor
	public RestaurantHashTableMap() {
		capacity = 10;
		size = 0;
		list = new LinkedList[10];
	}
	//non default constructor
	public RestaurantHashTableMap(int capacity) {
		this.capacity = capacity;
		size = 0;
		list = new LinkedList[capacity];
	}
	
		
	/**
	 * return a string contains all restaurants info
	 * 
	 * @return String 
	 */
	public java.lang.String allRestaurants() {
		java.lang.String toReturn =  "";
		
		for(int i=0; i<capacity; i++) {
			System.out.println("hi");
			if(list[i] != null) {
				//traversing through the linkedlists
				for(int j=0; j<list[i].size(); j++) {
					toReturn = toReturn + list[i].get(j).getVal() + "\n";
				}
			}
		}
		
		return toReturn;
		
	}
}
