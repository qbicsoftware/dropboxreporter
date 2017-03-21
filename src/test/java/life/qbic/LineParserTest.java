package life.qbic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by sven on 3/21/17.
 */
public class LineParserTest {

    private LineParser lineParser;

    @Before
    public void setUp(){
        String exampleLine = "2015-06-03 17:10:47,825 dropboxhandler    line:385  (levelname)-8s New file arrived for dropbox dmcegat: /mnt/nfs/qbic/dropboxes/dmcegat/incoming/20150601095546_QC005007R0_S243_2_somatic_missense.vcf";
        lineParser = new LineParser(exampleLine);
    }

    @Test
    public void parse_line_and_get_date(){
        Assert.assertTrue(lineParser.getDate().equals("2015-06-03"));
    }

}