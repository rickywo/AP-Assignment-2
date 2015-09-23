package gui;

import java.awt.EventQueue;

import javax.swing.*;

public class LibraryController extends JFrame {
    private final static int PANEL_COUNT = 5;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel panel1 = new RegisterMember(); // Register Member
    private JPanel panel2 = new BorrowBook(); // Borrow Book
    private JPanel panel3 = new ReturnBook(); // Return Book
    private JPanel panel4 = new PayFine(); // Pay Fine
    private JPanel panel5 = new BrowseBook(); // Browse Book

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LibraryController frame = new LibraryController();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LibraryController() {
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initTabs();
        getContentPane().add(tabbedPane);
    }

    private void initTabs() {
        tabbedPane.add(RegisterMember.TAB_TITLE, panel1);
        tabbedPane.add(BorrowBook.TAB_TITLE, panel2);
        tabbedPane.add(ReturnBook.TAB_TITLE, panel3);
        tabbedPane.add(PayFine.TAB_TITLE, panel4);
        tabbedPane.add(BrowseBook.TAB_TITLE, panel5);

    }

}
