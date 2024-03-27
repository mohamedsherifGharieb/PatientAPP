package sample;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sample.UI.UITask;
import sample.WeekPlan.Patient;
import sample.utils.LoadPlan;
import sample.utils.SavePlan;

public class ReloadApp extends Application{
	public String patientUsername = "";
	public String patientPassword = "";
	public Button reloadapp;
	public ReloadApp(String username,String password){
		patientUsername = username;
		patientPassword = password;
		reloadapp = new Button();
	}
	
	
	 Stage dialog = new Stage();
	Stage editTask = new Stage();
    Button freeDone;
    UITask uiTask = UITask.getInstance();
    Stage webView = new Stage();
    Stage loginStage = new Stage();
    Stage signupStage = new Stage();
    Stage errorMessage = new Stage();
    String pgRequest = "";
    final String local = "http://localhost:8080";
    final String heroku = "https://safe-reaches-92386-edf83a4939bb.herokuapp.com/";
    String urlFinal = heroku;
    public void start(Stage primaryStage) throws Exception {
    	
    	System.out.println("IN THE RELOADAPP");
    	
        //setting buttons effect
        int depth = 3;
        DropShadow borderGlow= new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.WHITE);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        Adaptor adaptor = Adaptor.getInstance();
        //dialog
        VBox dialogVbox = new VBox();
        //SubmitLabel
        Label submitLabel = new Label("Submit"); //this is the label of the box containing the submit button
        submitLabel.setTextAlignment(TextAlignment.CENTER);
        submitLabel.setAlignment(Pos.CENTER);
        submitLabel.setTextFill(Color.WHITE);
        submitLabel.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 26));
        submitLabel.setContentDisplay(ContentDisplay.CENTER);
        submitLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
        submitLabel.setPrefHeight(60);

        HBox submitLabelHBox = new HBox();
        submitLabelHBox.getChildren().add(submitLabel);
        submitLabelHBox.setAlignment(Pos.CENTER);
        submitLabelHBox.setStyle("-fx-background-color: #2e6a6f;");
        //taskName
        Label taskName = new Label(adaptor.getTaskNow());
        taskName.setTextAlignment(TextAlignment.CENTER);
        taskName.setAlignment(Pos.CENTER);
        taskName.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 22));
        taskName.setContentDisplay(ContentDisplay.CENTER);
        taskName.setPrefWidth(Control.USE_COMPUTED_SIZE);
        taskName.setPrefHeight(40);

        //Reviue HBox
        Label review = new Label("Review");
        review.setTextAlignment(TextAlignment.CENTER);
        review.setAlignment(Pos.CENTER);

        review.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 18));
        review.setContentDisplay(ContentDisplay.CENTER);
        review.setPrefWidth(100);
        review.setPrefHeight(30);

        TextArea reviewArea = new TextArea();
        reviewArea.setPrefSize(275, 150);
        reviewArea.setWrapText(true);
        reviewArea.setFont(new Font("Copperplate Gothic Bold", 16));
        reviewArea.setEditable(true);

        HBox revewHBox = new HBox();
        revewHBox.getChildren().addAll(review, reviewArea);
        revewHBox.setAlignment(Pos.TOP_CENTER);

        //done%
        Slider slider =  new Slider(0, 100, 0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMinorTickCount(1);
        slider.setMin(0);
        slider.setMax(100);
        slider.setMaxWidth(375);
        slider.setPrefSize(25, 25);

        Label done = new Label("done: ");
        done.setAlignment(Pos.CENTER);
        done.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 18));
        done.setContentDisplay(ContentDisplay.CENTER);
        done.setPrefWidth(100);
        done.setPrefHeight(30);

        TextField donePer = new TextField();
        donePer.setText(""+ Math.round(slider.getValue()));
        donePer.setPrefSize(45, 30);
        donePer.setEditable(false);
        donePer.setAlignment(Pos.CENTER);

        Label per = new Label("%");
        per.setAlignment(Pos.CENTER);
        per.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 18));
        per.setContentDisplay(ContentDisplay.CENTER);
        per.setPrefWidth(50);
        per.setPrefHeight(30);

        HBox doneHBox = new HBox();
        doneHBox.getChildren().addAll(done, donePer, per);
        doneHBox.setAlignment(Pos.TOP_CENTER);


        VBox doneVBox = new VBox();
        doneVBox.setAlignment(Pos.CENTER);
        doneVBox.setPrefWidth(350);
        doneVBox.setPrefHeight(40);
        doneVBox.getChildren().addAll(slider, doneHBox);

        //Buttons
        Button submit = new Button("Submit"); //the submit before time ends and the normal submit
        submit.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 20));
        submit.setTextFill(Color.WHITE);
        submit.setPrefWidth(150);
        submit.setPrefHeight(25);
        submit.setStyle("-fx-background-color: #2b686d55;");

        Button cancel = new Button("Cancel");

        cancel.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 20));
        cancel.setPrefWidth(150);
        cancel.setTextFill(Color.WHITE);
        cancel.setPrefHeight(25);
        cancel.setStyle("-fx-background-color: #2b686d55;");

        HBox buttons = new HBox();
        buttons.setStyle("-fx-background-color: #2e6a6f;");
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(50);
        buttons.getChildren().addAll(submit, cancel);
        adaptor.setCancel(cancel);
        adaptor.setSubmit(submit);

        dialogVbox.getChildren().addAll(submitLabelHBox, taskName, revewHBox, doneVBox, buttons);
        dialogVbox.setAlignment(Pos.TOP_CENTER);
        dialogVbox.setSpacing(5);
        Scene dialogScene = new Scene(dialogVbox, 600, 370 );


        //primaryStage
        
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root));

        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("Resized :)))");
            }
        });

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        System.out.println("width is: " + width + " and height is: " + height);
        primaryStage.setX((width)/2-510);
        primaryStage.setY((height)/2-400);
        //primaryStage.setX((width / 2) - 510);
        //primaryStage.setY((height / 2) - 540); // - 100 to hide small task
        primaryStage.sizeToScene();
        primaryStage.setAlwaysOnTop(true);



        //webView to connect to server
        WebView web = new WebView();

        web.setPrefSize(640, 390);
        webView = new Stage();
        webView.initModality(Modality.APPLICATION_MODAL);
        webView.initOwner(primaryStage);
        webView.centerOnScreen();
        webView.setScene(new Scene(web));


        adaptor.getReloadapp().defaultButtonProperty().setValue(true);
        
        final WebEngine webengine = web.getEngine();
       
        pgRequest = "patientLogin";
        web.getEngine().load(urlFinal + "/patientLogin/?userName=" + patientUsername+ "&password=" + patientPassword);
        System.out.println("sent the request to fetch the patient file");
        

