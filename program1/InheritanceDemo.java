class A { void displayA() { System.out.println("Class A"); } }

class B extends A { void displayB() { System.out.println("Class B (Single)"); } } // Single

class C extends B { void displayC() { System.out.println("Class C (Multilevel)"); } } // Multilevel

class D extends A { void displayD() { System.out.println("Class D (Hierarchical)"); } } // Hierarchical

public class InheritanceDemo {
    public static void main(String[] args) {
        C c = new C(); c.displayA(); c.displayB(); c.displayC();
        D d = new D(); d.displayA(); d.displayD();
        System.out.println("Name: Abhay Raj, 00976803122");
    }
}
