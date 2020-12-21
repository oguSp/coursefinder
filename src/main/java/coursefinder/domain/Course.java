package coursefinder.domain;

import java.util.Date;
import java.util.List;

public class Course {
    
    private String courseNumber;
    private String courseTitle;
    private CourseYear courseYear;
    private Date launchDate;
    private List<Instructor> instructors;
    private Institution institution;



    public Course(String courseNumber, String courseTitle, CourseYear courseYear, Date launchDate, List<Instructor> instructors,
            Institution institution) {
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.courseYear = courseYear;
        this.launchDate = launchDate;
        this.instructors = instructors;
        this.institution = institution;
    }

    

    @Override
    public String toString() {
        return "Course Number = " + courseNumber + ", Course Title = " + courseTitle +", " + institution + "\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseNumber == null) ? 0 : courseNumber.hashCode());
        result = prime * result + ((institution == null) ? 0 : institution.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (courseNumber == null) {
            if (other.courseNumber != null)
                return false;
        } else if (!courseNumber.equals(other.courseNumber))
            return false;
        if (institution == null) {
            if (other.institution != null)
                return false;
        } else if (!institution.equals(other.institution))
            return false;
        return true;
    }

    
}
