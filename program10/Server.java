import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server is listening...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String clientMessage, serverMessage;

        while ((clientMessage = in.readLine()) != null) {
            System.out.println("Client: " + clientMessage);
            if ("bye".equalsIgnoreCase(clientMessage)) break;
            System.out.print("Server: ");
            serverMessage = console.readLine();
            out.println(serverMessage);
        }

        socket.close();
        serverSocket.close();
    }
}
