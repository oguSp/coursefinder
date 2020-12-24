package coursefinder.services.printerfactory;

public class ExcelCoursePrinterFactory extends BasePrinterFactory {

    @Override
    public CoursePrinter createCoursePrinter() {
        return new ExcelCoursePrinter();
    }
    
}
