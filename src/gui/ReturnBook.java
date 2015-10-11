package gui;

import model.Book;
import model.LibraryBook;
import model.Log;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class ReturnBook extends JPanel implements ListViewPanel{
	private static final String RETURN_BOOK = "Return Book";
	private static final String SELECT_BOOK = "Select Book";
	private static final String BOOK_NUMBER = "Book Number";
	private static final String TITLE = "Title";
	private static final String AUTHOR = "Author";
	private static final String AVAILABLE = "Available";
	private static final String ENTER_MEMBER_ID = "Enter Member ID";
    private static final String DAYS_BORROWED = "Days borrowed";
    private static final String YES = "Yes";
    private static final String NO = "No";

	public static final String TAB_TITLE = RETURN_BOOK;

    // Controller
    private LibraryController controller;
    private JPanel west = new JPanel();
    private JPanel center = new JPanel();
    private JScrollPane scrollPane = new JScrollPane();

    private JList book_list = new JList();
    private JLabel select_book_label = new JLabel(SELECT_BOOK, JLabel.LEFT);
    // Book Number components
    private JLabel book_number_label = new JLabel(BOOK_NUMBER + " :");
    private JLabel book_number_value = new JLabel();
    // Book Title components
    private JLabel book_title_label = new JLabel(TITLE + " :");
    private JLabel book_title_value = new JLabel();
    // Book Author components
    private JLabel book_author_label = new JLabel(AUTHOR + " :");
    private JLabel book_author_value = new JLabel();
    // Availability components
    private JLabel book_availability_label = new JLabel(AVAILABLE + " :");
    private JLabel book_availability_value = new JLabel();
    // Days borrowed components
    private JLabel days_label = new JLabel(DAYS_BORROWED + " :");
    private JTextField days_textfield = new JTextField();
    // Member ID components
    private JLabel member_id_label = new JLabel(ENTER_MEMBER_ID + " :");
    private JTextField member_id_textfield = new JTextField();

    private JButton return_button = new JButton(RETURN_BOOK);
    private ActionListener mListener = new ActionListener (){

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO register new member into library system
            Log.d(RETURN_BOOK);
            try {
            controller.returnBook(member_id_textfield.getText(),
                    book_list.getSelectedValue().toString(),
                    Integer.parseInt(days_textfield.getText()));
                displayBookDetails();
            } catch (NumberFormatException ne) {
                showDialog("Input format error, please check \"" + DAYS_BORROWED+ "\" entered.");
            }
        }

    };

    private KeyListener mKeyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
            return_button.setEnabled(validateInputs());
            Log.d(Boolean.toString(validateInputs()));
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    private ListSelectionListener listSelectionListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (book_list.getValueIsAdjusting()) {
                // get Book number from list and pass to controller for getting
                // book instance
                displayBookDetails();
            }
        }
    };
	/**
	 * Create the panel.
	 */
	public ReturnBook(LibraryController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 0));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        initContent();
	}

    private void initContent() {
        initBooklist();
        initWestView();
        initCenterView();
        add(west, BorderLayout.LINE_START);
        add(center, BorderLayout.CENTER);
    }

    private void initBooklist() {
        scrollPane.setViewportView(book_list);
        //add(scrollPane);
        book_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        book_list.addListSelectionListener(listSelectionListener);
    }

    private void initWestView() {
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
        west.add(select_book_label);
        west.add(scrollPane);
    }

    private void initCenterView() {
        center.setLayout(new GridLayout(0, 2, 10, 0));
        // Book number components
        center.add(book_number_label);
        center.add(book_number_value);
        // Book title components
        center.add(book_title_label);
        center.add(book_title_value);
        // Book author components
        center.add(book_author_label);
        center.add(book_author_value);
        // Book availability components
        center.add(book_availability_label);
        center.add(book_availability_value);
        // Days borrowed components
        center.add(days_label);
        center.add(days_textfield);
        days_textfield.addKeyListener(mKeyListener);
        // Memeber ID components
        center.add(member_id_label);
        center.add(member_id_textfield);
        member_id_textfield.addKeyListener(mKeyListener);
        center.add(new JPanel()); // Dummy placeholder
        // Return Button
        center.add(return_button);
        return_button.setEnabled(false);
        return_button.addActionListener(mListener);
    }

    private void displayBookDetails() {
        Book b = (Book)controller.getBookByID(book_list.getSelectedValue().toString());
        book_number_value.setText(b.getBookNumber());
        book_title_value.setText(b.getTitle());
        book_author_value.setText(b.getAuthor());
        book_availability_value.setText(b.isAvailable()?YES:NO);
    }

    public void showDialog(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private boolean validateInputs() {
        if(member_id_textfield.getText().isEmpty() ||
                days_textfield.getText().isEmpty() ||
                book_list.getSelectedIndex() == -1)
            return false;
        else
            return true;
    }

    public void loadData() {
        book_list.setListData(controller.getBooksIDArray());
    }
}
