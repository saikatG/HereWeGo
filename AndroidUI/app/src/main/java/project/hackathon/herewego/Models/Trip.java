package project.hackathon.herewego.Models;

import com.google.common.collect.HashBasedTable;
import project.hackathon.herewego.Models.Trip;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saikat on 7/24/2016.
 */
public class Trip {
    private int Id;
    private HashMap<Integer, ArrayList<Location> > locationsSequence;

    public Trip(int id, HashMap<Integer, ArrayList<Location> > locationsSequence){
        this.Id=id;
        this.locationsSequence = locationsSequence;
    }

    public HashMap<Integer, ArrayList<Location> > getLocations(){
        return this.locationsSequence;
    }
    public int getId(){
        return this.Id;
    }
}
