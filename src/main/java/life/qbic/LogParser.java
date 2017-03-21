package life.qbic;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sven on 3/21/17.
 */
public class LogParser {


    private Map<String, List> dateEventCollection;

    public void parse(Path logFile) throws IOException{

        // Override existing date event collection
        dateEventCollection = new LinkedHashMap<>();

        BufferedReader br = Files.newBufferedReader(logFile);

        String line;

        while((line=br.readLine()) != null){
            lineParserHelper(line);
        }

        br.close();
    }

    private void lineParserHelper(String line){

    }

    public Map<String, List> getDateEventCollection(){
        return this.dateEventCollection;
    }
}
