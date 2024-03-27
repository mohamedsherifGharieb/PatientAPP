package sample.WeekPlan;

import java.time.LocalDate;
import java.util.ArrayList;


public class WeekPlan extends Coach{
	public int weekPlanID;
	public String weekPlanSDate;
	public String weekPlanEDate;

    public String getWeekPlanName() {
        return weekPlanName;
    }

    public void setWeekPlanName(String weekPlanName) {
        this.weekPlanName = weekPlanName;
    }

    public String getWeekPlanEDate() {
        return weekPlanEDate;
    }

    public void setWeekPlanEDate(String weekPlanEDate) {
        this.weekPlanEDate = weekPlanEDate;
    }

    public String getWeekPlanSDate() {
        return weekPlanSDate;
    }

    public void setWeekPlanSDate(String weekPlanSDate) {
        this.weekPlanSDate = weekPlanSDate;
    }

    public int getWeekPlanID() {
        return weekPlanID;
    }

    public void setWeekPlanID(int weekPlanID) {
        this.weekPlanID = weekPlanID;
    }

    public String weekPlanName;
	public ArrayList<Day> days;
	
	public WeekPlan(){
		this.days = new ArrayList<Day>();
	}
	public WeekPlan(String name, LocalDate startDate, int id){
		this.weekPlanID = id;
		this.weekPlanName = name;
		this.weekPlanSDate = startDate.getDayOfMonth() + "/" + startDate.getMonthValue() + "/" + startDate.getYear();
		System.out.println(startDate + "   before <<<<<<");
		this.addDays();
		LocalDate endDate = startDate.plusDays(6);
		System.out.println(endDate + "   after >>>>>>");
		this.weekPlanEDate = endDate.getDayOfMonth() + "/" + endDate.getMonthValue() + "/" + endDate.getYear();

	}

	public ArrayList<Day> getDays() {
		return days;
	}
	public void addDay(Day day){
		this.days.add(day);
	}
	public void setPlanID(int id){
		this.weekPlanID = id;
	}
	public int getID(){
		return this.weekPlanID;
	}
	public Day getDay(Day.DAY_NAME day) {

		switch (day) {

			case FRIDAY:
				break;

			case SATURDAY:
				break;

			case SUNDAY:
				break;

			case MONDAY:
				break;

			case TUESDAY:
				break;

			case WEDNESDAY:
				break;

			case THURSDAY:
				break;
		}
		return null;
	}
	public Day getDay(int i){
		//System.out.println(this.days.size()+ "Days Size<<<<<<<<<<<<<<<<<<<<<<<<");
		return this.days.get(i);
	}
	public void setDays(ArrayList<Day> days) {
		this.days = days;
	}
	public Day getDayById(int id){
		for(int x = 0; x < this.days.size(); x++){
			if(this.getDay(x).getDayID() == id){
				return this.getDay(x);
			}
		}
		return null;
	}


    public Day getDayByName(String name){
        for(int x = 0; x < this.days.size(); x++){
            if((this.getDay(x).getDayName()).equals(name)){
                return this.getDay(x);
            }
        }
        return null;
    }
    private void addDays(){
        this.days = new ArrayList<Day>();
        this.addDay(new Day("Sunday", 1));
        this.addDay(new Day("Monday", 2));
        this.addDay(new Day("Tuesday", 3));
        this.addDay(new Day("Wednesday", 4));
        this.addDay(new Day("Thursday", 5));
        this.addDay(new Day("Friday", 6));
        this.addDay(new Day("Saturday", 7));
    }

	
}