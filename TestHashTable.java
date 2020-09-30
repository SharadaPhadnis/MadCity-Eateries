// --== CS400 File Header Information ==--
// Name: <Robert Schultz>
// Email: <rlschultz5@wisc.edu>
// Team: <MC>
// TA: <Harit>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;

public class TestHashTable {

    /**
     * This test uses the put and get method.
     * @return true if all tests pass
     */
    public static boolean test1() {
        RestaurantHashTableMap<String,String> trial1 = new RestaurantHashTableMap();
        if(trial1.size() != 0){
            return false;
        }
        trial1.put("Hello", "World");
        trial1.put("Hi","Back");
        if((trial1.size() != 2) || (!trial1.get("Hi").equals("Back")) || !trial1.get("Hello").equals("World")){
            return false;
        }
        int exceptionCount = 0;
        try {
            trial1.get("Nope");
        } catch (NoSuchElementException e) {
            exceptionCount++;
        }
        if(exceptionCount != 1){
            return false;
        }
        return true;
    }

    /**
     * This test uses the remove method.
     * @return true if all tests pass
     */
    public static boolean test2() {
    	RestaurantHashTableMap<String,String> trial2 = new RestaurantHashTableMap();
        trial2.put("Hello", "World");
        trial2.put("Hi","Back");
        if(!(trial2.remove("Hi").equals("Back")) || (trial2.size() != 1)){
            return false;
        }
        if(!(trial2.remove("Hello").equals("World")) || (trial2.size() != 0)){
            return false;
        }
        if((trial2.remove("Kurt") != null) || (trial2.size() != 0)){
            return false;
        }
        return true;
    }

    /**
     * This test adds colliding keys.
     * @return true if all tests pass
     */
    public static boolean test3() {
    	RestaurantHashTableMap<String,String> trial3 = new RestaurantHashTableMap();
        trial3.put("Hello", "World");
        trial3.put("Hi","Back");
        trial3.put("Hi","Again");

        if(!(trial3.get("Hi").equals("Back")) || (trial3.size() != 2) || (trial3.put("Hi", "Again"))){
            return false;
        }
        return true;
    }

    /**
     * This test adds colliding hashcode and clear.
     * @return true if all tests pass
     */
    public static boolean test4() {
    	RestaurantHashTableMap<String,String> trial4 = new RestaurantHashTableMap();
        trial4.put("FB","Football");
        trial4.put("Ea","Sports");

        if(!(trial4.get("FB").equals("Football")) || !(trial4.get("Ea").equals("Sports")) || (trial4.size() != 2)){
            return false;
        }
        //clear test on a non-empty array
        trial4.clear();
        if(trial4.size() != 0){
            return false;
        }
        //clear test on an empty array
        trial4.clear();
        if(trial4.size() != 0){
            return false;
        }
        return true;
    }

    /**
     * This test the grow and containsKey methods
     * @return true if all tests pass
     */
    public static boolean test5() {
    	RestaurantHashTableMap<String,String> trial5 = new RestaurantHashTableMap(3);
        trial5.put("A", "a");
        trial5.put("B","b");
        trial5.put("C","c");
        trial5.put("D", "d");
        trial5.put("E","e");

        //testing to make sure grow works but putting 5 objects in a 3-capacity array
        if(trial5.size() != 5){
            return false;
        }
        //testing containsKey on a valid and invalid key
        if((!trial5.containsKey("D")) || (trial5.containsKey("Z"))){
            return false;
        }
        return true;
    }

    /**
     * Testing after using ReadData Class to import data from csv file
     * @return true if all tests pass, otherwise false and prints test that failed.
     */
    public static boolean csvFileTests() {
    	RestaurantHashTableMap<String,String> csvHashMap = new ReadData().restaurantHashMap;
        //1. Tests containsKey for 1st, last and random keys in file
        if(!(csvHashMap.containsKey("6081362543")) || !(csvHashMap.containsKey("6081377415")) || !(csvHashMap.containsKey("6089215392"))){
            System.out.println("CSVFileTests 1 Failed.");
            return false;
        }
        //2. Tests containsKey for non-matching key
        if(csvHashMap.containsKey("23")){
            System.out.println("CSVFileTests 2 Failed.");
            return false;
        }
        //3. Tests size
        if(csvHashMap.size() != 50){
            System.out.println("CSVFileTests 3 Failed.");
            return false;
        }
        //4. Tests get
        if(!(csvHashMap.get("6081362543").equals("Freddy's Seafood")) || !(csvHashMap.get("6087693961").equals("Meat U There")) || !(csvHashMap.get("6089215392").equals("Atomic Sushi"))){
            System.out.println("CSVFileTests 4 Failed.");
            return false;
        }
        //5. Tests remove
        if(!csvHashMap.remove("6081362543").equals("Freddy's Seafood") || (csvHashMap.size() != 49)){
            System.out.println("CSVFileTests 5 Failed.");
            return false;
        }
        if(!csvHashMap.remove("6087693961").equals("Meat U There") || (csvHashMap.size() != 48)){
            System.out.println("CSVFileTests 5 Failed.");
            return false;
        }
        if(!csvHashMap.remove("6089215392").equals("Atomic Sushi") || (csvHashMap.size() != 47)){
            System.out.println("CSVFileTests 5 Failed.");
            return false;
        }
        //6. Tests colliding keys
        if(csvHashMap.put("6084285431", "Duplicate")){
            System.out.println("CSVFileTests 6 Failed.");
            return false;
        }
        //7. Tests collisions
        if(!(csvHashMap.get("6085638293").equals("BBQ Express")) || !(csvHashMap.get("6089516413").equals("Flavoroso"))){
            System.out.println("CSVFileTests 7 Failed.");
            return false;
        }
        //8. Tests clear method
        csvHashMap.clear();
        if((csvHashMap.size() != 0) || csvHashMap.containsKey("6089516413")){
            System.out.println("CSVFileTests 8 Failed.");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Test 1      : " + test1());
        System.out.println("Test 2      : " + test2());
        System.out.println("Test 3      : " + test3());
        System.out.println("Test 4      : " + test4());
        System.out.println("Test 5      : " + test5());
        System.out.println("csvFileTests: " + csvFileTests());
    }
}
