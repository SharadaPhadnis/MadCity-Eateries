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
        HashTableMap<String,String> trial1 = new HashTableMap();
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
        HashTableMap<String,String> trial2 = new HashTableMap();
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
        HashTableMap<String,String> trial3 = new HashTableMap();
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
        HashTableMap<String,String> trial4 = new HashTableMap();
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
     * This test the grow, containsKey methods, and getIndex.
     * @return true if all tests pass
     */
    public static boolean test5() {
        HashTableMap<String,String> trial5 = new HashTableMap(3);
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
        //testing to check getIndex and array length after growth
        if(("D".hashCode() % 6) != trial5.getIndex("D")){
            return false;
        }
        return true;
    }

    /**
     * Testing after using ReadData Class to import data from csv file
     * @return true if all tests pass, otherwise false and prints test that failed.
     */
    public static boolean csvFileTests() {
        HashTableMap<String,String> csvHashMap = new ReadData().hashMap;
        //1. Tests containsKey for 1st, last and random keys in file
        if(!(csvHashMap.containsKey("1362543")) || !(csvHashMap.containsKey("1377415")) || !(csvHashMap.containsKey("9215392"))){
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
        if(!(csvHashMap.get("1362543").equals("Freddy's Seafood")) || !(csvHashMap.get("7693961").equals("Meat U There")) || !(csvHashMap.get("9215392").equals("Atomic Sushi"))){
            System.out.println("CSVFileTests 4 Failed.");
            return false;
        }
        //5. Tests remove
        if(!csvHashMap.remove("1362543").equals("Freddy's Seafood") || (csvHashMap.size() != 49)){
            System.out.println("CSVFileTests 5 Failed.");
            return false;
        }
        if(!csvHashMap.remove("7693961").equals("Meat U There") || (csvHashMap.size() != 48)){
            System.out.println("CSVFileTests 5 Failed.");
            return false;
        }
        if(!csvHashMap.remove("9215392").equals("Atomic Sushi") || (csvHashMap.size() != 47)){
            System.out.println("CSVFileTests 5 Failed.");
            return false;
        }
        //6. Tests colliding keys
        if(csvHashMap.put("4285431", "Duplicate")){
            System.out.println("CSVFileTests 6 Failed.");
            return false;
        }
        //7. Tests collisions and getIndex
        if(!(csvHashMap.get("5638293").equals("BBQ Express")) || !(csvHashMap.get("9516413").equals("Flavoroso"))){
            System.out.println("CSVFileTests 7 Failed.");
            return false;
        }
        if((csvHashMap.getIndex("9516413") != csvHashMap.getIndex("5638293"))){
            System.out.println("CSVFileTests 7 Failed.");
            return false;
        }
        //8. Tests clear method
        csvHashMap.clear();
        if((csvHashMap.size() != 0) || csvHashMap.containsKey("9516413")){
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
