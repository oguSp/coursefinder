package coursefinder.domain;

import java.util.Date;

public class CourseYear {
    
    private int year;
    private Date launchDate;

    public CourseYear(int year, Date launchDate){
        this.year = year;
        this.launchDate = launchDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    @Override
    public String toString() {
        return "Coure Year + " + year + "Launch Date = " + launchDate;
    }

    
}
