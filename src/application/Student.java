package application;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Student extends Application {
	
	private StackPane root = new StackPane();
	
	String name = "";
	String student_id = "";
	HashMap<String, String> inputs = null;
	JdbcDao jdbcDao = null;
	String emailId = "";
	
	public void callingStaff(Stage stage,String user){
		jdbcDao = new JdbcDao();
		HashMap<String, String> inputs = null;
		try {
			inputs = new HashMap<String, String>();
			inputs = jdbcDao.getUserName(user, "Student");
			name = inputs.get("NAME");
			student_id = inputs.get("STUD_ID");
			emailId = user;
			start(new Stage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override 
	   public void start(Stage stage) {   
		
		 BorderPane bp = new BorderPane();
	      bp.setPadding(new Insets(40,80,80,40));
	        
	      HBox hb = new HBox();
	      hb.setPadding(new Insets(0,0,20,20));

	      Label lblcourse = new Label("Course");
	      ComboBox comboBox = new ComboBox();
	      comboBox.setItems(FXCollections.observableArrayList(JdbcDao.getData()));
	      comboBox.setPromptText("--Select-Course--");
	        
		  Button submit = new Button("Submit"); 
	      Button close = new Button("Close");
	      GridPane gridPane = new GridPane();
	      gridPane.setPadding(new Insets(10, 10, 10, 10));  
	      gridPane.setVgap(5); 
	      gridPane.setHgap(5);       
	      gridPane.setAlignment(Pos.BASELINE_LEFT);
	      gridPane.add(lblcourse, 0, 2); 
	      gridPane.add(comboBox, 1, 2); 
	      gridPane.add(submit, 2, 3); 
	      bp.setId("bp");
	      gridPane.setId("root");
	      Text text = new Text("Welcome to Course Management System - "+name);
	      text.setFont(Font.font("Calibri", FontWeight.BOLD, 28));
	      Button logoutBtn = new Button();
	      logoutBtn.setText("Logout");
	      
	      VBox vbox = new VBox();
	      vbox.setPadding(new Insets(100,0,0,20));
	      vbox.setSpacing(40);
	      hb.getChildren().addAll(text);
	      vbox.getChildren().addAll(logoutBtn); 
	      bp.setRight(vbox);
	      bp.setTop(hb);
	      bp.setCenter(gridPane);
	      bp.setId("student");
	      gridPane.setId("stud");
	      
	      Scene scene = new Scene(bp);
	      scene.getStylesheets().add("Student.css");
	      stage.setScene(scene);
	      stage.setTitle("Student Profile - Course Management System"); 
	      stage.setResizable(true);
	      stage.show();
	      
	      logoutBtn.setOnAction(new EventHandler() {
				@Override
				public void handle(Event event) {
					callingHome(stage);
				}
	      });
	      
	      submit.setOnAction(new EventHandler() {
				@Override
				public void handle(Event event) {
					try{
						HashMap<String, String> registerFormValues = new HashMap<String, String>();
						registerFormValues.put("student_name", name);
						registerFormValues.put("student_id", student_id);
						registerFormValues.put("course", comboBox.getValue().toString());
						JdbcDao dao = new JdbcDao();
						boolean flag = dao.postStudentData(registerFormValues);
						if(flag){
							Admin1.showAlert(Alert.AlertType.INFORMATION, null, "Form Error!", "Enrolled Successfully !!");
						}
					} catch(Exception e){
						e.printStackTrace();
					}
					
				}
		  });
	   } 
	   
	   public void callingHome(Stage stage){
		   stage.close();
		   Login home = new Login();
		   home.start(new Stage());
	   }
	   
	   public static void main(String args[]){ 
	      launch(args); 
	   } 
	   
	}