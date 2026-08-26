package benji;

/**
 * Raises by the Benji Chatbot if an exception is identified.
 */
public class BenjiException extends Exception {

    /**
     * Creates the Benji exception.
     * @param message
     */
    public BenjiException(String message) {
        super(message);
    }
}
