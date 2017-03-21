package life.qbic;

/**
 * Created by sven on 3/21/17.
 */
public class LineParser {

    private String date;

    public LineParser(String exampleLine) {

        parseLine(exampleLine);

    }

    private void parseLine(String line) {

        retrieveDate(line);


    }

    private void retrieveDate(String line) {

        String datePart = line.split("\t")[0];

        date = datePart.split("\\s+")[0];

    }

    public String getDate(){
        return this.date;
    }


}
