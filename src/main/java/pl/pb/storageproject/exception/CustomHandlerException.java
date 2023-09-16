package pl.pb.storageproject.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pb.storageproject.model.Category;

@ControllerAdvice
public class CustomHandlerException {

    private final Logger logger = LogManager.getLogger(getClass());

    @ExceptionHandler(UserDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userDuplicateHandler(UserDuplicateException exception) {
        logger.error("Exception: " + exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(CategoryNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String categoryNotExistHandler(CategoryNotExistException exception) {
        logger.error("Exception: " + exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(InformationNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String informationNotExistHandler(InformationNotExistException exception) {
        logger.error("Exception: " + exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(InformationDuplicateException.class)
    public String informationDuplicateHandler(InformationDuplicateException exception) {
        logger.error("Exception: " + exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(RoleNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String roleNotExistHandler(RoleNotExistException exception) {
        logger.error("Exception: " + exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(RoleDuplicateException.class)
    public String roleDuplicateHandler(RoleDuplicateException exception) {
        logger.error("Exception: " + exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotExistHandler(UserNotExistException exception) {
        logger.error("Exception: " + exception.getMessage());
        return exception.getMessage();
    }

}
