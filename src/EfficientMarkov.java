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
			String key_ = myText.substring(i, i+myOrder);	// note: .substring() truncates at i+myOrder so value at i+myOrder is not included in substring

			//String value = PSEUDO_EOS;
			//String value = "";

			//if(i+myOrder == myText.length()){	// if the characters are the last characters of the string they will have no following characters
			//	String value = PSEUDO_EOS;
			//} else {
			myMap.putIfAbsent(key_, new ArrayList<String>());

			//if(i+myOrder+1 < myText.length()){
			if(i+myOrder == myText.length()){
				myMap.get(key_).add(PSEUDO_EOS);
			} else {
				String value = myText.substring(i + myOrder /*+ 1*/, i + myOrder + 1/*2*/); // get the letter that occurs next
				myMap.get(key_).add(value);
			}

			//myMap.putIfAbsent(key_, new ArrayList<String>());
			//ArrayList<String> current_value = myMap.get(key_);
			//current_value.add(value);
			//myMap.put(key, myMap.get(key).add(value));
			//myMap.put(key_, current_value);
			//System.out.println(myMap);
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
