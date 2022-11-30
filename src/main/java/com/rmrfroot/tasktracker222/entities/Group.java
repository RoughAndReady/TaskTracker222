package com.rmrfroot.tasktracker222.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Group {

    private static final String RANKS_FILE_NAME = "config/rank.txt";
    private static final String WORKCENTERS_FILE_NAME = "config/workcenter.txt";
    private static final String FLIGHTS_FILE_NAME = "config/flight.txt";
    private static final String TEAMS_FILE_NAME = "config/team.txt";

    private static final String LOCATIONS_FILE_NAME = "config/location.txt";

    public static String[] getRanks(){
        return readGroup(RANKS_FILE_NAME);
    }

    public static String[] getWorkcenters(){
        return readGroup(WORKCENTERS_FILE_NAME);
    }

    public static String[] getFlights(){
        return readGroup(FLIGHTS_FILE_NAME);
    }

    public static String[] getTeams(){
        return readGroup(TEAMS_FILE_NAME);
    }

    public static String[] getLocations() {
        return readGroup(LOCATIONS_FILE_NAME);
    }

    private static String[] readGroup(String file) {
        int ctr = 0;
        try {
            Scanner s1 = new Scanner(new File(file));
            while (s1.hasNextLine()) {
                ctr = ctr + 1;
                s1.nextLine();
            }
            String[] words = new String[ctr];
            Scanner s2 = new Scanner(new File(file));
            for (int i = 0; i < ctr; i = i + 1) {
                words[i] = s2.nextLine();
            }
            return words;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        }
        return null;
    }


}
