package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/////////////////////////////////////////////////////////////////////////
//////////Coded and written by Abir Gamal on March 2020//////////////////
/////////////////////////////////////////////////////////////////////////

public class ReportFeed
{       
	
	private final static Integer startTime = 60;
	private static Integer seconds  = startTime; //integer for object seconds not int as number
	private static Label countDownLabel;
	 static Stage windows;
	//the function is found in the controller class with its buttons referenced in the fxml file 
	 public static void display(String title) {
		 
		 Group root = new Group();
		 
		 //Message displayed in the report window
         Label taskLabel = new Label("Are you working on your task?");
         taskLabel.setTextAlignment(TextAlignment.CENTER);
         taskLabel.setAlignment(Pos.BASELINE_LEFT);
         taskLabel.setTextFill(Color.RED);
         taskLabel.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 14));
         taskLabel.setContentDisplay(ContentDisplay.CENTER);
         taskLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
       //  taskLabel.setPrefHeight(60);
         
       //the button submit function that sends to the server in the controller and main class 
			Button submit = new Button("Submit");
	        submit.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 11));
	        submit.setTextFill(Color.BLACK);
	        submit.setPrefWidth(150);
	        submit.setPrefHeight(25);
	        submit.setStyle("-fx-background-color: #2b686d55;");
			
			ChoiceBox<String> choiceBox = new ChoiceBox<>();
			choiceBox.setPrefWidth(150);
			choiceBox.setPrefHeight(25);
			choiceBox.setStyle("-fx-background-color: #2b686d55;");
			choiceBox.getItems().addAll("Yes", "No");
	        //Set a default value
	        choiceBox.setValue("Yes");
	        submit.setOnAction(e -> getChoice(choiceBox));

	        
			
			countDownLabel = new Label();
			//countDownLabel.setTextFill(Color.RED);
			//countDownLabel.setFont(Font.font(20));
			HBox layout = new HBox(10);
			layout.setLayoutX(20);
			layout.getChildren().addAll(countDownLabel,taskLabel,choiceBox,submit); //addAll since it is more than one object
			root.getChildren().add(layout);
			doTime();
   
		 Stage windows = new Stage();
		 windows.initModality(Modality.APPLICATION_MODAL);
		 windows.setScene(new Scene(root , 750 , 70 , Color.WHITE));
	//	 windows.setTitle("A Reminder Alert to your on your task with countdown");
		 windows.show();
	
	 }
		public static void getChoice(ChoiceBox<String> choiceBox) {
		       String choice = choiceBox.getValue();
		      /* if(choiceBox.getValue() == "Yes"){

		       }*/
		        System.out.println(choice);

		//	return null;
		}

		
	//A real time system based on the computer timer extended from a library with a countdown
		private static void doTime() {
			Timeline time = new Timeline();
			time.setCycleCount(Timeline.INDEFINITE);
			if(time != null){
				time.stop(); //to start from intial time no points in the middle
			}
			KeyFrame frame = new KeyFrame(javafx.util.Duration.seconds(1) , new EventHandler<ActionEvent>(){
	       //keyFrame cause in every step in my timeline , there will be a key preforming smth so for every one second a key will do smth 
				@Override
				public void handle(ActionEvent event) {
					
					seconds --;
					countDownLabel.setText("Count Down : "+ seconds.toString() + ">>");
					countDownLabel.setTextAlignment(TextAlignment.CENTER);
					countDownLabel.setAlignment(Pos.TOP_LEFT);
					countDownLabel.setTextFill(Color.BLACK);
					countDownLabel.setFont(new javafx.scene.text.Font("Copperplate Gothic Bold", 16));
					countDownLabel.setContentDisplay(ContentDisplay.CENTER);
					countDownLabel.setPrefWidth(Control.USE_COMPUTED_SIZE);
					countDownLabel.setPrefHeight(60);

					//to make countdown stops when it reaches 0 and it won't send to the server once the countdown reaches 0
			       if (seconds <= 0){
			    	   time.stop();
			    	   Alert alert = new Alert(Alert.AlertType.INFORMATION); 
			    	   alert.setHeaderText("Count Down reset to 0! you can no longer fill the Feedback task form");
			    	   alert.show();
			       }

				}
				
			});
			time.getKeyFrames().add(frame);
			time.playFromStart();

			
		}

	
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	

}
         }
