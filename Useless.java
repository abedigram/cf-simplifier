import java.util.Iterator;
import java.util.Vector;

/**
 * Created by bsQer on 26/05/2018.
 */
public class Useless {
    public static Grammer delUseless1(Grammer g){
        Vector<String> list = new Vector<String>();
        boolean changed = true;
        int listlength = list.size();

        while(changed) {
            for (Rule r : g.rules) {
                int i = 0;
                if (isIn(r.head, list))
                    continue;
                for (String s : r.rights) {
                    if (s.matches("[A-Z]") && !isIn(s, list))
                        i++;
                }
                if (i == 0 && !isIn(r.head, list))
                    list.add(r.head);
            }
            if(listlength == list.size())
                changed = false;
            listlength = list.size();
        }
        Iterator<Rule> iter = g.rules.iterator();
        while (iter.hasNext()) {
            Rule r = iter.next();
            if (!isIn(r.head, list))
                iter.remove();
            Iterator<String> iiter = r.rights.iterator();
            while (iiter.hasNext()) {
                String s = iiter.next();
                if (s.matches("[A-Z]") && !isIn(s, list))
                    iiter.remove();
                if(r.rights.size()==0)
                    iter.remove();
            }
        }
        return g;
    }

    public static Grammer delUseless2(Grammer g){
        Vector<String> list = new Vector<String>();
        list.add("S");
        Vector<String> gList = useCur("S",list,g);
        Iterator<Rule> iter = g.rules.iterator();
        while (iter.hasNext()) {
            Rule r = iter.next();
            if (!isIn(r.head, gList))
                iter.remove();
            Iterator<String> iiter = r.rights.iterator();
        }
        return g;
    }
    static Vector<String> useCur(String start, Vector<String> list, Grammer g){
        Vector<String> li = list;
        boolean continu = false;
        for (Rule r:g.rules) {
            if(r.head.equals(start))
                for (String s:r.rights) {
                    if (s.matches("[A-Z]") && !isIn(s,li)){
                        li.add(s);
                        Vector<String> listt = useCur(s, li, g);
                        for (String tr:listt) {
                            if (!isIn(tr,li))
                                li.add(tr);
                        }
                    }
                }
        }
        return li;
    }



    static boolean isIn(String s, Vector<String> list){
        for (String t:list) {
            if (t.equals(s))
                return true;
        }
        return false;
    }


}

