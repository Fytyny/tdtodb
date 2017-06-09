package Exceptions;

/**
 * Created by Cziczarito on 09.06.2017.
 */
public class NoSuchDbTypeException extends Exception {
    public NoSuchDbTypeException() {
        super();
    }

    public NoSuchDbTypeException(String message) {
        super(message);
    }

    public NoSuchDbTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDbTypeException(Throwable cause) {
        super(cause);
    }

    protected NoSuchDbTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
