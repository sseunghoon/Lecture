import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class Template {
    public static void main(String[] args) {
        LinkedList<String> arr = new LinkedList<>();

        arr.add("aaaaa");
        arr.add("bbbbb");
        arr.add("ccccc");
        arr.add("ddddd");

        Iterator iter = arr.iterator();
        while (iter.hasNext()){
            String str = (String) iter.next();
            System.out.println(str);
        }

        Iterator<String> iter2 = arr.iterator();
        while (iter2.hasNext()){
            String str = iter2.next();
            System.out.println(str);
        }

        Iterator<String> iter3 = arr.iterator();
        while (iter3.hasNext()){
            if (iter.next())
        }
    }
}
