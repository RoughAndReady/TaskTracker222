package com.rmrfroot.tasktracker222.controllers;

import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;

@Controller
public class FileController {

    private static final String CONFIG_PATH = "./config";
    private static final String RANK_FILE = "./config/rank.txt";
    private static final String FLIGHT_FILE = "./config/flight.txt";
    private static final String WORKCENTER_FILE = "./config/workcenter.txt";
    private static final String TEAM_FILE = "./config/team.txt";
    private static final String LOCATION_FILE = "./config/location.txt";

    @PostConstruct
    public static void createConfigDirectory() {
        try {
            File configDirectory = new File(CONFIG_PATH);

            if(!configDirectory.exists()) {
                System.out.println("Attempting to create config folder...");

                configDirectory.mkdirs();
                createDefaultFiles();

                System.out.println("Created!");
            }
        } catch (Exception e) {
            System.out.println("Could not create config directory.");
            e.printStackTrace();
        }
    }

    private static void createDefaultFiles() {
        createDefaultFile(RANK_FILE);
        createDefaultFile(FLIGHT_FILE);
        createDefaultFile(WORKCENTER_FILE);
        createDefaultFile(TEAM_FILE);
        createDefaultFile(LOCATION_FILE);
    }

    private static void createDefaultFile(String directory) {
        File ranks = new File(directory);
        if (!ranks.exists()) {
            System.out.println("File " + directory + " does not exist. Writing it...");
            try {
                FileWriter writer = new FileWriter(directory);

                if (directory.equals(RANK_FILE)) {
                    writer.write(getDefaultRanks());
                } else if (directory.equals(FLIGHT_FILE)) {
                    writer.write(getDefaultFlights());
                } else if (directory.equals(WORKCENTER_FILE)) {
                    writer.write(getDefaultWorkcenters());
                } else if (directory.equals(TEAM_FILE)) {
                    writer.write(getDefaultTeams());
                } else if (directory.equals(LOCATION_FILE)) {
                    writer.write(getDefaultLocations());
                }

                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getDefaultRanks() {
        return "AB\n" +
                "Amn\n" +
                "A1C\n" +
                "SrA\n" +
                "SSgt\n" +
                "TSgt\n" +
                "MSgt\n" +
                "SMSgt\n" +
                "CMSgt\n" +
                "CCM\n" +
                "CMSAF\n" +
                "2d Lt\n" +
                "1st Lt\n" +
                "Capt\n" +
                "Maj\n" +
                "Lt Col\n" +
                "Col\n" +
                "Brig Gen\n" +
                "Maj Gen\n" +
                "Lt Gen\n" +
                "Gen\n" +
                "GOAF";
    }

    private static String getDefaultFlights() {
        return "CMD\n" +
                "SCO1\n" +
                "SCO2\n" +
                "SCP";
    }

    private static String getDefaultWorkcenters() {
        return "SCOP\n" +
                "SCOS\n" +
                "SCOX\n" +
                "SCOI\n" +
                "SCOT";
    }

    private static String getDefaultTeams() {
        return "222ALL\n" +
                "PTL\n" +
                "Training\n" +
                "Booster\n" +
                "Top3\n" +
                "Rising6\n" +
                "Flt Leadership\n" +
                "DOMOPS";
    }

    private static String getDefaultLocations() {
        return "The Oasis\n" +
                "DGS triangle\n" +
                "2145 (front)\n" +
                "Main Office\n" +
                "9IS Sm conf\n" +
                "9IS Lg conf\n" +
                "195 Gp conf\n" +
                "Wg Training rm\n" +
                "CORE";
    }
}
