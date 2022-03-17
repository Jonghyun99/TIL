import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Employee {

    public Employee(int i, String name, double v) {
    }

    private static Employee[] arrayOfEmps = {
            new Employee(1, "Jeff Bezos", 100000.0),
            new Employee(2, "Bill Gates", 200000.0),
            new Employee(3, "Mark Zuckerberg", 300000.0)
    };

    private static List<Employee> empList = Arrays.asList(arrayOfEmps);

    public static void main(String[] args) {
        System.out.println(Stream.of(arrayOfEmps));
        empList.stream();
        
    }

}
