import java.util.Vector;

/**
 * Created by bsQer on 26/05/2018.
 */
public class Rule {
    String head;
    Vector<String> rights;

    public Rule(String head, Vector<String> rights){
        this.head = head;
        this.rights = rights;
    }

    public void print(){
        System.out.print(head);
        System.out.print("->");
        for (String a:rights)
            System.out.print(a);
        System.out.println();
    }

}
