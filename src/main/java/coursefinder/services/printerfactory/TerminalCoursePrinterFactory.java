package coursefinder.services.printerfactory;

/**
 * Factory concreta per la stampante su terminale.
 */
public class TerminalCoursePrinterFactory extends BasePrinterFactory {

    @Override
    public CoursePrinter createCoursePrinter() {
        return new TerminalCoursePrinter();
    }   
}