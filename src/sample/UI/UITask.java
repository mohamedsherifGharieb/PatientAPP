package sample.UI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import sample.WeekPlan.Task;

/**
 * Created by Johnny Bishara on 08/03/2016.
 */
public class UITask {
    private static UITask instance = null;
    private Label title, description, startTime, endTime, currentProgress;
    private ProgressBar pBar;
    private CheckBox checkBox;
    private Button extend;

    private UITask(){

    }
    public static UITask getInstance(){
        if(instance == null)
            instance = new UITask();

        return instance;
    }
    public static void setInstance(UITask instance){
        UITask.instance = instance;
    }

    public Button getFreeDone() {
        return freeDone;
    }
    

    private Button freeDone = new Button();



    public ArrayList<Control> createGraphicalTask(Task task){

        ArrayList<Control> out = new ArrayList<Control>();

        title = new Label(task.getTaskName());
        title.getStyleClass().add("title");
        description = new Label(task.getTaskDescription());
        currentProgress = new Label("" + task.getTaskProgress());

        out.add(title);
        out.add(description);
        out.add(currentProgress);

        return out;
    }

    public void bindTitleToLabel(Task task, Label label) {
        label.setText(task.getTaskName());
    }
    public void bindTitleToLabelNotActive(Task task, Label label) {
        label.setText("Upcoming! - " + task.getTaskName());
    }

    public void bindDescriptionToLabel(Task task, Label label) {
        label.setText(task.getTaskDescription());
    }

    public void bindStartTimeToLabel(Task task, Label label) {
        label.setText(task.getStartTimeH()+":"+task.getStartTimeM());
    }
    public void bindRemainingTimeToLabel(Task task, Label label, ProgressBar pb) {

    	//progress of current task
        javafx.concurrent.Task<Void> concurrent_task_pogress = new javafx.concurrent.Task<Void>() {
            @Override
            protected Void call() throws Exception {

                while (true) {
                    int h, m;
                    Calendar cal = new GregorianCalendar();
                    h = task.getEndTimeH() - cal.get(Calendar.HOUR_OF_DAY);
                    //System.out.println(h);
                    m = task.getEndTimeM() - cal.get(Calendar.MINUTE);
                    double remaining = h * 60 + m;
                    //System.out.println(task.getDurationInMinutes());
                    //
                    updateMessage("" + ((((task.getDurationInMinutes() - remaining) * 100) / task.getDurationInMinutes())/100));
                   // if(Math.round(remaining) <= 0) return null;
                    
                    //System.out.println(remaining);
                    //System.out.println(task.getDurationInMinutes());
                    //System.out.println(((((task.getDurationInMinutes() - remaining) * 100) / task.getDurationInMinutes())/100));
                }
                
            }
        };
        new Thread(concurrent_task_pogress).start();

        concurrent_task_pogress.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                pb.setProgress(Double.parseDouble(newValue));
            }
        });


        //task right now
        javafx.concurrent.Task<Void> concurrent_task_label = new javafx.concurrent.Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    int h, m;
                    Calendar cal = new GregorianCalendar();
                    h = task.getEndTimeH() - cal.get(Calendar.HOUR_OF_DAY);
                    m = task.getEndTimeM() - cal.get(Calendar.MINUTE);
                    double remaining = h * 60 + m;
                    //
                    updateMessage(Math.round(remaining) + "  min left!");
                    
                }
            }
        };
        new Thread(concurrent_task_label).start();

        concurrent_task_label.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                label.setText(newValue);
            }
        });
    }
    
    //come back in time:

    public void bindRemainingTimeTillTask(Task task, int freemin, Label label) {

        javafx.concurrent.Task<Void> concurrent_task_label = new javafx.concurrent.Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    int h, m;
                    Calendar cal = new GregorianCalendar();
                    h = task.getStartTimeH() - cal.get(Calendar.HOUR_OF_DAY);
                    m = task.getStartTimeM() - cal.get(Calendar.MINUTE);
                    double remaining = h * 60 + m;
                    //
                    updateMessage(" " + Math.round(remaining));
                   
                }
            }
        };
        
        new Thread(concurrent_task_label).start();
        
        
        //come back in:
        concurrent_task_label.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	StringTokenizer st = new StringTokenizer(newValue);
            	String num = st.nextToken();
                label.setText("Come back in : " + num + " min.");
                if (Integer.parseInt(num) == 0){
                    freeDone.defaultButtonProperty().setValue(true);
                }
            }
        });
    }

}
