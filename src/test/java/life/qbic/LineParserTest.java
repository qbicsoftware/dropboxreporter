package life.qbic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by sven on 3/21/17.
 */
public class LineParserTest {

    private LineParser lineParser;

    @Before
    public void setUp() throws LineParserException {
        String exampleLine = "2015-06-03 17:10:47,825 dropboxhandler    line:385  (levelname)-8s New file arrived for dropbox dmcegat: /mnt/nfs/qbic/dropboxes/dmcegat/incoming/20150601095546_QC005007R0_S243_2_somatic_missense.vcf";
        lineParser = new LineParser(exampleLine);
    }

    @Test
    public void parse_line_and_get_date() throws LineParserException{
        Assert.assertTrue(lineParser.getDate().equals("2015-06-03"));
    }

    @Test
    public void parse_line_and_get_dropboxname() throws LineParserException{
        Assert.assertEquals("Expected", "dmcegat", lineParser.getSource());
    }

    @Test (expected = LineParserException.class)
    public void parse_line_and_throw_exception() throws LineParserException{
        new LineParser("Ohne Datum");
    }

    @Test
    public void check_regex_matcher(){

        Pattern p = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");
        Matcher m = p.matcher("2015-02-06 Blalala");

        Assert.assertTrue("Date pattern match should have worked.", m.find());

    }

}