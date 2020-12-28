package coursefinder.controller;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import org.junit.Test;

import coursefinder.domain.Course;
import coursefinder.repository.course.CSVCourseRepository;

/**
 * I file "oracolo" su cui viene fatto il confronto sono salvati in vari csv (uno per ogni test). 
 * Bisogna mettere il path assoluto per i file.
 */
public class CourseFinderControllerTest {

    @Test
    public void filterByInstructorYearLaunchDateTest() throws FileNotFoundException, ParseException {
        CourseFinderController controllerResult1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/coursefinder/src/test/java/coursefinder/resources/filterByInstructorYearLaunchDate.csv"));
        CourseFinderController controller1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/harvardMIT.csv"));
        List<Course> result1 = controllerResult1.findAllCourses();
        String[] instructorNames = {"Chris Terman","Bonnie Lam"};
        int year = 4;
        DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        Date launchDate = format.parse("2015");
        List<Course> courses1 = controller1.filterByInstructorYearLaunchDate(instructorNames, year, launchDate);
        assertTrue(courses1.containsAll(result1));
    }

    @Test
    public void filterByInstructorYearTest() throws FileNotFoundException {
        CourseFinderController controllerResult1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/coursefinder/src/test/java/coursefinder/resources/filterByInstructorYear.csv"));
        CourseFinderController controller1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/harvardMIT.csv"));
        List<Course> result1 = controllerResult1.findAllCourses();
        String[] instructorNames = {"Elisa New"};
        int year = 2;
        List<Course> courses1 = controller1.filterByInstructorYear(instructorNames,year);
        assertTrue(courses1.containsAll(result1));
    }

    @Test
    public void filterByInstructorLaunchDateTest() throws FileNotFoundException, ParseException {
        CourseFinderController controllerResult1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/coursefinder/src/test/java/coursefinder/resources/filterByInstructorLaunchDate.csv"));
        CourseFinderController controller1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/harvardMIT.csv"));
        List<Course> result1 = controllerResult1.findAllCourses();
        String[] instructorNames = {" David E. Pritchard","Isaac Chuang"};
        DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        Date launchDate = format.parse("2016");
        List<Course> courses1 = controller1.filterByInstructorLaunchDate(instructorNames, launchDate);
        assertTrue(courses1.containsAll(result1));
    }

    @Test
    public void filterByYearLaunchDateTest() throws ParseException, FileNotFoundException {
        CourseFinderController controllerResult1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/coursefinder/src/test/java/coursefinder/resources/filterByYearLaunchDate.csv"));
        CourseFinderController controller1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/harvardMIT.csv"));
        List<Course> result1 = controllerResult1.findAllCourses();
        DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        Date launchDate = format.parse("2016");
        int year = 4;
        List<Course> courses1 = controller1.filterByYearLaunchDate(year, launchDate);
        assertTrue(courses1.containsAll(result1));
    }

    @Test
    public void filterByInstructorTest() throws FileNotFoundException {
        CourseFinderController controllerResult1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/coursefinder/src/test/java/coursefinder/resources/filterByInstructor.csv"));
        CourseFinderController controller1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/harvardMIT.csv"));
        List<Course> result1 = controllerResult1.findAllCourses();
        String[] instructorNames = {"David E. Pritchard"};
        List<Course> courses1 = controller1.filterByInstructor(instructorNames);
        assertTrue(courses1.containsAll(result1));
    }

    @Test
    public void filterByYearTest() throws FileNotFoundException {
        CourseFinderController controllerResult1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/coursefinder/src/test/java/coursefinder/resources/filterByYear.csv"));
        CourseFinderController controller1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/harvardMIT.csv"));
        List<Course> result1 = controllerResult1.findAllCourses();
        int year = 1;
        List<Course> courses1 = controller1.filterByYear(year);
        assertTrue(courses1.containsAll(result1));
    }

    @Test
    public void filterByLaunchDate() throws FileNotFoundException, ParseException {
        CourseFinderController controllerResult1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/coursefinder/src/test/java/coursefinder/resources/filterByLaunchDate.csv"));
        CourseFinderController controller1 = new CourseFinderController(new CSVCourseRepository("/home/ugo/Desktop/harvardMIT.csv"));
        List<Course> result1 = controllerResult1.findAllCourses();
        DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        Date launchDate = format.parse("2014");
        List<Course> courses1 = controller1.filterByLaunchDate(launchDate);
        assertTrue(courses1.containsAll(result1));
    }
}