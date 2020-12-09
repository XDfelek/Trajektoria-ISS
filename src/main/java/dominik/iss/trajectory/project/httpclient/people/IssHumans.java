package dominik.iss.trajectory.project.httpclient.people;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IssHumans {
private String message;
private int number;
private List<Person> people;
}
