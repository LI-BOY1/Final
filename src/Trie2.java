import java.util.*;


class TrieNode2{
    String ch;
    Map<String, TrieNode2> children = new HashMap<>();
    TrieNode2[] children2 = new TrieNode2[26];
    StringBuilder[] sb = new StringBuilder[26];
    boolean b;

    public TrieNode2(boolean b) {
        this.b = b;
    }
}

public class Trie2 extends Trie{

    TrieNode2 root;

    @Override
    public void add(String word){


    }


    @Override
    public void search(String word){


    }

    @Override
    public List<String> searchWithPrefix(String prefix){

        return new ArrayList<>();

    }



}


