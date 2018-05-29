import java.util.List;
import java.util.Vector;

/**
 * Created by bsQer on 26/05/2018.
 */
public class Grammer {
    Vector<Rule> rules;

    public Grammer(){
        rules = new Vector<Rule>();
    }


    public void add(String rule){
        Vector<String> rights = new Vector<String>();
        String head = "";
        boolean first = true;
        String[] r = rule.split("");

        for( String s : r){
            if(first) {
                head = s;
                first = false;
            }
            else
                rights.add(s);
        }
        rules.add(new Rule(head,rights));

    }

    public Vector<Rule> getRule() {
        return rules;
    }

    public void print(){
        System.out.println("--------");
        for(Rule rule : rules)
            rule.print();
        System.out.println("--------");
    }
}
