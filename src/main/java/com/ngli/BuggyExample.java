import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BuggyExample {

    private String dbUrl = "jdbc:mysql://localhost:3306/users"; // hardcoded config
    private String dbUser = "root";
    private String dbPassword = "password";

    public void readFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line.toUpperCase()); // Code smell: unnecessary transformation
            }
        } catch (IOException e) {
            e.printStackTrace(); // Code smell: should use proper logging
        }
    }

    public void insertUser(String username, String email) {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            Statement stmt = conn.createStatement();

            // Vulnerability: SQL Injection risk
            String sql = "INSERT INTO users (username, email) VALUES ('" + username + "', '" + email + "')";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error inserting user"); // Code smell: swallowing exception
        }
    }

    public void printUserDetails(String[] userDetails) {
        for (int i = 0; i <= userDetails.length; i++) { // Bug: possible ArrayIndexOutOfBoundsException
            System.out.println(userDetails[i]);
        }
    }

    public String getUserGreeting(String name) {
        if (name.equals("admin")) { // Bug: possible NullPointerException
            return "Welcome, administrator!";
        }
        return "Welcome, " + name;
    }

    public void longMethodWithBadPractice() {
        System.out.println("This method does too much and is not split properly.");
        System.out.println("It also violates the Single Responsibility Principle.");
        System.out.println("Plus it repeats code.");
        System.out.println("Plus it repeats code.");
        System.out.println("Plus it repeats code.");
        System.out.println("Plus it repeats code.");
        System.out.println("Plus it repeats code.");
    }

    public static void main(String[] args) {
        BuggyExample example = new BuggyExample();
        example.readFile("users.txt");
        example.insertUser("john", "john@example.com");
        String[] userDetails = { "John", "Doe" };
        example.printUserDetails(userDetails);
        System.out.println(example.getUserGreeting(null));
    }
}
