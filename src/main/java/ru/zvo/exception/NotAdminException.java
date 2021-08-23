package ru.zvo.exception;

public class NotAdminException extends RuntimeException {
    public NotAdminException(String s) {
        super(s);
    }
}
