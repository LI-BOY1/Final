import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class TrieNode2{
    String ch;
    Map<String, TrieNode2> children ;
    Map<String, Integer> two_word_children ;

    public TrieNode2(){
        children = new HashMap<>();
    }

    public TrieNode2(String string,int number){
        two_word_children = new HashMap<String,Integer>();
        two_word_children.put(string,number);
    }
}

public class Trie2 extends Trie{

    TrieNode2 root;
    int word_number = 0;

    @Override
    public void add(String word){
        int bitNumber = 3;
        int remainder = word.length()%bitNumber;
        int quotient = word.length()/bitNumber;
        if(root == null){
            root  = new TrieNode2();
        }
        word_number++;
        if(quotient == 0){ // when quotient is equal to 0, we know that the word length is less than 3, so put it into a special hash
            TrieNode2 temp = new TrieNode2(word,word_number);
            if(!root.children.containsKey("word length is less than 3")){
                root.children.put("word length is less than 3",new TrieNode2(word,word_number));
            }else{
                root.children.get("word length is less than 3").two_word_children.put(word,word_number);
            }
        }else{
            if(remainder == 0){
                TrieNode2 temp = new TrieNode2();     // we need a  point to follow the iteration level to keep track of the level where we are now
                temp = root;                      // each time we add a new word, we will start from the root
                int iteration_number = 0;
                for(int i = 0; i < quotient; i++){
                    String input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber);
                    if(!temp.children.containsKey(input)){  // if the map does not have the string value
                        temp.children.put(input,temp = new TrieNode2());
                    }else{
                        while(temp.children.containsKey(input)){       // if it's true,say input exists and the value which is the trienode2 also exists,so temp can be assigned as new TrieNode2
                            temp = temp.children.get(input);
                            i++;
                            if(i>=quotient){break;}
                            input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber); // if i is larger than quotient, so it will go out of the boundary of the word length
                        }
                        if(i<quotient){
                            temp.children.put(input,temp = new TrieNode2());
                        }
                    }
                }
            }else{
                TrieNode2 temp = new TrieNode2();     // we need a  point to follow the iteration level to keep track of the level where we are now
                temp = root;                      // each time we add a new word, we will start from the root
                int iteration_number = 0;
                for(int i = 0; i < quotient; i++){
                    String input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber);
                    if(!temp.children.containsKey(input)){  // if the map does not have the string value
                        temp.children.put(input,temp = new TrieNode2());
                        iteration_number++;
                    }else{
                        while(temp.children.containsKey(input)){       // if it's true,say input exists and the value which is the trienode2 also exists,so temp can be assigned as new TrieNode2
                            temp = temp.children.get(input);
                            i++;
                            iteration_number++;
                            if(i>=quotient){break;}
                            input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber); // if i is larger than quotient, so it will go out of the boundary of the word length
                        }
                        if(i<quotient){
                            temp.children.put(input,temp = new TrieNode2());
                            iteration_number++;
                        }
                    }
                }
                int left_length = word.length() - iteration_number*bitNumber;
                String input = (String)word.subSequence(iteration_number*bitNumber,iteration_number*bitNumber + left_length - 1);
                if(temp.children.containsKey(input)){ return;};
                temp.children.put(input,temp = new TrieNode2());
            }
        }



//        String[] segment;
//        segment = word.split("");
//        word.subSequence(1,2);


    }


    @Override
    public boolean search(String word){

        return false;

    }

    @Override
    public List<String> searchWithPrefix(String prefix){

        return new ArrayList<>();

    }

    public static void main(String[] args) throws IOException {
        Trie2 dict = new Trie2();
        dict.add("nihao");
        System.out.println(dict.word_number);
        FileReader fr = new FileReader("English dictionary.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            dict.add(line);
        }
        System.out.println(dict.word_number);
    }
}


