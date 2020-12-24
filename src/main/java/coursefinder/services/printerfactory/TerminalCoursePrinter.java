package coursefinder.services.printerfactory;

import coursefinder.domain.Course;

public class TerminalCoursePrinter implements CoursePrinter {

    @Override
    public void print(Course course) {
        System.out.println(course);
    }
    
}
