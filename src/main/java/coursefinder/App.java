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

import coursefinder.domain.Instructor;
import coursefinder.repository.course.CSVCourseRepository;
import coursefinder.repository.course.ICourseRepository;

public class App {
    public static void main(String[] args) {
        CommandLine commandLine;
        Option option_Instructor = Option.builder("i").required(false)
                .desc("The name of Instructor surrended by a single quote").longOpt("instructor").hasArgs().build();
        Option option_Year = Option.builder("y").required(false).desc("The course Year").longOpt("year").hasArg()
                .build();
        Option option_LaunchDate = Option.builder("l").required(false).desc("The launch date").longOpt("launchdate")
                .hasArg().build();

        Options options = new Options();
        options.addOption(option_Instructor);
        options.addOption(option_Year);
        options.addOption(option_LaunchDate);

        CommandLineParser parser = new DefaultParser();
        try {
            // parsing degli argomenti da linea di comando
            CommandLine line = parser.parse(options, args);
            if (line.getArgs().length > 1)
                System.err.println("Wrong number of arguments");
            // nessuna opzione, prelevo tutti i corsi mostrandoli solo una volta nel caso il
            // corso sia ripetuto
            else if (line.getOptions().length == 0) {
                ICourseRepository courseRepository = new CSVCourseRepository(line.getArgs()[0]);
                courseRepository.getAllCourses().stream().distinct().forEach(course -> System.out.println(course));
            } else if (line.hasOption('i') && line.hasOption('y') && line.hasOption('l')) {
                int yearInput = Integer.parseInt(line.getOptionValue('y'));
                DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                Date launchDateInput = format.parse(line.getOptionValue('l'));
                ICourseRepository courseRepository = new CSVCourseRepository(line.getArgs()[0]);
                courseRepository.findByInstructorNames(line.getOptionValues('i')).stream()
                        .filter(course -> course.getCourseYear().getYear() == yearInput
                                && course.getCourseYear().getLaunchDate().getYear() == launchDateInput.getYear())
                        .forEach(course -> System.out.println(
                                course + " Year = " + yearInput + " Launch date = " + launchDateInput.getYear()));
            } else if (line.hasOption('i') && line.hasOption('y')) {
                int yearInput = Integer.parseInt(line.getOptionValue('y'));
                ICourseRepository courseRepository = new CSVCourseRepository(line.getArgs()[0]);
                courseRepository.findByInstructorNames(line.getOptionValues('i')).stream()
                        .filter(course -> course.getCourseYear().getYear() == yearInput)
                        .forEach(course -> System.out.println(course + " Year = " + yearInput));
            } else if (line.hasOption('i') && line.hasOption('l')) {
                DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                Date launchDateInput = format.parse(line.getOptionValue('l'));
                ICourseRepository courseRepository = new CSVCourseRepository(line.getArgs()[0]);
                courseRepository.findByInstructorNames(line.getOptionValues('i')).stream()
                        .filter(course -> course.getCourseYear().getLaunchDate().getYear() == launchDateInput.getYear())
                        .forEach(course -> System.out.println(course + " Launch date = " + launchDateInput.getYear()));
            } else if (line.hasOption('y') && line.hasOption('l')) {
                ICourseRepository courseRepository = new CSVCourseRepository(line.getArgs()[0]);
                int yearInput = Integer.parseInt(line.getOptionValue('y'));
                DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                Date launchDateInput = format.parse(line.getOptionValue('l'));
                courseRepository.getAllCourses().stream().filter(course -> course.getCourseYear().getYear() == yearInput
                && course.getCourseYear().getLaunchDate().getYear() == launchDateInput.getYear())
                .forEach(course -> System.out.println(
                course + " Year = " + yearInput + " Launch date = " + launchDateInput.getYear()));
            } else if (line.hasOption('i')) {
                ICourseRepository courseRepository = new CSVCourseRepository(line.getArgs()[0]);
                courseRepository.findByInstructorNames(line.getOptionValues('i')).stream().distinct()
                        .forEach(course -> System.out.println(course));
            }
            //TODO Mettere opzioni per solo anno e solo data di lancio
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
}