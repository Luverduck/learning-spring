package springdb.jdbc.repository.exceptioin;

public class MyDuplicateKeyException extends MyDbException {

    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException() {
    }
}
