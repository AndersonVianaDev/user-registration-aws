package com.anderson.msuser.shared.exceptions;

import java.time.LocalDateTime;

public class StandardException {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

    public StandardException(LocalDateTime timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
