package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import sample.WeekPlan.Day;
import sample.WeekPlan.Patient;
import sample.WeekPlan.Task;
import sample.WeekPlan.WeekPlan;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Johnny Bishara on 15/03/2016.
 */
public class Adaptor {

    private static Adaptor instance = null;
    private Adaptor(){

    }
    private Button reloadapp = new Button();
    private Calendar calendar = new GregorianCalendar();
    private int today = calendar.get(Calendar.DAY_OF_WEEK);
    private boolean expaned = true, hidden = false;
    private int originalHeightTop;
    private int originalHeightBottom;
    private Button submitButton;
    private Button progDone;

    public  Button getReloadapp(){
    	return reloadapp;
    }
    
    
    public WeekPlan getWeekPlan() {
        return weekPlan;
    }

    public void setWeekPlan(WeekPlan weekPlan) {
        this.weekPlan = weekPlan;
    }

    private WeekPlan weekPlan = new WeekPlan();

    public Button getVideoButton() {
        return videoButton;
    }

    public void setVideoButton(Button videoButton) {
        this.videoButton = videoButton;
    }

    private Button videoButton = new Button();

    public Button getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Button loggedIn) {
        this.loggedIn = loggedIn;
    }

    private Button loggedIn = new Button();

    private Task taskToEdit;

    public Day getDayToEdit() {
        return dayToEdit;
    }

    public void setDayToEdit(Day dayToEdit) {
        this.dayToEdit = dayToEdit;
    }

    private Day dayToEdit;

    public Button getWantToEdit() {
        return wantToEdit;
    }

    public void setWantToEdit(Button wantToEdit) {
        this.wantToEdit = wantToEdit;
    }

    public Task getTaskToEdit() {
        return taskToEdit;
    }

    public void setTaskToEdit(Task taskToEdit) {
        this.taskToEdit = taskToEdit;
    }

    private Button wantToEdit = new Button();
    public Button getNewDay() {
        return newDay;
    }

    public void setNewDay(Button newDay) {
        this.newDay = newDay;
    }

    private Button newDay = new Button();
    public Button getFreeDone() {
        return freeDone;
    }

    public void setFreeDone(Button freeDone) {
        this.freeDone = freeDone;
    }

    private Button freeDone = new Button();

    public Button getFreedone() {
        return freedone;
    }

    public void setFreedone(Button freedone) {
        this.freedone = freedone;
    }

    private Button freedone = new Button();
    private ProgressBar progress;
    private int taskId;

    public void setTaskId(int i){
        this.taskId = i;
    }
    public int getTaskId(){
        return this.taskId;
    }

    public Button getProgDone() {
        return progDone;
    }

    public void setProgDone(Button progDone) {
        this.progDone = progDone;
    }



    public ProgressBar getProgress() {
        return progress;
    }

    public void setProgress(ProgressBar progress) {
        this.progress = progress;
    }

    public Button getSubmit() {
        return submit;
    }

    public void setSubmit(Button submit) {
        this.submit = submit;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
        this.cancel.setTextFill(Color.WHITE);
    }

    private Button submit;

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    private Button edit = new Button();
    private Button cancel;
    private String taskReview;
    private int taskProg;
    private Task tasknow;

    private boolean submitClicked = false;

    public void setSubmitClicked(boolean b){
        this.submitClicked = b;
    }
    public boolean isSubmitClicked(){
        return submitClicked;
    }
    public Task getTasknow() {
        return tasknow;
    }

    public void setTasknow(Task tasknow) {
        this.tasknow = tasknow;
    }

    public int getTaskProg() {
        return taskProg;
    }

    public void setTaskProg(int taskProg) {
        this.taskProg = taskProg;
    }

    public void setTaskReview(String r){
        this.taskReview = r;
    }
    public String getTaskReview(){
        return this.taskReview;
    }

    public String getTaskNow() {
        return taskNow;
    }

    public void setTaskNow(String taskNow) {
        this.taskNow = taskNow;
    }

    private String taskNow;

    public VBox getMainVBox() {
        return mainVBox;
    }

    public void setMainVBox(VBox mainVBox) {
        this.mainVBox = mainVBox;
        this.setOriginalheightVBMain((int)mainVBox.getHeight());
    }
    public void setSubmitButton(Button s){
        this.submitButton = s;
        this.submitButton.setTextFill(Color.WHITE);
    }
    public Button getSubmitButton(){
        return this.submitButton;
    }


    private VBox mainVBox;

    private int originalheightHBMain;

    public int getOriginalheightVBMain() {
        return originalheightVBMain;
    }

    public void setOriginalheightVBMain(int originalheightVBMain) {
        this.originalheightVBMain = originalheightVBMain;
    }

    public int getOriginalheightHBMain() {
        return originalheightHBMain;
    }

    public void setOriginalheightHBMain(int originalheightHBMain) {
        this.originalheightHBMain = originalheightHBMain;
    }

    private int originalheightVBMain;
    private ObservableList<Node> mainChildren;

    public HBox getTaskNowTopHBox() {
        return taskNowTopHBox;
    }

    public void setTaskNowTopHBox(HBox taskNowTopHBox) {
        this.taskNowTopHBox = taskNowTopHBox;
        originalHeightTop = (int)taskNowTopHBox.getHeight();
    }

    public HBox getTaskNowBottomHBox() {
        return taskNowBottomHBox;
    }

    public void setTaskNowBottomHBox(HBox taskNowBottomHBox) {
        this.taskNowBottomHBox = taskNowBottomHBox;
        originalHeightBottom = (int)taskNowBottomHBox.getHeight();
    }

    public static void setInstance(Adaptor instance) {
        Adaptor.instance = instance;
    }

    private HBox taskNowTopHBox;
    private HBox taskNowBottomHBox;

    public HBox getMainHBox() {
    	
        return mainHBox;
    }

    public void setMainHBox(HBox mainHBox) {
        this.mainHBox = mainHBox;
        this.setMainChildren();
        this.setOriginalheightHBMain((int)mainHBox.getHeight());
    }

    private HBox mainHBox;

    public ObservableList<Node> getMainChildren() {
        return mainChildren;
    }

    private void setMainChildren() {
        mainChildren = mainHBox.getChildren();
    }



    public static Adaptor getInstance() {

        if(instance == null)
            instance = new Adaptor();

        return instance;

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    Patient patient = new Patient();

    public void setOriginalHeightTop(int height) { this.originalHeightTop = height; }
    public void setOriginalHeightBottom(int height) { this.originalHeightBottom = height; }
    public int getOriginalHeightTop() { return originalHeightTop; }
    public int getOriginalHeightBottom() { return originalHeightBottom; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }
    public void setExpaned(boolean expaned) { this.expaned = expaned; }
    public boolean isHidden() { return hidden; }
    public boolean isExpaned() { return expaned; }

    private Day day_now;
    private Label dayLabel;
    private HBox tasksHBox;
    private int hour = calendar.get(Calendar.HOUR_OF_DAY);

    public ProgressBar getDayProgress() {
        return dayProgress;
    }

    public void setDayProgress(ProgressBar dayProgress) {
        this.dayProgress = dayProgress;
    }

    ProgressBar dayProgress;

    public int getMinute() {
        setMinute(calendar.get(Calendar.MINUTE));
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        setHour(calendar.get(Calendar.HOUR_OF_DAY));
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public HBox getTasksHBox() {
        return tasksHBox;
    }

    public void setTasksHBox(HBox tasksHBox) {
        this.tasksHBox = tasksHBox;
    }

    public Label getDayLabel() {
        return dayLabel;
    }

    public void setDayLabel(Label dayLabel) {
        this.dayLabel = dayLabel;
    }

    public Day getDay_now() {
        return day_now;
    }

    public void setDay_now(Day day_now) {
        this.day_now = day_now;
    }

    public void setTaskReviewPer(String review){
        this.tasknow.setTaskReveiw(review);
    }

    private int minute = calendar.get(Calendar.MINUTE);

    public String getAddPatientFile() {
        return addPatientFile;
    }

    public void setAddPatientFile(String addPatientFile) {
        this.addPatientFile = addPatientFile;
    }

    private String addPatientFile = "";

    public void addingAllTasks(Day day_Selected, WeekPlan p){
        if(day_Selected != null) {
            dayLabel.setText(day_Selected.getDayName());
            tasksHBox.getChildren().clear();
            double total = 0;
            double total1 = 0;
//            System.out.println(tasksHBox.getChildren().size() + "tasksHBox size <<<<<<<<<<<<<<");
            for (int t = 0; t < day_Selected.getTasks().size(); t++) {
                //TitleLabel
                Label taskTile = new Label(day_Selected.getTask(t).getTaskName());  //change
                taskTile.setTextAlignment(TextAlignment.CENTER);
                taskTile.setAlignment(Pos.CENTER);
                taskTile.setFont(new Font("Copperplate Gothic Bold", 20));
                taskTile.setContentDisplay(ContentDisplay.CENTER);
                taskTile.setPrefWidth(Control.USE_COMPUTED_SIZE);
                taskTile.setPrefHeight(40);

                //Task Time period
                Label taskStartTime = new Label("Start: " + day_Selected.getTask(t).getStartTimeH() + ":"
                        + day_Selected.getTask(t).getStartTimeM() + "   End: " + day_Selected.getTask(t).getEndTimeH() + ":"
                        + day_Selected.getTask(t).getEndTimeM());
                taskStartTime.setTextAlignment(TextAlignment.CENTER);
                taskStartTime.setAlignment(Pos.CENTER);
                taskStartTime.setFont(new Font("Copperplate Gothic Bold", 14));
                taskStartTime.setContentDisplay(ContentDisplay.CENTER);
                taskStartTime.setPrefWidth(Control.USE_COMPUTED_SIZE);
                taskStartTime.setPrefHeight(20);

                //tasktitle Vbox
                VBox taskTitleVBox = new VBox();
                taskTitleVBox.setAlignment(Pos.CENTER);
                taskTitleVBox.getChildren().addAll(taskTile, taskStartTime);

                //DescriptionVbox
                Label descTitle = new Label("Description");
                descTitle.setTextAlignment(TextAlignment.CENTER);
                descTitle.setFont(new Font("Copperplate Gothic Bold", 16));
                descTitle.setContentDisplay(ContentDisplay.CENTER);
                descTitle.setAlignment(Pos.CENTER);
                descTitle.setPrefWidth(Control.USE_COMPUTED_SIZE);
                descTitle.setPrefHeight(30);

                TextArea descText = new TextArea();
                descText.setText(day_Selected.getTask(t).getTaskDescription()); //change
                descText.setFont(new Font("Copperplate Gothic Bold", 14));
                descText.setEditable(false);
                //descText.setMinSize(150, 150);
                descText.setPrefSize(150, 150);
                descText.setMaxSize(200, 150);
                descText.setWrapText(true);
                descText.setMouseTransparent(true);

                VBox descVBox = new VBox();
                descVBox.setAlignment(Pos.CENTER);
                descVBox.getChildren().addAll(descTitle, descText);

                //ProgramsVBox
                Label progTitle = new Label("Programs");
                progTitle.setTextAlignment(TextAlignment.CENTER);
                progTitle.setFont(new Font("Copperplate Gothic Bold", 16));
                progTitle.setContentDisplay(ContentDisplay.CENTER);
                progTitle.setAlignment(Pos.CENTER);
                progTitle.setPrefWidth(Control.USE_COMPUTED_SIZE);
                progTitle.setPrefHeight(30);

                TextArea progText = new TextArea();
                StringBuffer buff = new StringBuffer();
                String progs = "";
                for (int d = 0; d < day_Selected.getTask(t).getPrograms().size(); d++) {
                    //progs += "- " + day_Selected.getTask(t).getProgram(d).getBaseName() + "\n";
                    buff.append("- " + day_Selected.getTask(t).getProgram(d).getBaseName() + "\n");
                }
                progs = buff.toString();
                progText.setText(progs); //change
                progText.setFont(new Font("Copperplate Gothic Bold", 14));
                progText.setEditable(false);
                //progText.setMinSize(150, 150);
                progText.setPrefSize(150, 150);
                progText.setMaxSize(200, 150);
                progText.setWrapText(true);
                progText.setMouseTransparent(true);

                VBox progVBox = new VBox();
                progVBox.setAlignment(Pos.CENTER);
                progVBox.getChildren().addAll(progTitle, progText);

                //StatusLabel
                if (tasknow == day_Selected.getTask(t) && !tasknow.isSubmitted()) {
                    if (!tasknow.isSubmitted()) {
                        day_Selected.getTask(t).setStatus("Running..");
                    } else {
                        day_Selected.getTask(t).setStatus("Submitted: " + day_Selected.getTask(t).getSubmittedPercentage() + "% Completed");
                        day_Selected.getTask(t).setSubmitted(true);
                    }

                } else {
                    if (day_Selected == day_now){
                        if (day_now.getTask(t).getStartTimeH() == hour && day_now.getTask(t).getEndTimeH() == hour) {
                            if (day_now.getTask(t).getStartTimeM() < minute && day_now.getTask(t).getEndTimeM() <= minute) {
                                day_Selected.getTask(t).setStatus("Submitted: " + day_Selected.getTask(t).getSubmittedPercentage() + "% Completed");
                                day_Selected.getTask(t).setSubmitted(true);
                            } else {
                                if (day_now.getTask(t).getStartTimeM() <= minute && day_now.getTask(t).getEndTimeM() > minute && !day_now.getTask(t).isSubmitted()) {
                                    day_Selected.getTask(t).setStatus("Running..");
                                } else {
                                    if (day_now.getTask(t).getStartTimeM() > minute) {
                                        day_Selected.getTask(t).setStatus("Waiting..");
                                        day_Selected.getTask(t).setSubmitted(false);
                                        System.out.println(day_now.getTask(t).getStatus() + "awel wa7da begad");
                                        System.out.println(minute + "    " + day_now.getTask(t).getEndTimeM());
                                    }
                                }
                            }
                        } else {
                            if (day_now.getTask(t).getStartTimeH() < hour && day_now.getTask(t).getEndTimeH() < hour) {
                                day_Selected.getTask(t).setStatus("Submitted: " + day_Selected.getTask(t).getSubmittedPercentage() + "% Completed");
                                day_Selected.getTask(t).setSubmitted(true);
                            } else {
                                if (day_now.getTask(t).getStartTimeH() > hour && day_now.getTask(t).getEndTimeH() > hour) {
                                    day_Selected.getTask(t).setStatus("Waiting..");

                                } else {
                                    if (day_now.getTask(t).getStartTimeH() < hour && day_now.getTask(t).getEndTimeH() == hour && !day_now.getTask(t).isSubmitted()) {
                                        if (day_now.getTask(t).getEndTimeM() > minute) {
                                            day_Selected.getTask(t).setStatus("Running..");
                                        } else {
                                            day_Selected.getTask(t).setStatus("Submitted: " + day_Selected.getTask(t).getSubmittedPercentage() + "% Completed");
                                            day_Selected.getTask(t).setSubmitted(true);
                                        }
                                    } else {
                                        if (day_now.getTask(t).getStartTimeH() == hour && day_now.getTask(t).getEndTimeH() > hour && !day_now.getTask(t).isSubmitted()) {
                                            if (day_now.getTask(t).getStartTimeM() <= minute) {
                                                day_Selected.getTask(t).setStatus("Running..");
                                            } else {
                                                day_Selected.getTask(t).setStatus("Waiting..");

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (day_Selected.getDayID() < day_now.getDayID()) {
                            System.out.println(day_Selected.getDayID() + "<><<<<<<><><><><<<<<<><><><<<< day selected");
                            System.out.println(day_now.getDayID() + "<><<<<<<><><><><<<<<<><><><<<< day now");
                            day_Selected.getTask(t).setStatus("Submitted: " + day_Selected.getTask(t).getSubmittedPercentage() + "% Completed");
                            day_Selected.getTask(t).setSubmitted(true);
                        } else {
                            day_Selected.getTask(t).setStatus("Waiting..");
                        }
                    }
                }
                Label stauslabel = new Label(day_Selected.getTask(t).getStatus()); // change!!!!!!!!!!!!!!!!!!!!!!!!!! Submitted: 100% Completed | Running | Wainting..
                stauslabel.setTextAlignment(TextAlignment.CENTER);
                stauslabel.setFont(new Font("Copperplate Gothic Bold", 16));
                stauslabel.setContentDisplay(ContentDisplay.CENTER);
                stauslabel.setAlignment(Pos.CENTER);
                stauslabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
                stauslabel.setPrefHeight(30);

                //Edit Button
                Button edit = new Button("Edit");
                edit.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 20));
                edit.setPrefWidth(150);
                edit.setPrefHeight(25);
                edit.setStyle("-fx-background-color: #ABABABAB;");

                //TaskBPane
                BorderPane newTask = new BorderPane();
//            newTask.setLeft(edit);
//            newTask.setAlignment(edit, Pos.CENTER);
                newTask.setTop(taskTitleVBox);
                newTask.setAlignment(taskTitleVBox, Pos.CENTER);
                newTask.setCenter(descVBox);
                newTask.setAlignment(descVBox, Pos.CENTER);
                newTask.setRight(progVBox);
                newTask.setAlignment(progVBox, Pos.CENTER);
                newTask.setBottom(stauslabel);
                newTask.setAlignment(stauslabel, Pos.CENTER);
                newTask.setMaxHeight(237);
                newTask.setPrefHeight(237);
                newTask.setMinHeight(237);
                //System.out.println(day_now.getTask(t).getPercentageOfDay());
                newTask.setMinWidth(300 + (10 * day_Selected.getTask(t).getPercentageOfDay())); //
                newTask.setMaxWidth(300 + (10 * day_Selected.getTask(t).getPercentageOfDay())); //
                newTask.setPrefWidth(300 + (10 * day_Selected.getTask(t).getPercentageOfDay()));  // change
                newTask.setStyle("-fx-background-color: #a5b0b0;");
                newTask.setId("" + day_Selected.getTask(t).getTaskID());

                //adding Task to taskHBox
                tasksHBox.getChildren().add(newTask);
                //Changing day Progress
                if (day_Selected.getTask(t).isSubmitted()) {
                    total1 += day_Selected.getTask(t).getPercentageOfDay();
                    System.out.println(total1+"..........day total 1 !!!");
                    total += day_Selected.getTask(t).getSubmittedPercentage();
                    System.out.println(total+"..........day total !!!");
                    //System.out.println(total);
                }
            }
            System.out.println((total/total1)*100 +"..........day progress !!!");
            if(total1 <= 0) {
            	dayProgress.setProgress(0.0); 
            	 p.getDayById(day_Selected.getDayID()).setDayProgress(0.0);}
            else
            {
            p.getDayById(day_Selected.getDayID()).setDayProgress((total/total1));
            dayProgress.setProgress((total/total1));
            }
        }
        for (Node x: tasksHBox.getChildren()) {
            x.setOnMouseEntered(event -> {
                if(day_now.getTaskByID(Integer.parseInt(x.getId())).isSubmitted()) {
                    x.setStyle("-fx-background-color: lightgray;");
                }
            });
            x.setOnMouseExited(event -> {
                if(day_now.getTaskByID(Integer.parseInt(x.getId())).isSubmitted()) {
                    x.setStyle("-fx-background-color: #a5b0b0;");
                }
            });
            x.setOnMouseClicked(event -> {
                if(day_now.getTaskByID(Integer.parseInt(x.getId())).isSubmitted()) {
                    dayToEdit = day_now;
                    day_now = day_Selected.getTaskByID(Integer.parseInt(x.getId()));
                    wantToEdit.defaultButtonProperty().setValue(true);
                }
            });


        }

    }

}
