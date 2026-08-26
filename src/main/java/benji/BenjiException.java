package benji;

/**
 * Raises by the Benji Chatbot if an exception is identified.
 */
public class BenjiException extends Exception {
    public BenjiException(String message) {

        super(message);
    }
}
