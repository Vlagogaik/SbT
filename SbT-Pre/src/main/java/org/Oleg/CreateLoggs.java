package org.Oleg;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class CreateLoggs {
    private static final String LOG_DIR = "F:\\SbT\\SbT-Pre\\log";
    private static Path logFilePath;
    public static void createLogDir() {
        Path logPath = Paths.get(LOG_DIR);
        if (!Files.exists(logPath)) {
            try {
                Files.createDirectory(logPath);
            } catch (IOException e) {
                System.err.println("Error 001: " + LOG_DIR);
                e.printStackTrace();
            }
        }
    }
    public static Path createLogFile() {
        createLogDir();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String logFileName = "log_" + timestamp + ".txt";
        Path logFilePath = Paths.get(LOG_DIR, logFileName);

        try {
            return Files.createFile(logFilePath);
        } catch (IOException e) {
            System.err.println("Error 002: " + logFileName);
            e.printStackTrace();
            return null;
        }
    }


    public static void cleanOldLogFiles() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(LOG_DIR))) {
            List<Path> files = Files.list(Paths.get(LOG_DIR))
                    .filter(Files::isRegularFile)
                    .sorted(Comparator.comparingLong(p -> p.toFile().lastModified()))
                    .collect(Collectors.toList());

            while (files.size() > 2) {
                Files.delete(files.remove(0));
            }
        } catch (IOException e) {
            System.err.println("Error 003: " + LOG_DIR);
            e.printStackTrace();
        }
    }
    public static void writeInLogFile(String message) {
        if (logFilePath == null) {
            logFilePath = createLogFile();
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMessage = timestamp + " - " + message;

        try (BufferedWriter writer = Files.newBufferedWriter(logFilePath, StandardOpenOption.APPEND)) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error 004: " + logFilePath);
            e.printStackTrace();
        }
    }
}
