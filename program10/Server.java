import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        // Use try-with-resources for ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is waiting for client connection on port 1234...");

            // Accept client connection - use try-with-resources for client socket and streams
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Client connected from: " + clientSocket.getInetAddress().getHostAddress());

                String clientMessage;
                String serverResponse;

                // Loop until client sends "quit"
                while ((clientMessage = input.readLine()) != null) {
                    System.out.println("Received from Client: " + clientMessage);

                    if (clientMessage.equalsIgnoreCase("quit")) {
                        System.out.println("Client requested disconnection.");
                        break; // Exit loop
                    }

                    // Simple echo response
                    serverResponse = "Server received: " + clientMessage;
                    output.println(serverResponse); // Send response back to client
                    System.out.println("Sent to Client: " + serverResponse);
                }

                System.out.println("Client disconnected.");
                System.out.println("\nName: Abhay Raj\nEnrolment No: 00976803122");

            } catch (IOException e) {
                System.err.println("Error handling client connection: " + e.getMessage());
                // e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Could not start server on port 1234: " + e.getMessage());
            // e.printStackTrace();
        }
    }
}