package service.fileService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {
    public String open(String fileName) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        String line;
        StringBuilder text = new StringBuilder();

        while ((line = fileReader.readLine()) != null) {
            text.append(line).append("\n");
        }

        return text.toString();
    }

    public void save(String fileName, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(content);
        fileWriter.close();
    }
}
