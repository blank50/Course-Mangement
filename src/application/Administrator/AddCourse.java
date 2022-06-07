package application.Administrator;

import application.Admin1;
import application.JdbcDao;
import application.Login;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class AddCourse extends Application{
	@Override
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
		// Button addCourse = new Button("Add New Course");
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

		GridPane gridPane = new GridPane();    	      
	    gridPane.setMinSize(300, 300); 	           
	    gridPane.setPadding(new Insets(10, 10, 10, 10));  
	    gridPane.setVgap(5); 
	    gridPane.setHgap(5);       
	    //   gridPane.setAlignment(Pos.BASELINE_LEFT);
		TextField addCourseMaster = new TextField();
		addCourseMaster.setPromptText("Enter New Course Name");  
		gridPane.add(addCourseMaster, 0, 0);
		Button addCourse = new Button("Add Course");  
	    gridPane.add(addCourse, 1, 0); 
		ComboBox comboBox = new ComboBox();
	    comboBox.setItems(FXCollections.observableArrayList(JdbcDao.getCourseData()));
	    comboBox.setPromptText("----View-Course----");
		gridPane.add(comboBox, 0, 1); 
		Button submit = new Button("Submit"); 
	    gridPane.add(submit, 0, 4);

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

		logoutBtn.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				callingHome(stage);
			}
	  });

	  addCourse.setOnAction(new EventHandler() {
		@Override
		public void handle(Event event) {
			try{
				if(addCourseMaster.getText().trim().length()>0){
					JdbcDao dao = new JdbcDao();
					boolean flag = dao.postCourseMasterData(addCourseMaster.getText().trim());
					if(flag){
						Admin1.showAlert(Alert.AlertType.INFORMATION, null, "Form Error!", "Records Added to Course Master Successfully !!");
					}
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
  });
  submit.setOnAction(new EventHandler() {
	@Override
	public void handle(Event event) {
		try{
			JdbcDao dao = new JdbcDao();
			boolean flag = dao.postCourseData(comboBox.getValue().toString());
			if(flag){
				Admin1.showAlert(Alert.AlertType.INFORMATION, null, "Form Error!", "Records Added Successfully !!");
			}
		} catch(Exception nex) {
			nex.printStackTrace();
		}
	}
});

	  
    }
	public void callingHome(Stage stage) {
		stage.close();
		   Login home = new Login();
		   home.start(new Stage());
	}
	public static void main(String args[]){ 
		launch(args); 
	 } 
	 
    
}
