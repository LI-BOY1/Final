import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

// boyang li
class TrieNode1{
    char ch;
    boolean word;
    HashMap<Character, TreeNode1> children = new HashMap<>();
 //   TrieNode1[] children = new TrieNode1[26];
}

// Xiangjun Meng
public class Trie1 extends Trie{
    TrieNode1 root;

    @Override
    public void add(String word){
    	if (search(word)) {
    	    return false;
        }
    	TrieNode1 cur = root;
    	for (int i = 0; i < word.length(); i++) {
    	    TrieNode1 next = cur.children.get(word.charAt(i));
    	    if (next == null){
    	        next = new TrieNode1();
    	        cur.children.put(word.charAt(i), next);
            }
    	    cur = next;
    	    cur.count++;
        }
    	cur.isWord = true;
    	return true;
    }

    @Override
    public boolean search(String word){
        TrieNode1 cur = root;
        for (int i = 0; i < word.length; i++) {
            TrieNode1 next = cur.children.get(word.charAt(i));
            if(next == null) {
                return false;
            }
            cur = next;
        }
        return cur.isWord;
    }

    @Override
    public List<String> searchWithPrefix(TrieNode1 root, String prefix) {
        TrieNode1 matchNode = searchNode(root, prefix);
        List<String> result = new ArrayList<>();
        if (matchNode == null) {
            return result;
        }
        searchWithUnderNode(matchNode, new StringBuilder(prefix), result);
        return result;
    }

    public void searchWithUnderNode (TrieNode1 current, StringBuilder curPath, List<String> result) {
        if (current.isWord) {
            result.add(curPath.toString());
        }
        for (Entry<Character, TrieNode> child : current.children.EntrySet()) {
            curPath.append(child.getKey());
            searchWithUnderNode(child.getValue, curPath, result);
        }
    }
    
    

}



