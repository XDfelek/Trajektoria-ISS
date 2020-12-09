package dominik.iss.trajectory.project.database;

import dominik.iss.trajectory.project.httpclient.IssCurrentLocation;

public interface IssDao {
    void save(IssCurrentLocationEntity data);
}
