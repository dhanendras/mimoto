package io.mosip.mimoto.exception;

public class InvalidIssuerIdException extends BaseUncheckedException {

    /**
     * Unique id for serialization
     */
    private static final long serialVersionUID = -5350213197226295789L;

    /**
     * Constructor with errorCode, and errorMessage
     *
     * @param errorCode    The error code for this exception
     * @param errorMessage The error message for this exception
     */
    public InvalidIssuerIdException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public InvalidIssuerIdException(String errorMessage) {
        super(PlatformErrorMessages.INVALID_ISSUER_ID_EXCEPTION.getCode(), errorMessage);
    }

}
