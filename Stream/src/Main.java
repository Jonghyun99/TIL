import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        Consumer<Integer> consumer;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);

//        list.forEach(s-> System.out.println(s.getClass()));

        list.forEach((n) -> { System.out.println(n+1); });
    }
}
