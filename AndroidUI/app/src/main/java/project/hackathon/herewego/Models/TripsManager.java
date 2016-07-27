package project.hackathon.herewego.Models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sadutt on 7/27/2016.
 */
public class TripsManager {
    private static int tripId=1;
    private static ArrayList<Trip> savedtrips;
    private static ArrayList<Trip> trips;

    public static Integer createTrip(HashMap<Integer, ArrayList<Location> > locationsSequence){
        Trip trip = new Trip(tripId++, locationsSequence);
        if(trips!=null){
            trips.add(trip);
        }
        else{
            trips = new ArrayList<Trip>();
            trips.add(trip);
        }
        return trip.getId();
    }
}
