package sample.utils;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sample.WeekPlan.*;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by Johnny Bishara on 19/03/2016.
 */
public class LoadPlan extends Coach {

    public static ArrayList<WeekPlan> parse(Patient patient, String file){
        WeekPlan wPlan = null;
        LocalDate now = new LocalDate();
        LocalDate monday = now.withDayOfWeek(DateTimeConstants.MONDAY);
        System.out.println(monday);
        int number = 0;
        WeekPlan current = null;
        ArrayList<WeekPlan> rest = new ArrayList<WeekPlan>();
        ArrayList<WeekPlan> weekPlans = new ArrayList<WeekPlan>();
        JSONParser parser = new JSONParser();
        try {
            System.out.println("Reading JSON file from Java program");
            JSONObject json = (JSONObject) parser.parse(file);
            //System.out.println(file);
            if(!json.isEmpty()) {
                JSONArray plans = (JSONArray) json.get("plans");
                //System.out.println("LPP The number of plans is: " + plans.size());
                Iterator<JSONObject> iterator = plans.iterator();
                while(iterator.hasNext()) {
                	number++;
                    JSONObject jsonweek = (JSONObject) iterator.next();
                	System.out.println("weekplan:#### " + ((String) (jsonweek).get("weekPlanName")));

                    String[] wPlanStartDate = ((String) jsonweek.get("weekPlanSDate")).split("/");
                    if (monday.getDayOfMonth() <= Integer.parseInt(wPlanStartDate[0])
                            && (monday.getDayOfMonth() + 7) > Integer.parseInt(wPlanStartDate[0])
                            && (monday.getMonthOfYear() == Integer.parseInt(wPlanStartDate[1]))
                            && (monday.getYear() == Integer.parseInt(wPlanStartDate[2]))) {
                    
                        wPlan = new WeekPlan();
                        String weekPlanName = (String) jsonweek.get("weekPlanName");
                        System.out.println("weekplan name is: " + weekPlanName);
                        wPlan.setWeekPlanName(weekPlanName);
                        String weekPlanID = (String) jsonweek.get("weekPlanID");
                        wPlan.setWeekPlanID(Integer.parseInt(weekPlanID));
                        String weekPlanSDate = (String) jsonweek.get("weekPlanSDate");
                        wPlan.setWeekPlanSDate(weekPlanSDate);
                        String weekPlanEDate = (String) jsonweek.get("weekPlanEDate");
                        //System.out.println(weekPlanEDate + "    null??");
                        wPlan.setWeekPlanEDate(weekPlanEDate);
                        JSONArray weekPlan = (JSONArray) jsonweek.get("weekPlan");
                        ArrayList<Day> Days = new ArrayList<Day>();
                        Iterator<JSONObject> iterator1 = weekPlan.iterator();
                        while(iterator1.hasNext()) {
                            JSONObject factObj = (JSONObject) iterator1.next();
                            ArrayList<Task> dayTasks = new ArrayList<Task>();
                            Day day = new Day();
                            String dayName = (String) factObj.get("dayName");
                            day.setDayName(dayName);
                            String dayID = (String) factObj.get("dayID");
                            day.setID(dayID);
                            System.out.println(dayName);
                            String dayProgress = (String) factObj.get("dayProgress");
                            day.setDayProgress(Double.parseDouble(dayProgress));
                            String totalTasksDration = (String) factObj.get("totalTasksDration");
                            day.setTotalTasksDuration(Integer.parseInt(totalTasksDration));
                            String numberOfTasks = (String) factObj.get("numberOfTasks");
                            day.setNumberOfTasks(Integer.parseInt(numberOfTasks));
                            String[] dTimeSlots = ((String) factObj.get("timeSlots")).split(",");
                            if (!((String) factObj.get("timeSlots")).equals("")) {
                                for (int x = 0; x < dTimeSlots.length; x++) {
                                    day.getTimeSlots()[x] = Integer.parseInt(dTimeSlots[x]);
                                }
                            }
                            //System.out.println(day.getDayID());
                            JSONArray tasks = (JSONArray) factObj.get("tasks");
                            Iterator<JSONObject> iterator2 = tasks.iterator();
                            while(iterator2.hasNext()) {
                                ArrayList<Program> Programs = new ArrayList<Program>();
                                Task t = new Task();
                                JSONObject factObj2 = (JSONObject) iterator2.next();
                                String taskID = (String) factObj2.get("taskID");
                                t.setTaskID(taskID);
                                //System.out.println(t.taskID);
                                String taskName = (String) factObj2.get("taskName");
                                t.setTaskName(taskName);
                                //System.out.println(t.getTaskName());
                                String startTime = (String) factObj2.get("startTime");
                                t.setStartTime(startTime);
                                //System.out.println(t.startTimeH + ":" + t.startTimeM);
                                String endTime = (String) factObj2.get("endTime");
                                t.setEndTime(endTime);
                                //System.out.println(t.endTimeH + ":" + t.endTimeM);
                                //String finished = (String) factObj2.get("finished");
                                //t.setFinished(finished);
                                String description = (String) factObj2.get("description");
                                t.setTaskDescription(description);
                                String taskDuration = (String) factObj2.get("taskDuration");
                                t.setDurationInMinutes(Integer.parseInt(taskDuration));
                                String taskProgress = (String) factObj2.get("taskProgress");
                                t.setTaskProgress(Integer.parseInt(taskProgress));
                                String taskReview = (String) factObj2.get("taskReview");
                                t.setTaskReveiw(taskReview);
                               
                                String submitted = (String) factObj2.get("submitted");
                                t.setSubmitted(Boolean.parseBoolean(submitted));
                                String percentageOfDay = (String) factObj2.get("percentageOfDay");
                                t.setPercentageOfDay(Double.parseDouble(percentageOfDay));
                                String status = (String) factObj2.get("status");
                                if(status.length() > 10){
                                	System.out.println("status"+status);
                                	
                                    String[] statusTrimmed = status.split("%");
                                    
                                    for(int st = 0; st < statusTrimmed.length;st++) 
                                    	System.out.println("st: " + st + " " + statusTrimmed[st]);
                                    status = statusTrimmed[0] + "% " + statusTrimmed[1] ;
                                }

                                t.setStatus(status);
                                String submittedPercentage = (String) factObj2.get("submittedPercentage");
                                t.setSubmittedPercentage(Double.parseDouble(submittedPercentage));
                                //System.out.println(t.submitted);
                                JSONArray programs = (JSONArray) factObj2.get("programs");
                                Iterator<JSONObject> iterator3 = programs.iterator();
                                while (iterator3.hasNext()) {
                                    JSONObject factObj3 = (JSONObject) iterator3.next();
                                    String baseName = (String) factObj3.get("baseName");
                                    Program p = new Program();
                                    p.setBaseName(baseName);
                                    //System.out.println(p.BaseName);
                                    Programs.add(p);
                                }
                                t.programs = Programs;
                                dayTasks.add(t);
                                System.out.println(dayTasks.size());
                                day.setDayTasks(dayTasks);
                            }
                            Days.add(day);
                        }
                        wPlan.setDays(Days);
//                      
                        current = wPlan;
                       // weekPlans.add(wPlan);
                    }
                    else{
                    	wPlan = new WeekPlan();
                        String weekPlanName = (String) jsonweek.get("weekPlanName");
                        System.out.println("weekplan name is: " + weekPlanName);
                        wPlan.setWeekPlanName(weekPlanName);
                        String weekPlanID = (String) jsonweek.get("weekPlanID");
                        wPlan.setWeekPlanID(Integer.parseInt(weekPlanID));
                        String weekPlanSDate = (String) jsonweek.get("weekPlanSDate");
                        wPlan.setWeekPlanSDate(weekPlanSDate);
                        String weekPlanEDate = (String) jsonweek.get("weekPlanEDate");
                        //System.out.println(weekPlanEDate + "    null??");
                        wPlan.setWeekPlanEDate(weekPlanEDate);
                        JSONArray weekPlan = (JSONArray) jsonweek.get("weekPlan");
                        ArrayList<Day> Days = new ArrayList<Day>();
                        Iterator<JSONObject> iterator1 = weekPlan.iterator();
                        while(iterator1.hasNext()) {
                            JSONObject factObj = (JSONObject) iterator1.next();
                            ArrayList<Task> dayTasks = new ArrayList<Task>();
                            Day day = new Day();
                            String dayName = (String) factObj.get("dayName");
                            day.setDayName(dayName);
                            String dayID = (String) factObj.get("dayID");
                            day.setID(dayID);
                            System.out.println(dayName);
                            String dayProgress = (String) factObj.get("dayProgress");
                            day.setDayProgress(Double.parseDouble(dayProgress));
                            String totalTasksDration = (String) factObj.get("totalTasksDration");
                            day.setTotalTasksDuration(Integer.parseInt(totalTasksDration));
                            String numberOfTasks = (String) factObj.get("numberOfTasks");
                            day.setNumberOfTasks(Integer.parseInt(numberOfTasks));
                            String[] dTimeSlots = ((String) factObj.get("timeSlots")).split(",");
                            if (!((String) factObj.get("timeSlots")).equals("")) {
                                for (int x = 0; x < dTimeSlots.length; x++) {
                                    day.getTimeSlots()[x] = Integer.parseInt(dTimeSlots[x]);
                                }
                            }
                            //System.out.println(day.getDayID());
                            JSONArray tasks = (JSONArray) factObj.get("tasks");
                            Iterator<JSONObject> iterator2 = tasks.iterator();
                            while(iterator2.hasNext()) {
                                ArrayList<Program> Programs = new ArrayList<Program>();
                                Task t = new Task();
                                JSONObject factObj2 = (JSONObject) iterator2.next();
                                String taskID = (String) factObj2.get("taskID");
                                t.setTaskID(taskID);
                                //System.out.println(t.taskID);
                                String taskName = (String) factObj2.get("taskName");
                                t.setTaskName(taskName);
                                //System.out.println(t.getTaskName());
                                String startTime = (String) factObj2.get("startTime");
                                t.setStartTime(startTime);
                                //System.out.println(t.startTimeH + ":" + t.startTimeM);
                                String endTime = (String) factObj2.get("endTime");
                                t.setEndTime(endTime);
                                //System.out.println(t.endTimeH + ":" + t.endTimeM);
                                //String finished = (String) factObj2.get("finished");
                                //t.setFinished(finished);
                                String description = (String) factObj2.get("description");
                                t.setTaskDescription(description);
                                String taskDuration = (String) factObj2.get("taskDuration");
                                t.setDurationInMinutes(Integer.parseInt(taskDuration));
                                String taskProgress = (String) factObj2.get("taskProgress");
                                t.setTaskProgress(Integer.parseInt(taskProgress));
                                String taskReview = (String) factObj2.get("taskReview");
                                t.setTaskReveiw(taskReview);
                               
                                String submitted = (String) factObj2.get("submitted");
                                t.setSubmitted(Boolean.parseBoolean(submitted));
                                String percentageOfDay = (String) factObj2.get("percentageOfDay");
                                t.setPercentageOfDay(Double.parseDouble(percentageOfDay));
                                String status = (String) factObj2.get("status");
                                if(status.length() > 10){
                                	System.out.println("status"+status);
                                	
                                    String[] statusTrimmed = status.split("%");
                                    
                                    for(int st = 0; st < statusTrimmed.length;st++) 
                                    	System.out.println("st: " + st + " " + statusTrimmed[st]);
                                    status = statusTrimmed[0] + "% " + statusTrimmed[1] ;
                                }

                                t.setStatus(status);
                                String submittedPercentage = (String) factObj2.get("submittedPercentage");
                                t.setSubmittedPercentage(Double.parseDouble(submittedPercentage));
                                //System.out.println(t.submitted);
                                JSONArray programs = (JSONArray) factObj2.get("programs");
                                Iterator<JSONObject> iterator3 = programs.iterator();
                                while (iterator3.hasNext()) {
                                    JSONObject factObj3 = (JSONObject) iterator3.next();
                                    String baseName = (String) factObj3.get("baseName");
                                    Program p = new Program();
                                    p.setBaseName(baseName);
                                    //System.out.println(p.BaseName);
                                    Programs.add(p);
                                }
                                t.programs = Programs;
                                dayTasks.add(t);
                                System.out.println(dayTasks.size());
                                day.setDayTasks(dayTasks);
                            }
                            Days.add(day);
                        }
                        wPlan.setDays(Days);
                        
                        rest.add(wPlan);
                    }
                   
                }
                if(current != null)
                weekPlans.add(current);
                for(int i = 0; i < rest.size();i++){
                	weekPlans.add(rest.get(i));
                }
                patient.setPlans(weekPlans);
       

            }
        }
        catch(RuntimeException e){
        	throw e;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
//        
        return weekPlans;
    }
}
