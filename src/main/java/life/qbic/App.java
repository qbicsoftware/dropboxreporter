package life.qbic;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{

    private static final String APP_NAME = "Dropboxreporter";

    private static final Map<String, Map<String, Integer>> projectSummaries = new HashMap<>();

    /**
     * A list containing file entries without barcode that
     * need manual intervention.
     */
    private static final List<String> listWithManualInterventions = new ArrayList<>();


    public static void main( String[] args ) {
        if (args.length != 2) {
            System.out.format("Please provide a date and the path to a log file:\n");
            System.out.format("> java -jar %s.jar [date] [path/to/file.log]\n", APP_NAME);
            System.out.format("NOTE: \tThe expected date format is [%s]", "YYYY-MM-DD");
            System.exit(1);
        }

        String date = args[0];
        Path logFile = Paths.get(args[1]);

        LogParser logParser = new LogParser();

        try {
            logParser.parse(logFile);
            System.out.println("Log file parsed successfully.");
        } catch (IOException exp) {
            System.out.println(exp);
            System.exit(1);
        }

        if (logParser.getDateEventCollection().get(date)== null){
            System.out.format("Nothing seem to have happened on %s", date);
            System.exit(0);
        }

        try {
            logParser.getDateEventCollection().get(date).forEach(App::sortAndDetermine);
        } catch (NullPointerException exp) {
            exp.printStackTrace();
            System.exit(1);
        }

        printSummary(date, logFile.toString());
        System.exit(0);

    }

    private static void sortAndDetermine(LineParser lp){

        if(!lp.getBarcode().isEmpty()){
            String projectCode = lp.getBarcode().substring(0, 5);

            if(!projectSummaries.containsKey(projectCode)){
                HashMap<String, Integer> events = new HashMap<>();
                events.put("numberOfManualIntervention", 0);
                events.put("numberOfIncomingFiles", 0);
                projectSummaries.put(projectCode, events);
            }

            Map<String, Integer> events = projectSummaries.get(projectCode);
            events.put("numberOfIncomingFiles", events.get("numberOfIncomingFiles") + 1);

            if(lp.requiresManualIntervention()){
                events.put("numberOfManualIntervention", events.get("numberOfManualIntervention") + 1);
            }

        } else{
            if(lp.requiresManualIntervention()){
                listWithManualInterventions.add(lp.getFile());
            }
        }

    }

    private static void printSummary(String date, String logFile){
        System.out.println("-------------------");
        System.out.println("Log overview");
        System.out.println("Date: " + date);
        System.out.println("Log: " + logFile);
        System.out.println("-------------------");

        projectSummaries.forEach(App::printStats);

        System.out.println("-------------------");
        System.out.println("Files without barcode:");
        listWithManualInterventions.forEach( e -> {
            System.out.format("Filepath:\t%s\n", e);
        });
    }

    private static void printStats(String s, Map<String, Integer> stringIntegerMap) {
        System.out.format("Project code: %s\n\n", s);
        stringIntegerMap.forEach((k, v) -> {
            System.out.format("%s: %s\n", k, v);
        });
        System.out.println();
    }

}

