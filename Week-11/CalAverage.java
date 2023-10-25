import java.sql.*;
import java.lang.*;
public class CalAverage {
    public static void main(String args[]) {
        try {
              Connection conn = null;
              Statement stmt = null;
              String DB_URL = "jdbc:sqlite:/tempfs/db";
              System.setProperty("org.sqlite.tmpdir", "/tempfs");
              String query="";
              // Open a connection
              conn = DriverManager.getConnection(DB_URL);
              stmt = conn.createStatement();
String CREATE_TABLE_SQL="CREATE TABLE players ( UID INT, first_name VARCHAR(45), last_name VARCHAR(45), age INT);";
			stmt.executeUpdate(CREATE_TABLE_SQL);			
			query = " insert into Players (UID, first_name, last_name, age)"  + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt (1, 1);
			preparedStmt.setString (2, "Rama");
			preparedStmt.setString (3, "Gopala");
			preparedStmt.setInt(4, 24);
			preparedStmt.execute();
			preparedStmt.setInt (1, 2);
			preparedStmt.setString (2, "John");
			preparedStmt.setString   (3, "Mayer");
			preparedStmt.setInt(4, 22);
			preparedStmt.execute();
			preparedStmt.setInt (1, 3);
			preparedStmt.setString (2, "Leo");
			preparedStmt.setString   (3, "Martin");
			preparedStmt.setInt(4, 27);
			preparedStmt.execute();

			ResultSet rs = stmt.executeQuery("SELECT * FROM players;");
			int count=0,total=0;
			while(rs.next()){
				count++;
				total = total + Integer.parseInt(rs.getString(4));
			}
			
			//Output
			System.out.println("Average age of players is " +(total/count));
				

			conn.close();


			
		}
                catch(Exception e){ System.out.println(e);}  
	}  
}  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CalAverage {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            String DB_URL = "jdbc:sqlite:/tempfs/db";
            System.setProperty("org.sqlite.tmpdir", "/tempfs");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();

            // Create the "players" table if it doesn't exist
            String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS players (UID INT, first_name VARCHAR(45), last_name VARCHAR(45), age INT);";
            stmt.executeUpdate(CREATE_TABLE_SQL);

            // Insert sample data into the table
            String query = "INSERT INTO players (UID, first_name, last_name, age) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, 1);
            preparedStmt.setString(2, "Rama");
            preparedStmt.setString(3, "Gopala");
            preparedStmt.setInt(4, 24);
            preparedStmt.execute();

            preparedStmt.setInt(1, 2);
            preparedStmt.setString(2, "John");
            preparedStmt.setString(3, "Mayer");
            preparedStmt.setInt(4, 22);
            preparedStmt.execute();

            preparedStmt.setInt(1, 3);
            preparedStmt.setString(2, "Leo");
            preparedStmt.setString(3, "Martin");
            preparedStmt.setInt(4, 27);
            preparedStmt.execute();

            // Calculate the average age of players
            ResultSet rs = stmt.executeQuery("SELECT * FROM players;");
            int count = 0;
            int total = 0;
            while (rs.next()) {
                count++;
                total += rs.getInt("age");
            }

            // Output the average age
            if (count > 0) {
                System.out.println("Average age of players is " + (total / count));
            } else {
                System.out.println("No players in the database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
