package coursefinder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import coursefinder.repository.course.CSVCourseRepository;
import coursefinder.repository.course.ICourseRepository;

public class App 
{
    public static void main( String[] args )
    {   
        CommandLine commandLine;
        Option option_Instructor = Option.builder("i")
            .required(false)
            .desc("The name of Instructor surrended by a single quote")
            .longOpt("instructor")
            .hasArgs()
            .build();
        Options options = new Options();
        options.addOption(option_Instructor);

        CommandLineParser parser = new DefaultParser();
            try {
                // parsing degli argomenti da linea di comando
                CommandLine line = parser.parse( options, args );
                if (line.getArgs().length > 1) System.err.println("Wrong number of arguments");
                else if (line.getOptions().length == 0) {
                    //TODO metodo che carica tutti i corsi
                }
                else if (line.hasOption('i')){
                    ICourseRepository courseRepository = new CSVCourseRepository(line.getArgs()[0]);
                    System.out.println(courseRepository.findByInstructorNames(line.getOptionValues('i')));
                }
            }
            catch( ParseException exp ) {
                // Stampa errore nel caso di opzioni non riconosciute 
                System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            }

        
        
    }
}
