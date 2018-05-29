import javax.management.relation.Role;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by bsQer on 28/05/2018.
 */
public class Lambda {
    Vector<String> least = new Vector<String>();
    public Grammer delLambda(Grammer g){
        Grammer gfinal = new Grammer();
        Vector<String> l = new Vector<String>();
        for (Rule r: g.rules) {
            String ss = new String(r.head);
            for (String s:r.rights) {
                ss+=s;
            }
            gfinal.add(ss);
        }

        Vector<String> list = new Vector<String>();
        int i = 1;
        while (i != 0){
            i = 0;
            //detectLambda
            for (Rule r: g.rules) {
                if(r.rights.firstElement().equals("l") && !isIn(r.head, list) ){
                    list.add(r.head);
                    i++;
                }
            }
            //lambdize
            for (Rule r:g.rules) {
                Iterator<String> iter = r.rights.iterator();
                while (iter.hasNext()) {
                    String s = iter.next();
                    if (isIn(s, list)) {
                        iter.remove();
                    }
                }
                if (r.rights.size()==0) {
                    r.rights.add("l");
                }
            }

        }
        g = new Grammer();

        Iterator<Rule> iter = gfinal.rules.iterator();
        while (iter.hasNext()) {
            Rule r = iter.next();

            String esht = "";
            for (String s:r.rights) {
                if (isIn(s, list))
                    esht += s;
            }
            Vector<String> perm = new Comb().printCombinations(esht,esht.length());
            for (String s: perm) {
                String rhgts= "";
                for (String st: r.rights) {
                    if(s.contains(st))
                        continue;
                    else
                        rhgts+= st;
                }
                l.add(r.head+rhgts);

            }
        }
        Grammer gpass = new Grammer();
        for (String s:l) {
            gpass.add(s);
        }
        for (Rule r:gpass.rules) {
            if (r.rights.size()==0) {
                r.rights.add("l");
            }
        }

        Iterator<Rule> terr = gpass.rules.iterator();
        while (terr.hasNext()) {
            Rule r = terr.next();

            Iterator<String> rett = r.rights.iterator();
            while (rett.hasNext()) {
                String s = rett.next();
                if(r.rights.firstElement().equals("l"))
                    terr.remove();
            }
        }
        return gpass;
    }

    static boolean isIn(String s, Vector<String> list){
        for (String t:list) {
            if (t.equals(s))
                return true;
        }
        return false;
    }
}
