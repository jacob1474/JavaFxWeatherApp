package sk.kasv.degro.worldappgui.Api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class ApiCaller {
    public static double getTemperature(String city, String countryCode3) {
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city+","+countryCode3+"&units=metric&appid=b4358eb8138f20206a7c5da7eac59a57");
        var client = HttpClient.newHttpClient();
        System.out.println("Sending request to: " + uri);
        var request = HttpRequest
                .newBuilder()
                .uri(uri)
                .header("accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONObject main = jsonResponse.getJSONObject("main");
                double temperature = main.getDouble("temp");
                return temperature;
            } else {
                System.out.println("Request failed with status code: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
