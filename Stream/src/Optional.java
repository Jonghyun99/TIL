import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Consumer;

public class Optional {

    public static void main(String[] args) {

        int integer = 3;
//        Map<Object,String> map = new HashMap<>();
//        Map<Object,String> map = null;
//        map.put("testKey","testValue");

//        java.util.Optional<String> opt = java.util.Optional.ofNullable("r");
//        java.util.Optional<Integer> opt = java.util.Optional.ofNullable(integer);
          java.util.Optional<String> opt = java.util.Optional.empty();

//        System.out.println(opt.getClass());
          System.out.println(opt.orElse("NULL!"));
//        System.out.println(opt.orElseGet(() -> "aa"));
//        System.out.println(opt.isPresent());

//        java.util.Optional opt2 = java.util.Optional.ofNullable(map);
//        System.out.println(opt2);

//        OptionalInt optInt = OptionalInt.of(3);
//        System.out.println(optInt);
//        System.out.println(optInt.getClass());
//        System.out.println(optInt.getAsInt());
    }
}
