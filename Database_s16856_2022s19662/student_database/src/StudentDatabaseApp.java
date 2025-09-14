import java.sql.*;
import java.util.Scanner;

public class StudentDatabaseApp {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/stu_database";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection conn;
    private Scanner scanner;

    public StudentDatabaseApp() {
        scanner = new Scanner(System.in);
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to Database Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create Student Table
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "age INT, " +
                "course VARCHAR(100))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Students table created successfully (if not exists).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert Student
    public void insertStudent() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        String sql = "INSERT INTO students(name, age, course) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, course);
            pstmt.executeUpdate();
            System.out.println("✅ Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Students
    public void viewStudents() {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("---- Student List ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Course: " + rs.getString("course"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Student
    public void updateStudent() {
        System.out.print("Enter Student ID to Update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter New Course: ");
        String course = scanner.nextLine();

        String sql = "UPDATE students SET name=?, age=?, course=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, course);
            pstmt.setInt(4, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student updated successfully!");
            } else {
                System.out.println("⚠ No student found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Student
    public void deleteStudent() {
        System.out.print("Enter Student ID to Delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        String sql = "DELETE FROM students WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student deleted successfully!");
            } else {
                System.out.println("⚠ No student found with that ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menu
    public void showMenu() {
        while (true) {
            System.out.println("\n===== Student Database Menu =====");
            System.out.println("1. Create Students Table");
            System.out.println("2. Add Student");
            System.out.println("3. View Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> createTable();
                case 2 -> insertStudent();
                case 3 -> viewStudents();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    System.out.println("👋 Exiting...");
                    close();
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    // Close resources
    public void close() {
        try {
            if (conn != null) conn.close();
            if (scanner != null) scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main
    public static void main(String[] args) {
        StudentDatabaseApp app = new StudentDatabaseApp();
        app.showMenu();
    }
}
