package logger;

import org.apache.logging.log4j.Logger;

public final class LogManager {

    private LogManager() {}

    public static Logger getLogger(Class<?> clazz) {
        return org.apache.logging.log4j.LogManager.getLogger(clazz);
    }
}