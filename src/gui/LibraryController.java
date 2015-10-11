package gui;

import model.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LibraryController extends JFrame {
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JMenuBar menuBar;
    private JPanel panel1; // Register Member
    private JPanel panel2; // Borrow Book
    private JPanel panel3; // Return Book
    private JPanel panel4; // Pay Fine
    private JPanel panel5; // Browse Book

    // Models
    private static final LibrarySystem library = new LibrarySystem();

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
        setBounds(100, 100, 600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Load all data from file into model
        // initLibrarySystem();
        // Initialize all panels
        initTabs();
        getContentPane().add(tabbedPane);
        //addWindowListener(mListener);
    }


    private void initTabs() {
        menuBar = new SystemMenu(this);
        setJMenuBar(menuBar);
        panel1 = new RegisterMember(this); // Register Member
        panel2 = new BorrowBook(this); // Borrow Book
        panel3 = new ReturnBook(this); // Return Book
        panel4 = new PayFine(this); // Pay Fine
        panel5 = new BrowseBook(this); // Browse Book
        tabbedPane.add(RegisterMember.TAB_TITLE, panel1);
        tabbedPane.add(BorrowBook.TAB_TITLE, panel2);
        tabbedPane.add(ReturnBook.TAB_TITLE, panel3);
        tabbedPane.add(PayFine.TAB_TITLE, panel4);
        tabbedPane.add(BrowseBook.TAB_TITLE, panel5);
        tabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    loadListData();
                }
            }
        });

    }

    public static void load() {
        loadBooks();
        loadMembers();
        initRelations();
        //library.displayAllMembers();

    }

    public static void save() {
        saveBooks();
        saveMembers();
        //library.displayAllMembers();
    }

    public void loadListData() {
        ((ListViewPanel) panel2).loadData();
        ((ListViewPanel) panel3).loadData();
        ((ListViewPanel) panel4).loadData();
        ((ListViewPanel) panel5).loadData();
    }

    public void registerMember(LibraryMember member) {
        if(library.addMember(member)) {
            showDialog("New member added to system succesfully");
        } else {
            showDialog("Duplicate member ID found - " +
                    "member not added to system!");
        }
    }

    public static void loadBooks() {
        Log.d("Loading book data into system...");
        library.loadAllBooks();
        Log.d("Book data loading complete.");
    }

    public static void loadMembers() {
        Log.d("Loading member data into system...");
        library.loadAllMembers();
        Log.d("Member data loading complete.");
    }

    public static void initRelations() {
        Log.d("Loading borrowing relation data...");
        ((LibrarySystem) library).initRelations();
        Log.d("Relation loading completed.");
    }

    public static void saveBooks() {
        Log.d("Saving book data out to file");
        library.saveAllBooks();
        Log.d("Saving of book data to file complete.");

    }

    public static void saveMembers() {
        Log.d("Saving member data out to file");
        library.saveAllMembers();
        Log.d("Saving of member data to file complete.");

    }

    public String[] getBooksIDArray() {
        try {
            String[] bIDs = library.bookMap.keySet().toArray(new String[library.bookMap.size()]);
            return bIDs;
        } catch (NullPointerException ne) {
            return new String[0];
        }
    }

    public String[] getBooksIDArrayByMatchingTitle(String str) {
        try {
            ArrayList<LibraryBook> al = new ArrayList<>(library.bookMap.values());
            ArrayList<String> nl = new ArrayList<>();
            for (LibraryBook b : al) {
                if (((Book) b).getTitle().toLowerCase().indexOf(str.toLowerCase()) != -1) {
                    Log.d(((Book) b).getTitle());
                    nl.add(b.getBookNumber());
                }
            }
            String[] bIDs = nl.toArray(new String[nl.size()]);
            return bIDs;
        } catch (NullPointerException ne) {
            return new String[0];
        }
    }

    public String[] getMembersIDArray() {
        String[] mIDs = library.memberMap.keySet().toArray(new String[library.memberMap.size()]);
        return mIDs;
    }

    public String[] getStudentsIDArray() {
        String[] mIDs;
        ArrayList<String> c = new ArrayList<>();
        try {
            for (LibraryMember lm : library.memberMap.values()) {
                if (lm instanceof Student) {
                    c.add(lm.getMemberID());
                }
            }
            mIDs = c.toArray(new String[c.size()]);
            return mIDs;
        } catch (NullPointerException ne) {
            return new String[0];
        }

    }

    public LibraryBook getBookByID(String id) {
        return library.getBook(id);
    }

    public LibraryMember getMemberByID(String id) {
        return library.getMember(id);
    }

    public double payFine(String memberID, double amount)
    {

        LibraryMember member = library.getMember(memberID);

        Student s = (Student)member;

        double change = s.payFine(amount);

        if (change > 0) {
            showDialog(String.format(" - change due: $%.2f\n", change));
            change = 0;
        }
        showDialog("Fines is paid successfully by " + memberID + ".");
        return change;
    }

    public boolean borrowBook(String memberID, String bookNumber) {
        boolean result = false;
        try
        {
            result = library.borrowBook(memberID, bookNumber);

            if (result == true)
                showDialog("Book " + bookNumber +
                        " borrowed successfully by " + memberID + ".");
            else
                showDialog("Error - member " + memberID + " not found.");
        }
        catch (LoanException e)
        {
            showDialog(e.getMessage());
        }

        return result;
    }

    public boolean returnBook(String memberID, String bookNumber, int days)
    {
        boolean result = false;
        try
        {
            result = library.returnBook(memberID, bookNumber, days);

            if (result == true)
                showDialog("Book " + bookNumber +
                        " returned successfully by " + memberID + ".");
            else
                showDialog("Error - member " + memberID + " not found.");
        }
        catch (LoanException e)
        {
            showDialog(e.getMessage());
        }
        return result;

    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
