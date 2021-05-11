import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Trie3 trie = new Trie3();
        // File file = new File("boyang_test.txt");
        File file = new File("dict.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                word.trim();
                if(word.length() > 0) {
                    trie.add(word);
                }
            }

            trie.createBinary();

        }catch (Exception e){
            System.err.println(e);
        }

        List<String> list = new ArrayList<>();

        try {
            Path path = Paths.get("boyang.dat");
            byte[] fileContents =  Files.readAllBytes(path);

            for(byte b: fileContents){
                String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                // System.out.println(s1);
                list.add(s1);
            }

            if(list.size() != trie.byteList.size())
                throw new Exception("sddfdg");

            for(int i = 0; i < list.size(); i++){
                if(!list.get(i).equals(trie.byteList.get(i)))
                    throw new Exception("not equals");
            }
        }catch (Exception e){
            System.err.println(e);
        }











    }

}
