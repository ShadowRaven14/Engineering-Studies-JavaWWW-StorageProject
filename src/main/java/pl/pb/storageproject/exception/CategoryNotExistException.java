package pl.pb.storageproject.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryNotExistException extends RuntimeException{

    private final Logger logger = LogManager.getLogger(getClass());

    public CategoryNotExistException(String message) {
        super(message);
        logger.error("Exception: " + message);
    }
}
