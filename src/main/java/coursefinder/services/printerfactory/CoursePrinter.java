package coursefinder.services.printerfactory;
/**
 * Interfaccia comune per le stampanti dei corsi.
 */
import coursefinder.domain.Course;

public interface CoursePrinter {
    
    public void print(Course course);

}
