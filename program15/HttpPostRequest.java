import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostRequest {

    public static void main(String[] args) {
        HttpURLConnection connection = null;
        try {
            String urlString = "https://jsonplaceholder.typicode.com/posts";
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

            // Write JSON input to the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get and print the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read and print the response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine).append("\n");
                }

                System.out.println("Response: " + response.toString());
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            System.out.println("Name: Abhay Raj\nEnrolment No: 00976803122");
        }
    }
}
