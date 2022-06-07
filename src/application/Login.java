package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application{
    public void start(Stage primaryStage){
        GridPane gridPane = new GridPane();
        Text text = new Text("Course Management System");
	    Font font = new Font("TimesNewRoman",28);
	    text.setFont(font);
        gridPane.setMinSize(500, 250);
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getChildren().add(text);     
        Label labelUser = new Label("User");
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("Admin");
        comboBox.getItems().add("Student");
        comboBox.setPromptText("--Select-Role--");       
        Label labelEmailId = new Label("Email Address");
        final TextField textEmailId = new TextField();
        textEmailId.setPromptText("Enter Your Email-Id");
        Label labelPassword = new Label("Password");
        final PasswordField passwordfield = new PasswordField();
        passwordfield.setPromptText("Enter your Password");
        Button BLogin = new Button("Login");
        Button BRegister = new Button("Register");
        
        gridPane.add(labelEmailId, 0, 1);
        gridPane.add(textEmailId, 1, 1);
        gridPane.add(labelPassword, 0, 2);
        gridPane.add(passwordfield, 1, 2);
        gridPane.add(labelUser, 2, 0);
        gridPane.add(comboBox, 2, 1);
        gridPane.add(BLogin, 1, 8);
        gridPane.add(BRegister, 2, 8);
        gridPane.setId("LogInSignUp");

        BLogin.setOnAction(event -> {
			try{
				String userId = textEmailId.getText().toString();
		          String password = passwordfield.getText().toString();
		          String role = (String) comboBox.getValue();
		          System.out.println("ROLE :: "+role);
		          LoginController login = new LoginController();
		          login.login(event,userId,password,role,primaryStage);
		          textEmailId.setText("");
		          passwordfield.setText("");
			} catch (Exception e){
				e.printStackTrace();
			}
		});
        
        BRegister.setOnAction(event -> {
			primaryStage.close();
			RegisterController register = new RegisterController();
			register.start(new Stage());
		});

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Or Sign-Up");
        primaryStage.getIcons().add(new Image("E:/wzuLogo.jpg"));
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
