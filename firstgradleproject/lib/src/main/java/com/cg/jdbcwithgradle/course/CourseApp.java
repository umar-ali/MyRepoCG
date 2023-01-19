package com.cg.jdbcwithgradle.course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class CourseApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		try {
			Class.forName("org.postgresql.Driver");
			
	        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Practice","postgres","admin");
	        Courses courses = new Courses(conn, sc);
	        
	        boolean run = true;
	       while(run) {
	        System.out.println("Choose the following operation to perform\n"+
	        					"1. Insert data \n"+
	        					"2. Update course fee \n"+
	        					"3. Delete course \n"+
	        					"4.Display course by Name \n"+
	        					"5. Display All\n"+
	        					"6. Exit\n");
	        
	        int ch = sc.nextInt();
	        switch(ch) {
	        case 1:
	        	courses.insertData();
	        	Courses.displayAll();
	        	break;
	        case 2:
	        	courses.updateCourse();
	        	Courses.displayAll();
	        	break;
	        case 3:
	        	courses.deleteCourse();
	        	Courses.displayAll();
	        	break;
	        case 4:
	        	courses.displayCourse();
	        	break;
	        case 5:
	        	Courses.displayAll();
	        	break;
	        case 6:
	        	run = false;
	        	break;
	        }
	        }
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
        
	}

}
