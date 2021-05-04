import java.io.BufferedReader;
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
        int bitNumber = 3;   // the number of letter would be stored in one level- one hash
        int remainder = word.length()%bitNumber;  // to judge if the word length is the multiple of bitNumber
        int quotient = word.length()/bitNumber;   // to judge if the word is less or more than 3
        if(root == null){
            root  = new TrieNode2();
        }

        if(quotient == 0){ // when quotient is equal to 0, we know that the word length is less than 3, so put it into a special hash
            if(!root.children.containsKey("word length is less than 3")){
                word_number++;
                root.children.put("word length is less than 3",new TrieNode2(word,word_number));
            }else{
                if(root.children.get("word length is less than 3").two_word_children.containsKey(word)){return;}
                word_number++;
                root.children.get("word length is less than 3").two_word_children.put(word,word_number);
            }
        }else{  // this condition is when the word length is larger than 3
            if(remainder == 0){  // this is the condition when the word length is multiple of 3
                TrieNode2 temp;     // we need a  pointer to follow the iteration level to keep track of the level where we are now
                temp = root;                      // each time we add a new word, we will start from the root
                int iteration_number = 0;
                boolean add_new_word = false;
                for(int i = 0; i < quotient; i++){
                    String input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber);
                    if(!temp.children.containsKey(input)){  // if the map does not have the string value
                        temp.children.put(input,temp = new TrieNode2());
                        add_new_word = true;
                    }else{
                        while(temp.children.containsKey(input)){       // if it's true,say input exists and the value which is the trienode2 also exists,so temp can be assigned as new TrieNode2
                            temp = temp.children.get(input);
                            i++;
                            if(i>=quotient){break;}
                            input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber); // if i is larger than quotient, so it will go out of the boundary of the word length
                        }
                        if(i<quotient){
                            temp.children.put(input,temp = new TrieNode2());
                            add_new_word = true;
                        }
                    }
                }if (add_new_word){word_number++;}
            }else{  // this condition is when the word length is not multiple of 3, the left part which is less than 3 will be disposed in the final
                TrieNode2 temp = root;     // we need a  pointer to  keep track of the node level where we are now
                // each time when we add a new word, we will start from the root. the complexity will not so high, depending on the length of the word, at most O(n/3) = O(n) average,at least 60%faster than the 1 letter storage
                int iteration_number = 0; // iteration_number is used to track the word letters
                boolean add_new_word = false;
                for(int i = 0; i < quotient; i++){
                    String input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber);
                    if(!temp.children.containsKey(input)){  // if the map does not have the string value
                        temp.children.put(input,temp = new TrieNode2());
                        iteration_number++;
                        add_new_word = true;
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
                            add_new_word = true;
                            iteration_number++;
                        }
                    }
                }
                String input = (String)word.subSequence(iteration_number*bitNumber,word.length());
                if(temp.children.containsKey(input)){ return;}
                temp.children.put(input,new TrieNode2());
                add_new_word = true;
                word_number++;
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
//        dict.add("nihao");
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


