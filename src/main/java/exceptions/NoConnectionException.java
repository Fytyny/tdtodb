package exceptions;

/**
 * Created by Cziczarito on 08.06.2017.
 */
public class NoConnectionException extends Exception {
    public NoConnectionException() {
        super("No db connection has been found");
    }

    public NoConnectionException(String message) {
        super(message);
    }

    public NoConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoConnectionException(Throwable cause) {
        super(cause);
    }

    protected NoConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
