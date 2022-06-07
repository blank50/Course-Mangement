
package application.Administrator;

import application.Admin1;
import application.Login;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AdminHome extends Application{
    public void start(Stage stage){
        FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(10, 10, 10, 10));
		Text text = new Text("Welcome Admin");
	    text.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 28));	  
		flow.getChildren().add(text);


        AnchorPane anchorPane = new AnchorPane();
		// anchorPane.setPadding(new Insets(10,10,10,10));
		Button home = new Button("Home");
		Button createStaff= new Button("New Staff");
		Button deleteStaff = new Button("Delete Staff");
		Button deleteStudent = new Button("Delete Student");
		Button add = new Button("Add New Course");
		Button deleteCourse = new Button("Delete Course");
		Button showStudent = new Button("Show Student Accounts");
		Button showStaff = new Button("Show Staff Accounts");
		 
		VBox vb = new VBox();
		vb.setPadding(new Insets(10, 50, 50, 50));
    	vb.setSpacing(10);
		// vb.setPadding(new Insets(10,10,10,10));
		vb.getChildren().addAll(home,createStaff,deleteStaff,deleteStudent,add,deleteCourse,showStudent,showStaff);
		anchorPane.getChildren().addAll(vb);
		anchorPane.setMinSize(200,200);
		AnchorPane.setTopAnchor(vb, 10.0);
		AnchorPane.setLeftAnchor(vb, 10.0);
		AnchorPane.setBottomAnchor(vb, 10.0);
		AnchorPane.setRightAnchor(vb, 10.0);

		

		TilePane tile = new TilePane();
		tile.setPadding(new Insets(10, 10, 10, 10));
		Button logoutBtn = new Button("Logout");
		tile.setPrefColumns(2);
		HBox hbox2 = new HBox(8); // spacing = 8
		hbox2.getChildren().addAll(logoutBtn);
		tile.getChildren().add(hbox2);


		BorderPane bp = new BorderPane();
        //ImageView imageView = new ImageView(new Image(getClass().getResource("logo.jpg").toExternalForm()));
        
        ImageView imageView = new ImageView(new Image(getClass().getResource("student.jpg").toExternalForm()));
        // bp.getChildren().addAll(imageView);
		bp.setTop(flow);
		bp.setLeft(anchorPane);
        bp.setCenter(imageView);
		bp.setBottom(tile);
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.getIcons().add(new Image("E:/wzuLogo.jpg"));
	    stage.setTitle("Welcome Admin - Course Management System");
		stage.setMaximized(true);
		stage.show();


		logoutBtn.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				callingHome(stage);
			}
		});

		createStaff.setOnAction(new EventHandler(){
			public void handle(Event event){
				NewStaff newStaff = new NewStaff();
					newStaff.start(new Stage());
				}
		});
		deleteStaff.setOnAction(new EventHandler(){
			public void handle(Event event){
				DeleteStaff delStaff = new DeleteStaff();
					delStaff.start(new Stage());
				}
		});
		deleteStudent.setOnAction(new EventHandler(){
			public void handle(Event event){
				DeleteStudent delStudent = new DeleteStudent();
					delStudent.start(new Stage());
				}
		});
		// add.setOnAction(new EventHandler(){
		// 	public void handle(Event event){
		// 		AddCourse addCourse = new AddCourse();
		// 		addCourse.start(new Stage());
		// 	}
		// });
		add.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				Admin1 admin = new Admin1();
				admin.start(new Stage());
			}
	  });
		deleteCourse.setOnAction(new EventHandler(){
			public void handle(Event event){
			DeleteCourse delCourse = new DeleteCourse();
				delCourse.start(new Stage());
			}
		});
		showStudent.setOnAction(new EventHandler(){
			public void handle(Event event){
				ShowStaffAccounts staffAcc = new ShowStaffAccounts();
				staffAcc.start(new Stage());
			}
		});
		showStaff.setOnAction(new EventHandler(){
			public void handle(Event event){
				ShowStaffAccounts staffAcc = new ShowStaffAccounts();
				staffAcc.start(new Stage());
			}
		});

}
	
	public void callingHome(Stage stage) {
		stage.close();
		   Login home = new Login();
		   home.start(new Stage());
	}
    public static void main(String[] args){
        launch(args);
    }
        
}