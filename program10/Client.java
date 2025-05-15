import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connected to the server!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String serverMessage, clientMessage;

        while (true) {
            System.out.print("Client: ");
            clientMessage = console.readLine();
            out.println(clientMessage);
            if ("bye".equalsIgnoreCase(clientMessage)) break;
            serverMessage = in.readLine();
            System.out.println("Server: " + serverMessage);
        }

        socket.close();
    }
}
