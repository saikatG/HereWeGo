package project.hackathon.herewego.Models;

import com.google.common.collect.HashBasedTable;
import project.hackathon.herewego.Models.Trip;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saikat on 7/24/2016.
 */
public class Trip {
    private String Id;
    private ArrayList<Location> locationsSequence;
    private HashBasedTable<String,String,Double> distanceMap;

    public Trip(String id, List<Location> locationsSequence, HashBasedTable<String,String,Double> distanceMap){
        this.Id=id;
        this.locationsSequence = new ArrayList<Location>(locationsSequence);
        this.distanceMap = HashBasedTable.create();
        this.distanceMap.putAll(distanceMap);
    }

    public ArrayList<Location> getLocations(){
        return this.locationsSequence;
    }

    public String getId(){
        return this.Id;
    }

    public HashBasedTable<String,String,Double> getDistanceMap(){
        return this.distanceMap;
    }


}
