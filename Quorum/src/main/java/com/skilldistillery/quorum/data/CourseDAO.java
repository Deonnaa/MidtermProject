package com.skilldistillery.quorum.data;

import java.util.List;

import com.skilldistillery.quorum.entities.Course;
import com.skilldistillery.quorum.entities.Professor;
import com.skilldistillery.quorum.entities.School;

public interface CourseDAO {

	Course createCourse(Course course);
    Course getCourseById(int id);
    List<Course> getAllCourses();
    Course updateCourse(int id, Course course);
    boolean deactivateCourse(int id);
	List<Course> getCoursesBySchool(int schoolId);
	List<Course> getCoursesByUser(int userID);
	boolean addCourseToUser(int userId, int courseId);
	boolean removeCourseFromUser(int userId, int courseId);

	
}
