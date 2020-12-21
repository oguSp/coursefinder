package coursefinder.domain;

public class CourseYear {
    
    private int year;

    public CourseYear(int year){
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + year;
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
        CourseYear other = (CourseYear) obj;
        if (year != other.year)
            return false;
        return true;
    }

    

}
