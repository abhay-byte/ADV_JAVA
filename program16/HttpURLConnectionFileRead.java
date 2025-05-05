import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionFileRead {

    public static void main(String[] args) {
        HttpURLConnection connection = null;
        try {
            String urlString = "https://raw.githubusercontent.com/selva86/datasets/refs/heads/master/yoga.txt";
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "text/plain");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine).append("\n");
                    }

                    System.out.println("File Content:\n" + content.toString());
                }
            } else {
                System.out.println("Failed to retrieve the file. HTTP Response Code: " + responseCode);
            }
        } catch (Exception e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            System.out.println("Name: Abhay Raj\nEnrolment No: 00976803122");
        }
    }
}