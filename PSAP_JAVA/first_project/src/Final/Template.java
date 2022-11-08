public class Template {
    public static void main(String[] args) {
        Vector arr = new Vector();
        arr.add("sejong");
        arr.add(100);
        arr.add(134.55);

        for (Object e : arr) {
            System.out.println(e);
        }
    }
}