import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class MethodReference {

    public void callText(Object obj){
        System.out.println("call! :" + obj);
    }


    public static void main(String[] args) {
        Consumer<String> func = System.out::println;
        func.accept("a");

    }
}
