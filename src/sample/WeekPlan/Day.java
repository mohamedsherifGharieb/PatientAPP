package sample.WeekPlan;

import java.util.ArrayList;

public class Day extends WeekPlan{

	enum DAY_NAME {

		SUNDAY,
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,

	};

	public int dayID;
	public ArrayList<Task> tasks = new ArrayList<Task>();
	public double dayProgress;
	public int totalTasksDuration;
    public String dayName;
	public int[]timeSlots = new int[288];

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public int numberOfTasks;

	public int[] getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(int[] timeSlots) {
		this.timeSlots = timeSlots;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public void setDayID(int dayID) {
		this.dayID = dayID;
	}

	public Day(String name, int id){
		this.dayName = name;
		this.dayID = id;
		this.totalTasksDuration = 0;
		this.tasks = new ArrayList<Task>();
		this.totalTasksDuration = 0;
		this.timeSlots = new int[288];
	}

	public Day(String id){
        this.dayName = "";
		this.dayID = Integer.parseInt(id);
		//this.cal = cal; // i do not create a day with instance calendar anymore
		this.tasks = new ArrayList<Task>();
		this.dayProgress = 0;
	}
	public Day(){
		
	}
    public void setDayName(String day) {
       this.dayName = day;
    }
    public String getDayName(){
        return this.dayName;
    }


	public void setID(String id){
		this.dayID = Integer.parseInt(id);
	}
	public int getTotalTasksDuration(){
		return this.totalTasksDuration;
	}
    public void setTotalTasksDuration(int x){
        this.totalTasksDuration = x;
    }
	public void setTotalTasksDuration(){
		int total = 0;
        for(int x = 0; x < this.tasks.size(); x++){
            total += this.getTask(x).getDurationInMinutes();
        }
        this.totalTasksDuration = total;
	}
    public void settasksPercentage(){
        double per = 0;
        for(int x = 0; x < this.tasks.size(); x++){
            per = this.getTask(x).getDurationInMinutes()/this.totalTasksDuration;
            this.getTask(x).setPercentageOfDay(per);
        }
    }
	public int getDayID(){
		return this.dayID;
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}
	
	public void deleteTask(int i){
		this.tasks.remove(i);
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public Task getTask(int i){
		return this.tasks.get(i);
		
	}
	public void setDayProgress(double i){
		this.dayProgress = i;
	}
	public double getDayProgress(){
		return this.dayProgress;
	}
	public void setDayTasks(ArrayList<Task> t) {
		this.tasks = t;
	}

	public Task getTaskByName(String tName) {
		for (int x = 0; x < this.tasks.size(); x++){
			if (this.tasks.get(x).getTaskName().equals(tName)){
				//System.out.println(this.patients.get(x).getPatientName() + " <<<< found patient");
				return this.tasks.get(x);
			}
		}
		System.out.println("null <<<< didn't find task");
		return null;
	}
	public Task getTaskByID(int i){
		for(int x = 0; x < this.tasks.size(); x++){
			if(this.getTask(x).getTaskID() == i){
				return this.getTask(x);
			}

		}
        return null;
    }
    public void mergeTasks(Day d, Day m){
        for(int x = 0; x < d.getTasks().size(); x++){
            d.getTask(x).setSubmitted(m.getTask(x).isSubmitted());
            d.getTask(x).setTaskReveiw(m.getTask(x).getTaskReview());
            d.getTask(x).setSubmittedPercentage(m.getTask(x).getSubmittedPercentage());
            d.getTask(x).setStatus(m.getTask(x).getStatus());
        }
    }
}
