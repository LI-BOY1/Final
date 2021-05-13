import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Trie4 extends Trie{

    Set<String>[] map;

    public Trie4(){
        this.map = new HashSet[26*26*26];
        for(int i = 0; i < map.length; i++){
            map[i] = new HashSet<>();
        }
    }

    @Override
    public void add(String word){
        if(word.length() <= 2) {
            map[0].add(word);
            return;
        }
        if(word.length() == 3){
            int index = cal(word);
            map[index].add("");
            return;
        }

        String prefix = word.substring(0, 3);
        String token = word.substring(3);

        int index = cal(prefix);

        map[index].add(token);
    }

    @Override
    public boolean search(String word){
        if(word.length() <= 2)
            return map[0].contains(word);

        if(word.length() == 3) {
            int index = cal(word);
            return map[index].contains("");
        }

        String prefix = word.substring(0, 3);
        String token = word.substring(3);

        int index = cal(prefix);

        return map[index].contains(token);
    }

    public void compare(Set<String>[] A){
        for(int i = 0; i < A.length; i++){
            if(!A[i].equals(map[i])){
                System.out.println(i + " not same");
            }
        }
    }


    private int cal(String A){
        int i = ( (A.charAt(0) - 'a') * 26 + A.charAt(1) - 'a') * 26 + A.charAt(2) - 'a';
        return i;
    }

    @Override
    public List<String> searchWithPrefix(String prefix){
        return new ArrayList<>();
    }

    public void createBinary(){
        try {
            FileOutputStream f = new FileOutputStream("boyang_2.dat");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(map);
            f.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }



}
