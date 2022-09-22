package com.rmrfroot.tasktracker222;
import java.time.LocalDate;
import java.util.*;


public class Officer {

    


    //declare variables
    private boolean promotion_eligibility;
    private String name;
    private String address;
    private LocalDate Birthday;
    private String education;
    private String hobby;
    private String phone_number;
    private int contact_info_id;
    private int officer_info_id;
    private int general_info_id;

    private int timeline_info_id;
    private int re_dis_dem_info_id;
    private String rank;
    private int id;
    private String email;
    private String hometown;
    private boolean marital_status;
    private Map<String,String> familyMap;
    private String awards_annual;
    private String awards_pme;
    private String award_other;
    private int number_of_department;
    private String decoration_afam;
    private String  decoration_afcm;
    private String decoration_msm;
    private String decoration_other;
    private String wingman;
    private String professional_timeline_info;
    private String personal_timeline_info;
    private Date tafmsd;
    private String das;
    private Date ets;
    private String pet;
    private String preferences;


//constructor
public  Officer(){

    familyMap = new HashMap<String , String> ();

}

//set officer name
    public void setName(String name) {
        this.name = name;
    }







//get officer name
    public String getName() {
        return name;
    }







    //set address
    public void setAddress(String address) {
        this.address = address;
    }






    //get address
    public String getAddress() {
        return address;
    }








    //set birthday
    public void setBirthday(LocalDate birthday) {
        Birthday = birthday;
    }






    //get birthday
    public LocalDate getBirthday() {
        return Birthday;
    }






    //set contact info id
    public void setContact_info_id(int contact_info_id) {
        this.contact_info_id = contact_info_id;
    }







    //get contact info id
    public int getContact_info_id() {
        return contact_info_id;
    }







    //set general info id
    public void setGeneral_info_id(int general_info_id) {
        this.general_info_id = general_info_id;
    }







    //get general info id
    public int getGeneral_info_id() {
        return general_info_id;
    }





//set timeline info id
    public void setTimeline_info_id(int timeline_info_id) {
        this.timeline_info_id = timeline_info_id;
    }





    //get timeline info id
    public int getTimeline_info_id() {
        return timeline_info_id;
    }




//set the re_dis_dem_info id
    public void setRe_dis_dem_info_id(int re_dis_dem_info_id) {
        this.re_dis_dem_info_id = re_dis_dem_info_id;
    }





    //get the re_dis_dem_info_info
    public int getRe_dis_dem_info_id() {
        return re_dis_dem_info_id;
    }








    // set education
    public void setEducation(String education) {
        this.education = education;
    }







    //get education
    public String getEducation() {
        return education;
    }









    //set officer info id
    public void setOfficer_info_id(int officer_info_id) {
        this.officer_info_id = officer_info_id;
    }








    //get officer info id
    public int getOfficer_info_id() {
        return officer_info_id;
    }






    //set phone number
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }






    //get phone number
    public String getPhone_number() {
        return phone_number;
    }






    //set promotion eligibility
    public void setPromotion_eligibility(boolean promotion_eligibility) {
        this.promotion_eligibility = promotion_eligibility;
    }







    //check promotion eligibility
    public boolean isPromotion_eligibility() {
        return promotion_eligibility;
    }





    //set officer's hobby
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }





    //get officer's hobby
    public String getHobby() {
        return hobby;
    }





    //set officer's rank
    public void setRank(String rank) {
        this.rank = rank;
    }








    //get officer's rank
    public String getRank() {
        return rank;
    }









    //set officer's id
    public void setId(int id) {
        this.id = id;
    }









    //get officer's id
    public int getId() {
        return id;
    }








//set the officer's email
    public void setEmail(String email) {
        this.email = email;
    }





 //get the officer's email
    public String getEmail() {
        return email;
    }









//set hometown
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }









//get hometown
    public String getHometown() {
        return hometown;
    }








//set marital status
    public void setMarital_status(boolean marital_status) {
        this.marital_status = marital_status;
    }





//get marital status
    public boolean isMarital_status() {
        return marital_status;
    }




    //set the award annual
    public void setAwards_annual(String awards_annual) {
        this.awards_annual = awards_annual;
    }





    //get the annual award
    public String getAwards_annual() {
        return awards_annual;
    }





//set the number of department
    public void setNumber_of_department(int number_of_department) {
        this.number_of_department = number_of_department;
    }









//get the number of department
    public int getNumber_of_department() {
        return number_of_department;
    }







//set award pme
    public void setAwards_pme(String awards_pme) {
        this.awards_pme = awards_pme;
    }





//get award pme
    public String getAwards_pme() {
        return awards_pme;
    }







//set award other
    public void setAward_other(String award_other) {
        this.award_other = award_other;
    }







    //get award other
    public String getAward_other() {
        return award_other;
    }





//set decoration afam
    public void setDecoration_afam(String decoration_afam) {
        this.decoration_afam = decoration_afam;
    }




//get decoration afam
    public String getDecoration_afam() {
        return decoration_afam;
    }





//set decoration afcm
    public void setDecoration_afcm(String decoration_afcm) {
        this.decoration_afcm = decoration_afcm;
    }





//get decoration afcm
    public String getDecoration_afcm() {
        return decoration_afcm;
    }





//set decoration msm
    public void setDecoration_msm(String decoration_msm) {
        this.decoration_msm = decoration_msm;
    }






//get decoration msm
    public String getDecoration_msm() {
        return decoration_msm;
    }





//set decoration other
    public void setDecoration_other(String decoration_other) {
        this.decoration_other = decoration_other;
    }




//get decoration other
    public String getDecoration_other() {
        return decoration_other;
    }








//set the family member names and their relationship with the officer
    public void setFamilyMap(Map<String, String> familyMap) {
        this.familyMap = familyMap;
    }







//get the family member name and their relationship with the officer
    public Map<String, String> getFamilyMap() {
        return familyMap;
    }



//set the Wingman
    public void setWingman(String wingman) {
        this.wingman = wingman;
    }





//get the Wingman
    public String getWingman() {
        return wingman;
    }





    //set personal timeline info
    public void setPersonal_timeline_info(String personal_timeline_info) {
        this.personal_timeline_info = personal_timeline_info;
    }






//get personal timeline info
    public String getPersonal_timeline_info() {
        return personal_timeline_info;
    }





    //set professional timeline info
    public void setProfessional_timeline_info(String professional_timeline_info) {
        this.professional_timeline_info = professional_timeline_info;
    }





//get professional timeline info
    public String getProfessional_timeline_info() {
        return professional_timeline_info;
    }





    //set the total active federal military service date
    public void setTafmsd(Date tafmsd) {
        this.tafmsd = tafmsd;
    }





    //get the total active federal military service date
    public Date getTafmsd() {
        return tafmsd;
    }





//set the defensive aide system
    public void setDas(String das) {
        this.das = das;
    }





//get the defensive aide system
    public String getDas() {
        return das;
    }








    //set the Expiration Term of Service
    public void setEts(Date ets) {
        this.ets = ets;
    }






//get the Expiration Term of Service
    public Date getEts() {
        return ets;
    }






    //set the pet
    public void setPet(String pet) {
        this.pet = pet;
    }








    //get the pet
    public String getPet() {
        return pet;
    }






    //set preferences
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }





    //get preferences
    public String getPreferences() {
        return preferences;
    }





}
