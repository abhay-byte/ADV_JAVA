import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // Use try-with-resources for automatic closing of socket and streams
        try (Socket socket = new Socket("localhost", 1234);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String message = "Hello, Server!";
            output.println(message); // Send message to server
            System.out.println("Client sent: " + message);

            String serverResponse = input.readLine(); // Read response from server
            System.out.println("Server responded: " + serverResponse);

            System.out.println("\nName: Abhay Raj\nEnrolment No: 00976803122");

        } catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            // e.printStackTrace(); // Uncomment for more detailed error info
        }
    }
}