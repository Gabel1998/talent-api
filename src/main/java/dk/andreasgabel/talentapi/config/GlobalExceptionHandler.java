package dk.andreasgabel.talentapi.config;

import dk.andreasgabel.talentapi.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex,
                                                                       HttpServletRequest request) {
        Locale locale = resolveLocale(request);
        String message = messageSource.getMessage(ex.getMessage(), null, ex.getMessage(), locale);
        String error = messageSource.getMessage("error.not_found", null, "Not Found", locale);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody(404, error, message));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoResourceFoundException ex,
                                                               HttpServletRequest request) {
        Locale locale = resolveLocale(request);
        String error = messageSource.getMessage("error.not_found", null, "Not Found", locale);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody(404, error, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                    HttpServletRequest request) {
        Locale locale = resolveLocale(request);
        String error = messageSource.getMessage("error.bad_request", null, "Bad Request", locale);
        String message = messageSource.getMessage("error.invalid_parameter",
                new Object[]{ex.getName()}, "Invalid parameter: " + ex.getName(), locale);
        return ResponseEntity.badRequest().body(errorBody(400, error, message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex, HttpServletRequest request) {
        Locale locale = resolveLocale(request);
        String message = messageSource.getMessage("error.internal", null, "An unexpected error occurred", locale);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorBody(500, "Internal Server Error", message));
    }

    private Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader("Accept-Language");
        if (lang != null && lang.toLowerCase().startsWith("da")) {
            return Locale.forLanguageTag("da");
        }
        return Locale.ENGLISH;
    }

    private Map<String, Object> errorBody(int status, String error, String message) {
        return Map.of(
                "timestamp", Instant.now().toString(),
                "status", status,
                "error", error,
                "message", message
        );
    }
}
