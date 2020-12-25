package coursefinder.services.printerfactory;

/**
 * Factory concreta della stampante excel.
 */
public class ExcelCoursePrinterFactory extends BasePrinterFactory {

    @Override
    public CoursePrinter createCoursePrinter() {
        return new ExcelCoursePrinter();
    }
    
}
