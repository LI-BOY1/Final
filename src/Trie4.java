import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class TrieNode4{
    String ch;
    boolean isword = false;
    boolean isleaf = false;

    public TrieNode4(){
        Map<String, TrieNode2> children = new LinkedHashMap<>();
    }

    public TrieNode4(char input){
        char[] arr = new char[28];

    }

}

public class Trie4 extends Trie{

    TrieNode4 root;

    @Override
    public void add(String word) {
        int bitNumber = 3;   // the number of letter would be stored in one level- one hash
        int remainder = word.length()%bitNumber;  // to judge if the word length is the multiple of bitNumber
        int quotient = word.length()/bitNumber;   // to judge if the word is less or more than 3

        if (word.length()<3){
            if(root == null){
                root = new TrieNode4();

            }

        }
        if(root == null){

            root  = new TrieNode4();
        }




    }

    @Override
    public boolean search(String word) {
        return false;
    }

    @Override
    public List<String> searchWithPrefix(String prefix) {
        return null;
    }

    public static void main(String[] args) {

    }
}
