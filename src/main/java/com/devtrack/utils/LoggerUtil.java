package com.devtrack.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    public static Logger getLogger(Class<?> classRef) {
        return LoggerFactory.getLogger(classRef);
    }
}