package hello;

import service.fileService.FileService;

class UserInterfaceFileService {
    
    private UserInterface userInterface;
    private TextEditor textEditor;
    private String currentlyOpenedFile;
    private FileService fileService;

    UserInterfaceFileService(UserInterface userInterface, TextEditor textEditor) {
        this.userInterface = userInterface;
        this.textEditor = textEditor;
        this.fileService = new FileService();
    }

    boolean saveFile(String fileName) {
        try {
            fileService.save(fileName, textEditor.getEditorText());
            userInterface.setWindowTitle(fileName);
            textEditor.setTextChanged(false);
            currentlyOpenedFile = fileName;
        } catch (Exception exception) {
            System.out.println("Saving to file failed: " + exception.toString());
            return false;
        }
        return true;
    }

    boolean openFile(String fileName) {
        try {
            textEditor.setEditorText(fileService.open(fileName));
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
