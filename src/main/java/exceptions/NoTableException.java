package exceptions;

public class NoTableException extends Exception {
    public NoTableException() {
        super("No table has been found");
    }

    public NoTableException(String message) {
        super(message);
    }

    public NoTableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTableException(Throwable cause) {
        super(cause);
    }

    protected NoTableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
