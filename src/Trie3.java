import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie3 extends Trie{

    class TrieNode{
        TrieNode[] children;
        boolean isword;
        char ch;

        public TrieNode(){
            this.children = new TrieNode[26];
            this.ch = '0';
            this.isword = false;
        }

        public TrieNode(char ch){
            this();
            this.ch = ch;
        }
    }

    private TrieNode root;

    public Trie3(){
        this.root = new TrieNode();
    }

    @Override
    public void add(String word){
        TrieNode node = root;
        TrieNode[] children = node.children;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(children[ch - 'a'] == null){
                children[ch - 'a'] = new TrieNode(ch);
            }

            node = children[ch - 'a'];
            children = node.children;
        }

        node.isword = true;
    }

    @Override
    public boolean search(String word){
        TrieNode node = root;
        TrieNode[] children = node.children;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(children[ch - 'a'] == null)
                return false;

            node = children[ch - 'a'];
            children = node.children;
        }
        return node.isword;
    }

    @Override
    public List<String> searchWithPrefix(String prefix){

        return new ArrayList<>();

    }

    public void createBinary(){
        try {
            FileOutputStream fos = new FileOutputStream(new File("output.dat"));

            fos.write("Hey, there!".getBytes());
            fos.write("\n".getBytes());
            fos.write("How are you doing?".getBytes());

            // close the writer
            fos.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }



}

