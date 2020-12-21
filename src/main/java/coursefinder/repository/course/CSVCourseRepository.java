package coursefinder.repository.course;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import coursefinder.domain.Course;
import coursefinder.domain.CourseYear;
import coursefinder.domain.Institution;
import coursefinder.domain.Instructor;

public class CSVCourseRepository implements ICourseRepository {

    private String filePath;

    public CSVCourseRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Course> findByInstructorNames(String[] instructorNames) {
        try {
            List<Course> courses = new ArrayList<>();
            List<String> instructorsNamesAsList = Arrays.asList(instructorNames);
            Reader input = new FileReader(this.filePath);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(input);
            for (CSVRecord record : records) {
                String[] csvInstructorNames = record.get("Instructors").split(", ");
                List<String> csvInstructorsNamesAsList = Arrays.asList(csvInstructorNames);
                for (String csvInstructorName : csvInstructorsNamesAsList) {
                    if (instructorsNamesAsList.contains(csvInstructorName)){
                        Course course = mapCourse(record.get("Course Number"), record.get("Course Title"), record.get("Year"), record.get("Launch Date"), csvInstructorsNamesAsList, record.get("Institution"));
                        if (!courses.contains(course)){
                            courses.add(course);
                        }
                        break; //Se ho trovato anche solo uno degli insegnanti creo il corso.
                    }
                }
            }
            return courses;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private Course mapCourse(String courseNumber, String courseTitle, String year, String date, List<String> instructorsNames, String csvInstitution )
            throws ParseException {
        CourseYear intYear = new CourseYear(Integer.parseInt(year));
        DateFormat format = new SimpleDateFormat("MM/d/yyyy", Locale.ENGLISH);
        Date launchDate = format.parse(date);
        Institution institution =new Institution(csvInstitution);
        List<Instructor> instructors = new ArrayList<>();
        for (String instructorName : instructorsNames) {
            instructors.add(new Instructor(instructorName));
        }
        return new Course(courseNumber, courseTitle, intYear, launchDate, instructors, institution);
    }
}
