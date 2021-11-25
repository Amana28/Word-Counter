import com.sun.deploy.security.BadCertificateDialog;

import java.io.File;
import java.util.Scanner;
public class WordCount {

    public static void main(String[] args) throws Exception {

        // pass the path to the file as a parameter
        File file = new File("Location");
        Scanner sc = new Scanner(file);
        BinarySearchTree<String, Integer> dictionary = new BinarySearchTree<>();
        String word = "";

        while (sc.hasNext()) {

            word = sc.next();
            word = word.replaceAll("[^a-zA-Z]","");

            Integer val = dictionary.lookup(word);
            if(val == null){
                dictionary.add(word, 1);
            }
            else{
                val += 1;
                dictionary.add(word, val);
            }
        }

        dictionary.inOrderTraverse();
        }
}
