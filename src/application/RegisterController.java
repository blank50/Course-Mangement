package application;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application; 
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window; 
         
public class RegisterController extends Application { 
   @Override 
   public void start(Stage stage) {   	  
	  BorderPane bp = new BorderPane();
      bp.setPadding(new Insets(10,10,10,10));     
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
      Button home = new Button("Back to Login");
      
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
      gridPane.add(home, 3, 7);
      bp.setId("areg");
      gridPane.setId("register");
      
      Text text = new Text("Register As A New User");
      text.setFont(Font.font("Calibri", FontWeight.BOLD, 32));
      hb.getChildren().addAll(text);
      
      bp.setTop(hb);
      bp.setCenter(gridPane);
      
      Scene scene = new Scene(bp); 
      scene.getStylesheets().add("register.css");
      stage.setScene(scene);
      stage.setTitle("New User - Course Management System"); 
   
      stage.setResizable(true);
      stage.show();
   
      
      buttonRegister.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				try {
					JdbcDao jdbcDao = new JdbcDao();
					HashMap<String, String> registerFormValues = new HashMap<String, String>();
					registerFormValues.put("ID", studentName.getText());
					registerFormValues.put("NAME", nameText.getText());
					registerFormValues.put("PWD", pwd.getText());
					registerFormValues.put("CNFPWD", confirmPwd.getText());
					registerFormValues.put("DATE", datePicker.getValue().toString());
					registerFormValues.put("ROLE", (String) comboBox.getValue());
					registerFormValues.put("GEN", (String) genderBox.getValue());
					registerFormValues.put("EMAIL", email.getText());
					
					boolean studIdFlag = jdbcDao.validRegData(studentName.getText());
					System.out.println("studIdFlag :: "+studIdFlag);
					if(studIdFlag){
						showAlert(Alert.AlertType.ERROR, null, "Form Error!", "User Id already Registered with us");
						return;
					}
					boolean emailIdFlag = jdbcDao.validRegDataId(email.getText());
					System.out.println("emailIdFlag :: "+emailIdFlag);
					if(emailIdFlag){
						showAlert(Alert.AlertType.ERROR, null, "Form Error!", "E-Mail Id is already Registered with us");
						return;
					}
					
					boolean valid = validationInput(registerFormValues);
					if(valid){
						boolean flag = jdbcDao.postData(registerFormValues);
						System.out.println("Flag :: "+flag);
						showAlert(Alert.AlertType.INFORMATION, null, "Form Error!", "Your Account is Registered");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
      });
      
      clear.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				stage.close();
				RegisterController register = new RegisterController();
				register.start(new Stage());
			}
      });
      
      home.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				callingHome(stage,"Home");
			}
      });
   }
   public void callingHome(Stage stage,String action){
	   stage.close();
	   Login home = new Login();
	   home.start(new Stage());
	   showAlert(Alert.AlertType.ERROR, null, "Back!", "You'll be redirected to Login Screen !!!");
   }
   
   public static void main(String args[]){ 
      launch(args); 
   }
   
   private boolean validationInput(HashMap<String, String> registerFormValues) {
	   String regex = "^(.+)@(.+)$";
	   String pwdRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
	   Pattern pattern = Pattern.compile(regex);	   
	   try {
		   if(registerFormValues.get("ID").isEmpty()){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please enter your Id");
	            return false;
		   }
		   if(registerFormValues.get("DATE").isEmpty()){
			   showAlert(Alert.AlertType.ERROR,null, "Form Error!", "Please Choose your Date of Birth");
	            return false;
		   }
		   if(registerFormValues.get("NAME").isEmpty()){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please enter your Name");
			   return false;
		   }
		   if(registerFormValues.get("EMAIL").isEmpty()){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please enter your Email-Id");
			   return false;
		   }
		   Matcher matcher = pattern.matcher(registerFormValues.get("EMAIL"));  
		   if(matcher.matches()==false){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please enter valid Email-Id");
			   return false;
		   }	
		   boolean validPassword = isValidPassword(registerFormValues.get("PWD"),pwdRegex);
		   if(registerFormValues.get("PWD").isEmpty()){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please Enter your password");
			   return false;
		   }
		   if(!validPassword){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Password should be atleast 8 characters, a caps letter, Number and a Special Character");
			   return false;
		   }
		   boolean validCnfPassword = isValidPassword(registerFormValues.get("CNFPWD"),pwdRegex);
		   if(registerFormValues.get("CNFPWD").isEmpty()){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please fill in the confirm password.");
			   return false;
		   }
		   if(!validCnfPassword){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Password should be atleast 8 characters, a caps letter, Number and a Special Character");
			   return false;
		   }
		   if(!registerFormValues.get("PWD").equals(registerFormValues.get("CNFPWD"))){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Password and Confirm Password Does not Match");
			   return false;
		   }
		   if(registerFormValues.get("ROLE").isEmpty()){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please select your Role");
			   return false;
		   }
		   if(registerFormValues.get("GEN").isEmpty()){
			   showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please select your Gender");
			   return false;
		   }
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return true;
	}
   
   public static boolean isValidPassword(String password,String regex)
   {
       Pattern pattern = Pattern.compile(regex);
       Matcher matcher = pattern.matcher(password);
       return matcher.matches();
   }
private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(message);
       alert.initOwner(owner);
       alert.show();
   }
  
}