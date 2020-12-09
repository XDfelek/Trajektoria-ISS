package dominik.iss.trajectory.project.view;

import dominik.iss.trajectory.project.httpclient.IssHttpClient;

public class Main {
    public static void main(String[] args) {
        IssHttpClient issHttpClient = new IssHttpClient();
        System.out.println(issHttpClient.getSpeedOfIss());
    }
}
