/**
 * Created by bsQer on 26/05/2018.
 */
public class App {
    Grammer rules;

    public void r(){
        rules = new Reader().read();
        System.out.println("Entered C-F Grammer:");
        rules.print();
    }
    public void delUseless1(){
        rules = Useless.delUseless1(rules);
    }
    public void delUseless2(){
        rules = Useless.delUseless2(rules);
    }
    public void delLambda(){
        rules = new Lambda().delLambda(rules);
    }
    public void delUnit(){
        rules = new Unit().delUnit(rules);
    }
    public void sort(){
        rules = new Sort().sort(rules);
        System.out.println("Simplified C-F Grammer:");
        rules.print();
    }
}
