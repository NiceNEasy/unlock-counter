package me.erickim.android.unlockcount;

/**
 * Created by kimeric on 12/1/16.
 */


public class InvalidSessionException extends Exception {
    public InvalidSessionException() {
        super();
    }

    public InvalidSessionException(String message) {
        super(message);
    }

    public InvalidSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSessionException(Throwable cause) {
        super(cause);
    }
}
