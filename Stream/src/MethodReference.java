import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class MethodReference {

    String stra = "Aa";
    static public void callText(String obj){
        System.out.println("call! :" + obj);

    }


    public static void main(String[] args) {
        List<String> animals = new ArrayList<>();
        animals.add("monkey");
        animals.add("dog");
        animals.add("chiken");
        animals.add("elephent");

//        for(String str:animals){
//            System.out.println(str.length());
//        }

                animals.stream()
                .mapToInt(a -> a.length())
                .forEach((a) -> System.out.println());

//        animals.stream()
//                .mapToInt(String::length)
//                .forEach(System.out::println);
    }
}
