package sample.WeekPlan;

import java.util.ArrayList;

/**
 * Created by Johnny Bishara on 28/03/2016.
 */
public class Patient extends Coach{

    public WeekPlan weekPlan;
    public int patientID;
    public String patientName;
    public String coachName;
    public ArrayList<WeekPlan> plans;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String file;

    public ArrayList<WeekPlan> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<WeekPlan> plans) {
        this.plans = plans;
    }

    public Patient(String name, String file){
        this.patientName = name;
       /* this.patientID = patientID;
        this.coachName = coachName;
        this.coachID = coachID;*/
        this.plans = new ArrayList<WeekPlan>();
    }

    public Patient(){
        plans = new ArrayList<WeekPlan>();

    }
    public WeekPlan getWeekPlanByName(String wPName) {
        for (int x = 0; x < this.plans.size(); x++){
            if (this.plans.get(x).getWeekPlanName().equals(wPName)){
                //System.out.println(this.patients.get(x).getPatientName() + " <<<< found patient");
                return this.plans.get(x);
            }
        }
        System.out.println("null <<<< didn't find patient");
        return null;
    }
    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public WeekPlan getWeekPlan() {
        return weekPlan;
    }

    public void setWeekPlan(WeekPlan weekPlan) {
        this.weekPlan = weekPlan;
    }





}
