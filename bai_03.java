//package Ket_Noi_CSDL;
//
//public class bai_03 {
//	import java.sql.Connection;
//	import java.sql.DriverManager;
//	import java.sql.ResultSet;
//	import java.sql.SQLException;
//	import java.sql.Statement;
//
//	class Student {
//	    private int id;
//	    private String name;
//	    private int age;
//
//	    public Student(int id, String name, int age) {
//	        this.id = id;
//	        this.name = name;
//	        this.age = age;
//	    }
//
//	    @Override
//	    public String toString() {
//	        return "Student{id=" + id + ", name='" + name + "', age=" + age + "}";
//	    }
//
//	    public int getId() {
//	        return id;
//	    }
//
//	    public String getName() {
//	        return name;
//	    }
//
//	    public int getAge() {
//	        return age;
//	    }
//	}
//
//	public class Main {
//	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
//	    private static final String JDBC_USER = "your_username";
//	    private static final String JDBC_PASSWORD = "your_password";
//
//	    public static void main(String[] args) {
//	        Connection connection = null;
//
//	        try {
//	            // 1. Kết nối tới cơ sở dữ liệu
//	            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//
//	            // 2. Thêm một học sinh mới
//	            addStudent(connection, new Student(1, "John Doe", 20));
//
//	            // 3. Sửa thông tin học sinh
//	            updateStudent(connection, new Student(1, "John Smith", 21));
//
//	            // 4. Xóa một học sinh
//	            deleteStudent(connection, 1);
//
//	            // 5. In danh sách học sinh
//	            printAllStudents(connection);
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            // Đóng kết nối
//	            try {
//	                if (connection != null) connection.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//
//	    // Thêm học sinh
//	    public static void addStudent(Connection connection, Student student) throws SQLException {
//	        String sql = "INSERT INTO students (id, name, age) VALUES (" + student.getId() + ", '" + student.getName() + "', " + student.getAge() + ")";
//	        Statement statement = connection.createStatement();
//	        int rowsInserted = statement.executeUpdate(sql);
//	        if (rowsInserted > 0) {
//	            System.out.println("A new student was inserted successfully!");
//	        }
//	    }
//
//	    // Sửa thông tin học sinh
//	    public static void updateStudent(Connection connection, Student student) throws SQLException {
//	        String sql = "UPDATE students SET name='" + student.getName() + "', age=" + student.getAge() + " WHERE id=" + student.getId();
//	        Statement statement = connection.createStatement();
//	        int rowsUpdated = statement.executeUpdate(sql);
//	        if (rowsUpdated > 0) {
//	            System.out.println("An existing student was updated successfully!");
//	        }
//	    }
//
//	    // Xóa học sinh
//	    public static void deleteStudent(Connection connection, int studentId) throws SQLException {
//	        String sql = "DELETE FROM students WHERE id=" + studentId;
//	        Statement statement = connection.createStatement();
//	        int rowsDeleted = statement.executeUpdate(sql);
//	        if (rowsDeleted > 0) {
//	            System.out.println("A student was deleted successfully!");
//	        }
//	    }
//
//	    // In danh sách học sinh
//	    public static void printAllStudents(Connection connection) throws SQLException {
//	        String sql = "SELECT id, name, age FROM students";
//	        Statement statement = connection.createStatement();
//	        ResultSet resultSet = statement.executeQuery(sql);
//
//	        while (resultSet.next()) {
//	            int id = resultSet.getInt("id");
//	            String name = resultSet.getString("name");
//	            int age = resultSet.getInt("age");
//
//	            Student student = new Student(id, name, age);
//	            System.out.println(student);
//	        }
//	    }
//	}
//
//}
