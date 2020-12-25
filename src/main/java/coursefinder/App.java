package coursefinder;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import coursefinder.controller.CourseFinderController;
import coursefinder.repository.course.CSVCourseRepository;
import coursefinder.services.printerfactory.BasePrinterFactory;
import coursefinder.services.printerfactory.ExcelCoursePrinterFactory;
import coursefinder.services.printerfactory.TerminalCoursePrinterFactory;


public class App {
    private static BasePrinterFactory basePrinterFactory;
    public static void main(String[] args) {
        Option option_Instructor = Option.builder("i").required(false)
                .desc("The name of Instructor surrended by a single quote").longOpt("instructor").hasArgs().build();
        Option option_Year = Option.builder("y").required(false).desc("The course Year").longOpt("year").hasArg()
                .build();
        Option option_LaunchDate = Option.builder("l").required(false).desc("The launch date").longOpt("launchdate")
                .hasArg().build();
        Option option_writeToxlsFile = Option.builder("x").required(false).desc("Write the result on xls file in the current user directory").longOpt("xls")
                .build();
        Options options = new Options();
        options.addOption(option_Instructor);
        options.addOption(option_Year);
        options.addOption(option_LaunchDate);
        options.addOption(option_writeToxlsFile);
        CommandLineParser parser = new DefaultParser();
        try {
            // parsing degli argomenti da linea di comando
            CommandLine line = parser.parse(options, args);
            configureCoursePrinter(line.hasOption('x'));
            if (line.getArgs().length > 1)
                System.err.println("Wrong number of arguments");
            // nessuna opzione, prelevo tutti i corsi mostrandoli solo una volta nel caso il
            // corso sia ripetuto
            else if (line.getOptions().length == 0) {
                CourseFinderController controller = new CourseFinderController(new CSVCourseRepository(line.getArgs()[0]));
                controller.findAllCourses().forEach(course -> basePrinterFactory.createCoursePrinter().print(course));;
                // filtro per tutte e tre le opzioni contemporaneamente
            } else if (line.hasOption('i') && line.hasOption('y') && line.hasOption('l')) {
                int yearInput = Integer.parseInt(line.getOptionValue('y'));
                DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                Date launchDateInput = format.parse(line.getOptionValue('l'));
                CourseFinderController controller = new CourseFinderController(new CSVCourseRepository(line.getArgs()[0]));
                controller.filterByInstructorYearLaunchDate(line.getOptionValues('i'), yearInput, launchDateInput)
                        .forEach(course -> basePrinterFactory.createCoursePrinter().print(course));
                // filtro per instruttori e anno
            } else if (line.hasOption('i') && line.hasOption('y')) {
                int yearInput = Integer.parseInt(line.getOptionValue('y'));
                CourseFinderController controller = new CourseFinderController(new CSVCourseRepository(line.getArgs()[0]));
                controller.filterByInstructorYear(line.getOptionValues('i'), yearInput)
                        .forEach(course -> basePrinterFactory.createCoursePrinter().print(course));
                // filtro per instruttori e anno solare
            } else if (line.hasOption('i') && line.hasOption('l')) {
                DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                Date launchDateInput = format.parse(line.getOptionValue('l'));
                CourseFinderController controller = new CourseFinderController(new CSVCourseRepository(line.getArgs()[0]));
                controller.filterByInstructorLaunchDate(line.getOptionValues('i'), launchDateInput)
                        .forEach(course -> basePrinterFactory.createCoursePrinter().print(course));
                // filtro per tutti i corsi, anno e anno solare
            } else if (line.hasOption('y') && line.hasOption('l')) {
                CourseFinderController controller = new CourseFinderController(new CSVCourseRepository(line.getArgs()[0]));
                int yearInput = Integer.parseInt(line.getOptionValue('y'));
                DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                Date launchDateInput = format.parse(line.getOptionValue('l'));
                controller.filterByYearLaunchDate(yearInput, launchDateInput)
                        .forEach(course -> basePrinterFactory.createCoursePrinter().print(course));
                 // filtro per i soli instruttori       
            } else if (line.hasOption('i')) {
                CourseFinderController controller = new CourseFinderController(new CSVCourseRepository(line.getArgs()[0]));
                controller.filterByInstructor(line.getOptionValues('i'))
                        .forEach(course -> basePrinterFactory.createCoursePrinter().print(course));
                // filtro per tutti i corsi e anno
            } else if (line.hasOption('y')) {
                CourseFinderController controller = new CourseFinderController(new CSVCourseRepository(line.getArgs()[0]));
                int yearInput = Integer.parseInt(line.getOptionValue('y'));
                controller.filterByYear(yearInput)
                        .forEach(course -> basePrinterFactory.createCoursePrinter().print(course));
                // filtro per tutti i corsi e anno solare
            } else if (line.hasOption('l')) {
                CourseFinderController controller = new CourseFinderController(new CSVCourseRepository(line.getArgs()[0]));
                DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                Date launchDateInput = format.parse(line.getOptionValue('l'));
                controller.filterByLaunchDate(launchDateInput)
                        .forEach(course -> basePrinterFactory.createCoursePrinter().print(course));
            }        
        } catch (ParseException exp) {
            // Stampa errore nel caso di opzioni non riconosciute
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        } catch (java.text.ParseException e) {
            // Stampa errore nel caso di data passata in forma non corretta
            System.err.println("Date Parsing failed.  Reason: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println(" Error: File not found");
        }
    }
    // Selezione della factory relativa alla stampante
    static void configureCoursePrinter(boolean hasXlsOption) {
        if (hasXlsOption == true) {
            basePrinterFactory = new ExcelCoursePrinterFactory();
        } else {
            basePrinterFactory = new TerminalCoursePrinterFactory();
        }
    }
}