package dominik.iss.trajectory.project.service;

import dominik.iss.trajectory.project.database.IssCurrentLocationEntity;
import dominik.iss.trajectory.project.database.IssDao;
import dominik.iss.trajectory.project.database.IssDaoHibernateImpl;
import dominik.iss.trajectory.project.httpclient.IssCurrentLocation;
import dominik.iss.trajectory.project.httpclient.IssHttpClient;
import dominik.iss.trajectory.project.httpclient.people.IssHumans;

import java.util.Optional;

public class Service {
    private IssDao issDaoHibernateImpl;
    private IssHttpClient issHttpClient;

    public Service() {
        this.issDaoHibernateImpl = new IssDaoHibernateImpl();
        this.issHttpClient = new IssHttpClient();
    }

    public double getSpeedOfIss() {
        Optional<IssCurrentLocation> firstLocation = issHttpClient.getApiIssLocation();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Optional<IssCurrentLocation> secondLocation = issHttpClient.getApiIssLocation();
        long period = secondLocation.get().getTimestamp() - firstLocation.get().getTimestamp();
        double latitudeDifference = secondLocation.get().getLocation().getLatitude() - firstLocation.get().getLocation().getLatitude();
        double longtitudeDifference = secondLocation.get().getLocation().getLongitude() - firstLocation.get().getLocation().getLongitude();
        double distance = 111 * Math.sqrt(Math.pow(latitudeDifference, 2) + Math.pow(longtitudeDifference, 2));
        issDaoHibernateImpl.save(convertToEntity(firstLocation.get()));
        issDaoHibernateImpl.save(convertToEntity(secondLocation.get()));
        return distance / period;

    }

    private IssCurrentLocationEntity convertToEntity(IssCurrentLocation issCurrentLocation) {
        return new IssCurrentLocationEntity(null, issCurrentLocation.getLocation().getLongitude(), issCurrentLocation.getLocation().getLatitude(), issCurrentLocation.getTimestamp());
    }

    public int howManyPeopleInSpace (){
        IssHumans humans = issHttpClient.getApiHumansInSpace().get();
        return humans.getNumber();
    }
}
