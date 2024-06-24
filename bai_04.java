//package Ket_Noi_CSDL;
//
//public class bai_04 {
//	import java.sql.Connection;
//	import java.sql.DriverManager;
//	import java.sql.PreparedStatement;
//	import java.sql.SQLException;
//
//	public class PreparedStatementExample {
//	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
//	    private static final String JDBC_USER = "your_username";
//	    private static final String JDBC_PASSWORD = "your_password";
//
//	    public static void main(String[] args) {
//	        try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
//	            // Thêm một học sinh mới
//	            addStudent(con, 1, "John Doe", 20);
//
//	            // Sửa thông tin học sinh
//	            updateStudent(con, 1, "John Smith", 21);
//
//	            // Xóa học sinh
//	            deleteStudent(con, 1);
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    // Thêm học sinh
//	    public static void addStudent(Connection con, int id, String name, int age) throws SQLException {
//	        String sql = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
//	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
//	            pstmt.setInt(1, id);
//	            pstmt.setString(2, name);
//	            pstmt.setInt(3, age);
//	            int rowsInserted = pstmt.executeUpdate();
//	            if (rowsInserted > 0) {
//	                System.out.println("A new student was inserted successfully!");
//	            }
//	        }
//	    }
//
//	    // Sửa thông tin học sinh
//	    public static void updateStudent(Connection con, int id, String name, int age) throws SQLException {
//	        String sql = "UPDATE students SET name=?, age=? WHERE id=?";
//	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
//	            pstmt.setString(1, name);
//	            pstmt.setInt(2, age);
//	            pstmt.setInt(3, id);
//	            int rowsUpdated = pstmt.executeUpdate();
//	            if (rowsUpdated > 0) {
//	                System.out.println("An existing student was updated successfully!");
//	            }
//	        }
//	    }
//
//	    // Xóa học sinh
//	    public static void deleteStudent(Connection con, int id) throws SQLException {
//	        String sql = "DELETE FROM students WHERE id=?";
//	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
//	            pstmt.setInt(1, id);
//	            int rowsDeleted = pstmt.executeUpdate();
//	            if (rowsDeleted > 0) {
//	                System.out.println("A student was deleted successfully!");
//	            }
//	        }
//	    }
//	}
//
//}
