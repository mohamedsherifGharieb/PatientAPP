package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javafx.util.Duration;
import org.joda.time.LocalDate;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.UI.UITask;
import sample.WeekPlan.Day;
import sample.WeekPlan.Patient;
import sample.WeekPlan.Task;
import sample.WeekPlan.WeekPlan;
import sample.utils.*;


public class Controller extends PlanParser implements Initializable {
 //   Timer animTimer = new Timer();

    @FXML private Label taskLabel;
    @FXML private Label desctiptionLabel;
    @FXML private Label descriptionField;
    @FXML private Label sTime;
    @FXML private Label rTime;
    @FXML private ProgressBar progressBar;
    @FXML private Button expandButton;
    @FXML private Button submitButton;
    @FXML private VBox tasksHBox;
    @FXML private Label dayLabel;
    @FXML private Button nextDay;
    @FXML private Button previousDay;
    @FXML private ProgressBar dayProgress;
    @FXML private BorderPane dayInfo;
    @FXML private HBox mainHBox;
    @FXML private VBox mainVBox;
    @FXML private BorderPane taskBPane;
    @FXML private HBox dayHBox;
    @FXML private HBox taskNowBottom;
    @FXML private HBox taskNowTop;
    @FXML private HBox hboxProgress;
    @FXML private VBox vBoxTask;
    @FXML private HBox taskNowMiddle;
    @FXML private Button Notifications;
    @FXML private static Button Report ;
    public TextArea messageDisplay;
    private Timeline timeline;


  //  boolean pressedSubmit = false;

    Calendar calendar = new GregorianCalendar();
    int today = calendar.get(Calendar.DAY_OF_WEEK);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    Patient patient = new Patient();
    WeekPlan p;
    boolean flag = true;
    Task taskNow, previousTask;
    Day day_now;
    Day day_Selected;
    boolean expaned = true;
    int freeMin;
   Button reloadapp = new Button();
    int dayNow;
   
    Stage theWindow;
    int initialWindowHeight;

    UITask ui_task1 = UITask.getInstance();

    //adaptor
    Adaptor adaptor = Adaptor.getInstance();

    
    //Test
    BooleanProperty t1,t2;


    boolean hidden = false;


    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	 
    	 adaptor.setMainHBox(mainHBox);
         adaptor.setTaskNowBottomHBox(taskNowBottom);
         adaptor.setTaskNowTopHBox(taskNowTop);
         adaptor.setSubmitButton(submitButton);
         adaptor.setProgress(progressBar);
         adaptor.isSubmitClicked();
         adaptor.setDayLabel(dayLabel);
         adaptor.setTasksHBox(tasksHBox);
         adaptor.setDayProgress(dayProgress);
         adaptor.setHour(hour);
         adaptor.setMinute(minute);
         Button progDone = new Button();
         adaptor.setProgDone(progDone);


