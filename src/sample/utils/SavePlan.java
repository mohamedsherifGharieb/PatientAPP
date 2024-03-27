package sample.utils;

import sample.WeekPlan.Patient;

import java.io.*;
/**
 * Created by Johnny Bishara on 16/03/2016.
 */
public class SavePlan extends Patient{
    public static void savePlan(Patient p) throws IOException {
        String patientFile = "";
        
        try {
            File pFile = new File(p.patientName + ".txt");
            FileWriter s = new FileWriter(pFile);
            BufferedWriter writer = new BufferedWriter(s);
            writer.write("{");
           // //                            writer.newLine();;
            writer.write("\"patientName\": \"" + p.getPatientName() + "\",");
           // writer.newLine();
            writer.write("\"coachName\": \"" + p.getCoachName() + "\",");
           // writer.newLine();
            writer.write("\"patientID\": \"" + p.getPatientID() + "\",");
           // writer.newLine();
            writer.write("\"plans\":");
           // writer.newLine();
            writer.write("[");
          //  writer.newLine();
            int t = 0;
            int numberOfPlans = p.getPlans().size();
            System.out.println("SPP The number of plans is: " +numberOfPlans);
            for(int f = 0; f < p.plans.size(); f++){
                if(f == numberOfPlans-1){
                    writer.write("{");
                   // writer.newLine();
                    writer.write("\"weekPlanName\": \"" + p.plans.get(f).getWeekPlanName() + "\",");
                   // writer.newLine();
                    writer.write("\"weekPlanID\": \"" + p.plans.get(f).getWeekPlanID() + "\",");
//                   writer.newLine();                 
                    writer.write("\"weekPlanSDate\": \"" + p.plans.get(f).getWeekPlanSDate()+ "\",");
//                    writer.newLine();
                    writer.write("\"weekPlanEDate\": \"" + p.plans.get(f).getWeekPlanEDate() + "\",");
//                    writer.newLine();
                    writer.write("\"weekPlan\":");
//                    writer.newLine();
                    writer.write("[");
//                    writer.newLine();
                    int y = 0;
                    int numberOfDays = p.plans.get(f).days.size();
                    for (int i = 0; i < p.plans.get(f).getDays().size(); i++) {
                        if (y == numberOfDays-1) {
                            writer.write("{");
 //                           writer.newLine();
                            writer.write("\"dayName\": \"" + p.plans.get(f).getDay(i).getDayName() + "\",");
//                           writer.newLine();
                            writer.write("\"dayID\": \"" + p.plans.get(f).getDay(i).getDayID() + "\",");
//                            writer.newLine();
                            writer.write("\"dayProgress\" : \"" + p.plans.get(f).getDay(i).getDayProgress() + "\",");
//                            writer.newLine();
                            writer.write("\"totalTasksDration\": \"" + p.plans.get(f).getDay(i).getTotalTasksDuration() + "\",");
//                            writer.newLine();
                            writer.write("\"numberOfTasks\": \"" + p.plans.get(f).getDay(i).getNumberOfTasks() + "\",");
//                            writer.newLine();
                            String dTimeSlots = "";
                            StringBuffer buff = new StringBuffer();
                            for(int x = 0; x < p.plans.get(f).getDay(i).getTimeSlots().length; x++){
                            	
                                if(x==p.plans.get(f).getDay(i).getTimeSlots().length-1){
                                   // dTimeSlots += p.plans.get(f).getDay(i).getTimeSlots()[x];
                                	buff.append(p.plans.get(f).getDay(i).getTimeSlots()[x]);
                                }
                                else{
                                	buff.append(p.plans.get(f).getDay(i).getTimeSlots()[x] + ",");
                                   // dTimeSlots += p.plans.get(f).getDay(i).getTimeSlots()[x] + ",";
                                }
                            }
                            dTimeSlots = buff.toString();
                            writer.write("\"timeSlots\": \"" + dTimeSlots + "\",");
                            writer.write("\"tasks\" : [");
//                            writer.newLine();
                            int x = 0;
                            int numberOfTasks = p.plans.get(f).getDay(i).getTasks().size();
                            for (int j = 0; j < p.plans.get(f).getDay(i).getTasks().size(); j++) {
                                if (x == numberOfTasks - 1) {
                                    writer.write("{");
//                                    writer.newLine();
                                    writer.write("\"taskID\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskID() + "\",");
//                                    writer.newLine();
                                    writer.write("\"taskName\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskName() + "\",");
//                                    writer.newLine();
                                    writer.write("\"startTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTime() + "\",");
//                                    writer.newLine();
                                    writer.write("\"endTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTime() + "\",");
//                                    writer.newLine();
                                    writer.write("\"description\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskDescription() + "\",");
//                                    writer.newLine();
                                    writer.write("\"taskDuration\" : \"" + p.plans.get(f).getDay(i).getTask(j).getDurationInMinutes() + "\",");
//                                    writer.newLine();
                                    writer.write("\"taskProgress\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskProgress() + "\",");
//                                    writer.newLine();
                                    writer.write("\"taskReview\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskReview() + "\",");
//                                    writer.newLine();
                                    writer.write("\"startTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeH() + "\",");
//                                    writer.newLine();
                                    writer.write("\"startTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeM() + "\",");
//                                    writer.newLine();
                                    writer.write("\"endTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeH() + "\",");
//                                    writer.newLine();
                                    writer.write("\"endTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeM() + "\",");
//                                    writer.newLine();
                                    writer.write("\"submitted\": \"" + p.plans.get(f).getDay(i).getTask(j).isSubmitted() + "\",");
//                                    writer.newLine();
                                    writer.write("\"percentageOfDay\": \"" + p.plans.get(f).getDay(i).getTask(j).getPercentageOfDay() + "\",");
//                                    writer.newLine();
                                    writer.write("\"status\": \"" + p.plans.get(f).getDay(i).getTask(j).getStatus() + "\",");
//                                    writer.newLine();
                                    writer.write("\"submittedPercentage\": \"" + p.plans.get(f).getDay(i).getTask(j).getSubmittedPercentage() + "\",");
//                                    writer.newLine();
                                    writer.write("\"programs\" : [");
//                                    writer.newLine();
                                    int b = 0;
                                    int numberOfPrograms = p.plans.get(f).getDay(i).getTask(j).programs.size();
                                    for (int k = 0; k < p.plans.get(f).getDay(i).getTask(j).programs.size(); k++) {
                                        if (b == numberOfPrograms - 1) {
                                            writer.write("{");
//                                            writer.newLine();
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
//                                            writer.newLine();
                                            writer.write("}");
//                                            writer.newLine();
                                            b++;
                                        } else {
                                            writer.write("{");
//                                            writer.newLine();
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
//                                            writer.newLine();
                                            writer.write("},");
//                                            writer.newLine();
                                            b++;
                                        }
                                    }
                                    writer.write("]");
//                                    writer.newLine();
                                    writer.write("}");
//                                    writer.newLine();
                                    x++;
                                } else {
                                    writer.write("{");
//                                    writer.newLine();
                                    writer.write("\"taskID\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskID() + "\",");
//                                    writer.newLine();
                                    writer.write("\"taskName\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskName() + "\",");
//                                    writer.newLine();
                                    writer.write("\"startTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTime() + "\",");
//                                    writer.newLine();
                                    writer.write("\"endTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTime() + "\",");
//                                    writer.newLine();
                                    writer.write("\"description\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskDescription() + "\",");
//                                    writer.newLine();
                                    writer.write("\"taskDuration\" : \"" + p.plans.get(f).getDay(i).getTask(j).getDurationInMinutes() + "\",");
//                                    writer.newLine();
                                    writer.write("\"taskProgress\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskProgress() + "\",");
//                                    writer.newLine();
                                    writer.write("\"taskReview\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskReview() + "\",");
//                                    writer.newLine();
                                    writer.write("\"startTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeH() + "\",");
//                                    writer.newLine();
                                    writer.write("\"startTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeM() + "\",");
//                                    writer.newLine();
                                    writer.write("\"endTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeH() + "\",");
//                                    writer.newLine();
                                    writer.write("\"endTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeM() + "\",");
//                                    writer.newLine();
                                    writer.write("\"submitted\": \"" + p.plans.get(f).getDay(i).getTask(j).isSubmitted() + "\",");
//                                    writer.newLine();
                                    writer.write("\"percentageOfDay\": \"" + p.plans.get(f).getDay(i).getTask(j).getPercentageOfDay() + "\",");
//                                    writer.newLine();
                                    writer.write("\"status\": \"" + p.plans.get(f).getDay(i).getTask(j).getStatus() + "\",");
//                                    writer.newLine();
                                    writer.write("\"submittedPercentage\": \"" + p.plans.get(f).getDay(i).getTask(j).getSubmittedPercentage() + "\",");
//                                    writer.newLine();
                                    writer.write("\"programs\" : [");
//                                    writer.newLine();
                                    int b = 0;
                                    int numberOfPrograms = p.plans.get(f).getDay(i).getTask(j).programs.size();
                                    for (int k = 0; k < p.plans.get(f).getDay(i).getTask(j).programs.size(); k++) {
                                        if (b == numberOfPrograms - 1) {
                                            writer.write("{");
//                                            writer.newLine();
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
//                                            writer.newLine();
                                            writer.write("}");
//                                            writer.newLine();
                                            b++;
                                        } else {
                                            writer.write("{");
//                                            writer.newLine();
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
//                                          writer.newLine();
                                            writer.write("},");
//                                            writer.newLine();
                                            b++;
                                        }
                                    }
                                    writer.write("]");
//                                    writer.newLine();
                                    writer.write("},");
//                                    writer.newLine();
                                    x++;
                                }
                            }
                            writer.write("]");
//                            writer.newLine();
                            writer.write("}");
//                            writer.newLine();
                            y++;
                        }    else {
                            System.out.println(p.plans.get(f).getDay(i).getDayID());
                            writer.write("{");
//                            writer.newLine();
                            writer.write("\"dayName\": \"" + p.plans.get(f).getDay(i).getDayName() + "\",");
//                            writer.newLine();
                            writer.write("\"dayID\": \"" + p.plans.get(f).getDay(i).getDayID() + "\",");
//                            writer.newLine();
                            writer.write("\"dayProgress\" : \"" + p.plans.get(f).getDay(i).getDayProgress() + "\",");
//                            writer.newLine();
                            writer.write("\"totalTasksDration\": \"" + p.plans.get(f).getDay(i).getTotalTasksDuration() + "\",");
//                            writer.newLine();
                            writer.write("\"numberOfTasks\": \"" + p.plans.get(f).getDay(i).getNumberOfTasks() + "\",");
//                            writer.newLine();
                            String dTimeSlots = "";
                            for(int x = 0; x < p.plans.get(f).getDay(i).getTimeSlots().length; x++){
                                if(x==p.plans.get(f).getDay(i).getTimeSlots().length-1){
                                    dTimeSlots += p.plans.get(f).getDay(i).getTimeSlots()[x];
                                }
                                else{
                                    dTimeSlots += p.plans.get(f).getDay(i).getTimeSlots()[x] + ",";
                                }
                            }
                            writer.write("\"timeSlots\": \"" + dTimeSlots + "\",");
//                           writer.newLine();
                            writer.write("\"tasks\" : [");
//                            writer.newLine();
                            int x = 0;
                            int numberOfTasks = p.plans.get(f).getDay(i).getTasks().size();
                            for (int j = 0; j < p.plans.get(f).getDay(i).getTasks().size(); j++) {
                                if (x == numberOfTasks - 1) {
                                    writer.write("{");
//                                  writer.newLine();
                                    writer.write("\"taskID\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskID() + "\",");
//                                  writer.newLine();
                                    writer.write("\"taskName\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskName() + "\",");
//                                  writer.newLine();
                                    writer.write("\"startTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTime() + "\",");
//                                  writer.newLine();
                                    writer.write("\"endTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTime() + "\",");
//                                  writer.newLine();
                                    writer.write("\"description\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskDescription() + "\",");
//                                  writer.newLine();
                                    writer.write("\"taskDuration\" : \"" + p.plans.get(f).getDay(i).getTask(j).getDurationInMinutes() + "\",");
//                                  writer.newLine();
                                    writer.write("\"taskProgress\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskProgress() + "\",");
//                                  writer.newLine();
                                    writer.write("\"taskReview\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskReview() + "\",");
//                                  writer.newLine();
                                    writer.write("\"startTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeH() + "\",");
//                                  writer.newLine();
                                    writer.write("\"startTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeM() + "\",");
                                    writer.newLine();
                                    writer.write("\"submitted\": \"" + p.plans.get(f).getDay(i).getTask(j).isSubmitted() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"percentageOfDay\": \"" + p.plans.get(f).getDay(i).getTask(j).getPercentageOfDay() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"status\": \"" + p.plans.get(f).getDay(i).getTask(j).getStatus() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submittedPercentage\": \"" + p.plans.get(f).getDay(i).getTask(j).getSubmittedPercentage() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"programs\" : [");
                                    //                            writer.newLine();;
                                    int b = 0;
                                    int numberOfPrograms = p.plans.get(f).getDay(i).getTask(j).programs.size();
                                    for (int k = 0; k < p.plans.get(f).getDay(i).getTask(j).programs.size(); k++) {
                                        if (b == numberOfPrograms - 1) {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("}");
                                            //                            writer.newLine();;
                                            b++;
                                        } else {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("},");
                                            //                            writer.newLine();;
                                            b++;
                                        }
                                    }
                                    writer.write("]");
                                    //                            writer.newLine();;
                                    writer.write("}");
                                    //                            writer.newLine();;
                                    x++;
                                } else {
                                    writer.write("{");
                                    //                            writer.newLine();;
                                    writer.write("\"taskID\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskID() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskName\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskName() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"description\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskDescription() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskDuration\" : \"" + p.plans.get(f).getDay(i).getTask(j).getDurationInMinutes() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskProgress\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskProgress() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskReview\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskReview() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submitted\": \"" + p.plans.get(f).getDay(i).getTask(j).isSubmitted() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"percentageOfDay\": \"" + p.plans.get(f).getDay(i).getTask(j).getPercentageOfDay() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"status\": \"" + p.plans.get(f).getDay(i).getTask(j).getStatus() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submittedPercentage\": \"" + p.plans.get(f).getDay(i).getTask(j).getSubmittedPercentage() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"programs\" : [");
                                    //                            writer.newLine();;
                                    int b = 0;
                                    int numberOfPrograms = p.plans.get(f).getDay(i).getTask(j).programs.size();
                                    for (int k = 0; k < p.plans.get(f).getDay(i).getTask(j).programs.size(); k++) {
                                        if (b == numberOfPrograms - 1) {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("}");
                                            //                            writer.newLine();;
                                            b++;
                                        } else {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("},");
                                            //                            writer.newLine();;
                                            b++;
                                        }
                                    }
                                    writer.write("]");
                                    //                            writer.newLine();;
                                    writer.write("},");
                                    //                            writer.newLine();;
                                    x++;
                                }

                            }
                            writer.write("]");
                            //                            writer.newLine();;
                            writer.write("},");
                            //                            writer.newLine();;
                            y++;
                        }
                    }
                    writer.write("]");
                    //                            writer.newLine();;
                    writer.write("}");
                    //                            writer.newLine();;
                    t++;
                }
                else
                {
                    writer.write("{");
                    //                            writer.newLine();;
                    writer.write("\"weekPlanName\": \"" + p.plans.get(f).getWeekPlanName() + "\",");
                    //                            writer.newLine();;
                    writer.write("\"weekPlanID\": \"" + p.plans.get(f).getWeekPlanID() + "\",");
                    //                            writer.newLine();;
                    writer.write("\"weekPlanSDate\": \"" + p.plans.get(f).getWeekPlanSDate()+ "\",");
                    //                            writer.newLine();;
                    writer.write("\"weekPlanEDate\": \"" + p.plans.get(f).getWeekPlanEDate() + "\",");
                    //                            writer.newLine();;
                    writer.write("\"weekPlan\":");
                    //                            writer.newLine();;
                    writer.write("[");
                    //                            writer.newLine();;
                    int y = 0;
                    int numberOfDays = p.plans.get(f).days.size();
                    for (int i = 0; i < p.plans.get(f).getDays().size(); i++) {
                        if (y == numberOfDays-1) {
                            writer.write("{");
                            //                            writer.newLine();;
                            writer.write("\"dayName\": \"" + p.plans.get(f).getDay(i).getDayName() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"dayID\": \"" + p.plans.get(f).getDay(i).getDayID() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"dayProgress\" : \"" + p.plans.get(f).getDay(i).getDayProgress() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"totalTasksDration\": \"" + p.plans.get(f).getDay(i).getTotalTasksDuration() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"totalTasksDration\": \"" + p.plans.get(f).getDay(i).getTotalTasksDuration() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"numberOfTasks\": \"" + p.plans.get(f).getDay(i).getNumberOfTasks() + "\",");
                            //                            writer.newLine();;
                            String dTimeSlots = "";
                            for(int x = 0; x < p.plans.get(f).getDay(i).getTimeSlots().length; x++){
                                if(x==p.plans.get(f).getDay(i).getTimeSlots().length-1){
                                    dTimeSlots += p.plans.get(f).getDay(i).getTimeSlots()[x];
                                }
                                else{
                                    dTimeSlots += p.plans.get(f).getDay(i).getTimeSlots()[x] + ",";
                                }
                            }
                            writer.write("\"timeSlots\": \"" + dTimeSlots + "\",");
                            //                            writer.newLine();;
                            writer.write("\"tasks\" : [");
                            //                            writer.newLine();;
                            int x = 0;
                            int numberOfTasks = p.plans.get(f).getDay(i).getTasks().size();
                            for (int j = 0; j < p.plans.get(f).getDay(i).getTasks().size(); j++) {
                                if (x == numberOfTasks - 1) {
                                    writer.write("{");
                                    //                            writer.newLine();;
                                    writer.write("\"taskID\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskID() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskName\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskName() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"description\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskDescription() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskDuration\" : \"" + p.plans.get(f).getDay(i).getTask(j).getDurationInMinutes() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskProgress\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskProgress() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskReview\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskReview() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submitted\": \"" + p.plans.get(f).getDay(i).getTask(j).isSubmitted() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"percentageOfDay\": \"" + p.plans.get(f).getDay(i).getTask(j).getPercentageOfDay() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"status\": \"" + p.plans.get(f).getDay(i).getTask(j).getStatus() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submittedPercentage\": \"" + p.plans.get(f).getDay(i).getTask(j).getSubmittedPercentage() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"programs\" : [");
                                    //                            writer.newLine();;
                                    int b = 0;
                                    int numberOfPrograms = p.plans.get(f).getDay(i).getTask(j).programs.size();
                                    for (int k = 0; k < p.plans.get(f).getDay(i).getTask(j).programs.size(); k++) {
                                        if (b == numberOfPrograms - 1) {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("}");
                                            //                            writer.newLine();;
                                            b++;
                                        } else {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("},");
                                            //                            writer.newLine();;
                                            b++;
                                        }
                                    }
                                    writer.write("]");
                                    //                            writer.newLine();;
                                    writer.write("}");
                                    //                            writer.newLine();;
                                    x++;
                                } else {
                                    writer.write("{");
                                    //                            writer.newLine();;
                                    writer.write("\"taskID\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskID() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskName\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskName() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"description\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskDescription() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskDuration\" : \"" + p.plans.get(f).getDay(i).getTask(j).getDurationInMinutes() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskProgress\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskProgress() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskReview\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskReview() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submitted\": \"" + p.plans.get(f).getDay(i).getTask(j).isSubmitted() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"percentageOfDay\": \"" + p.plans.get(f).getDay(i).getTask(j).getPercentageOfDay() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"status\": \"" + p.plans.get(f).getDay(i).getTask(j).getStatus() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submittedPercentage\": \"" + p.plans.get(f).getDay(i).getTask(j).getSubmittedPercentage() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"programs\" : [");
                                    //                            writer.newLine();;
                                    int b = 0;
                                    int numberOfPrograms = p.plans.get(f).getDay(i).getTask(j).programs.size();
                                    for (int k = 0; k < p.plans.get(f).getDay(i).getTask(j).programs.size(); k++) {
                                        if (b == numberOfPrograms - 1) {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("}");
                                            //                            writer.newLine();;
                                            b++;
                                        } else {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("},");
                                            //                            writer.newLine();;
                                            b++;
                                        }
                                    }
                                    writer.write("]");
                                    //                            writer.newLine();;
                                    writer.write("},");
                                    //                            writer.newLine();;
                                    x++;
                                }
                            }
                            writer.write("]");
                            //                            writer.newLine();;
                            writer.write("}");
                            //                            writer.newLine();;
                            y++;
                        }    else {
                            System.out.println(p.plans.get(f).getDay(i).getDayID());
                            writer.write("{");
                            //                            writer.newLine();;
                            writer.write("\"dayName\": \"" + p.plans.get(f).getDay(i).getDayName() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"dayID\": \"" + p.plans.get(f).getDay(i).getDayID() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"dayProgress\" : \"" + p.plans.get(f).getDay(i).getDayProgress() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"totalTasksDration\": \"" + p.plans.get(f).getDay(i).getTotalTasksDuration() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"totalTasksDration\": \"" + p.plans.get(f).getDay(i).getTotalTasksDuration() + "\",");
                            //                            writer.newLine();;
                            writer.write("\"numberOfTasks\": \"" + p.plans.get(f).getDay(i).getNumberOfTasks() + "\",");
                            writer.newLine();
                            String dTimeSlots = "";
                            for(int x = 0; x < p.plans.get(f).getDay(i).getTimeSlots().length; x++){
                                if(x==p.plans.get(f).getDay(i).getTimeSlots().length-1){
                                    dTimeSlots += p.plans.get(f).getDay(i).getTimeSlots()[x];
                                }
                                else{
                                    dTimeSlots += p.plans.get(f).getDay(i).getTimeSlots()[x] + ",";
                                }
                            }
                            writer.write("\"timeSlots\": \"" + dTimeSlots + "\",");
                            //                            writer.newLine();;
                            writer.write("\"tasks\" : [");
                            //                            writer.newLine();;
                            int x = 0;
                            int numberOfTasks = p.plans.get(f).getDay(i).getTasks().size();
                            for (int j = 0; j < p.plans.get(f).getDay(i).getTasks().size(); j++) {
                                if (x == numberOfTasks - 1) {
                                    writer.write("{");
                                    //                            writer.newLine();;
                                    writer.write("\"taskID\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskID() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskName\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskName() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"description\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskDescription() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskDuration\" : \"" + p.plans.get(f).getDay(i).getTask(j).getDurationInMinutes() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskProgress\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskProgress() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskReview\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskReview() + "\",");
                                    ////                            writer.newLine();;
                                    writer.write("\"startTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submitted\": \"" + p.plans.get(f).getDay(i).getTask(j).isSubmitted() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"percentageOfDay\": \"" + p.plans.get(f).getDay(i).getTask(j).getPercentageOfDay() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"status\": \"" + p.plans.get(f).getDay(i).getTask(j).getStatus() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submittedPercentage\": \"" + p.plans.get(f).getDay(i).getTask(j).getSubmittedPercentage() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"programs\" : [");
                                    //                            writer.newLine();;
                                    int b = 0;
                                    int numberOfPrograms = p.plans.get(f).getDay(i).getTask(j).programs.size();
                                    for (int k = 0; k < p.plans.get(f).getDay(i).getTask(j).programs.size(); k++) {
                                        if (b == numberOfPrograms - 1) {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("}");
                                            //                            writer.newLine();;
                                            b++;
                                        } else {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("},");
                                            //                            writer.newLine();;
                                            b++;
                                        }
                                    }
                                    writer.write("]");
                                    //                            writer.newLine();;
                                    writer.write("}");
                                    //                            writer.newLine();;
                                    x++;
                                } else {
                                    writer.write("{");
                                    //                            writer.newLine();;
                                    writer.write("\"taskID\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskID() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskName\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskName() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTime\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTime() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"description\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskDescription() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskDuration\" : \"" + p.plans.get(f).getDay(i).getTask(j).getDurationInMinutes() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskProgress\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskProgress() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"taskReview\" : \"" + p.plans.get(f).getDay(i).getTask(j).getTaskReview() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"startTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getStartTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeH\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeH() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"endTimeM\": \"" + p.plans.get(f).getDay(i).getTask(j).getEndTimeM() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submitted\": \"" + p.plans.get(f).getDay(i).getTask(j).isSubmitted() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"percentageOfDay\": \"" + p.plans.get(f).getDay(i).getTask(j).getPercentageOfDay() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"status\": \"" + p.plans.get(f).getDay(i).getTask(j).getStatus() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"submittedPercentage\": \"" + p.plans.get(f).getDay(i).getTask(j).getSubmittedPercentage() + "\",");
                                    //                            writer.newLine();;
                                    writer.write("\"programs\" : [");
                                    //                            writer.newLine();;
                                    int b = 0;
                                    int numberOfPrograms = p.plans.get(f).getDay(i).getTask(j).programs.size();
                                    for (int k = 0; k < p.plans.get(f).getDay(i).getTask(j).programs.size(); k++) {
                                        if (b == numberOfPrograms - 1) {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("}");
                                            //                            writer.newLine();;
                                            b++;
                                        } else {
                                            writer.write("{");
                                            //                            writer.newLine();;
                                            writer.write("\"baseName\": \"" + p.plans.get(f).getDay(i).getTask(j).getProgram(k).getBaseName() + "\"");
                                            //                            writer.newLine();;
                                            writer.write("},");
                                            //                            writer.newLine();;
                                            b++;
                                        }
                                    }
                                    writer.write("]");
                                    //                            writer.newLine();;
                                    writer.write("},");
                                    //                            writer.newLine();;
                                    x++;
                                }
                            }
                            writer.write("]");
                            //                            writer.newLine();;
                            writer.write("},");
                            //                            writer.newLine();;
                            y++;
                        }
                    }
                    writer.write("]");
                    //                            writer.newLine();;
                    writer.write("},");
                    //                            writer.newLine();;
                    t++;
                }
            }
            writer.write("]");
            //                            writer.newLine();;
            writer.write("}");
            writer.close();
            //System.out.print(wPlan.getDay(0).tasks.get(2).getTaskName());

            String sCurrentLine = "";
            BufferedReader br = new BufferedReader(new FileReader(pFile));
            while ((sCurrentLine = br.readLine()) != null) {
                patientFile += sCurrentLine;
            }
//            String sCurrentLine2 = "";
//            BufferedReader br2 = new BufferedReader(new FileReader("osos.txt"));
//            while ((sCurrentLine2 = br2.readLine()) != null) {
//                sCurrentLine2.replace("'", "%2C");
//            }
            br.close();
            pFile.delete();
            //Files.delete(Paths.get(p.patientName+".txt"));
            p.file = patientFile;

        }
        catch (IOException e){
            p.file = patientFile;
        }
    }
   
}
