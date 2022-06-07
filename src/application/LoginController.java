package application;
import application.Administrator.AddCourse;
import application.Administrator.AdminHome;
import javafx.event.Event;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController {
    
    public void login(Event event,String user,String pwd,String role,Stage stage) throws SQLException{
    	
    	System.out.println("User Id :::: Login Controller - "+user);
    	System.out.println("Password :::: Login Controller - "+pwd);
    	System.out.println("Role :::: Login Controller - "+role);
    	String regex = "^(.+)@(.+)$";
 	    String pwdRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
 	    Pattern pattern = Pattern.compile(regex);
 	    if("null".equals(role) && role == null){
 	    	System.out.println("NULL Value");
 	    }
    	if(user.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please enter your email id");
            return;
        }
        if(pwd.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please enter a password");
            return;
        }
        Matcher matcher = pattern.matcher(user);  
		if(matcher.matches()==false){
			showAlert(Alert.AlertType.ERROR, null, "Form Error!", "Please enter valid Email Id");
	        return;
		}	
    	JdbcDao jdbcDao = new JdbcDao();
    	boolean flag = jdbcDao.validate(user, pwd, role);
    	try {
    		if(!flag) {
        		infoBox("Please enter correct Email and Password", null, "Failed");
        	}else {
        		infoBox("Login Successful!", null, "Success");
        		if("Student".equals(role)){
        			stage.close();
        			Student student = new Student();
        			student.callingStaff(stage,user);
        		} else {
        			stage.close();
        			AdminHome adminHome = new AdminHome();
        			adminHome.start(new Stage());
        		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
