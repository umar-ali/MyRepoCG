package com.cg.jdbcwithgradle.course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Courses {
		static Connection conn;
		static Scanner sc;
		
		Courses(Connection conn, Scanner sc) throws SQLException{
			Courses.conn = conn;
			Courses.sc = sc;
			//boolean created =Courses.conn.prepareStatement("create table courses(course_id serial primary key,course_name text not null, course_fees integer );").execute();
			//if(created)System.out.println("table created");
		}
		
		void insertData() throws SQLException{
			System.out.println("Enter Course data");
			System.out.println("Enter Course name");
			String name = Courses.sc.next();
			System.out.println("Enter Course fees");
			int  fees=  Courses.sc.nextInt();
			PreparedStatement pstmt = Courses.conn.prepareStatement("insert into courses(course_name, course_fees) values(?,?);");
			pstmt.setString(1, name);
			pstmt.setInt(2, fees);
			pstmt.execute();
			System.out.println("data inserted");

		
		}
		
		void updateCourse() throws SQLException{
			System.out.println("Enter Course name");
			String name =  Courses.sc.next();
			System.out.println("Enter Course new fees");
			int  fees=  Courses.sc.nextInt();
			PreparedStatement pstmt = Courses.conn.prepareStatement("update courses set course_fees = ? where course_name = ?;");
			pstmt.setString(2, name);
			pstmt.setInt(1, fees);
			pstmt.execute();
			System.out.println("data updated");

		}
		
		void deleteCourse() throws SQLException{
			System.out.println("Enter course id to delete");
			int id = Courses.sc.nextInt();
			PreparedStatement pstmt = Courses.conn.prepareStatement("delete from courses where course_id = ?;");
			pstmt.setInt(1, id);
			pstmt.execute();
			System.out.println("data deleted");

		}
		
		void displayCourse() throws SQLException{
			System.out.println("Enter Course name");
			String name = Courses.sc.next();
			PreparedStatement pstmt = Courses.conn.prepareStatement("select * from courses where course_name = ?;");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				System.out.println(String.format("The course Id = %d -> The course Name = %s -> The course fees = %d\n",rs.getInt("course_id"), rs.getString("course_name"), rs.getInt("course_fees")));
		}
		
		static void displayAll() throws SQLException{
			 ResultSet rs = Courses.conn.prepareStatement("select * from courses").executeQuery();
			while(rs.next())
						System.out.println(String.format("The course Id = %d -> The course Name = %s -> The course fees = %d\n",rs.getInt("course_id"), rs.getString("course_name"), rs.getInt("course_fees")));
		}
		
}