import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		this(3);
	}

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();

		for(int i=0; i < myText.length() - myOrder + 1; i++){
			String key = myText.substring(i, i+myOrder);	// note: .substring() truncates at i+myOrder so value at i+myOrder is not included in substring

			String value = PSEUDO_EOS;

			//if(i+myOrder == myText.length()){	// if the characters are the last characters of the string they will have no following characters
			//	String value = PSEUDO_EOS;
			//} else {
			if(i+myOrder+1 < myText.length()){
				value = myText.substring(i + myOrder /*+ 1*/, i + myOrder + 1/*2*/); // get the letter that occurs next
			}
			myMap.putIfAbsent(key, new ArrayList<String>());
			ArrayList<String> current_value = myMap.get(key);
			current_value.add(value);
			//myMap.put(key, myMap.get(key).add(value));
			myMap.put(key, current_value);
		}
	}
	@Override
	public ArrayList<String> getFollows(String key) {
		if(myMap.containsKey(key)){
			return myMap.get(key);
		} else {
			throw new NoSuchElementException(key+" not in map");
		}
		//return null;
	}
}	
