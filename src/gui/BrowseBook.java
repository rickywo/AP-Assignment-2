package gui;

import model.Log;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BrowseBook extends JPanel {

	private static final String BROWSE_BOOK = "Browse Books";
	private static final String ENTER_KEYWORDS = "Enter keyword(s)";
	private static final String MATCHING_BOOKS = "Matching books";
	private static final String BOOK_NUMBER = "Book Number";
	private static final String TITLE = "Title";
	private static final String AUTHOR = "Author";
	private static final String AVAILABLE = "Available";
	private static final String BORROWER_ID = "Borrower ID";

    public static final String TAB_TITLE = BROWSE_BOOK;

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
	/**
	 * Create the panel.
	 */
	public BrowseBook() {
        initContent();
	}

    private void initContent() {
        add(enter_keyword_label);
        add(keyword_textfield);
        add(matching_label);
        add(matching_list);
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


}
