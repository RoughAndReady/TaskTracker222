package com.rmrfroot.tasktracker222.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Store list of Drills</h1>
 *<p> Allow the users to add drill to the drillCollection</p>
 * @author Brian Frey
 */
public class DrillCollection {
    private List<Drill> drillCollection;

  /**
 *Class Constructor create an ArrayList called drillCollection 
 *@see DrillCollection()
*/  
    public DrillCollection() {
        drillCollection = new ArrayList<>();
    }

   /**
 * This method add a new drill to the drillCollection
 * @param  drill 
 * @see add(Drill drill)
 */
    public void add(Drill drill) {
        drillCollection.add(drill);
    }

    /**
 * This method check whether the drillCollection is empty
 * @return 0 or 1 . If return value is 0 mean drillCollection is empty or return value is 1 means drillCollection is not empty
 * @see isEmpty()
 */
    public int isEmpty() {
        if(drillCollection.size() == 0)
            return 0;
        return 1;
    }

  /**
 * This method check how many number of drills is in the drillCollection
 * @return size of the drillCollection
 * @see getSize()
 */
    public int getSize() {
        return drillCollection.size();
    }
}
