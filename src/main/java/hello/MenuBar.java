package hello;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class MenuBar extends JMenuBar implements ActionListener {

    private JMenuItem saveItem, openItem, saveAsItem;
    private JCheckBox nightModeCheckbox;
    private FileService fileService;
    private UserInterface userInterface;
    private JMenu settingsMenu;

    MenuBar(FileService fileService, UserInterface userInterface) {

        this.fileService = fileService;
        this.userInterface = userInterface;

        JMenu menu = new JMenu("Menu");

        saveItem = new JMenuItem("Save", KeyEvent.VK_S);
        menu.add(saveItem);
        saveItem.addActionListener(this);

        saveAsItem = new JMenuItem("Save as", KeyEvent.VK_A);
        menu.add(saveAsItem);
        saveAsItem.addActionListener(this);

        openItem = new JMenuItem("Open", KeyEvent.VK_O);
        menu.add(openItem);
        openItem.addActionListener(this);

        this.add(menu);

        settingsMenu = new JMenu("Settings");

        this.loadStyles();

        this.add(settingsMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(e.getActionCommand().equals("change_style")) {
            String fileName = ((JRadioButton)source).getClientProperty("style_file").toString();
            userInterface.setStyle(fileName);
        }

        if (source == saveItem) {
            this.saveFile();
        } else if (source == saveAsItem) {
            this.saveFileAs();
        } else if (source == openItem) {
            this.openFile();
        }
    }

    private void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();

        int returnVal = fileChooser.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileService.saveFile(fileChooser.getSelectedFile().toString());
        }
    }

    private void saveFile() {

        if (fileService.getCurrentlyOpenedFile() == null) {
            JFileChooser fileChooser = new JFileChooser();

            int returnVal = fileChooser.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fileService.saveFile(fileChooser.getSelectedFile().toString());
            }
        } else {
            fileService.saveFile(fileService.getCurrentlyOpenedFile());
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileService.openFile(fileChooser.getSelectedFile().toString());
        }
    }

    private void loadStyles() {

        File folder = new File("./styles");
        File[] files = folder.listFiles();

        ButtonGroup styleSelectRadioGroup = new ButtonGroup();

        for(File file : files) {

            String fileName = file.getName();

            if(fileName.substring(fileName.length() - 4).toLowerCase().equals("json")) {
                JRadioButton styleButton = new JRadioButton(fileName.substring(0, fileName.length() - 5));
                styleButton.setActionCommand("change_style");
                styleButton.putClientProperty("style_file", file.getName());
                styleButton.addActionListener(this);
                styleSelectRadioGroup.add(styleButton);
                settingsMenu.add(styleButton);
            }

        }
    }
}