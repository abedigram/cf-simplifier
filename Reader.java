import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by bsQer on 26/05/2018.
 */
public class Reader {

    Grammer rules;
    Scanner sc = new Scanner(System.in);
    BufferedReader buff;
    public Reader(){
        rules = new Grammer();
    }
    public Grammer read(){
        System.out.println("Enter directory to read Grammer");
        String dir = sc.nextLine();
        try {
            buff = new BufferedReader(new FileReader(dir));
//            buff = new BufferedReader(new FileReader(dir));
            String line = buff.readLine();
            while (line != null){
                rules.add(line);
                line = buff.readLine();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.out.println("file not found!");
        }
        return rules;
    }
}


