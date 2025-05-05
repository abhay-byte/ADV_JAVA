import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // Use try-with-resources for automatic closing of socket and stream
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Client connected to server. Waiting for message...");
            String serverMessage = input.readLine(); // Read message from server
            System.out.println("Client received: " + serverMessage);

            System.out.println("\nName: Abhay Raj\nEnrolment No: 00976803122");

        } catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            // e.printStackTrace(); // Uncomment for more detailed error info
        }
    }
}