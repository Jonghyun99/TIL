import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {

    static MethodReference obj = new MethodReference();

//    static Consumer func = (a) -> obj.callText("str");
    static Consumer<Double> func2 = obj::callText;
//    static Function func2 = obj::callText;

    public static void main(String[] args) {
        func2.accept(12D);

    }
}
