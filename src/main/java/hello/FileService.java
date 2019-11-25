package hello;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

class FileService {
    
    private UserInterface userInterface;
    private TextEditor textEditor;
    private String currentlyOpenedFile;

    FileService(UserInterface userInterface, TextEditor textEditor) {
        this.userInterface = userInterface;
        this.textEditor = textEditor;
    }

    boolean saveFile(String fileName) {
        try {

            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(textEditor.getEditorText());

            userInterface.setWindowTitle(fileName);
            textEditor.setTextChanged(false);
            currentlyOpenedFile = fileName;

            fileWriter.close();
        } catch (Exception exception) {
            System.out.println("Saving to file failed: " + exception.toString());
            return false;
        }
        return true;
    }

    boolean openFile(String fileName) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            String line;

            StringBuilder text = new StringBuilder();

            while ((line = fileReader.readLine()) != null) {
                text.append(line).append("\n");
            }

            textEditor.setEditorText(text.toString());
            textEditor.setTextChanged(false);
            userInterface.setWindowTitle(fileName);

            currentlyOpenedFile = fileName;

        } catch (Exception exception) {
            System.out.println("Opening file failed: " + exception.toString());
            return false;
        }
        return true;
    }

    String getCurrentlyOpenedFile() {
        return this.currentlyOpenedFile;
    }
}
