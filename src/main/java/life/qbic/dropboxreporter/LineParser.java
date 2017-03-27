package life.qbic.dropboxreporter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sven on 3/21/17.
 */
public class LineParser {

    private static final String DATE_REGEX = "(\\d{4}-\\d{2}-\\d{2})";
    private static final String BARCODE_REGEX = "(Q[A-X0-9]{4}[0-9]{3}[A-X][A-X0-9])";
    private static final String FULL_PATH_REGEX = "([\\/][\\.]*)\\w+\\b(\\.*)+(.*)";

    private String date = "";
    private String source = "";
    private String barcode = "";
    private Boolean manualIntervention = false;
    private String file = "";

    public LineParser(String exampleLine) {

        parseLine(exampleLine);

    }

    private void parseLine(String line) {

        retrieveDate(line);

        if(line.contains("manual intervention")){
            manualIntervention = true;
        }

        findFilePath(line);

        if (line.contains("New file arrived")){
            retrieveSource(line);
            findBarcode(line);
        }
    }

    /**
     * Try to find a QBiC barcode
     * @param line A line possibly containing a barcode
     */
    private void findBarcode(String line) {

        Pattern p = Pattern.compile(BARCODE_REGEX);
        Matcher m = p.matcher(line);

        if(m.find()){
            this.barcode = m.group();
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
        Pattern p = Pattern.compile(DATE_REGEX);
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

    public String getBarcode() {
        return this.barcode;
    }

    public Boolean requiresManualIntervention(){
        return manualIntervention;
    }

    private void findFilePath(String line){
        Pattern p = Pattern.compile(FULL_PATH_REGEX);
        Matcher m = p.matcher(line);

        if(m.find()){
            file = m.group();
        }
    }

    public String getFile(){
        return this.file;
    }

}
