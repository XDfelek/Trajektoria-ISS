package dominik.iss.trajectory.project.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import dominik.iss.trajectory.project.httpclient.people.IssHumans;
import dominik.iss.trajectory.project.passtime.PasstimeResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class IssHttpClient {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;


    public IssHttpClient() {
        this.httpClient = HttpClient.newBuilder().build();
        this.objectMapper = new ObjectMapper();
    }

    public Optional<IssCurrentLocation> getApiIssLocation(){
        String requestUrl= "http://api.open-notify.org/iss-now.json";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(requestUrl))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            IssCurrentLocation asObject = objectMapper.readValue(body, IssCurrentLocation.class);

            return Optional.ofNullable(asObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<PasstimeResponse> getApiIssPassTimes(Location location){
        String requestUrl= "http://api.open-notify.org/iss-pass.json?lat=" + location.getLatitude() + "&lon=" + location.getLongitude();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(requestUrl))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            PasstimeResponse asObject = objectMapper.readValue(body, PasstimeResponse.class);

            return Optional.ofNullable(asObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<IssHumans> getApiHumansInSpace(){
        String requestUrl= "http://api.open-notify.org/astros.json";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(requestUrl))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            IssHumans asObject = objectMapper.readValue(body, IssHumans.class);

            return Optional.ofNullable(asObject);
        } catch (Exception e) {
            e.getMessage();
        }
        return Optional.empty();
    }

}
