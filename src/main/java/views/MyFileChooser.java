package views;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.io.*;

public class MyFileChooser extends JFileChooser {
    private static final String FILE_SEPARATOR = File.separator;
    private static final String DEFAULT_PATH = "." + FILE_SEPARATOR + "test-cases";

    public MyFileChooser() {
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Text files", "csv", "txt");
        this.setFileFilter(fileNameExtensionFilter);

        File currentDirectory = new File(DEFAULT_PATH);
        if (!currentDirectory.exists()) {
            currentDirectory.mkdirs();
        }

        this.setCurrentDirectory(currentDirectory);
    }

    public int saveFile(Component parent, String content, String defaultFileName) {
        this.setSelectedFile(new File(DEFAULT_PATH + FILE_SEPARATOR + defaultFileName));
        this.updateUI();

        int response = super.showSaveDialog(parent);

        if (response == JFileChooser.APPROVE_OPTION) {
            this.printFile(parent, content, defaultFileName);
        }

        return response;
    }

    private void printFile(Component parent, String content, String defaultFileName) {
        if (this.getSelectedFile().exists()) {
            int option = JOptionPane.showConfirmDialog(parent, "Another file with the same name already exists in \"" + this.getSelectedFile().getParentFile().getName() + "\".\nReplacing it will overwrite it content.", "Replace file \"" + this.getSelectedFile().getName() + "\"?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (option == JOptionPane.CLOSED_OPTION) {
                return;
            } else if (option != JOptionPane.YES_OPTION) {
                this.saveFile(parent, content, defaultFileName);
                return;
            }
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.getSelectedFile()));

            bw.write(content);

            bw.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
