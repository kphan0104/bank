package com.exaltit.kata.bankaccount.log;

import com.exaltit.kata.bankaccount.context.ContextManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.ResourceBundle;

public class Audit {
    static {
        applicationVersion = ResourceBundle.getBundle("application").getString("version");
    }

    private static final String applicationVersion;

    private static final Logger LOGGER = LoggerFactory.getLogger("AUDIT");

    private static final String LOG_SEPARATOR = " | ";

    public static void log(Level level, String title) {
        write(level, title, null, null, null);
    }

    public static void log(Level level, String title, String message, Instant start) {
        write(level, title, message, start, null);
    }

    public static void log(Level level, String title, Instant start) {
        write(level, title, null, start, null);
    }

    public static void log(Level level, String title, Throwable exception) {
        write(level, title, null, null, exception);
    }

    private static void write(Level level, String title, String message, Instant start, Throwable exception) {
        StringBuilder sb = new StringBuilder();
        sb.append(infoString("version", applicationVersion))
                .append(infoString("title", title))
                .append(infoString("requestId", ContextManager.get().getRequestId().toString()))
                .append(infoString("message", message))
                .append(infoString("time",
                        Optional.ofNullable(start)
                                .map(begin -> String.valueOf(Duration.between(begin, Instant.now()).toMillis()))
                                .orElse(null)))
                .append(infoString("stackTrace",
                        Optional.ofNullable(exception).map(Audit::getStackTrace).orElse(null)));
        trace(level, sb.toString());
    }

    private static String infoString(String info, String log) {
        if (log == null || log.isBlank()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(LOG_SEPARATOR).append(info).append("=").append(log);
        return sb.toString();
    }

    private static String getStackTrace(final Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        if (throwable != null) {
            sb.append(throwable).append(" ");
            if (throwable.getStackTrace() != null && throwable.getStackTrace().length != 0) {
                int stackDepth = 3;
                for (int i = 0; i < stackDepth && i < throwable.getStackTrace().length; i++) {
                    String className = throwable.getStackTrace()[i].getClassName();
                    String fileName = throwable.getStackTrace()[i].getFileName();
                    String methodName = throwable.getStackTrace()[i].getMethodName();
                    int line = throwable.getStackTrace()[i].getLineNumber();

                    sb.append(className + "." + methodName + "(" + fileName + ":" + line + ")\t");
                }
            }
        }

        return sb.toString();
    }

    private static void trace(Level level, String message) {
        switch (level) {
            case DEBUG:
                LOGGER.debug(message);
                break;
            case INFO:
                LOGGER.info(message);
                break;
            case WARNING:
                LOGGER.warn(message);
                break;
            case ERROR:
                LOGGER.error(message);
                break;
            default:
                break;
        }
    }

    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }
}

