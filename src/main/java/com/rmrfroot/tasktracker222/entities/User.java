package com.rmrfroot.tasktracker222.entities;


import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.annotation.PostConstruct;
import javax.persistence.*;

//import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
@TypeDef(
        name = "team",
        typeClass = ListArrayType.class
)
public class User implements Comparable<User>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mil_email")
    private String militaryEmail;

    @Column(name = "civ_email")
    private String civilianEmail;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "office_number")
    private String officeNumber;

    @Column(name = "rank")
    private String rank;

    @Column(name = "work_center")
    private String workCenter;

    @Column(name = "flight")
    private String flight;

    @Type(type = "team")
    @Column(name = "teams", columnDefinition = "text[]")
    private ArrayList<String> teams;

    @ManyToMany(
            fetch=FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name="users_drill_schedule",
            joinColumns =@JoinColumn(name="users_id"),
            inverseJoinColumns = @JoinColumn(name="drill_schedule_id")
    )
    private List<DrillSchedules> drillSchedulesList;

    private static final String TEAM_LIST_FILENAME = "team.txt";

    public User() {

    }

    public User(String userName, String firstName, String lastName, String militaryEmail, String civilianEmail, String email,
                String phoneNumber, String officeNumber, String rank, String workCenter,
                String flight, ArrayList<String> teams) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.militaryEmail = militaryEmail;
        this.civilianEmail = civilianEmail;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.officeNumber = officeNumber;
        this.rank = rank;
        this.workCenter = workCenter;
        this.flight = flight;
        //this.teamList = teamList;
        //this.team = team;
        this.teams = teams;
    }

    @Override public int compareTo(User comparedUser){
        return this.getFirstName().compareTo(comparedUser.getFirstName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static boolean isValidEmailAddrRegex(String emailValidationRegex, String emailAddrToValidate) {
        return Pattern.matches(emailValidationRegex, emailAddrToValidate);
    }
    public String getCivilianEmail() {
        return civilianEmail;
    }

    public void setCivilianEmail(String civilianEmail) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(isValidEmailAddrRegex(regexPattern, civilianEmail)) {
            this.civilianEmail = civilianEmail;
        }
        else {
            this.email = email;
        }
    }
    public String getMilitaryEmail() {
        return militaryEmail;
    }

    public void setMilitaryEmail(String militaryEmail) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(isValidEmailAddrRegex(regexPattern, militaryEmail) || militaryEmail.isBlank()) {
            this.militaryEmail = militaryEmail;
        }
        else {
            throw new IllegalArgumentException("Not a valid email");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.matches("[a-zA-Z]+")) {
            this.firstName = firstName;
        }
        else {
            throw new IllegalArgumentException("Not a valid name. First name should only contain letters");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.matches("[a-zA-Z]+")) {
            this.lastName = lastName;
        }
        else {
            throw new IllegalArgumentException("Not a valid name. Last name should only contain letters");
        }
    }



//    public String getRegister_date() {
//        return register_date;
//}

//    public void setRegister_date(String register_date) {
//        still working to fix this to work properly
//        String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";
//        if (register_date.matches(datePattern)) {
//            this.register_date = register_date;
//        }
//        else {
//            throw new IllegalArgumentException("Register date must be MM/DD/YYYY. Not a valid date.");
//        }
//        this.register_date = register_date;
//   }

//    public String getUpdate_date() {
//        return update_date;
//   }

