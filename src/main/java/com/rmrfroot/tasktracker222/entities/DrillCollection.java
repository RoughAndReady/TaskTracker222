package com.rmrfroot.tasktracker222.entities;

import java.util.ArrayList;
import java.util.List;

//drill collection
public class DrillCollection {
    private List<Drill> drillCollection;

    public DrillCollection() {
        drillCollection = new ArrayList<>();
    }

    public void add(Drill drill) {
        drillCollection.add(drill);
    }

    public int isEmpty() {
        if(drillCollection.size() == 0)
            return 0;
        return 1;
    }

    public int getSize() {
        return drillCollection.size();
    }
}
