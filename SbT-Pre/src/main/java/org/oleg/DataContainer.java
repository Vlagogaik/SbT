package org.oleg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataContainer {
    private String description;
    private int id;
    public void writeToFileNIO(String filePath) throws IOException {
        String data = "Description: " + description + "\n" + "ID: " + id + "\n";
        Files.write(Paths.get(filePath), data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }
}