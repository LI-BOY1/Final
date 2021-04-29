import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

// boyang li
class TrieNode1{
    char ch;
    TrieNode1[] children = new TrieNode1[26];
}

public class Trie1 extends Trie{
    TrieNode1 root;

    @Override
    public void add(String word){



    }

    @Override
    public boolean search(String word){

        return false;

    }

    @Override
    public List<String> searchWithPrefix(String prefix){

        return new ArrayList<>();

    }

}



