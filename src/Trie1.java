import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

// boyang li
class TrieNode1{
    char ch;
    boolean word;
    HashMap<Character, TreeNode> children = new HashMap<>();
    TrieNode1[] children1 = new TrieNode1[26];
	public int count;
}

// Xiangjun Meng
public abstract class Trie1 extends Trie{
    TrieNode1 root;
    static final int[][] DIRS = {{-1, 0}, {1,0},{0,-1},{0,1}};

	@Override
    public void add(String word){
    	if (search(word)) {
    	    return;
        }
    	TrieNode1 cur = root;
    	for (int i = 0; i < word.length(); i++) {
    	    TrieNode1 next = (TrieNode1) cur.children.get(word.charAt(i));
    	    if (next == null){
    	        next = new TrieNode1();
    	        cur.children.put(word.charAt(i), (TreeNode) next);
            }
    	    cur = next;
    	    cur.count++;
        }
    	cur.word = true;
    	return;
    }

    @Override
    public boolean search(String word){
        TrieNode1 cur = root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode1 next = (TrieNode1) cur.children.get(word.charAt(i));
            if(next == null) {
                return false;
            }
            cur = next;
        }
        return cur.word;
    }
    
    @Override
	public boolean delete(String word) {
    	if (!search(word)) {
    		return false;
    	}
    	TrieNode1 cur = root;
    	for (int i = 0; i < word.length(); i++) {
    		TrieNode1 next = (TrieNode1) cur.children.get(word.charAt(i));
    		next.count--;
    		if(next.count == 0) {
    			cur.children.remove(word.charAt(i));
    			return true;
    		}
    		cur = next;
    	}
    	cur.word = false;
    	return true;
    }

}