//TODO: UPDATE PATIENT FILE IN POSTGRESQL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! & change SavePlan to return String or just read file
        primaryStage.setOnCloseRequest(event -> {
            pgRequest = "";
            
           
                 try {
						 SavePlan.savePlan(adaptor.getPatient()); 
						 String urlParameter1 = "patientName=" + adaptor.getPatient().getPatientName();
            		 String urlParamter2 = "file=" + adaptor.getPatient().getFile();
            		 String param = urlParameter1 + "&" + urlParamter2;	                    		 
            		 URL url = new URL(urlFinal + "/setPatientFile/"); 
            		 HttpURLConnection con = (HttpURLConnection) url.openConnection();
            		 con.setDoOutput(true);
            		 con.setDoInput(true);	                    		 
            		 con.setRequestMethod("POST");
            		 con.setRequestProperty("User-Agent", "Mozilla/5.0");	                    	
            		 try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                         wr.writeBytes(param.toString());
                         wr.flush();
                         wr.close();
                     }
            		 StringBuilder content;

                     try (BufferedReader in = new BufferedReader(
                             new InputStreamReader(con.getInputStream()))) {

                         String line;
                         content = new StringBuilder();

                         while ((line = in.readLine()) != null) {
                             content.append(line);
                             content.append(System.lineSeparator());
                             
                         }
                        System.out.println(content.toString());
                         con.disconnect();
                       //  primaryStage.close();
                     }

            		
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        

        });

        //error message
        VBox errorMessageVBox = new VBox();
        //error Label
        Label errorLabel = new Label("Error");
        errorLabel.setTextAlignment(TextAlignment.CENTER);
        errorLabel.setAlignment(Pos.CENTER);
        errorLabel.setTextFill(Color.WHITE);
        errorLabel.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 24));
        errorLabel.setContentDisplay(ContentDisplay.CENTER);
        errorLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
        errorLabel.setPrefHeight(60);

        HBox errorLabelHBox = new HBox();
        errorLabelHBox.getChildren().add(errorLabel);
        errorLabelHBox.setAlignment(Pos.CENTER);
        errorLabelHBox.setStyle("-fx-background-color: #476A6F;");

        //error Messaage
        Label errorText = new Label("");
        errorText.setTextAlignment(TextAlignment.CENTER);
        errorText.setAlignment(Pos.CENTER);
        errorText.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 16));
        errorText.setContentDisplay(ContentDisplay.CENTER);
        errorText.setPrefWidth(460);
        errorText.setPrefHeight(70);

        HBox errorTextHBox = new HBox();
        errorTextHBox.getChildren().addAll(errorText);
        errorTextHBox.setAlignment(Pos.CENTER);

        //Buttons
        Button errorOkButton = new Button("Ok");
        errorOkButton.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 20));
        errorOkButton.setPrefWidth(150);
        errorOkButton.setTextFill(Color.WHITE);
        errorOkButton.setPrefHeight(25);
        errorOkButton.setStyle("-fx-background-color: #2b686d55;");

        errorOkButton.setOnMouseEntered(event4 -> {
            errorOkButton.setEffect(borderGlow);
        });
        errorOkButton.setOnMouseExited(event4 -> {
            errorOkButton.setEffect(null);
        });

        HBox buttonsError = new HBox();
        buttonsError.setStyle("-fx-background-color: #2e6a6f;");
        buttonsError.setAlignment(Pos.CENTER);
        buttonsError.setSpacing(70);
        buttonsError.getChildren().addAll(errorOkButton);

        errorMessageVBox.getChildren().addAll(errorLabelHBox, errorTextHBox, buttonsError);
        errorMessageVBox.setAlignment(Pos.CENTER);
        errorMessageVBox.setSpacing(30);
        Scene errorScene = new Scene(errorMessageVBox, 500, 208);

        errorMessage.initModality(Modality.APPLICATION_MODAL);
        errorMessage.initOwner(primaryStage);
        errorMessage.centerOnScreen();
        errorMessage.initStyle(StageStyle.TRANSPARENT);
        errorMessage.setAlwaysOnTop(true);
        errorMessage.setResizable(false);


        root.setOnMouseMoved(e -> {

            if( e.getScreenX() > primaryStage.getX() && e.getScreenX() < primaryStage.getX() + primaryStage.getWidth() &&
                e.getScreenY() < 10 ) {

                if(!adaptor.isExpaned() && adaptor.isHidden()) {
                    adaptor.setHidden(false);
                    System.out.println("Revive");
                    primaryStage.getScene().getWindow().setY(0);
                //    primaryStage.setAlwaysOnTop(true);
                }
            }

        });
        root.setOnMouseExited(e -> {
            if(!adaptor.isExpaned() && !adaptor.isHidden()) {
                adaptor.setHidden(true);
                System.out.println("Kill");
                primaryStage.getScene().getWindow().setY(-92);
                primaryStage.setAlwaysOnTop(true);
            }

        });

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                donePer.setText(""+ Math.round(slider.getValue()));
                adaptor.setTaskProg((int)Math.round(slider.getValue()));
            }
        });
        submit.setOnMouseEntered(event2 -> {
            submit.setEffect(borderGlow);
        });
        submit.setOnMouseExited(event2 -> {
            submit.setEffect(null);
        });
        cancel.setOnMouseEntered(event2 -> {
            cancel.setEffect(borderGlow);
        });
        cancel.setOnMouseExited(event2 -> {
            cancel.setEffect(null);
        });
        
        submit.setOnAction(event1 -> { //the only submit that matters?
                    adaptor.getMainHBox().setDisable(false);
                   // adaptor.getMainVBox().setDisable(false);
        			adaptor.setTaskReviewPer(reviewArea.getText() + " ");
                    adaptor.getTasknow().setSubmittedPercentage(Integer.parseInt(donePer.getText()));
                    adaptor.getTasknow().setSubmitted(true);
                    adaptor.addingAllTasks(adaptor.getDay_now(), adaptor.getWeekPlan());  
                    dialog.close();
                    adaptor.getSubmit().defaultButtonProperty().setValue(true);
                    
                  try {
						 SavePlan.savePlan(adaptor.getPatient()); 
						 String urlParameter1 = "patientName=" + adaptor.getPatient().getPatientName();
             		 String urlParamter2 = "file=" + adaptor.getPatient().getFile();
             		 String param = urlParameter1 + "&" + urlParamter2;	                    		 
             		 URL url = new URL(urlFinal + "/setPatientFile/"); 
             		 HttpURLConnection con = (HttpURLConnection) url.openConnection();
             		 con.setDoOutput(true);
             		 con.setDoInput(true);	                    		 
             		 con.setRequestMethod("POST");
             		 con.setRequestProperty("User-Agent", "Mozilla/5.0");	                    	
             		 try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                          wr.writeBytes(param.toString());
                          wr.flush();
                          wr.close();
                      }
             		 StringBuilder content;

                      try (BufferedReader in = new BufferedReader(
                              new InputStreamReader(con.getInputStream()))) {

                          String line;
                          content = new StringBuilder();

                          while ((line = in.readLine()) != null) {
                              content.append(line);
                              content.append(System.lineSeparator());
                              
                          }
                         System.out.println(content.toString());
                          con.disconnect();
                        //  primaryStage.close();
                      }

             		
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                  primaryStage.close();
                  ReloadApp r = new ReloadApp(patientUsername,patientPassword);
                  Platform.runLater(() ->{
                     try {
                   	adaptor.getReloadapp().defaultButtonProperty().setValue(true);
						r.start(new Stage());
						this.stop();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                  });
          

                    //returning values like they were for next task
                //    adaptor.getSubmitButton().defaultButtonProperty().setValue(true);
                    
                   
                

        });
        cancel.setOnAction(event1 -> {
            adaptor.getMainHBox().setDisable(false);
            dialog.close();
        });

//youtube popup
        Button submitbtn = adaptor.getSubmitButton();
        submitbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	System.out.println("in submitbtnnnn");
               dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                taskName.setText(adaptor.getTasknow().getTaskName());
                buttons.getChildren().clear();
                submit.setId("" + adaptor.getTasknow().getTaskID());
                buttons.getChildren().addAll(submit, cancel);
                dialogVbox.getChildren().clear();
                dialogVbox.getChildren().addAll(submitLabelHBox, taskName, revewHBox, doneVBox, buttons);
                dialog.initOwner(primaryStage);
                dialog.centerOnScreen();
                dialog.initStyle(StageStyle.TRANSPARENT);
                dialog.setAlwaysOnTop(false);
                dialog.setScene(dialogScene);
                dialog.setResizable(false);
               adaptor.getSubmitButton().defaultButtonProperty().setValue(true);
                //TODO: was true at first
                adaptor.getMainHBox().setDisable(false);
                
                System.out.println("submitbtn todo");
                
                dialog.show();
//                File file = new File("Notification.mp3");
//                Media media = new Media(file.toURI().toString());
//                MediaPlayer mediaplayer = new MediaPlayer(media);
//                mediaplayer.play();
            	
            //	adaptor.getProgDone().defaultButtonProperty().setValue(true);
                
         
            }
        });
        
      //this is where the submit box appears
        adaptor.getProgDone().defaultButtonProperty().addListener(new ChangeListener<Boolean>() { 
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue ) {
                	
                			 dialog = new Stage();
                             dialog.initModality(Modality.APPLICATION_MODAL);
                             taskName.setText(adaptor.getTasknow().getTaskName());
                             buttons.getChildren().clear();
                             submit.setId("" + adaptor.getTasknow().getTaskID());
                             buttons.getChildren().addAll(submit);
                             dialogVbox.getChildren().clear();
                             dialogVbox.getChildren().addAll(submitLabelHBox, taskName, revewHBox, doneVBox, buttons);
                             dialog.initOwner(primaryStage);
                             dialog.initStyle(StageStyle.TRANSPARENT);
                             dialog.setAlwaysOnTop(false);
                             dialog.setScene(dialogScene);
                             dialog.setResizable(false);
                             dialog.centerOnScreen();
//                             File file = new File("Notification.mp3");
//                             Media media = new Media(file.toURI().toString());
//                             MediaPlayer mediaplayer = new MediaPlayer(media);
//                             mediaplayer.play();
                             //TODO: this disable was true at the beginning
                            // adaptor.getMainHBox().setDisable(true);
                             	
                            	 System.out.println("in condition");
                             
                             dialog.show();
                          
                            // TODO: THIS TURNED OUT TO BE THE PROBLEM
                             adaptor.getProgDone().defaultButtonProperty().setValue(false);
                         
                             
                             
//                	submitbtn.fire();
//                	System.out.println("in progdone");
//                	adaptor.getProgDone().defaultButtonProperty().setValue(false);
                             }
//                else{
//                	adaptor.getMainHBox().setDisable(false);
//                	adaptor.getSubmit().setDisable(false);
//                	adaptor.getSubmitButton().setDisable(false);
//                }
                             
                		}
                
            
        });

        uiTask.getFreeDone().defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue && dialog.isShowing()) {
                    System.out.println("dialogue showing<<<<<<");
                    System.out.println(newValue);
                    adaptor.getMainHBox().setDisable(false);
                    //adaptor.getMainVBox().setDisable(false);
                    adaptor.setTaskReviewPer(reviewArea.getText() + " ");
                    adaptor.getTasknow().setSubmittedPercentage(Integer.parseInt(donePer.getText()));
                    adaptor.getTasknow().setSubmitted(true);
                    adaptor.addingAllTasks(adaptor.getDay_now(), adaptor.getWeekPlan());
                    dialog.close();
                    uiTask.getFreeDone().defaultButtonProperty().setValue(false);
                    adaptor.getFreeDone().defaultButtonProperty().setValue(false);
                    
                    //trying something
                    adaptor.getSubmit().defaultButtonProperty().setValue(true);
                   
                    
                    
                }
                else {
                    if(newValue){
                    	System.out.println(newValue);
                        System.out.println("dialogue not showing>>>>>>>>>");
                        adaptor.getMainHBox().setDisable(false);
                     //   adaptor.getMainVBox().setDisable(false);
                        adaptor.getFreeDone().defaultButtonProperty().setValue(true);
                       
                       
                     
                    }
                }
            }
        });

        adaptor.getWantToEdit().defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    //Label
                    Label edit = new Label("Submit");
                    edit.setTextAlignment(TextAlignment.CENTER);
                    edit.setAlignment(Pos.CENTER);
                    edit.setTextFill(Color.WHITE);
                    edit.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 24));
                    edit.setContentDisplay(ContentDisplay.CENTER);
                    edit.setPrefWidth(Control.USE_COMPUTED_SIZE);
                    edit.setPrefHeight(60);
                    //edit Label HBox
                    HBox elh = new HBox();
                    elh.getChildren().add(edit);
                    elh.setAlignment(Pos.CENTER);
                    elh.setStyle("-fx-background-color: #2e6a6f;");
                    //taskName
                    Label tname = new Label(adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).getTaskName());
                    tname.setTextAlignment(TextAlignment.CENTER);
                    tname.setAlignment(Pos.CENTER);
                    tname.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 22));
                    tname.setContentDisplay(ContentDisplay.CENTER);
                    tname.setPrefWidth(Control.USE_COMPUTED_SIZE);
                    tname.setPrefHeight(40);
                    //Review HBox
                    Label r = new Label("Review:");
                    r.setTextAlignment(TextAlignment.CENTER);
                    r.setAlignment(Pos.CENTER);
                    r.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 18));
                    r.setContentDisplay(ContentDisplay.CENTER);
                    r.setPrefWidth(100);
                    r.setPrefHeight(30);

                    TextArea ra = new TextArea();
                    ra.setPrefSize(275, 150);
                    ra.setWrapText(true);
                    ra.setText(adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).getTaskReview());
                    ra.positionCaret(ra.getText().length());
                    ra.setEditable(true);

                    HBox rah = new HBox();
                    rah.getChildren().addAll(r, ra);
                    rah.setAlignment(Pos.TOP_CENTER);

                    //done%
                    Slider s =  new Slider(0, 100, 0);
                    s.setShowTickMarks(true);
                    s.setShowTickLabels(true);
                    s.setValue(adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).getSubmittedPercentage());
                    s.setMinorTickCount(1);
                    s.setMin(0);
                    s.setMax(100);
                    s.setMaxWidth(375);
                    s.setPrefSize(25, 25);

                    Label dl = new Label("done: ");
                    dl.setAlignment(Pos.CENTER);
                    dl.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 18));
                    dl.setContentDisplay(ContentDisplay.CENTER);
                    dl.setPrefWidth(100);
                    dl.setPrefHeight(30);

                    TextField dp = new TextField();
                    dp.setText(""+ adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).getSubmittedPercentage());
                    dp.setPrefSize(45, 30);
                    dp.setEditable(false);
                    dp.setAlignment(Pos.CENTER);

                    Label p = new Label("%");
                    p.setAlignment(Pos.CENTER);
                    p.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 18));
                    p.setContentDisplay(ContentDisplay.CENTER);
                    p.setPrefWidth(50);
                    p.setPrefHeight(30);

                    HBox dh = new HBox();
                    dh.getChildren().addAll(dl, dp, p);
                    dh.setAlignment(Pos.TOP_CENTER);


                    VBox dv = new VBox();
                    dv.setAlignment(Pos.CENTER);
                    dv.setPrefWidth(350);
                    dv.getChildren().addAll(s, dh);

                    //Buttons
                    Button su = new Button("Submit");
                    adaptor.setSubmit(su);
                    su.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 20));
                    su.setTextFill(Color.WHITE);
                    su.setPrefWidth(150);
                    su.setPrefHeight(25);
                    su.setStyle("-fx-background-color: #2b686d55;");

                    Button ca = new Button("Cancel");
                    adaptor.setCancel(ca);
                    ca.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 20));
                    ca.setTextFill(Color.WHITE);
                    ca.setPrefWidth(150);
                    ca.setPrefHeight(25);
                    ca.setStyle("-fx-background-color: #2b686d55;");

                    HBox bt = new HBox();
                    bt.setStyle("-fx-background-color: #2e6a6f;");
                    bt.setAlignment(Pos.CENTER);
                    bt.setSpacing(50);
                    bt.getChildren().addAll(su, ca);

                    VBox ev = new VBox();
                    ev.getChildren().addAll(elh, tname, rah, dv, bt);
                    ev.setAlignment(Pos.TOP_CENTER);
                    ev.setSpacing(10);
                    Scene ed = new Scene(ev, 500, 380);

                    editTask = new Stage();
                    editTask.initModality(Modality.APPLICATION_MODAL);
                    tname.setText(adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).getTaskName());
                    buttons.getChildren().clear();
                    su.setId("" + adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).getTaskID());
                    editTask.initOwner(primaryStage);
                    editTask.centerOnScreen();
                    editTask.initStyle(StageStyle.TRANSPARENT);
                    editTask.setAlwaysOnTop(true);
                    editTask.setScene(ed);
                    editTask.setResizable(false);
                    adaptor.getMainHBox().setDisable(true);
                    editTask.show();

                    s.valueProperty().addListener(new ChangeListener<Number>() {
                        public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                            dp.setText(""+ Math.round(s.getValue()));
                            adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).setTaskProgress((int)Math.round(s.getValue()));
                        }
                    });
                    su.setOnMouseEntered(event2 -> {
                        su.setEffect(borderGlow);
                    });
                    su.setOnMouseExited(event2 -> {
                        su.setEffect(null);
                    });
                    ca.setOnMouseEntered(event2 -> {
                        ca.setEffect(borderGlow);
                    });
                    ca.setOnMouseExited(event2 -> {
                        ca.setEffect(null);
                    });
                    su.setOnAction(event1 -> {
                        adaptor.getMainHBox().setDisable(false);
                        adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).setTaskReview(ra.getText() + " ");
                        adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).setSubmittedPercentage(Integer.parseInt(dp.getText()));
                        adaptor.getDayToEdit().getTaskByID(adaptor.getTaskToEdit().getTaskID()).setSubmitted(true);
                        System.out.println(Integer.parseInt(dp.getText()) + "<<<<<<<<<<<<<<<>>>>>>>>>>>");
                        //adaptor.addingAllTasks(adaptor.getDayToEdit());
                        editTask.close();
                        adaptor.getEdit().defaultButtonProperty().setValue(true);
                        
                        try {
   						 SavePlan.savePlan(adaptor.getPatient()); 
   						 String urlParameter1 = "patientName=" + adaptor.getPatient().getPatientName();
                  		 String urlParamter2 = "file=" + adaptor.getPatient().getFile();
                  		 String param = urlParameter1 + "&" + urlParamter2;	                    		 
                  		 URL url = new URL(urlFinal + "/setPatientFile/"); 
                  		 HttpURLConnection con = (HttpURLConnection) url.openConnection();
                  		 con.setDoOutput(true);
                  		 con.setDoInput(true);	                    		 
                  		 con.setRequestMethod("POST");
                  		 con.setRequestProperty("User-Agent", "Mozilla/5.0");	                    	
                  		 try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                               wr.writeBytes(param.toString());
                               wr.flush();
                               wr.close();
                           }
                  		 StringBuilder content;

                           try (BufferedReader in = new BufferedReader(
                                   new InputStreamReader(con.getInputStream()))) {

                               String line;
                               content = new StringBuilder();

                               while ((line = in.readLine()) != null) {
                                   content.append(line);
                                   content.append(System.lineSeparator());
                                   
                               }
                              System.out.println(content.toString());
                               con.disconnect();
                             //  primaryStage.close();
                           }

                  		
   					} catch (Exception e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}

                    });
                    ca.setOnAction(event1 -> {
                        adaptor.getMainHBox().setDisable(false);
                        editTask.close();
                    });

                    adaptor.getWantToEdit().defaultButtonProperty().setValue(false);
                }
            }
        });
        Button video = adaptor.getVideoButton();
        video.defaultButtonProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue && !webView.isShowing()){
                    WebView webview = new WebView();
                    ArrayList<String> motivationList = new ArrayList<String>();
                    motivationList.add("https://www.youtube.com/watch?v=mgmVOuLgFB0");
                    motivationList.add("https://www.youtube.com/watch?v=LrhSJ1FHeaA");
                    motivationList.add("https://www.youtube.com/watch?v=26U_seo0a1g");
                    motivationList.add("https://www.youtube.com/watch?v=g-jwWYX7Jlo");
                    motivationList.add("https://www.youtube.com/watch?v=NpjreVYdjJY");
                    motivationList.add("https://www.youtube.com/watch?v=P-N8To7zCwo");
                    motivationList.add("https://www.youtube.com/watch?v=fviFNrWKzZ8");
                    motivationList.add("https://www.youtube.com/watch?v=41Zjh3AirjU");
                    motivationList.add("https://www.youtube.com/watch?v=wzhzkKccBi8");
                    motivationList.add("https://www.youtube.com/watch?v=zQ8itWzHALE");
                    Random r = new Random();
                    webview.getEngine().load(motivationList.get(r.nextInt(10-0) + 0));
                    webview.setPrefSize(640, 390);

                    webView = new Stage();
                    webView.initModality(Modality.APPLICATION_MODAL);
                    webView.initOwner(primaryStage);
                    //webView.centerOnScreen();
                    webView.setScene(new Scene(webview));
                    //adaptor.getMainHBox().setDisable(true);
                    webView.show();
                    video.defaultButtonProperty().setValue(false);
                    webView.setOnCloseRequest(new EventHandler<WindowEvent>(){

                        @Override public void handle(WindowEvent event) {
                            adaptor.getMainHBox().setDisable(false);
                            webview.getEngine().load(null);
                        }

                    });
                }
            }
        });
