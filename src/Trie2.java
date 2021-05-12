import java.io.*;
import java.util.*;


class TrieNode2{
    String ch;
    Map<String, TrieNode2> children ;
    boolean isword = false;
    boolean isleaf = false;

    public TrieNode2(){
        children = new LinkedHashMap<>();
    }
}

public class Trie2 extends Trie{

    TrieNode2 root;
    int word_number = 0;
    ArrayList<String> comporessed_letters;
    ArrayList<String> root_letters;
    ArrayList binary_array = new ArrayList();
    int i = 0;

    @Override
    public void add(String word){
        int bitNumber = 3;   // the number of letter would be stored in one level- one hash
        int remainder = word.length()%bitNumber;  // to judge if the word length is the multiple of bitNumber
        int quotient = word.length()/bitNumber;   // to judge if the word is less or more than 3
        if(root == null){
            root  = new TrieNode2();
        }

        if(quotient == 0){ // when quotient is equal to 0, we know that the word length is less than 3, so put it into a special hash
            if(!root.children.containsKey("word length is less than 3")){
                TrieNode2 temp = new TrieNode2();
                temp.children.put(word,new TrieNode2());
                root.children.put("word length is less than 3",temp);
                word_number++;
                temp.isword = true;
                temp.isleaf = true;
            }else{
                if(root.children.get("word length is less than 3").children.containsKey(word)){return;}
                word_number++;
                root.children.get("word length is less than 3").children.put(word,new TrieNode2());
                root.children.get("word length is less than 3").isleaf = true;
                root.children.get("word length is less than 3").isword = true;
            }
        }else{  // this condition is when the word length is larger than 3
            if(remainder == 0){  // this is the condition when the word length is multiple of 3
                TrieNode2 temp;     // we need a  pointer to follow the iteration level to keep track of the level where we are now
                temp = root;                      // each time we add a new word, we will start from the root
                boolean add_new_word = false;
                for(int i = 0; i < quotient; i++){
                    String input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber);
                    if(!temp.children.containsKey(input)){  // if the map does not have the string value
                        temp.children.put(input,new TrieNode2());
                        add_new_word = true;
                        temp.children.get(input).ch = input;
                    }else{
                        while(temp.children.containsKey(input)){       // if it's true,say input exists and the value which is the trienode2 also exists,so temp can be assigned as new TrieNode2
                            temp.isleaf = false;
                            temp = temp.children.get(input);
                            i++;
                            if(i>=quotient){break;}
                            input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber); // if i is larger than quotient, so it will go out of the boundary of the word length
                        }
                        if(i<quotient){
                            temp.children.put(input,new TrieNode2());
                            temp.children.get(input).ch = input;
                            add_new_word = true;
                        }
                    }
                }if (add_new_word){word_number++;temp.isword = true; temp.isleaf = true;}
            }else{  // this condition is when the word length is not multiple of 3, the left part which is less than 3 will be disposed in the final
                TrieNode2 temp = root;     // we need a  pointer to  keep track of the node level where we are now
                // each time when we add a new word, we will start from the root. the complexity will not so high, depending on the length of the word, at most O(n/3) = O(n) average,at least 60%faster than the 1 letter storage
                int iteration_number = 0; // iteration_number is used to track the word letters
                boolean add_new_word = false;
                for(int i = 0; i < quotient; i++){
                    String input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber);
                    if(!temp.children.containsKey(input)){  // if the map does not have the string value
                        temp.children.put(input,new TrieNode2());
                        temp.children.get(input).ch = input;
                        iteration_number++;
                        add_new_word = true;
                    }else{
                        while(temp.children.containsKey(input)){       // if it's true,say input exists and the value which is the trienode2 also exists,so temp can be assigned as new TrieNode2
                            temp.isleaf = false;
                            temp = temp.children.get(input);
                            i++;
                            iteration_number++;
                            if(i>=quotient){break;}
                            input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber); // if i is larger than quotient, so it will go out of the boundary of the word length
                        }
                        if(i<quotient){
                            temp.children.put(input,new TrieNode2());
                            temp.children.get(input).ch = input;
                            add_new_word = true;
                            iteration_number++;
                        }
                    }
                }
                String input = (String)word.subSequence(iteration_number*bitNumber,word.length());
                if(temp.children.containsKey(input)){ return;}
                temp.isword = true;
                temp.isleaf = true;
                temp.children.put(input,new TrieNode2());
                temp.children.get(input).ch = input;
                add_new_word = true;
                word_number++;
            }
        }
    }

    @Override
    public boolean search(String word) {
        return false;
    }


    public String stringSearch(String word){
        int bitNumber = 3;   // the number of letter would be stored in one level- one hash
        int remainder = word.length()%bitNumber;  // to judge if the word length is the multiple of bitNumber
        int quotient = word.length()/bitNumber;   // to judge if the word is less or more than 3
        if(root == null){return "NO";}

        if(quotient == 0){
            if(root.children.containsKey("word length is less than 3")){
                if(root.children.get("word length is less than 3").children.containsKey(word)){return "Yes";}
                return "No";
            }else{
                return "No";
            }
        }else{
            TrieNode2 temp = root;
            int iteration_number = 0;
            for(int i = 0; i < quotient; i++){
                String input = (String)word.subSequence(i*bitNumber,(i+1)*bitNumber);
                if (!temp.children.containsKey(input)){return "No";} // if the hashmap of the node does not contain the 3-letter key,it will return immediately and give false
                temp = temp.children.get(input);
                iteration_number++;
            }if(remainder == 0){
                return "Yes";
            }else{
                String input = (String)word.subSequence(iteration_number*bitNumber,word.length());
                if (temp.children.containsKey(input)){return "Yes";}
                return "No";
            }
        }
    }

    @Override
    public List<String> searchWithPrefix(String prefix){
        return new ArrayList<>();
    }

    public void writeTrieToTxtfile(TrieNode2 node) throws IOException {
        comporessed_letters = new ArrayList<>();
        root_letters = new ArrayList<>();
        showTrie(node);
        BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
        for(String key : comporessed_letters){
            out.write(key+"\t");
        }
        out.close();
    }

    public void showTrie(TrieNode2 node) throws IOException {
        Map<String,TrieNode2> map = node.children;
        for (String key: map.keySet()){
            comporessed_letters.add(key);
            if(node.equals(root)){
                root_letters.add(key);
                System.out.print("\n");
//                System.out.print(key+"\t");
            }
            System.out.print(key+"\t");
            showTrie(map.get(key));
//            i++;
        }
    }

    public void writeBinaryToTxtfile(TrieNode2 node) throws IOException {
        turnIntoBinary(node);
        convert();
        BufferedWriter out = new BufferedWriter(new FileWriter("binary.txt"));
//        out.write(String.valueOf(binary_array));
        for(Object key : binary_array){
            out.write(key+"\t");
        }
        out.close();
    }

    public void turnIntoBinary(TrieNode2 node){
        Map<String,TrieNode2> map = node.children;
        for (String key: map.keySet()){
            strToBinary(key);
            turnIntoBinary(map.get(key));
            }
    }

    public ArrayList strToBinary(String s)
    {
        int n = s.length();
        String count = "";
        for (int i = 0; i < n; i++)
        {
            // convert each char to
            // ASCII value
            int val = Integer.valueOf(s.charAt(i));

            // Convert ASCII value to binary
            String bin = "";
            while (val > 0)
            {
                if (val % 2 == 1)
                {
                    bin += '1';
                }
                else
                    bin += '0';
                val /= 2;
            }
            bin = reverse(bin);

            binary_array.add(Byte.parseByte(bin,2));
//            count += bin;
//            System.out.print(bin + " ");
        }


        return binary_array;
    }

    public void convert(){
        for(Object c: binary_array){
            System.out.println(c.getClass());
        }
    }

    public String reverse(String input)
    {
        char[] a = input.toCharArray();
        int l, r = 0;
        r = a.length - 1;
        for (l = 0; l < r; l++, r--)
        {
            // Swap values of l and r
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }


//    public void write(TrieNode2 node){
//        Iterator<Map.Entry<String,TrieNode2>> entries = node.children.entrySet().iterator();
//        while(entries.hasNext()){
//            Map.Entry<String,TrieNode2> entry = entries.next();
//        }
//
//    }

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Trie2 dict = new Trie2();
//        dict.add("nihao");
        System.out.println(dict.word_number);
        FileReader fr = new FileReader("English dictionary.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            dict.add(line);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(dict.word_number);
        System.out.println((endTime - startTime)+"ms");

//        dict.write_trie();
//
//        System.out.println(dict.root.children.containsKey("abh"));

//        FileReader fr1 = new FileReader("English dictionary.txt");
//        BufferedReader br1 = new BufferedReader(fr1);
//        String line1;
//        String check;
//        int Number = 0;
//        while((line1 = br1.readLine()) != null){
//            check = dict.Stringsearch(line1);
//            if(check.equals("No")){System.out.println("have words not in the dict!");Number++;}
//        }
//        System.out.println(Number);


//        Trie2 dict1 = new Trie2();
////        dict.add("nihao");
//        System.out.println(dict1.word_number);
//        FileReader fr2 = new FileReader("test.txt");
//        BufferedReader br2 = new BufferedReader(fr2);
//        String line2;
//        int a = 0;
//        while((line2 = br2.readLine()) != null){
//            dict1.add(line2);
//            a++;
//            if (line2.equals("aaa")){break;}
//        }
//        System.out.println(dict1.word_number);
//
////        dict1.write_trie();
//
//        System.out.println(dict1.root.children.containsKey("phe"));
//        System.out.println(a);

        dict.writeTrieToTxtfile(dict.root);
        dict.writeBinaryToTxtfile(dict.root);

        //        System.out.println(dict.i);
    }
}