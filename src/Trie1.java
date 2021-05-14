import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Xiangjun Meng
class TrieNode1{
    public int count;
    boolean word;
    HashMap<Character, TrieNode1> children = new HashMap<>();

    public TrieNode1 get(char c) {
        return children.getOrDefault(c, null);
    }

    public TrieNode1 addWord(char c, TrieNode1 node) {
        children.putIfAbsent(c, node);
        return get(c);
    }
}

public class Trie1 extends Trie {
    //TrieNode1 root;
    TrieNode1 root = new TrieNode1();

	@Override
    public void add(String word) {
	    TrieNode1 node = root;
	    for (int i = 0; i < word.length(); i++) {
	        node = node.addWord(word.charAt(i), node);
        }
	    node.word = true;
    }

   /* public void insert(String word) {
    	if (find(word)) {
    	    return;
        }
    	TrieNode1 cur = root;
    	for (int i = 0; i < word.length(); i++) {
    	    TrieNode1 next = (TrieNode1) cur.children.get(word.charAt(i));
    	    if (next == null){
    	        next = new TrieNode1();
    	        cur.children.put(word.charAt(i), (TrieNode1) next);
            }
    	    cur = next;
    	    cur.count++;
        }
    	cur.word = true;
    	return;
    }
     /*
        TrieNode1 visited = root;
        int i = 0;
        while (i < word.length() && visited.sb[word.charAt(i) - CASE] != null) {
            int index = word.charAt(i) - CASE;
            int j = 0;
            StringBuilder sbsub = visited.sb[index];
            while (j < sbsub.length() && i < word.length() && sbsub.charAt(j) == word.charAt(i)) {
                ++i;
                ++j;
            }
            if (j == sbsub.length()) {
                visited = visited.children1[index];
            } else {
                if (i == word.length()) {
                    TrieNode1 existingChild = visited.children1[index];
                    TrieNode1 newChild = new TrieNode1(true);
                    StringBuilder remaining = strCopy(sbsub, j);
                    sbsub.setLength(j);
                    visited.children1[index] = newChild;
                    newChild.children1[remaining.charAt(0) - CASE] = existingChild;
                    newChild.sb[remaining.charAt(0) - CASE] = remaining;
                } else {
                    StringBuilder remaining = strCopy(sbsub, j);
                    TrieNode1 newChild = new TrieNode1(false);
                    StringBuilder remainingW = strCopy(word, i);
                    TrieNode1 temp = visited.children1[index];
                    sbsub.setLength(j);
                    visited.children1[index] = newChild;
                    newChild.sb[remaining.charAt(0) - CASE] = remaining;
                    newChild.children1[remaining.charAt(0) - CASE] = temp;
                    newChild.sb[remainingW.charAt(0) - CASE] = remainingW;
                    newChild.children1[remainingW.charAt(0) - CASE] = new TrieNode1(true);
                }
            }
            return;
        }
    }

    private StringBuilder strCopy (CharSequence str, int index) {
        StringBuilder result = new StringBuilder(100);
        while(index != str.length()) {
            result.append(str.charAt(index++));
        }
        return result;*/

    @Override
    public void search(String word) {
    }

    public boolean find(String word){
        TrieNode1 cur = root;
        for (int i = 0; i < word.length(); i++) {
            cur = cur.get(word.charAt(i));
            if(cur == null) {
                return false;
            }
        }
        return cur.word;
    }
    /*
        int i = 0;
        TrieNode1 node = root;
        while(i < word.length() && node.sb[word.charAt(i) - CASE] != null) {
            int index = word.charAt(i) - CASE;
            StringBuilder sbsub = node.sb[index];
            int j = 0;

            while (i < word.length()
                    && j < sbsub.length()) {

                // Character mismatch
                if (word.charAt(i) != sbsub.charAt(j)) {
                    return;
                }
                ++i;
                ++j;
            }
        }
        return;
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
    }*/

    @Override
    public List<String> searchWithPrefix(String prefix) {
        return new ArrayList<>();
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.nanoTime();
        HashMap<Integer, String> map = new HashMap<>();
        Trie1 trie = new Trie1();
        File file = new File("englishDict.txt");
        Scanner scan = new Scanner(file);
        String[] keys = new String[5];
        int key = 0;
        while(scan.hasNextLine()) {
            String str1 = scan.nextLine();
            map.put(key, str1);
            key++;
        }
        scan.close();
        for(int i = 0; i < keys.length; i++) {
            System.out.println(key);
            trie.add(keys[i]);
        }
        long endTime = System.nanoTime();
        System.out.println(trie);
    }

}



