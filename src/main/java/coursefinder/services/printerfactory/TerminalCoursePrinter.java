package coursefinder.services.printerfactory;

import coursefinder.domain.Course;

/**
 * Stampante che stampa su terminale.
 */
public class TerminalCoursePrinter implements CoursePrinter {

    @Override
    public void print(Course course) {
        System.out.println(course);
    }
    
}
