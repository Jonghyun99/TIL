import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class MethodReference {




    public static void main(String[] args) {
        Consumer<String> func = System.out::println;
        func.accept("a");

    }
}
