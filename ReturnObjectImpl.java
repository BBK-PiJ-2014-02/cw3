/**
 * A wrapper containing either an object (the result of an operation
 * on a data structure) or an error value.
 *
 * @author Vasco Horta
 */
public class ReturnObjectImpl implements ReturnObject {
	/**
	 * The error message to be returned
	 */
	private final ErrorMessage error;

	/**
	 * The Object being to be wrapped
	 */
	private final Object obj;


    /**
     * Constructor requiring the Object to passed in
     * to then be wrapped with the ReturnObjectImpl.
     * @param obj the object to the wrapped
     */
    public ReturnObjectImpl(Object obj) {
		this.error = ErrorMessage.NO_ERROR;
		this.obj   = obj;
	}

    /**
     * Constructor for when an error is to be passed.
     * When in error, no need to assign any Object
     * @param ErrorMessage
     */
    public ReturnObjectImpl(ErrorMessage error) {
		this.error = error;
		this.obj   = null;
	}

	/**
	 * Returns whether there has been an error
	 * @return whether there has been an error
	 */
	public boolean hasError() {
		if ( error.equals(ErrorMessage.NO_ERROR) ) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * Returns the error message.
	 *
	 * This method must return NO_ERROR if and only if
	 * {@hasError} returns false.
	 *
	 * @return the error message
	 */
	public ErrorMessage getError() {
		if ( hasError() ) {
			return this.error;
		}
		else {
			return ErrorMessage.NO_ERROR;
		}
	}

	/**
	 * Returns the object wrapped in this ReturnObject, i.e. the
	 * result of the operation if it was successful, or null if
	 * there has been error.
	 *
	 * Note that the output of this method must be null if {@see
	 * hasError} returns true, but the opposite is not true: if
	 * {@see hasError} returns false, this method may or may not
	 * return null.
	 *
	 * @return the return value from the method or null if there has been an
	 *         error
	 */
	public Object getReturnValue() {
	    if ( hasError() ) {
			return null;
		}
		else {
			return this.obj;
		}
	}
}
