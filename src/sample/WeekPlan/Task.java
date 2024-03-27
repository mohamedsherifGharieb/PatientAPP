package sample.WeekPlan;


import java.util.ArrayList;

public class Task extends Day {

    public int taskID;
	public String taskName;
    public String startTime, endTime;
    public String taskdescription;
    public int durationInMinutes;
    public int taskProgress;
    public String taskReveiw;
	public int startTimeH;
	public int startTimeM;
	public int endTimeH;
	public int endTimeM;
    public boolean submitted;
    public double percentageOfDay;
    public String status;
    public double submittedPercentage;
    public ArrayList<Program> programs;

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public void setTaskdescription(String taskdescription) {
		this.taskdescription = taskdescription;
	}

	public void setTaskProgress(int taskProgress) {
		this.taskProgress = taskProgress;
	}

	public void setTaskReveiw(String taskReveiw) {
		this.taskReveiw = taskReveiw;
	}

	public void setPrograms(ArrayList<Program> programs) {
		this.programs = programs;
	}

	public void setEndTimeM(int endTimeM) {
		this.endTimeM = endTimeM;
	}

	public void setEndTimeH(int endTimeH) {
		this.endTimeH = endTimeH;
	}

	public void setStartTimeH(int startTimeH) {
		this.startTimeH = startTimeH;
	}

	public void setStartTimeM(int startTimeM) {
		this.startTimeM = startTimeM;
	}

	public Task(String tID, String tName, String startTime, String endTime, ArrayList <Program> programs){
		taskID = Integer.parseInt(tID);
		taskName = tName;
		String[] st = startTime.split(":");
		this.startTimeH = Integer.parseInt(st[0]);
		this.startTimeM = Integer.parseInt(st[1]);
		String[] et = endTime.split(":");
		this.endTimeH = Integer.parseInt(et[0]);
		this.endTimeM = Integer.parseInt(et[1]);
        this.taskdescription = "";
		this.submitted = false;
		this.programs = programs;
        this.taskProgress = 0;
        this.taskReveiw = "";
		this.durationInMinutes = 90;
		this.percentageOfDay = 0;
		this.status = "Submitted";
		this.submittedPercentage = 0.0;
	}
	
	public Task(){
		
	}
	public void setStatus(String s){
		this.status = s;
	}
	public String getStatus(){
		return this.status;
	}

	public double getSubmittedPercentage() {
		return submittedPercentage;
	}

	public void setSubmittedPercentage(double submittedPercentage) {
		this.submittedPercentage = submittedPercentage;
	}
	public double getPercentageOfDay(){
		return this.percentageOfDay;
	}
	public void setPercentageOfDay(double p){
		this.percentageOfDay = p;
	}
	public String getTaskName() {
		return taskName;
	}
	public boolean isSubmitted(){
		return this.submitted;
	}
	public void setSubmitted(boolean s){
		this.submitted = s;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getEndTimeH() {
		return this.endTimeH;
	}
	public int getEndTimeM() {
		return this.endTimeM;
	}
	public void setEndTimeH(String endTime) {
		this.endTimeH = Integer.parseInt(endTime);
	}
	public void setEndTimM(String endTime) {
		this.endTimeM = Integer.parseInt(endTime);
	}

	public void setTaskID(String id){
		this.taskID = Integer.parseInt(id);
	}
	public int getTaskID(){
		return this.taskID;
	}
	
	public int getStartTimeH() {
		return this.startTimeH;
	}
	public int getStartTimeM() {
		return this.startTimeM;
	}

	public void setStartTimeH(String start) {
		this.startTimeH = Integer.parseInt(start);
	}
	public void setStartTimeM(String start) {
		this.startTimeM = Integer.parseInt(start);
	}
	public ArrayList<Program> getPrograms() {
		return programs;
	}

	public void addPrograms(ArrayList<Program> programs) {
		//this.programs.clear();
		this.programs.addAll(programs);
	}
	
	
	public void clearPrograms() {
		this.getPrograms().clear();
		
	}
	public void addProgram(Program p){
		this.programs.add(p);
	}
	public Program getProgram(int i){
		return this.programs.get(i);
	}
	public void setStartTime(String t){
		this.startTime = t;
		String[] st = t.split(":");
		this.startTimeH = Integer.parseInt(st[0]);
		this.startTimeM = Integer.parseInt(st[1]);
	}
	public void setEndTime(String t){
		this.endTime = t;
		String[] st = t.split(":");
		this.endTimeH = Integer.parseInt(st[0]);
		this.endTimeM = Integer.parseInt(st[1]);
	}
	public int getDurationInMinutes(){
		return this.durationInMinutes;
	}
	public void setDurationInMinutes(int x){
		this.durationInMinutes = x;
	}
    public void setDurationInMinutes(){
        int x = 0;
        if(this.startTimeH == this.endTimeH){
            x = this.endTimeM - this.startTimeM;
        }
        else {
            if ((this.endTimeH - this.startTimeH) >= 1)
                x = 60 * (this.endTimeH - this.startTimeH - 1) + (60 - this.startTimeM) + this.endTimeM;
        }
        this.durationInMinutes = x;
    }
    public String getTaskDescription(){
        return this.taskdescription;
    }

	public void setTaskDescription(String description) {
		this.taskdescription = description;
	}
    public int getTaskProgress(){
        return this.taskProgress;
    }
    public void setTaskReview(String r) {
        this.taskReveiw = r;
    }
    public String getTaskReview() {
        return this.taskReveiw;
    }

}
