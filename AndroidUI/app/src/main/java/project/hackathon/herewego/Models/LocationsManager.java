package project.hackathon.herewego.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saikat on 7/24/2016.
 */
public class LocationsManager {
    private static LocationsManager sInstance = null;
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

    public ArrayList<Location> getUnselectedLocations() {
        return this.unselectedLocations;
    }

    public ArrayList<Location> getChosenLocations() {
        return this.chosenLocations;
    }

    public void initialize(){

    }
        public static LocationsManager getInstance()
        {
            if(sInstance == null){
                sInstance = new LocationsManager();
                sInstance.chosenLocations = new ArrayList<Location>();
                sInstance.unselectedLocations = new ArrayList<Location>();
            }
            return sInstance;
        }
    private LocationsManager() {
    }

    public void addLocations(ArrayList<Location> locations, boolean selected){
        if(selected){
            this.chosenLocations.addAll(locations);
        }
        else{
            this.unselectedLocations.addAll(locations);
        }
    }

    public void addLocation(Location location, boolean selected){
        if(selected)
            this.chosenLocations.add(location);
        else
            this.unselectedLocations.add(location);
    }

    public void removeLocation(int index, boolean selected){
        if(selected){
            this.unselectedLocations.add(chosenLocations.get(index));
            this.chosenLocations.remove(index);}
        else{
            this.chosenLocations.add(unselectedLocations.get(index));
            this.unselectedLocations.remove(index);}
    }

    public void removeLocation(Location location, boolean selected) {
        if (selected) {
            this.unselectedLocations.add(location);
            this.chosenLocations.remove(location);
        } else {
            this.chosenLocations.add(location);
            this.unselectedLocations.remove(location);
        }
    }
}
