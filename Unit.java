import java.util.Iterator;
import java.util.Vector;

/**
 * Created by bsQer on 28/05/2018.
 */
public class Unit {
    Grammer gUnit = new Grammer();
    Grammer g;

    Vector<String> list = new Vector<String>();
    boolean[] mark = new boolean[list.size()];

    public Grammer delUnit(Grammer g){
        this.g = g;
        Iterator<Rule> iter = g.rules.iterator();
        while (iter.hasNext()) {
            Rule r = iter.next();
            if(r.rights.firstElement().matches("[A-Z]") && r.rights.size()==1) {
                gUnit.add(r.head + r.rights.firstElement());
                iter.remove();
            }
        }
        unitGen();
        unitDel();
        return g;
    }

    void unitDel(){
        Vector<String> st = new Vector<String>();
        for (Rule r:gUnit.rules) {
            for (Rule rr:g.rules) {
                if (rr.head.equals(r.rights.firstElement())){
                    String s = r.head;
                    for (String ss:rr.rights) {
                        s+=ss;
                    }
                    st.add(s);
                }
            }
        }
        for (String s:st) {
            g.add(s);
        }
    }

    void unitGen(){
        for (Rule r:gUnit.rules) {
            if(!isIn(r.head,list)) {
                list.add(r.head);
            }
        }
        mark = new boolean[list.size()];
        for (boolean b: mark) {
            b = false;
        }
        for (String s:list) {
            dfs(s);
            for(int i=0; i<list.size(); i++) {
                if(mark[i] && !isRuleIn(s,list.elementAt(i),gUnit))
                    gUnit.add(s+list.elementAt(i));
            }
            mark = new boolean[list.size()];
            for (int i=0; i<mark.length; i++) {
                mark[i] = false;

            }
        }

    }

    void dfs(String s){
        for (Rule r:gUnit.rules) {
            if(r.head.equals(s)){
                for(int i =0; i<list.size(); i++) {
                    String ii = list.elementAt(i);
                    if (ii.equals(r.rights.firstElement())) {
                        mark[i] = true;
                        for (Rule ru : gUnit.rules) {
                            if (ru.head.equals(ii) && !ru.rights.firstElement().equals(s)) {
                                dfs(r.rights.firstElement());
                            }
                        }
                    }
                }
            }
        }
    }

    static boolean isIn(String s, Vector<String> list){
        for (String t:list) {
            if (t.equals(s))
                return true;
        }
        return false;
    }
    boolean isRuleIn(String head, String tail,Grammer g){
        for (Rule r:g.rules) {
            if(r.head.equals(head) && r.rights.firstElement().equals(tail))
                return true;
        }
        return false;
    }
}
