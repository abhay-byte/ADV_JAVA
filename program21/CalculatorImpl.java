import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    public CalculatorImpl() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            CalculatorImpl obj = new CalculatorImpl();
            Naming.rebind("CalculatorService", obj);
            System.out.println("Calculator Server is ready.");
	System.out.println("Abhay Raj, 00976803122");
        } catch (Exception e) {
            System.out.println("Calculator Server failed: " + e);
        }
    }
}
