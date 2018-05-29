import java.util.Collections;
import java.util.Vector;

/**
 * Created by bsQer on 28/05/2018.
 */
public class Sort {

    public Grammer sort(Grammer g){
        Vector<Integer> index = new Vector<Integer>();
        for (int i=0; i<g.rules.size(); i++) {
            if (g.rules.elementAt(i).head.equals("S"))
                index.add(i);
        }
        for (int i=0; i<index.size(); i++)
            Collections.swap(g.rules,i,index.elementAt(i));
            return g;
    }
}
