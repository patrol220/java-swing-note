package hello;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;


public class UserInterface implements Runnable{

    private JFrame frame;
    private TextEditor editorPane;
    private MenuBar menuBar;
    private Gson gson;

    private void createAndShowGUI() {
        gson = new Gson();
        frame = new JFrame();
        setWindowTitle();

        editorPane = new TextEditor(this);

        UserInterfaceFileService userInterfaceFileService = new UserInterfaceFileService(this, editorPane);

        menuBar = new MenuBar(userInterfaceFileService, this);
        frame.setJMenuBar(menuBar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.getContentPane().add(editorPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void setWindowTitle() {
        frame.setTitle("Notepad");
    }

    void setWindowTitle(String fileName) {
        frame.setTitle("Notepad - " + fileName);
    }

    void setWindowTitleFileChanged() {
        frame.setTitle(frame.getTitle() + " [changed]");
    }

    void setStyle(String fileName) {
        System.out.println(fileName);
    }

    public void run() {
        createAndShowGUI();
    }
}
