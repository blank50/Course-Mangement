package application;
import java.util.List;
import application.Admin1;

import javafx.application.Application;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Admin extends Application {
	@Override 
	   public void start(Stage stage) {
		      
		// MenuBar menubar = new MenuBar();
		// Menu file = new Menu("File");
        // Menu edit = new Menu("Edit");
        // Menu format = new Menu("Format");
        // Menu view = new Menu("View");
        // Menu help = new Menu("Help");
		// MenuItem New = new MenuItem("New");
        // MenuItem Open = new MenuItem("Open");
        // MenuItem Save = new MenuItem("Save");
        // MenuItem Save_as = new MenuItem("Save as");
		// file.getItems().addAll(New,Open,Save,Save_as);
        // menubar.getMenus().addAll(file,edit,format,view,help);

		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(10, 10, 10, 10));
		Text text = new Text("Welcome Admin");
	    text.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 28));	  
		// Button leftFlow = new Button("left");
		// Button centerFlow = new Button("center");
		// flow.setStyle("-fx-background-color: DAE6F3;");
		// flow.setHgap(5);
		// flow.getChildren().addAll(leftFlow, centerFlow);
		flow.getChildren().addAll(text);

		// AnchorPane anchorPane = new AnchorPane();
		// Button buttonSave = new Button("Save");
		// Button buttonCancel = new Button("Cancel");
		// Button add = new Button("View/Update Student's");
	    
		// HBox hb = new HBox();
		// hb.getChildren().addAll(add);
		// anchorPane.getChildren().addAll(hb);

		// anchorPane.setMinSize(200,200);
		// AnchorPane.setRightAnchor(hb, 10.0);

		TilePane tile = new TilePane();
		tile.setPadding(new Insets(10, 10, 10, 10));
		Button logoutBtn = new Button("Logout");
		tile.setPrefColumns(2);
		HBox hbox2 = new HBox(8); // spacing = 8
		hbox2.getChildren().addAll(logoutBtn);
		tile.getChildren().add(hbox2);
		

		
	    //   ImageView imageView = new ImageView(new Image(getClass().getResource("student.jpg").toExternalForm()));
	    //   bp.getChildren().addAll(imageView);
	        
	    //   HBox hb = new HBox();
	    // //   hb.setPadding(new Insets(80,0,80,60));
	    // StackPane root = new StackPane();
		// Button btn1 = new Button(" 1 ");
		// Button btn2 = new Button("22222222");
		// ImageView imageView = new ImageView(new Image(getClass().getResource("logo.jpg").toExternalForm()));
		// root.getChildren().addAll(imageView);
	      
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

		VBox vbox = new VBox(5);

		RadioButton btn = new RadioButton("1");
		RadioButton btn2 = new RadioButton("2");
		RadioButton btn3 = new RadioButton("Update Student's");
	
		ToggleGroup group = new ToggleGroup();
		btn.setToggleGroup(group);
		btn2.setToggleGroup(group);
		btn3.setToggleGroup(group);
	
		btn.getStyleClass().remove("radio-button");
		btn.getStyleClass().add("toggle-button");        
	
	
		btn2.getStyleClass().remove("radio-button");
		btn2.getStyleClass().add("toggle-button");
		
		btn3.getStyleClass().remove("radio-button");
		btn3.getStyleClass().add("toggle-button");
	
		final Pane cardsPane = new StackPane();
		
		final Group card1 = new Group(new Text(25, 25, "Card 1"));
		final Group card2 = new Group(new Text(25, 25, "Card 2"));
		final Group card3 = new Group();
		// final Button add = new Button("Update Student's");
	
		cardsPane.getChildren().addAll(card1, card2, card3);
		btn.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent t)
			{
				showNodeHideNodes(cardsPane.getChildren(), card1);
			}
		});
	
		btn2.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent t)
			{
				showNodeHideNodes(cardsPane.getChildren(), card2);
			}
		});
	
		vbox.getChildren().addAll(btn, btn2, cardsPane);
		stage.setScene(new Scene(vbox));

		BorderPane bp = new BorderPane();
	    bp.setId("areg");
	    //gridPane.setId("register");

	        
	    // Button logoutBtn = new Button();
	    // logoutBtn.setText("Logout");
	      
	    // VBox vbox = new VBox();
	    // vbox.setPadding(new Insets(0,0,0,10));
	    // vbox.setSpacing(20);
	    // hb.getChildren().addAll(text);
	    // vbox.getChildren().addAll(logoutBtn);
	    // bp.setRight(anchorPane);
		bp.setCenter(vbox);
	    bp.setTop(flow);
	    bp.setLeft(gridPane);
		bp.setBottom(tile);
	    Scene scene = new Scene(bp);
	    scene.getStylesheets().add("register.css");
	    stage.setScene(scene);
		stage.getIcons().add(new Image("E:/wzuLogo.jpg"));
	    stage.setTitle("Welcome Admin - Course Management System");
		stage.setMaximized(true);
	    // stage.setResizable(true);
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
	      
	    //   add.setOnAction(new EventHandler() {
		// 		@Override
		// 		public void handle(Event event) {
		// 			Admin1 admin = new Admin1();
		// 			admin.start(new Stage());
		// 		}
	    //   });
		//   btn2.setOnAction(new EventHandler<ActionEvent>()
		// {
		// 	public void handle(ActionEvent t)
		// 	{
		// 		showNodeHideNodes(cardsPane.getChildren(), add);
		// 	}
		// });
		  
	   } 
	   
	   public void callingHome(Stage stage){
		   stage.close();
		   Login home = new Login();
		   home.start(new Stage());
	   }
	   private static void showNodeHideNodes(List<Node> nodes, Node nodeToShow)
{
    for (Node node : nodes)
    {
        if (node.equals(nodeToShow))
        {
            node.setVisible(true);
        } else
        {
            node.setVisible(false);
        }
    }

}

	   public static void main(String args[]){ 
	      launch(args); 
	   } 
	   
	}
	