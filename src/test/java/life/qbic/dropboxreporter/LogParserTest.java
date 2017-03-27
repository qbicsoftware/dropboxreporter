package life.qbic.dropboxreporter;

import life.qbic.dropboxreporter.LogParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by sven on 3/21/17.
 */
public class LogParserTest {

    LogParser logParser;
    Path logFile;
    Path fakeLogFile;

    @Before
    public void setUp(){
        logFile = Paths.get("src/test/resources/example.log");
        fakeLogFile = Paths.get("example.log");
        logParser = new LogParser();
    }

    @Test (expected = IOException.class)
    public void parse_log_and_raise_IOException() throws IOException{
        logParser.parse(fakeLogFile);
    }

    @Test
    public void parse_example_log_and_succeed() throws IOException{
        logParser.parse(logFile);
    }

    @Test
    public void parse_example_log_and_confirm_entries() throws IOException{
        logParser.parse(logFile);
        Assert.assertTrue("Collection should contain a key \'2015-06-03\'",logParser.getDateEventCollection().containsKey("2015-06-03"));
        Assert.assertTrue("Collection should contain a key \'2015-07-10\'",logParser.getDateEventCollection().containsKey("2015-07-10"));
        Assert.assertEquals("Collection should have date entries ", 2, logParser.getDateEventCollection().keySet().size());
    }


}