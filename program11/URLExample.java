import java.net.URL;
import java.net.MalformedURLException; // Import specific exception

public class URLExample {
    public static void main(String[] args) {
        try {
            // Define the URL string
            String urlString = "https://www.example.com:8080/path/to/resource?query=example¶m=test#fragment";
            URL url = new URL(urlString);

            // Display the properties of the URL object
            System.out.println("Original URL: " + url.toString());
            System.out.println("------------------------------------");
            System.out.println("Protocol:     " + url.getProtocol());
            System.out.println("Host:         " + url.getHost());
            // getPort() returns -1 if the port is not explicitly specified or is the default port
            System.out.println("Port:         " + url.getPort());
            System.out.println("Default Port: " + url.getDefaultPort()); // Default port for the protocol (e.g., 443 for https)
            System.out.println("Authority:    " + url.getAuthority()); // Host + Port (if specified)
            System.out.println("Path:         " + url.getPath());
            System.out.println("Query:        " + url.getQuery()); // Part after '?'
            System.out.println("File:         " + url.getFile()); // Path + Query
            System.out.println("Ref (Fragment): " + url.getRef()); // Part after '#'
            System.out.println("User Info:    " + url.getUserInfo()); // User info part (e.g., user:pass@host) - null here
            System.out.println("------------------------------------");
            System.out.println("\nName: Abhay Raj\nEnrolment No: 00976803122");

        } catch (MalformedURLException e) {
            // Handle the case where the URL string is invalid
            System.err.println("Invalid URL format: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}