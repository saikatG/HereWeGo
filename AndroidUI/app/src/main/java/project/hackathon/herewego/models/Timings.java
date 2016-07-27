package project.hackathon.herewego.Models;

import java.util.Date;

/**
 * Created by kabaska on 27-Jul-16.
 */
public class Timings {
    Date openingTime;
    Date closingTime;

    public Timings(Date openingTime, Date closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
}
