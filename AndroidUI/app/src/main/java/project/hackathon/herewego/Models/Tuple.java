package project.hackathon.herewego.Models;

import java.io.Serializable;

/**
 * Created by sadutt on 7/26/2016.
 */
public class Tuple<X, Y> implements Serializable {
    public final X x;
    public final Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}
