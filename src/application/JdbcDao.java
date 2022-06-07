package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JdbcDao {
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/management";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "Paradox123@";
	//Login
	private static final String SELECT_QUERY = "SELECT * FROM management.user WHERE email_id = ? and password = ? and role = ?";
	
	private static final String SQL_INSERT = "insert into management.user values(?,?,?,?,?,?,?,?)";
	//Dashboard student
	private static final String SELECT_QRY1 = "SELECT name,password,student_id FROM management.user WHERE email_id = ? and role = ?";
	
	private static final String DATA_QRY = "SELECT distinct course FROM management.course_name";

	private static final String SHOW_STUDENT_DATA = "SELECT distinct student_name FROM management.student";
	
	private static final String SQL_STUDENT_INSERT = "insert into management.student values(?,?,?,?)";
	
	private static final String SQL_STUDENT_DELETE = "DELETE FROM management.student where student_id = ?";

	private static final String SQL_USER_DELETE = "DELETE FROM management.user where student_id = ?";
	//admin add course to the list
	private static final String SQL_COURSE_INSERT = "insert into management.course_name values(?,?)";
	
	private static final String DATA_COURSE_QRY = "SELECT distinct course FROM management.majorinfo";
	//admin add to database
	private static final String SQL_COURSE_INSERT1 = "insert into management.majorinfo values(?,?)";
	
	private static final String SQL_COURSE_UPDATE = "UPDATE management.student SET course=? where  student_name = ? and course=? ";
	//private static final String SQL_COURSE_UPDATE = "UPDATE management.student SET course=? where  student_id = ? and course=? ";
	
	private static final String SQL_VALIDREG_ID = "SELECT count(*) id FROM management.user WHERE student_id = ?";
	
	private static final String SQL_VALIDREG_EMAILID = "SELECT count(*) id FROM management.user WHERE email_id = ?";
	
	private static final String SQL_COURSE = "select course from management.student a, management.user b where a.student_name = b.name and b.student_id = ?";
	//Login
	public boolean validate(String emailId, String password, String role) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            System.out.println(preparedStatement);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }           	
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }
	//Register
	public boolean postData(HashMap<String, String> registerFormValues) throws SQLException {
		 try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
		    PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
			stmt.setInt(1,0);  
			stmt.setString(2,registerFormValues.get("EMAIL"));  
			stmt.setString(3,registerFormValues.get("PWD"));  
			stmt.setString(4,registerFormValues.get("DATE"));  
			stmt.setString(5,registerFormValues.get("ID"));  
			stmt.setString(6,registerFormValues.get("NAME"));  
			stmt.setString(7,registerFormValues.get("GEN"));  
			stmt.setString(8,registerFormValues.get("ROLE"));  
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			if(i==1){
				return true;
			}
			connection.close();  
		}catch(Exception e){
			System.out.println(e);
		}  
	   return false;
	}
	//dashboard student
	public HashMap<String, String> getUserName(String emailId, String role) throws SQLException {
		HashMap<String, String> inputs = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QRY1)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, role);
            System.out.println(preparedStatement);         
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                inputs = new HashMap<String, String>();
            	inputs.put("NAME", resultSet.getString("name"));
            	inputs.put("PWD", resultSet.getString("password"));
            	inputs.put("STUD_ID", resultSet.getString("student_id"));
            }           	
        } catch (SQLException e) {
            printSQLException(e);
            inputs.put("NAME", "");
        	inputs.put("PWD", "");
        }
        return inputs;
    }
	
	public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
	
	static List<String> getData() {
		List<String> options = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			       PreparedStatement preparedStatement = connection.prepareStatement(DATA_QRY)) {
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                options.add(set.getString("course"));
            }
            preparedStatement.close();
            set.close();
			return options;
		} catch (SQLException e) {
		    printSQLException(e);
		    return null;
		}
	}
	
	//get the course list from admin
	public static List<String> getCourseData() {
		List<String> options = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			       PreparedStatement preparedStatement = connection.prepareStatement(DATA_COURSE_QRY)) {
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                options.add(set.getString("course"));
            }
            preparedStatement.close();
            set.close();
			return options;
		} catch (SQLException e) {
		    printSQLException(e);
		    return null;
		}
	}

	public static List<String> getStudentData() {
		List<String> options = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
			       PreparedStatement preparedStatement = connection.prepareStatement(SHOW_STUDENT_DATA)) {
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                options.add(set.getString("student"));
            }
            preparedStatement.close();
            set.close();
			return options;
		} catch (SQLException e) {
		    printSQLException(e);
		    return null;
		}
	}

	//student new course
	public boolean postStudentData(HashMap<String, String> studentForm) throws SQLException {
		 try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
		            PreparedStatement stmt = connection.prepareStatement(SQL_STUDENT_INSERT)) {
			stmt.setInt(1,0);  
			stmt.setString(2,studentForm.get("student_name"));  
			stmt.setString(3,studentForm.get("course"));
			stmt.setString(4,studentForm.get("student_id"));
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			if(i==1){
				return true;
			}
			connection.close();  
		}catch(Exception e){
			System.out.println(e);
		}  
	   return false;
	}
	//admin add course to the list
	public boolean postCourseData(String course) throws SQLException {
		 try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
		            PreparedStatement stmt = connection.prepareStatement(SQL_COURSE_INSERT)) {
			stmt.setInt(1,0);  
			stmt.setString(2,course);  
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			if(i==1){
				return true;
			}
			connection.close();  
		}catch(Exception e){
			System.out.println(e);
		}  
	   return false;
	}
	//admin add to database
	public boolean postCourseMasterData(String course) throws SQLException {
		 try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
		            PreparedStatement stmt = connection.prepareStatement(SQL_COURSE_INSERT1)) {
			stmt.setInt(1,0);  
			stmt.setString(2,course);  
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			if(i==1){
				return true;
			}
			connection.close();  
		}catch(Exception e){
			System.out.println(e);
		}  
	   return false;
	}
	//update course from admin
	public boolean updCourseData(String course,String name,String oldCourse) throws SQLException {
		 try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
		            PreparedStatement stmt = connection.prepareStatement(SQL_COURSE_UPDATE)) {
			stmt.setString(1,course);  
			stmt.setString(2,name);  
			stmt.setString(3,oldCourse);  
			
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records Updated");  
			if(i>=1){
				return true;
			}
			connection.close();  
		}catch(Exception e){
			System.out.println(e);
		}  
	   return false;
	}

	public boolean deleteUser(String student_id) throws SQLException {
		try (
			Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
	
				   PreparedStatement stmt = connection.prepareStatement(SQL_USER_DELETE)
				   
				   ) {
		   stmt.setString(1,student_id);  
		
			 
		   int i=stmt.executeUpdate();  
		   System.out.println(i+" records Updated");  
		   if(i>=1){
			   return true;
		   }
		   connection.close();  
	   }catch(Exception e){
		   System.out.println(e);
	   }  
	  return false;
   }
   public boolean deleteStudentFromCourse(String student_id) throws SQLException {
	try (
		Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

			   PreparedStatement stmt = connection.prepareStatement(SQL_STUDENT_DELETE)
			   
			   ) {
	   stmt.setString(1,student_id);  
	
		 
	   int i=stmt.executeUpdate();  
	   System.out.println(i+" records Updated");  
	   if(i>=1){
		   return true;
	   }
	   connection.close();  
   }catch(Exception e){
	   System.out.println(e);
   }  
  return false;
}
	
	public boolean validRegData(String studentId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_VALIDREG_ID)) {
            preparedStatement.setString(1, studentId);
            System.out.println(preparedStatement);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            	 int status = resultSet.getInt("id");
            	 System.out.println("Status ::"+status);
            	 if(status>0){
            		 return true;
            	 }
            }           	
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }
	
	public boolean validRegDataId(String emailId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_VALIDREG_EMAILID)) {
            preparedStatement.setString(1, emailId);
            System.out.println(preparedStatement);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            	 int status = resultSet.getInt("id");
            	 System.out.println("Status ::"+status);
            	 if(status>0){
            		 return true;
            	 }
            }           	
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }
	
}