        submitButton.setBackground(new Background(new BackgroundImage(new Image("submit.png",30,30,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        submitButton.setOnMouseEntered(evnt -> {
            submitButton.setBackground(new Background(new BackgroundImage(new Image("submit_Hover.png",30,30,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        });
        submitButton.setOnMouseExited(eventx -> {
            submitButton.setBackground(new Background(new BackgroundImage(new Image("submit.png",30,30,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        });

        //
        nextDay.setBackground(new Background(new BackgroundImage(new Image("arrowNext.png",40,40,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        nextDay.setOnMouseEntered(evn ->{
            nextDay.setBackground(new Background(new BackgroundImage(new Image("arrowNext_Hover.png",40,40,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        });
        nextDay.setOnMouseExited(evnt -> {
            nextDay.setBackground(new Background(new BackgroundImage(new Image("arrowNext.png",40,40,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        });

        //
        previousDay.setBackground(new Background(new BackgroundImage(new Image("arrowPrevious.png",40,40,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        previousDay.setOnMouseEntered(e ->{
            previousDay.setBackground(new Background(new BackgroundImage(new Image("arrowPrevious_Hover.png",40,40,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        });
        previousDay.setOnMouseExited(ev ->{
            previousDay.setBackground(new Background(new BackgroundImage(new Image("arrowPrevious.png",40,40,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        });

        expandButton.setBackground(new Background(new BackgroundImage(new Image("expanded.png",30,30,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        expandButton.setOnMouseEntered(e -> {
            if(expaned)
                expandButton.setBackground(new Background(new BackgroundImage(new Image("expanded_Hover.png",30,30,false,true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
            else
                expandButton.setBackground(new Background(new BackgroundImage(new Image("expand_Hover.png",30,30,false,true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        });
        expandButton.setOnMouseExited(e -> {
            if(expaned)
                expandButton.setBackground(new Background(new BackgroundImage(new Image("expanded.png",30,30,false,true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
            else
                expandButton.setBackground(new Background(new BackgroundImage(new Image("expand.png",30,30,false,true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        });

if(adaptor.getReloadapp().defaultButtonProperty().getValue()){
	adaptor.getReloadapp().defaultButtonProperty().setValue(false);
	
	 		adaptor.setInstance(null);
	 		adaptor.getInstance();
             WeekPlan p = adaptor.getWeekPlan();
             System.out.println("THE DAY TODAY IS: " + p.getDayById(today).dayName + " THE TIME IS " + hour + ":" + minute);
//             System.out.println(p + "............plan ???");
             expandButton.setOnAction(event -> {
                 addingAllTasks(day_now, p);
                 dayNow = day_now.getDayID();

//    
                 //
                 if(expaned) {
                     initialWindowHeight = (int) dayInfo.getScene().getWindow().getHeight();
                     expandButton.setBackground(new Background(new BackgroundImage(new Image("expand_Hover.png",30,30,false,true),
                             BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
                 }
                 else {
                     expandButton.setBackground(new Background(new BackgroundImage(new Image("expanded_Hover.png",30,30,false,true),
                             BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
                 }
                 expaned = !expaned;
                 adaptor.setExpaned(expaned);
                 dayInfo.setPrefHeight(expaned ? 362:0);
                 dayInfo.getScene().getWindow().setHeight(expaned ? initialWindowHeight:initialWindowHeight-362);
             });
          
             //Getting Today
             Locale locale = Locale.getDefault();


             //delwa2ty
             day_now = p.getDayById(today);
             addingAllTasks(day_now, p);
             System.out.println(day_now.getDayName()+ "Day_Now <<<<<<<<");
             dayNow = day_now.getDayID();

             //Current TAsk ------------------------------------------------
             taskNow = getTaskNow();
             System.out.println(taskNow+ "Task_Now <<<<<<<<");
             adaptor.setTasknow(taskNow);
             setTask(taskNow);
             //

             //Current Day
             //addingAllTasks(day_now);
             day_Selected = day_now;

          

             //Adaptor


             adaptor.setTaskNow(taskLabel.getText());
             adaptor.setTasknow(taskNow);
             adaptor.setDay_now(day_now);

             Button submit = adaptor.getSubmit();
             submit.defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
                 @Override
                 public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                     p.getDayById(day_now.getDayID()).mergeTasks(p.getDayById(day_now.getDayID()), adaptor.getDay_now());
                     //TODO: THERE WAS A ! BEFORE THE CONDITION
                     if(adaptor.getProgDone().defaultButtonProperty().getValue() == false) {
                         System.out.println("entered <<<<<<<");
                         taskNow = adaptor.getTasknow(); 
                         p.getDayById(day_now.getDayID()).getTaskByName(adaptor.getTasknow().getTaskName()).setTaskReveiw(adaptor.getTasknow().taskReveiw);
                         p.getDayById(day_now.getDayID()).getTaskByName(adaptor.getTasknow().getTaskName()).setSubmittedPercentage(
                                 adaptor.getTasknow().getSubmittedPercentage());
                         p.getDayById(day_now.getDayID()).getTaskByName(adaptor.getTasknow().getTaskName()).setSubmitted(true);
                         adaptor.getPatient().getWeekPlanByName(p.getWeekPlanName()).setDays(p.getDays());
                         day_now = p.getDayById(day_now.getDayID());
                         taskNow.setSubmitted(true);
                         addingAllTasks(day_now, p);
                         System.out.println(taskNow.getTaskName());
                        // taskNow = getTaskNow();
                         setTask(taskNow);
                         if(taskNow!=null) {
                             adaptor.setTaskNow(taskNow.getTaskName());
                             adaptor.setTasknow(taskNow);
                         }
                         
                         adaptor.getSubmit().defaultButtonProperty().setValue(false);
                         adaptor.getMainHBox().setDisable(false);

                       
                        
                     }
                 }
             });

             adaptor.getEdit().defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
                 @Override
                 public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                     if (newValue) {
                         p.getDayById(adaptor.getDayToEdit().getDayID()).mergeTasks(p.getDayById(adaptor.getDayToEdit().getDayID()), adaptor.getDayToEdit());
                         adaptor.getEdit().defaultButtonProperty().setValue(false);
                         addingAllTasks(adaptor.getDayToEdit(), p);
                     }
                 }
             });

             progressBar.progressProperty().addListener(new ChangeListener<Number>() {
                 @Override
                 public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                     if((double)newValue == 1.0 && !adaptor.getSubmit().defaultButtonProperty().getValue()){
                         adaptor.getProgDone().defaultButtonProperty().setValue(true);
                         System.out.println("entered progress<<<<<<<");
                         taskNow = adaptor.getTasknow();
                         taskNow.setTaskReveiw(adaptor.getTaskReview());
                         taskNow.setSubmittedPercentage(adaptor.getTaskProg());
                         hour = calendar.get(Calendar.HOUR_OF_DAY);
                         minute = calendar.get(Calendar.MINUTE);
                         p.getDayById(day_now.getDayID()).mergeTasks(p.getDayById(day_now.getDayID()), adaptor.getDay_now());
                         taskNow.setEndTimeH(hour);
                         taskNow.setEndTimeM(minute);
                         taskNow.setSubmitted(true);
                         taskNow = getTaskNow();
                         adaptor.addingAllTasks(day_now, p);
                         setTask(taskNow);
//                         File file = new File("Notification.mp3");
//                         Media media = new Media(file.toURI().toString());
//                         MediaPlayer mediaplayer = new MediaPlayer(media);
//                         mediaplayer.play();
                     	
                    
                     	
                       
                         
                     }
                 }
             });


             adaptor.getFreeDone().defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
                 @Override
                 public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                     if(newValue) {
                         System.out.println("entered freeDone Contoller<<<<<<<<<<<<<<<<<<<<<<<");
                         p.getDayById(day_now.getDayID()).mergeTasks(p.getDayById(day_now.getDayID()), adaptor.getDay_now());
                         taskNow = getNextTask(day_now);
                         setTask(taskNow);
                         addingAllTasks(day_now, p);
                         adaptor.setTasknow(taskNow);
                         adaptor.addingAllTasks(day_now, p);
//                         File file = new File("Notification.mp3");
//                         Media media = new Media(file.toURI().toString());
//                          MediaPlayer mediaplayer = new MediaPlayer(media);
//                         mediaplayer.play();
                         adaptor.getFreedone().defaultButtonProperty().setValue(false);
                         ui_task1.getFreeDone().defaultButtonProperty().setValue(false);
                     }
                 }
             });
          
             //Day Navigation should start here
             System.out.println(day_now + "DayNow<<<<<<<<<<<");
           previousDay.setOnMousePressed(event1 -> {
                 dayNow--;
                 dayNow =  ((7 - dayNow)/7) * 7 + dayNow;
                 System.out.println(dayNow);
                 day_Selected = p.getDayById(dayNow);
                 addingAllTasks(day_Selected, p);
                 adaptor.setTasksHBox(tasksHBox);
             });

             nextDay.setOnMousePressed(event2 -> {
                 dayNow = dayNow % 7 +1;
                 System.out.println(dayNow);
                 day_Selected = p.getDayById(dayNow);
                 addingAllTasks(day_Selected, p);
                 adaptor.setTasksHBox(tasksHBox);
             });

}

        adaptor.getLoggedIn().defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    WeekPlan p = adaptor.getWeekPlan();
                    System.out.println(p.getWeekPlanName());
                    System.out.println("THE DAY TODAY IS: " + p.getDayById(today).dayName + " THE TIME IS " + hour + ":" + minute);
//                    System.out.println(p + "............plan ???");
                    expandButton.setOnAction(event -> {
                        addingAllTasks(day_now, p);
                        dayNow = day_now.getDayID();

//           
                        //
                        if(expaned) {
                            initialWindowHeight = (int) dayInfo.getScene().getWindow().getHeight();
                            expandButton.setBackground(new Background(new BackgroundImage(new Image("expand_Hover.png",30,30,false,true),
                                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
                        }
                        else {
                            expandButton.setBackground(new Background(new BackgroundImage(new Image("expanded_Hover.png",30,30,false,true),
                                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
                        }
                        expaned = !expaned;
                        adaptor.setExpaned(expaned);
                        dayInfo.setPrefHeight(expaned ? 362:0);
                        dayInfo.getScene().getWindow().setHeight(expaned ? initialWindowHeight:initialWindowHeight-362);

                    });
                 
                    //Getting Today
                    Locale locale = Locale.getDefault();


                    //delwa2ty
                    day_now = p.getDayById(today);
                    addingAllTasks(day_now, p);
                    System.out.println(day_now.getDayName()+ "Day_Now <<<<<<<<");
                    dayNow = day_now.getDayID();

                    //Current TAsk ------------------------------------------------
                    taskNow = getTaskNow();
                    System.out.println(taskNow+ "Task_Now <<<<<<<<");
                    adaptor.setTasknow(taskNow);
                    setTask(taskNow);
                    //

                    //Current Day
                    //addingAllTasks(day_now);
                    day_Selected = day_now;

                    // Trying Pop up window

                    //Adaptor


                    adaptor.setTaskNow(taskLabel.getText());
                    adaptor.setTasknow(taskNow);
                    adaptor.setDay_now(day_now);

                    Button submit = adaptor.getSubmit();
                    submit.defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            p.getDayById(day_now.getDayID()).mergeTasks(p.getDayById(day_now.getDayID()), adaptor.getDay_now());
                            //TODO: THERE WAS A ! BEFORE THE CONDITION
                            if(adaptor.getProgDone().defaultButtonProperty().getValue() == false) {
                                System.out.println("entered <<<<<<<");
                                taskNow = adaptor.getTasknow(); 
                                p.getDayById(day_now.getDayID()).getTaskByName(adaptor.getTasknow().getTaskName()).setTaskReveiw(adaptor.getTasknow().taskReveiw);
                                p.getDayById(day_now.getDayID()).getTaskByName(adaptor.getTasknow().getTaskName()).setSubmittedPercentage(
                                        adaptor.getTasknow().getSubmittedPercentage());
                                p.getDayById(day_now.getDayID()).getTaskByName(adaptor.getTasknow().getTaskName()).setSubmitted(true);
                                adaptor.getPatient().getWeekPlanByName(p.getWeekPlanName()).setDays(p.getDays());
                                day_now = p.getDayById(day_now.getDayID());
                                taskNow.setSubmitted(true);
                                addingAllTasks(day_now, p);
                                System.out.println(taskNow.getTaskName());
                               // taskNow = getTaskNow();
                                setTask(taskNow);
                                if(taskNow!=null) {
                                    adaptor.setTaskNow(taskNow.getTaskName());
                                    adaptor.setTasknow(taskNow);
                                }
                                
                                adaptor.getSubmit().defaultButtonProperty().setValue(false);
                                adaptor.getMainHBox().setDisable(false);
                                adaptor.getProgDone().defaultButtonProperty().
                                setValue(false);
                              
                               
                            }
                        }
                    });

                    adaptor.getEdit().defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if (newValue) {
                                p.getDayById(adaptor.getDayToEdit().getDayID()).mergeTasks(p.getDayById(adaptor.getDayToEdit().getDayID()), adaptor.getDayToEdit());                                
                                addingAllTasks(adaptor.getDayToEdit(), p);
                                adaptor.getEdit().defaultButtonProperty().setValue(false);
                            }
                        }
                    });

                    progressBar.progressProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                            if((double)newValue == 1.0 && !adaptor.getSubmit().defaultButtonProperty().getValue()){
                                adaptor.getProgDone().defaultButtonProperty().setValue(true);
                                System.out.println("entered progress<<<<<<<");
                                taskNow = adaptor.getTasknow();
                                taskNow.setTaskReveiw(adaptor.getTaskReview());
                                taskNow.setSubmittedPercentage(adaptor.getTaskProg());
                                hour = calendar.get(Calendar.HOUR_OF_DAY);
                                minute = calendar.get(Calendar.MINUTE);
                                p.getDayById(day_now.getDayID()).mergeTasks(p.getDayById(day_now.getDayID()), adaptor.getDay_now());
                                taskNow.setEndTimeH(hour);
                                taskNow.setEndTimeM(minute);
                                taskNow.setSubmitted(true);
                                taskNow = getTaskNow();
                                adaptor.addingAllTasks(day_now, p);
                                setTask(taskNow);
//                                File file = new File("Notification.mp3");
//                                Media media = new Media(file.toURI().toString());
//                                MediaPlayer mediaplayer = new MediaPlayer(media);
//                                mediaplayer.play();
                            	
                            	adaptor.getProgDone().defaultButtonProperty().setValue(true);
                            	
                              
                                
                            }
                        }
                    });


                    adaptor.getFreeDone().defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if(newValue) {
                                System.out.println("entered freeDone Contoller<<<<<<<<<<<<<<<<<<<<<<<");
                                p.getDayById(day_now.getDayID()).mergeTasks(p.getDayById(day_now.getDayID()), adaptor.getDay_now());
                                taskNow = getNextTask(day_now);
                                setTask(taskNow);
                                addingAllTasks(day_now, p);
                                adaptor.setTasknow(taskNow);
                                adaptor.addingAllTasks(day_now, p);
//                                File file = new File("Notification.mp3");
//                                Media media = new Media(file.toURI().toString());
//                                 MediaPlayer mediaplayer = new MediaPlayer(media);
//                                mediaplayer.play();
                                adaptor.getFreedone().defaultButtonProperty().setValue(false);
                                ui_task1.getFreeDone().defaultButtonProperty().setValue(false);
                            }
                        }
                    });
                 
                    //Day Navigation should start here
                    System.out.println(day_now + "DayNow<<<<<<<<<<<");
                  previousDay.setOnMousePressed(event1 -> {
                        dayNow--;
                        dayNow =  ((7 - dayNow)/7) * 7 + dayNow;
                        System.out.println(dayNow);
                        day_Selected = p.getDayById(dayNow);
                        addingAllTasks(day_Selected, p);
                        adaptor.setTasksHBox(tasksHBox);
                    });

                    nextDay.setOnMousePressed(event2 -> {
                        dayNow = dayNow % 7 +1;
                        System.out.println(dayNow);
                        day_Selected = p.getDayById(dayNow);
                        addingAllTasks(day_Selected, p);
                        adaptor.setTasksHBox(tasksHBox);
                    });

            //      adaptor.getLoggedIn().defaultButtonProperty().setValue(false);
                }
            }
        });


    }

    //day Navigation
    public void addingAllTasks(Day day_Selected, WeekPlan p){
        if(day_Selected != null) {
            dayLabel.setText(day_Selected.getDayName());
            tasksHBox.getChildren().clear();
            double total = 0;
            double total1 = 0;
            for (int t = 0; t < day_Selected.getTasks().size(); t++) {
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
                descText.setPrefSize(150, 100);
                descText.setMaxSize(200,100);
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
                String progs = "";
                StringBuffer buff = new StringBuffer();
                for (int d = 0; d < day_Selected.getTask(t).getPrograms().size(); d++) {
                	buff.append("- " + day_Selected.getTask(t).getProgram(d).getBaseName() + "\n");
                   // progs += "- " + day_Selected.getTask(t).getProgram(d).getBaseName() + "\n";
                }
                progs = buff.toString();
                progText.setText(progs); //change
                progText.setFont(new Font("Copperplate Gothic Bold", 14));
                progText.setEditable(false);
                progText.setPrefSize(100, 100);
                progText.setMaxSize(100, 100);
                progText.setWrapText(true);
                progText.setMouseTransparent(true);

                VBox progVBox = new VBox();
                progVBox.setAlignment(Pos.CENTER);
                progVBox.getChildren().addAll(progTitle, progText);

                //StatusLabel
                if (taskNow == day_Selected.getTask(t)) {
                    //System.out.println("MUST GE HERE!!!!<<<<<<<<>>>>>>>");
                    if (!taskNow.isSubmitted()) {
                        day_Selected.getTask(t).setStatus("Running..");
                        System.out.println(hour + ":" + minute);
                    } else {
                        day_Selected.getTask(t).setStatus("Submitted: " + day_Selected.getTask(t).getSubmittedPercentage() + "% Completed");
                        day_Selected.getTask(t).setSubmitted(true);
                        System.out.println(taskNow.getStatus() + "awel awelwa7da begad");
                    }
                } else {
                    if (day_Selected == day_now) {
                        if (day_now.getTask(t).getStartTimeH() == hour && day_now.getTask(t).getEndTimeH() == hour ) {
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
                                    System.out.println(day_now.getTask(t).getStatus() + "tani wa7da begad");

                                } else {
                                	//TODO: changed < in first condition to be <=
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
                                                System.out.println("<><<<<<<><><><><<<<<<><><><<<< Running tani wa7da");
                                            } else {
                                                day_Selected.getTask(t).setStatus("Waiting..");
                                                System.out.println(day_now.getTask(t).getStatus() + "talet wa7da begad");

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
                edit.setStyle("-fx-background-color: #103F66;"+
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; " +
                "-fx-border-style: solid; " +
                "-fx-border-color: #000000; " +
                "-fx-text-fill: white;"+
                "-fx-min-height: 35; " +  
                "-fx-pref-height: 35;");     

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
                newTask.setMaxHeight(220);
                newTask.setPrefHeight(220);
                newTask.setMinHeight(220);

                newTask.setMinWidth(250); //
                newTask.setMaxWidth(250); //
                newTask.setPrefWidth(250);  // change
                newTask.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #103F66; -fx-border-width: 3px; -fx-border-radius: 10px; -fx-text-fill: white;");

                newTask.setId("" + day_Selected.getTask(t).getTaskID());

                //adding Task to taskHBox
                tasksHBox.getChildren().add(newTask);
                //Changing day Progress
                if (day_Selected.getTask(t).isSubmitted()) {
                    total1 += day_Selected.getTask(t).getPercentageOfDay();
                    total += day_Selected.getTask(t).getSubmittedPercentage();
                    
                    if(total1 == 0){
                    	System.out.println((total/total1) +"..........day progress !!!" + " " + total1);
                    	 p.getDayById(day_Selected.getDayID()).setDayProgress((total/100));
                       dayProgress.setProgress((total/100));
                       String progressBarStyle = "-fx-accent: darkgreen;";
                       dayProgress.setStyle(progressBarStyle);
                    }
                    else{
                    System.out.println((total/total1) +"..........day progress !!!");
                    p.getDayById(day_Selected.getDayID()).setDayProgress((total/total1));
                    dayProgress.setProgress((total/total1));
                    String progressBarStyle = "-fx-accent: darkgreen;";
                    dayProgress.setStyle(progressBarStyle);
                    }
                }
            }
           
            
//            System.out.println((total/total1) +"..........day progress !!!");
//            p.getDayById(day_Selected.getDayID()).setDayProgress((total/total1));
//            dayProgress.setProgress((total/total1));
            
            
            
        }
        adaptor.setTasksHBox(tasksHBox);

        for (Node x: tasksHBox.getChildren()) {
            x.setOnMouseEntered(event -> {
                if(day_Selected.getTaskByID(Integer.parseInt(x.getId())).isSubmitted()) {
                    x.setStyle("-fx-background-color: #106662; -fx-text-fill: white;");
                }
            });
            x.setOnMouseExited(event -> {
                if(day_Selected.getTaskByID(Integer.parseInt(x.getId())).isSubmitted()) {
                    x.setStyle("-fx-border-color: #103F66; -fx-border-width: 3px; -fx-border-radius: 10px;");
                }
            });
            x.setOnMouseClicked(event -> {
                if(day_Selected.getTaskByID(Integer.parseInt(x.getId())).isSubmitted()) {
                    adaptor.setDayToEdit(day_Selected);
                    adaptor.setTaskToEdit(day_Selected.getTaskByID(Integer.parseInt(x.getId())));
                    adaptor.getWantToEdit().defaultButtonProperty().setValue(true);
                }
            });
        }
    }
    public Task getTaskNow() {
        Task temp = null;
        if(day_now != null) {
            //int currentTimeInMin = hour*60+minute;
            for (int c = 0; c < day_now.getTasks().size(); c++) {
            	
                if (day_now.getTask(c).getStartTimeH() == hour && day_now.getTask(c).getEndTimeH() == hour && !day_now.getTask(c).isSubmitted()) {

                    if (day_now.getTask(c).getStartTimeM() > minute) {
                        temp = null;
                        System.out.println("<><<<<<<><><><><<<<<<><><><<<< Running 1 wa7da");
                    } else {
                        if (day_now.getTask(c).getStartTimeM() <= minute && day_now.getTask(c).getEndTimeM() > minute) {
                            temp = day_now.getTask(c);
                            return day_now.getTask(c);
                        } else {
                            System.out.println("<><<<<<<><><><><<<<<<><><><<<< Running 2 wa7da");
                            temp = null;

                        }
                    }
                } else {
                    if (day_now.getTask(c).getStartTimeH() == hour && !day_now.getTask(c).isSubmitted()) {
                        if (day_now.getTask(c).getStartTimeM() > minute) {
                            temp = null;
                            System.out.println("<><<<<<<><><><><<<<<<><><><<<< Running 3 wa7da");
                        } else {
                            if (day_now.getTask(c).getStartTimeM() <= minute) {
                                temp = day_now.getTask(c);
                                break;
                            }
                        }
                    } else {
                        if (day_now.getTask(c).getStartTimeH() > hour && !day_now.getTask(c).isSubmitted()) {
                            temp = null;
                            System.out.println("<><<<<<<><><><><<<<<<><><><<<< Running 4 wa7da");
                        } else {
                            if (day_now.getTask(c).getEndTimeH() == hour && !day_now.getTask(c).isSubmitted()) {
                                if (minute < day_now.getTask(c).getEndTimeM()) {
                                    temp = day_now.getTask(c);
                                    break;
                                } else {
                                    temp = null;
                                    System.out.println("<><<<<<<><><><><<<<<<><><><<<< Running 5 wa7da");
                                }
                            } else {
                                if (hour < day_now.getTask(c).getEndTimeH() && !day_now.getTask(c).isSubmitted()) {
                                    temp = day_now.getTask(c);
                                    break;
                                } else {
                                    temp = null;
                                    System.out.println("start: " + day_now.getTask(c).getStartTimeH() 
                                    	+ ":" + day_now.getTask(c).getStartTimeM() + " " + hour + ":" + minute);
                                    
                                    System.out.println("start: " + day_now.getTask(c).getEndTimeH() 
                                        	+ ":" + day_now.getTask(c).getEndTimeM() + " " + hour + ":" + minute);
                                    System.out.println("<><<<<<<><><><><<<<<<><><><<<< Running 6 wa7da");
                                }
                            }
                        }
                    }
                }

            }
        }
        return temp;
    }

    public Task getNextTask(Day day_Now){
        int leastMinDif = 1000;
        int leastHourDif = 1000;
        int arrayMinAdd = 0;
        int arrayHourAdd = 0;
        int temp = 0;
        System.out.println("GETTING NEXT TASK");
        if(day_Now != null) {
            for (int k = 0; k < day_Now.getTasks().size(); k++) {
                if (!day_Now.getTask(k).isSubmitted()) {
                    if (day_Now.getTask(k).getStartTimeH() == hour) {
                        if (((day_Now.getTask(k).getStartTimeM() - minute) >= 0) && ((day_Now.getTask(k).getStartTimeM() - minute) < leastMinDif)) {
                            leastMinDif = day_Now.getTask(k).getStartTimeM() - minute;
                            arrayMinAdd = k;
                        }
                    } else {
                        if ((day_Now.getTask(k).getStartTimeH() > hour)) {
                            temp = 60 * (day_Now.getTask(k).getStartTimeH() - hour - 1) + (60 - minute) + day_Now.getTask(k).getStartTimeM();
                            if (temp < leastHourDif) {
                                leastHourDif = temp;
                                arrayHourAdd = k;
                            }
                        }
                    }
                }
            }
        }
        if(leastMinDif != 1000){
        	System.out.println("NAME OF THE NEXT TASK IS: " + day_Now.getTask(arrayMinAdd).getTaskName());
            return day_Now.getTask(arrayMinAdd);
        }
        else{
            if(leastHourDif != 1000) {
                return day_Now.getTask(arrayHourAdd);
            }
            else {
                return null;
            }
        }
    }

    public Task getPreviousTask(Day day_Now){
        int leastMinDif = 1000;
        int leastHourDif = 1000;
        int arrayMinAdd = 0;
        int arrayHourAdd = 0;
        int temp = 0;
        if(day_Now != null) {
            for (int k = 0; k < day_Now.getTasks().size(); k++) {
                if (day_Now.getTask(k).isSubmitted()) {
                    if (day_Now.getTask(k).getEndTimeH() == hour) {
                        if ((minute - day_Now.getTask(k).getEndTimeM()) >= 0 && (minute - day_Now.getTask(k).getEndTimeM()) < leastMinDif) {
                            leastMinDif = minute - day_Now.getTask(k).getEndTimeM();
                            arrayMinAdd = k;
                        }
                    } else {
                        if (day_Now.getTask(k).getEndTimeH() < hour) {
                            temp = 60 * (hour - day_Now.getTask(k).getEndTimeH() - 1) + (60 - day_Now.getTask(k).getEndTimeM()) + minute;
                            if (temp < leastHourDif) {
                                leastHourDif = temp;
                                arrayHourAdd = k;
                            }
                        }
                    }
                }
            }
        }
        if(leastMinDif != 1000){
            return day_Now.getTask(arrayMinAdd);
        }
        else{
            if(leastHourDif != 1000) {
                return day_Now.getTask(arrayHourAdd);
            }
            else {
                return null;
            }
        }
    }
    public int getFreeMin(Task prev, Task next){
        int h,m;
        if(prev != null && next != null) {
            if (next.getStartTimeH() == prev.getEndTimeH()) {
                System.out.print(next.getStartTimeM() + "<<<<<<----------------" + prev.getEndTimeM());
                return next.getStartTimeM() - prev.getEndTimeM();
            } else {
                h = next.getStartTimeH() - prev.getEndTimeH();
                m = (60 - prev.getEndTimeM()) + next.getStartTimeM();
                return (h * 60 + m);
            }
        }else {
            return 0;
        }

    }
    public void setTask(Task t){
        if(t != null) {
            submitButton.setDisable(false);
            ui_task1.bindTitleToLabel(t, taskLabel);
            t.setDurationInMinutes();
            adaptor.setTasknow(t);
            ui_task1.bindDescriptionToLabel(t, descriptionField);
            hboxProgress.getChildren().clear();
            hboxProgress.getChildren().addAll(sTime, progressBar, rTime);
            ui_task1.bindStartTimeToLabel(t, sTime);
            ui_task1.bindRemainingTimeToLabel(t, rTime, progressBar);
        }
        else{
            if(!isLast(day_now)) {
                submitButton.setDisable(true);
                t = getNextTask(day_now);
                previousTask = getPreviousTask(day_now);
                freeMin = getFreeMin(t, previousTask);
                ui_task1.bindTitleToLabelNotActive(t, taskLabel);
                ui_task1.bindDescriptionToLabel(t, descriptionField);
                hboxProgress.getChildren().clear();
                Label comeBack = new Label();
                comeBack.setTextAlignment(TextAlignment.CENTER);
                comeBack.setAlignment(Pos.CENTER);
                comeBack.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 16));
                comeBack.setContentDisplay(ContentDisplay.CENTER);
                comeBack.setPrefWidth(Control.USE_COMPUTED_SIZE);
                comeBack.setPrefWidth(Region.USE_COMPUTED_SIZE);
                hboxProgress.getChildren().add(comeBack);
                ui_task1.bindRemainingTimeTillTask(t, freeMin, comeBack);
                adaptor.getMainHBox().setDisable(false);
                
            }
            else {
                submitButton.setDisable(true);
                double total = day_now.tasks.size()*100;
                double doneTotal = 0;
                for(int z = 0; z < day_now.tasks.size(); z++){
                    doneTotal += day_now.getTask(z).submittedPercentage;
                }
                System.out.println(doneTotal + "!!!!<<<<<< Done total");
                System.out.println(total + "!!!!<<<<<< total");
            	
                if(((doneTotal)/(total)) < 0.5){
                    adaptor.getVideoButton().defaultButtonProperty().setValue(true);
                }
                taskLabel.setText("Done!");
                Label done1 = new Label("You have finished all the available tasks for today");
                done1.setTextAlignment(TextAlignment.CENTER);
                done1.setAlignment(Pos.CENTER);
                done1.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 15));
                done1.setContentDisplay(ContentDisplay.CENTER);
                done1.setPrefWidth(Control.USE_COMPUTED_SIZE);
                done1.setPrefHeight(30);
                
                Label done2 = new Label("Great Job!");
                done2.setTextAlignment(TextAlignment.CENTER);
                done2.setAlignment(Pos.CENTER);
                done2.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 15));
                done2.setContentDisplay(ContentDisplay.CENTER);
                done2.setPrefWidth(Control.USE_COMPUTED_SIZE);
                done2.setPrefHeight(30);
                
                
                //Report button that calls it from report class
                Button Report = new Button("Report");
                Report.setTextAlignment(TextAlignment.RIGHT);
                Report.setAlignment(Pos.CENTER_RIGHT);
                Report.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 15));
                Report.setContentDisplay(ContentDisplay.RIGHT);
                Report.setPrefWidth(Control.USE_COMPUTED_SIZE);
                Report.setPrefHeight(30);
                Report.setStyle("-fx-background-color: #103F66;"+
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; " +
                "-fx-border-style: solid; " +
                "-fx-border-color: #000000; " +
                "-fx-text-fill: white;"+
                "-fx-min-height: 35; " +  
                "-fx-pref-height: 35;");      
                
                
                // Create Chat button
                Button chatButton = new Button("Chat");
                chatButton.setTextAlignment(TextAlignment.RIGHT);
                chatButton.setAlignment(Pos.CENTER_RIGHT);
                chatButton.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 15));
                chatButton.setContentDisplay(ContentDisplay.RIGHT);
                chatButton.setPrefWidth(Control.USE_COMPUTED_SIZE);
                chatButton.setPrefHeight(30);
                chatButton.setStyle("-fx-background-color: #103F66;"+
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; " +
                "-fx-border-style: solid; " +
                "-fx-border-color: #000000; " +
                "-fx-text-fill: white;"+
                "-fx-min-height: 35; " +  
                "-fx-pref-height: 35;");

                chatButton.setOnMouseClicked(event -> {
                    openChatWindow();
                });
                
                
                
                
                
                
                //Notification button that calls it from Notification class
                Button Notifications = new Button("Notifications");
                Notifications.setTextAlignment(TextAlignment.RIGHT);
                Notifications.setAlignment(Pos.CENTER_RIGHT);
                Notifications.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 15));
                Notifications.setContentDisplay(ContentDisplay.RIGHT);
                Notifications.setPrefWidth(Control.USE_COMPUTED_SIZE);
                Notifications.setPrefHeight(30);
                Notifications.setStyle("-fx-background-color: #2b686d55;");
                Notifications.setVisible(false);

                HBox buttonBox = new HBox();
                buttonBox.setSpacing(10); // Adjust the spacing between buttons
                buttonBox.getChildren().addAll(Notifications ,Report, chatButton);
                
                
                //notification functions and logic
        		ActionEvent event = null;
        		Notifications.setOnAction(e -> NotificationController.handleButtonAction((ActionEvent)event));
        		Report.setOnAction(e -> ReportFeed.display((String)"A Reminder Alert to your on your task with countdown"));
                vBoxTask.getChildren().clear();
                vBoxTask.getChildren().addAll(done1, done2,buttonBox);
                vBoxTask.setAlignment(Pos.CENTER);
                
        	/*	ActionEvent event = null;
        		Report.setOnAction(e -> Timer.handleButtonAction((ActionEvent)Alertevent));
            */
            }
        }
    }
    



    public boolean isLast(Day day_Now){
        boolean isIt = true;
        if(day_Now != null) {
            //int taskNowEndTime = adaptor.getTasknow().getEndTimeH()*60 + adaptor.getTasknow().getEndTimeM();
            for (int d = 0; d < day_Now.getTasks().size(); d++) {
//                int dayTaskEndTime = day_Now.getTask(d).getEndTimeH()*60 + day_Now.getTask(d).getEndTimeM();
//                if(dayTaskEndTime > taskNowEndTime){
//                    return false;
//                }
                if (day_Now.getTask(d).getEndTimeH() == hour) {
                    if (day_Now.getTask(d).getEndTimeM() < minute && day_Now.getTask(d).isSubmitted()) {
                        isIt = true;
                    } else {
                        if (day_Now.getTask(d).getEndTimeM() > minute && !day_Now.getTask(d).isSubmitted()) {
                            isIt = false;
                        }
                    }
                } else {
                    if (day_Now.getTask(d).getEndTimeH() < hour && day_Now.getTask(d).isSubmitted()) {
                        isIt = true;
                    } else {
                        if (day_Now.getTask(d).getEndTimeH() > hour && !day_Now.getTask(d).isSubmitted()) {
                            isIt = false;
                        }
                    }
                }
            }
        }
        return isIt;
    }

	public static Button getReport() {
		return Report;
	}
    private void refreshChat() {
        // Send HTTP request to the server
        String response = sendHTTPRequest("https://server---app-d244e2f2d7c9.herokuapp.com" + "/getChat/?coachName=" + adaptor.getPatient().getCoachName() + "&patientName=" + adaptor.getPatient().getPatientName());
    
        try {
            // Parse the JSON array
            JSONArray jsonArray = new JSONArray(response);
    
            // Get the inbox array from the JSON object
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray inboxArray = jsonObject.getJSONArray("inbox");
    
            // Display each sender-message pair in the message display area
            StringBuilder formattedMessages = new StringBuilder();
            for (int i = 0; i < inboxArray.length(); i++) {
                String senderMessage = inboxArray.getString(i);
                formattedMessages.append(senderMessage).append("\n");
            }
            messageDisplay.setText(formattedMessages.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void openChatWindow() {
        // Define chatbox UI components
        startChatRefresh();

        TextField chatInput = new TextField();
        chatInput.setPrefHeight(50.0);
        chatInput.setPrefWidth(399.0);
        chatInput.setPromptText("Type a message...");

        Button sendButton = new Button();
        sendButton.setPrefHeight(35.0);
        sendButton.setPrefWidth(65.0);
        sendButton.setText("Send");
        sendButton.setStyle("-fx-background-color: #103F66;"+
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 20px; " +
                    "-fx-background-radius: 20px; " +
                    "-fx-border-style: solid; " +
                    "-fx-border-color: #000000; " +
                    "-fx-text-fill: white;"+
                    "-fx-min-height: 35; " +  
                    "-fx-pref-height: 35;");
        sendButton.setTextFill(javafx.scene.paint.Color.WHITE);
        sendButton.getStyleClass().add("inner_pane");





        String coachName = adaptor.getPatient().getCoachName();
        String patientName = adaptor.getPatient().getPatientName();

        sendButton.setOnAction(e -> {
            String message = chatInput.getText();
            System.out.println("Message sent: " + message);      
             String encodedMessage = "";
        try {
            encodedMessage = URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException a) {
            a.printStackTrace();
            // Handle encoding exception
        } 
            String url = "https://server---app-d244e2f2d7c9.herokuapp.com/sendMassegeP/?coachName=" + coachName + "&patientName=" + patientName + "&message=" + encodedMessage;
            sendHTTPRequestPost(url);
            chatInput.clear();
            refreshChat();
        });
      
        
    
        messageDisplay = new TextArea();
        messageDisplay.setEditable(false);        
        messageDisplay.setPrefHeight(333.0);
        messageDisplay.setPrefWidth(474.0);
        messageDisplay.setStyle("-fx-background-color: inherit;");
    
    
        HBox inputLayout = new HBox(chatInput, sendButton);
        inputLayout.setAlignment(Pos.CENTER);
        inputLayout.setSpacing(10);
    
        VBox chatboxLayout = new VBox(messageDisplay, inputLayout);
        chatboxLayout.setAlignment(Pos.CENTER);
        chatboxLayout.setSpacing(10);
        chatboxLayout.setAlignment(javafx.geometry.Pos.CENTER);
        chatboxLayout.setPrefHeight(439.0);
        chatboxLayout.setPrefWidth(505.0);
        chatboxLayout.setStyle("-fx-background-color: #bebeb6; -fx-border-color: #103F66; -fx-border-width: 3px;"); 

    
        Stage chatboxStage = new Stage();
        chatboxStage.setTitle(adaptor.getPatient().getCoachName());
        chatboxStage.setScene(new Scene(chatboxLayout, 505, 439));
    
        chatboxStage.initModality(Modality.APPLICATION_MODAL);
    
        chatboxStage.show();
        chatboxStage.setOnCloseRequest(event->stopRefresh());
    }
    private void startChatRefresh() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            // Send HTTP request to refresh chat
            refreshChat();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void stopRefresh() {
        if (timeline != null) {
            timeline.stop();
        }
    }
  
   private String sendHTTPRequest(String urlString) {
    StringBuilder response = new StringBuilder();
    try {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
    } catch (IOException e) {
        e.printStackTrace(); // Handle the exception properly in your application
    }
    return response.toString();
}
private String sendHTTPRequestPost(String url) {
    try {
        // Create the URL object
        URL requestUrl = new URL(url);

        // Create the HttpURLConnection object
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("POST");

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Read the response from the server
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        // Close the connection
        connection.disconnect();

        // Return the response
        return response.toString();
    } catch (IOException e) {
        e.printStackTrace();
        // Handle the exception as needed
        return null;
    }
}

    
}
    




    