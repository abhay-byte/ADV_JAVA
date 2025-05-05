class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}

public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught an ArithmeticException: " + e.getMessage());
        }

        try {
            String str = null;
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("Caught a NullPointerException: " + e.getMessage());
        }

        try {
            int[] arr = new int[2];
            arr[5] = 10;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught an ArrayIndexOutOfBoundsException: " + e.getMessage());
        }

        try {
            String str = "abc";
            int num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Caught a NumberFormatException: " + e.getMessage());
        }

        try {
            throw new CustomException("This is a custom exception.");
        } catch (CustomException e) {
            System.out.println("Caught a CustomException: " + e.getMessage());
        }

        System.out.println("Name: Abhay Raj\nEnrolment No: 00976803122");
    }
}