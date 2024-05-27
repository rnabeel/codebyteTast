package com.task.jwt.security.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;
import java.sql.Timestamp;

@ControllerAdvice
public class ApplicationExceptionMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionMapper.class);

    public ApplicationExceptionMapper() {
    }

    @ExceptionHandler({Throwable.class})
    protected ResponseEntity<GeneralError> handleConflict(Exception ex, WebRequest request) {
        this.logExceptionStackTrace(ex);
        return new ResponseEntity(new GeneralError(ex.getMessage(), MDC.get("request_id")), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ApplicationException.class})
    protected ResponseEntity<APIError> handleConflict(ApplicationException ex, WebRequest request) {
        this.logExceptionStackTrace(ex);
        return new ResponseEntity(new APIError(ex.getErrorCode(), ex.getArguments(), ex.getLocalizedMessage(), MDC.get("request_id")), HttpStatus.BAD_REQUEST);
    }

    private void logExceptionStackTrace(Exception ex) {
        ExceptionDAO exceptionDAO = ExceptionUtil.toExceptionDAO(ex);
        LOGGER.error(exceptionDAO.getRequestId(), exceptionDAO.getExceptionArgs());
    }

    private static class GeneralError implements Serializable {
        private static final long serialVersionUID = 1L;
        private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        private String debugMessage;
        private String trackingId;

        public GeneralError(String debugMessage, String trackingId) {
            this.debugMessage = debugMessage;
            this.trackingId = trackingId;
        }

        public GeneralError() {
        }

        public Timestamp getTimestamp() {
            return this.timestamp;
        }

        public String getDebugMessage() {
            return this.debugMessage;
        }

        public String getTrackingId() {
            return this.trackingId;
        }
    }

    public static class APIError extends GeneralError implements Serializable {
        private static final long serialVersionUID = 1L;
        private String errorCode;
        private Object[] arguments;

        public APIError(String errorCode, Object[] arguments, String debugMessage, String trackingId) {
            super(debugMessage, trackingId);
            this.errorCode = errorCode;
            this.arguments = arguments;
        }

        public APIError(String errorCode, Object[] arguments) {
            this.errorCode = errorCode;
            this.arguments = arguments;
        }

        public APIError() {
        }

        public String getErrorCode() {
            return this.errorCode;
        }

        public Object[] getArguments() {
            return this.arguments;
        }
    }
}
