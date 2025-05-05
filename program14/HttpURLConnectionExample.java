import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException; // Import specific IO exceptions
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets; // For specifying UTF-8 encoding

public class HttpURLConnectionExample { // Renamed class to avoid conflict

    public static void main(String[] args) {
        HttpURLConnection connection = null;

        try {
            // --- 1. Specify the URL for the POST request ---
            String urlString = "https://jsonplaceholder.typicode.com/posts"; // Example REST API endpoint
            URL url = new URL(urlString);

            // --- 2. Open the connection ---
            connection = (HttpURLConnection) url.openConnection();

            // --- 3. Configure the connection for POST ---
            connection.setRequestMethod("POST"); // Set the request method to POST
            connection.setRequestProperty("Content-Type", "application/json; utf-8"); // Set Content-Type header for JSON data
            connection.setRequestProperty("Accept", "application/json"); // Indicate we accept JSON response
            connection.setDoOutput(true); // !!! Important: Allow sending data (body) for POST/PUT requests
            connection.setConnectTimeout(5000); // Connection timeout
            connection.setReadTimeout(5000);    // Read timeout

            // --- 4. Prepare the request body (JSON data) ---
            // Example JSON payload as a String
            String jsonInputString = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

            // --- 5. Send the request body ---
            // Use try-with-resources for the output stream to ensure it's closed
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8); // Encode string to bytes using UTF-8
                os.write(input, 0, input.length); // Write the byte array to the output stream
                System.out.println("Sending POST request to URL : " + url);
                System.out.println("Post parameters : " + jsonInputString);
            }

            // --- 6. Get the response code ---
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            System.out.println("Response Message : " + connection.getResponseMessage());


            // --- 7. Read the response ---
            // Use try-with-resources for the input stream reader
            // Choose getInputStream for success (2xx codes), getErrorStream for errors (4xx, 5xx codes)
            InputStream inputStream = (responseCode >= 200 && responseCode < 300) ?
                                       connection.getInputStream() :
                                       connection.getErrorStream();

            if (inputStream != null) {
                 try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    // Print result
                    System.out.println("Response Body : " + response.toString());
                }
            } else {
                 System.out.println("No response body received.");
            }


            System.out.println("\nName: Abhay Raj\nEnrolment No: 00976803122");

        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("I/O Error during HTTP request: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) { // Catch unexpected errors
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // --- 8. Disconnect ---
            if (connection != null) {
                connection.disconnect();
                System.out.println("\nConnection disconnected.");
            }
        }
    }
}