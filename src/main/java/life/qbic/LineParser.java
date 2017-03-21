package life.qbic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sven on 3/21/17.
 */
public class LineParser {

    private String date = "";
    private String source = "";

    public LineParser(String exampleLine) {

        parseLine(exampleLine);

    }

    private void parseLine(String line) {

        retrieveDate(line);

        if (line.contains("New file arrived")){
            retrieveSource(line);
        }
    }

    private void retrieveSource(String line) {

        String[] list =  line.split("\\s+");

        for (int i = 0; i < list.length; i++){
            if (list[i].equals("dropbox") && i < list.length - 1){
                this.source = list[i+1].replace(":", "");
                break;
            }
        }

    }

    private void retrieveDate(String line) {
        Pattern p = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");
        Matcher m = p.matcher(line);

        if (!m.find()){
            throw new LineParserException("Line does not contain a date");
        }
        String datePart = line.split("\t")[0];
        date = datePart.split("\\s+")[0];

    }

    public String getDate(){
        return this.date;
    }


    public String getSource() {
        return this.source;
    }
}
