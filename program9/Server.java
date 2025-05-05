import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        // Use try-with-resources for automatic closing of serverSocket and clientSocket
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is waiting for client connection on port 1234...");

            // Wait for a client to connect - this blocks until a connection is made
            try (Socket clientSocket = serverSocket.accept();
                 // Set up stream to send data to the client
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Client connected from: " + clientSocket.getInetAddress().getHostAddress());

                String serverMessage = "Hello from Server! Welcome.";
                output.println(serverMessage); // Send the message to the client
                System.out.println("Server sent: " + serverMessage);

                System.out.println("\nName: Abhay Raj\nEnrolment No: 00976803122");

            } catch (IOException e) {
                System.err.println("Error handling client connection: " + e.getMessage());
                // e.printStackTrace(); // Uncomment for more detailed error info
            }

        } catch (IOException e) {
            System.err.println("Could not start server on port 1234: " + e.getMessage());
            // e.printStackTrace(); // Uncomment for more detailed error info
        }
    }
}