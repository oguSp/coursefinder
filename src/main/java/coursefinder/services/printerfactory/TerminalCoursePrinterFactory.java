package coursefinder.services.printerfactory;

public class TerminalCoursePrinterFactory extends BasePrinterFactory {

    @Override
    public CoursePrinter createCoursePrinter() {
        return new TerminalCoursePrinter();
    }   
}
