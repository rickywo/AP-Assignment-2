package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ricky Wu on 2015/10/11.
 */

public class SystemMenu extends JMenuBar {
    private static final String SYSTEM = "System";
    private static final String LOAD = "Load Data";
    private static final String SAVE = "Save Data";
    private static final String EXIT = "Exit";
    private static final String EXIT_CONFIRM_MESSAGE = "Click Yes button to exit Library System";
    private static final String EXIT_PROGRESSING_MESSAGE = "Program is exiting ...";
    private LibraryController controller;
    private JMenu system = new JMenu(SYSTEM);
    private JMenuItem loadData = new JMenuItem(LOAD);
    private JMenuItem saveData = new JMenuItem(SAVE);
    private JMenuItem exitLibrary = new JMenuItem(EXIT);

    SystemMenu(LibraryController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        system.add(loadData);
        system.add(saveData);
        system.add(exitLibrary);

        // Add action listener to Load data option for reading data from file
        loadData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                LibraryController.load();
                controller.loadListData();
            }
        });

        // Add action listener to save data option for writing data to file
        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                LibraryController.save();
            }
        });

        // Add action listener to exit option for exiting program
        exitLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO: show confirm dialog for getting confirmation from user to quit program
                int result = JOptionPane.showConfirmDialog(controller, EXIT_CONFIRM_MESSAGE, "Confirm", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) {
                    // TODO: Exit program after close message dialog
                    JOptionPane.showMessageDialog(controller, EXIT_PROGRESSING_MESSAGE);
                    System.exit(0);
                }
            }
        });
        add(system);
    }
}
