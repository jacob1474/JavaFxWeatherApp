package sk.kasv.degro.worldappgui.Api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;
import sk.kasv.degro.worldappgui.Util.CityNameEditor;

public class ApiCaller {
    public static double getTemperature(String city, String countryCode3) {
        city = CityNameEditor.editCityName(city);
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

    public static double getHumidity(String city, String countryCode3) {
        city = CityNameEditor.editCityName(city);
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city+","+countryCode3+"&units=metric&appid=b4358eb8138f20206a7c5da7eac59a57");
        var client = HttpClient.newHttpClient();
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
                double humidity = main.getDouble("humidity");
                return humidity;
            } else {
                System.out.println("Request failed with status code: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getWindSpeed(String city, String countryCode3) {
        city = CityNameEditor.editCityName(city);
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city+","+countryCode3+"&units=metric&appid=b4358eb8138f20206a7c5da7eac59a57");
        var client = HttpClient.newHttpClient();
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
                JSONObject wind = jsonResponse.getJSONObject("wind");
                double windSpeed = wind.getDouble("speed");
                return windSpeed;
            } else {
                System.out.println("Request failed with status code: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getWeatherInfo(String city, String countryCode3) {
        city = CityNameEditor.editCityName(city);
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city+","+countryCode3+"&units=metric&appid=b4358eb8138f20206a7c5da7eac59a57");
        var client = HttpClient.newHttpClient();
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
                JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
                String weatherInfo = weather.getString("description");
                return weatherInfo;
            } else {
                System.out.println("Request failed with status code: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static double getTemperatureFeel(String city, String countryCode3){
        city = CityNameEditor.editCityName(city);
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city+","+countryCode3+"&units=metric&appid=b4358eb8138f20206a7c5da7eac59a57");
        var client = HttpClient.newHttpClient();
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
                double temperatureFeel = main.getDouble("feels_like");
                return temperatureFeel;
            } else {
                System.out.println("Request failed with status code: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getWeatherIcon(String city, String countryCode3){
        city = CityNameEditor.editCityName(city);
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city+","+countryCode3+"&units=metric&appid=b4358eb8138f20206a7c5da7eac59a57");
        var client = HttpClient.newHttpClient();
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
                JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
                String imageCode = weather.getString("icon");
                return imageCode;
            } else {
                System.out.println("Request failed with status code: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static double getLongitude(String city, String countryCode3) {
        city = CityNameEditor.editCityName(city);
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city+","+countryCode3+"&units=metric&appid=b4358eb8138f20206a7c5da7eac59a57");
        var client = HttpClient.newHttpClient();
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
                JSONObject coord = jsonResponse.getJSONObject("coord");
                double longitude = coord.getDouble("lon");
                return longitude;
            } else {
                System.out.println("Request failed with status code: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double getLatitude(String city, String countryCode3) {
        city = CityNameEditor.editCityName(city);
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city+","+countryCode3+"&units=metric&appid=b4358eb8138f20206a7c5da7eac59a57");
        var client = HttpClient.newHttpClient();
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
                JSONObject coord = jsonResponse.getJSONObject("coord");
                double latitude = coord.getDouble("lat");
                return latitude;
            } else {
                System.out.println("Request failed with status code: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
