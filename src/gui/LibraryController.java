package gui;

import model.*;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;

public class LibraryController extends JFrame {
    private final static int PANEL_COUNT = 5;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel panel1; // Register Member
    private JPanel panel2; // Borrow Book
    private JPanel panel3; // Return Book
    private JPanel panel4; // Pay Fine
    private JPanel panel5; // Browse Book

    // Models
    private static final LibrarySystem library = new LibrarySystem();

    // Listeners

    private WindowAdapter mListener = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent we) {
            // TODO save all data into nonvolatile storage before close window
            exitLibrarySystem();
            System.exit(0);
        }
    };

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
        // Load all data from file into model
        initLibrarySystem();
        // Initialize all panels
        initTabs();
        getContentPane().add(tabbedPane);
        addWindowListener(mListener);
    }


    private void initTabs() {
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

    }

    private void initLibrarySystem() {
        loadBooks();
        loadMembers();
        initRelations();
        //library.displayAllMembers();

    }

    private void exitLibrarySystem() {
        saveBooks();
        saveMembers();
        //library.displayAllMembers();
    }

    public void registerMember(LibraryMember member) {
        library.addMember(member);

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
        String[] bIDs = library.bookMap.keySet().toArray(new String[library.bookMap.size()]);
        return bIDs;
    }

    public String[] getMembersIDArray() {
        String[] mIDs = library.memberMap.keySet().toArray(new String[library.memberMap.size()]);
        return mIDs;
    }

    public String[] getStudentsIDArray() {
        String[] mIDs;
        ArrayList<String> c = new ArrayList<>();
        for(LibraryMember lm: library.memberMap.values()) {
            if(lm instanceof Student) {
                c.add(lm.getMemberID());
            }
        }
        mIDs = c.toArray(new String[c.size()]);
        return mIDs;
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

        return change;
    }

    public boolean borrowBook(String memberID, String bookNumber) {
        boolean result = false;
        try
        {
            result = library.borrowBook(memberID, bookNumber);

            if (result != true)
                showDialog("Error - member " +  memberID + " not found.");
        }
        catch (LoanException e)
        {
            showDialog(e.getMessage());
            System.out.println(e.getMessage());
        }

        return result;
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
