package de.bo.borm;

/**
 * @author sma@baltic-online.de
 * @version 1.0
 */
public class RuntimeSQLException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Throwable exception;

    public RuntimeSQLException(Throwable exception) {
        this.exception = exception;
    }

    public RuntimeSQLException(String s, Throwable exception) {
        super(s);
        this.exception = exception;
    }

    public Throwable getSQLException() {
        return exception;
    }
}