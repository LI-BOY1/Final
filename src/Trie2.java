import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class TrieNode2{
    String ch;
    Map<String, TrieNode2> children = new HashMap<>();
}

public class Trie2 extends Trie{

    TrieNode2 root;

    @Override
    public void add(String word){
        int bitNumber = 3;
        int remainder = word.length()%bitNumber;
        int quotient = word.length()/bitNumber;

        if(remainder == 0){
            for(int i = 0; i < quotient; i++){
                String input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber);
                root 

            }
        }else{






        }



        String[] segment;
        segment = word.split("");
        word.subSequence(1,2);


    }


    @Override
    public boolean search(String word){

        return false;

    }

    @Override
    public List<String> searchWithPrefix(String prefix){

        return new ArrayList<>();

    }

    public static void main(String[] args) {
        Trie2 dict = new Trie2();
        dict.add("nihao");
    }
}


