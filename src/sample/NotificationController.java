package sample;
	
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/////////////////////////////////////////////////////////////////////////
//////////Coded and written by Abir Gamal on March 2020//////////////////
/////////////////////////////////////////////////////////////////////////



public class NotificationController extends Application implements Initializable {
	@Override
	public void start(Stage primaryStage) {

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//the following extends a JavaFx library the implements a certain notification layout
	//and displays the following messages when clicking on the buttons in the Main class , 
	//the notifications can be randomized and regenerating in a function in the main class.
	
	static void handleButtonAction(ActionEvent event) {
		Notifications notificationBuilder = Notifications.create()
				.title("Are you working on your task?")
				.text("If not then get back to it, if yes, keep up the good work!")
				.graphic(null)
				.hideAfter(Duration.seconds(15))
                .position(Pos.BOTTOM_RIGHT);
		Notifications notificationsecond = Notifications.create()
				.title("Here's your motivational quote for today")
				.text("Productivity is never an accident. It is always the result of a commitment to excellence, intelligent planning, and focused effort.")
				.graphic(null)
				.hideAfter(Duration.seconds(30))
                .position(Pos.BOTTOM_RIGHT);
		
		
		/* .onAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						System.out.println("Clicked on the notification");
						
					}
                	
                });*/
		notificationBuilder.darkStyle();
		notificationBuilder.showConfirm(); //showconfirm cause it is a confrimation message
        
		notificationsecond.darkStyle();
		notificationsecond.showConfirm(); 
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

}
