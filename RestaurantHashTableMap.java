// --== CS400 File Header Information ==--
// Name: Sharada Phadnis
// Email: sphadnis@wisc.edu
// Team: your team name: MadCity Eateries
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class RestaurantHashTableMap<Long, String> implements RestaurantMapADT<Long, String> {
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
	@Override
	
	
	/**
	 * Adds an Key-Value Pair to the list if key doesn't already exist
	 * Hashes the key and adds key-value to the hashed index value
	 * rehashes and grows the array if the capacity is more than 80% filled
	 * @param key and value
	 * @return true if key-value pair was sucessfully added and false otherwise
	 */
	public boolean put(Long key, String value) {
		
		if(containsKey(key)) {
			return false;
		}
		if(key !=null && value !=null) {
		int index = key.hashCode();
		if(index < 0) {
			index = (index * -1) % capacity;
		}
		else {
			index = index % capacity;
		}
		//checking for growing and rehashing array
		/**if((double)size/(double)capacity >= 0.80) {
		  growAndRehash();
		}**/
		
		
		//System.out.println(index);
		// if key already exists
		//System.out.println(containsKey(key));
		//if(containsKey(key)) {
		//	return false;
		//}
		
		//if no key-value pair found at index
		if(list[index] == null) {
		list[index] = new LinkedList<RestaurantPair>();
		list[index].add(new RestaurantPair(key,value));
		size++;
		}
		else {
			
			list[index].add(new RestaurantPair(key,value));
			size++;
			
		}
		if((double)size/(double)capacity >= 0.80) {
			  growAndRehash();
		}
		
		return true;
		
		}
		
		return false;
	}
	
	/**
	 * grows and adds all hey-value pairs to the map of increased size if
	 * array is more than 80% filles 
	 * @return void
	 */
	private void growAndRehash(){
		capacity = capacity * 2;
		LinkedList<RestaurantPair>[] myList;
		myList = new LinkedList[capacity];
		for(int i=0; i<size; i++) {
			if(list[i] != null) {
				//traversing through the linkedlist at each point index of list[i]
				for(int j=0; j<list[i].size(); j++) {
					Long keyToHash = (Long)list[i].get(j).getKey();
					String value = (String)list[i].get(j).getVal();
					int indexHashed = keyToHash.hashCode();
					indexHashed = (int) (Math.abs(indexHashed) % capacity);
					if(myList[indexHashed] == null) {
						myList[indexHashed] = new LinkedList<RestaurantPair>();
						myList[indexHashed].add(new RestaurantPair(keyToHash, value));
					}
					else {
						myList[indexHashed].add(new RestaurantPair(keyToHash, value));
					}
					
				}
			}
			list = myList;
		}
		
		
	}
	@Override
	/**
	 * finds the value of the key that is passed
	 * @param key- to find the value from the key-value pair.
	 * @return V- the value from the key-value pair
	 * @throws NoSuchElementException if the key does not exist in the 
	 * array
	 */
	public String get(Long key) throws NoSuchElementException {
		if(!containsKey(key)) {
			throw new NoSuchElementException("Key not found!");
		}
		//traversing through the array
		for(int i=0; i<capacity; i++) {
			if(list[i] != null) {
				//traverse through the linked list
				for(int j=0; j<list[i].size(); j++) {
					if(list[i].get(j).getKey().equals(key)) {
						return (String) list[i].get(j).getVal();
					}
				}
			}
			
		}
		return null;
	}
	@Override
	/**
	 * returns the number of elements(key-value pairs) in the list
	 * @return the number of elements in the map
	 */
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	@Override
	public boolean containsKey(Long key) {
		/**for(int i=0; i<list.length; i++) {
			if(list[i]!= null && list[i].contains(key)) {
				return true;
			}
		}
		
		return false;**/
		
		for(int i=0; i<capacity; i++) {
			if(list[i] != null) {
			int linkedListSize = list[i].size();
			//traversing through the linkedlist
			for(int j=0; j<linkedListSize; j++) {
				//getting the pair object in the node
				RestaurantPair tempPair = list[i].get(j);
				if(tempPair.getKey().equals(key)) {
					return true;
				}
			}
		}
		}
		return false;
	}
	@Override
	/**
	 * Removes and returns the value from the given key in the key-value pair.
	 * Check if the key exists in the map
	 * returns V- the value associated with the key to be removed
	 * null - if the key does not exist in the list
	 */
	public String remove(Long key) {
		if(!containsKey(key)) {
			return null;
		}
		//traversing through the array 
		for(int i=0; i<capacity; i++) {
			if(list[i] != null) {
				//traversing through the linkedlist
				for(int j=0; j<list[i].size();j++) {
					if(list[i].get(j).getKey().equals(key)) {
						RestaurantPair toRemove = list[i].remove(j);
						size--;
						return (String) toRemove.getVal();
				
					}
				}
			}
		}
		return null;
	}
	@Override
	/**
	 * removes all the key-value pairs from the list adn changes the size to 0
	 * @return void
	 */
	public void clear() {
		// TODO Auto-generated method stub
		list = new LinkedList[capacity];
		size =0;
		
		
	}
	
	public java.lang.String allRestaurants() {
		java.lang.String toReturn =  "";
		
		for(int i=0; i<capacity; i++) {
			if(list[i] != null) {
				//traversing through the linkedlists
				for(int j=0; j<list[i].size(); j++) {
					toReturn = toReturn + list[i].get(j).getVal() + "\n";
				}
			}
		}
		System.out.println(capacity);
		return toReturn;
		
	}
}
