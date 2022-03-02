import java.util.Optional;

public class Wrapper {

    public static void main(String[] args) {
        Integer num1 = new Integer(7);	// 박싱
        Integer num2 = new Integer(3);	// 박싱

        int int1 = num1.intValue();		// 언박싱
        int int2 = num2.intValue();		// 언박싱

        Integer result1 = num1 + num2;
        System.out.println(result1);

        Integer result2 = int1 - int2;
        System.out.println(result2);

        Integer result3 = num1 * int2;
        System.out.println(result3.getClass());

        int result4 = num1 * int2;
        System.out.println(result4);

    }
}
