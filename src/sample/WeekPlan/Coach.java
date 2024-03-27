package sample.WeekPlan;

import java.util.ArrayList;

/**
 * Created by Johnny Bishara on 28/03/2016.
 */
public class Coach {

    public String coachName;
    public int coachID;
    public ArrayList<Patient> patients;

    public Coach(){
        this.patients = new ArrayList<Patient>();
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public int getCoachID() {
        return coachID;
    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }








}
