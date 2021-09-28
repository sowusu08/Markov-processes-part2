import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
    //private Map<String,ArrayList<String>> myMap;
    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov(){
        this(3);
    }

    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<>();
    }

    //@Override
    //public void setTraining(String text){
    //    myWords = text.split("\\s+");
    //}

    @Override
    public void setTraining(String text) {
        //super.setTraining(text);
        myWords = text.split("\\s+");
        myMap.clear();

        for(int i=0; i < myWords.length - myOrder + 1; i++){
            // loop through my words array
            // make each word a wordgram object with myOrder number of words and set it as the key
            WordGram key = new WordGram(myWords,i,myOrder);

            String value = PSEUDO_EOS;
            // get the next word following word gram and set to value
            if(i+myOrder < myWords.length){
                value = myWords[i + myOrder]; // get the word that occurs next
            }
            myMap.putIfAbsent(key, new ArrayList<String>());
            ArrayList<String> current_value = myMap.get(key);
            current_value.add(value);
            myMap.put(key, current_value);
        }
    }
}
