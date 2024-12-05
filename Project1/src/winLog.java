import javax.xml.namespace.QName;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;



public class winLog {
    public Stack<logItem> log;

    public winLog() {
        log = new Stack<>();
    }

    public void add(int score, String name){
        logItem item = new logItem(score, name);
        log.push(item);
    }

    public void printLog(){
        System.out.println("Win Log: ");
        for (logItem item : log) {
            System.out.println("Player: " + item.getName() + ", Score: " + item.getScore());
        }
    }
}
