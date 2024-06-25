package org.oleg;

import lombok.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataContainer {
    private String Description;
    private int ID;

    public void writeToFileNIO(String filePath) throws IOException {
        String data = "Description: " + Description + "\n" + "ID: " + ID + "\n";
        Files.write(Paths.get(filePath), data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
    }
}