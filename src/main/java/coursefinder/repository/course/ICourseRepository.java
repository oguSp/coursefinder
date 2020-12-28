package coursefinder.repository.course;

import java.util.List;

import coursefinder.domain.Course;

/**
 * Interfaccia che definisce i metodi per recuperare i dati da più fonti.
 */
public interface ICourseRepository {
    
    List<Course> findByInstructorNames(String[] instructorNames);
    List<Course> getAllCourses();
}