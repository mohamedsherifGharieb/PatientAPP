package sample.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sample.WeekPlan.Day;
import sample.WeekPlan.Program;
import sample.WeekPlan.Task;
import sample.WeekPlan.WeekPlan;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("deprecation")
public class PlanParser extends WeekPlan {


    public static WeekPlan parse(String path){

        WeekPlan wPlan = new WeekPlan();
        JSONParser parser = new JSONParser();
        try {
            System.out.println("Reading JSON file from Java program");
            JSONObject json = (JSONObject) parser.parse(new FileReader(path));
            JSONArray weekPlan = (JSONArray) json.get("weekPlan");
            ArrayList<Day> Days = new ArrayList<Day>();
            Iterator<JSONObject> iterator1 = weekPlan.iterator();
            while (iterator1.hasNext()) {
                JSONObject factObj = (JSONObject) iterator1.next();
                ArrayList<Task>Tasks = new ArrayList<Task>();
                Day day = new Day();
                String dayName = (String) factObj.get("dayName");
                day.setDayName(dayName);
                String dayID = (String) factObj.get("dayID");
                day.setID(dayID);
                //System.out.println(day.getDayID());
                JSONArray tasks = (JSONArray) factObj.get("tasks");
                Iterator<JSONObject> iterator2 = tasks.iterator();
                while (iterator2.hasNext()){
                    ArrayList<Program>Programs = new ArrayList<Program>();
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
                    //System.out.println(t.submitted);
                    JSONArray programs = (JSONArray) factObj2.get("programs");
                    Iterator<JSONObject> iterator3 = programs.iterator();
                    while (iterator3.hasNext()){
                        JSONObject factObj3 = (JSONObject) iterator3.next();
                        String baseName = (String) factObj3.get("BaseName");
                        Program p = new Program();
                        p.setBaseName(baseName);
                        //System.out.println(p.BaseName);
                        if(p != null){
                            Programs.add(p);
                        }
                    }
                    t.programs = Programs;
                    Tasks.add(t);
                    day.setDayTasks(Tasks);
                }
                Days.add(day);
                wPlan.setDays(Days);
            }
        }
        catch(RuntimeException e){
        	throw e;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        //Setting Task Duration for each Task
        //int numOfTasks = wPlan.getDay(0).getTasks().size();
        int totalDuration = 0;
        int durationInMinutes = 0;
        int nOm,nOh;

        for(int f = 0; f < wPlan.getDays().size(); f++) {
            for (int x = 0; x < wPlan.getDay(f).getTasks().size(); x++) {
                int sH = wPlan.getDay(f).getTask(x).getStartTimeH();
                int sM = wPlan.getDay(f).getTask(x).getStartTimeM();
                int eH = wPlan.getDay(f).getTask(x).getEndTimeH();
                int eM = wPlan.getDay(f).getTask(x).getEndTimeM();
                if (sH == eH) {
                    durationInMinutes = eM - sM;
                } else {
                    if ((eH - sH) == 1) {
                        nOm = (60 - sM) + eM;
                        durationInMinutes = nOm;
                        wPlan.getDay(f).getTask(x).setDurationInMinutes(durationInMinutes);
                        //System.out.println(wPlan.getDay(f).getTask(x).getDurationInMinutes());

                    } else {
                        nOh = eH - sH;
                        nOm = (60 - sM) + eM;
                        durationInMinutes = 60 * (nOh - 1) + nOm;
                        wPlan.getDay(f).getTask(x).setDurationInMinutes(durationInMinutes);
                        //System.out.println(wPlan.getDay(f).getTask(x).getDurationInMinutes());
                    }
                }
                totalDuration += durationInMinutes;
            }
        }
        //Setting total duration of tasks in a day
        for (int v = 0; v < wPlan.getDays().size(); v++){
            int total = 0;
            for(int u = 0; u < wPlan.getDay(v).getTasks().size(); u++){
                total += wPlan.getDay(v).getTask(u).getDurationInMinutes();
            }
            wPlan.getDay(v).setTotalTasksDuration(total);
            //System.out.println( wPlan.getDay(v).getTotalTasksDuration());
        }

        //Setting task percentage of day
        for(int q = 0; q < wPlan.getDays().size(); q++){
            for (int k = 0; k < wPlan.getDay(q).getTasks().size(); k++){
                wPlan.getDay(q).getTask(k).setPercentageOfDay((double)(wPlan.getDay(q).getTask(k).getDurationInMinutes()*100)/wPlan.getDay(q).getTotalTasksDuration());
                //System.out.println(wPlan.getDay(q).getTask(k).getPercentageOfDay());
            }
        }


        //System.out.print(wPlan.getDay(0).getTasks().get(6).getTaskName());
        return wPlan;
    }
}
