package application.Administrator;

import application.JdbcDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewStaff extends Application{
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

		// GridPane gridPane = new GridPane();    	      
	    // gridPane.setMinSize(300, 300); 	           
	    // gridPane.setPadding(new Insets(10, 10, 10, 10));  
	    // gridPane.setVgap(5); 
	    // gridPane.setHgap(5);
		// Label studentNameLabel = new Label();
		// TextField studentName = new TextField();
	  	// studentName.setPromptText("Teacher ID");	   
      	// TextField nameText = new TextField(); 
      	// nameText.setPromptText("Teacher Name");
      	// TextField email = new TextField();
      	// email.setPromptText("Email-Id");      
      	// final PasswordField pwd = new PasswordField();
      	// pwd.setPromptText("Password");
      	// final PasswordField confirmPwd = new PasswordField();
      	// confirmPwd.setPromptText("Confirm Password");
      	// DatePicker datePicker = new DatePicker(); 
      	// datePicker.setPromptText("Date Of Birth");
      	// Text role = new Text("Role");
	    //   gridPane.setAlignment(Pos.BASELINE_LEFT);
		// TextField addCourseMaster = new TextField();
		// addCourseMaster.setPromptText("Enter New Course Name");  
		// gridPane.add(addCourseMaster, 0, 0);
		// Button addCourse = new Button("Add Course");  
	    // gridPane.add(addCourse, 1, 0); 
		// ComboBox comboBox = new ComboBox();
	    // comboBox.setItems(FXCollections.observableArrayList(JdbcDao.getCourseData()));
	    // comboBox.setPromptText("----View-Course----");
		// gridPane.add(comboBox, 0, 1); 
		// Button submit = new Button("Submit"); 
	    // gridPane.add(submit, 0, 4);
		HBox hb = new HBox();       
      hb.setPadding(new Insets(0,0,80,150));      
	  TextField studentName = new TextField();
	  studentName.setPromptText("User ID");	   
      TextField nameText = new TextField(); 
      nameText.setPromptText("User Name");
      TextField email = new TextField();
      email.setPromptText("Email-Id");      
      final PasswordField pwd = new PasswordField();
      pwd.setPromptText("Password");
      final PasswordField confirmPwd = new PasswordField();
      confirmPwd.setPromptText("Confirm Password");
      DatePicker datePicker = new DatePicker(); 
      datePicker.setPromptText("Date Of Birth");
      Text role = new Text("Role"); 
//      
//      Label labelUser = new Label("User");
//      labelUser.getStyleClass().add("label1");
      ComboBox comboBox = new ComboBox();
      comboBox.getStyleClass().add("comboColor");
      comboBox.getItems().add("Student");
      comboBox.getItems().add("Admin");
      comboBox.setPromptText("--Select-Role--");
      
      Text gender = new Text("Gender"); 
      ComboBox genderBox = new ComboBox();
      genderBox.getStyleClass().add("comboColor");
      genderBox.getItems().add("Male");
      genderBox.getItems().add("Female");
      genderBox.setPromptText("--Choose-Gender--");
       
      Button buttonRegister = new Button("Register");  
      Button clear = new Button("Clear");
    //   Button home = new Button("Back to Login");
      
      GridPane gridPane = new GridPane();    
      gridPane.setMinSize(700, 300);     
      gridPane.setPadding(new Insets(10, 10, 10, 10));
      gridPane.setVgap(6); 
      gridPane.setHgap(6);       
      gridPane.setAlignment(Pos.BASELINE_LEFT);  
      gridPane.add(studentName, 0, 0);
      gridPane.add(nameText, 0, 1);
      gridPane.add(pwd, 0, 2);
      gridPane.add(confirmPwd, 0, 3);    
      gridPane.add(datePicker, 3, 0);
      gridPane.add(email, 0, 4); 
      gridPane.add(role, 0, 5); 
      gridPane.add(comboBox, 1, 5);  
      gridPane.add(gender, 0,6); 
      gridPane.add(genderBox, 1, 6);       
      gridPane.add(buttonRegister, 1, 7);
      gridPane.add(clear, 6, 9);
    //   gridPane.add(home, 3, 7);

		TilePane tile = new TilePane();
		tile.setPadding(new Insets(10, 10, 10, 10));
		Button logoutBtn = new Button("Logout");
		tile.setPrefColumns(2);
		HBox hbox2 = new HBox(8); // spacing = 8
		hbox2.getChildren().addAll(logoutBtn);
		tile.getChildren().add(hbox2);




		BorderPane bp = new BorderPane();
		bp.setTop(flow);
		bp.setLeft(anchorPane);
		bp.setCenter(gridPane);
		bp.setBottom(tile);
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.getIcons().add(new Image("E:/wzuLogo.jpg"));
	    stage.setTitle("Welcome Admin - Course Management System");
		stage.setMaximized(true);
		stage.show();
    }
	public static void main(String args[]){ 
		launch(args); 
	 } 
}
