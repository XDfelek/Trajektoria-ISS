package dominik.iss.trajectory.project.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    public Optional<IssCurrentLocation> getApitResponse (){
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

    public double getSpeedOfIss(){
        Optional <IssCurrentLocation> firstLocation = getApitResponse();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Optional <IssCurrentLocation> secondLocation = getApitResponse();
        long period = secondLocation.get().getTimestamp()-firstLocation.get().getTimestamp();
        double latitudeDifference = secondLocation.get().getLocation().getLatitude()-firstLocation.get().getLocation().getLatitude();
        double longtitudeDifference = secondLocation.get().getLocation().getLongitude()-firstLocation.get().getLocation().getLongitude();
        double distance = 111*Math.sqrt(Math.pow(latitudeDifference,2)+Math.pow(longtitudeDifference,2)) ;
        return distance/period;

    }
}
