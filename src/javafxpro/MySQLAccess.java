/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxpro;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author Happy
 */
public class MySQLAccess {
 public static Connection get_DB_Connection() {
        final String DB_SERVER = "localhost";
        final String PORT = "3306";
        final String DB_NAME = "exam";
        final String DB_URL = "jdbc:mysql://" + DB_SERVER + ":" + PORT + "/" + DB_NAME;
        final String USER = "root";
        final String PASS = "";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (SQLException e) {
            System.out.println("Database is not connected !");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean searchStudent(String email, String password) {
        boolean existed = false;
        try {
            Connection conn = get_DB_Connection();
            String sql_query = "select Semail, Spassword from students where Semail=? and Spassword=?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet results = stmt.executeQuery();
            existed = results.next();
            conn.close();
            return existed;
        } catch (SQLException e) {
            e.printStackTrace();
            return existed;
        }
    }
    
        public static boolean existStudent(String email) {
        boolean existed = false;
        try {
            Connection conn = get_DB_Connection();
            String sql_query = "select Semail from students where Semail=?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, email);
            ResultSet results = stmt.executeQuery();
            existed = results.next();
            conn.close();
            return existed;
        } catch (SQLException e) {
            e.printStackTrace();
            return existed;
        }
    }
        
        public static boolean existTeacher(String email) {
        boolean existed = false;
        try {
            Connection conn = get_DB_Connection();
            String sql_query = "select TEmail from teacher where Temail=?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, email);
            ResultSet results = stmt.executeQuery();
            existed = results.next();
            conn.close();
            return existed;
        } catch (SQLException e) {
            e.printStackTrace();
            return existed;
        }
    }
    
    public static boolean searchTeacher(String email, String password) {
        boolean existed = false;
        try {
            Connection conn = get_DB_Connection();
            String sql_query = "select Temail, Tpassword from teacher where Temail=? and Tpassword=?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet results = stmt.executeQuery();
            existed = results.next();
            conn.close();
            return existed;
        } catch (SQLException e) {
            e.printStackTrace();
            return existed;
        }
    }

    public static void insertStudent(String username, String email, String password, String year) {
        try {
            boolean existed = searchStudent(username, password);
            if (existed) {
                System.out.println("User is already existed before !");
            } else {
                Connection conn = get_DB_Connection();
                // generate random id from current time millesecond
                Date date = new Date();
                long id = date.getTime();
                String sql_query = "insert into students (Sid, Sname, Semail, Spassword, Year) values (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql_query);
                stmt.setLong(1, id);
                stmt.setString(2, username);
                stmt.setString(3, email);
                stmt.setString(4, password);
                stmt.setString(5, year);
                stmt.executeUpdate();
                System.out.println("Insertion is done");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertTeacher(String username, String email, String password) {
        try {
            boolean existed = searchTeacher(username, password);
            if (existed) {
                System.out.println("User is already existed before !");
            } else {
                Connection conn = get_DB_Connection();
                // generate random id from current time millesecond
                Date date = new Date();
                long id = date.getTime();
                String sql_query = "insert into teacher (tid, tname, temail, tpassword) values (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql_query);
                stmt.setLong(1, id);
                stmt.setString(2, username);
                stmt.setString(3, email);
                stmt.setString(4, password);;
                stmt.executeUpdate();
                System.out.println("Insertion is done");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertQuestion(String question, String a, String b, String c, String d, String answer) {
        try {
                int questionNumber = 0;
                Connection conn = get_DB_Connection();
                String no_query = "SELECT Number FROM questions ORDER BY Number DESC LIMIT 1;";
                PreparedStatement stmt1 = conn.prepareStatement(no_query);
                ResultSet results = stmt1.executeQuery();
                while (results.next()) {
                    // Retrieve data
                    questionNumber = results.getInt(1);
                }
                questionNumber++;
                String sql_query = "insert into questions (Number, question, a, b, c, d, answer) values (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql_query);
                stmt.setInt(1, questionNumber);
                stmt.setString(2, question);
                stmt.setString(3, a);
                stmt.setString(4, b);
                stmt.setString(5, c);
                stmt.setString(6, d);
                stmt.setString(7, answer);
                stmt.executeUpdate();
                System.out.println("Insertion is done");
                conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String email, String password) {
        try {
            Connection conn = get_DB_Connection();
            String sql_query = "update Students set Spassword=? where Semail=?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, password);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Updating is done");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void gradeUpdate(String email, int grade) {
        try {
            Connection conn = get_DB_Connection();
            String sql_query = "update Students set Grade=? where Semail=?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, grade);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Updating is done");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String username, String password) {
        try {
            boolean existed = searchStudent(username, password);
            if (!existed) {
                System.out.println("User is not founded to be deleted !");
            } else {
                Connection conn = get_DB_Connection();
                String sql_query = "delete from Students where Sname= ? and Spassword=?";
                PreparedStatement stmt = conn.prepareStatement(sql_query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
                System.out.println("Deleting is done");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static String getName(String email){
        String name = "";
            try {
            Connection conn = get_DB_Connection();
            String sql_query = "select Sname from Students where Semail=?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, email);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // Retrieve data
                name = results.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    
        public static String getQuestion(int questionNumber){
        String question = "";
            try {
            Connection conn = get_DB_Connection();
            String sql_query = "select question from questions where Number =?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, questionNumber);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // Retrieve data
                question = results.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }
    
     public static String getChoiceA(int questionNumber){
        String choiceA = "";
            try {
            Connection conn = get_DB_Connection();
            String sql_query = "select a from questions where Number =?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, questionNumber);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // Retrieve data
                choiceA = results.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choiceA;
    }   
     
     public static String getChoiceB(int questionNumber){
        String choiceB = "";
            try {
            Connection conn = get_DB_Connection();
            String sql_query = "select b from questions where Number =?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, questionNumber);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // Retrieve data
                choiceB = results.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choiceB;
    }   
    
     public static String getChoiceC(int questionNumber){
        String choiceC = "";
            try {
            Connection conn = get_DB_Connection();
            String sql_query = "select c from questions where Number =?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, questionNumber);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // Retrieve data
                choiceC = results.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choiceC;
    }   
     
     public static String getChoiceD(int questionNumber){
        String choiceD = "";
            try {
            Connection conn = get_DB_Connection();
            String sql_query = "select d from questions where Number =?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, questionNumber);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // Retrieve data
                choiceD = results.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choiceD;
    }   
     
     public static String getAnswer(int questionNumber){
        String answer = "";
            try {
            Connection conn = get_DB_Connection();
            String sql_query = "select answer from questions where Number =?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setInt(1, questionNumber);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // Retrieve data
                answer = results.getString(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }
     
    public static int getGrade(String email){
        int grade = 0;
            try {
            Connection conn = get_DB_Connection();
            String sql_query = "select Grade from Students where Semail=?";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, email);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                // Retrieve data
                grade = results.getInt(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }
    
    public static void getAllData() {
        boolean emptyTable = true;
        try {
            Connection conn = get_DB_Connection();
            String sql_query = "select * from Students";
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            ResultSet results = stmt.executeQuery();
            System.out.println("****** Table data *****");
            while (results.next()) {
                // Retrieve each row data
                System.out.print("id: " + results.getLong("SID"));
                System.out.print(" , Sname: " + results.getString("Sname"));
                System.out.print(" , Semail: " + results.getString("Semail"));
                System.out.println(" , Spassword: " + results.getString("Spassword"));
                emptyTable = false;
            }
            if (emptyTable) {
                System.out.println("Users table is empty !");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
