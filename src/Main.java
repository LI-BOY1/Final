import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        testTrie3();


        //testTrie4();
    }

    public static void testTrie4(){
        Trie4 trie = new Trie4();
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

        try {
            FileInputStream f = new FileInputStream("boyang_2.dat");
            ObjectInputStream in = new ObjectInputStream(f);
            Set<String>[] map = (Set<String>[]) in.readObject();

            trie.compare(map);
        }catch (Exception e){
            System.err.println(e);
        }


    }


    public static void testTrie3(){
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

        try {
            Path path = Paths.get("boyang.dat");
            byte[] fileContents =  Files.readAllBytes(path);

            // check(fileContents, trie);

            Trie3 decodeTrie = new Trie3(fileContents);

            trie.compare(decodeTrie);

        }catch (Exception e){
            System.err.println(e);
        }
    }

    private static void check(byte[] fileContents, Trie3 trie) throws Exception {
        System.out.println(fileContents.length/4);
        System.out.println(trie.numOfNodes);
        List<String> list = new ArrayList<>();

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
    }





}
