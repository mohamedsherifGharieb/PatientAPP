package sample.utils;

import sample.WeekPlan.WeekPlan;

import java.io.*;

/**
 * Created by Johnny Bishara on 07/03/2016.
 */
public class Report extends PlanParser{

    public static void writeReport(WeekPlan wPlan) throws IOException {
        try {
            FileWriter report = new FileWriter("PatientReportTest.txt");
            BufferedWriter writer = new BufferedWriter(report);
            writer.write("{\"Report\":[\n");
            writer.newLine();
            int y = 0;
            int numberOfDays = wPlan.getDays().size();
            for (int i = 0; i < wPlan.getDays().size(); i++) {
                if (y == numberOfDays-1) {
                    System.out.println(wPlan.getDay(i).getDayID());
                    writer.write("{\"dayID\": \"" + wPlan.getDay(i).getDayID() + "\",");
                    writer.newLine();
                    writer.write("\"dayProgress\" : \"" + wPlan.getDay(i).getDayProgress() + "\",");
                    writer.newLine();
                    writer.write("\"tasks\" : [");
                    writer.newLine();
                    int x = 0;
                    int numberOfTasks = wPlan.getDay(i).getTasks().size();
                    for (int j = 0; j < wPlan.getDay(i).getTasks().size(); j++) {
                        if (x < numberOfTasks-1) {
                            writer.write("{");
                            writer.newLine();
                            writer.write("\"taskID\" : \"" + wPlan.getDay(i).getTask(j).getTaskID() + "\",");
                            writer.newLine();
                            writer.write("\"taskName\" : \"" + wPlan.getDay(i).getTask(j).getTaskName() + "\",");
                            writer.newLine();
                            writer.write("\"taskDescription\" : \"" + wPlan.getDay(i).getTask(j).getTaskDescription() + "\",");
                            writer.newLine();
                            writer.write("\"taskDuration\" : \"" + wPlan.getDay(i).getTask(j).getDurationInMinutes() + "\",");
                            writer.newLine();
                            writer.write("\"taskProgress\" : \"" + wPlan.getDay(i).getTask(j).getTaskProgress() + "\",");
                            writer.newLine();
                            writer.write("\"taskReview\" : \"" + wPlan.getDay(i).getTask(j).getTaskReview() + "\"");
                            writer.newLine();
                            writer.write("},");
                            writer.newLine();
                            x++;
                        } else {
                            writer.write("\"taskID\" : \"" + wPlan.getDay(i).getTask(j).getTaskID() + "\",");
                            writer.newLine();
                            writer.write("\taskName\" : \"" + wPlan.getDay(i).getTask(j).getTaskName() + "\",");
                            //System.out.print("task name is :" + wPlan.getDay(i).getTask(j).getTaskName());
                            writer.newLine();
                            writer.write("\"taskDescription\" : \"" + wPlan.getDay(i).getTask(j).getTaskDescription() + "\",");
                            writer.newLine();
                            writer.write("\"taskDuration\" : \"" + wPlan.getDay(i).getTask(j).getDurationInMinutes() + "\",");
                            writer.newLine();
                            writer.write("\"taskProgress\" : \"" + wPlan.getDay(i).getTask(j).getTaskProgress() + "\",");
                            writer.newLine();
                            writer.write("\"taskReview\" : \"" + wPlan.getDay(i).getTask(j).getTaskReview() + "\"");
                            writer.newLine();
                            writer.write("}");
                            writer.newLine();
                            x++;
                        }
                    }
                    writer.write("]");
                    writer.newLine();
                    writer.write("}");
                    writer.newLine();
                    y++;
                } else {
                    System.out.println(wPlan.getDay(i).getDayID());
                    writer.write("{\"dayID\": \"" + wPlan.getDay(i).getDayID() + "\",");
                    writer.newLine();
                    writer.write("\"dayProgress\" : \"" + wPlan.getDay(i).getDayProgress() + "\",");
                    writer.newLine();
                    writer.write("\"tasks\" : [");
                    writer.newLine();
                    int x = 0;
                    int numberOfTasks = wPlan.getDay(i).getTasks().size();
                    for (int j = 0; j < wPlan.getDay(i).getTasks().size(); j++) {
                        if (x == numberOfTasks-1) {
                            writer.write("{");
                            writer.newLine();
                            writer.write("\"taskID\" : \"" + wPlan.getDay(i).getTask(j).getTaskID() + "\",");
                            writer.newLine();
                            writer.write("\taskName\" : \"" + wPlan.getDay(i).getTask(j).getTaskName() + "\",");
                            writer.newLine();
                            writer.write("\"taskDescription\" : \"" + wPlan.getDay(i).getTask(j).getTaskDescription() + "\",");
                            writer.newLine();
                            writer.write("\"taskDuration\" : \"" + wPlan.getDay(i).getTask(j).getDurationInMinutes() + "\",");
                            writer.newLine();
                            writer.write("\"taskProgress\" : \"" + wPlan.getDay(i).getTask(j).getTaskProgress() + "\",");
                            writer.newLine();
                            writer.write("\"taskReview\" : \"" + wPlan.getDay(i).getTask(j).getTaskReview() + "\"");
                            writer.newLine();
                            writer.write("}");
                            writer.newLine();
                            x++;
                        } else {
                            writer.write("\"taskID\" : \"" + wPlan.getDay(i).getTask(j).getTaskID() + "\",");
                            writer.newLine();
                            writer.write("\taskName\" : \"" + wPlan.getDay(i).getTask(j).getTaskName() + "\",");
                            writer.newLine();
                            writer.write("\"taskDescription\" : \"" + wPlan.getDay(i).getTask(j).getTaskDescription() + "\",");
                            writer.newLine();
                            writer.write("\"taskDuration\" : \"" + wPlan.getDay(i).getTask(j).getDurationInMinutes() + "\",");
                            writer.newLine();
                            writer.write("\"taskProgress\" : \"" + wPlan.getDay(i).getTask(j).getTaskProgress() + "\",");
                            writer.newLine();
                            writer.write("\"taskReview\" : \"" + wPlan.getDay(i).getTask(j).getTaskReview() + "\"");
                            writer.newLine();
                            writer.write("},");
                            writer.newLine();
                            x++;
                        }
                    }
                    writer.write("]");
                    writer.newLine();
                    writer.write("},");
                    writer.newLine();
                    y++;
                }
            }
            writer.write("]");
            writer.newLine();
            writer.write("}");
            writer.close();
            //System.out.print(wPlan.getDay(0).tasks.get(2).getTaskName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}