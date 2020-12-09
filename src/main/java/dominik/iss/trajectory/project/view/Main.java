package dominik.iss.trajectory.project.view;

import dominik.iss.trajectory.project.httpclient.IssHttpClient;
import dominik.iss.trajectory.project.httpclient.Location;
import dominik.iss.trajectory.project.service.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
     /*   IssHttpClient issHttpClient = new IssHttpClient();

        System.out.println("current location = " + issHttpClient.getApiIssLocation().get().getLocation());
        Location location = issHttpClient.getApiIssLocation().get().getLocation();
        issHttpClient
                .getApiIssPassTimes(location)
                .stream()
                .flatMap(p -> p.getResponse().stream())
                .map(p ->
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(p.getRisetime()*1000), TimeZone.getDefault().toZoneId())
                )
                .forEach(System.out::println);

      */

        Service service = new Service();
        double speed = service.getSpeedOfIss();
        System.out.println(speed);
        System.out.println(service.howManyPeopleInSpace());
    }
}
