package project.hackathon.herewego.Models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saikat on 7/24/2016.
 */
public class LocationsManager {
    private static LocationsManager sInstance;
    /**
     * Currently selected locations
     */
    private ArrayList<Location> chosenLocations;

    /**
     * Currently unselected locations
     */
    private ArrayList<Location> unselectedLocations;

    /**
     * Sequence of locations indexed by day numbers
     */
    private HashMap<Integer,ArrayList<Location>> itinerary;

}
