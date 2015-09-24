package gui;

import model.Book;
import model.LibraryBook;
import model.Log;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BrowseBook extends JPanel {

	private static final String BROWSE_BOOK = "Browse Books";
	private static final String ENTER_KEYWORDS = "Enter keyword(s)";
	private static final String MATCHING_BOOKS = "Matching books";
	private static final String BOOK_NUMBER = "Book Number";
	private static final String TITLE = "Title";
	private static final String AUTHOR = "Author";
	private static final String AVAILABLE = "Available";
	private static final String BORROWER_ID = "Borrower ID";
    private static final String YES = "Yes";
    private static final String NO = "No";

    public static final String TAB_TITLE = BROWSE_BOOK;

    // Controller
    private LibraryController controller;

    // Enter Keyword components
    private JLabel enter_keyword_label = new JLabel(ENTER_KEYWORDS);
    private JTextField keyword_textfield = new JTextField();
    // Macing book components
    private JLabel matching_label = new JLabel(MATCHING_BOOKS);
    private JList matching_list = new JList();
    // Book number components
    private JLabel book_number_label = new JLabel(BOOK_NUMBER);
    private JLabel book_number_value = new JLabel();
    // Book title components
    private JLabel book_title_label = new JLabel(TITLE);
    private JLabel book_title_value = new JLabel();
    // Book Author components
    private JLabel book_author_label = new JLabel(AUTHOR);
    private JLabel book_author_value = new JLabel();
    // Availability components
    private JLabel book_availability_label = new JLabel(AVAILABLE);
    private JLabel book_availability_value = new JLabel();
    // Borrower ID components
    private JLabel borrower_id_label = new JLabel(BORROWER_ID);
    private JLabel borrower_id_value = new JLabel();

    private KeyListener mKeyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {

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
            JList list = (JList) e.getSource();
            if (list.getValueIsAdjusting()) {
                // get Book number from list and pass to controller for getting
                // book instance

                LibraryBook b = controller.getBookByID(list.getSelectedValue().toString());
                System.out.println(b.getBookNumber());
                displayBookDetails(b);
            }
        }
    };
	/**
	 * Create the panel.
	 */
	public BrowseBook(LibraryController controller) {
        this.controller = controller;
        initContent();
	}

    private void initContent() {
        add(enter_keyword_label);
        add(keyword_textfield);
        add(matching_label);
        initBooklist();
        add(book_number_label);
        add(book_number_value);
        add(book_title_label);
        add(book_title_value);
        add(book_author_label);
        add(book_author_value);
        add(book_availability_label);
        add(book_availability_value);
        add(borrower_id_label);
        add(borrower_id_value);
    }

    private void initBooklist() {
        add(matching_list);
        matching_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matching_list.setListData(controller.getBooksIDArray());
        matching_list.addListSelectionListener(listSelectionListener);
    }

    public void showBooklist(ArrayList<Book> books) {

    }

    private void displayBookDetails(LibraryBook book) {
        Book b = (Book)book;
        book_number_value.setText(b.getBookNumber());
        book_title_value.setText(b.getTitle());
        book_author_value.setText(b.getAuthor());
        book_availability_value.setText(b.isAvailable() ? YES : NO);
        borrower_id_value.setText(b.getBorrowerID());
    }



}
