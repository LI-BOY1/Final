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

    public static void main(String[] args) {
        Trie2 dict = new Trie2();
        dict.add("nihao");
    }
}