//        primaryStage.getScene().addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Timer timer = new Timer();
//                timer.scheduleAtFixedRate(new TimerTask() {
//                    @Override
//                    public void run() {
//                        System.out.print("I would be called every 2 seconds");
//                    }
//                }, 0, 2000);
//            }
//        });
        webengine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    //System.out.println(webengine.getDocument().getDocumentElement().getTextContent());
                    switch (pgRequest){
                   
                        case "patientLogin":
                            //System.out.println(webengine.getDocument().getDocumentElement().getTextContent() + "<<<<<<<<<<<<<<<>>>>>>>>>>>");
                        		System.out.println(webengine.getDocument().getDocumentElement().getTextContent());
                                    if (webengine.getDocument().getDocumentElement().getTextContent().length() > 5000) {
                                        //parsing PLAN JSON file
                                        adaptor.getPatient().setPatientName(patientUsername);
                                        adaptor.setAddPatientFile(webengine.getDocument().getDocumentElement().getTextContent());                                   
                                        adaptor.getMainHBox().setDisable(false);
                                        Patient patient = new Patient(patientUsername, adaptor.getAddPatientFile());
                                    
                                        patient.setPlans(LoadPlan.parse(patient, adaptor.getAddPatientFile()));
                                        System.out.println("RELOAD APP, LOADING PLAN");
                                        //System.out.println(patient.getPlans().get(0)+"...................patient plan");
                                       // System.out.println(LoadPlan.parse(patient,adaptor.getAddPatientFile())+" $$$$$$$$4");
                                        System.out.println(patient.plans.size() + "		$$$$$");
                                        if(patient.plans.size() != 0){
                                            //Patient patient = LoadPlan.parse(webengine.getDocument().getDocumentElement().getTextContent());
                                            
                                        	adaptor.setPatient(patient);
                                            adaptor.setWeekPlan(patient.getPlans().get(0));
                                            adaptor.getLoggedIn().defaultButtonProperty().setValue(true);
         
                                            adaptor.getLoggedIn().defaultButtonProperty().setValue(true);
                                            //Logged in
                                            primaryStage.show();
                                            System.out.println("primaryStage.show()");
                                        }
                                        else{
                                            errorText.setText("No Tasks were assigned to you today,\n come back tomorrow");
                                            errorMessage.setScene(errorScene);
                                            errorMessage.show();
                                            errorOkButton.setOnMouseClicked(event2 -> {
                                                errorMessage.close();
                                            
                                            });
                                        }

                                   } else {
                                        errorText.setText("No Tasks were assigned to your WeekPlan yet,\n come back tomorrow.");
                                        errorMessage.setScene(errorScene);
                                        errorMessage.show();
                                        errorOkButton.setOnMouseClicked(event2 -> {
                                            errorMessage.close();
                                        });
                                    }
                                
                            
                            break;
                        case "":
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        webView.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override public void handle(WindowEvent event) {
            	
                web.getEngine().load(null);
            }
        });

    }
}
