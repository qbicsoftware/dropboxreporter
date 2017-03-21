package life.qbic;

import javax.sound.sampled.Line;

/**
 * Created by sven on 3/21/17.
 */
public class LineParserException extends RuntimeException {

    public LineParserException() {
        super();
    }

    public LineParserException(String message){
        super(message);
    }

    public LineParserException(Throwable t){
        super(t);
    }

    public LineParserException(String message, Throwable t){
        super(message, t);
    }
}
