import java.rmi.Naming;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Calculator calc = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");
            int result = calc.add(10, 20);
            System.out.println("Result of addition: " + result);
	System.out.println("Abhay Raj, 00976803122");

        } catch (Exception e) {
            System.out.println("Calculator Client failed: " + e);
        }
    }
}