//    public void setUpdate_date(String update_date) {
//        still working to fix this to work properly
//        String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";
//        if (update_date.matches(datePattern)) {
//            this.update_date = update_date;
//        }
//        else {
//            throw new IllegalArgumentException("Update date must be MM/DD/YYYY. Not a valid date.");
//        }
//        this.update_date = update_date;
//    }


    public String getRank() {
        return rank;
    }

    public void setRank(String rank) throws FileNotFoundException {
//        File fileText = new File("rank.txt");
//        Scanner s = new Scanner(fileText);
//        int r = 0;
//
//        while(s.hasNextLine()){
//            if(rank.equals(s.nextLine().trim())){
//                r += 1;
//            }
//        }
//        if (r == 1) {
//            this.rank = rank;
//        }
//        else {
//            throw new IllegalArgumentException("Not a valid Rank.");
//        }
        this.rank = rank;
    }

    public String getWorkCenter() {
        return workCenter;
    }

    public void setWorkCenter(String workCenter) throws FileNotFoundException {
//        File fileText = new File("workcenter.txt");
//        Scanner s = new Scanner(fileText);
//        int w = 0;
//
//        while(s.hasNextLine()){
//            if(workCenter.equals(s.nextLine().trim())){
//                w += 1;
//            }
//        }
//        if (w == 1) {
//            this.workCenter = workCenter;
//        }
//        else {
//            throw new IllegalArgumentException("Not a valid workcenter.");
//        }
        this.workCenter = workCenter;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) throws FileNotFoundException {
//        File fileText = new File("flight.txt");
//        Scanner s = new Scanner(fileText);
//        int f = 0;
//
//        while(s.hasNextLine()){
//            if(flight.equals(s.nextLine().trim())){
//                f += 1;
//            }
//        }
//        if (f == 1) {
//            this.flight = flight;
//        }
//        else {
//            throw new IllegalArgumentException("Not a valid flight.");
//        }
        this.flight = flight;
    }

//    public ArrayList<String> getTeamList() {
//        return teamList;
//    }
//
//    public void setTeamList(ArrayList<String> teamList) {
//        this.teamList = teamList;
//    }

//    public String getTeam() {
//        return team;
//    }
//    public void setTeam(String team) throws FileNotFoundException {
//        File fileText = new File("team.txt");
//        Scanner s = new Scanner(fileText);
//        int t = 0;
//
//        while(s.hasNextLine()){
//            if(team.equals(s.nextLine().trim())){
//                t += 1;
//            }
//        }
//        if (t <= 1) {
//            this.team = team;
//        }
//        else {
//            throw new IllegalArgumentException("Not a valid team.");
//        }
//    }

    public static void addTeam() {
        Scanner s = new Scanner(System.in);
        String teams = s.nextLine();
        ArrayList<String> list = new ArrayList<>(Arrays.asList(teams.split("\\s*,\\s*")));
        Set<String> aSet = new HashSet<>(list);
        list.add(String.valueOf(aSet));
    }

    //not used yet, ready to implement when needed
    //    random uuid
    private static void generateUUID() {
        UUID uuid = UUID.randomUUID();
    }

  
    //figured out a way to read user groups from text file, but not sure
    //how to implement to the project.
    //    public static void main (String[] args) {
//        String[] words = readGroup("group.txt");
//        for (int i = 0; i < words.length; i = i + 1){
//            System.out.println(words[i]);
//        }
//        System. out.println(Arrays.toString(words));
//    }
    public static String[] readGroup(String file) {
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
            System.out.println("File not found");
        }
        return null;
    }

    public static void readTeamsFromFile(){
        try {
            File curDir = new File(".");
            File[] filesList = curDir.listFiles();
            for (File f : filesList) {
                if (f.isFile()) {
                    System.out.println(f.getName());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<DrillSchedules> getDrillSchedulesList() {
        return drillSchedulesList;
    }

    public void setDrillSchedulesList(List<DrillSchedules> drillSchedulesList) {
        this.drillSchedulesList = drillSchedulesList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addDrillSchedule(DrillSchedules drillSchedules){
        if(drillSchedulesList ==null){
            drillSchedulesList=new ArrayList<>();
        }
        drillSchedulesList.add(drillSchedules);
    }
    public boolean findDrillScheduleById(int id){
        boolean check=false;
        for(DrillSchedules drillSchedules:getDrillSchedulesList()){
            if(drillSchedules.getId()==id){
                check=true;
                break;
            }
        }
        return check;
    }
}