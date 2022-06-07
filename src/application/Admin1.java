package application;

import java.sql.Connection;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
 
public class Admin1 extends Application {
 
	private TableView table;
    private ObservableList<ObservableList> data;
    final HBox hb = new HBox();
    final HBox hb1 = new HBox();
    String name = "";
    //String id = "";
    String oldCourse = "";
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
    	 try {
	    	table = new TableView();
	        Scene scene = new Scene(new Group());
	        scene.getStylesheets().add("Admin.css");
	        stage.setTitle("Student View");
	        stage.setWidth(430);
	        stage.setHeight(550);
	 
	        final Label label = new Label("List of Students Enrolled");
	        label.setFont(new Font("Calibri", 20));
	 
	        Connection c;
	        data = FXCollections.observableArrayList();
	       
	            c = DBConnect.connect();
//	            String SQL = "select b.student_id,a.student_name,a.course from management.student a, management.user b where a.student_name = b.name";
	            String SQL = "select b.student_id,a.student_name,a.course from management.student a, management.user b where a.student_name = b.name";
	            ResultSet rs = c.createStatement().executeQuery(SQL);
	 
	            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
	                final int j = i;
	                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
	                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
	                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
	                        return new SimpleStringProperty(param.getValue().get(j).toString());
	                    }
	                });
	                table.getColumns().addAll(col);
	                System.out.println("Column [" + i + "] ");
	            }
	 
	            while (rs.next()) {
	                ObservableList<String> row = FXCollections.observableArrayList();
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    row.add(rs.getString(i));
	                }
	                System.out.println("Row [1] added " + row);
	                data.add(row);
	            }
	            table.setItems(data);
	            
	            TextField addStudentName = new TextField();
	            addStudentName.setPromptText("Student Name");

		        ComboBox comboBox = new ComboBox();
		        comboBox.setItems(FXCollections.observableArrayList(JdbcDao.getCourseData()));
		        comboBox.getSelectionModel().selectFirst();
		        
		        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
		            if(newSelection != null)
		            {
		            	System.out.println(table.getSelectionModel().getSelectedItems());
        		    	String input = table.getSelectionModel().getSelectedItems().toString();
        		    	String[] parts = input.split(",");
        		    	name = parts[1];
        		    	//id = parts[1];
        		    	oldCourse = parts[2].replace("]]", "");
        		    	addStudentName.setText(name);
        		    	System.out.println(name);
//        		    	addStudentName.setText(id);
//        		    	System.out.println(id);
		            }
		        });
		        
		        final Button updButton = new Button("Update");
		        updButton.setOnAction(new EventHandler<ActionEvent>() {
		        	 @Override
			            public void handle(ActionEvent e) {
		        		    try{
		        		    	System.out.println(comboBox.getValue().toString());
		        		    	System.out.println(name);
		        		    	//System.out.println(id);
		        		    	System.out.println(oldCourse);
				            	JdbcDao dao = new JdbcDao();
				            	boolean flag = dao.updCourseData(comboBox.getValue().toString(),name/*id*/.trim(),oldCourse.trim());
				                if(flag == true){
				                	stage.close();
				            		addStudentName.clear();
					                hb.getChildren().clear();
					                hb1.getChildren().clear();
					                start(stage);
					                
					                showAlert(Alert.AlertType.INFORMATION, null, "Form Error!", "Records Updated Successfully!");
				            	} else {
				            		showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Records  Not Successfull!");
				            		return;
				            	}
			            	} catch (Exception exp) {
			            		exp.printStackTrace();
			            	}
		        	 }
		        });
	            
	            final Button close = new Button("Close");
	            close.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent e) {
		            	try {
			            	stage.close();
		            	} catch (Exception exp) {
		            		exp.printStackTrace();
		            	}
		            }
	            });
	            
	        hb.getChildren().addAll(addStudentName, comboBox,updButton);
	        hb.setSpacing(4);
	        hb1.getChildren().addAll(close);	        
	        final VBox vbox = new VBox();
	        vbox.setSpacing(2);
	        vbox.setPadding(new Insets(10, 10, 10, 10));
	        vbox.getChildren().addAll(label, table, hb,hb1);
	        vbox.setId("admin");
	        //hb.setId("root");
	        hb1.setId("admin");
	 
	        ((Group) scene.getRoot()).getChildren().addAll(vbox);
	 
	        stage.setScene(scene);
	        stage.show();
    	 } catch (Exception e) {
             e.printStackTrace();
             System.out.println("Error on Building Data");
         }   
    }
    
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    
} 