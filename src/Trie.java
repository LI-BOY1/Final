import java.util.List;

public abstract class Trie{
    public abstract void add(String word);
    public abstract boolean search(String word);
    public abstract List<String> searchWithPrefix(String prefix);
	public abstract boolean delete(String word);

//    public int hash(String str){
//        return 0;
//    }
    

}