import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;

public class Trie3 extends Trie{
    private TrieNode root;
    public List<String> byteList;
    public Trie3(){
        this.root = new TrieNode();
        this.byteList = new ArrayList<>();
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

    public void display(){
        Queue<TrieNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                TrieNode node = q.poll();
                byte[] array = convert(node);
                for(int j = 0; j < node.children.length; j++){
                    if(node.children[j] != null) {
                        q.offer(node.children[j]);
                    }
                }
            }
        }
    }

    public void createBinary(){
        try {
            FileOutputStream fos = new FileOutputStream(new File("boyang.dat"));
            Queue<TrieNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i < size; i++){
                    TrieNode node = q.poll();
                    byte[] array = convert(node);
                    fos.write(array);

                    for(int j = 0; j < node.children.length; j++){
                        if(node.children[j] != null) {
                            q.offer(node.children[j]);
                        }
                    }
                }
            }
//            fos.write("Hey, there!".getBytes());
//            fos.write("\n".getBytes());
//            fos.write("How are you doing?".getBytes());

            // close the writer
            fos.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private byte[] convert(TrieNode node){
        StringBuilder sb = new StringBuilder();
        boolean isLeaf = true;
        for(int j = 0; j < node.children.length; j++){
            if(node.children[j] != null)
                isLeaf = false;
        }

        if(isLeaf) {
            sb.append('1');
        }else {
            sb.append('0');
        }

        if(node.isword){
            sb.append('1');
        }else{
            sb.append('0');
        }

        for(int j = 0; j < node.children.length; j++) {
            if(node.children[j] == null) {
                sb.append('0');
            }else{
                sb.append('1');
            }
        }
        // so far, we have 1 + 1 + 26 = 28 bits,
        // we need 4 more bits to be 4 bytes
        String s = new String(sb);


        String[] stringArr = new String[4];
        int index = 0;
        for(int i = 0; i < 4; i++){
            if(i == 3){
                stringArr[i] = '0' + s.substring(index);
            }else{
                stringArr[i] = '0' + s.substring(index, index+7);
                index += 7;
            }
            byteList.add(stringArr[i]);
        }

        byte[] ans = new byte[4];

        for(int i = 0; i < 4; i++){
            ans[i] = Byte.parseByte(stringArr[i], 2);
        }

        return ans;










//        BitSet bitSet = new BitSet(s.length());
//
//        for(int i = 0; i < s.length(); i++){
//            if(s.charAt(i) == '1'){
//                bitSet.set(i);
//            }
//        }
//
//        byte[] A = bitSet.toByteArray();
//        System.out.println(A.length);
//        return A;














//        byte[] a = new BigInteger(s, 2).toByteArray();
//        System.out.println(s + "   " + node.ch + "   " + a.length);
//        return a;

//        short a = Short.parseShort(s, 2);
//        ByteBuffer bytes = ByteBuffer.allocate(4).putShort(a);
//
//        byte[] array = bytes.array();
//        System.out.println(s + "   " + node.ch + "   " + array.length);
//        return array;






//        byte[] array = new byte[4];
//        int index = 0;
//
//
//
//
//
//
//        for(int i = 0; i < s.length(); i+=8){
//            String b = s.substring(i, i+8);
//            byte mybyte = 0;
//
//
//
//
//
//
//
//
//            array[index++] = Byte.parseByte(b, 2);
//            System.out.println(b + "  " + Byte.parseByte(b, 2));
//        }
//
//        System.out.println(s + "   " + node.ch + "   " + array.length);
//
//        return array;

        // return new byte[0];
    }




}


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

    public String toString(){
        String s = "";
        s = s + " " + ch + "   " + children;
        return s;
    }


}
