import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, Integer> map = new HashMap<>();
        File text = new File("englishDict.txt");
        Scanner scan = new Scanner(text);

        int key = 0;
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            map.put(line, key);
            key++;
        }

        System.out.println("Size of the map is:" + map.size());
        System.out.println(map);
    }
}
-