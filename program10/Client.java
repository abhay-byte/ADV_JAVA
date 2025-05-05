import java.io.*;
import java.net.*;
import java.util.Scanner; // Import Scanner for user input

public class Client {
    public static void main(String[] args) {
        // Use try-with-resources for automatic closing
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             Scanner consoleInput = new Scanner(System.in)) { // Scanner for user input

            System.out.println("Connected to Server. Type 'quit' to exit.");
            String clientMessage;
            String serverResponse;

            do {
                System.out.print("Client: ");
                clientMessage = consoleInput.nextLine(); // Read message from user
                output.println(clientMessage);         // Send message to server

                if (!clientMessage.equalsIgnoreCase("quit")) {
                    serverResponse = input.readLine(); // Read response from server
                    if (serverResponse == null) {
                        System.out.println("Server disconnected.");
                        break; // Exit loop if server disconnects
                    }
                    System.out.println("Server: " + serverResponse);
                }

            } while (!clientMessage.equalsIgnoreCase("quit"));

            System.out.println("Client disconnecting.");
            System.out.println("\nName: Abhay Raj\nEnrolment No: 00976803122");

        } catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error or Server disconnected: " + e.getMessage());
            // e.printStackTrace();
        }
    }
}