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

            String value = "";
            // get the next word following word gram and set to value
            if(i+myOrder == myWords.length){
                value = PSEUDO_EOS;
            } else {
                value = myWords[i + myOrder]; // get the word that occurs next
            }
            myMap.putIfAbsent(key, new ArrayList<String>());
            ArrayList<String> current_value = myMap.get(key);
            current_value.add(value);
            myMap.put(key, current_value);
        }
    }

    @Override
    public ArrayList<String> getFollows(WordGram kGram) {

        int pos = 0;
        ArrayList<String> follows = new ArrayList<String>();
        while (true) {
            int index = indexOf(myWords,kGram,pos);
            if (index == -1) {
                break;
            }
            int start = index + kGram.length();
            if (start >= myWords.length) {
                follows.add(PSEUDO_EOS);
                break;
            }

            follows.add(myWords[start]);
            pos = index+1;
        }
        return follows;
    }
}
