
// --== CS400 File Header Information ==--
// Name: Sharada Phadnis
// Email: sphadnis@wisc.edu
// Team: your team name: MadCity Eateries
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
public class RestaurantPair<K,V> {
	private K key;
	private V value;
	//constructor
	public RestaurantPair(K key, V value) {
	this.key = key;
	this.value =value;
	}
	//setters and getters
	public void setKey(K key) {
		this.key = key;
	}
	public K getKey() {
		return key;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public V getVal() {
		return value;
	}
	

}