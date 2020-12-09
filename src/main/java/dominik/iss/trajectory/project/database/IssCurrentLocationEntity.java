package dominik.iss.trajectory.project.database;

import dominik.iss.trajectory.project.httpclient.IssCurrentLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "current_location")
public class IssCurrentLocationEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @Column
    private double longitude;
    @Column
    private double latitude;
    @Column
    private long timestamp;


}
