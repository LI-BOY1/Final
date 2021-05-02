import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie3 extends Trie{

    class TrieNode{
        Map<String, TrieNode> children;
        String token;
        boolean isword;

        public TrieNode(){
            this.children = new HashMap<>();
            this.token = "";
            this.isword = false;
        }

        public TrieNode(String t){
            this();
            this.token = t;
        }
    }

    private TrieNode root;
    private int len;

    public Trie3(){
        this.root = new TrieNode();
        this.len = 5;
    }

    private List<String> splitWord(String word){
        List<String> list = new ArrayList<>();
        while(word.length() > len){
            list.add(word.substring(0, len));
            word = word.substring(len);
        }
        if(word.length() != 0){
            list.add(word);
        }
        return list;
    }


    @Override
    public void add(String word){
        List<String> list = splitWord(word);

        TrieNode node = root;
        Map<String, TrieNode> children = node.children;
        for(int i = 0; i < list.size(); i++) {
            if (!children.containsKey(list.get(i))) {
                children.put(list.get(i), new TrieNode(list.get(i)));
            }
            node = children.get(list.get(i));
            children = node.children;
        }

        node.isword = true;
    }

    @Override
    public boolean search(String word){
        List<String> list = splitWord(word);
        TrieNode node = root;
        Map<String, TrieNode> children = node.children;

        for(int i = 0; i < list.size(); i++) {
            if (!children.containsKey(list.get(i))) {
                return false;
            }
            node = children.get(list.get(i));
            children = node.children;
        }

        return node.isword;
    }

    @Override
    public List<String> searchWithPrefix(String prefix){

        return new ArrayList<>();

    }

}

