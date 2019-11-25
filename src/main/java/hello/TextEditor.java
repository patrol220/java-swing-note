package hello;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextEditor extends JScrollPane implements DocumentListener {

    private UserInterface userInterface;
    private JEditorPane editorPane;
    private boolean textChanged = false;

    TextEditor(UserInterface userInterface) {
        this.userInterface = userInterface;

        this.editorPane = new JEditorPane();
        super.getViewport().add(this.editorPane);

        this.editorPane.getDocument().addDocumentListener(this);
    }

    public boolean isTextChanged() {
        return textChanged;
    }

    void setTextChanged(boolean textChanged) {
        this.textChanged = textChanged;
    }

    String getEditorText() {
        return this.editorPane.getText();
    }

    void setEditorText(String text) {
        this.editorPane.setText(text);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(!textChanged) {
            textChanged = true;
            userInterface.setWindowTitleFileChanged();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if(!textChanged) {
            textChanged = true;
            userInterface.setWindowTitleFileChanged();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if(!textChanged) {
            textChanged = true;
            userInterface.setWindowTitleFileChanged();
        }
    }
}
