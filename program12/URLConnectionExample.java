import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException; // For URL creation errors
import java.net.SocketTimeoutException; // For timeout errors

public class URLConnectionExample {
    public static void main(String[] args) {
        HttpURLConnection connection = null; // Declare outside try block for finally
        BufferedReader reader = null;      // Declare outside try block for finally

        try {
            URL url = new URL("https://www.example.com");
            // Open the connection (does not establish network connection yet)
            // We cast to HttpURLConnection for HTTP-specific methods
            connection = (HttpURLConnection) url.openConnection();

            // --- Configure the request ---
            connection.setRequestMethod("GET"); // Specify the request method (GET is default)
            connection.setConnectTimeout(5000); // Set connection timeout to 5 seconds (5000 ms)
            connection.setReadTimeout(5000);    // Set read timeout to 5 seconds (5000 ms)
            // You can set request headers if needed:
            // connection.setRequestProperty("User-Agent", "MyJavaApp/1.0");
            // connection.setRequestProperty("Accept", "text/html");

            // --- Connect and get response code ---
            // connect() is implicitly called by getResponseCode(), getInputStream() etc.
            int responseCode = connection.getResponseCode();
            System.out.println("Request URL: " + url);
            System.out.println("Request Method: " + connection.getRequestMethod());
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Message: " + connection.getResponseMessage());

            // --- Process the response ---
            if (responseCode == HttpURLConnection.HTTP_OK) { // Check for successful response (200)
                System.out.println("\n--- Response Headers ---");
                connection.getHeaderFields().forEach((key, value) -> {
                    if (key != null) { // The first header line (status) has a null key
                        System.out.println(key + ": " + value);
                    }
                });

                System.out.println("\n--- Response Content (first few lines) ---");
                // Get the input stream to read the response body
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                int lineCount = 0;
                // Using StringBuffer for thread safety (though not needed here)
                // StringBuilder is generally preferred for single-threaded use
                StringBuffer responseContent = new StringBuffer();

                while ((inputLine = reader.readLine()) != null && lineCount < 10) { // Read only first 10 lines
                    responseContent.append(inputLine).append("\n");
                    lineCount++;
                }
                System.out.println(responseContent.toString());
                if (inputLine != null) {
                     System.out.println("... (content truncated)");
                }

            } else {
                System.out.println("Failed to open connection or received non-OK response.");
                // You might want to read the error stream for more details on failure
                // InputStream errorStream = connection.getErrorStream();
                // if (errorStream != null) { ... read error stream ... }
            }

            System.out.println("\nName: Abhay Raj\nEnrolment No: 00976803122");

        } catch (MalformedURLException e) {
            System.err.println("Error creating URL: " + e.getMessage());
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            System.err.println("Connection or read timed out: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error during network communication: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // --- Clean up ---
            // Close the reader if it was opened
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