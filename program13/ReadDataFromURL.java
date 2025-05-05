import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets; // Specify Charset

public class ReadDataFromURL {
    public static void main(String[] args) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // Define the URL to read from
            URL url = new URL("https://www.example.com");

            // Open the connection
            connection = (HttpURLConnection) url.openConnection();

            // Set request method and timeouts
            connection.setRequestMethod("GET"); // GET is usually default, but good practice to set
            connection.setConnectTimeout(5000); // 5 seconds connection timeout
            connection.setReadTimeout(5000);    // 5 seconds read timeout

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Request URL: " + url);
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Message: " + connection.getResponseMessage());

            // Check if the request was successful (HTTP_OK = 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Get the input stream to read the response body
                // Use InputStreamReader to specify character encoding (UTF-8 is common)
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

                String inputLine;
                // Use StringBuilder for efficient string concatenation
                StringBuilder content = new StringBuilder();

                // Read the response line by line
                while ((inputLine = reader.readLine()) != null) {
                    content.append(inputLine).append("\n"); // Append line and newline character
                }

                // Print the retrieved content
                System.out.println("\n--- Content from URL ---");
                System.out.println(content.toString());

            } else {
                System.out.println("Failed to retrieve data. Status Code: " + responseCode);
                // Optionally read the error stream if available
                InputStream errorStream = connection.getErrorStream();
                if (errorStream != null) {
                    try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream))) {
                        System.out.println("--- Error Stream ---");
                        String errorLine;
                        while ((errorLine = errorReader.readLine()) != null) {
                             System.out.println(errorLine);
                        }
                    } catch (IOException ex) {
                        System.err.println("Error reading error stream: " + ex.getMessage());
                    }
                }
            }

            System.out.println("\nName: Abhay Raj\nEnrolment No: 00976803122");

        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            System.err.println("Connection or read timed out: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading from URL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // --- Clean up resources ---
            // Close the BufferedReader
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing reader: " + e.getMessage());
                }
            }
            // Disconnect the HttpURLConnection
            if (connection != null) {
                connection.disconnect();
                System.out.println("\nConnection disconnected.");
            }
        }
    }
}