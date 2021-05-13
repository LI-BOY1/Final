import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;

public class Trie3 extends Trie{
    private TrieNode root;
    public List<String> byteList;
    public int numOfNodes;
    char[] chaArray;

    public Trie3(){
        this.root = new TrieNode();
        this.byteList = new ArrayList<>();
        this.numOfNodes = 0;
        chaArray = new char[26];
        for(char c = 'a'; c <= 'z'; c++){
            int i = c - 'a';
            chaArray[i] = c;
        }
    }

    public Trie3(byte[] A){
        chaArray = new char[26];
        for(char c = 'a'; c <= 'z'; c++){
            int i = c - 'a';
            chaArray[i] = c;
        }
        this.byteList = new ArrayList<>();
        this.numOfNodes = 0;


        // 4个byte 为一组 --> 1个trienode
        // 先把每个byte变成 string, 去掉leading zeros, and concate together.---> string
        // 每个string 第一位是is leaf, 第二位是 isword, 剩下26位是 26个 trienode
        Deque<String> dequeS = new ArrayDeque<>();

        for(int i = 0; i < A.length; i+=4){
            byte b1 = A[i];
            byte b2 = A[i+1];
            byte b3 = A[i+2];
            byte b4 = A[i+3];

            String[] arr = new String[4];

            arr[0] = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
            arr[1] = String.format("%8s", Integer.toBinaryString(b2 & 0xFF)).replace(' ', '0');
            arr[2] = String.format("%8s", Integer.toBinaryString(b3 & 0xFF)).replace(' ', '0');
            arr[3] = String.format("%8s", Integer.toBinaryString(b4 & 0xFF)).replace(' ', '0');

            // System.out.println(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3]);

            String node = arr[0].substring(1) + arr[1].substring(1) + arr[2].substring(1) + arr[3].substring(1);
            dequeS.offerLast(node);
        }

        root = new TrieNode();
        Queue<TrieNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            TrieNode node = q.poll();
            String s = dequeS.pollFirst();
            // System.out.println(s);

            if(s.charAt(0) == '0'){ // not a leaf

            }else{ // is a leaf

            }

            if(s.charAt(1) == '1'){
                node.isword = true;
            }else{
                node.isword = false;
            }

            for(int i = 2; i < s.length(); i++){
                if(s.charAt(i) == '1'){
                    char ch = cal(i);
                    int index = ch - 'a';
                    //System.out.println(index + "   " + ch + "   " + i);
                    node.children[index] = new TrieNode(ch);
                    q.offer(node.children[index]);
                }
            }
        }
    }

    private char cal(int i){
        return chaArray[i - 2];
    }

    public void compare(Trie3 trie) throws Exception {
        Queue<TrieNode> q1 = new LinkedList<>();
        Queue<TrieNode> q2 = new LinkedList<>();

        while(!q1.isEmpty() && !q2.isEmpty()){
            TrieNode n1 = q1.poll();
            TrieNode n2 = q2.poll();

            if(n1.ch != n2.ch){
                throw new Exception("no same");
            }

            for(int j = 0; j < n1.children.length; j++){
                if(n1.children[j] != null) {
                    q1.offer(n1.children[j]);
                }
            }

            for(int j = 0; j < n2.children.length; j++){
                if(n2.children[j] != null) {
                    q2.offer(n2.children[j]);
                }
            }
        }
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
                    numOfNodes++;
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
