package coursefinder.repository.course;

import java.util.List;

import coursefinder.domain.Course;

public interface ICourseRepository {
    
    List<Course> findByInstructorNames(String[] instructorNames);
    List<Course> getAllCourses();
}